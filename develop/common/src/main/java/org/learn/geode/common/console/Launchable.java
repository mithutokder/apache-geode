package org.learn.geode.common.console;

import java.util.concurrent.CountDownLatch;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.learn.geode.common.exception.GeodeException;

public interface Launchable {
	
	public void start(CountDownLatch latch, String ... args) throws GeodeException;

	public void stop() throws GeodeException;
	
	public CommandLineParser cmdLineParser = new DefaultParser();
	
	default public CommandLine getCommandLine(Options options, String ... args) throws GeodeException {
		try {
			return cmdLineParser.parse(options, args);
		} catch (ParseException e) {
			throw new GeodeException("Failed to parse command line args", e);
		}
	}
}
