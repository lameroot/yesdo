package ru.yesdo.admin.web.security;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;
import java.util.Map;

/**
 * User: Dikansky
 * Date: 29.08.13
 */
public class ResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {
	public Map<Object, Object> getMessages(Locale locales){
		return getMergedProperties(locales).getProperties();
	}
}
