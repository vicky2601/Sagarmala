<!-- Sidebar mobile toggler -->
<!-- <div class="sidebar-mobile-toggler text-center">
	<a href="#" class="sidebar-mobile-main-toggle"> <i
		class="icon-arrow-left8"></i>
	</a> Navigation <a href="#" class="sidebar-mobile-expand"> <i
		class="icon-screen-full"></i> <i class="icon-screen-normal"></i>
	</a>
</div> -->
<!-- /sidebar mobile toggler -->


<!-- Sidebar content -->
<!-- <div class="sidebar-content">

	User menu
	<div class="sidebar-user">
		<div class="card-body">
			<div class="media">
				<div class="mr-3">
					<a href="#"><img
						src="global_assets/images/demo/users/face11.png" width="38"
						height="38" class="rounded-circle" alt=""></a>
				</div>

				<div class="media-body">
					<div class="media-title font-weight-semibold">Admin</div>
					<div class="font-size-xs opacity-50">
						<i class="icon-pin font-size-sm"></i>
					</div>
				</div>

				<div class="ml-3 align-self-center">
					<a href="#" class="text-white"><i class="icon-cog3"></i></a>
				</div>
			</div>
		</div>
	</div> -->
<!-- /user menu -->


<!-- Main navigation -->
<!-- <div class="card card-sidebar-mobile">
		<ul class="nav nav-sidebar" data-nav-type="accordion">
			Main
			<li class="nav-item-header"><div
					class="text-uppercase font-size-xs line-height-xs">Main</div> <i
				class="icon-menu" title="Main"></i></li>
			<li class="nav-item"><a href="index.html"
				class="nav-link active"> <i class="icon-home4"></i> <span>
						Dashboard </span>
			</a></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">User Management<span
					class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="javascript:void(0);"
						onclick="getRequest('/user-list', listPage);">User</a></li>
					<li><a href="javascript:void(0);"
						onclick="getRequest('/role-list', listPage);">Role</a></li>
					<li><a href="javascript:void(0);"
						onclick="getRequest('/role-authorise-list', listPage);">Role
							Authorization</a></li>
					<li><a href="javascript:void(0);"
						onclick="getRequest('/changePassword',listPage);">Change
							password</a></li>

				</ul></li>
		</ul>
	</div> -->
<!-- /main navigation -->


<!-- /sidebar content -->
<!-- Main sidebar -->


	<!-- Sidebar mobile toggler -->
	<div class="sidebar-mobile-toggler text-center">
		<a href="#" class="sidebar-mobile-main-toggle"> <i
			class="icon-arrow-left8"></i>
		</a> Navigation <a href="#" class="sidebar-mobile-expand"> <i
			class="icon-screen-full"></i> <i class="icon-screen-normal"></i>
		</a>
	</div>
	<!-- /sidebar mobile toggler -->


	<!-- Sidebar content -->
	<div class="sidebar-content">

		<!-- User menu -->
		<div class="sidebar-user">
			<div class="card-body">
				<div class="media">
					<div class="mr-3">
						<a href="#"><img
							src="${pageContext.request.contextPath}/resources/global_assets/images/demo/users/face11.png"
							width="38" height="38" class="rounded-circle" alt=""></a>
					</div>

					<div class="media-body">
						<div class="media-title font-weight-semibold" id="menuusername"></div>
						<div class="font-size-xs opacity-50">
							<i class="icon-pin font-size-sm"></i>
						</div>
					</div>

					<div class="ml-3 align-self-center">
						<a href="#" class="text-white"><i class="icon-cog3"></i></a>
					</div>
				</div>
			</div>
		</div>
		<!-- /user menu -->


		<!-- Main navigation -->
		<div class="card card-sidebar-mobile">
			<ul class="nav nav-sidebar" data-nav-type="accordion">
				<!-- Main -->
				<li class="nav-item-header">
				  <div class="text-uppercase font-size-xs line-height-xs">Main</div> 
				     <i class="icon-menu" title="Main"></i>
				</li>
				<li class="nav-item">
				   <a href="index.html" class="nav-link"> <i class="icon-home4"></i><span>Dashboard </span></a>
				</li>
				<li class="nav-item nav-item-submenu">
				   <a onclick="" href="#" class="nav-link"><i class="icon-users"></i><span>User Management</span></a>
				   <ul class="nav nav-group-sub" data-submenu-title="User Management">
						<li class="nav-item"><a class="nav-link" href="javascript:void(0);" onclick="getRequest('/user-list', listPage);">User</a></li>
						<li class="nav-item"> <a class="nav-link" href="javascript:void(0);" onclick="getRequest('/role-authorise-list', listPage);">Role Authorization</a></li>
						<!-- <li  class="nav-item"><a class="nav-link" href="javascript:void(0);" onclick="getRequest('/adduserdynamically', listPage);">Add User Dynamically</a></li> -->
						<li  class="nav-item"><a class="nav-link" href="javascript:void(0);" onclick="getRequest('/assignedProjectList', listPage);">Assigned Projects List</a>
						<li  class="nav-item"><a class="nav-link" href="javascript:void(0);" onclick="getRequest('/assignProject', listPage);">Assign Projects</a></li>
					</ul>
				</li>
				<li class="nav-item nav-item-submenu">
				   <a onclick="" href="#" class="nav-link"><i class="icon-insert-template"></i><span>Masters</span></a>
				   <ul class="nav nav-group-sub" data-submenu-title="User Management">
						<li class="nav-item"><a class="nav-link" href="javascript:void(0);" onclick="getRequest('/agency-list', listPage);">Agency</a></li>
						<li class="nav-item"> <a class="nav-link" href="javascript:void(0);" onclick="getRequest('/lineministry-list', listPage);">LineMinistry</a></li>
						<li  class="nav-item"><a class="nav-link" href="javascript:void(0);" onclick="getRequest('/department-list', listPage);">Department</a></li> 
						<li  class="nav-item"><a class="nav-link" href="javascript:void(0);" onclick="getRequest('/fundingAgency-list', listPage);">Funding Agency</a></li> 
						<li  class="nav-item"><a class="nav-link" href="javascript:void(0);" onclick="getRequest('/locationMaster-list', listPage);">Location Master</a></li> 
					</ul>
				</li>
				<li class="nav-item nav-item-submenu">
				   <a onclick="" href="#" class="nav-link"><i class="icon-copy"></i><span>Concept</span></a>
				   <ul class="nav nav-group-sub" data-submenu-title="Concept Management">
						<li class="nav-item"><a class="nav-link" href="javascript:void(0);" onclick="getRequest('/addconcept', listPage);">Add Concept</a></li>
					</ul>
				</li>
				<!-- <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">User Management<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="javascript:void(0);"
							onclick="getRequest('/user-list', listPage);">User</a></li>
						<li><a href="javascript:void(0);"
							onclick="getRequest('/role-list', listPage);">Role</a></li>
						<li><a href="javascript:void(0);"
							onclick="getRequest('/role-authorise-list', listPage);">Role
								Authorization</a></li>
								<li><a href="javascript:void(0);"
							onclick="getRequest('/adduserdynamically', listPage);">Add user dynamically</a></li>
						<li><a href="javascript:void(0);"
						onclick="getRequest('/changePassword',listPage);">Change
							password</a></li>

					</ul></li> -->
			</ul>
		</div>
		<!-- /main navigation -->

	</div>
	<!-- /sidebar content -->
 
<!-- /main sidebar -->

<script type="text/javascript">
	$(document).ready(function() {		
		var user = JSON.parse(localStorage.getItem('user_data'));
		$("#menuusername").append(user.name);
		  //App.initBeforeLoad();
		   // App.initCore();
		   // App.initAfterLoad();
	}); 
</script>

<!--  <script src="resources/js/app.js"></script> -->


