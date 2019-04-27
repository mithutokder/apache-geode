package org.learn.geode.common.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class GeodeServerContext implements ContextManager {

	private ApplicationContext serverContext;
	
	public GeodeServerContext(ApplicationContext serverContext) {
		this.serverContext = serverContext;
	}
	
	@Override
	public ApplicationContext getContext() {
		return serverContext;
	}
	
	@Override
	public void destroy() {
		if(serverContext != null && serverContext instanceof AbstractApplicationContext) {
			((AbstractApplicationContext)serverContext).destroy();
			serverContext = null;
		}
	}

}
