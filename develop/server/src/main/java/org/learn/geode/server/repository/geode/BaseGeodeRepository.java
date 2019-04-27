package org.learn.geode.server.repository.geode;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.query.SelectResults;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.util.Assert;

/**
 * Abstract implementation of {@link RepositoryBase}.
 * The class performs the operation on Geode Region
 * @author Mithu Tokder
 *
 * @param <K>
 * @param <V>
 */
public abstract class BaseGeodeRepository<K, V> implements RepositoryBase<K, V>, InitializingBean {

	/**
     * GemfireTemplate instance.
     */
    private GemfireTemplate gemfireTemplate;
    
	/**
	 * This method is used to get the {@link GemfireTemplate} instance.
	 * @return
	 */
	public GemfireTemplate getGemfireTemplate() {
		return gemfireTemplate;
	}

	@Override
	public List<V> find(String query) {
		Assert.notNull(query, "Query string can not be null");
		SelectResults<V> results =  getGemfireTemplate().find(query);
		return results.asList();
	}

	@Override
	public List<V> find(String query, Object... params) {
		Assert.notNull(query, "Query string can not be null");
        SelectResults<V> results =  getGemfireTemplate().find(query, params);
        return results.asList();
	}

	@Override
	public V findUnique(String query, Object... params) {
		Assert.notNull(query, "Query string can not be null");
        return getGemfireTemplate().findUnique(query, params);
	}

	@Override
	public V get(K key) {
		return getGemfireTemplate().get(key);
	}

	@Override
	public void put(K k, V v) {
		getGemfireTemplate().put(k, v);
		
	}

	@Override
	public List<V> getAllValues() {
		return getGemfireTemplate().<K, V>getRegion()
				.values()
				.stream()
				.collect(Collectors.toList());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Region<K, V> region = getRegion();
		Assert.notNull(region, "Region instance can not be null");
        this.gemfireTemplate = new GemfireTemplate(region);
	}
	
	public abstract Region<K, V> getRegion();
	
}
