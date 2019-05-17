<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
getRoleDropDownByDepartment('organisationId','roleId');
	var user = JSON.parse(localStorage.getItem('user_data'));
		if(user.role.roleId != -1){			
			$('#organisationId').val(user.organisationId.masterId).change();
			document.getElementById("organisationId").disabled = true;
		   //getRoleDropDownByDepartment('organisationId','role');
		}
	$(document).ready(function() {
		listRoleAuthorizedata(0, 10);
	});
</script>
 <style>
	   body{display: initial;}
	</style>
<!-- Content area -->

<!-- 2 columns form -->
<div class="card">
	<div class="card-header header-elements-inline">
		<h5 class="card-title font-weight-bold">Role Authorization</h5>
		<div class="header-elements">
			<div class="list-icons">
				<a class="list-icons-item" data-action="collapse"></a>
			</div>
		</div>
	</div>

	<div class="card-body">
		<form id="roleAuthlistform">
			<div class="row">
				<div class="col-md-6"> 
					<fieldset>
						<div class="form-group row">
							<label class="col-sm-5 col-form-label">Organization</label>
							<div class="col-sm-7">
								<select class="form-control" id="organisationId" onchange="getRoleDropDownByDepartment('organisationId','roleId');">
									<option value="0">Select Organization</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.organisation }"
										var="organisation">
										<option value="${organisation.masterId}">${organisation.masterName}</option>
									</c:forEach>

								</select>
							</div>
						</div>
					</fieldset>
				</div>

				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label class="col-sm-5 col-form-label">Role Name</label>
							<div class="col-sm-7">
								<!-- <input class="form-control" type="text" id="rolename"
									name="roleName"> -->
									<select class="form-control" id="roleId">
									<option value="0">Select Role</option>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
			</div>
		</form>
		<div class="text-right mb-1">
			<button type="submit"
				onclick="resetField('roleAuthlistform');listRoleAuthorizedata(0,10);"
				class="btn btn-sm">Reset</button>
			<button type="submit" class="btn btn-sm btn-primary"
				onclick="listRoleAuthorizedata(0, 10)">Search</button>

		</div>
		<div class="row"> 
				<div class="col-sm-12">
				   <div class="table-responsive">
					<table class="table datatable-basic table-bordered table-striped table-hover" id="table_details" style="width: 100%">
						<!-- <thead>
							<tr class="bg-primary">
								<td>S.No.</td>
								<td>Level</td>
								<td>Organization</td>
								<td>Role Name</td>
								<td>Role Type</td>
								<td>Assign</td>
							</tr>
						</thead> -->
						<tfoot>
							<tr>
								<td class="text-center" colspan="8"><span
									class="label label-danger"> There are no data available
								</span></td>
							</tr>
						</tfoot>
					</table>
				</div>
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
								<strong>Role wise Authorization</strong>
							</h5>
						</div>
					</div>
					<form id="roleAuthlistform">
						<div class="col-sm-12">
							<div class="form-group">
								<div class="col-sm-4">
									<label class="col-md-4 control-label">Role Name</label>
									<div class="col-md-8">
										<input class="form-control" type="text" id="rolename"
											name="roleName">
									</div>
								</div>
								<div class="col-sm-4">
									<label class="col-md-4 control-label">Role Type</label>
									<div class="col-md-8">
										<select class="form-control" id="roletype">
											<option value="0">Select Role Type</option>
											<c:forEach
												items="${ master.serviceResponse.commonMastersList.role_type }"
												var="roleType">
												<option value="${roleType.masterId}">${roleType.masterName}</option>
											</c:forEach>

										</select>
									</div>
								</div>
								<div class="col-sm-4">
									<label class="col-md-4 control-label">Level</label>
									<div class="col-md-8">
										<select class="form-control" id="level">
											<option value="0">Select Level</option>
											<c:forEach
												items="${ master.serviceResponse.commonMastersList.level }"
												var="level">
												<option value="${level.masterId}">${level.masterName}</option>
											</c:forEach>

										</select>
									</div>
								</div>

							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<div class="col-md-4">
									<label class="col-md-4 control-label">Department</label>
									<div class="col-md-8">
										<select class="form-control" id="department">
											<option value="0">Select Department</option>
											<c:forEach
												items="${ master.serviceResponse.commonMastersList.department }"
												var="department">
												<option value="${department.masterId}">${department.masterName}</option>
											</c:forEach>

										</select>
									</div>
								</div>
								<div class="col-md-4"></div>
								<div class="col-sm-4"></div>
							</div>
						</div>
					</form>
					<div class="col-sm-12">
						<div class="form-group">
							<div class="col-sm-6 "></div>
							<div class="col-sm-6 ">
								<label class="col-md-5 control-label"> </label>
								<div class="col-md-7">
									<button type="submit" class="btn btn-sm btn-primary pull-right m-r-2" onclick="listRoleAuthorizedata(0, 10)">Search</button>
									<button type="submit"
										onclick="resetField('roleAuthlistform');listRoleAuthorizedata(0,10);"
										class="btn btn-sm btn-grey pull-right m-r-2">Reset</button>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="row">
							<div class="general-table-block">
								<div class="table-responsive">
									<table class="table table-striped table-bordered text-center"
										 id="table_details">
										<thead class="heading">
											<tr>
												<td>S.No.</td>
												<td>Level</td>
												<td>Department</td>
												<td>Role Name</td>
												<td>Role Type</td>
												<td>Assign</td>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<td class="text-center" colspan="8"><span
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
