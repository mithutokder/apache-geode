package org.learn.geode.server.service;

import org.learn.geode.server.domain.ConnectedSystem;

public interface ConnectedSystemService extends CrudService<ConnectedSystem> {
	
	public ConnectedSystem getConnectedSystem(String connectedSystemId);

}
