package org.learn.geode.common.bootstrap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import org.learn.geode.common.bootstrap.BootstrapConfigBuilder.AppMode;
import org.learn.geode.common.context.ContextManager;
import org.learn.geode.common.context.GeodeClientContext;
import org.learn.geode.common.context.GeodeServerContext;
import org.learn.geode.common.exception.GeodeException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * A singleton class that manages application specific
 * configuration.
 *
 */
public final class Bootstrap {
	
	private static AtomicBoolean started = new AtomicBoolean(Boolean.FALSE); 
	private static Semaphore semaphore = new Semaphore(1);
	
	private ApplicationContext applicationContext;
	private AppMode appMode;
	
	private Map<String, Object> attribute = new ConcurrentHashMap<>();
	
	private static Bootstrap instance = null;
	
	private ContextManager serverContext;
	
	private ContextManager clientContext;
	
	private Bootstrap(BootstrapConfig bootstrapConfig) {
		appMode = bootstrapConfig.getAppMode();
		applicationContext = (bootstrapConfig.getApplicationContext() != null) ? bootstrapConfig.getApplicationContext()
				: new ClassPathXmlApplicationContext(bootstrapConfig.getContextPath());
		String home= System.getProperty("APP_HOME");
		if(!Strings.isNullOrEmpty(home)) {
			attribute.put("APP_HOME", home);
		}
	}
	
	public static Bootstrap getInstance() throws GeodeException {
		if(!started.get()) {
			throw new GeodeException("Application is not yet initialized.");
		}
		return instance;
	}
	
	public static void init(BootstrapConfig bootstrapConfig) throws GeodeException {
		try {
			// acquire lock
			semaphore.acquire();
			if(started.get()) {
				throw new GeodeException("Application already initialized.");
			}
			instance = new Bootstrap(bootstrapConfig);
			started.set(Boolean.TRUE);
			// release the lock
			semaphore.release();
		} catch (InterruptedException e) {
			throw new GeodeException("Unable to acquire lock during application bootstrap");
		}
	}
	
	
	public static void destroy() throws GeodeException {
		if(!started.get()) {
			throw new GeodeException("Application is not yet initialized.");
		}
		
		instance.clear();
		started.set(Boolean.FALSE);
	}
	
	public void createServerContext(ApplicationContext serverContext) {
		Preconditions.checkArgument(serverContext != null, "Server context can not be null");		
		this.serverContext = new GeodeServerContext(serverContext);
	}
	
	public ContextManager getServerContext() {
		return serverContext;
	}
	
	public void createClientContext(ApplicationContext clientContext) {
		Preconditions.checkArgument(clientContext != null, "Client context can not be null");		
		this.clientContext = new GeodeClientContext(clientContext);
	}
	
	public ContextManager getClientContext() {
		return clientContext;
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public AppMode getAppMode() {
		return appMode;
	}
	
	public void put(String key, Object value) {
		attribute.put(key, value);
	}
	
	public Object get(String key) {
		return attribute.get(key);
	}
	
	public <T> T get(String key, Class<T> clazz) {
		return attribute.get(key) == null ? null : clazz.cast(attribute.get(key));
	}

	private void clear() {
		if(this.serverContext != null) {
			this.serverContext.destroy();
			this.serverContext = null;
		}
		if(this.clientContext != null) {
			this.clientContext.destroy();
			this.clientContext = null;
		}
		destroySpringContext(instance.applicationContext);
		instance.applicationContext = null;
		instance.appMode = null;
		instance = null;
	}
	
	private void destroySpringContext(ApplicationContext context) {
		if(context != null && context instanceof AbstractApplicationContext) {
			((AbstractApplicationContext)context).destroy();
		}
	}
}
