package org.learn.geode.common.console;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CommandLineOptionBuilder {
	
	private Options options;
	
	private CommandLineOptionBuilder(Options options) {
		this.options = options;
	}
	
	public static CommandLineOptionBuilder builder() {
		return new CommandLineOptionBuilder(new Options());
	}

	public CommandLineOptionBuilder add(Option option) {
		options.addOption(option);
		return this;
	}
	
	public Options get() {
		return options;
	}
	
}
