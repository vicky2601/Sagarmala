<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="card">
	<div class="card-header header-elements-inline">
		<h5 class="card-title font-weight-bold">Concept List</h5>
		<div class="header-elements">
			<div class="list-icons">
				<!-- <a class="list-icons-item" data-action="collapse"></a> -->

			</div>
		</div>
	</div>

	<div class="card-body">
		<form id="userlistform">

			<div class="col-sm-12 ">
				<div class="form-group row">
					<div class="col-sm-6 float-left"></div>
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
							<label class="col-md-4 col-form-label">Project Name</label>
							<div class="col-md-8">
								<input class="form-control" type="text" name="projectName"
									id="projectName" />
							</div>
						</div>
					</fieldset>
				</div>

				<div class="col-md-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">Port</label>
							<div class="col-md-8">
								<select class="form-control" id="portId">
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
							<label class="col-md-4 col-form-label">Maritime Board</label>
							<div class="col-md-8">
								<select class="form-control" id="role">
									<option value="0">Select Board</option>
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
							<label class="col-md-4 col-form-label">Concept ID</label>
							<div class="col-md-8">
								<input class="form-control" type="text" name="conceptId"
									id="conceptId" />
							</div>
						</div>
					</fieldset>
				</div>

				<div class="col-md-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">State</label>
							<div class="col-md-8">
								<select class="form-control" id="stateId">
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

				<div class="col-md-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">Department</label>
							<div class="col-md-8">
								<select class="form-control" id="role">
									<option value="0">Select Department</option>
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
							<label class="col-md-4 col-form-label">Status</label>
							<div class="col-md-8">
								<input class="form-control" type="text" name="status"
									id="status" />
							</div>
						</div>
					</fieldset>
				</div>

				<div class="col-md-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label">Line Ministry</label>
							<div class="col-md-8">
								<select class="form-control" id="role">
									<option value="0">Select State</option>
								</select>
							</div>
						</div>
					</fieldset>
				</div>

				<div class="col-md-4">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label"> </label>
							<div class="col-md-8"></div>
						</div>
					</fieldset>
				</div>

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
						<button type="button" class="btn btn-grey">Reset</button>
						<button type="button" class="btn btn-primary" id=" ">Search</button>
						<button type="button" class="btn btn-warning" id=" ">Revive
							Project</button>

					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-12 p-0">
			<div class="table-responsive">
				<table class="table datatable-basic table-bordered table-striped"
					id="conceptlist">

				</table>
			</div>
		</div>
	</div>
</div>


<div class="card">

	<select NAME="ages" id="ages" style="display: none">

		<c:forEach items="${master.serviceResponse.commonMastersList.agency}"
			var="agency">
			<option VALUE="${agency.masterId}">${agency.masterName}</option>
		</c:forEach>
	</select> <select id="state1" NAME="state1" STYLE="display: none">

		<c:forEach items="${master.serviceResponse.commonMastersList.state}"
			var="state">
			<option VALUE="${state.masterId}">${state.masterName}</option>
		</c:forEach>


	</select> <select id="port1" NAME="port1" STYLE="display: none">

		<c:forEach items="${master.serviceResponse.commonMastersList.port}"
			var="port">
			<option value="${port.portId}">${port.portName}</option>
		</c:forEach>

	</select> <select id="locs" NAME="locs" STYLE="display: none">
		<c:forEach
			items="${master.serviceResponse.commonMastersList.locationmaster}"
			var="location">
			<option VALUE="${location.locationId}">${location.locationName}</option>
		</c:forEach>
	</select> <select id="props" NAME="props" STYLE="display: none">
		<c:forEach
			items="${master.serviceResponse.commonMastersList.organisation}"
			var="propenent">
			<option VALUE="${propenent.masterId}">${propenent.masterName}</option>
		</c:forEach>
	</select>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		conceptlist(0,10);
	});
</script>