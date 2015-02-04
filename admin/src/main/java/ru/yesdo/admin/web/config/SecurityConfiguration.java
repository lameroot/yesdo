package ru.yesdo.admin.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsServiceConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.yesdo.admin.web.security.*;

/**
 * User: Dikansky
 * Date: 29.07.2014
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
@ComponentScan(
		basePackages = {
				"ru.yesdo.admin.web.security"
		},
		basePackageClasses = UserDetailsServiceImpl.class
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private static final String LOGIN_URL = "/index#login";
	private static final int MAXIMUM_SESSIONS = 200;
	public static final String LOGIN_PROCESSING_URL = "/login";
	private AuthenticationManager authManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers(
						"/resources/**", "/app/**", "/ext/**","/**.js", "/**.css", "/**.json"
				);
	}

	@Autowired
	public void registerGlobalAuthentication(
			AuthenticationManagerBuilder auth) throws Exception {
		UserDetailsServiceConfigurer configurer  = new UserDetailsServiceConfigurer(userDetailsService);
		configurer.addObjectPostProcessor(new DaoAuthenticationProviderPostProcessor());
		auth.apply(configurer).passwordEncoder(passwordEncoder());
		auth.authenticationEventPublisher(new DefaultAuthenticationEventPublisher(applicationEventPublisher));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.headers().frameOptions().disable()
				.authorizeRequests()
//					.antMatchers("/locales", "/languages", "/currency", "/users/changepassword").permitAll()
					.antMatchers("/index").permitAll()
					.antMatchers("/user/permissions").permitAll()
					.antMatchers("/users/**").hasAuthority("MANAGE_USERS")
					.antMatchers("/**").fullyAuthenticated()
				.and()
				.exceptionHandling()
					.authenticationEntryPoint(new AjaxAwareLoginUrlAuthenticationEntryPoint(LOGIN_URL))
				.and()
					.logout()
					.logoutSuccessHandler(new AjaxAwareLogoutSuccessHandler())
					.permitAll()
				.and()
					.formLogin()
					.loginProcessingUrl(LOGIN_PROCESSING_URL)
					.loginPage(LOGIN_URL)
					.failureUrl(LOGIN_URL)
					.permitAll()
					.failureHandler(new AjaxAwareAuthenticationFailureHandler())
					.successHandler(new AjaxAwareAuthenticationSuccessHandler())
				.and()
					.sessionManagement()
					.maximumSessions(MAXIMUM_SESSIONS)
					//.sessionRegistry(sessionRegistry())
					.expiredUrl(LOGIN_URL);
	}

	protected AuthenticationManager authenticationManager() throws Exception{
		authManager = super.authenticationManager();
		return authManager;
	}

	@Bean
	public AuthenticationManager authManager() {
		return authManager;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource  messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setDefaultEncoding("UTF8");
		messageSource.setBasename("classpath:org/springframework/security/messages");
		return messageSource;
	}

	private static class DaoAuthenticationProviderPostProcessor implements ObjectPostProcessor<DaoAuthenticationProvider> {
		@Override
		public DaoAuthenticationProvider postProcess(DaoAuthenticationProvider provider) {
			provider.setPostAuthenticationChecks(new DefaultPostAuthenticationChecks());
			provider.setPreAuthenticationChecks(new DefaultPreAuthenticationChecks());
			return provider;
		}
	}
}
