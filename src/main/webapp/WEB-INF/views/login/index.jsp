<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<link id="contextPathHolder"
	data-contextPath="${pageContext.request.contextPath}" />
<c:set var="context" value="${pageContext.request.contextPath}"
	scope="request" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sagarmala</title>

<!-- Important Link  -->
<base href="${context}/">
<!-- Global stylesheets -->
<link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
<link href="${context}/resources/global_assets/css/icons/icomoon/styles.css" rel="stylesheet" type="text/css">
<link href="${context}/resources/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${context}/resources/assets/css/bootstrap_limitless.min.css" rel="stylesheet" type="text/css">
<link href="${context}/resources/assets/css/layout.min.css" rel="stylesheet" type="text/css">
<link href="${context}/resources/assets/css/components.min.css" rel="stylesheet" type="text/css">
<link href="${context}/resources/assets/css/colors.min.css" rel="stylesheet" type="text/css">
<link href="${context}/resources/assets/css/custom.css" rel="stylesheet" type="text/css">
<!-- /global stylesheets -->
<link href="${context}/resources/css/animate.css" rel="stylesheet" type="text/css" />
<!--Extra Stylesheet-->
<!-- <link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" /> -->
<link href="${context}/resources/css/style.css" rel="stylesheet" type="text/css" />
<link href="${context}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
<link href="${context}/resources/css/jquery.sweet-modal.min.css" rel="stylesheet" type="text/css">
<link href="${context}/resources/css/bootstrap-select.css" rel="stylesheet" type="text/css" />
<!--Extra Stylesheet-->
<!-- Core JS files -->
<script src="${context}/resources/global_assets/js/main/jquery.min.js"></script>
<script	src="${context}/resources/global_assets/js/main/bootstrap.bundle.min.js"></script>
<script	src="${context}/resources/global_assets/js/plugins/loaders/blockui.min.js"></script>
<!-- /core JS files -->

<!-- Theme JS files -->
<script src="${context}/resources/global_assets/js/plugins/forms/styling/uniform.min.js"></script>
<%-- <script src="${context}/resources/assets/js/app.js"></script> --%>
<script src="${context}/resources/global_assets/js/demo_pages/login.js"></script>
<%-- <script src="${context}/resources/global_assets/js/plugins/tables/datatables/datatables.min.js"></script> --%>
<%-- <script	src="${context}/resources/global_assets/js/plugins/forms/selects/select2.min.js"></script> --%>
 
<%-- <script	src="${context}/resources/global_assets/js/demo_pages/datatables_basic.js"></script> --%>
<!-- /theme JS files -->
<script src="${context}/resources/global_assets/js/plugins/loaders/progressbar.min.js"></script>
	<script src="${context}/resources/global_assets/js/demo_pages/components_progress.js"></script>
<!-- Extra JS files -->
<script type="text/javascript" src="${context}/resources/js/bootstrap-toggle.min.js"></script>
<script type="text/javascript" src="${context}/resources/js/login.js"></script>
<script type="text/javascript" src="${context}/resources/js/project.js"></script>
<script type="text/javascript" src="${context}/resources/js/request.js"></script>
<script type="text/javascript" src="${context}/resources/js/validation.js"></script>
<script type="text/javascript" src="${context}/resources/js/jquery.sweet-modal.min.js"></script>
<script type="text/javascript" src="${context}/resources/js/bootstrap-select.min.js"></script>

<%-- <script type="text/javascript" src="${context}/resources/js/wow.js"></script> --%>
<!-- Extra JS files -->
<!-- Important Link  -->
 
</head>
<body onload="generateCaptcha();" >
	<!-- Page content -->
	<div class="page-content login-cover">
		<!-- Main content -->
		<div class="content-wrapper">
			<!-- Content area -->
			<div class="content d-flex justify-content-center align-items-center">
				<!-- Login form -->
				<form id="loginForm" class="login-form wmin-sm-500">
					<div class=" wow fadeInDown animated card mb-0" data-wow-duration="2s" style="background-color: #fff;color: #333;" >
						<!-- <ul class="nav nav-tabs nav-justified alpha-grey mb-0">
							<li class="nav-item">
							  <a href="#login-tab1" class="nav-link border-y-0 border-left-0 active" data-toggle="tab">
							     <h6 class="my-1">Sign in</h6>
							  </a>
							</li> 
						</ul> -->

						<div class="tab-content card-body">
							<div class="tab-pane show active">
								<div class="text-center mb-3">
									<img src="${context}/resources/global_assets/images/main.png" width="158"
										height="91" />
									<!-- <i class="icon-reading icon-2x text-slate-300 border-slate-300 border-3 rounded-round p-3 mb-3 mt-1"></i> -->
									<h5 class="mb-0 mt-1 font-weight-bold">Management
										Information System</h5>
									<span class="d-block font-weight-light">Ministry of
										Shipping</span>
								</div>
								<div class="form-group">
									<div
										class="form-group form-group-feedback form-group-feedback-left">
										<input class="form-control" type="text" id="user_name"
											type="text" name="username" placeholder="Username">
										<div class="form-control-feedback">
											<i class="icon-user text-muted"></i>
										</div>
										<div class="">
											<span><a href="javascript:void(0);"
												onclick="getRequestWithoutToken('/forget/forgetUsername', forgetPage);">Forgot
													Username</a></span>
										</div>
										<div class="error-msg">Please Fill The Name</div>
									</div>
								</div>

								<div class="form-group">
									<!-- <label for="exampleInputPassword1">Password</label> -->

									<div
										class="form-group form-group-feedback form-group-feedback-left">
										<input id="password" type="password" name="password"
											class="form-control"
											onchange="validatePassword(this.attributes['id'].value)"
											onkeypress="validatePassword(this.attributes['id'].value)"
											onkeyup="validatePassword(this.attributes['id'].value)">
										<div class="row mt-2">
											<div class="col-sm-1 float-left">
												<input class="form-input-styled" type="checkbox"
													onclick="showPassword()">
											</div>
											<div class="col-sm-6 float-left">
												<label class="control-label">Show Password</label>
											</div>
										</div>
										<div class="error-msg">Please Fill The Password</div>
										<div class="form-control-feedback">
											<i class="icon-lock2 text-muted"></i>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-sm-6">
											<h5 id="mainCaptcha" class="capt"></h5>
										</div>
										<div class="col-sm-6">
											<span id="refresh" onclick="generateCaptcha();"><i
												class="fa fa-refresh fa-3x" aria-hidden="true"></i></span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Captcha Code</label> <input
										type="text" class="form-control" id="txtInput"
										style="text-transform: uppercase;color: #333;background-color: #fff" />
									<div class="error-msg">Please Fill The Captcha</div>
								</div>


								<div class="form-group d-flex align-items-center">
									<div class="form-check mb-0">
										<label class="form-check-label"> <input
											type="checkbox" name="remember" class="form-input-styled"
											checked data-fouc> Remember
										</label>
									</div>
									<a href="javascript:void(0);"
										onclick="getRequestWithoutToken('/forget/forget', forgetPage);"
										class="ml-auto">Forgot password?</a>
								</div>

								<div class="form-group">
									<button type="button" onclick="signIn();"
										class="btn btn-primary btn-block" id="spinner-dark-2">Sign in</button>
										 
								</div>
								<!-- <span class="form-text text-center text-muted">By continuing, you're confirming that you've read our <a href="#">Terms &amp; Conditions</a> and <a href="#">Cookie Policy</a></span> -->
							</div>
						</div>
					</div>
				</form>

				<!-- /login form -->

			</div>
			<!-- /content area -->

		</div>
		<!-- /main content -->

	</div>
	<!-- /page content -->

</body>
<!-- <body class="half-image" onload="generateCaptcha();">
	<div class="hero-image">
		<div class="hero-text"></div>
	</div>

	<div class="container">
		<div class="row ">
			<div
				class="col-md-4 col-xs-offset-4 col-sm-6 col-xs-12 wow fadeInDown animated cpais-heading "
				data-wow-duration="2s">
				<h1>Login</h1>
				<form id="loginForm" class="cpais-login ">
					<div class="form-group">
						<label for="exampleInputEmail1">User Name</label> <input
							type="text" id="user_name" type="text" name="username"
							class="form-control">
						<div class="">
							<span><a
								href="javascript:void(0);"
								onclick="getRequestWithoutToken('/forget/forgetUsername', forgetPage);">Forgot
									Username</a></span>
						</div>

						<div class="error-msg">Please Fill The Name</div>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label> <input
							id="password" type="password" name="password"
							class="form-control"
							onchange="validatePassword(this.attributes['id'].value)"
							onkeypress="validatePassword(this.attributes['id'].value)"
							onkeyup="validatePassword(this.attributes['id'].value)">
						<input type="checkbox" onclick="showPassword()">Show
						Password
						<div class="error-msg">Please Fill The Password</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-6">
								<h5 id="mainCaptcha" class="capt"></h5>
							</div>
							<div class="col-sm-6">
								<span id="refresh" onclick="generateCaptcha();"><i
									class="fa fa-refresh fa-3x" aria-hidden="true"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Captcha Code</label> <input
							type="text" class="form-control" id="txtInput"
							style="text-transform: uppercase">
						<div class="error-msg">Please Fill The Captcha</div>
					</div>
					<button type="button" onclick="signIn();" class="btn btn-default">Submit</button>

					<div class="forget-i">
						<span class="forget-icon"><i class="forget-icon"></i><a
							href="javascript:void(0);"
							onclick="getRequestWithoutToken('/forget/forget', forgetPage);">Forgot
								Password</a></span>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="myfooter">
		<p>&copy; THE SAGARMALA PROJECT</p>
	</div>
</body> -->

<script>
	//$('.datatable-basic').DataTable();
	
</script>
</html>