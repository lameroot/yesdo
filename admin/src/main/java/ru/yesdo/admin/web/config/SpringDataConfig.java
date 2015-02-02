package ru.yesdo.admin.web.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.yesdo.model.Merchant;
import ru.yesdo.model.Permission;
import ru.yesdo.model.User;
import ru.yesdo.repository.MerchantRepository;
import ru.yesdo.repository.UserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.stream.Collectors;

/**
 * Created by dsvdsv on 02.01.15.
 */
@Configuration
@EnableJpaRepositories(basePackages = "ru.yesdo.repository")
public class SpringDataConfig  implements InitializingBean {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MerchantRepository merchantRepository;

	@Override
	public void afterPropertiesSet() throws Exception {
		Merchant merchant = new Merchant();
		merchant.setName("test");
		merchantRepository.save(merchant);

		merchant = new Merchant();
		merchant.setName("root");
		merchant = merchantRepository.save(merchant);

		User u = new User();
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		u.setLogin("admin");
		u.setPasswordHash(encoder.encode("admin"));
		u.setPermissions(EnumSet.allOf(Permission.class));
		u = userRepository.save(u);
		u.setMerchant(merchant);
		u = userRepository.save(u);

		u = new User();
		u.setLogin("testadmin");
		u.setPasswordHash(encoder.encode("test"));
		u.setPermissions(EnumSet.allOf(Permission.class));
		u = userRepository.save(u);
		u = userRepository.save(u);
		u.setMerchant(merchant);
		u = userRepository.save(u);


		merchant = new Merchant();
		merchant.setName("test_test");
		u = new User();
		u.setLogin("test");
		u.setPasswordHash(encoder.encode("test"));
		u.setMerchant(merchant);
		u.setPermissions(EnumSet.allOf(Permission.class));
		u = userRepository.save(u);


	}
}
