package ru.yesdo.admin.web.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.yesdo.admin.web.security.AjaxHelpers.isAjax;
import static ru.yesdo.admin.web.security.AjaxHelpers.sendAjaxError;


/**
 * User: Dikansky
 * Date: 06.05.13
 */
public class AjaxAwareAccessDeniedHandler extends AccessDeniedHandlerImpl {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
			throws IOException, ServletException {
		if (isAjax(request)) {
			sendAjaxError(response, HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
		} else {
			super.handle(request, response, exception);
		}
	}
}
