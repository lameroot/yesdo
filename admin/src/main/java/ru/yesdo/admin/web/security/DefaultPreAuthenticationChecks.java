package ru.yesdo.admin.web.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * User: Dikansky
 * Date: 28.02.14
 */
public class DefaultPreAuthenticationChecks implements UserDetailsChecker {
	public void check(UserDetails user) {

	}
}
