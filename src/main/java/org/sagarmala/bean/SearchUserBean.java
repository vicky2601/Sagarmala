package org.sagarmala.bean;

import java.util.List;
import java.util.Set;

import org.sagarmala.model.MinorPortMaster;

public class SearchUserBean {

	private int levelId;
	private int stateId;
	private int portId;
	private int organisationId;
	private int designation;
	private String username;
	private int roleId;
	private int agencyId;
	private int status;
	private int startResult;
	private int maxResult;
	private int lineMinistryId;
	private int maritimeBoardId;
	private int departmentId;
	private int searchByUserId;
	private int minnorPortId;
	private Set<MinorPortMaster> minnorPortList;
	

	public Set<MinorPortMaster> getMinnorPortList() {
		return minnorPortList;
	}

	public void setMinnorPortList(Set<MinorPortMaster> minnorPortList) {
		this.minnorPortList = minnorPortList;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getPortId() {
		return portId;
	}

	public void setPortId(int portId) {
		this.portId = portId;
	}

	public int getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}

	public int getDesignation() {
		return designation;
	}

	public void setDesignation(int designation) {
		this.designation = designation;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
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

	public int getLineMinistryId() {
		return lineMinistryId;
	}

	public void setLineMinistryId(int lineMinistryId) {
		this.lineMinistryId = lineMinistryId;
	}

	public int getMaritimeBoardId() {
		return maritimeBoardId;
	}

	public void setMaritimeBoardId(int maritimeBoardId) {
		this.maritimeBoardId = maritimeBoardId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getSearchByUserId() {
		return searchByUserId;
	}

	public void setSearchByUserId(int searchByUserId) {
		this.searchByUserId = searchByUserId;
	}

	public int getMinnorPortId() {
		return minnorPortId;
	}

	public void setMinnorPortId(int minnorPortId) {
		this.minnorPortId = minnorPortId;
	}

	@Override
	public String toString() {
		return "SearchUserBean [levelId=" + levelId + ", stateId=" + stateId + ", portId=" + portId
				+ ", organisationId=" + organisationId + ", designation=" + designation + ", username=" + username
				+ ", roleId=" + roleId + ", agencyId=" + agencyId + ", status=" + status + ", startResult="
				+ startResult + ", maxResult=" + maxResult + ", lineMinistryId=" + lineMinistryId + ", maritimeBoardId="
				+ maritimeBoardId + ", departmentId=" + departmentId + ", searchByUserId=" + searchByUserId
				+ ", minnorPortId=" + minnorPortId + ", minnorPortList=" + minnorPortList + "]";
	}

}
