package org.sagarmala.bean;

public class SearchRoleBean {

	private String roleName;
	private int roleTpyeId;
	private int levelId;
	private int organisationId;
	private int status;
	private int startResult;
	private int maxResult;
	private int roleId;
	private int orgRoleId;
	private Integer userRoleId;

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public int getOrgRoleId() {
		return orgRoleId;
	}

	public void setOrgRoleId(int orgRoleId) {
		this.orgRoleId = orgRoleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleTpyeId() {
		return roleTpyeId;
	}

	public void setRoleTpyeId(int roleTpyeId) {
		this.roleTpyeId = roleTpyeId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "SearchRoleBean [roleName=" + roleName + ", roleTpyeId=" + roleTpyeId + ", levelId=" + levelId
				+ ", organisationId=" + organisationId + ", status=" + status + ", startResult=" + startResult
				+ ", maxResult=" + maxResult + ", roleId=" + roleId + ", orgRoleId=" + orgRoleId + ", userRoleId="
				+ userRoleId + "]";
	}

	
}
