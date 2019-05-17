<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="${context}/">


<!--Start code for js-->
<script type="text/javascript"
	src="${context}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${context}/resources/js/login.js"></script>
<script type="text/javascript" src="${context}/resources/js/project.js"></script>
<script type="text/javascript" src="${context}/resources/js/request.js"></script>
<script type="text/javascript"
	src="${context}/resources/js/bootstrap.min.js"></script>
<!-- <script type="text/javascript" src="/resources/js/wow.js"></script> -->

<!--Start code for Stylesheet-->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="${context}/resources/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="${context}/resources/css/animate.css" rel="stylesheet"
	type="text/css" />
<link href="${context}/resources/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


</head>
<body>
	<div class="log-area">
		<h2 class="text-center mt40">Reset password</h2>
		<form name="form">

			<div class="form-group">
				<input type="text" id="useNameValue" name="username" value="${username}"  class="form-control" required
					disabled/>
			</div>
			<div class="form-group">
				<input type="password" class="form-control reset-password"
					id="newPassword" name="newPassword" maxlength="15" placeholder="Password" onchange="validatePassword(this.attributes['id'].value)"
							onkeypress="validatePassword(this.attributes['id'].value)"
							onkeyup="validatePassword(this.attributes['id'].value)" required />
			</div>
			<div class="form-group">
				<input type="password" class="form-control" id="confpassword"
					name="confpassword" maxlength="15" placeholder="Confirm Password"  onchange="validatePassword(this.attributes['id'].value)"
							onkeypress="validatePassword(this.attributes['id'].value)"
							onkeyup="validatePassword(this.attributes['id'].value)" required />
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-link" data-dismiss="modal">Cancel</button>
				<button type="button" class="btn bg-primary"
					onclick="resetUserPassword()">Submit</button>
			</div>
		</form>
	</div>
</body>
</html>