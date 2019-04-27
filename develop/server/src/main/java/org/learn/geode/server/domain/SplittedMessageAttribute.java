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
@Region("splitted_attribute")
@Table(name = "splitted_attribute")
@NamedQueries({
        @NamedQuery(name="SplittedMessageAttribute.getAll",
                query="SELECT e FROM SplittedMessageAttribute e")
})
public class SplittedMessageAttribute extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long splitAttributePk;
	private SplittedMessage splittedMessage;
	private String key;
	private String value;
	
	@Id
    @Column(name = "splitted_attr_pk", nullable = false, precision = 0)
    public Long getSplitAttributePk() {
		return splitAttributePk;
	}
	public void setSplitAttributePk(Long splitAttributePk) {
		this.splitAttributePk = splitAttributePk;
	}
	
	@ManyToOne
    @JoinColumn(name = "splitted_pk", referencedColumnName = "splitted_pk", nullable = false)
    public SplittedMessage getSplittedMessage() {
		return splittedMessage;
	}
	public void setSplittedMessage(SplittedMessage splittedMessage) {
		this.splittedMessage = splittedMessage;
	}
	
	@Basic
    @Column(name = "key", nullable = false)
    public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	@Basic
    @Column(name = "value", nullable = false)
    public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
