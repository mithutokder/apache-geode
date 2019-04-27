package org.learn.geode.server.service.impl;

import java.text.ParseException;
import java.util.Date;

import org.learn.geode.common.helper.DateTimeHelper;
import org.learn.geode.server.enums.MessageType;
import org.learn.geode.server.service.ReferenceNumberServive;
import org.springframework.stereotype.Service;

@Service
public class ReferenceNumberServiveImpl implements ReferenceNumberServive {

	@Override
	public String generate(MessageType type) {
		try {
			String currTime = DateTimeHelper.formatDate(new Date(), DateTimeHelper.DATE_TIME_PATTERN);
			return new StringBuilder(type.name().charAt(0))
					.append(currTime)
					.toString();
		} catch (ParseException e) {
			return null;
		}
		
	}

}
