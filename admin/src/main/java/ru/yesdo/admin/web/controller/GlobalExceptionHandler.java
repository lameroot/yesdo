package ru.yesdo.admin.web.controller;

import com.google.common.base.StandardSystemProperty;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * User: Dikansky
 * Date: 28.02.13
 */
@Component
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


	public static Map<String, ?> errorResponse(String message, List<?> errors) {
		return ImmutableMap.of(
				"error", "true",
				"errors", errors != null ? errors : Collections.emptyList(),
				"message", message
		);
	}

	private static List<Object[]> toErrors(List<ObjectError> objectErrors) {
		List<Object[]> errors = new ArrayList<Object[]>();
		for (ObjectError error : objectErrors) {
			if (error instanceof FieldError) {
				FieldError fieldError = (FieldError) error;
				errors.add(new Object[]{fieldError.getField(), fieldError.getDefaultMessage()});
			}
		}
		return errors;
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, ?> handleAllException(Throwable ex) {
		log.error("Unexpected exception ", ex);
		String msg = ExceptionUtils.getRootCauseMessage(ex);
		return errorResponse(Strings.isNullOrEmpty(msg) ? ex.toString() : msg, null);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, ?> handleValidationException(MethodArgumentNotValidException ex) {

		log.error("Error when performs search action ", ex);

		return errorResponse(ex.getMessage(), toErrors(ex.getBindingResult().getAllErrors()));
	}

	@ExceptionHandler(BindException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, ?> handleValidationException(BindException ex) {
		log.error("Error when performs search action ", ex);
		List<Object[]> errors = toErrors(ex.getAllErrors());
		String message = ex.getMessage();
		if (!ex.getAllErrors().isEmpty()) {
			StringBuilder builder = new StringBuilder();
			for (ObjectError error : ex.getAllErrors()) {
				builder.append(error.getDefaultMessage()).append(StandardSystemProperty.LINE_SEPARATOR.value());
			}
			message = builder.toString();
		}
		return errorResponse(message, errors);
	}
}
