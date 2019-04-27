package org.learn.geode.server.repository.geode.impl;

import javax.annotation.Resource;

import org.apache.geode.cache.Region;
import org.learn.geode.server.domain.OutMessage;
import org.learn.geode.server.repository.geode.BaseGeodeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OutMessageRepository extends BaseGeodeRepository<Long, OutMessage> {

	@Resource(name = "out_msg")
	Region<Long, OutMessage> region;

	@Override
	public Region<Long, OutMessage> getRegion() {
		return region;
	}
	
}
