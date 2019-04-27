package org.learn.geode.server.repository.geode.impl;

import javax.annotation.Resource;

import org.apache.geode.cache.Region;
import org.learn.geode.server.domain.SplittedMessage;
import org.learn.geode.server.repository.geode.BaseGeodeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SplittedMessageRepository extends BaseGeodeRepository<Long, SplittedMessage> {

	@Resource(name = "splitted_msg")
	Region<Long, SplittedMessage> region;

	@Override
	public Region<Long, SplittedMessage> getRegion() {
		return region;
	}
	
}
