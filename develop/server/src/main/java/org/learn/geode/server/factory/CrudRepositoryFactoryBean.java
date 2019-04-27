package org.learn.geode.server.factory;

import java.io.Serializable;

import org.learn.geode.common.bootstrap.Bootstrap;
import org.learn.geode.server.domain.BaseEntity;
import org.springframework.data.repository.CrudRepository;

public class CrudRepositoryFactoryBean {
	private static final String beanNamePattern = "%sCrudRepository";
	
	@SuppressWarnings("unchecked")
	public static <K extends Serializable, V extends BaseEntity> CrudRepository<V, K> getCrudRepository(String type) {
		final String beanName = String.format(beanNamePattern, type);
		return Bootstrap.getInstance().getApplicationContext().getBean(beanName, CrudRepository.class);
	}

}
