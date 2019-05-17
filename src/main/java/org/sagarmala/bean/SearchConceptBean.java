package org.sagarmala.bean;

public class SearchConceptBean {

	private String projectName;
	private String conceptId;
	private String directorateOfPorts;
	private String privatePorts;

	private String stateId;
	private String majorPort;
	private String centralMinistry;
	private String status;

	private String maritimeBoard;
	private String department;
	private String spvName;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getConceptId() {
		return conceptId;
	}

	public void setConceptId(String conceptId) {
		this.conceptId = conceptId;
	}

	public String getDirectorateOfPorts() {
		return directorateOfPorts;
	}

	public void setDirectorateOfPorts(String directorateOfPorts) {
		this.directorateOfPorts = directorateOfPorts;
	}

	public String getPrivatePorts() {
		return privatePorts;
	}

	public void setPrivatePorts(String privatePorts) {
		this.privatePorts = privatePorts;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getMajorPort() {
		return majorPort;
	}

	public void setMajorPort(String majorPort) {
		this.majorPort = majorPort;
	}

	public String getCentralMinistry() {
		return centralMinistry;
	}

	public void setCentralMinistry(String centralMinistry) {
		this.centralMinistry = centralMinistry;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMaritimeBoard() {
		return maritimeBoard;
	}

	public void setMaritimeBoard(String maritimeBoard) {
		this.maritimeBoard = maritimeBoard;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSpvName() {
		return spvName;
	}

	public void setSpvName(String spvName) {
		this.spvName = spvName;
	}

	@Override
	public String toString() {
		return "SearchConceptBean [projectName=" + projectName + ", conceptId=" + conceptId + ", directorateOfPorts="
				+ directorateOfPorts + ", privatePorts=" + privatePorts + ", stateId=" + stateId + ", majorPort="
				+ majorPort + ", centralMinistry=" + centralMinistry + ", status=" + status + ", maritimeBoard="
				+ maritimeBoard + ", department=" + department + ", spvName=" + spvName + "]";
	}
}
