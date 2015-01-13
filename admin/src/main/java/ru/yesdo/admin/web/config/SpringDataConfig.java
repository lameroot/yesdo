package ru.yesdo.admin.web.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.yesdo.model.Permission;
import ru.yesdo.model.User;
import ru.yesdo.repository.UserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;

/**
 * Created by dsvdsv on 02.01.15.
 */
@Configuration
@EnableJpaRepositories(basePackages = "ru.yesdo.repository")
public class SpringDataConfig  implements InitializingBean {

	@Autowired
	private UserRepository userRepository;


	@Override
	public void afterPropertiesSet() throws Exception {
		User u = new User();
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		u.setLogin("admin");
		u.setPasswordHash(encoder.encode("admin"));
		u.setPermissions(EnumSet.allOf(Permission.class));
		userRepository.save(u);
	}
}
