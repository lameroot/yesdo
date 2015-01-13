package ru.yesdo.admin.web.security;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.yesdo.admin.web.security.AjaxHelpers.isAjax;
import static ru.yesdo.admin.web.security.AjaxHelpers.sendAjaxAccountExpired;
import static ru.yesdo.admin.web.security.AjaxHelpers.sendAjaxError;


/**
 * User: Dikansky
 * Date: 07.03.13
 */
public class AjaxAwareAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
	                                    AuthenticationException exception) throws IOException, ServletException {
		if (isAjax(request)) {
			if (exception instanceof AccountExpiredException) {
				sendAjaxAccountExpired(response, HttpServletResponse.SC_OK);
			} else {
				sendAjaxError(response, HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
			}
		} else {
			super.onAuthenticationFailure(request, response, exception);
		}
	}
}
