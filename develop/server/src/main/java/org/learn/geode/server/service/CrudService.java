package org.learn.geode.server.service;

import java.util.List;
import java.util.Map;

public interface CrudService<E> {

	public void enter(E entity);

    public void update(E entity);

    public List<E> getQueryResult(String query, Object... args);

    public Map<Long, String> asMap();

    public List<E> getAll();
    
    public E get(Long key);
}
