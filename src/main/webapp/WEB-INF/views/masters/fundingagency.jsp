<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
	$(document).ready(function() {
		listFundingAgencyData(0,10);
	});
	function clearName() {
		var a = document.getElementById("agencyName");
		a.value = a.defaultValue;
	};
</script>

	<div class="card">
		<div class="card-header header-elements-inline">
			<h5 class="card-title font-weight-bold">Funding Agency</h5>
			<div class="header-elements">
				<div class="list-icons">
					<a class="list-icons-item" data-action="collapse"></a>
				</div>
			</div>
		</div>

		<div class="card-body">
			<form id=" ">
				<div class="row">
					<div class="col-sm-12">
						<div class="col-md-6 float-left">
							<fieldset>
								<div class="form-group row">
									<label class="col-md-3 col-form-label">State</label>
									<div class="col-md-9">
										<select class="form-control" id="byState">
											<option value="0">Select State</option>
											<c:forEach
												items="${master.serviceResponse.commonMastersList.state}"
												var="state">
												<option value="${state.masterId}">${state.masterName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</fieldset>
						</div>
						<div class="col-md-6 float-left">
							<fieldset>
								<div class="form-group row">
									<label class="col-md-3 col-form-label">Funding
										Agency</label>
									<div class="col-md-9">
										<input class="form-control" type="text" id="byAgency" onkeydown="validateMastersName(event)"/>
									</div>
								</div>
							</fieldset>
						</div>

					</div>
				</div>
				<div class="col-sm-12">
					<div class="text-right mb-1">
						<button type="button" onclick="listFundingAgencyData(0,10)" class="btn btn-primary">
							<i class="icon-search4"></i>
						</button>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="form-group row">
						<div class="col-sm-6 "></div>
						<div id=" " class="col-sm-6">
							<button type="button" class="btn btn-sm btn-success pull-right"
								data-toggle="modal" data-target="#modal_form_horizontal">
								<i class="fa fa-plus"></i> Add New
							</button>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="table-responsive">
						<table class="table datatable-basic table-bordered table-striped" id="table_details">
							<thead>
								<tr class="bg-blue">
									<th>Sl.No.</th>
									<th>State</th>
									<th>Funding Agency</th>
									<th>Edit</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td></td>
									<td></td>
									<td><button class="btn btn-sm btn-info">
											<i class="fa fa-edit"></i>
										</button></td>
									<td><label class="switch"> <input type="checkbox"
											onclick=" " checked=""> <span class="slider round">
										</span>
									</label></td>

								</tr>
							</tbody>
						</table>
					</div>

				</div>
			</form>
		</div>
	</div>
	<!-- Horizontal form modal -->
	<div id="modal_form_horizontal" class="modal fade" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Add New FundingAgency</h5>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<form action="#" class="form-horizontal">
					<div class="modal-body">
						<div class="form-group row">
							<label class="col-form-label col-sm-3">State</label>
							<div class="col-sm-9">
								<select class="form-control" id="states">
									<option value="">Select</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.state}"
										var="state">
										<option value="${state.masterId}">${state.masterName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-form-label col-sm-3">Add Funding
								Agency<span class="compulsory-field">*</span>
							</label>
							<div class="col-sm-9">
								<input type="text" id="agencyName" placeholder=" " class="form-control" onkeydown="validateMastersName(event)">
							</div>
						</div>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-link" onclick="clearName()" data-dismiss="modal">Cancel</button>
						<button type="button" class="btn bg-primary" onclick="createFundingAgencyMaster()">Submit</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- /horizontal form modal -->