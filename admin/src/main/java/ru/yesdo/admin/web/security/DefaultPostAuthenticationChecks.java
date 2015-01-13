package ru.yesdo.admin.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * User: Dikansky
 * Date: 28.02.14
 */
public class DefaultPostAuthenticationChecks implements UserDetailsChecker {
	private static final Logger logger = LoggerFactory.getLogger(DefaultPostAuthenticationChecks.class);
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	public void check(UserDetails user) {
		if (!user.isAccountNonLocked()) {
			logger.debug("User account is locked");

			throw new LockedException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked",
					"User account is locked"), user);
		}

		if (!user.isEnabled()) {
			logger.debug("User account is disabled");

			throw new DisabledException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled",
					"User is disabled"), user);
		}

		if (!user.isAccountNonExpired()) {
			logger.debug("User account is expired");

			throw new AccountExpiredException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired",
					"User account has expired"), user);
		}
		if (!user.isCredentialsNonExpired()) {
			logger.debug("User account credentials have expired");

			throw new CredentialsExpiredException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.credentialsExpired",
					"User credentials have expired"), user);
		}
	}
}