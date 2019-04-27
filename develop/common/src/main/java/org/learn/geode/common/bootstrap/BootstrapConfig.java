package org.learn.geode.common.bootstrap;

import org.learn.geode.common.bootstrap.BootstrapConfigBuilder.AppMode;
import org.springframework.context.ApplicationContext;

public interface BootstrapConfig {
	
	public String getContextPath();
	
	public AppMode getAppMode();
	
	public ApplicationContext getApplicationContext();

}
