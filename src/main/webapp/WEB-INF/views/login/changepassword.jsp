<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 

<!-- <div class="wrapper">
	<div class="log-area">
		<h2>Change Your Password</h2>
		<form id="forgetform" name="form">
			<div class="form-group">
				<input id="current_password" type="password" class="form-control"
					name="currentpassword" maxlength="254" placeholder="Current Password" onchange="validatePassword(this.attributes['id'].value)"
							onkeypress="validatePassword(this.attributes['id'].value)"
							onkeyup="validatePassword(this.attributes['id'].value)" required>
			</div>
			<div class="form-group">
				<input id="new_password" type="password" class="form-control"
					name="newpassword" maxlength="254" placeholder="New Password" onchange="validatePassword(this.attributes['id'].value)"
							onkeypress="validatePassword(this.attributes['id'].value)"
							onkeyup="validatePassword(this.attributes['id'].value)" required>
			</div>
			
			<div class="form-group">
				<input id="conf_password" type="password" class="form-control"
					name="confpassword" maxlength="254" placeholder="Confirm Password" onchange="validatePassword(this.attributes['id'].value)"
							onkeypress="validatePassword(this.attributes['id'].value)"
							onkeyup="validatePassword(this.attributes['id'].value)" required>
			</div>
			<input type="button" class="btn" onclick="changePassword()" value="Submit" />
		</form>
	</div>
</div> -->

 <!-- Content area -->
			<div class="content d-flex justify-content-center align-items-center">

				<!-- Password recovery form -->
				<form id="forgetform" name="form" class="login-form">
					<div class="card mb-0">
						<div class="card-body">
							<div class="text-center mb-3">
								<i class="icon-spinner11 icon-2x text-warning border-warning border-3 rounded-round p-3 mb-3 mt-1"></i>
								<h5 class="mb-0">Change Your Password</h5>
								<span class="d-block text-muted">We'll send you instructions</span>
							</div>

							<div class="form-group form-group-feedback form-group-feedback-right"> 
								<input id="current_password" type="password" class="form-control"
					name="currentpassword" maxlength="15" placeholder="Current Password" onchange="validatePassword(this.attributes['id'].value)"
							onkeypress="validatePassword(this.attributes['id'].value)"
							onkeyup="validatePassword(this.attributes['id'].value)" required>
								<div class="form-control-feedback">
									 
								</div>
							</div>
							<div class="form-group form-group-feedback form-group-feedback-right"> 
								 <input id="new_password" type="password" class="form-control"
					name="newpassword" maxlength="15" placeholder="New Password" onchange="validatePassword(this.attributes['id'].value)"
							onkeypress="validatePassword(this.attributes['id'].value)"
							onkeyup="validatePassword(this.attributes['id'].value)" required>
								<div class="form-control-feedback">
									 
								</div>
							</div>  
							<div class="form-group form-group-feedback form-group-feedback-right"> 
								 <input id="conf_password" type="password" class="form-control"
					name="confpassword" maxlength="15" placeholder="Confirm Password" onchange="validatePassword(this.attributes['id'].value)"
							onkeypress="validatePassword(this.attributes['id'].value)"
							onkeyup="validatePassword(this.attributes['id'].value)" required>
								<div class="form-control-feedback">
									 
								</div>
							</div> 
							<input type="button"  class="btn bg-blue btn-block" onclick="changePassword()" value="Submit" />
							 
						</div>
					</div>
				</form>
				<!-- /password recovery form -->

			</div>
			<!-- /content area -->


