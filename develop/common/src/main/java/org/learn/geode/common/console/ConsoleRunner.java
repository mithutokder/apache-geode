package org.learn.geode.common.console;

import java.util.concurrent.CountDownLatch;

import org.learn.geode.common.bootstrap.Bootstrap;
import org.learn.geode.common.bootstrap.BootstrapConfig;
import org.learn.geode.common.bootstrap.BootstrapConfigBuilder;
import org.learn.geode.common.exception.GeodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleRunner {
	
	private static final Logger log = LoggerFactory.getLogger(ConsoleRunner.class);

	public static void main(String[] args) throws GeodeException {
		if(args.length == 0) {
            System.err.print("ConsoleRunner expects the service name to launch");
            System.exit(-1);
        }
        final String serviceName = args[0];
        String[] params = new String[args.length-1];
        System.arraycopy(args, 1 ,params, 0, args.length-1);
        
        BootstrapConfig bootstrapConfig = BootstrapConfigBuilder.defaultConsoleConfig();
        Bootstrap.init(bootstrapConfig);
        
        Launchable launchable = Bootstrap.getInstance().getApplicationContext().getBean(serviceName, Launchable.class);
        CountDownLatch latch = new CountDownLatch(1);
        registerShutDownHook(launchable, latch);
        launchable.start(latch, args);
        
        // waiting for the service to stop 
        try {
			latch.await();
		} catch (InterruptedException e) {
			log.warn("Could not wait for the service to stop. Existing the service", e);
		}
        
	}
	
	private static void registerShutDownHook(final Launchable launchable, final CountDownLatch latch) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
				try {
					launchable.stop();
					Bootstrap.destroy();
				} catch (GeodeException e) {
					log.error("Failed to stop service", e);
					System.exit(-1);
				} finally {
					latch.countDown();
				}
            }
        });
    }

}
