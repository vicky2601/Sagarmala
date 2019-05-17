<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sagarmala</title>

<!--Start code for js-->
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/login.js"></script>
<script type="text/javascript" src="/resources/js/project.js"></script>
<script type="text/javascript" src="/resources/js/request.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<!-- <script type="text/javascript" src="/resources/js/wow.js"></script> -->


<!--Start code for Stylesheet-->

<!-- <link href="/resources/css/style.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/animate.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="/resources/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" /> -->
	
</head>
<body>

<div class="card">
	<div class="card-header header-elements-inline">
		<h5 class="card-title font-weight-bold">Reset password Details</h5>
		<div class="header-elements">
			<div class="list-icons">
				<a class="list-icons-item" data-action="collapse"></a>
			</div>
		</div>
	</div>
	<div class="card-body">
		<form name="form">
		    <div class="row">
		       <div class="col-md-4" >
		           <fieldset>
						<div class="form-group row">
							<label class="col-md-6 col-form-label">Login ID </label>
							<div class="col-md-6">
								 <input type="text" class="form-control reset-password" id="userName" name="userName" value="${userDetail.userName}" disabled/>
							</div>
						</div>
					</fieldset>
		       </div>
		       
		       <div class="col-md-4" >
		           <fieldset>
						<div class="form-group row">
							<label class="col-md-6 col-form-label">Mobile Number</label>
							<div class="col-md-6">
								<input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${userDetail.mobileNumber}" disabled/>
							</div>
						</div>
					</fieldset>
		       </div>
		       <div class="col-md-4" >
		           <fieldset>
						<div class="form-group row">
							<label class="col-md-6 col-form-label">Email ID</label>
							<div class="col-md-6">
								<input type="text" class="form-control" id="email" name="email" value="${userDetail.email}" disabled/>
							</div>
						</div>
					</fieldset>
		       </div>
		       <div class="col-md-4" >
		           <fieldset>
						<div class="form-group row">
							<label class="col-md-6 col-form-label">Enter New Password<span class="compulsory-field">*</span></label>
							<div class="col-md-6">
								<input type="password" class="form-control reset-password" id="newpassword" name="newpassword"  maxlength="15" onchange="validatePassword(this.attributes['id'].value)"
					onkeypress="validatePassword(this.attributes['id'].value)"
					onkeyup="validatePassword(this.attributes['id'].value)" required />
							</div>
						</div>
					</fieldset>
		       </div>
		       <div class="col-md-4" >
		           <fieldset>
						<div class="form-group row">
							<label class="col-md-6 col-form-label">Confirm Password<span class="compulsory-field">*</span></label>
							<div class="col-md-6">
								<input type="password" class="form-control" id="confPassword" name="confPassword"  maxlength="15" onchange="validatePassword(this.attributes['id'].value)"
					onkeypress="validatePassword(this.attributes['id'].value)"
					onkeyup="validatePassword(this.attributes['id'].value)" required/>
							</div>
						</div>
					</fieldset>
		       </div>
		       <div class="col-md-4" >
		           
		       </div>
		    </div>
		    <div class="row">
		       <div class="col-md-12" >
		           <fieldset>
						<div class="text-right">
							 <input type="button" class="btn btn-primary pull-right ml-1" onclick="resetPasswordAdmin()" value="Submit" /> 
							 <input type="button" class="btn btn-grey pull-right" onclick="getRequest('/user-list', listPage);" value="Cancel" />
						</div>
					</fieldset>
		       </div>
		    </div>
		</form>
  </div>
  </div>
  


	<%-- <div class="log-area">
		<h2 class="text-center mt40">Reset password Details</h2>
		<form name="form">
			<div class="form-group">
				Login ID* &nbsp &nbsp &nbsp <input type="text"
					class="form-control reset-password" id="userName" name="userName" value="${userDetail.userName}" disabled/>
			</div>
			<div class="form-group">
				Enter New Password &nbsp &nbsp &nbsp <input type="password"
					class="form-control reset-password" id="newpassword" name="newpassword" />
			</div>
			<div class="form-group">
				Confirm Password &nbsp &nbsp &nbsp <input type="password"
					class="form-control" id="confPassword" name="confPassword" />
			</div>
			<div class="form-group">
				Mobile Number &nbsp &nbsp &nbsp <input type="text"
					class="form-control" id="phoneNumber" name="phoneNumber" value="${userDetail.mobileNumber}" disabled/>
			</div>
			<div class="form-group">
				Email ID &nbsp &nbsp &nbsp <input type="text" class="form-control" id="email" name="email" value="${userDetail.email}" disabled/>
			</div>
			 

			<input type="button" class="btn" onclick="resetPasswordAdmin()" value="Submit" />
				 &nbsp <input type="button" class="btn"	onclick="getRequest('/user-list', listPage);" value="Cancel" />
		</form>
	</div> --%>
	
	
	
	
	
</body>
<script type="text/javascript">
	$(document).ready(function() {
		var data = window.location.href;
		$("#username").val(data.split("/")[5]);
	});
</script>
