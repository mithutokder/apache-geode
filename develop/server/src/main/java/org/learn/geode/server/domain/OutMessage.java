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
@Region("out_msg")
@Table(name = "out_msg")
@NamedQueries({
        @NamedQuery(name="OutMessage.getAll",
                query="SELECT e FROM OutMessage e")
})
public class OutMessage extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long outMessagePk;
	private ConnectedSystem connectedSystem;
	private String messagePath;
	
	@Id
    @Column(name = "out_pk", nullable = false, precision = 0)
    public Long getOutMessagePk() {
		return outMessagePk;
	}
	
	public void setOutMessagePk(Long outMessagePk) {
		this.outMessagePk = outMessagePk;
	}
	
	@OneToOne
    @JoinColumn(name = "destination_sys_pk", referencedColumnName = "connected_sys_pk", nullable = true)
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
