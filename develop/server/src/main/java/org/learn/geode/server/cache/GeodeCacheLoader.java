package org.learn.geode.server.cache;

import java.io.Serializable;
import java.util.Optional;

import org.apache.geode.cache.CacheLoader;
import org.apache.geode.cache.CacheLoaderException;
import org.apache.geode.cache.LoaderHelper;
import org.learn.geode.server.domain.BaseEntity;
import org.learn.geode.server.factory.CrudRepositoryFactoryBean;
import org.springframework.data.repository.CrudRepository;

public class GeodeCacheLoader<K extends Serializable, V extends BaseEntity> implements CacheLoader<K, V> {
	
	private CrudRepository<V, K> repository;
	
	

	public GeodeCacheLoader(String type) {
		repository = CrudRepositoryFactoryBean.getCrudRepository(type);
	}

	@Override
	public void close() {
		
	}

	@Override
	public V load(LoaderHelper<K, V> helper) throws CacheLoaderException {
		K id = helper.getKey();
		Optional<V> op = repository.findById(id);
		return op.isPresent() ? op.get() : null;
	}

}