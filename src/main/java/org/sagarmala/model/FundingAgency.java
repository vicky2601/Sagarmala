package org.sagarmala.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "mst_funding_agency")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "fundingAgency")
public class FundingAgency implements Serializable{


	/** Primary key. */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "agency_id")
	private int masterId;

	@Column(name = "agency_name", nullable = false)
	private String masterName;

	@Column(name = "agency_type_code", nullable = false)
	private String masterCode;

	@Column(name = "agency_code", nullable = false)
	private int masterUniqueCode;

	@JoinColumn(name = "state_Id")
	@OneToOne(fetch = FetchType.EAGER)
	private StateMaster stateId;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "created_date", columnDefinition = "DATETIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "updated_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;

	@Column(name = "is_active")
	private int isActive;

	public StateMaster getStateId() {
		return stateId;
	}

	public void setStateId(StateMaster stateId) {
		this.stateId = stateId;
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

	public String getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getMasterUniqueCode() {
		return masterUniqueCode;
	}

	public void setMasterUniqueCode(int masterUniqueCode) {
		this.masterUniqueCode = masterUniqueCode;
	}

	@Override
	public String toString() {
		return "Agency [masterId=" + masterId + ", masterName=" + masterName + ", masterCode=" + masterCode
				+ ", masterUniqueCode=" + masterUniqueCode + ", stateId=" + stateId + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", isActive=" + isActive + "]";
	}
}
