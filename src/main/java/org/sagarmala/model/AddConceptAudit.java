package org.sagarmala.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AddConceptAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "audit_id")
	private long auditId;
	@Column(name = "id")
	private int id;
	@Column(name = "concept_id", length = 20)
	private String conceptId;
	@Column(name = "project_type", length = 255)
	private String projectType;
	@Column(name = "id_funding_agencies", length = 255)
	private String idFundingAgencies;
	@Column(name = "project_name", length = 255)
	private String projectName;
	@Column(name = "state_id", length = 255)
	private String stateId;
	@Column(name = "port_type", length = 255)
	private String portType;
	@Column(name = "port_name", length = 255)
	private String portName;
	@Column(name = "project_location", length = 255)
	private String projectLocation;
	@Column(name = "project_brief")
	private String projectBrief;
	@Column(name = "aims_and_objectives")
	private String aimsAndObjectives;
	@Column(name = "scope_of_work")
	private String scopeOfWork;
	@Column(name = "estimated_cost", precision = 12)
	private float estimatedCost;
	@Column(name = "project_proponent", length = 255)
	private String projectProponent;
	@Column(name = "start_date")
	private Timestamp startDate;
	@Column(name = "end_date")
	private Timestamp endDate;
	@Column(length = 255)
	private String document;
	@Column(name = "nodal_officer_name", length = 255)
	private String nodalOfficerName;
	@Column(name = "nodal_officer_landline", length = 255)
	private String nodalOfficerLandline;
	@Column(name = "nodal_officer_designation", length = 255)
	private String nodalOfficerDesignation;
	@Column(name = "nodal_officer_mobile", length = 255)
	private String nodalOfficerMobile;
	@Column(name = "nodal_officer_postal_address", length = 255)
	private String nodalOfficerPostalAddress;
	@Column(name = "nodal_officer_email", length = 255)
	private String nodalOfficerEmail;
	@Column(name = "status", length = 2)
	private String status;
	@Column(name = "remark", length = 255)
	private String remark;
	@Column(name = "created_by", length = 255)
	private String createdBy;
	@Column(name = "updated_by", length = 255)
	private String updatedBy;
	@Column(name = "created_date")
	private Timestamp createdDate;
	@Column(name = "updated_date")
	private Timestamp updatedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConceptId() {
		return conceptId;
	}

	public void setConceptId(String conceptId) {
		this.conceptId = conceptId;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getIdFundingAgencies() {
		return idFundingAgencies;
	}

	public void setIdFundingAgencies(String idFundingAgencies) {
		this.idFundingAgencies = idFundingAgencies;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getPortType() {
		return portType;
	}

	public void setPortType(String portType) {
		this.portType = portType;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getProjectLocation() {
		return projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	public String getProjectBrief() {
		return projectBrief;
	}

	public void setProjectBrief(String projectBrief) {
		this.projectBrief = projectBrief;
	}

	public String getAimsAndObjectives() {
		return aimsAndObjectives;
	}

	public void setAimsAndObjectives(String aimsAndObjectives) {
		this.aimsAndObjectives = aimsAndObjectives;
	}

	public String getScopeOfWork() {
		return scopeOfWork;
	}

	public void setScopeOfWork(String scopeOfWork) {
		this.scopeOfWork = scopeOfWork;
	}

	public float getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(float estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public String getProjectProponent() {
		return projectProponent;
	}

	public void setProjectProponent(String projectProponent) {
		this.projectProponent = projectProponent;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getNodalOfficerName() {
		return nodalOfficerName;
	}

	public void setNodalOfficerName(String nodalOfficerName) {
		this.nodalOfficerName = nodalOfficerName;
	}

	public String getNodalOfficerLandline() {
		return nodalOfficerLandline;
	}

	public void setNodalOfficerLandline(String nodalOfficerLandline) {
		this.nodalOfficerLandline = nodalOfficerLandline;
	}

	public String getNodalOfficerDesignation() {
		return nodalOfficerDesignation;
	}

	public void setNodalOfficerDesignation(String nodalOfficerDesignation) {
		this.nodalOfficerDesignation = nodalOfficerDesignation;
	}

	public String getNodalOfficerMobile() {
		return nodalOfficerMobile;
	}

	public void setNodalOfficerMobile(String nodalOfficerMobile) {
		this.nodalOfficerMobile = nodalOfficerMobile;
	}

	public String getNodalOfficerPostalAddress() {
		return nodalOfficerPostalAddress;
	}

	public void setNodalOfficerPostalAddress(String nodalOfficerPostalAddress) {
		this.nodalOfficerPostalAddress = nodalOfficerPostalAddress;
	}

	public String getNodalOfficerEmail() {
		return nodalOfficerEmail;
	}

	public void setNodalOfficerEmail(String nodalOfficerEmail) {
		this.nodalOfficerEmail = nodalOfficerEmail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
}
