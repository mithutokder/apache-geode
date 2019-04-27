package org.learn.geode.server.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Entity
@Region("splitted_msg")
@Table(name = "splitted_msg")
@NamedQueries({
        @NamedQuery(name="SplittedMessage.getAll",
                query="SELECT e FROM SplittedMessage e")
})
public class SplittedMessage extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long splittedMessagePk;
	private IncomingMessage incomingMessage;
	private String messagePath;

	@Id
    @Column(name = "splitted_pk", nullable = false, precision = 0)
    public Long getSplittedMessagePk() {
		return splittedMessagePk;
	}
	public void setSplittedMessagePk(Long splittedMessagePk) {
		this.splittedMessagePk = splittedMessagePk;
	}
	
	@ManyToOne
    @JoinColumn(name = "incoming_pk", referencedColumnName = "incoming_pk", nullable = false)
    public IncomingMessage getIncomingMessage() {
		return incomingMessage;
	}
	public void setIncomingMessage(IncomingMessage incomingMessage) {
		this.incomingMessage = incomingMessage;
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
