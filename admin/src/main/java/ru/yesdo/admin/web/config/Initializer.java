package ru.yesdo.admin.web.config;

import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by dsvdsv on 01.01.15.
 */
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{TestJpaConfig.class, SpringDataConfig.class, SecurityConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{MvcConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		DelegatingFilterProxy proxy = new DelegatingFilterProxy();
		proxy.setTargetBeanName("springSecurityFilterChain");
		return new Filter[]{proxy};
	}

	@Override
	protected void registerContextLoaderListener(ServletContext servletContext) {
		super.registerContextLoaderListener(servletContext);
		servletContext.addListener(HttpSessionEventPublisher.class);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);


        /*  If set to true, it will use mapped file buffer to serve static content
        when using NIO connector. Setting this value to false means that
        a direct buffer will be used instead of a mapped file buffer.
        By default, this is set to true. Only for Jetty.
        */
		servletContext.setInitParameter(
				"org.eclipse.jetty.servlet.Default.useFileMappedBuffer",
				System.getProperty("useFileMappedBuffer", "true")
		);
		servletContext.setInitParameter("org.eclipse.jetty.servlet.Default.gzip", "true");

		servletContext.addListener(RequestContextListener.class);
	}
}
