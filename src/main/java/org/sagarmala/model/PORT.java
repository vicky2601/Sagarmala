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
@Table(name = "mst_port")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "port")
public class PORT implements Serializable {

	/** Primary key. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "port_id")
	private int portId;

	@Column(name = "port_name", nullable = false)
	private String portName;

	@Column(name = "port_code", nullable = false)
	private String portCode;

	@Column(name = "port_type_code", nullable = false)
	private String portTypeCode;
	
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

	public String getPortTypeCode() {
		return portTypeCode;
	}

	public void setPortTypeCode(String portTypeCode) {
		this.portTypeCode = portTypeCode;
	}

	public int getPortId() {
		return portId;
	}

	public void setPortId(int portId) {
		this.portId = portId;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getPortCode() {
		return portCode;
	}

	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}

	public StateMaster getStateId() {
		return stateId;
	}

	public void setStateId(StateMaster stateId) {
		this.stateId = stateId;
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

	@Override
	public String toString() {
		return "PORT [portId=" + portId + ", portName=" + portName + ", portCode=" + portCode + ", portTypeCode="
				+ portTypeCode + ", stateId=" + stateId + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", isActive=" + isActive + "]";
	}
}
