package com.org.learn.geode.console.parser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.learn.geode.common.dto.ConnectedSystemDto;
import org.springframework.stereotype.Service;

@Service
public class ConnectedSystemMessageParser {
	
	private static final Map<String, Integer> contentMap = new HashMap<>();
	
	static {
		contentMap.put("connectedSystemId", 0);
		contentMap.put("createdBy", 1);
		contentMap.put("updatedBy", 2);
		contentMap.put("version", 3);
	}

	public ConnectedSystemDto parse(final String content) {
		ConnectedSystemDto dto = new ConnectedSystemDto();
		String[] split = content.split("|");
		dto.setConnectedSystemId(split[0]);
		dto.setCreatedBy(split[1]);
		dto.setCreationDate(new Date());
		dto.setUpdatedBy(split[2]);
		dto.setUpdatedDate(new Date());
		dto.setVersionNo(Integer.valueOf(split[3]));
		return null;
	}
}
