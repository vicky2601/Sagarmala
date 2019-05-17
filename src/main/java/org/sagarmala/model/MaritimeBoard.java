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

@SuppressWarnings("serial")
@Entity
@Table(name = "mst_maritime_board")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "board")
public class MaritimeBoard implements Serializable {
	
	/** Primary key. */
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maritime_board_id")
	private long maritimeBoardId;

	@JoinColumn(name = "state_Id")
	@OneToOne(fetch = FetchType.EAGER)
	private StateMaster stateId;

	@Column(name = "maritime_board_name")
	private String maritimeBoardName;

	@Column(name = "maritime_code")
	private String maritimeCode;

	@Column(name = "maritime_board_code")
	private int maritimeBoardCode;

	@Column(name = "createdBy")
	private int createdBy;

	@Column(name = "CreatedDate", columnDefinition = "DATETIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "updatedBy")
	private int updatedBy;

	@Column(name = "updated_Date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;

	@Column(name = "is_active")
	private int isActive;

	public long getMaritimeBoardId() {
		return maritimeBoardId;
	}

	public void setMaritimeBoardId(long maritimeBoardId) {
		this.maritimeBoardId = maritimeBoardId;
	}

	public StateMaster getStateId() {
		return stateId;
	}

	public void setStateId(StateMaster stateId) {
		this.stateId = stateId;
	}

	public String getMaritimeBoardName() {
		return maritimeBoardName;
	}

	public void setMaritimeBoardName(String maritimeBoardName) {
		this.maritimeBoardName = maritimeBoardName;
	}

	public String getMaritimeCode() {
		return maritimeCode;
	}

	public void setMaritimeCode(String maritimeCode) {
		this.maritimeCode = maritimeCode;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
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

	public int getMaritimeBoardCode() {
		return maritimeBoardCode;
	}

	public void setMaritimeBoardCode(int maritimeBoardCode) {
		this.maritimeBoardCode = maritimeBoardCode;
	}

	@Override
	public String toString() {
		return "MaritimeBoard [maritimeBoardId=" + maritimeBoardId + ", stateId=" + stateId + ", maritimeBoardName="
				+ maritimeBoardName + ", maritimeCode=" + maritimeCode + ", maritimeBoardCode=" + maritimeBoardCode
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", isActive=" + isActive + "]";
	}
}
