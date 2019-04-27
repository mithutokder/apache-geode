package org.learn.geode.common.context;

import org.springframework.context.ApplicationContext;

public interface ContextManager {

	public ApplicationContext getContext();

	void destroy();
}
