package ru.yesdo.admin.web.security;

import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.yesdo.admin.web.security.AjaxHelpers.isAjax;
import static ru.yesdo.admin.web.security.AjaxHelpers.sendAjaxResponse;


/**
 * User: Dikansky
 * Date: 07.03.13
 */
public class AjaxAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


	public AjaxAwareAuthenticationSuccessHandler() {
		super();

		final RedirectStrategy originStrategy = super.getRedirectStrategy();
		setRedirectStrategy(new RedirectStrategy() {
			@Override
			public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
				if (isAjax(request)) {
					sendAjaxResponse(response, HttpServletResponse.SC_OK, SecurityHelpers.currentEnhancedUser().user());
				} else {
					originStrategy.sendRedirect(request, response, url);
				}
			}
		});
	}
}
