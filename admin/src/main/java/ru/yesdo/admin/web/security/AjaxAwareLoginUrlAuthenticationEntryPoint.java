package ru.yesdo.admin.web.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.yesdo.admin.web.security.AjaxHelpers.isAjax;
import static ru.yesdo.admin.web.security.AjaxHelpers.sendAjaxError;


/**
 * User: Dikansky
 * Date: 07.03.13
 */
public class AjaxAwareLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
	public AjaxAwareLoginUrlAuthenticationEntryPoint(String loginUrl) {
		super(loginUrl);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		if (isAjax(request)) {
			sendAjaxError(response, HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
		} else {
			super.commence(request, response, exception);
		}
	}
}
