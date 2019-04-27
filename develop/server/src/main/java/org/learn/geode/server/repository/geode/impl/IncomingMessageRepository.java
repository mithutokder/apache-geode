package org.learn.geode.server.repository.geode.impl;

import javax.annotation.Resource;

import org.apache.geode.cache.Region;
import org.learn.geode.server.domain.IncomingMessage;
import org.learn.geode.server.repository.geode.BaseGeodeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class IncomingMessageRepository extends BaseGeodeRepository<Long, IncomingMessage> {

	@Resource(name = "incoming_msg")
	Region<Long, IncomingMessage> region;

	@Override
	public Region<Long, IncomingMessage> getRegion() {
		return region;
	}
	
}
