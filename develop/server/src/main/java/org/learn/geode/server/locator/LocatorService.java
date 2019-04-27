package org.learn.geode.server.locator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.lang3.StringUtils;
import org.apache.geode.distributed.AbstractLauncher.Status;
import org.apache.geode.distributed.LocatorLauncher;
import org.apache.geode.distributed.LocatorLauncher.LocatorState;
import org.learn.geode.common.console.CommandLineOptions;
import org.learn.geode.common.bootstrap.Bootstrap;
import org.learn.geode.common.console.CommandLineOptionBuilder;
import org.learn.geode.common.console.Launchable;
import org.learn.geode.common.exception.GeodeException;
import org.learn.geode.common.helper.DirectoryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

@Component("StartLocator")
public class LocatorService implements Launchable {
	
	private static final Logger log = LoggerFactory.getLogger(LocatorService.class);
	private static final String DEFAULT_HOST = "localhost";
	private static final Integer DEFAULT_PORT = 10334;
	
	
	private String memberName;
	private String host;
	private Integer port;
	private String workingDirectory;
	
	private LocatorLauncher locatorLauncher = null;

	@Override
	public void start(CountDownLatch latch, String... args) throws GeodeException {
		processArgs(args);
		
		locatorLauncher = new LocatorLauncher.Builder()
				.setMemberName(getMemberName())
				.setBindAddress(getHost())
				.setHostnameForClients(getHost())
				.setPort(getPort())
				.setWorkingDirectory(getWorkingDirectory())
				.build();

		locatorLauncher.start();
		
		LocatorState state = locatorLauncher.waitOnStatusResponse(30, 4, TimeUnit.SECONDS);
		if(state.getStatus().compareTo(Status.ONLINE) == 0) {
			System.out.println(String.format("Locator running on Host [%s] : Port [%d]", 
					locatorLauncher.getBindAddress().getHostAddress(),
					locatorLauncher.getPort()));
			System.out.println("client : " + locatorLauncher.getHostnameForClients());
			System.out.println("status : " + state.getStatus() + " message : " + state.getStatusMessage());
			locatorLauncher.waitOnLocator();
			latch.countDown();
		} else {
			System.out.println("Unable to start Locator. Receiving status : " + state.getStatusMessage());
			latch.countDown();
		}
	}

	@Override
	public void stop() throws GeodeException {
		System.out.println("Stop request invoked");
		if(locatorLauncher != null) {
			locatorLauncher.stop();
			LocatorState state = locatorLauncher.waitOnStatusResponse(30, 4, TimeUnit.SECONDS);
			if(state.getStatus().compareTo(Status.STOPPED) == 0) {
				System.out.println("Locator successfully stopped.");
			} else {
				System.out.println("status : " + state.getStatus() + " message : " + state.getStatusMessage());
			}
		}
	}
	
	private void processArgs(String ... args) throws GeodeException {
		Options options = CommandLineOptionBuilder.builder()
				.add(CommandLineOptions.MEMBER_NAME.constractOption())
				.add(CommandLineOptions.HOST.constractOption())
				.add(CommandLineOptions.PORT.constractOption())
				.add(CommandLineOptions.DIRECTORY.constractOption())
				.get();
		
		CommandLine cmd = getCommandLine(options, args);
		String memberName = cmd.getOptionValue(CommandLineOptions.MEMBER_NAME.getOpt());
		if(Strings.isNullOrEmpty(memberName)) {
			String errorMsg = "Member Name option is required to start locator";
			log.error(errorMsg);
			throw new GeodeException(errorMsg);
		} else {
			setMemberName(memberName);
		}
		String host = cmd.getOptionValue(CommandLineOptions.HOST.getOpt());
		if(Strings.isNullOrEmpty(host)) {
			log.warn("Host address is not provided. Using the default Host : " + DEFAULT_HOST);
			host = DEFAULT_HOST;
		}
		setHost(host);
		
		String portStr = cmd.getOptionValue(CommandLineOptions.PORT.getOpt());
		Integer port = null;
		if(Strings.isNullOrEmpty(portStr)) {
			log.warn("Port is not provided. Using the default Port : " + DEFAULT_PORT);
			port = DEFAULT_PORT;
		} else if(!StringUtils.isNumeric(portStr)) {
			String errorMsg = String.format("Invalid port [%s] provided. Using the default Port : [%d]", 
					portStr, DEFAULT_PORT);
			log.warn(errorMsg);
			port = DEFAULT_PORT;
		} else {
			port = Integer.parseInt(portStr);
		}
		setPort(port);
		
		String workingDir = cmd.getOptionValue(CommandLineOptions.DIRECTORY.getOpt());
		if(Strings.isNullOrEmpty(workingDir)) {
			String defaultDir = Bootstrap.getInstance().get("APP_HOME", String.class);
			if(Strings.isNullOrEmpty(defaultDir)) {
				throw new GeodeException("Working directory location must be provided");
			} else {
				workingDir = DirectoryHelper.getLocatorWorkingDirectory(defaultDir);
				log.warn("Working Directory not provided. Using the default Directory {}.", workingDir);
			}
		}
		DirectoryHelper.createDir(workingDir);
		setWorkingDirectory(workingDir);
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}
	
}
