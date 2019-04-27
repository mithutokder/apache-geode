package org.learn.geode.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.learn.geode.server.domain.ConnectedSystem;
import org.learn.geode.server.domain.IncomingMessage;
import org.learn.geode.server.enums.MessageType;
import org.learn.geode.server.repository.geode.BaseGeodeRepository;
import org.learn.geode.server.repository.geode.impl.IncomingMessageRepository;
import org.learn.geode.server.service.ConnectedSystemService;
import org.learn.geode.server.service.IncomingMessageService;
import org.learn.geode.server.service.MessagePersistenceService;
import org.learn.geode.server.service.ReferenceNumberServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomingMessageServiceImpl extends AbstractService<IncomingMessage> implements IncomingMessageService {

	@Autowired
	private IncomingMessageRepository repository; 
	
	@Autowired
	private ConnectedSystemService connectedSystemService;
	
	@Autowired
	private MessagePersistenceService messagePersistenceService;
	
	@Autowired
	private ReferenceNumberServive referenceNumberServive;
	
	@Override
	public Map<Long, String> asMap() {
		List<IncomingMessage> results = getAll();
		Map<Long, String> maps = new HashMap<>(results.size());
		results.stream().forEach(i -> maps.put(i.getIncomingMessagePk(), i.getMessagePath()));
		return maps;
	}

	@Override
	public Long getKey(IncomingMessage entity) {
		return entity.getIncomingMessagePk();
	}

	@Override
	protected BaseGeodeRepository<Long, IncomingMessage> getRepository() {
		return repository;
	}

	@Override
	public IncomingMessage save(byte[] message, String connectedSystemId) {
		ConnectedSystem connectedSystem = connectedSystemService.getConnectedSystem(connectedSystemId);
		final String path = messagePersistenceService.storeMessage(message, MessageType.INCOMING);
		IncomingMessage entity = createEntity();
		entity.setReferenceNumber(referenceNumberServive.generate(MessageType.INCOMING));
		entity.setConnectedSystem(connectedSystem);
		entity.setMessagePath(path);
		enter(entity);
		return entity;
	}
	
	private IncomingMessage createEntity() {
		IncomingMessage entity = new IncomingMessage();
		return entity;
	}
	
}
