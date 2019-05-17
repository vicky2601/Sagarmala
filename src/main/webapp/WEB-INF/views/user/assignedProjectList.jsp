 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	getRoleDropDownByDepartment('assignPListOrgId','assignPListRole');
	listProjectAssignedUser(0, 10);
</script>
<div class="card">
	<div class="card-header header-elements-inline">
		<h5 class="card-title font-weight-bold">Assigned Project list</h5>
		<div class="header-elements">
			<div class="list-icons">
				<a class="list-icons-item" data-action="collapse"></a>
			</div>
		</div>
	</div> 
	<div class="card-body">
		<form id="userlistform">
			<div class="row">
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">Organization</label>
							<div class="col-md-8">
								<select class="form-control" name="" id="assignPListOrgId" onchange="">
									<option value="0">Select Organization</option> 
									<c:forEach
										items="${master.serviceResponse.commonMastersList.organisation}" var="org">
										<option value="${org.masterId}">${org.masterName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</fieldset>
				</div>

				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">Role Name</label>
							<div class="col-md-8">
								<select class="form-control" id="assignPListRole">
									<option value="0">Select Role</option> 
								</select>
							</div>
						</div>
					</fieldset>
				</div>
				<!-- <div class="col-md-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">User Name</label>
							<div class="col-md-8">
								<select class="form-control" id="assignPListUser">
									<option value="0">Select User</option> 
								</select>
							</div>
						</div>
					</fieldset>
				</div> -->
				
				 <div class="col-sm-12">
						<div class="text-right mb-3">
							<button type="button" onclick="resetAssignProjectOrgAndRole()" class="btn btn-sm btn-grey">Reset</button>
							<button type="button" class="btn btn-sm btn-primary" onclick="listProjectAssignedUser(0, 10);">Search</button>
						</div>
		         </div>
		         <div class="col-sm-12 p-0">
				  <hr />
				</div>
		         <div class="col-sm-12"> 
					<div class="table-responsive">
						<table class="table datatable-basic table-bordered table-striped"
							id="assignUsrPList_table">
							<thead>
								<tr class="bg-blue">
									<th>#</th>
									<th>User Name</th>
									<th>Organization</th>
									<th>Role</th>
									<th class="text-center">Assigned Projects</th>
									<th class="text-center">Add/Remove Projects</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td class="text-center"><button type="button"
											class="btn btn-sm btn-info" data-toggle="modal"
											data-target="#modal_large" id="btnTrigger">
											<i class="fa fa-eye"></i>
										</button></td>
									<td class="text-center">
										<button class="btn btn-sm btn-primary">
											<i class="fa fa-edit"></i>
										</button>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				   
				</div>
			</div>
		</form>
	</div>
</div>
 <!-- Large modal -->
				<div id="modal_large" class="modal fade" tabindex="-1">
					<div class="modal-dialog modal-full">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Assigned Projects</h5>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<div class="modal-body">
								<table class="table datatable-basic table-bordered table-striped" id="viewUsrPList_table">
							<thead>
								<tr class="bg-blue">
									<th>#</th>
									<th>Project ID</th>
									<th>Project Name</th> 
								</tr>
							</thead>
							<tbody>
								<tr>
									<td></td> 
									<td> </td>
									<td> </td> 
								</tr> 
							</tbody>
						</table>
							</div>

							<div class="modal-footer">
								 
							</div>
						</div>
					</div>
				</div>
				<!-- /large modal -->
<script>
$('.datatable-basic').DataTable();
</script>