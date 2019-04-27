package org.learn.geode.server.domain;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Entity
@Region("connected_system")
@Table(name = "connected_system")
@NamedQueries({
        @NamedQuery(name="ConnectedSystem.getAll",
                query="SELECT e FROM ConnectedSystem e")
})
public class ConnectedSystem extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long connectedSystemPk;
	private String connectedSystemId;
	private Timestamp t;
	
	public Timestamp getT() {
		return t;
	}

	public void setT(Timestamp t) {
		this.t = t;
	}

	@Id
    @Column(name = "connected_sys_pk", nullable = false, precision = 0)
    public Long getConnectedSystemPk() {
		return connectedSystemPk;
	}
	
	public void setConnectedSystemPk(Long connectedSystemPk) {
		this.connectedSystemPk = connectedSystemPk;
	}
	
	@Basic
    @Column(name = "connected_sys_id", nullable = false)
    public String getConnectedSystemId() {
		return connectedSystemId;
	}
	public void setConnectedSystemId(String connectedSystemId) {
		this.connectedSystemId = connectedSystemId;
	}
	
}
