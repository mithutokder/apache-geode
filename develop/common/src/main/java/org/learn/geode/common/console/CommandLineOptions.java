package org.learn.geode.common.console;

import org.apache.commons.cli.Option;

public enum CommandLineOptions {
	
	MEMBER_NAME("name", true, "Member Name"),
	HOST("host", true, "Host Address"),
	PORT("port", true, "Port"),
	DIRECTORY("dir", true, "Working Directory");
	
	private String opt;
	private Boolean hasArg;
	private String description;
	
	CommandLineOptions(String option, Boolean hasArg, String description) {
		this.opt = option;
		this.hasArg = hasArg;
		this.description = description;
	}
	
	public Option constractOption() {
		return new Option(getOpt(), getHasArg(), getDescription());
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String option) {
		this.opt = option;
	}

	public Boolean getHasArg() {
		return hasArg;
	}

	public void setHasArg(Boolean hasArg) {
		this.hasArg = hasArg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
