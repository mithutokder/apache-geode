package org.learn.geode.server.repository.geode.impl;

import javax.annotation.Resource;

import org.apache.geode.cache.Region;
import org.learn.geode.server.domain.SplittedOutMessageXref;
import org.learn.geode.server.repository.geode.BaseGeodeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SplittedOutMessageXRefRepository extends BaseGeodeRepository<Long, SplittedOutMessageXref> {

	@Resource(name = "split_out_xref")
	Region<Long, SplittedOutMessageXref> region;

	@Override
	public Region<Long, SplittedOutMessageXref> getRegion() {
		return region;
	}
	
}
