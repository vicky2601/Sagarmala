<p>This is the Add role page<p>


<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>

<form  method="post" id="addRoleForm">
<label>Level*</label>
<select name="levelID" id="addRolelevelId">
  <option value="0">Select Level</option>
  <c:forEach
			items="${master.serviceResponse.commonMastersList.level}"
			var="level">
		    <option value="${level.masterId}">${level.masterName}</option>
	</c:forEach>
</select>
<p>&nbsp;</p>
<p>
  <label>Department*</label>
  <select name="department" id="addRoledepartmentId">
    <option value="0">Select Department</option>
     <c:forEach
			items="${master.serviceResponse.commonMastersList.department}"
			var="department">
		    <option value="${department.masterId}">${department.masterName}</option>
	</c:forEach>
  </select>
</p>
<p>&nbsp;</p>
<p>
  <label>Role Type*</label>
  <select name="roleType" id="addRoleroleTypeId">
    <option value="0">Select Role*</option>
     <c:forEach
			items="${master.serviceResponse.commonMastersList.role_type}"
			var="roleMaster">
		    <option value="${roleMaster.masterId}">${roleMaster.masterName}</option>
	</c:forEach>
  </select>
</p>
<p>&nbsp;</p>
<p>
  <label>Role Name*</label>
  <input type="text" name="roleName" id="addRoleName">
</p>
<p>&nbsp;</p>

<p>
  <label>Role Description</label>
  <textarea rows="4" cols="50" name="roleDescription" id="addRoleDescription"></textarea>
</p>
<p>&nbsp;</p>

<p>&nbsp;</p>
<p>&nbsp;</p>
<p>
  <input type="button" name="AddRoleSubmit" value="Submit" onclick="saveRoleData();"/>
  <!--   <input type="button" name="Submit2" value="Reset"> -->
  <input type="button" name="AddRoleCancel" value="Cancel" onclick="getRequest('/role-list', listPage);"/>
</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
</form>
</body>
</html>
