package org.sagarmala.bean;

import java.util.Date;

public class CommonMasterBean {

	private int masterId;
	private String masterName;
	private int masterCode;
	private String masterType;
	private int stateId;
	private Integer createdBy;
	private Integer updatedBy;
	private Date createdDate;
	private Date updatedDate;
	private String masterTypeCode;
	private int isActive;
	private int startResult;
	private int maxResult;

	public int getStartResult() {
		return startResult;
	}

	public void setStartResult(int startResult) {
		this.startResult = startResult;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public String getMasterType() {
		return masterType;
	}

	public void setMasterType(String masterType) {
		this.masterType = masterType;
	}

	public int getMasterId() {
		return masterId;
	}

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public int getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(int masterCode) {
		this.masterCode = masterCode;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getMasterTypeCode() {
		return masterTypeCode;
	}

	public void setMasterTypeCode(String masterTypeCode) {
		this.masterTypeCode = masterTypeCode;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "CommonMasterBean [masterId=" + masterId + ", masterName=" + masterName + ", masterCode=" + masterCode
				+ ", masterType=" + masterType + ", stateId=" + stateId + ", createdBy=" + createdBy + ", updatedBy="
				+ updatedBy + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", masterTypeCode="
				+ masterTypeCode + ", isActive=" + isActive + ", startResult=" + startResult + ", maxResult="
				+ maxResult + "]";
	}
}
