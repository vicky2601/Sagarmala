<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	$(document).ready(function() {
		listRoledata(0, 10);
	});
</script>


</head>

<body>
	<div class="tab-content">
		<div class="tab-pane active in">
			<div class="form-horizontal">
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<h5>
								<strong>Manage Role</strong>
							</h5>
						</div>
					</div>
					<form id="rolelistform">
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
									<label class="col-md-4 control-label">Status</label>
									<div class="col-md-8">
										<select class="form-control" id="status">
											<option value="2">Select Status</option>
											<option value="1">Active</option>
											<option value="0">InActive</option>

										</select>
									</div>
								</div>
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
									<button type="submit"
										class="btn btn-sm btn-primary pull-right m-r-2"
										onclick="listRoledata(0,10)">Search</button>
									<button type="submit"
										onclick="resetField('rolelistform');listRoledata(0,10);"
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
									<a onclick="getRequest('/redirectToAddRole', listPage);"
										class="btn btn-success pull-right"> <i class="fa fa-plus"></i>
										Add Role
									</a>

								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="row">
							<div class="general-table-block">
								<div class="table-responsive">
									<table class="table table-striped table-bordered text-center"
										width="100%" id="table_details">
										<thead class="heading">
											<tr>
												<td>S.No.</td>
												<td>Level</td>
												<td>Department</td>
												<td>Role Name</td>
												<td>Role Type</td>
												<td>Edit</td>
												<td>Status</td>
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
	</div>
</body>
</html>
