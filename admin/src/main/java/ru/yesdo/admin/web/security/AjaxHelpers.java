package ru.yesdo.admin.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * User: Dikansky
 * Date: 07.03.13
 */
abstract class AjaxHelpers {
	private static final Logger log = LoggerFactory.getLogger(AjaxHelpers.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();

	static boolean isAjax(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}

	static void sendAjaxError(HttpServletResponse response, int sc, String msg) throws IOException {
		sendAjaxResponse(response, sc, String.format("{success:false, error: true, message: '%s'}", msg));
	}

	static void sendAjaxAccountExpired(HttpServletResponse response, int sc) throws IOException {
		sendAjaxResponse(response, sc, "{success:true, expired: true}");
	}

	static void sendAjaxSuccess(HttpServletResponse response, int sc) throws IOException {
		sendAjaxResponse(response, sc, ImmutableList.of("success", true));
	}

	static void sendAjaxResponse(HttpServletResponse response, int sc, Object content) throws IOException {
		sendAjaxResponse(response, sc, MAPPER.writeValueAsString(content));
	}

	static void sendAjaxResponse(HttpServletResponse response, int sc, String content) throws IOException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(sc);
		response.setCharacterEncoding(Charsets.UTF_8.name());
		Writer out = response.getWriter();
		out.write(content);
		out.close();
	}
}
