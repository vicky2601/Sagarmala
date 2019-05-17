<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="card">
	<div class="card-header header-elements-inline">
		<h5 class="card-title font-weight-bold">Edit Profile</h5>
		<div class="header-elements">
			<div class="list-icons">
				<a class="list-icons-item" data-action="collapse"></a>
			</div>
		</div>
	</div>
<%-- ${master.serviceResponse.commonMastersList.agency} --%>
	<div class="card-body">
		<form method="post" id="adduserform">
			<input type="hidden" id="userId" name="userId"
				value="" /> <input type="hidden" id="status"
				name="status" value="" /> <input
				type="hidden" id="isfirstattempt" name="isfirstattempt"
				value="" />
			<div class="row">
				<div class="col-md-4" id="organisationDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Organization</label>
							<div class="col-md-7">
								<select class="form-control" name="organisation" id="organisationId"
									onchange="getRoleDropDownByDepartment('organisationId','roleId');" disabled="disabled">
									<option value="0">Select Organization</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.organisation}"
										var="organisation">
										<option value="${organisation.masterId}" >${organisation.masterName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</fieldset>
				</div>

				<div class="col-md-4" id="roleDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Role</label>
							<div class="col-md-7">
								<select class="form-control" name="role" id="roleId" disabled="disabled">
									<option value="0">Select Roles</option>	
									 <c:forEach
										items="${master.serviceResponse.roleList}"
										var="role">
										<option value="${role.roleId}">${role.roleName}</option>
									</c:forEach>				 
								</select>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-4" id="lineMinistryDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Line Ministry<span
								class="compulsory-field">*</span></label>
							<div class="col-md-7">
								<select class="form-control" name="lineMinistry"
									id="lineMinistryId" disabled="disabled">
									<option value="0">Select Line Ministry</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.lineministryId}"
										var="lineministry">
										<option value="${lineministry.masterId}">${lineministry.masterName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
			</div>

			<div class="row">
				<div class="col-md-4" id="stateDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">State</label>
							<div class="col-md-7">
								<select class="form-control" name="state" id="stateId"
									onchange="fillSubDepartmentMaritimeBoardByState('stateId', 'sub-departmentId','boardId','departmentId','portId');" disabled="disabled" >
									<option value="0">Select State</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.state}"
										var="state">
									  <option value="${state.masterId}">${state.masterName}</option> 
									</c:forEach>
								</select>
							</div>
						</div>
					</fieldset>
				</div>

				<div class="col-md-4" id="departmentDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Department<span
								class="compulsory-field">*</span></label>
							<div class="col-md-7">
								<select class="form-control" name="department"
									id="departmentId" disabled="disabled">
									<option value="">Select Department</option>
									 <c:forEach
										items="${master.serviceResponse.commonMastersList.department}"
										var="department">
										<option value="${department.departmentId}" >${department.departmentName}</option>
									</c:forEach> 
								</select>
							</div>
						</div>
					</fieldset>
				</div>

				<div class="col-md-4" id="portDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Port</label>
							<div class="col-md-7">
								<select class="form-control" name="port" id="portId" disabled="disabled">
									<option value="0">Select Port</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.port}"
										var="port">
										<option value="${port.portId}">${port.portName}</option>
									</c:forEach> 
								</select>
							</div>
						</div>
					</fieldset>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4" id="boardDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Maritime Board<span
								class="compulsory-field">*</span></label>
							<div class="col-md-7">
								<select class="form-control" name="board" id="boardId" disabled="disabled">
									<option value="0">Select Maritime Board</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.maritimeBoardId}"
										var="maritime">
										<option value="${maritime.maritimeBoardId}">${maritime.maritimeBoardName}</option>
									</c:forEach>  
								</select>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-4" id="agencyDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Implementation
								Agency<span class="compulsory-field">*</span>
							</label>
							<div class="col-md-7">
								<select class="form-control" name="implementation_agency"
									id="agencyId" onchange="getLoginId();" disabled="disabled">
									<option value="0">Select Agency</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.agency}"
										var="agency">
										<option value="${agency.masterId}">${agency.masterName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-4" id="assignDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Assign Project/s<span
								class="compulsory-field">*</span></label>
							<div class="col-md-7">
								<select name="assign_project" id="assignProjectId"
									class="mdb-select md-form colorful-select dropdown-primary form-control"
									multiple searchable="Search here..">
									<c:forEach items="${project.serviceResponse.projectList}"
										var="project">
										<option value="${project.projectId}">${project.projectName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</fieldset>
				</div>

			</div>
			<div class="row">
				<div class="col-sm-4" id="designationDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Designation</label>
							<div class="col-md-7">
								<input class="form-control" type="text" id="designation" disabled
									name="designationId" onkeydown="allowOnlyAlphabet(event)"
									ondrag="return false" value="" ondrop="return false">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-sm-4" id="loginDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Login Id<span
								class="compulsory-field"></span></label>
							<div class="col-md-7">
								<input class="form-control" type="text" id="userName"
									name="userName" value="" disabled>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-sm-4" id="nameDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Name<span
								class="compulsory-field"></span></label>
							<div class="col-md-7">
								<input class="form-control" type="text" id="name" name="name" disabled
									onkeydown="allowOnlyAlphabet(event)" value="" ondrag="return false"
									ondrop="return false">
							</div>
						</div>
					</fieldset>
				</div>
			</div>

			<div class="row" id="phoneDiv">
				<div class="col-sm-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Mobile Number<span
								class="compulsory-field">*</span></label>
							<div class="col-md-7">
								<input class="form-control" id="mobile_number" type="text"
									maxlength="10" name="mobile_number"
									onkeydown="allowOnlyNumber(event)" value="" ondrag="return false"
									ondrop="return false">
									<input type="hidden" id="mobilealt" value="">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-sm-4" id="emailDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Email ID<span
								class="compulsory-field">*</span></label>
							<div class="col-md-7">
								<input class="form-control" type="text" id="email" value="${userDetails.email}" name="email">
									<input  type="hidden" id="emailalt" value="">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-sm-4"></div>
			</div>
		</form>
		<div class="text-right mb-1">
			<button type="submit" value="Cancel"
				onclick="getRequest('/user-list', listPage);"
				class="btn btn-sm btn-default">Cancel</button>
		<!-- <button type="button" class="btn btn-sm btn-primary" 
				onclick="addUserData('edit');">Update</button>  -->
				 <button type="button" class="btn btn-sm btn-primary"
				onclick="editProfileByPhoneAndEmail('edit');">Update</button>  
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
	    
	   	editProfileData();
	   	
		$('#assignDiv').hide();
		
		var projectValues = new Array();
		
	     console.log(projectValues);
	  
		//document.getElementById("organisationId").disabled = true;
		//document.getElementById("roleId").disabled = true;
	
	});
</script>