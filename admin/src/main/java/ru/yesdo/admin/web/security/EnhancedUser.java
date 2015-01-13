package ru.yesdo.admin.web.security;

import org.springframework.security.core.GrantedAuthority;
import ru.yesdo.model.User;


import java.util.Collection;

/**
 * User: Dikansky
 * Date: 11.03.13
 */
public class EnhancedUser extends org.springframework.security.core.userdetails.User {

	private final User user;

	public EnhancedUser(
			User userAccount,
			String username,
			String password,
			boolean enabled,
			boolean accountNonExpired,
			boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities
	) {
		super(
				username,
				password,
				enabled,
				accountNonExpired,
				credentialsNonExpired,
				accountNonLocked,
				authorities
		);
		this.user = userAccount;
	}

	public User user() {
		return user;
	}
}
