package com.konnectus.domain;

import java.util.Date;

public class Audit {

	protected Date createionDate;
	protected Date updateDate;
	protected String createdBy;
	protected String updatedBy;
	
	
	public Date getCreateionDate() {
		return createionDate;
	}
	public void setCreateionDate(Date createionDate) {
		this.createionDate = createionDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
}
