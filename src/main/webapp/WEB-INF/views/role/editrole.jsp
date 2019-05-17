<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div class="col-sm-12">
		<div class="form-group">
			<form method="post" id="addRoleForm">
				<input type="hidden" id="roleId" name="roleId"
					value="${roleDescription.serviceResponse.role.roleId}" /> 
					<input type="hidden" id="isActive" name="isActive"
					value="${roleDescription.serviceResponse.role.isActiv}" /> 
					<label>Level*</label>
				<select name="levelID" id="addRolelevelId">
					<option value="0">Select Level</option>
					<c:forEach
						items="${master.serviceResponse.commonMastersList.level}"
						var="level">
						<option value="${level.masterId}"
							${level.masterId == roleDescription.serviceResponse.role.levelId.masterId ? 'selected' : ''}>${level.masterName}</option>
					</c:forEach>
				</select>
				<p>&nbsp;</p>
				<p>
					<label>Department*</label> <select name="department"
						id="addRoledepartmentId">
						<option value="0">Select Department</option>
						<c:forEach
							items="${master.serviceResponse.commonMastersList.department}"
							var="department">
							<option value="${department.masterId}"
								${department.masterId == roleDescription.serviceResponse.role.deparmentId.masterId ? 'selected' : ''}>${department.masterName}</option>
						</c:forEach>
					</select>
				</p>
				<p>&nbsp;</p>
				<p>
					<label>Role Type*</label> <select name="roleType"
						id="addRoleroleTypeId">
						<option value="0">Select Role</option>
						<c:forEach
							items="${master.serviceResponse.commonMastersList.role_type}"
							var="roleMaster">
							<option value="${roleMaster.masterId}"
								${roleMaster.masterId == roleDescription.serviceResponse.role.roleTpyeId.masterId ? 'selected' : ''}>${roleMaster.masterName}</option>
						</c:forEach>
					</select>
				</p>
				<p>&nbsp;</p>
				<p>
					<label>Role Name*</label> <input type="text" name="roleName"
						id="addRoleName"
						value="${roleDescription.serviceResponse.role.roleName}">
				</p>
				<p>&nbsp;</p>

				<p>
					<label>Role Description</label>
					<textarea rows="4" cols="50" name="roleDescription"
						id="addRoleDescription">${roleDescription.serviceResponse.role.roleDescription}</textarea>
				</p>
				<p>&nbsp;</p>

				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<p>
					<input type="button" name="AddRoleSubmit" value="Submit"
						onclick="saveRoleData();" />
					<!--   <input type="button" name="Submit2" value="Reset"> -->
					<input type="button" name="AddRoleCancel" value="Cancel"
						onclick="getRequest('/role-list', listPage);" />
				</p>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
			</form>
</body>
</html>
