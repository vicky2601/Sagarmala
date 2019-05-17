<!-- <div class="wrapper">
	<div class="log-area">
		<h2>Forgot User Name</h2>
		<form id="forgetform" name="form">
			<div class="form-group">
				<input id="user_name" type="text" class="form-control"
					name="username" maxlength="254" placeholder="Enter Email Or Mobile Number" required>
			</div>
			<input type="button" class="btn" onclick="forgetUsername()"
				value="Submit" />
		</form>
	</div>
</div> -->
<style>
.navbar, .page-header.page-header-light{
   display: none; 
}

</style>
<!-- Content area -->
			<div class="content d-flex justify-content-center align-items-center">

				<!-- Password recovery form -->
				<form class="login-form"  id="forgetform" name="form">
					<div class="card mb-0">
						<div class="card-body">
							<div class="text-center mb-3">
								<i class="icon-spinner11 icon-2x text-warning border-warning border-3 rounded-round p-3 mb-3 mt-1"></i>
								<h5 class="mb-0">Forgot User Name</h5>
								<span class="d-block text-muted">We'll send you instructions in email</span>
							</div>

							<div class="form-group form-group-feedback form-group-feedback-right"> 
								<input id="user_name" type="text" class="form-control"
					name="username" maxlength="254" placeholder="Enter Email Or Mobile Number" required>
								<div class="form-control-feedback">
									<i class="icon-mail5 text-muted"></i>
								</div>
							</div> 
							<input type="button"  class="btn bg-blue btn-block" onclick="forgetUsername()"
				value="Submit" /><a href="javascript:void(0);" onclick="homeScreen();"  class="btn bg-warning btn-block"><span class="fa fa-sign-in"></span> Back</a>
							<!-- <button type="submit" class="btn bg-blue btn-block"  onclick="forgetUsername()"> Submit</button> -->
						</div>
					</div>
				</form>
				<!-- /password recovery form -->

			</div>
			<!-- /content area -->

