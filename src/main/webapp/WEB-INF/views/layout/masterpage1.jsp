<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="context" value="${pageContext.request.contextPath}" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="${context}/">
<title><tiles:insertAttribute name="title" /></title>

<!--Start code for js-->
<script type="text/javascript" src="${context}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${context}/resources/js/login.js"></script>
<script type="text/javascript" src="${context}/resources/js/project.js"></script>
<script type="text/javascript" src="${context}/resources/js/request.js"></script>
<script type="text/javascript" src="${context}/resources/js/bootstrap.min.js"></script>
<!-- <script type="text/javascript" src="/resources/js/wow.js"></script> -->

<!--Start code for Stylesheet-->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="${context}/resources/css/style.css" rel="stylesheet" type="text/css" />
<link href="${context}/resources/css/animate.css" rel="stylesheet" type="text/css" />
<link href="${context}/resources/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<style type="text/css">
.affix {
	top: 0;
	width: 100%;
	z-index: 9999 !important;
}

.navbar {
	margin-bottom: 0px;
}

.affix ~ .container-fluid {
	position: relative;
	top: 50px;
}
</style>

</head>
<body>

</body>
</html>