<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="card">
	<div class="card-header header-elements-inline">
		<h5 class="card-title font-weight-bold">Edit User</h5>
		<div class="header-elements">
			<div class="list-icons">
				<a class="list-icons-item" data-action="collapse"></a>
			</div>
		</div>
	</div>

	<div class="card-body">
		<form method="post" id="adduserform">
			<input type="hidden" id="userId" name="userId"
				value="${userDetail.userId}" /> <input type="hidden" id="status"
				name="status" value="${userDetail.isActive}" /> <input
				type="hidden" id="isfirstattempt" name="isfirstattempt"
				value="${userDetail.isFirstAttempt}" />
			<div class="row">
				<div class="col-md-4" id="organisationDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Organization</label>
							<div class="col-md-7">
								<select class="form-control" name="organisation" id="organisationId"
									onchange="hideShowUserFieldForEditView();getRoleDropDownByDepartment('organisationId','roleId');">
									<option value="0">Select Organization</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.organisation}"
										var="organisation">
										<option value="${organisation.masterId}" ${organisation.masterId == userDetail.organisationId.masterId ? 'selected' : ''}>${organisation.masterName}</option>
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
								<select class="form-control" name="role" id="roleId">
									<option value="0">Select Role</option>									
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
									id="lineMinistryId">
									<option value="0">Select Line Ministry</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.lineministry}"
										var="lineministry">
										<option value="${lineministry.masterId}" ${lineministry.masterId == userDetail.lineMinistryId.masterId ? 'selected' : ''}>${lineministry.masterName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
			 
				<div class="col-md-4" id="stateDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">State</label>
							<div class="col-md-7">
								<select class="form-control" name="state" id="stateId"
									onchange="fillSubDepartmentMaritimeBoardByState('stateId', 'sub-departmentId','boardId','departmentId','portId');">
									<option value="0">Select State</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.state}"
										var="state">
										<option value="${state.masterId}" ${state.masterId == userDetail.state.masterId ? 'selected' : ''}>${state.masterName}</option>
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
									id="departmentId">
									<option value="0">Select Department</option>
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
								<select class="form-control" name="port" id="portId">
									<option value="0">Select Port</option>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
			 
				<div class="col-md-4" id="boardDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Maritime Board<span
								class="compulsory-field">*</span></label>
							<div class="col-md-7">
								<select class="form-control" name="board" id="boardId">
									<option value="0">Select Maritime Board</option>
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
									id="agencyId" onchange="getLoginId();">
									<option value="0">Select Agency</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.agency}"
										var="agency">
										<option value="${agency.masterId}" ${agency.masterId == userDetail.agency.masterId ? 'selected' : ''}>${agency.masterName}</option>
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

			 
				<div class="col-sm-4" id="designationDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Designation</label>
							<div class="col-md-7">
								<input class="form-control" type="text" id="designation"
									name="designationId" onkeydown="allowOnlyAlphabet(event)"
									ondrag="return false" value="${userDetail.designation}" ondrop="return false">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-sm-4" id="loginDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Login Id<span
								class="compulsory-field">*</span></label>
							<div class="col-md-7">
								<input class="form-control" type="text" id="userName"
									name="userName" value="${userDetail.userName}" disabled>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-sm-4" id="nameDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Name<span
								class="compulsory-field">*</span></label>
							<div class="col-md-7">
								<input class="form-control" type="text" id="name" name="name"
									onkeydown="allowOnlyAlphabet(event)" value="${userDetail.name}" ondrag="return false"
									ondrop="return false">
							</div>
						</div>
					</fieldset>
				</div> 
				<div class="col-sm-4" id="phoneDiv">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Mobile Number<span
								class="compulsory-field">*</span></label>
							<div class="col-md-7">
								<input class="form-control" id="mobile_number" type="text"
									maxlength="10" name="mobile_number"
									onkeydown="allowOnlyNumber(event)" value="${userDetail.mobileNumber}" ondrag="return false"
									ondrop="return false">
									<input type="hidden" id="mobilealt" value="${userDetail.mobileNumber}">
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
								<input class="form-control" type="text" id="email" value="${userDetail.email}" name="email">
									<input  type="hidden" id="emailalt" value="${userDetail.email}">
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
			<button type="button" class="btn btn-sm btn-primary"
				onclick="addUserData('edit');">Update</button>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var user = JSON.parse(localStorage.getItem('user_data'));
		hideShowUserFieldForEditView();
		getRoleDropDownByDepartment('organisationId','roleId');	
		$('#assignDiv').hide();
		fillSubDepartmentMaritimeBoardByState('stateId', 'sub-departmentId','boardId','organisationId','portId');
		var projectValues = new Array();
		<c:forEach var="project" items="${userDetail.projects}" >
			projectValues.push("${project.projectId}");   
	    </c:forEach>
	    console.log(projectValues);
		$("#assignProjectId").val(projectValues);
		setTimeout(function(){			
			$('#roleId').val('${userDetail.role.roleId}');
			$('#portId').val('${userDetail.port.portId}').change();
			$('#boardId').val('${userDetail.maritimeBoardId.maritimeBoardId}').change();
			$('#departmentId').val('${userDetail.departmentId.departmentId}').change();
		}, 1000);		
		document.getElementById("organisationId").disabled = true;
	/*	if (user.role.roleTpyeId.masterId != 41) {
			document.getElementById("departmentId").disabled = true;			
			document.getElementById("roleId").disabled = true;
			document.getElementById("designationId").disabled = true;
			document.getElementById("stateId").disabled = true;
			document.getElementById("portId").disabled = true;
			document.getElementById("agencyId").disabled = true;
			document.getElementById("userName").disabled = true;
			document.getElementById("name").disabled = true;
		}*/
	});
</script>