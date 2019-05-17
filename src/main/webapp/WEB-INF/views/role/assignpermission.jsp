<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript">
        $(document).ready(function() {        	
        	$.extend($.fn.treegrid.defaults, {
        	    expanderExpandedClass: 'fa fa-minus',
        	    expanderCollapsedClass: 'fa fa-plus'
        	});

            $('.tree').treegrid();
            $('.tree-2').treegrid({
                expanderExpandedClass: 'glyphicon glyphicon-minus',
                expanderCollapsedClass: 'glyphicon glyphicon-plus'
            });

        });
        </script>
<div class="card">
	<div class="card-header header-elements-inline">
		<h5 class="card-title font-weight-bold">Assign Permission</h5>
		<div class="header-elements">
			<div class="list-icons">
				<a class="list-icons-item" data-action="collapse"></a>
			</div>
		</div>
	</div>

	<div class="card-body">
		<form id="roleauthorization">
			<%-- 	 <input  id= "hidden" value="${roleAuthDescription[0].Id}">  --%>

			<div class="row">
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label class="col-sm-5 col-form-label">Organization</label>
							<div class="col-sm-7">
								<select class="form-control" name="select-1544426406205"
									id="select-1544426406205" disabled>
									<option
										value="${roleDescription.serviceResponse.role.organisation.masterId}"
										selected="true" id="select-1544426406205-0">${roleDescription.serviceResponse.role.organisation.masterName}</option>

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
								<input type="text" class="form-control"
									name="text-1544426409365" id="text-1544426409365"
									value="${roleDescription.serviceResponse.role.roleMaster.roleName}" disabled>
							</div>
						</div>
					</fieldset>
				</div>
			</div>
			<div class="row">
				<div class="table-responsive">
					<div class="col-sm-12 mb-2">
						<input type="hidden" class="form-control" name="roleid"
							id="roleId"
							value="${roleDescription.serviceResponse.role.orgRoleId}"> <select
							class="menuids" id="dropdown" style="display:none">
							<c:forEach items="${roleAuthDescription}" var="roleAuth">
								<option value="${roleAuth.id}">${roleAuth.id}</option>
							</c:forEach>
						</select>
						<table class="table tree table-bordered table-striped table-hover">
							<thead id="mainhead">
								<tr>
									<th>Select</th>
									<th>Menu/Module</th>
									<th>Add/Send</th>
									<th>Edit/Resubmit</th>
									<th>View</th>
									<th>Approve/Sent for re-vision</th>
									<th>Reject</th>
								</tr>
							</thead>
						</table>


					</div>
				</div>
			</div>
			<div class="text-right mb-1">
				<button class="btn btn-sm" type="button" name="button-1544427006625"
					id="button-1544427006625">Reset</button>
				<button class="btn btn-sm btn-primary" type="button"
					name="button-1544427004834" id="button-1544427004834"
					onclick="addauthorization();">Submit</button>

			</div>
		</form>

	</div>

</div>











<%-- <form id="roleauthorization">
		<div class="rendered-form">
			<div class="fb-select form-group field-select-1544426403261">
				<label for="select-1544426403261" class="fb-select-label">Level</label>
				<select class="form-control" id="level">
					<option value="0">Select Level</option>
					<option
						value="${roleDescription.serviceResponse.role.levelId.masterId}"
						selected="true" id="select-1544426406205-0">${roleDescription.serviceResponse.role.levelId.masterName}</option>
				</select>
			</div>
			<div class="fb-select form-group field-select-1544426406205">
				<label for="select-1544426406205" class="fb-select-label">Department</label>
				<select class="form-control" name="select-1544426406205"
					id="select-1544426406205">
					<option value="option-1" selected="true"
						id="select-1544426406205-0">Option 1</option>

					<option
						value="${roleDescription.serviceResponse.role.deparmentId.masterId}"
						selected="true" id="select-1544426406205-0">${roleDescription.serviceResponse.role.deparmentId.masterName}</option>

				</select>
			</div>
			<div class="fb-text form-group field-text-1544426409365">
				<label for="text-1544426409365" class="fb-text-label">Role
					Name</label><input type="text" class="form-control"
					name="text-1544426409365" id="text-1544426409365"
					value="${roleDescription.serviceResponse.role.roleName}">
			</div>
			<div class="fb-text form-group field-text-1544426409365"
				style="display: none">
				<label for="roleid" class="fb-text-label">RoleID </label><input
					type="text" class="form-control" name="roleid" id="roleId"
					value="${roleDescription.serviceResponse.role.roleId}">
			</div>
		</div> --%>

<%--  <c:out value="${roleDescription.serviceResponse.role.addRight}"/>   --%>


</form>

<!-- <div id="table" style="background-color: white">

		<div id="table" style="background-color: white; width:750px;">

			<table  class="table table-responsive table-hover">
				<thead>
					<tr>
						<th>Select</th>
						<th>Pillar/Menu</th>
						<th>Add</th>
						<th>Edit</th>
						<th>View</th>
						<th>Query</th>
						<th>Delete/Scrapped</th>
					</tr>
				</thead>
				<tbody>
					<tr class="clickable" data-toggle="collapse"
						data-target="#group-of-rows-1" aria-expanded="false"
						aria-controls="group-of-rows-1">
						<td><i class="fa fa-plus" aria-hidden="true"></i></td>

					</tr>
				</tbody>
				<tbody id="group-of-rows-1" class="collapse">
					<tr>
						<%-- <c:forEach items="${roleAuthDescription}" var = "roleAuth"> --%>


						<td id="menuID">1</td>
						<td>Add project</td>
						<td><input id="addpermisiion" type="checkbox"
							<c:if test="${roleAuthDescription[0].addRight == '1'}">checked="checked"</c:if> />

						</td>
						<td><input id="editpermisiion" type="checkbox"
							name="editpermisiion"
							<c:if test="${roleAuthDescription[0].editRight == '1'}">checked="checked"</c:if> />

						</td>
						<td><input id="viewpermisiion" type="checkbox"
							name="viewpermisiion"
							<c:if test="${roleAuthDescription[0].viewRight == '1'}">checked="checked"</c:if> />

						</td>
						<td><input id="querypermisiion" type="checkbox"
							name="querypermisiion"
							<c:if test="${roleAuthDescription[0].query == '1'}">checked="checked"</c:if> /></td>
						<td><input id="deletepermisiion" type="checkbox"
							name="deletepermission"
							<c:if test="${roleAuthDescription[0].deleteRight == '1'}">checked="checked"</c:if> /></td>
						<%-- </c:forEach> --%>
					</tr>

					<tr>
						<td id="menuID">2</td>
						<td>Submit proposal</td>
						<td><input id="addpermisiion" type="checkbox"
							<c:if test="${roleAuthDescription[1].addRight == '1'}">checked="checked"</c:if> />

						</td>
						<td><input id="editpermisiion" type="checkbox"
							name="editpermisiion"
							<c:if test="${roleAuthDescription[1].editRight == '1'}">checked="checked"</c:if> />

						</td>
						<td><input id="viewpermisiion" type="checkbox"
							name="viewpermisiion"
							<c:if test="${roleAuthDescription[1].viewRight == '1'}">checked="checked"</c:if> />

						</td>
						<td><input id="querypermisiion" type="checkbox"
							name="querypermisiion"
							<c:if test="${roleAuthDescription[1].query == '1'}">checked="checked"</c:if> /></td>
						<td><input id="deletepermisiion" type="checkbox"
							name="deletepermission"
							<c:if test="${roleAuthDescription[1].deleteRight == '1'}">checked="checked"</c:if> /></td>
					</tr>
					<tr>
						<td id="menuID">3</td>
						<td>Funding request</td>
						<td><input id="addpermisiion" type="checkbox"
							<c:if test="${roleAuthDescription[2].addRight == '1'}">checked="checked"</c:if> />

						</td>
						<td><input id="editpermisiion" type="checkbox"
							name="editpermisiion"
							<c:if test="${roleAuthDescription[2].editRight == '1'}">checked="checked"</c:if> />

						</td>
						<td><input id="viewpermisiion" type="checkbox"
							name="viewpermisiion"
							<c:if test="${roleAuthDescription[2].viewRight == '1'}">checked="checked"</c:if> />

						</td>
						<td><input id="querypermisiion" type="checkbox"
							name="querypermisiion"
							<c:if test="${roleAuthDescription[2].query == '1'}">checked="checked"</c:if> /></td>
						<td><input id="deletepermisiion" type="checkbox"
							name="deletepermission"
							<c:if test="${roleAuthDescription[2].deleteRight == '1'}">checked="checked"</c:if> /></td>
					</tr>
					<tr>
						<td id="menuID">4</td>
						<td>Installment updates</td>
						<td><input id="addpermisiion" type="checkbox"
							<c:if test="${roleAuthDescription[3].addRight == '1'}">checked="checked"</c:if> />

						</td>
						<td><input id="editpermisiion" type="checkbox"
							name="editpermisiion"
							<c:if test="${roleAuthDescription[3].editRight == '1'}">checked="checked"</c:if> />

						</td>
						<td><input id="viewpermisiion" type="checkbox"
							name="viewpermisiion"
							<c:if test="${roleAuthDescription[3].viewRight == '1'}">checked="checked"</c:if> />

						</td>
						<td><input id="querypermisiion" type="checkbox"
							name="querypermisiion"
							<c:if test="${roleAuthDescription[3].query == '1'}">checked="checked"</c:if> /></td>
						<td><input id="deletepermisiion" type="checkbox"
							name="deletepermission"
							<c:if test="${roleAuthDescription[3].deleteRight == '1'}">checked="checked"</c:if> /></td>
					</tr>
					<tr>
						<td id="menuID">5</td>
						<td>Milestone details</td>
						<td><input id="addpermisiion" type="checkbox"
							<c:if test="${roleAuthDescription[4].addRight == '1'}">checked="checked"</c:if> />

						</td>
						<td><input id="editpermisiion" type="checkbox"
							name="editpermisiion"
							<c:if test="${roleAuthDescription[4].editRight == '1'}">checked="checked"</c:if> />

						</td>
						<td><input id="viewpermisiion" type="checkbox"
							name="viewpermisiion"
							<c:if test="${roleAuthDescription[4].viewRight == '1'}">checked="checked"</c:if> />

						</td>
						<td><input id="querypermisiion" type="checkbox"
							name="querypermisiion"
							<c:if test="${roleAuthDescription[4].query == '1'}">checked="checked"</c:if> /></td>
						<td><input id="deletepermisiion" type="checkbox"
							name="deletepermission"
							<c:if test="${roleAuthDescription[4].deleteRight == '1'}">checked="checked"</c:if> /></td>
					</tr>
				</tbody>
				<tbody>
					<tr class="clickable" data-toggle="collapse"
						data-target="#group-of-rows-2" aria-expanded="false"
						aria-controls="group-of-rows-2">
						<td><i class="fa fa-plus" aria-hidden="true"></i></td>

					</tr>
				</tbody>
				<tbody id="group-of-rows-2" class="collapse">
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>

		</div>
		<div class="fb-button form-group field-button-1544427004834">
			<button type="button" name="button-1544427004834"
				id="button-1544427004834" onclick="addauthorization();">SUBMIT</button>
			<button type="button" name="button-1544427006625"
				id="button-1544427006625">RESET</button>


		</div>  -->

