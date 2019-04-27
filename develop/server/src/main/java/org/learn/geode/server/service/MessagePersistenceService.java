package org.learn.geode.server.service;

import org.learn.geode.server.enums.MessageType;

public interface MessagePersistenceService {
	
	public String storeMessage(byte[] message, MessageType type);

}
