package org.learn.geode.common.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class GeodeClientContext implements ContextManager {

	private ApplicationContext clientContext;
	
	public GeodeClientContext(ApplicationContext clientContext) {
		this.clientContext = clientContext;
	}
	
	@Override
	public ApplicationContext getContext() {
		return clientContext;
	}
	
	@Override
	public void destroy() {
		if(clientContext != null && clientContext instanceof AbstractApplicationContext) {
			((AbstractApplicationContext)clientContext).close();
			clientContext = null;
		}
	}

}
