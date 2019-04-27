package org.learn.geode.server.enums;

public enum MessageType {

	INCOMING("incoming"),
	SPLITTED("splitted"),
	OUTGOING("outgoing");
	
	private String type;
	
	MessageType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
