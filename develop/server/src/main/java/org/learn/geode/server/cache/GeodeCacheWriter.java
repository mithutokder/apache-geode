package org.learn.geode.server.cache;

import java.io.Serializable;
import java.util.Optional;

import org.apache.geode.cache.CacheWriter;
import org.apache.geode.cache.CacheWriterException;
import org.apache.geode.cache.EntryEvent;
import org.apache.geode.cache.RegionEvent;
import org.learn.geode.server.domain.BaseEntity;
import org.learn.geode.server.factory.CrudRepositoryFactoryBean;
import org.springframework.data.repository.CrudRepository;
public class GeodeCacheWriter<K extends Serializable, V extends BaseEntity> implements CacheWriter<K, V> {
	
	private CrudRepository<V, K> repository;
	
	public GeodeCacheWriter(String type) {
		repository = CrudRepositoryFactoryBean.getCrudRepository(type);
	}

	@Override
	public void close() {
		
	}

	@Override
	public void beforeUpdate(EntryEvent<K, V> event) throws CacheWriterException {
		repository.save(event.getNewValue());
	}

	@Override
	public void beforeCreate(EntryEvent<K, V> event) throws CacheWriterException {
		K key = event.getKey();
        Optional<V> value = repository.findById(key);
        if (!value.isPresent()) {
            repository.save(event.getNewValue());
        }
	}

	@Override
	public void beforeDestroy(EntryEvent<K, V> event) throws CacheWriterException {
		K pk = event.getKey();
        repository.deleteById(pk);
	}

	@Override
	public void beforeRegionDestroy(RegionEvent<K, V> event) throws CacheWriterException {
		// No Op
	}

	@Override
	public void beforeRegionClear(RegionEvent<K, V> event) throws CacheWriterException {
		// No Op
	}

}