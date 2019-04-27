package org.learn.geode.common.helper;

import org.learn.geode.common.exception.GeodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public abstract class ResourceLoader {


	private static final Logger log = LoggerFactory.getLogger(ResourceLoader.class);

	/** May be null if resource not found
	 * @param location
	 * @return
	 */
	public static InputStream loadResource(String location) {
		InputStream resource = ClassLoader.getSystemClassLoader().getResourceAsStream(location);
		return resource;
	}
	
	public static Properties loadProperties(String location) throws GeodeException {
		Properties prop = null;
		InputStream resource = null;
		try {
			resource = loadResource(location);
			if(resource == null)
				resource = new ClassPathResource(location).getInputStream();
			if(resource != null) {
				prop = new Properties();
				prop.load(resource);
			}
			return prop;
		} catch(IOException e) {
			throw new GeodeException("Failed to load properties : " + location, e);
		} finally {
			closeResource(resource);
		}
	}

	public static void closeResource(Closeable closeable) {
		if(closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				log.warn("Unable to close resource", e);
			}
		}
	}
	

}
