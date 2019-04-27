package org.learn.geode.server.service.impl;

import java.util.Date;
import java.util.List;

import org.learn.geode.server.domain.BaseEntity;
import org.learn.geode.server.generator.DefaultPrimaryGenerator;
import org.learn.geode.server.repository.geode.BaseGeodeRepository;
import org.learn.geode.server.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService<E extends BaseEntity> implements CrudService<E> {

	@Autowired
	protected DefaultPrimaryGenerator generator;
	
	@Override
	public void enter(E entity) {
		populateAuditFields(entity, true);
		getRepository().put(getKey(entity), entity);
	}

	@Override
	public void update(E entity) {
		populateAuditFields(entity, false);
		getRepository().put(getKey(entity), entity);
	}
	
	@Override
	public E get(Long key) {
		return getRepository().get(key);
	}
	
	/** createdBy & updatedBy fields should be populated by 
	 * geode client application.
	 * @param entity
	 * @param entry
	 */
	public void populateAuditFields(E entity, Boolean entry) {
		
		if(entry) {
			entity.setCreationDate(new Date());
		}
		entity.setUpdatedDate(new Date());
	}

	@Override
	public List<E> getQueryResult(String query, Object... args) {
		return getRepository().find(query, args);
	}

	@Override
	public List<E> getAll() {
		return getRepository().getAllValues();
	}
	
	public abstract Long getKey(E entity);

	protected abstract BaseGeodeRepository<Long,E> getRepository();
	
}
