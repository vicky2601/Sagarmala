<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<link id="contextPathHolder"
	data-contextPath="${pageContext.request.contextPath}" />
<c:set var="context" value="${pageContext.request.contextPath}" scope="request" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title></title>
 <!-- <link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" /> --> 
 <!-- Global stylesheets -->

	<link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
	<link href="${context}/resources/css/bootstrap-datepicker3.min.css" /> 
	<%-- <link href="${pageContext.request.contextPath}/resources/global_assets/css/icons/icomoon/styles.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/global_assets/css/icons/fontawesome/styles.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap_limitless.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/assets/css/layout.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/assets/css/components.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/assets/css/colors.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/assets/css/custom.css" rel="stylesheet" type="text/css"> --%>
	<!-- /global stylesheets -->
	
	<!-- ============ SWEET ALERT CSS ============ -->
	<link href="resources/css/sweetalert.css" rel="stylesheet" />
	

	<!-- Core JS files -->
	
	<!--  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>  -->
	<%-- <script src="${pageContext.request.contextPath}/resources/global_assets/js/main/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/global_assets/js/plugins/loaders/blockui.min.js"></script> --%>
	<!-- /core JS files -->
    <!-- Theme JS files -->
	 
	<%-- <script src="${pageContext.request.contextPath}/resources/assets/js/app.js"></script> --%>
	
	<script src="${context}/resources/global_assets/js/plugins/buttons/hover_dropdown.min.js"></script>
	<script src="${context}/resources/global_assets/js/plugins/ui/prism.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/global_assets/js/demo_pages/dashboard.js"></script>
   <script src="${pageContext.request.contextPath}/resources/global_assets/js/demo_pages/datatables_responsive.js"></script>
	<script src="${pageContext.request.contextPath}/resources/global_assets/js/plugins/tables/datatables/datatables.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/global_assets/js/plugins/tables/datatables/extensions/responsive.min.js"></script> 
 <script src="${pageContext.request.contextPath}/resources/js/concept/concept.js"></script> 
 <!-- this has to be removed -->
 <script type="text/javascript" src="${pageContext.request.contextPath}resources/js/concept/conceptlist.js"></script>
  <!-- this has to be removed -->
	<script src="${pageContext.request.contextPath}/resources/js/masters/master.js"></script>
		<script src="${context}/resources/js/concept/jquery-ui.min.js"></script>
		<script src="${context}/resources/global_assets/js/demo_pages/form_select2.js"></script> 
		<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
		<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.full.js"></script>
		<%--  <script src="${context}/resources/js/concept/select2multiselect.js"></script>  --%>
       <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.5/js/select2.min.js"></script> --> 
 		<script src="${context}/resources/global_assets/js/demo_pages/form_inputs.js"></script>
	<%-- <script src="${context}/resources/global_assets/js/plugins/forms/selects/select2.min.js"></script>  --%>
	 <!-- Table tree View -->
	 <script type="text/javascript" src="resources/js/jquery.treegrid.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/app.js"></script>
	 <!-- Table tree View -->
	 	
	 <!-- ============ SWEET ALERT JS ============ -->
	<script src="resources/js/sweetalert.js"></script>						
	 
	
	 
	<!-- /theme JS files -->
	 
</head>
<body style="padding-top:3.12503rem">
	<!-- Main navbar -->
	<div class="navbar navbar-expand-md navbar-dark bg-indigo-400 navbar-component mb-0 fixed-top">
		<tiles:insertAttribute name="header" />
	</div>
	<!-- /main navbar -->

	<!-- Page content -->
	<div class="page-content">

		<!-- Main sidebar -->
		<%-- <div class="sidebar sidebar-dark sidebar-main sidebar-expand-md">
                 
			<tiles:insertAttribute name="menu" />

		</div> --%>
		<!-- /main sidebar -->


		<!-- Main content -->
		<div class="content-wrapper">

			<!-- Page header -->
			<div class="page-header page-header-light">
				<div class="page-header-content header-elements-md-inline">
					<!-- <div class="page-title d-flex">
						<h4>
							<i class="icon-arrow-left52 mr-2"></i> <span
								class="font-weight-semibold">Home</span> - Dashboard
						</h4>
						<a href="#" class="header-elements-toggle text-default d-md-none"><i
							class="icon-more"></i></a>
					</div> -->
					<!-- <div class="header-elements d-none"> -->
					<!-- <div class="d-flex justify-content-center"> -->
					<!-- <a href="#" class="btn btn-link btn-float text-default"><i class="icon-bars-alt text-primary"></i><span>Statistics</span></a> -->
					<!-- <a href="#" class="btn btn-link btn-float text-default"><i class="icon-calculator text-primary"></i> <span>Invoices</span></a> -->
					<!-- <a href="#" class="btn btn-link btn-float text-default"><i class="icon-calendar5 text-primary"></i> <span>Schedule</span></a> -->
					<!-- </div> -->
					<!-- </div> --><!-- onclick="getRequest('/loginSuccess', loginSuccessfully);" -->
				</div>

				<div class="breadcrumb-line breadcrumb-line-light header-elements-md-inline">
					<div class="d-flex">
						<div class="breadcrumb">
							<a href="javascript:void(0);"								
								class="breadcrumb-item"><i class="icon-home2 mr-2"></i> Home</a>
							<span class="breadcrumb-item active"> </span>
						</div>

						<a href="#" class="header-elements-toggle text-default d-md-none"><i
							class="icon-more"></i></a>
					</div>

					<div class="header-elements d-none">
						<!-- <div class="breadcrumb justify-content-center">
							<a href="#" class="breadcrumb-elements-item"> <i
								class="icon-comment-discussion mr-2"></i> Support
							</a>

							<div class="breadcrumb-elements-item dropdown p-0">
								<a href="#" class="breadcrumb-elements-item dropdown-toggle"
									data-toggle="dropdown"> <i class="icon-gear mr-2"></i>
									Settings
								</a>

								<div class="dropdown-menu dropdown-menu-right">
									<a href="#" class="dropdown-item"><i class="icon-user-lock"></i>
										Account security</a> <a href="#" class="dropdown-item"><i
										class="icon-statistics"></i> Analytics</a> <a href="#"
										class="dropdown-item"><i class="icon-accessibility"></i>
										Accessibility</a>
									<div class="dropdown-divider"></div>
									<a href="#" class="dropdown-item"><i class="icon-gear"></i>
										All settings</a>
								</div>
							</div>
						</div> -->
					</div>
				</div>
			</div>
			<!-- /page header --> 
			<!-- Content area -->
			<div class="content">
				<tiles:insertAttribute name="body" />
			</div>

			<!-- /content area -->


			<!-- Footer -->
			<div class="navbar navbar-expand-lg navbar-light">
				<tiles:insertAttribute name="footer" />
			</div>
			<!-- /footer -->

		</div>
		<!-- /main content -->

	</div>
	<!-- /page content -->
	
 
</body>
<script type="text/javascript"> 
		    App.initBeforeLoad();
		    App.initCore();
		    App.initAfterLoad(); 
		    Select2Selects.init();
		    Progress.init();
		    InputsBasic.init();
		    //DatatableColumnVisibility.init();
		    //DatatableFixedColumns.init();
		    //DatatableBasic.init();
		   
</script> 


</html>