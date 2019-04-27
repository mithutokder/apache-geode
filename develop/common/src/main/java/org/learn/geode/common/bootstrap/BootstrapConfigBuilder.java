package org.learn.geode.common.bootstrap;

import org.springframework.context.ApplicationContext;

public class BootstrapConfigBuilder  implements BootstrapConfig {
	
	private static final String CONTEXT_PATH = "application-context.xml";
	
	private Builder bootstrapConfig;
	
	private BootstrapConfigBuilder(Builder builder) {
		this.bootstrapConfig = builder;
	}
	
	@Override
	public String getContextPath() {
		return bootstrapConfig.getContextPath();
	}

	@Override
	public AppMode getAppMode() {
		return bootstrapConfig.getAppMode();
	}
	
	public ApplicationContext getApplicationContext() {
		return bootstrapConfig.getApplicationContext();
	}
	
	public static final BootstrapConfig defaultConsoleConfig() {
		return build(CONTEXT_PATH, AppMode.CONSOLE);
	}
	
	public static final BootstrapConfig build(String path, AppMode appMode) {
		return BootstrapConfigBuilder
				.newBuilder()
				.withAppMode(appMode)
				.withContextFile(path)
				.build();
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static class Builder {
		private String springConfigFilePath;
		private AppMode appMode;
		private ApplicationContext applicationContext;

		public Builder(){}
		
		 public Builder withContextFile(final String filePath) {
			 this.springConfigFilePath = filePath;
			 return this;
		 }
		 
		 public Builder withSpringContext(ApplicationContext applicationContext) {
			 this.applicationContext = applicationContext;
			 return this;
		 }
		 public Builder withAppMode(AppMode appMode) {
			 this.appMode = appMode;
			 return this;
		 }
		 
		 public BootstrapConfigBuilder build() {
			 return new BootstrapConfigBuilder(this);
		 }
		 
		public String getContextPath() {
			return springConfigFilePath;
		}
		
		public ApplicationContext getApplicationContext() {
			return applicationContext;
		}

		public AppMode getAppMode() {
			return appMode;
		}
	}
	
	public enum AppMode {
		WEB,CONSOLE;
	}

}
