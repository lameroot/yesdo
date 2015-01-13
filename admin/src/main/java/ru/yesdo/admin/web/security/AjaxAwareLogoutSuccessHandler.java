package ru.yesdo.admin.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.yesdo.admin.web.security.AjaxHelpers.isAjax;
import static ru.yesdo.admin.web.security.AjaxHelpers.sendAjaxSuccess;


/**
 * User: Dikansky
 * Date: 29.07.2014
 */
public class AjaxAwareLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (isAjax(request)) {
			sendAjaxSuccess(response, HttpServletResponse.SC_OK);
		} else {
			super.onLogoutSuccess(request, response, authentication);
		}
	}
}