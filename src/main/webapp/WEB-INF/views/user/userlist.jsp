<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Theme JS files -->

	<!-- /theme JS files -->
<script src="resources/js/tableHeadFixer.js"></script> 
		<script>
			$(document).ready(function() {
				$(".fixTable").tableHeadFixer(); 
			});
		</script>
<script type="text/javascript">
	$(document).ready(function() {		
		var user = JSON.parse(localStorage.getItem('user_data'));
		listUserData(0, 10);
		getRoleDropDownByDepartment('organisationId','role');
		
		if(user.role.roleId != -1){			
			$('#organisationId').val(user.organisationId.masterId).change();
			document.getElementById("organisationId").disabled = true;
		}
	});
</script>
	<style>
	   body{display: initial;}
	 div#table_details_wrapper {
  /*   width: 1751px; */
}  
#parent {
			height: 572px;
			overflow-x: auto;
			}
.table th {
    background-color: #1d88e5;
    z-index: 1;
  /*   width: 18% !important; */
}
	</style>
<!-- Content area -->

<!-- 2 columns form -->
<div class="card">
	<div class="card-header header-elements-inline">
		<h5 class="card-title font-weight-bold">User List</h5>
		<div class="header-elements">
			<div class="list-icons">
				<!-- <a class="list-icons-item" data-action="collapse"></a> -->
				<div id="btnAdd">
					<a href="javascript:void(0);" onclick="getRequest('/usermanagement',listPage);" class="btn btn-success pull-right"> <i class="fa fa-plus"></i> Add User </a>
				</div>
			</div>
		</div>
	</div>

	<div class="card-body">
		<form id="userlistform">
		 
		  <div class="col-sm-12 ">
        <div class="form-group row">
         <div class="col-sm-6 float-left"> 
         </div>
       <div class="col-sm-6 float-left p-0"> 
				<!-- <div id="btnAdd">
					<a href="javascript:void(0);" onclick="getRequest('/usermanagement',listPage);" class="btn btn-success pull-right"> <i class="fa fa-plus"></i> Add User </a>
				</div>  -->
       </div>
       </div>
       </div>
		 
			<div class="row">
				<div class="col-md-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">Organization</label>
							<div class="col-md-8">
								<select class="form-control" name="organisation" id="organisationId"
									onchange="enableDisableUserField();getRoleDropDownByDepartment('organisationId','role');setUrserListFieldWithoutOrg('organisationId','departmentId','maritimeboard','port','lineMinistry','agency','state','status','minnorPortId');">
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

				<div class="col-md-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">Role</label>
							<div class="col-md-8">
								<select class="form-control" id="role">
									<option value="0">Select Role</option>
								</select>
							</div>
						</div>
					</fieldset>
				</div>

				<div class="col-md-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">State</label>
							<div class="col-md-8">
								<select class="form-control" id="state"
									onchange="fillSubDepartmentMaritimeBoardByState('state', 'departmentId','maritimeboard','organisationId','port','minnorPortId','agency');">
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

			</div>

			<div class="row">
				<div class="col-md-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">Line Ministry </label>
							<div class="col-md-8">
								<select class="form-control" id="lineMinistry">
									<option value="0">Select Line Ministry</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.lineministry}"
										var="department">
										<option value="${department.masterId}">${department.masterName}</option>
									</c:forEach>

								</select>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">Port</label>
							<div class="col-md-8">
								<select class="form-control" id="port">
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

				<div class="col-md-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">Maritime Board </label>
							<div class="col-md-8">
								<select class="form-control" id="maritimeboard">
									<option value="0">Select Maritime Board</option>
									<c:forEach items="${master.serviceResponse.commonMastersList.maritimeboard}"
										var="maritimeboard">
										<option value="${maritimeboard.maritimeBoardId}">${maritimeboard.maritimeBoardName}</option>
									</c:forEach>

								</select>
							</div>
						</div>
					</fieldset>
				</div>

			</div>
			<div class="row">
				<div class="col-md-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">Agency </label>
							<div class="col-md-8">
								<select class="form-control" id="agency">
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

				<div class="col-md-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">Department </label>
							<div class="col-md-8">
								<select class="form-control" id="departmentId">
									<option value="0">Select Department</option>
									<c:forEach items="${master.serviceResponse.commonMastersList.department}"
										var="department">
										<option value="${department.departmentId}">${department.departmentName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
			<div class="col-md-4" id="assignMiPortDev">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">Status </label>
							<div class="col-md-8">
								<select class="form-control" id="status">
									<option value="2">Select Status</option>
									<option value="1">Active</option>
									<option value="0">InActive</option>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-4">
					<fieldset>
					<div class="form-group row">
							<label class="col-md-4 col-form-label">Minor Ports</label>
							<div class="col-md-8">
								<select name="minnor_ports" id="minnorPortId"
									class="mdb-select md-form colorful-select dropdown-primary form-control"
									multiple>
									<option value="0">Select Minnor Ports</option>
									<c:forEach items="${master.serviceResponse.commonMastersList.minnerPorts}"
										var="miPorts">
										<option value="${miPorts.masterId}">${miPorts.masterName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
					</fieldset>
				</div>
				<div class="col-md-4"></div>
				<div class="col-md-4"></div>
			</div>
		</form>

       <div class="col-sm-12 ">
        <div class="form-group row">
       <div class="col-sm-6 float-left p-0"> 
				<div id="btnAdd" class="col-sm-6 p-0">
					<!-- <a href="javascript:void(0);"
						onclick="getRequest('/usermanagement',listPage);"
						class="btn btn-sm btn-success"> <i
						class="fa fa-plus"></i> Add User
					</a> -->
				</div> 
       </div>
       <div class="col-sm-6 float-left p-0">
		<div class="text-right mb-1">
			<button type="submit"
				onclick="resetField('userlistform');listUserData(0,10);"
				class="btn btn-grey">Reset</button>
			<button type="submit" class="btn btn-primary"
				onclick="listUserData(0,10)" id="spinner-dark-2">Search</button>
				 
		</div>
		</div>
		</div>
		</div>
		<div class="col-sm-12">
			<!-- <div class="form-group row">
				<div class="col-sm-6 "></div>
				<div id="btnAdd" class="col-sm-6 p-0">
					<a href="javascript:void(0);"
						onclick="getRequest('/usermanagement',listPage);"
						class="btn btn-sm btn-success pull-right"> <i
						class="fa fa-plus"></i> Add User
					</a>
				</div>
			</div> -->
		</div>

		<div class="col-sm-12 col-md-12 p-0"> 
				<div class="text-right mb-1">
					<button class="btn btn-success btn-sm m-r-5" type="button" icon="fa fa-file-excel-o" onclick="downloadUserExcel(0,10)"><i class="fa fa-file-excel-o"></i> Export to Excel
					</button>
					<button class="btn btn-warning btn-sm m-r-5" type="button" icon="fa fa-file-pdf-o" onclick="downloadUserPdf(0,10)"><i class="fa fa-file-pdf-o"></i> Export to PDF
					</button>
				</div> 
		</div>
		<div class="col-sm-12 p-0">
		<div  id="parent">
				<table class="table datatable-basic table-bordered table-striped table-hover fixtable" id="table_details" style="width: 100%">
					 <!-- <thead>
						<tr class="bg-blue">
							<th>S.No.</th>
							<th>Login ID</th>
							<th>User Name</th>
							<th>Organization</th>
							<th>Role</th>
							<th>State</th>
							<th>Port</th>
							<th>Maritime Board</th>
							<th>Line Ministry</th>
							<th>Department</th>
							<th>Agency</th>
							<th>Edit</th>
							<th>Status</th>
							<th>View</th>
						</tr>
					</thead>   -->
					<tfoot>
						<tr>
							<td class="text-center" colspan="14"><span class="label label-danger"> There are no data available </span></td>
						</tr>
					</tfoot>
				</table>
			 </div>
		 </div>
		 

			</div>
		</div>

	 


<!-- /content area -->





<%-- <div class="tab-content">
		<div class="tab-pane active in">
			<div class="form-horizontal">
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<h5>
								<strong>User List</strong>
							</h5>
						</div>
					</div>
					<form id="userlistform">
						<div class="col-sm-12">
							<div class="form-group">
								<div class="col-sm-4">
									<label class="col-md-4 control-label">Department</label>
									<div class="col-md-8">
										 <select class="form-control" name="department" id="organisationId"
											onchange="getRoleDropDownByDepartment(this,'role')">
											<option value="0">Select Department</option>
											<c:forEach
												items="${ master.serviceResponse.commonMastersList.department}"
												var="department">
												<option value="${department.masterId}">${department.masterName}</option>
											</c:forEach>
										</select> 
									</div>
								</div>
								<div class="col-sm-4">
									<label class="col-md-4 control-label">Role</label>
									<div class="col-md-8">
										 <select class="form-control" id="role">
											<option value="0">Select Role</option>
										</select> 
									</div>
								</div>
								<div class="col-sm-4">
									<label class="col-md-4 control-label">State</label>
									<div class="col-md-8">
										 <select class="form-control" id="state"
											onchange="fillSubDepartmentMaritimeBoardByState(this, 'departmentId','maritimeboard','organisationId');">
											<option value="0">Select State</option>
											<c:forEach
												items="${master.serviceResponse.commonMastersList.state}"
												var="state">
												<option value="${state.masterId}">${state.masterName}</option>
											</c:forEach>
										</select>  
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<div class="col-md-4">
									<label class="col-md-4 control-label">Line Ministry</label>
									<div class="col-md-8">
										 <select class="form-control" id="lineMinistry">
											<option value="0">Select Line Ministry</option>
											<c:forEach
												items="${ master.serviceResponse.commonMastersList.lineministry }"
												var="department">
												<option value="${department.masterId}">${department.masterName}</option>
											</c:forEach>

										</select> 
									</div>
								</div>
								<div class="col-md-4">
									<label class="col-md-4 control-label">Port</label>
									<div class="col-md-8">
										<select class="form-control" id="port">
											<option value="0">Select Port</option>
											<c:forEach
												items="${master.serviceResponse.commonMastersList.designation}"
												var="designation">
												<option value="${designation.masterId}">${designation.masterName}</option>
											</c:forEach>

										</select>
									</div>
								</div>
								<div class="col-md-4">
									<label class="col-md-4 control-label">Maritime Board</label>
									<div class="col-md-8">
										<select class="form-control" id="maritimeboard">
											<option value="0">Select Maritime Board</option>
											<c:forEach
												items="${master.serviceResponse.commonMastersList.designation}"
												var="designation">
												<option value="${designation.masterId}">${designation.masterName}</option>
											</c:forEach>

										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<div class="col-md-4">
									<label class="col-md-4 control-label">Agency</label>
									<div class="col-md-8">
										<select class="form-control" id="agency">
											<option value="0">Select Agency</option>
											<c:forEach items="${master.serviceResponse.role}" var="role">
												<option value="${role.roleId}">${role.roleName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<label class="col-md-4 control-label">Sub-Department</label>
									<div class="col-md-8">
										<select class="form-control" id="departmentId">
											<option value="0">Select Sub-Department</option>
											<c:forEach
												items="${master.serviceResponse.commonMastersList.agency}"
												var="agency">
												<option value="${agency.masterId}">${agency.masterName}</option>
											</c:forEach>

										</select>
									</div>
								</div>
								<div class="col-md-4">
									<label class="col-md-4 control-label">Status</label>
									<div class="col-md-8">
										<select class="form-control" id="status">
											<option value="2">Select Status</option>
											<option value="1">Active</option>
											<option value="0">InActive</option>

										</select>
									</div>
								</div>
							</div>
						</div>
					</form>
					<div class="col-sm-12">
						<div class="form-group">
							<div class="col-sm-6 "></div>
							<div class="col-sm-6 ">
								<label class="col-md-5 control-label"> </label>
								<div class="col-md-7">
									<button type="submit"
										class="btn btn-sm btn-primary pull-right m-r-2"
										onclick="listUserData(0,10)">Search</button>
									<button type="submit"
										onclick="resetField('userlistform');listUserData(0,10);"
										class="btn btn-sm btn-grey pull-right m-r-2">Reset</button>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="form-group">
							<div class="col-sm-6 "></div>
							<div class="col-sm-6 ">
								<label class="col-md-5 control-label"> </label>
								<div class="col-md-7">
									<a href="javascript:void(0);"
										onclick="getRequest('/usermanagement',listPage);"
										class="btn btn-success pull-right"> <i class="fa fa-plus"></i>
										Add User
									</a>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-12 col-md-12">
						<div class="row">
							<div class="general-table-block">
								<div class="table-responsive">
									<table class="table table-striped table-bordered text-center"
										id="table_details">
										<thead class="heading">
											<tr>
												<td>S.No.</td>
												<td>Login ID</td>
												<td>User Name</td>
												<td>Department</td>
												<td>Role</td>
												<td>State</td>
												<td>Port</td>
												<td>Maritime Board</td>
												<td>Line Ministry</td>
												<td>Sub-department</td>
												<td>Agency</td>
												<td>Edit</td>
												<td>Status</td>
												<td>View</td>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<td class="text-center" colspan="14"><span
													class="label label-danger"> There are no data
														available </span></td>
											</tr>
										</tfoot>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div> --%>
 
