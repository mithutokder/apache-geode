package org.learn.geode.common.dto;

public class ConnectedSystemDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	
	private Long connectedSystemPk;
	private String connectedSystemId;
	
	public Long getConnectedSystemPk() {
		return connectedSystemPk;
	}
	public void setConnectedSystemPk(Long connectedSystemPk) {
		this.connectedSystemPk = connectedSystemPk;
	}
	public String getConnectedSystemId() {
		return connectedSystemId;
	}
	public void setConnectedSystemId(String connectedSystemId) {
		this.connectedSystemId = connectedSystemId;
	}
	
}
