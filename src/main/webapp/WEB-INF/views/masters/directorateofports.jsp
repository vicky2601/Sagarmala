<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	   body{display: initial;}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		listdirectorateOfPortsData(0,10);
	});
	function clearName() {
		/* $("#agencyName").on('focusout', function() {
		    this.value = '';
		});  */
		var a = document.getElementById("directorateOfPorts");
		a.value = a.defaultValue;
	};
</script>

    <div class="row">
	 
	   <div class="col-sm-12">
	    <div class="alert alert-success alert-dismissible">
         <button type="button" class="close" data-dismiss="alert">&times;</button>
         <strong class='text'></strong> 
	      
	    </div>
	   </div>
		  </div> 
	<div class="card">
		<div class="card-header header-elements-inline">
			<h5 class="card-title font-weight-bold">Directorate of ports</h5>
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
								<select class="form-control" id="byState01">
						         <option value="0">Select State</option>
						         <c:forEach
										items="${master.serviceResponse.commonMastersList.state}"
										var="state">
										<option value="${state.masterId}">${state.masterName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Directorate of ports</label>
							<div class="col-md-9">
								<input type="text" class="form-control" onkeydown="validateMastersName(event)" id="bydirectorateOfPorts">
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
							data-toggle="modal" data-target="#dportaddbtn">
							<i class="fa fa-plus"></i> Add New
						</button>
					</div>
				</div>
			</div>
			<div class="col-sm-12 card-box">
				<table class="table datatable-responsive table-bordered" id="table_details">
					<thead>
						<tr>
							<th>State</th>
							<th class="text-center" width="220px">Directorate of ports</th>
							<th class="text-center" width="220px">Edit</th>
							<th class="text-center" width="220px">Status</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td></td>
							<td align="center">A</td>
							<td align="center">
								<a class="btn btn-warning btn-sm" href="javascript:void(0);" data-toggle="modal" data-target="#dporteditbtn">
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
							<td align="center"></td>
							<td align="center">
								<a class="btn btn-warning btn-sm"
								href="javascript:void(0);" data-toggle="modal"
								data-target="#dporteditbtn"><i class="fa fa-edit"></i></a>
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

	<div id="dportaddbtn" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Add Directorate of ports</h5>
					<button type="button" class="bootbox-close-button close"
						data-dismiss="modal" aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<div class="form-group row">
						<label class="col-md-4 col-form-label">State<span class="compulsory-field">*</span></label>
						<div class="col-md-8">
							<select class="form-control" id="state01">
								<option value="0">Select State</option>
								<c:forEach
									items="${master.serviceResponse.commonMastersList.state}"
									var="state">
									<option value="${state.masterId}">${state.masterName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-4 col-form-label">Directorate of ports<span class="compulsory-field">*</span></label>
						<div class="col-md-8">
							<input type="text"  class="form-control" placeholder=" " id="directorateOfPorts" onkeypress="validateMastersName(event)"
							 ondrag="return false"
									ondrop="return false">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button"  class="btn btn-default" data-dismiss="modal" onclick="clearName()">Cancel</button>
					<button type="button" 
						class="btn btn-primary" onclick="createDirectorateOfPorts()">Submit</button>
				</div>
			</div>
		</div>
	</div>

	<div id="dporteditbtn" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Update Directorate of ports</h5>
					<button type="button" class="bootbox-close-button close"
						data-dismiss="modal" aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<div class="form-group row">
						<label class="col-md-4 col-form-label">State<span class="compulsory-field">*</span></label>
						<div class="col-md-8">
							<select class="form-control" id="state01">
						          <option value="0">Select State</option>
								<c:forEach
									items="${master.serviceResponse.commonMastersList.state}"
									var="state">
									<option value="${state.masterId}">${state.masterName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-4 col-form-label">Directorate of ports<span class="compulsory-field">*</span></label>
						<div class="col-md-8">
							<input type="text" class="form-control" placeholder=" " id="directorateOfPorts" 
							onkeypress="validateMastersName(event)"  ondrag="return false"
									ondrop="return false">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button"  class="btn btn-default" data-dismiss="modal" onclick="clearName()">Cancel</button>
					<button type="button" 
						class="btn btn-primary" onclick="createDirectorateOfPorts()">Submit</button>
				</div>
			</div>
		</div>
	</div>