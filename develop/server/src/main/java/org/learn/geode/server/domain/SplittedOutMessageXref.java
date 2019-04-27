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
@Region("split_out_xref")
@Table(name = "split_out_xref")
@NamedQueries({
        @NamedQuery(name="SplittedOutMessageXref.getAll",
                query="SELECT e FROM SplittedOutMessageXref e")
})
public class SplittedOutMessageXref extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long xrefPk;
	private SplittedMessage splittedMessage;
	private OutMessage outMessage;
	private String processingStatus;
	
	@Id
    @Column(name = "xref_pk", nullable = false, precision = 0)
    public Long getXrefPk() {
		return xrefPk;
	}
	public void setXrefPk(Long xrefPk) {
		this.xrefPk = xrefPk;
	}
	
	@OneToOne
    @JoinColumn(name = "splitted_pk", referencedColumnName = "splitted_pk", nullable = true)
    public SplittedMessage getSplittedMessage() {
		return splittedMessage;
	}
	public void setSplittedMessage(SplittedMessage splittedMessage) {
		this.splittedMessage = splittedMessage;
	}
	
	@OneToOne
    @JoinColumn(name = "out_pk", referencedColumnName = "out_pk", nullable = true)
    public OutMessage getOutMessage() {
		return outMessage;
	}
	public void setOutMessage(OutMessage outMessage) {
		this.outMessage = outMessage;
	}
	
	@Basic
    @Column(name = "processing_status", nullable = false)
    public String getProcessingStatus() {
		return processingStatus;
	}
	public void setProcessingStatus(String processingStatus) {
		this.processingStatus = processingStatus;
	}
	
}
