package org.learn.geode.server.repository.geode.impl;

import javax.annotation.Resource;

import org.apache.geode.cache.Region;
import org.learn.geode.server.domain.SplittedMessageAttribute;
import org.learn.geode.server.repository.geode.BaseGeodeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SplittedMessageAttributeRepository extends BaseGeodeRepository<Long, SplittedMessageAttribute> {

	@Resource(name = "splitted_attribute")
	Region<Long, SplittedMessageAttribute> region;

	@Override
	public Region<Long, SplittedMessageAttribute> getRegion() {
		return region;
	}
	
}
