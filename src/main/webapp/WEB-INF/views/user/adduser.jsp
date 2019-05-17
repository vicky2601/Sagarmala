<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="card">
	<div class="card-header header-elements-inline">
		<h5 class="card-title font-weight-bold">Add User</h5>
		<div class="header-elements">
			<div class="list-icons">
				<a class="list-icons-item" data-action="collapse"></a>
			</div>
		</div>
	</div>
	<div class="card-body">
		<form method="post" id="adduserform">
		    <div class="row">
		       <div class="col-md-4" id="orgDiv">
		           <fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Organization</label>
							<div class="col-md-7">
								<select class="form-control" name="organisation" id="organisationId"
									onchange="hideShowUserField();getRoleDropDownByDepartment('organisationId','roleId');getLoginId();">
									<option value="0">Select Organization</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.organisation}"
										var="organisation">
										<option value="${organisation.masterId}">${organisation.masterName}</option>
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
								<select class="form-control" name="role" id="roleId" >
									<option value="0">Select Role</option>
									<c:forEach items="${master.serviceResponse.role}" var="role">
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
									id="lineMinistryId" onchange="getLoginId();">
									<option value="0">Select Line Ministry</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.lineministry}"
										var="lineministry" >
										<option value="${lineministry.masterId}" >${lineministry.masterName}</option>
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
									onchange="fillSubDepartmentMaritimeBoardByState('stateId', 'departmentId','boardId','organisationId','portId','minnorPortId','agencyId');getLoginId();">
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
								<select class="form-control" name="port" id="portId"
									onchange="getLoginId();">
									<option value="0">Select Major Port</option>
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
								<select class="form-control" name="board" id="boardId"
									onchange="getLoginId();">
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

			 
				<div class="col-sm-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Designation</label>
							<div class="col-md-7">
								<input class="form-control" type="text" id="designation"
									name="designationId" onkeydown="allowOnlyAlphabet(event)"
									ondrag="return false" ondrop="return false">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-sm-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Login Id<span
								class="compulsory-field">*</span></label>
							<div class="col-md-7">
								<input class="form-control" type="text" id="userName"
									name="userName" maxlength="10" onkeydown="allowOnlyAlphaNumberic(event)" style="text-transform:uppercase">
									 <!-- this line is commented on 18-02-2019 due to change informed by neetu <input type="checkbox" id="checkLoginIdEditable" onclick="editableLoginId();">Edit -->
							</div>							
						</div>
					</fieldset>
				</div>
				<div class="col-sm-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Name<span
								class="compulsory-field">*</span></label>
							<div class="col-md-7">
								<input class="form-control" type="text" id="name" name="name"
									onkeydown="allowOnlyAlphabet(event)" ondrag="return false"
									ondrop="return false">
							</div>
						</div>
					</fieldset>
				</div>
		 
				<div class="col-sm-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Mobile Number<span
								class="compulsory-field">*</span></label>
							<div class="col-md-7">
								<input class="form-control" id="mobile_number" type="text"
									maxlength="10" name="mobile_number"
									onkeydown="allowOnlyNumber(event)" ondrag="return false"
									ondrop="return false">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-sm-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Email ID<span
								class="compulsory-field">*</span></label>
							<div class="col-md-7">
								<input class="form-control" type="text" id="email" name="email">
							</div>
						</div>
					</fieldset>
				</div>
				<!--   <div class="col-sm-4"></div> -->
				
				<div class="col-md-4" id="assignMiPortDev">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-5 col-form-label">Minnor Ports<span
								class="compulsory-field">*</span></label>
							<div class="col-md-7">
								<select name="minnor_ports" id="minnorPortId"
									class="mdb-select md-form colorful-select dropdown-primary form-control"
									multiple searchable="Search here..">
									<c:forEach items="${master.serviceResponse.commonMastersList.minnerPorts}"
										var="miPorts">
										<option value="${miPorts.masterId}">${miPorts.masterName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</fieldset>
				</div>	
			</div>
		</form>
		<div class="text-right mb-1">
			<button type="submit" value="Cancel"
				onclick="getRequest('/user-list', listPage);"
				class="btn btn-sm btn-default">Cancel</button>
			<button type="submit" class="btn btn-sm btn-primary"
				onclick="addUserData('add');">Submit</button>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$('#assignDiv').hide();
		document.getElementById("userName").disabled = true;
		var user = JSON.parse(localStorage.getItem('user_data'));
		$('#assignDiv').hide();
		if(user.role.roleId != -1){			
			$('#organisationId').val(user.organisationId.masterId).change();
			document.getElementById("organisationId").disabled = true;
		
			if(user.lineMinistryId.masterId!=-1){
				$('#lineMinistryId').val(user.lineMinistryId.masterId).change();
				document.getElementById("lineMinistryId").disabled = true;
			}
			
			if(user.departmentId.departmentId!=-1 && user.state.masterId!=-1){
                $('#stateId').val(user.state.masterId).change();
                document.getElementById("stateId").disabled = true;
            }
		}
	});
	function editableLoginId(){
		let checkbox = document.getElementById('checkLoginIdEditable');		
		if(checkbox.checked) {
			document.getElementById("userName").disabled = false;
		} else {
			document.getElementById("userName").disabled = true;
			getLoginId();
		}
	}
</script>
