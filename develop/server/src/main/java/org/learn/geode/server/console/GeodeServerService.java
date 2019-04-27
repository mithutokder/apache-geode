package org.learn.geode.server.console;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.learn.geode.common.bootstrap.Bootstrap;
import org.learn.geode.common.console.CommandLineOptionBuilder;
import org.learn.geode.common.console.CommandLineOptions;
import org.learn.geode.common.console.Launchable;
import org.learn.geode.common.exception.GeodeException;
import org.learn.geode.server.domain.ConnectedSystem;
import org.learn.geode.server.enums.KeyType;
import org.learn.geode.server.generator.DefaultPrimaryGenerator;
import org.learn.geode.server.service.impl.ConnectedSystemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.google.common.base.Strings;

//@Component(value="StartServer")
public class GeodeServerService implements Launchable {

	private static final Logger log = LoggerFactory.getLogger(GeodeServerService.class);
	
	private String name;
	
	private final String CONTEXT_NAME_PATTER = "geode-%s.xml";
	
	private ConnectedSystemServiceImpl connectedSystemServiceImpl;
	
	@Override
	public void start(CountDownLatch latch, String... args) throws GeodeException {
		processArgs(args);
		String beanName = String.format(CONTEXT_NAME_PATTER, name);
		ApplicationContext mainContext = Bootstrap.getInstance().getApplicationContext();
		try {
			ApplicationContext serverContext = new ClassPathXmlApplicationContext(new String[]{beanName}, mainContext);
			Bootstrap.getInstance().createServerContext(serverContext);
			System.out.println("Server up and running ...");
			
			connectedSystemServiceImpl = serverContext.getBean(ConnectedSystemServiceImpl.class);
			//enterConnectedSystem(serverContext);
			StopWatch watch = new StopWatch();
			watch.start();
			List<ConnectedSystem> all = connectedSystemServiceImpl.getAll();
			watch.stop();
			System.out.println("Connected system successfully fetched Database, time : " + watch.getTotalTimeMillis() + "ms");

			
		} catch(Exception e) {
			final String errMsg = "Failed to load server context file. Exiting the service";
			System.err.println(errMsg);
			log.error(errMsg, e);
			latch.countDown();
		}
	}
	
	private void enterConnectedSystem(ApplicationContext serverContext) {
		DefaultPrimaryGenerator generator = serverContext.getBean(DefaultPrimaryGenerator.class);
		StopWatch watch = new StopWatch();
		watch.start();
		for (int i = 0; i < 20000; i++) {
			ConnectedSystem connSys = new ConnectedSystem();
			connSys.setConnectedSystemPk(generator.generate(KeyType.ConnectedSystem));
			connSys.setConnectedSystemId("Test"+i);
			connSys.setCreatedBy("mithut");
			connSys.setCreationDate(new Date());
			connSys.setUpdatedBy("mithut");
			connSys.setUpdatedDate(new Date());
			connSys.setVersionNo(1);
			connectedSystemServiceImpl.enter(connSys);
		}
		watch.stop();
		System.out.println("Connected system successfully inserted into Database, time : " + watch.getTotalTimeMillis() + "ms");
	}
	
	private void processArgs(String ...args) {
		Options options = CommandLineOptionBuilder.builder()
				.add(CommandLineOptions.MEMBER_NAME.constractOption())
				.get();
		CommandLine cmd = getCommandLine(options, args);
		String memberName = cmd.getOptionValue(CommandLineOptions.MEMBER_NAME.getOpt());
		if(Strings.isNullOrEmpty(memberName)) {
			String errorMsg = "Member Name option is required to start locator";
			log.error(errorMsg);
			throw new GeodeException(errorMsg);
		}
		this.name = memberName;
	}

	@Override
	public void stop() throws GeodeException {

	}

}
