package org.learn.geode.server.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.Date;

import org.learn.geode.common.exception.GeodeException;
import org.learn.geode.common.helper.DateTimeHelper;
import org.learn.geode.common.helper.DirectoryHelper;
import org.learn.geode.server.enums.MessageType;
import org.learn.geode.server.service.MessagePersistenceService;
import org.learn.geode.server.service.SystemAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

@Service
public class MessagePersistenceServiceImpl implements MessagePersistenceService {
	
	public static final String BASE_DIR_KEY = "server.base.directory";
	public static final String DATE_PATTERN_KEY = "server.date.format";
	
	@Autowired
	private SystemAttributeService systemAttributeService;

	@Override
	public String storeMessage(byte[] message, MessageType type) {
		Preconditions.checkNotNull(message, "Message can not be null");
		Path path = getPath(type);
		try {
			Files.write(path, message, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
		} catch (IOException e) {
			throw new GeodeException("Unable to save file", e);
		}
		
		return path.toString();
	}
	
	private Path getPath(MessageType type) {
		String baseDir = systemAttributeService.getSystemAttribute(BASE_DIR_KEY);
		String datePattern = systemAttributeService.getSystemAttribute(DATE_PATTERN_KEY);
		
		if(Strings.isNullOrEmpty(baseDir)) {
			throw new GeodeException("Base directory is not configured");
		}
		
		String path = DirectoryHelper.getAuditDirectory(baseDir, type.getType(), datePattern);
		
		File dir = new File(path);
		if (dir.isDirectory() && !dir.exists()) {
			dir.mkdirs();
		} else if(!dir.isDirectory()){
			throw new GeodeException("Configured base directory is not a proper directory");
		}
		
		try {
			return Paths.get(path.concat(DateTimeHelper.formatDate(new Date(), DateTimeHelper.DATE_TIME_PATTERN)));
		} catch (ParseException e) {
			throw new GeodeException("Failed to create new file name", e);
		}
	}

}
