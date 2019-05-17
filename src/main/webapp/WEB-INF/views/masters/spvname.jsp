<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="card">
		<div class="card-header header-elements-inline">
			<h5 class="card-title font-weight-bold">SPV Name</h5>
			<div class="header-elements">
				<div class="list-icons">
					<a class="list-icons-item" data-action="collapse"></a>
				</div>
			</div>
		</div>

		<div class="card-body">
			<div class="row">
				<div class="col-sm-12 card-box">
					<div class="col-md-6 offset-md-3 float-left">
						<fieldset>
							<div class="form-group row">
								<label class="col-md-3 col-form-label">SPV Name</label>
								<div class="col-md-9">
									<input type="text" class="form-control">
								</div>
							</div>
						</fieldset>
					</div>
					<div class="col-md-2 float-left">
						<div class="text-left">
							<button type="button" class="btn btn-primary">
								<i class="icon-search4"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 card-box">
				<div class="form-group row">
					<div class="col-sm-6 "></div>
					<div id=" " class="col-sm-6">
						<button type="button" class="btn btn-sm btn-success pull-right" data-toggle="modal" data-target="#spvnameaddbtn">
							<i class="fa fa-plus"></i> Add New
						</button>
					</div>
				</div>
			</div>
			<div class="col-sm-12 card-box">
				<table class="table datatable-responsive table-bordered">
					<thead>
						<tr>
							<th>SPV Name</th>
							<th>SPV Initials</th>
							<th class="text-center" width="220px">Edit</th>
							<th class="text-center" width="220px">Status</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td></td>
							<td></td>
							<td align="center">
								<a class="btn btn-warning btn-sm" href="javascript:void(0);" data-toggle="modal" data-target="#spvnameeditbtn"><i class="fa fa-edit"></i></a>
							</td>
							<td align="center">
								<label class="switch"> <input type="checkbox" checked=""><span class="slider round"> </span> </label>
							</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td align="center">
								<a class="btn btn-warning btn-sm" href="javascript:void(0);" data-toggle="modal" data-target="#spvnameeditbtn"><i class="fa fa-edit"></i></a>
							</td>
							<td align="center">
								<label class="switch"> <input type="checkbox"><span class="slider round"> </span> </label>
							</td>
						</tr>
					</tbody>
				</table>

			</div>

		</div>
	</div>

	<!-- ADD NEW POPUP  -->
	<div id="spvnameaddbtn" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Add new</h5>
					<button type="button" class="bootbox-close-button close"
						data-dismiss="modal" aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<div class="form-group row">

						<label class="col-md-3 col-form-label">Add SPV Name<span
							class="compulsory-field">*</span></label>
						<div class="col-md-9">
							<input type="text" placeholder=" " class="form-control">
						</div>

					</div>
					<div class="form-group row">

						<label class="col-md-3 col-form-label">SPV Initials<span
							class="compulsory-field">*</span></label>
						<div class="col-md-9">
							<input type="text" placeholder=" " class="form-control">
						</div>

					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#confirmpopup">Submit</button>
				</div>
			</div>
		</div>
	</div>

	<!-- CONFIRMATION POPUP -->
	<div id="confirmpopup" class="modal fade cnfpopup">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<p class="text-center">Are you sure you want to submit the details?</p>
				</div>
				<div class="modal-footer text-center">
					<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
					<button type="button"
						class="btn btn-primary">Yes</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- EDIT POPUP -->
	<div id="spvnameeditbtn" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Edit</h5>
					<button type="button" class="bootbox-close-button close"
						data-dismiss="modal" aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<div class="form-group row">
							
								<label class="col-md-3 col-form-label">Edit SPV Name<span class="compulsory-field">*</span></label>
								<div class="col-md-9">
									<input type="text" class="form-control">
								</div>
						
					</div>
					<div class="form-group row">
							
								<label class="col-md-3 col-form-label">SPV Initials<span class="compulsory-field">*</span></label>
								<div class="col-md-9">
									<input type="text" class="form-control">
								</div>
						
					</div>
				</div>
				<div class="modal-footer">
					<button data-bb-handler="cancel" type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button data-bb-handler="confirm" type="button"
						class="btn btn-primary">Submit</button>
				</div>
			</div>
		</div>
	</div>


</body>
</html>