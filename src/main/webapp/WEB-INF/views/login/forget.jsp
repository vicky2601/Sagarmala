<!-- <div class="wrapper">
	<div class="log-area">
		<h2>Forgot Password</h2>
		<form id="forgetform" name="form">
			<div class="form-group">
				<input id="user_name" type="text" class="form-control"
					name="username" maxlength="254" placeholder="Username" required>
			</div>
			<input type="button" class="btn" onclick="forgetRequest()"
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
				<form id="forgetform" name="form">
					<div class="card mb-0">
						<div class="card-body">
							<div class="text-center mb-3">
								<i class="icon-spinner11 icon-2x text-warning border-warning border-3 rounded-round p-3 mb-3 mt-1"></i>
								<h5 class="mb-0">Forgot Password</h5>
								<span class="d-block text-muted">We'll send you instructions</span>
							</div>

							<div class="form-group form-group-feedback form-group-feedback-right"> 
								 <input id="user_name" type="text" class="form-control"
					name="username" maxlength="254" placeholder="Username" required>
								<div class="form-control-feedback">
									<i class="icon-mail5 text-muted"></i>
								</div>
							</div> 
							<input type="button"  class="btn bg-blue btn-block" onclick="forgetRequest()"
				value="Submit" />
				<a href="javascript:void(0);" onclick="homeScreen();"  class="btn bg-warning btn-block"><span class="fa fa-sign-in"></span> Back</a>
							<!-- <button type="submit" class="btn bg-blue btn-block"  onclick="forgetUsername()"> Submit</button> -->
						</div>
					</div>
				</form>
				<!-- /password recovery form -->

			</div>
			<!-- /content area -->

