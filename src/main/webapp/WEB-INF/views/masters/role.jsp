<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<body>
<%--  ${master.serviceResponse.commonMastersList.state}  --%>
	<div class="card">
		<div class="card-header header-elements-inline">
			<h5 class="card-title font-weight-bold">Role</h5>
			<div class="header-elements">
				<div class="list-icons">
					<a class="list-icons-item" data-action="collapse"></a>
				</div>
			</div>
		</div>

		<div class="card-body">
			<div class="col-sm-12 card-box">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Organization</label>
							<div class="col-md-9">
								<select class="form-control" id="byOrg">
									<option value="0">Select Organisation</option>
								<c:forEach
										items="${master.serviceResponse.commonMastersList.organisation}"
										var="organisation1">
									<option value="${organisation1.masterId}">${organisation1.masterName}</option>
								</c:forEach>
							</select>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Role</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="byRole" onkeydown="validateMastersName(event)">
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="text-right">
							<button type="button" class="btn btn-primary">
								<i class="icon-search4"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 card-box">
				<div class="form-group row">
					<div id=" " class="col-sm-12">
						<button type="button" class="btn btn-sm btn-success pull-right"
							data-toggle="modal" data-target="#roleaddbtn">
							<i class="fa fa-plus"></i> Add New
						</button>
					</div>
				</div>
			</div>
			<div class="col-sm-12 card-box">
				<table class="table datatable-responsive table-bordered">
					<thead>
						<tr>
							<th>Organization</th>
							<th class="text-center" width="220px">Role</th>
							<th class="text-center" width="220px">Edit</th>
							<th class="text-center" width="220px">Status</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td></td>
							<td align="center">A</td>
							<td align="center">
								<a class="btn btn-warning btn-sm" href="javascript:void(0);" data-toggle="modal" data-target="#roleeditbtn">
									<i class="fa fa-edit"></i>
								</a>
							</td>
							<td align="center">
								<label class="switch">
									<input type="checkbox">
									<span class="slider round"></span>
								</label>
							</td>
						</tr>
						<tr>
							<td></td>
							<td align="center"></td>
							<td align="center">
								<a class="btn btn-warning btn-sm"
								href="javascript:void(0);" data-toggle="modal"
								data-target="#roleeditbtn"><i class="fa fa-edit"></i></a>
							</td>
							<td align="center">
								<label class="switch">
									<input type="checkbox" checked="">
									<span class="slider round"> </span>
								</label>
							</td>
						</tr>
					</tbody>
				</table>

			</div>

		</div>
	</div>

	<div id="roleaddbtn" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Add New Role</h5>
					<button type="button" class="bootbox-close-button close"
						data-dismiss="modal" aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Organization<span class="compulsory-field">*</span></label>
						<div class="col-md-9">
							<select class="form-control" id="organisation">
							   <option value="0">Select Organisation</option>  
							<c:forEach
										items="${master.serviceResponse.commonMastersList.organisation}"
										var="organisation1">
										<option value="${organisation1.masterId}">${organisation1.masterName}</option>
								</c:forEach> 
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Role<span class="compulsory-field">*</span></label>
						<div class="col-md-9">
							<input type="text"  class="form-control" id="roleName" onkeypress="validateMastersName(event)" ondrag="return false"
									ondrop="return false" placeholder=" ">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" type="button" class="btn btn-default" onclick="clearName()" data-dismiss="modal">Cancel</button>
					<button type="button" type="button"
						class="btn btn-primary" onclick="createRoles()">Submit</button>
				</div>
			</div>
		</div>
	</div>

	<div id="roleeditbtn" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Update Role</h5>
					<button type="button" class="bootbox-close-button close"
						data-dismiss="modal" aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Organization<span class="compulsory-field">*</span></label>
						<div class="col-md-9">
							<select class="form-control" id="byOrganisation1">
							 <option value="0">Select Organisation</option>
							   <c:forEach
									items="${master.serviceResponse.commonMastersList.organisation}"
										var="org">
											 <option value="${org.masterId}">${org.masterName}</option> -
										</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Add Role<span class="compulsory-field">*</span></label>
						<div class="col-md-9">
							<input type="text" class="form-control" id="roleName" onkeypress="validateMastersName(event)" placeholder=" " >
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" type="button" class="btn btn-default" onclick="clearName()" data-dismiss="modal">Cancel</button>
					<button type="button" type="button"
						class="btn btn-primary" onclick="createRoles()">Submit</button>
				</div>
			</div>
		</div>
	</div>



</body>
<script type="text/javascript">

function clearName() {
	var a = document.getElementById("byRole");
	a.value = a.defaultValue;
};
$(document).ready(function() {
	listRoleData(0,10);
});
</script>

