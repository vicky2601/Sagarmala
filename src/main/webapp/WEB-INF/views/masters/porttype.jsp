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
			<h5 class="card-title font-weight-bold">Port Type</h5>
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
								<label class="col-md-3 col-form-label">Port Type</label>
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
						<button type="button" class="btn btn-sm btn-success pull-right" data-toggle="modal" data-target="#porttypeaddbtn">
							<i class="fa fa-plus"></i> Add New
						</button>
					</div>
				</div>
			</div>
			<div class="col-sm-12 card-box">
				<table class="table datatable-responsive table-bordered">
					<thead>
						<tr>
							<th>Port Type</th>
							<th class="text-center" width="220px">Edit</th>
							<th class="text-center" width="220px">Status</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td></td>
							<td align="center">
								<a class="btn btn-warning btn-sm" href="javascript:void(0);" data-toggle="modal" data-target="#porttypeeditbtn"><i class="fa fa-edit"></i></a>
							</td>
							<td align="center">
								<label class="switch"> <input type="checkbox"><span class="slider round"> </span> </label>
							</td>
						</tr>
						<tr>
							<td></td>
							<td align="center">
								<a class="btn btn-warning btn-sm" href="javascript:void(0);" data-toggle="modal" data-target="#porttypeeditbtn"><i class="fa fa-edit"></i></a>
							</td>
							<td align="center">
								<label class="switch"> <input type="checkbox" checked=""><span class="slider round"> </span> </label>
							</td>
						</tr>
					</tbody>
				</table>

			</div>

		</div>
	</div>

	<div id="porttypeaddbtn" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Add new</h5>
					<button type="button" class="bootbox-close-button close"
						data-dismiss="modal" aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<div class="form-group row">
							
								<label class="col-md-3 col-form-label">Add Port Type<span class="compulsory-field">*</span></label>
								<div class="col-md-9">
									<input type="text"  placeholder=" " class="form-control">
								</div>
						
					</div>
				</div>
				<div class="modal-footer">
					<button type="button"  class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary">Submit</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="porttypeeditbtn" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Edit</h5>
					<button type="button" class="bootbox-close-button close"
						data-dismiss="modal" aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<div class="form-group row">
							
								<label class="col-md-3 col-form-label">Edit Port Type<span class="compulsory-field">*</span></label>
								<div class="col-md-9">
									<input type="text" class="form-control">
								</div>
						
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary">Submit</button>
				</div>
			</div>
		</div>
	</div>




</body>
</html>