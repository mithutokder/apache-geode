package com.org.learn.geode.client.web.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(ApplicationConfiguration.class);
		ctx.setServletContext(servletContext);

		// Manage the lifecycle of the root application context
		servletContext.addListener(new ContextLoaderListener(ctx));

		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		
	}

}
