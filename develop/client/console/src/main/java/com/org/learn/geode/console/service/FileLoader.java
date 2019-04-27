package com.org.learn.geode.console.service;

import java.util.concurrent.CountDownLatch;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.Registry;
import org.apache.camel.spring.spi.ApplicationContextRegistry;
import org.learn.geode.common.bootstrap.Bootstrap;
import org.learn.geode.common.console.Launchable;
import org.learn.geode.common.exception.GeodeException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.org.learn.geode.console.route.FileLoaderRouteBuilder;

public class FileLoader implements Launchable {
	
	private CamelContext camelContext = null;

	@Override
	public void start(CountDownLatch latch, String... args) throws GeodeException {
		ApplicationContext mainContext = Bootstrap.getInstance().getApplicationContext();
		try {
			ApplicationContext clientContext = new ClassPathXmlApplicationContext(new String[]{"geode-console-client.xml"}, mainContext);
			Bootstrap.getInstance().createClientContext(clientContext);
			
			final Registry registry = new ApplicationContextRegistry(clientContext);
			camelContext = new DefaultCamelContext(registry);
			
			FileLoaderRouteBuilder builder = clientContext.getBean("FileLoaderRouteBuilder", FileLoaderRouteBuilder.class);
			camelContext.addRoutes(builder);
			camelContext.start();
			
			System.out.println("File Consumer is running ...");
		} catch(Exception e) {
			System.out.println("Exception occured");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	@Override
	public void stop() throws GeodeException {
		if(camelContext != null) {
			try {
				camelContext.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
