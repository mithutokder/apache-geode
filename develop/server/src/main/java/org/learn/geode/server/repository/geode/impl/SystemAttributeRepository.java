package org.learn.geode.server.repository.geode.impl;

import javax.annotation.Resource;

import org.apache.geode.cache.Region;
import org.learn.geode.server.repository.geode.BaseGeodeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SystemAttributeRepository extends BaseGeodeRepository<String, String> {

	@Resource(name = "system_attribute")
	Region<String, String> region;

	@Override
	public Region<String, String> getRegion() {
		return region;
	}
	
}
