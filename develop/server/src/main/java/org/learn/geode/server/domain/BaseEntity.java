package org.learn.geode.server.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String createdBy;
    private Date creationDate;
    private String updatedBy;
    private Date updatedDate;
    private Integer versionNo;

    @Basic
    @Column(name = "created_by", nullable = true, length = 20)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "creation_date", nullable = true)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = new Date(creationDate.getTime());
    }

    @Basic
    @Column(name = "updated_by", nullable = true, length = 20)
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Basic
    @Column(name = "updated_date", nullable = true)
    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = new Date(updatedDate.getTime());
    }

    @Basic
    @Column(name = "version_no", nullable = true, precision = 0)
    @Version
    public Integer getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
    }


}
