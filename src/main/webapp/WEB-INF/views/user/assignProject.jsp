<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
body {
	display: initial
}
</style>
<div class="card">
	<div class="card-header header-elements-inline">
		<h5 class="card-title font-weight-bold">Assign Projects</h5>
		<div class="header-elements">
			<div class="list-icons">
				<a class="list-icons-item" data-action="collapse"></a>
			</div>
		</div>
	</div>
	<div class="card-body">
		<form id="userlistform">
			<input type="hidden" id="userIdForAssignProject" name="userId"
				value="${userDetail.userId}" />
			<div class="row">
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<input type="hidden" id="custId" value="${userDetail}"></input> <label
								class="col-md-4 col-form-label">User Name</label>
							<div class="col-md-8">
								<input type="text" class="form-control"
									value="${userDetail.userName}" disabled />
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">Role Name</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="roleUserForAssign"
									value="${userDetail.role.roleName}" disabled />
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">Select Project</label>
							<div class="col-md-8">
								<select class=" form-control" multiple data-live-search="true"
									id="aProjectList">
									<option value="0">Select Projects</option>
									<c:forEach varStatus="selected"
										items="${master.serviceResponse.commonMastersList.allProjects}"
										var="projt">
										<option value="${projt.projectId}">${projt.projectName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-0" style="display: none;">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">Select Project</label>
							<div class="col-md-8">
								<select class=" form-control" multiple data-live-search="true"
									id="aProjectListHidden">
									<option value="0">Select Projects</option>
									<c:forEach varStatus="selected"
										items="${master.serviceResponse.commonMastersList.allProjects}"
										var="projt">
										<option value="${projt.projectId}">${projt.projectName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
				
				<div class="col-sm-12">
					<div class="text-right mb-3">
						<button type="button" onclick="getRequest('/assignedProjectList', listPage)" class="btn btn-sm btn-grey">Cancel</button>
						<button type="button" class="btn btn-sm btn-primary"
							onclick="saveAssignProjects();">Submit</button>
					</div>
				</div>
				<div class="col-sm-12 p-0">
					<hr />
				</div>
				<div class="col-sm-12">
					<div class="table-responsive">
						<table class="table datatable-basic table-bordered table-striped"
							id="assignedUsrPList_table">
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
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>

				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	$('.datatable-basic').DataTable();
	    var projectValueId = new Array();
	    var projectValues = new Array();
		<c:forEach var="project" items="${userDetail.projects}" >
			projectValueId.push("${project.projectId}");  
			projectValues.push("${project.projectName}");    
	    </c:forEach> 
	    $('#aProjectList').val(projectValueId);
	    $('#aProjectListHidden').val(projectValueId);
	fillAssignedProject(projectValueId,projectValues);
</script>