package org.learn.geode.common.helper;

import java.io.File;
import java.text.ParseException;
import java.util.Date;

import com.google.common.base.Strings;

public class DirectoryHelper {

	private static final String LOCATOR_WORKING_DIRECTORY = "locators";
	private DirectoryHelper(){}
	
	public static String getLocatorWorkingDirectory(String baseDir) {
		baseDir = appendSeparator(baseDir);
		return baseDir.concat(LOCATOR_WORKING_DIRECTORY).concat(File.separator);
	}
	
	public static String getAuditDirectory(String baseDir, String type, String datePattern) {
		baseDir = appendSeparator(baseDir);
		StringBuilder sb = new StringBuilder(baseDir)
				.append(type);
		appendDate(sb, datePattern);
		sb.append(File.separator);
		return sb.toString();
	}
	
	private static void appendDate(StringBuilder sb, String datePattern) {
		if(Strings.isNullOrEmpty(datePattern)) {
			try {
				sb.append(File.separator)
				.append(DateTimeHelper.formatDate(new Date(), datePattern));
			} catch (ParseException e) {
				// TODO handle exception
			}
		}
	}
	
	public static String appendSeparator(String dir) {
		if(!dir.endsWith(File.separator)) {
			dir = dir.concat(File.separator);
		}
		return dir;
	}
	
	public static boolean createDir(String location) {
		File directory = new File(location);
		if(!directory.exists()) {
			return directory.mkdirs();
		}
		return false;
	}
}
