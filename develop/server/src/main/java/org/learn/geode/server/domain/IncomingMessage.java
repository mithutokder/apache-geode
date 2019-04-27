package org.learn.geode.server.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Entity
@Region("incoming_msg")
@Table(name = "incoming_msg")
@NamedQueries({
        @NamedQuery(name="IncomingMessage.getAll",
                query="SELECT e FROM IncomingMessage e")
})
public class IncomingMessage extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long incomingMessagePk;
	private String referenceNumber;
	private ConnectedSystem connectedSystem;
	private String messagePath;

	@Id
    @Column(name = "incoming_pk", nullable = false, precision = 0)
    public Long getIncomingMessagePk() {
		return incomingMessagePk;
	}
	public void setIncomingMessagePk(Long incomingMessagePk) {
		this.incomingMessagePk = incomingMessagePk;
	}
	
	@Basic
    @Column(name = "reference_no", nullable = false)
    public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	@OneToOne
    @JoinColumn(name = "originating_sys_pk", referencedColumnName = "connected_sys_pk", nullable = true)
    public ConnectedSystem getConnectedSystem() {
		return connectedSystem;
	}
	
	public void setConnectedSystem(ConnectedSystem connectedSystem) {
		this.connectedSystem = connectedSystem;
	}
	
	@Basic
    @Column(name = "message_path", nullable = false)
    public String getMessagePath() {
		return messagePath;
	}
	
	public void setMessagePath(String messagePath) {
		this.messagePath = messagePath;
	}
	
}
