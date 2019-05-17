package org.sagarmala.bean;

import java.util.Date;
import org.sagarmala.model.CommonMasters;

public class RoleBean {
private Long roleId;
	
	private String roleName;

	private CommonMasters levelId;//hello
	
	private Integer isActiv;
	
	private Integer isDeleted;
	
	private Integer createdBy;
	
	private Integer updatedBy;
	
	private Date createdDate;
	
	private Date updatedDate;
	
	private String remarks;
	
	private CommonMasters roleTpyeId;
	
	private CommonMasters deparmentId;
	
	private String roleDescription;
	
	private Integer status;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public CommonMasters getLevelId() {
		return levelId;
	}

	public void setLevelId(CommonMasters levelId) {
		this.levelId = levelId;
	}

	public Integer getIsActiv() {
		return isActiv;
	}

	public void setIsActiv(Integer isActiv) {
		this.isActiv = isActiv;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public CommonMasters getRoleTpyeId() {
		return roleTpyeId;
	}

	public void setRoleTpyeId(CommonMasters roleTpyeId) {
		this.roleTpyeId = roleTpyeId;
	}

	public CommonMasters getDeparmentId() {
		return deparmentId;
	}

	public void setDeparmentId(CommonMasters deparmentId) {
		this.deparmentId = deparmentId;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RoleBean [roleId=" + roleId + ", roleName=" + roleName + ", levelId=" + levelId + ", isActiv=" + isActiv
				+ ", isDeleted=" + isDeleted + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", remarks=" + remarks
				+ ", roleTpyeId=" + roleTpyeId + ", deparmentId=" + deparmentId + ", roleDescription=" + roleDescription
				+ ", status=" + status + "]";
	}

	
}
