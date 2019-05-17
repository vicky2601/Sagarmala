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
			<h5 class="card-title font-weight-bold">Port Name</h5>
			<div class="header-elements">
				<div class="list-icons">
					<a class="list-icons-item" data-action="collapse"></a>
				</div>
			</div>
		</div>

		<div class="card-body">
			<div class="col-sm-12 card-box">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group row">
							<label class="col-md-3 col-form-label">State</label>
							<div class="col-md-9">
								<select class="form-control">
						          <option>Select State</option>
						          <option>Andaman and Nicobar Islands</option>
						          <option>Andhra Pradesh</option>
						          <option>Arunachal Pradesh</option>
						          <option>Assam</option>
						          <option>Bihar</option>
						          <option>Chandigarh</option>
						        </select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Port Name</label>
							<div class="col-md-9">
								<input type="text" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Port type</label>
							<div class="col-md-9">
								<select class="form-control">
						          <option>Major</option>
						          <option>Non-Major Govt.</option>
						          <option>Non-Major Private</option>
						          <option>Other</option>
						        </select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Maritime Board</label>
							<div class="col-md-9">
								<select class="form-control">
						          <option></option>
						          <option></option>
						        </select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Directorate of ports</label>
							<div class="col-md-9">
								<select class="form-control">
						          <option></option>
						          <option></option>
						        </select>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="text-right">
							<button type="button" class="btn btn-primary">
								<i class="icon-search4"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 card-box">
				<div class="form-group row">
					<div class="col-sm-12">
						<button type="button" class="btn btn-sm btn-success pull-right"
							data-toggle="modal" data-target="#portaddbtn">
							<i class="fa fa-plus"></i> Add New
						</button>
					</div>
				</div>
			</div>
			<div class="col-sm-12 card-box">
				<table class="table datatable-responsive table-bordered">
					<thead>
						<tr>
							<th>State</th>
							<th>Port Name</th>
							<th>Port Type</th>
							<th>Maritime Board</th>
							<th>Directorate of ports</th>
							<th class="text-center" width="170px">Edit</th>
							<th class="text-center" width="170px">Status</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td></td>
							<td>A</td>
							<td></td>
							<td></td>
							<td></td>
							<td align="center">
								<a class="btn btn-warning btn-sm" href="javascript:void(0);" data-toggle="modal" data-target="#porteditbtn">
									<i class="fa fa-edit"></i>
								</a>
							</td>
							<td align="center">
								<label class="switch">
									<input type="checkbox">
									<span class="slider round"></span>
								</label>
							</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td align="center">
								<a class="btn btn-warning btn-sm"
								href="javascript:void(0);" data-toggle="modal"
								data-target="#porteditbtn"><i class="fa fa-edit"></i></a>
							</td>
							<td align="center">
								<label class="switch">
									<input type="checkbox" checked="">
									<span class="slider round"> </span>
								</label>
							</td>
						</tr>
					</tbody>
				</table>

			</div>

		</div>
	</div>

	<div id="portaddbtn" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Add New</h5>
					<button type="button" class="bootbox-close-button close"
						data-dismiss="modal" aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<div class="form-group row">
						<label class="col-md-3 col-form-label">State<span class="compulsory-field">*</span></label>
						<div class="col-md-9">
							<select class="form-control">
						         <option>Select State</option>
						         <option>Andaman and Nicobar Islands</option>
						         <option>Andhra Pradesh</option>
						         <option>Arunachal Pradesh</option>
						         <option>Assam</option>
						         <option>Bihar</option>
						         <option>Chandigarh</option>
						    </select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Port type<span class="compulsory-field">*</span></label>
						<div class="col-md-9">
							<select class="form-control">
						         <option>Major</option>
						         <option>Non-Major Govt.</option>
						         <option>Non-Major Private</option>
						         <option>Other</option>
						    </select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Add Port Name<span class="compulsory-field">*</span></label>
						<div class="col-md-9">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group row">
						<div class="col-md-9 offset-md-3">
							<div class="form-check fr-radio form-check-inline">
								<label class="form-check-label">
									<input type="radio" class="" name="portname" checked="">
										Maritime board
								</label>
							</div>
							<div class="form-check fr-radio form-check-inline">
								<label class="form-check-label">
									<input type="radio" class="" name="portname">
										Directorate of port
								</label>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Maritime Board<span class="compulsory-field">*</span></label>
						<div class="col-md-9">
							<input type="text" class="form-control">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" type="button"
						class="btn btn-primary">Submit</button>
				</div>
			</div>
		</div>
	</div>

	<div id="porteditbtn" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Edit</h5>
					<button type="button" class="bootbox-close-button close"
						data-dismiss="modal" aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<div class="form-group row">
						<label class="col-md-3 col-form-label">State<span class="compulsory-field">*</span></label>
						<div class="col-md-9">
							<select class="form-control">
						         <option>Select State</option>
						         <option>Andaman and Nicobar Islands</option>
						         <option>Andhra Pradesh</option>
						         <option>Arunachal Pradesh</option>
						         <option>Assam</option>
						         <option>Bihar</option>
						         <option>Chandigarh</option>
						    </select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Port type<span class="compulsory-field">*</span></label>
						<div class="col-md-9">
							<select class="form-control">
						         <option>Major</option>
						         <option>Non-Major Govt.</option>
						         <option>Non-Major Private</option>
						         <option>Other</option>
						    </select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Edit Port Name<span class="compulsory-field">*</span></label>
						<div class="col-md-9">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group row">
						<div class="col-md-9 offset-md-3">
							<div class="form-check fr-radio form-check-inline">
								<label class="form-check-label">
									<input type="radio" class="" name="gender" checked="">
										Maritime board
								</label>
							</div>
							<div class="form-check fr-radio form-check-inline">
								<label class="form-check-label">
									<input type="radio" class="" name="gender">
										Directorate of port
								</label>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Maritime Board<span class="compulsory-field">*</span></label>
						<div class="col-md-9">
							<input type="text" class="form-control">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" type="button"
						class="btn btn-primary">Submit</button>
				</div>
			</div>
		</div>
	</div>
						
</body>
</html>