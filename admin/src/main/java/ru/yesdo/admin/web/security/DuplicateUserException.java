package ru.yesdo.admin.web.security;

import org.springframework.security.core.AuthenticationException;

/**
 * User: Dikansky
 * Date: 11.03.13
 */
public class DuplicateUserException extends AuthenticationException {

	public DuplicateUserException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
