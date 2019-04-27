package org.learn.geode.server.service;

import org.learn.geode.server.domain.IncomingMessage;

public interface IncomingMessageService extends CrudService<IncomingMessage> {

	public IncomingMessage save(byte[] message, String connectedSystemId);
}
