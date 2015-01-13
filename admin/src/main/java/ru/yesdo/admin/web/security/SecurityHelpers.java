package ru.yesdo.admin.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * User: Dikansky
 * Date: 26.07.13
 */
public abstract class SecurityHelpers {
	private SecurityHelpers() {
	}

	public static EnhancedUser currentEnhancedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && authentication.getPrincipal() instanceof EnhancedUser ?
				(EnhancedUser) authentication.getPrincipal() : null;
	}

	public static boolean hasPermission(String permission) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}

		Collection<? extends GrantedAuthority> permissions = authentication.getAuthorities();

		if (permissions == null || permissions.isEmpty()) {
			return false;
		}

		return permissions.contains(new SimpleGrantedAuthority(permission));
	}
}
