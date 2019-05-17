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
@Table(name = "org_role_mapping")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "orgrolemapping")
public class OrgRoleMapping implements Serializable {
	
	/** Primary key. */
	private static final long serialVersionUID = 1L;
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "org_role_id")
	private int orgRoleId;

	//@Column(name = "role_id")
	//@OneToMany(targetEntity=RoleMasterr.class,mappedBy="OrgRoleMapping",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	//private Set<RoleMasterr> roleList;
	@JoinColumn(name = "role_id")
	@OneToOne(fetch = FetchType.EAGER)
	private RoleMasterr roleMaster;

	//@Column(name = "org_id")
	//@OneToMany(targetEntity=OgatizationMaster.class,mappedBy="OrgRoleMapping",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name = "org_id")
	@OneToOne(fetch = FetchType.EAGER)
	private OgatizationMaster organisation;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date", columnDefinition = "DATETIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;

	@Column(name = "is_active")
	private int isActive;

	public int getOrgRoleId() {
		return orgRoleId;
	}

	public void setOrgRoleId(int orgRoleId) {
		this.orgRoleId = orgRoleId;
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

	public RoleMasterr getRoleMaster() {
		return roleMaster;
	}

	public void setRoleMaster(RoleMasterr roleMaster) {
		this.roleMaster = roleMaster;
	}

	public OgatizationMaster getOrganisation() {
		return organisation;
	}

	public void setOrganisation(OgatizationMaster organisation) {
		this.organisation = organisation;
	}

	@Override
	public String toString() {
		return "OrgRoleMapping [orgRoleId=" + orgRoleId + ", roleMaster=" + roleMaster + ", organisation="
				+ organisation + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + ", isActive=" + isActive + "]";
	}

}
