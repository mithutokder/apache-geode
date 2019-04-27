package org.learn.geode.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.learn.geode.common.dto.ConnectedSystemDto;
import org.learn.geode.common.exception.GeodeException;
import org.learn.geode.server.domain.ConnectedSystem;
import org.learn.geode.server.enums.KeyType;
import org.learn.geode.server.repository.geode.BaseGeodeRepository;
import org.learn.geode.server.repository.geode.impl.ConnectedSystemRepository;
import org.learn.geode.server.service.ConnectedSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.function.annotation.GemfireFunction;
import org.springframework.stereotype.Service;

@Service
public class ConnectedSystemServiceImpl extends AbstractService<ConnectedSystem> implements ConnectedSystemService {

	@Autowired
	private ConnectedSystemRepository repository; 
	
	@Override
	public Map<Long, String> asMap() {
		List<ConnectedSystem> results = getAll();
		Map<Long, String> maps = new HashMap<>(results.size());
		results.stream().forEach(i -> maps.put(i.getConnectedSystemPk(), i.getConnectedSystemId()));
		return maps;
	}

	@Override
	public Long getKey(ConnectedSystem entity) {
		return entity.getConnectedSystemPk();
	}

	@Override
	protected BaseGeodeRepository<Long, ConnectedSystem> getRepository() {
		return repository;
	}

	@Override
	public ConnectedSystem getConnectedSystem(String connectedSystemId) {
		final String query = "select * from /connected_system c where c.connectedSystemId=$1";
		List<ConnectedSystem> results =  getQueryResult(query, connectedSystemId);
		if(results.isEmpty()) {
			throw new GeodeException(String.format("No Connected System found with connectedSystemId : %s", connectedSystemId));
		}
		return results.get(0);
	}

	@GemfireFunction(id="ConnectedSystemEntry",HA=true, batchSize =1000)
	public ConnectedSystemDto enter(ConnectedSystemDto dto) {
		ConnectedSystem entity = new ConnectedSystem();
		entity.setConnectedSystemId(dto.getConnectedSystemId());
		entity.setCreatedBy(dto.getCreatedBy());
		entity.setCreationDate(dto.getCreationDate());
		entity.setUpdatedDate(dto.getUpdatedDate());
		entity.setUpdatedDate(dto.getUpdatedDate());
		
		Long pk = generator.generate(KeyType.ConnectedSystem);
		entity.setConnectedSystemPk(pk);
		dto.setConnectedSystemPk(pk);
		enter(entity);
		
		return dto;
	}

}
