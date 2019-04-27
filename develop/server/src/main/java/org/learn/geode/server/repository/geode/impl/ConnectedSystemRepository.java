package org.learn.geode.server.repository.geode.impl;

import javax.annotation.Resource;

import org.apache.geode.cache.Region;
import org.learn.geode.server.domain.ConnectedSystem;
import org.learn.geode.server.repository.geode.BaseGeodeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectedSystemRepository extends BaseGeodeRepository<Long, ConnectedSystem> {

	@Resource(name = "connected_system")
	Region<Long, ConnectedSystem> region;

	@Override
	public Region<Long, ConnectedSystem> getRegion() {
		return region;
	}
	
}
