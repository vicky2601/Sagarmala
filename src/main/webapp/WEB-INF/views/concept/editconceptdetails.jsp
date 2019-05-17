<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style TYPE="text/css">
body {
	display: initial;
}
</style>
<%--  ${concept.serviceResponse.conceptBean.id} --%>
<%--  ${concept.serviceResponse.conceptBean} --%>
<form ID=" " ACTION="">
	<div CLASS="card">
		<div CLASS="card-header header-elements-inline">
			<h5 CLASS="card-title font-weight-bold">Edit Concepts</h5>
			<div CLASS="header-elements">
				<div CLASS="list-icons">
					<a CLASS="list-icons-item" DATA-ACTION="collapse"></a>
				</div>
			</div>
		</div>

		<div CLASS="card-body">

			<div CLASS="row">
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<input TYPE="hidden"  ID="id" name="id"  value="${concept.serviceResponse.conceptBean.id}"> <label
								FOR="projecttype" CLASS="col-md-4 col-form-label">Project
								Type</label>
							<div CLASS="col-md-8">
								<input TYPE="text" CLASS="form-control" NAME="projecttype"
									ID="projectType" DISABLED="disabled" VALUE="${concept.serviceResponse.conceptBean.projectType}">
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="select-1547635305225" CLASS="col-md-4 col-form-label">Funding
								Agencies<span CLASS="compulsory-field">*</span>
							</label>
							<div CLASS="col-md-8">
								<!-- 	<select class="form-control" name="select-1547635305225[]" multiple="multiple" id="select22" required="required" aria-required="true" disabled="disabled"> -->
								<select CLASS="js-example-basic-multiple" MULTIPLE="multiple"
									ID="selects22" DISABLED="disabled">
<%-- 									<option value="${concept.serviceResponse.conceptBean.idFundingAgencies}">${concept.serviceResponse.conceptBean.idFundingAgencies}</option> --%>
								</select>


							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="projecttype" CLASS="col-md-4 col-form-label">Project
								Name</label>
							<div CLASS="col-md-8">
								<input TYPE="text" CLASS="form-control"
									NAME="text-1547635404142" ID="projectName" value="${concept.serviceResponse.conceptBean.projectName}" disabled="disabled">
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="text-1547635418158" CLASS="col-md-4 col-form-label">Concept
								ID</label>
							<div CLASS="col-md-8">
								<input TYPE="text" CLASS="form-control" DISABLED="disabled"
									NAME="text-1547635418158" 
									VALUE="${concept.serviceResponse.conceptBean.conceptId}" ID="conceptid" >
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="select-1547635530273" CLASS="col-md-4 col-form-label">States</label>
							<div CLASS="col-md-8">
							  <input type="hidden" id="states" value="${concept.serviceResponse.conceptBean.stateId}">
							  
								<select CLASS="js-example-basic-multiple" MULTIPLE="multiple"
									ID="stateId" disabled="disabled">
<!-- 									<option VALUE="default" SELECTED="true">Select a state</option> -->
<%-- 									<c:forEach --%>
<%-- 										items="${master.serviceResponse.commonMastersList.state}" --%>
<%-- 										var="state"> --%>
<%-- 										<option VALUE="${state.masterId}">${state.masterName}</option> --%>
<%-- 									</c:forEach> --%>

								</select>
								<%--   <input type="text" class="form-control" name="text-1547635404142" id="state" value="${userDetails.state.masterName}"> --%>
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="select-1547635642895" CLASS="col-md-4 col-form-label">Port-Major/Non
								Major</label>
							<div CLASS="col-md-8">
								<select CLASS="form-control" NAME="select-1547635642895"
									ID="portType" disabled="disabled">
									<!-- <option VALUE="default" SELECTED="true"
										ID="select-1547635642895-0">Choose a port</option>
									<option VALUE="majorport" ID="select-1547635642895-1">Major-Port</option>
									<option VALUE="minorport" ID="select-1547635642895-2">Minor-Port</option> -->
									<option value="${concept.serviceResponse.conceptBean.portType}" selected="selected">${concept.serviceResponse.conceptBean.portType}</option>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="select-1547635678252" CLASS="col-md-4 col-form-label">Port
								Name</label>
							<div CLASS="col-md-8">
								<select CLASS="form-control" NAME="select-1547635678252"
									ID="portName" disabled="disabled">
									<option VALUE="default" SELECTED="true" disabled="disabled">Select a port</option>
								</select>
								<%--  <input type="text" class="form-control" name="text-1547635404142" id="port" value="${userDetails.port.portName}"> --%>
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="select-1547635692843" CLASS="col-md-4 col-form-label">Project
								Location</label>
								 <input type="hidden" id="projLocs" value="${concept.serviceResponse.conceptBean.projectLocation}">
							<div CLASS="col-md-8">
								<select CLASS="js-example-basic-multiple" MULTIPLE="multiple"
									ID="projectLocation" disabled="disabled">
									
								</select>
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="textarea-1547636380668"
								CLASS="col-md-4 col-form-label">Project Brief</label>
							<div CLASS="col-md-8">
								<textarea TYPE="textarea" MAXLENGTH="500" CLASS="form-control"
									NAME="textarea-1547636380668" ID="projectBrief" COLS="" ROWS=""  disabled="disabled">${concept.serviceResponse.conceptBean.projectBrief}</textarea>
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="textarea-1547636401692"
								CLASS="col-md-4 col-form-label">Aims and Objectives</label>
							<div CLASS="col-md-8">
								<textarea TYPE="textarea" MAXLENGTH="500" CLASS="form-control"
									NAME="textarea-1547636401692" ID="aimsAndObjectives" COLS=""
									ROWS="" disabled="disabled">${concept.serviceResponse.conceptBean.aimsAndObjectives}</textarea>
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="textarea-1547636423368"
								CLASS="col-md-4 col-form-label">Scope of work</label>
							<div CLASS="col-md-8">
								<textarea TYPE="textarea" MAXLENGTH="500" CLASS="form-control"
									NAME="textarea-1547636423368" ID="scopeOfWork" COLS="" ROWS="" disabled="disabled">${concept.serviceResponse.conceptBean.scopeOfWork}</textarea>
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="text-1547636449122" CLASS="col-md-4 col-form-label">Estimated
								Cost</label>
							<div CLASS="col-md-8">
								<input TYPE="text" CLASS="form-control"
									NAME="text-1547636449122" ID="estimatedCost" value="${concept.serviceResponse.conceptBean.estimatedCost}" disabled="disabled">
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="select-1547636484832" CLASS="col-md-4 col-form-label">Project
								Proponent</label>  
							<div CLASS="col-md-8">
								<select CLASS="form-control" NAME="select-1547636484832"
									ID="projectProponent" disabled="disabled">
									<!-- <option VALUE="option-1" SELECTED="true"
										ID="select-1547636484832-0">Option 1</option>
									<option VALUE="option-2" ID="select-1547636484832-1">Option
										2</option>
									<option VALUE="option-3" ID="select-1547636484832-2">Option
										3</option> -->
									<option value="${concept.serviceResponse.conceptBean.projectProponent}" selected="selected" disabled="disabled">${concept.serviceResponse.conceptBean.projectProponent}</option>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="date-1547636520974" CLASS="col-md-4 col-form-label">Expected
								Start Date</label>
							<div CLASS="col-md-8">
								<input TYPE="date" CLASS="form-control"
									NAME="date-1547636520974" ID="expectedStartDate" disabled="disabled">
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="date-1547636544458" CLASS="col-md-4 col-form-label">Expected
								End Date</label>
							<div CLASS="col-md-8">
								<input TYPE="date" CLASS="form-control"
									NAME="date-1547636544458" ID="expectedEndDate" value="${concept.serviceResponse.conceptBean.endDate}" disabled="disabled">
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<%-- <div CLASS="form-group row">
							<label FOR="file-1547641322808" CLASS="col-md-4 col-form-label">Upload
								Document-If Any</label>
							<div CLASS="col-md-8">
								<input TYPE="file" CLASS="form-control"
									NAME="file-1547641322808" ID="doc" value="${concept.serviceResponse.conceptBean.document}"> <input
									TYPE="hidden" ID="filepath">
							</div>
						</div> --%>
						<div CLASS="form-group row">
							<label FOR="file-1547641322808" CLASS="col-md-4 col-form-label">Download attached Document</label>
							<div CLASS="col-md-8">
								<a href="${concept.serviceResponse.conceptBean.document}" download >
 									 <input type=button id="downloadButton" value='Click here to Download'>
								</a>
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label CLASS="col-md-4 col-form-label"> </label>
							<div CLASS="col-md-8"></div>
						</div>
					</fieldset>
				</div>
			</div>
		</div>
	</div>

	<div CLASS="card" ID="add0">
		<div CLASS="card-header header-elements-inline">
			<h5 CLASS="card-title font-weight-bold">Project Nodal Officer</h5>
			<div CLASS="header-elements">
				<div CLASS="list-icons">
					<a CLASS="list-icons-item" DATA-ACTION="collapse"></a>
				</div>
			</div>
		</div>
		<div CLASS="card-body">
			<div CLASS="row">
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="select-1547641465506" CLASS="col-md-4 col-form-label">Name</label>
							<div CLASS="col-md-8">
								<select CLASS="form-control nodalOfficerName"
									NAME="select-1547641465506" ID="nodalOfficerName">
									<option VALUE="option-1" SELECTED="true"
										ID="select-1547641465506-0">Option 1</option>
									<option VALUE="option-2" ID="select-1547641465506-1">Option
										2</option>
									<option VALUE="option-3" ID="select-1547641465506-2">Option
										3</option>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="text-1547641493855" CLASS="col-md-4 col-form-label">Land
								Line Number</label>
							<div CLASS="col-md-8">
								<input TYPE="tel" CLASS="form-control nodalOfficerLandline"
									NAME="text-1547641493855" MAXLENGTH="12"
									ID="nodalOfficerLandline">
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="text-1547641613536" CLASS="col-md-4 col-form-label">Designation</label>
							<div CLASS="col-md-8">
								<input TYPE="text" CLASS="form-control nodalOfficerDesignation"
									NAME="text-1547641613536" ID="nodalOfficerDesignation">
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="text-1547641646157" CLASS="col-md-4 col-form-label">Mobile
								Number</label>
							<div CLASS="col-md-8">
								<input TYPE="text" CLASS="form-control nodalOfficerMobile"
									NAME="text-1547641646157" MAXLENGTH="12"
									ID="nodalOfficerMobile">
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="text-1547641764764" CLASS="col-md-4 col-form-label">Postal
								Address</label>
							<div CLASS="col-md-8">
								<input TYPE="text"
									CLASS="form-control nodalOfficerPostalAddress"
									NAME="text-1547641764764" ID="nodalOfficerPostalAddress">
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-6">
					<fieldset>
						<div CLASS="form-group row">
							<label FOR="text-1547641778215" CLASS="col-md-4 col-form-label">E-mail
								Address</label>
							<div CLASS="col-md-8">
								<input TYPE="email" CLASS="form-control nodalOfficerEmail"
									NAME="text-1547641778215" ID="nodalOfficerEmail">
							</div>
						</div>
					</fieldset>
				</div>
				<div CLASS="col-md-12">
					<fieldset>
						<div CLASS="col-md-12">
							<button TYPE="button" CLASS="btn btn-success pull-right minus"
								NAME="minus" ID="minus" STYLE="display: none">Remove</button>
						</div>
					</fieldset>
				</div>

			</div>

		</div>

	</div>

	<div ID="addextra0" CLASS="add1"></div>
	<div CLASS="row">
		<div CLASS="col-md-12">
			<fieldset>
				<div CLASS="col-md-12">
					<button TYPE="button" CLASS="btn btn-success pull-right"
						NAME="plus" ID="plus" ONCLICK="addRemoveExistNodalDiv();">Add</button>
				</div>
			</fieldset>
		</div>

	</div>
	
   <input type="hidden"  id="status" value="${concept.serviceResponse.conceptBean.status}">
	<div CLASS="row">
		<div CLASS="col-md-12">
			<fieldset>
				<div CLASS="col-md-12">
					<br> <br>
			   
			 	<button TYPE="button" CLASS="btn btn-primary pull-right" 
						NAME="approve" ID="approve" ONCLICK="changeStatus();" style="margin-left: 5px;">
						Approve</button>
					 <button TYPE="button" CLASS="btn btn-warning pull-right" 
						NAME="revision" ID="revision" ONCLICK="changeStatus();" style="margin-left: 5px;">
						Send for Re-vision</button>
					<button TYPE="button" CLASS="btn btn-danger pull-right"
						NAME="reject" ID="reject" ONCLICK="changeStatus();">
						Rejected</button>
						
						<button type="button" class="btn bg-primary pull-right promt-popup" data-toggle="modal" data-target="#prompt">
						Button
					</button>
					
						<!-- Modal Popup -->
					
						<div class="modal fade bootbox-prompt show" id="prompt" tabindex="-1">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">Enter Remarks</h5>
									<button type="button" class="close"
										data-dismiss="modal" aria-hidden="true">×</button>
								</div>
								<div class="modal-body">
									<div class="bootbox-body">
										<form class="bootbox-form">
											<textarea class="bootbox-input bootbox-input-text form-control"></textarea>
										</form>
									</div>
								</div>
								<div class="modal-footer">
									<button data-bb-handler="cancel" type="button"
										class="btn btn-link">Cancel</button>
									<button data-bb-handler="confirm" type="button"
										class="btn btn-primary">Submit</button>
								</div>
							</div>
						</div>
					</div>
				
					
				</div>
			</fieldset>
		</div>
		<input TYPE="hidden" VALUE="0" ID="countervar" />
	</div>
	<input type="hidden" id="funding" value="${concept.serviceResponse.conceptBean.idFundingAgencies}">
	<select NAME="ages"  id="ages" STYLE="display: none">

				<c:forEach
					items="${master.serviceResponse.commonMastersList.agency}"
					var="agency">
					<option VALUE="${agency.masterId}">${agency.masterName}</option>
				</c:forEach>
			</select>
			<select NAME="state1" STYLE="display: none">

				<c:forEach items="${master.serviceResponse.commonMastersList.state}"
							var="state">
							<option VALUE="${state.masterId}">${state.masterName}</option>
				</c:forEach>
			</select>
			<select id="locs" NAME="locs" STYLE="display: none">
				<c:forEach
						items="${master.serviceResponse.commonMastersList.locationmaster}"
						var="location">
						<option VALUE="${location.locationId}">${location.locationName}</option>
				</c:forEach>
			</select>
			<input type="text" value="${concept.serviceResponse.conceptBean.startDate}" id="strtdate" style="display: none">
			<input type="text" value="${concept.serviceResponse.conceptBean.endDate}" id="endDate" style="display: none">
</form>
<script TYPE="text/javascript">
	$(document).ready(
			function() {

				 $('.js-example-basic-multiple').select2();
				var agenciesList = $("#funding").val();
				
				var arrayAgenies = agenciesList.split(',');
				for (var i = 0; i < arrayAgenies.length; i++) {

					var newOption = new Option($(
							"select[name='ages'] option[value="
									+ arrayAgenies[i] + "]").text(),
							arrayAgenies[i], true, true);
					$("#selects22").append(newOption).trigger('change');
				}
				
				
				var stateList = $("#states").val();
				
				var stateArray = stateList.split(',');
				for (var i = 0; i < stateArray.length; i++) {

					var newOption1 = new Option($(
							"select[name='state1'] option[value="
									+ stateArray[i] + "]").text(),
									stateArray[i], true, true);
					$("#stateId").append(newOption1).trigger('change');
				}
				
				
				var projLocList = $("#projLocs").val();
				
				var projArray = projLocList.split(',');
				for (var i = 0; i < projArray.length; i++) {

					var newOption1 = new Option($(
							"select[name='locs'] option[value="
									+ projArray[i] + "]").text(),
									projArray[i], true, true);
					$("#projectLocation").append(newOption1).trigger('change');
				}
				var startDate=$("#strtdate").val();
			
				var endDate=$("#endDate").val();
				
				 $('#expectedStartDate').datepicker({dateFormat: 'yy-mm-dd'});
			   
			    $('#expectedStartDate').datepicker('setDate', startDate.toString('dd-MM-yy'));
			      
				$('#expectedEndDate').datepicker({dateFormat: 'yy-mm-dd'});
			      
			    $('#expectedEndDate').datepicker('setDate', endDate.toString('dd-MM-yy'));
				 
				 	var concept=$("#status").val();
				 	//alert(concept);
				 	if(concept=='P'){
				 		
				 		 $("#revision").show();
				 		 $("#approve").show();
				 		 $("#reject").show();
				 	}else if(concept=='A'){
				 		 $("#reject").show();
				 		 $("#revision").show();
				 		 $("#approve").hide();
				 		
				 	}else if(concept=='R'){
				 		$("#reject").show();
				 		 $("#revision").hide();
				 		 $("#approve").show();
				 		 
				 	}else{
				 		$("#reject").hide();
				 		 $("#revision").hide();
				 		 $("#approve").hide();
				 	}
				 	$("#revision").click(function(){
				 		
				 		$("#dialog").dialog('open');
				 	});
			});
</script>


