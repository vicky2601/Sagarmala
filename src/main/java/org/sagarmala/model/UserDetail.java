package org.sagarmala.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "user_data")
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "user")
public class UserDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_Id")
	@ApiModelProperty(notes = "The generated User ID")
	private int userId;

	@Column(name = "user_name", unique = true)
	@ApiModelProperty(notes = "The login ID of the user")
	private String userName;

	@Column(name = "is_login_username_editable", unique = true)
	@ApiModelProperty(notes = "name is edit by user")
	private int isLoginUserNameEditable;

	@Column(name = "password")
	@JsonIgnore
	private String password;

	@Column(name = "name")
	@ApiModelProperty(notes = "name of the User")
	private String name;

	@Column(name = "email")
	@ApiModelProperty(notes = "email id of user")
	private String email;

	/*
	 * @JoinColumn(name = "level_Id")
	 * 
	 * @OneToOne(fetch = FetchType.EAGER) private CommonMasters levelId;
	 */

	@Column(name = "is_active")
	@ApiModelProperty(notes = "check user active 0 or 1")
	private int isActive;

	@Column(name = "is_first_attempt")
	@ApiModelProperty(notes = "check user first time login 0 or 1")
	private int isFirstAttempt;

	@Column(name = "deleted")
	private int deleted;

	@JsonIgnore
	@Column(name = "reset_token")
	private String resetToken;

	@Column(name = "login_Id")
	private int loginId;

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

	@JoinColumn(name = "organisationId")
	@OneToOne(fetch = FetchType.EAGER)
	private OgatizationMaster organisationId;

	@JoinColumn(name = "state")
	@OneToOne(fetch = FetchType.EAGER)
	private StateMaster state;

	@JoinColumn(name = "port")
	@OneToOne(fetch = FetchType.EAGER)
	private PORT port;

	@JoinColumn(name = "agency")
	@OneToOne(fetch = FetchType.EAGER)
	private Agency agency;

	@Column(name = "mobile_number")
	private String mobileNumber;

	/*
	 * @JoinColumn(name = "designation")
	 * 
	 * @OneToOne(fetch = FetchType.EAGER)
	 */
	@Column(name = "designation")
	private String designation;

	@JoinColumn(name = "role")
	@OneToOne(fetch = FetchType.EAGER)
	private RoleMasterr role;

	@JoinColumn(name = "line_ministry_Id")
	@OneToOne(fetch = FetchType.EAGER)
	private CommonMasters lineMinistryId;
	
	@JoinColumn(name = "department_Id")
	@OneToOne(fetch = FetchType.EAGER)
	private Department departmentId;

	@JoinColumn(name = "maritime_board_Id")
	@OneToOne(fetch = FetchType.EAGER)
	private MaritimeBoard maritimeBoardId;
	
	@JoinColumn(name = "org_role_map_id")
	@OneToOne(fetch = FetchType.EAGER)
	private OrgRoleMapping orgRoleMapping;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "assign_project", joinColumns = {@JoinColumn(name = "user_Id")}, inverseJoinColumns = {
			@JoinColumn(name = "project_id")})
	private Set<Projects> projects = new HashSet<Projects>(0);
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_minor_port_mapping", joinColumns = { @JoinColumn(name = "user_Id") }, inverseJoinColumns = {
			@JoinColumn(name = "minor_port_id")})
	private Set<MinorPortMaster> minorPorts = new HashSet<MinorPortMaster>(0);

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getIsLoginUserNameEditable() {
		return isLoginUserNameEditable;
	}

	public void setIsLoginUserNameEditable(int isLoginUserNameEditable) {
		this.isLoginUserNameEditable = isLoginUserNameEditable;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsFirstAttempt() {
		return isFirstAttempt;
	}

	public void setIsFirstAttempt(int isFirstAttempt) {
		this.isFirstAttempt = isFirstAttempt;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
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

	
	public PORT getPort() {
		return port;
	}

	public void setPort(PORT port) {
		this.port = port;
	}

	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	
	public RoleMasterr getRole() {
		return role;
	}

	public void setRole(RoleMasterr role) {
		this.role = role;
	}

	public CommonMasters getLineMinistryId() {
		return lineMinistryId;
	}

	public void setLineMinistryId(CommonMasters lineMinistryId) {
		this.lineMinistryId = lineMinistryId;
	}

	public Department getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Department departmentId) {
		this.departmentId = departmentId;
	}

	public MaritimeBoard getMaritimeBoardId() {
		return maritimeBoardId;
	}

	public void setMaritimeBoardId(MaritimeBoard maritimeBoardId) {
		this.maritimeBoardId = maritimeBoardId;
	}

	public Set<Projects> getProjects() {
		return projects;
	}

	public void setProjects(Set<Projects> projects) {
		this.projects = projects;
	}

	public OgatizationMaster getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(OgatizationMaster organisationId) {
		this.organisationId = organisationId;
	}

	public StateMaster getState() {
		return state;
	}

	public void setState(StateMaster state) {
		this.state = state;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public OrgRoleMapping getOrgRoleMapping() {
		return orgRoleMapping;
	}

	public void setOrgRoleMapping(OrgRoleMapping orgRoleMapping) {
		this.orgRoleMapping = orgRoleMapping;
	}

	public Set<MinorPortMaster> getMinorPorts() {
		return minorPorts;
	}

	public void setMinorPorts(Set<MinorPortMaster> minorPorts) {
		this.minorPorts = minorPorts;
	}

	@Override
	public String toString() {
		return "UserDetail [userId=" + userId + ", userName=" + userName + ", isLoginUserNameEditable="
				+ isLoginUserNameEditable + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", isActive=" + isActive + ", isFirstAttempt=" + isFirstAttempt + ", deleted=" + deleted
				+ ", resetToken=" + resetToken + ", loginId=" + loginId + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", organisationId="
				+ organisationId + ", state=" + state + ", port=" + port + ", agency=" + agency + ", mobileNumber="
				+ mobileNumber + ", designation=" + designation + ", role=" + role + ", lineMinistryId="
				+ lineMinistryId + ", departmentId=" + departmentId + ", maritimeBoardId=" + maritimeBoardId
				+ ", orgRoleMapping=" + orgRoleMapping + ", projects=" + projects + ", minorPorts=" + minorPorts + "]";
	}

}
