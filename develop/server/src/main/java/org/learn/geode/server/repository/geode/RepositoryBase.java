package org.learn.geode.server.repository.geode;

import java.util.List;

/**
 * The base repository contract
 * @author Mithu Tokder
 * @param <K>
 * @param <V>
 */
public interface RepositoryBase<K, V> {
	
    public List<V> find(String query);

    public List<V> find(String query, Object ... params);

    public V findUnique(String query, Object ... params);

    public V get(K key);

    public void put(K k, V v);

    public List<V> getAllValues();

}
