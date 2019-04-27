package org.learn.geode.server.service.impl;

import org.learn.geode.server.repository.geode.impl.SystemAttributeRepository;
import org.learn.geode.server.service.SystemAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemAttributeServiceImpl implements SystemAttributeService {

	@Autowired
	private SystemAttributeRepository repository;
	
	@Override
	public String getSystemAttribute(String key) {
		return repository.get(key);
	}

}
