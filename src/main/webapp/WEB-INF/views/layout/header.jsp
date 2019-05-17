 
 
<base href="${context}/">
<!-- Global stylesheets --> 
<%-- <script type="text/javascript" src="${context}/resources/js/bootstrap-toggle.min.js"></script>  --%>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

   <%--  <script src="${context}/resources/js/styleSwitcher.js"></script>
    <script>
	    $(document).ready(function(){
	        $('#colorpick').styleSwitcher();
	    })
    </script> --%>
 
							<div class="navbar-brand">
								<a href="index.html" class="d-inline-block">
									<img src="${context}/resources/global_assets/images/logo.png" alt="">
								</a>
							</div>
                             <div class="d-md-none">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbar-mobile">
		<i class="icon-paragraph-justify3"></i>
	</button>
	<!-- <button class="navbar-toggler sidebar-mobile-main-toggle" type="button">
		<i class="icon-paragraph-justify3"></i>
	</button> -->
</div>
							<!-- <div class="d-xl-none">
								<button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbar-demo-dark">
									<i class="icon-menu"></i>
								</button>
							</div> -->

							<div class="navbar-collapse collapse" id="navbar-mobile">
								<ul class="navbar-nav"> 
								<li class="nav-item dropdown">
									    <a href="#" class="navbar-nav-link" aria-expanded="false">
											<i class="icon-home4 mr-2"></i>
											Dashboard
										</a>
										</li>
									<li class="nav-item dropdown"> 
										<a href="#" class="navbar-nav-link dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" aria-expanded="false">
											<i class="icon-cube3 mr-2"></i>
											Menu
										</a>

										<div class="dropdown-menu"> 
											<div class="dropdown-submenu">
												<a href="#" class="dropdown-item dropdown-toggle"><i class="icon-users"></i> User Management</a>
												<div class="dropdown-menu" data-submenu-title="User Management"> 
													<a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/user-list', listPage);">User</a>
													<a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/role-authorise-list', listPage);">Role Authorization</a>
													<a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/assignedProjectList', listPage);">Assigned Projects List</a>
													<a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/assignProject', listPage);">Assign Projects</a> 
												</div>
											</div>
											<div class="dropdown-submenu" data-submenu-title="User Management" >
												<a href="#" class="dropdown-item dropdown-toggle" id="master01" ><i class="icon-insert-template"></i> Masters</a>
												<div class="dropdown-menu"> 
													 <a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/agency-list', listPage);">Agency Master</a>
													 <a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/lineministry-list', listPage);">Central Ministry Master</a>
													 <a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/department-list', listPage);">Department Master</a>
													 <a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/fundingAgency-list', listPage);">Funding Agency Master</a>
													 <a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/locationMaster-list', listPage);">Location Master</a>
												     <a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/organizationMaster-list', listPage);">Organization Master</a>
												     <a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/roleMaster-list', listPage);">Role Master</a>
												     <a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/portTypeMaster-list', listPage);">Port Type Master</a>
												     <a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/portMaster-list', listPage);">Port Master</a>
												     <a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/maritimeBoard-list', listPage);">Maritime Board Master</a>
												     <a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/directorateOfPorts-list', listPage);">Directorate of ports Master</a>
												     <a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/spvName-list', listPage);">SPV Name Master</a>
												</div>
											</div>
											<div class="dropdown-submenu" data-submenu-title="Concept Management">
												<a href="#" class="dropdown-item dropdown-toggle"><i class="icon-copy"></i> Concept</a>
												<div class="dropdown-menu"> 
													<a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/addconcept', listPage);" id="add01">Add Concept</a>
													<a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/conceptlist', listPage);" id="view02">List All Concept.</a>													 
												</div>
												
											</div>
											<div class="dropdown-submenu" data-submenu-title="Reset Password">
												<a href="#" class="dropdown-item dropdown-toggle"><i class="icon-copy"></i> Reset Password</a>
												<div class="dropdown-menu"> 
													<a class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/resetpwd', listPage);">Reset Pwd</a>
																										 
												</div>
												
											</div>
										</div>
									</li>
								</ul>
                                <!--  <div class="navbar-nav ml-xl-auto">
                                   <select name="colorpick" id="colorpick" class="form-control">
							              <option value="defaultstyles" selected>Dark Theme</option>
							              <option value="customstyle1">Light Theme</option>
							              <option value="customstyle2">Green Theme</option>
							              <option value="customstyle3">Purple Theme</option> 
                                  </select></div> -->
                               
								<ul class="navbar-nav ml-xl-auto"> 

									<li class="nav-item dropdown dropdown-user">
										<a href="#" class="navbar-nav-link dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">
											<img src="${pageContext.request.contextPath}/resources/global_assets/images/demo/users/face11.png" class="rounded-circle mr-2" height="34" alt="">
											<span id="username"></span>
										</a>

										<div class="dropdown-menu dropdown-menu-right">
											<a href="#" class="dropdown-item" href="javascript:void(0);" onclick="getRequest('/edit_user_profile', listPage);"><i class="icon-user-plus"></i> Edit Profile
											 
											</a>
											
											
										
											<div class="dropdown-divider"></div>
											 <a href="javascript:void(0);"
						onclick="getRequest('/changePassword/0',listPage);" class="dropdown-item"><i class="icon-cog5"></i>
					Change Password</a> <a href="javascript:void(0);" onclick="logout();" class="dropdown-item"><i
					class="icon-switch2"></i> Logout</a>
											
										 
										</div>
									</li>
								</ul>
							</div>
						 
<%-- <div class="navbar-brand">
	<a href="javascript:void(0);" class="d-inline-block"> 
	<img src="${pageContext.request.contextPath}/resources/global_assets/images/logo_light.png" alt="">
	</a>
</div> --%>

<!-- <div class="d-md-none">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbar-mobile">
		<i class="icon-tree5"></i>
	</button>
	<button class="navbar-toggler sidebar-mobile-main-toggle" type="button">
		<i class="icon-paragraph-justify3"></i>
	</button>
</div> -->

<%-- <div class="collapse navbar-collapse" id="navbar-mobile">
	<ul class="navbar-nav">
		<li class="nav-item"><a href="#"
			class="navbar-nav-link sidebar-control sidebar-main-toggle d-none d-md-block">
				<i class="icon-paragraph-justify3"></i>
		</a></li>

		 
	</ul>

	<span class="badge bg-success ml-md-3 mr-md-auto">Online</span>

	<ul class="navbar-nav">
		<li class="nav-item dropdown">
			<!-- <a href="#" class="navbar-nav-link dropdown-toggle caret-0" data-toggle="dropdown"> -->
			<!-- <i class="icon-people"></i> --> <!-- <span class="d-md-none ml-2">Users</span> -->
			<!-- </a> -->

			<div
				class="dropdown-menu dropdown-menu-right dropdown-content wmin-md-300">
				<div class="dropdown-content-header">
					<span class="font-weight-semibold">Users online</span> <a href="#"
						class="text-default"><i class="icon-search4 font-size-base"></i></a>
				</div>

				<div class="dropdown-content-body dropdown-scrollable">
					<ul class="media-list">
						<li class="media">
							<div class="mr-3">
								<img src="${pageContext.request.contextPath}/resources/global_assets/images/demo/users/face18.jpg" width="36"
									height="36" class="rounded-circle" alt="">
							</div>
							<div class="media-body">
								<a href="#" class="media-title font-weight-semibold">Jordana
									Ansley</a> <span class="d-block text-muted font-size-sm">Lead
									web developer</span>
							</div>
							<div class="ml-3 align-self-center">
								<span class="badge badge-mark border-success"></span>
							</div>
						</li>

						<li class="media">
							<div class="mr-3">
								<img src="${pageContext.request.contextPath}/resources/global_assets/images/demo/users/face24.jpg" width="36"
									height="36" class="rounded-circle" alt="">
							</div>
							<div class="media-body">
								<a href="#" class="media-title font-weight-semibold">Will
									Brason</a> <span class="d-block text-muted font-size-sm">Marketing
									manager</span>
							</div>
							<div class="ml-3 align-self-center">
								<span class="badge badge-mark border-danger"></span>
							</div>
						</li>

						<li class="media">
							<div class="mr-3">
								<img src="${pageContext.request.contextPath}/resources/global_assets/images/demo/users/face17.jpg" width="36"
									height="36" class="rounded-circle" alt="">
							</div>
							<div class="media-body">
								<a href="#" class="media-title font-weight-semibold">Hanna
									Walden</a> <span class="d-block text-muted font-size-sm">Project
									manager</span>
							</div>
							<div class="ml-3 align-self-center">
								<span class="badge badge-mark border-success"></span>
							</div>
						</li>

						<li class="media">
							<div class="mr-3">
								<img src="${pageContext.request.contextPath}/resources/global_assets/images/demo/users/face19.jpg" width="36"
									height="36" class="rounded-circle" alt="">
							</div>
							<div class="media-body">
								<a href="#" class="media-title font-weight-semibold">Dori
									Laperriere</a> <span class="d-block text-muted font-size-sm">Business
									developer</span>
							</div>
							<div class="ml-3 align-self-center">
								<span class="badge badge-mark border-warning-300"></span>
							</div>
						</li>

						<li class="media">
							<div class="mr-3">
								<img src="${pageContext.request.contextPath}/resources/global_assets/images/demo/users/face16.jpg" width="36"
									height="36" class="rounded-circle" alt="">
							</div>
							<div class="media-body">
								<a href="#" class="media-title font-weight-semibold">Vanessa
									Aurelius</a> <span class="d-block text-muted font-size-sm">UX
									expert</span>
							</div>
							<div class="ml-3 align-self-center">
								<span class="badge badge-mark border-grey-400"></span>
							</div>
						</li>
					</ul>
				</div>

				<div class="dropdown-content-footer bg-light">
					<a href="#" class="text-grey mr-auto">All users</a> <a href="#"
						class="text-grey"><i class="icon-gear"></i></a>
				</div>
			</div>
		</li>

		<li class="nav-item dropdown"><a href="#"
			class="navbar-nav-link dropdown-toggle caret-0"
			data-toggle="dropdown"> <i class="icon-bubbles4"></i> <span
				class="d-md-none ml-2">Messages</span> <span
				class="badge badge-pill bg-warning-400 ml-auto ml-md-0">2</span>
		</a> --%>

			<%-- <div
				class="dropdown-menu dropdown-menu-right dropdown-content wmin-md-350">
				<div class="dropdown-content-header">
					<span class="font-weight-semibold">Messages</span> <a href="#"
						class="text-default"><i class="icon-compose"></i></a>
				</div>

				<div class="dropdown-content-body dropdown-scrollable">
					<ul class="media-list">
						<li class="media">
							<div class="mr-3 position-relative">
								<img src="${pageContext.request.contextPath}/resources/global_assets/images/demo/users/face10.jpg" width="36"
									height="36" class="rounded-circle" alt="">
							</div>

							<div class="media-body">
								<div class="media-title">
									<a href="#"> <span class="font-weight-semibold">James
											Alexander</span> <span class="text-muted float-right font-size-sm">04:58</span>
									</a>
								</div>

								<span class="text-muted">who knows, maybe that would be
									the best thing for me...</span>
							</div>
						</li>

						<li class="media">
							<div class="mr-3 position-relative">
								<img src="${pageContext.request.contextPath}/resources/global_assets/images/demo/users/face3.jpg" width="36"
									height="36" class="rounded-circle" alt="">
							</div>

							<div class="media-body">
								<div class="media-title">
									<a href="#"> <span class="font-weight-semibold">Margo
											Baker</span> <span class="text-muted float-right font-size-sm">12:16</span>
									</a>
								</div>

								<span class="text-muted">That was something he was unable
									to do because...</span>
							</div>
						</li>

						<li class="media">
							<div class="mr-3">
								<img src="${pageContext.request.contextPath}/resources/global_assets/images/demo/users/face24.jpg" width="36"
									height="36" class="rounded-circle" alt="">
							</div>
							<div class="media-body">
								<div class="media-title">
									<a href="#"> <span class="font-weight-semibold">Jeremy
											Victorino</span> <span class="text-muted float-right font-size-sm">22:48</span>
									</a>
								</div>

								<span class="text-muted">But that would be extremely
									strained and suspicious...</span>
							</div>
						</li>

						<li class="media">
							<div class="mr-3">
								<img src="${pageContext.request.contextPath}/resources/global_assets/images/demo/users/face4.jpg" width="36"
									height="36" class="rounded-circle" alt="">
							</div>
							<div class="media-body">
								<div class="media-title">
									<a href="#"> <span class="font-weight-semibold">Beatrix
											Diaz</span> <span class="text-muted float-right font-size-sm">Tue</span>
									</a>
								</div>

								<span class="text-muted">What a strenuous career it is
									that I've chosen...</span>
							</div>
						</li>

						<li class="media">
							<div class="mr-3">
								<img src="${pageContext.request.contextPath}/resources/global_assets/images/demo/users/face25.jpg" width="36"
									height="36" class="rounded-circle" alt="">
							</div>
							<div class="media-body">
								<div class="media-title">
									<a href="#"> <span class="font-weight-semibold">Lorem
											Ipsum</span> <span class="text-muted float-right font-size-sm">Mon</span>
									</a>
								</div>

								<span class="text-muted">Other travelling salesmen live a
									life of luxury...</span>
							</div>
						</li>
					</ul>
				</div>

				<div class="dropdown-content-footer justify-content-center p-0">
					<a href="#" class="bg-light text-grey w-100 py-2"
						data-popup="tooltip" title="Load more"><i
						class="icon-menu7 d-block top-0"></i></a>
				</div>
			</div> --%></li>

		<%-- <li class="nav-item dropdown dropdown-user"><a href="#"
			class="navbar-nav-link d-flex align-items-center dropdown-toggle"
			data-toggle="dropdown"> <img
				src="${pageContext.request.contextPath}/resources/global_assets/images/demo/users/face11.png"
				class="rounded-circle mr-2" height="34" alt=""> <!-- <span id="username"></span> -->
		</a>

			<div class="dropdown-menu dropdown-menu-right">
				<a href="#" class="dropdown-item"><i class="icon-user-plus"></i>
					My profile</a> <!-- <a href="#" class="dropdown-item"><i
					class="icon-coins"></i> My balance</a> <a href="#"
					class="dropdown-item"><i class="icon-comment-discussion"></i>
					Messages <span class="badge badge-pill bg-blue ml-auto">58</span></a> -->
				<div class="dropdown-divider"></div>
				<a href="javascript:void(0);"
						onclick="getRequest('/changePassword/0',listPage);" class="dropdown-item"><i class="icon-cog5"></i>
					Change Password</a> <a href="javascript:void(0);" onclick="logout();" class="dropdown-item"><i
					class="icon-switch2"></i> Logout</a>
			</div></li>
	</ul>
</div> --%>

<!--   @developer: Md Rashid Alam -->
<!--   @createdOn: 20/03/2019 -->
<!--  @purpose: For visible master menu of nodal officer.. -->
 
<script type="text/javascript">
	$(document).ready(function() {		
		var user = JSON.parse(localStorage.getItem('user_data'));
		console.log('user',user.name);
		
		/**
		 * @developer: Md Rashid Alam
		 * @createdOn: 20/03/2019
		 * @purpose: For visible master menu of nodal officer..
		 */
		$("#username").text(user.name);
		 roleId=user.role.roleId;
		console.log(roleId);
		if(roleId!=-1){ 
			$('#master01').hide();
			console.log('role hide')
			//$( "#master" ).prop( "disabled", true );
			 
		 } 
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {		
		var user = JSON.parse(localStorage.getItem('user_data'));
		$("#menuusername").append(user.name);
		  
	}); 
</script>