<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style TYPE="text/css">
body {
	display: initial;
}
</style>
<script src="resources/global_assets/js/plugins/forms/styling/uniform.min.js"></script>
<%-- ${concept.serviceResponse.conceptBean.conceptId} --%>
<%--  ${concept.serviceResponse.conceptBean} --%>
  <%-- <h1>${nodalOfficerName.serviceResponse.userDetail}</h1>  --%>
<%--  ${master.serviceResponse.commonMastersList.organisation}
 --%>
<form ID=" " ACTION="">
	<div class="card">
		<div class="card-header header-elements-inline">
			<h5 class="card-title font-weight-bold">Add Concepts</h5>
			<div class="header-elements">
				<div class="list-icons">
					<a class="list-icons-item" DATA-ACTION="collapse"></a>
				</div>
			</div>
		</div>

		<div class="card-body">

			<div class="row">
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<input TYPE="hidden" VALUE="${id}" ID="id" name="id"
								value="${concept.serviceResponse.conceptBean.projectType}">
							<label FOR="projecttype" class="col-md-4 col-form-label">Project
								Type</label>
							<div class="col-md-8">
								<input TYPE="text" class="form-control" NAME="projecttype"
									ID="projectType" DISABLED="disabled" VALUE="${projecttype}">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="select-1547635305225" class="col-md-4 col-form-label">Funding
								Agencies<span class="compulsory-field">*</span>
							</label>
							<div class="col-md-8">
								<!-- 	<select class="form-control" name="select-1547635305225[]" multiple="multiple" id="select22" required="required" aria-required="true" disabled="disabled"> -->
								<select class="js-example-basic-multiple" MULTIPLE="multiple"
									ID="selects22" DISABLED="disabled">



								</select>


							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="projecttype" class="col-md-4 col-form-label">Project
								Name</label>
							<div class="col-md-8">
								<input TYPE="text" class="form-control"
									NAME="text-1547635404142" ID="projectName">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="text-1547635418158" class="col-md-4 col-form-label">Concept
								ID</label>
							<div class="col-md-8">
								<input TYPE="text" class="form-control" DISABLED="disabled"
									NAME="text-1547635418158" VALUE="${generatedconceptid}"
									ID="conceptid"">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="select-1547635530273" class="col-md-4 col-form-label">States</label>
							<div class="col-md-8">
								<select class="js-example-basic-multiple" MULTIPLE="multiple"
									ID="stateId">
									<option VALUE="default" SELECTED="true">Select a state</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.state}"
										var="state">
										<option VALUE="${state.masterId}">${state.masterName}</option>
									</c:forEach>

								</select>
								<%--   <input type="text" class="form-control" name="text-1547635404142" id="state" value="${userDetails.state.masterName}"> --%>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="select-1547635642895" class="col-md-4 col-form-label">Port-Major/Non
								Major</label>
							<div class="col-md-8">
								<select class="form-control" NAME="select-1547635642895"
									ID="portType">
									<option VALUE="default" SELECTED="true"
										ID="select-1547635642895-0">Choose a port</option>
									<option VALUE="majorport" ID="select-1547635642895-1">Major-Port</option>
									<option VALUE="minorport" ID="select-1547635642895-2">Minor-Port</option>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="select-1547635678252" class="col-md-4 col-form-label">Port
								Name</label>
							<div class="col-md-8">
								<select class="form-control" NAME="select-1547635678252"
									ID="portName">
									<option VALUE="default" SELECTED="true">Select a port</option>
								</select>
								<%--  <input type="text" class="form-control" name="text-1547635404142" id="port" value="${userDetails.port.portName}"> --%>
							</div>
						</div>
					</fieldset>
				</div>
				<!-- Project Location -->
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="select-1547635692843" class="col-md-4 col-form-label">Project
								Location</label>
							<div class="col-md-8">
							 <!--  <select class="form-control" NAME="select-1547635678252"
									ID="projectLocation">
									<option VALUE="default" SELECTED="true" id="default">Select a
										Location</option>
							</select> -->
							<select class="js-example-basic-multiple" MULTIPLE="multiple"
									ID="projectLocation">
									<option VALUE="default" SELECTED="true" id="default">Select a
										Location</option> 
									<%-- <c:forEach
										items="${master.serviceResponse.commonMastersList.locationmaster}"
										var="location">
										<option VALUE="${location.locationId}">${location.locationName}</option>
									</c:forEach> --%>
								</select> 
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="textarea-1547636380668"
								class="col-md-4 col-form-label">Project Brief</label>
							<div class="col-md-8">
								<textarea TYPE="textarea" MAXLENGTH="500" class="form-control"
									NAME="textarea-1547636380668" ID="projectBrief" COLS="" ROWS=""></textarea>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="textarea-1547636401692"
								class="col-md-4 col-form-label">Aims and Objectives</label>
							<div class="col-md-8">
								<textarea TYPE="textarea" MAXLENGTH="500" class="form-control"
									NAME="textarea-1547636401692" ID="aimsAndObjectives" COLS=""
									ROWS=""></textarea>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="textarea-1547636423368"
								class="col-md-4 col-form-label">Scope of work</label>
							<div class="col-md-8">
								<textarea TYPE="textarea" MAXLENGTH="500" class="form-control"
									NAME="textarea-1547636423368" ID="scopeOfWork" COLS="" ROWS=""></textarea>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="text-1547636449122" class="col-md-4 col-form-label">Estimated
								Cost</label>
							<div class="col-md-8">
								<input TYPE="text" class="form-control"
									NAME="text-1547636449122" ID="estimatedCost">
							</div>
						</div>
					</fieldset>
				</div>
				<!-- Project Propenents -->
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="select-1547636484832" class="col-md-4 col-form-label">Project
								Proponent</label>
							<div class="col-md-8">
								<select class="form-control" NAME="select-1547636484832"
									ID="projectProponent">
									<option VALUE="default" SELECTED="true">Select a
										Project Proponents</option>
									<c:forEach
										items="${master.serviceResponse.commonMastersList.organisation}"
										var="propenent">
										<option VALUE="${propenent.masterId}">${propenent.masterName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="date-1547636520974" class="col-md-4 col-form-label">Expected
								Start Date</label>
							<div class="col-md-8">
								<input TYPE="date" class="form-control"
									NAME="date-1547636520974" ID="expectedStartDate">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="date-1547636544458" class="col-md-4 col-form-label">Expected
								End Date</label>
							<div class="col-md-8">
								<input TYPE="date" class="form-control"
									NAME="date-1547636544458" ID="expectedEndDate">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="file-1547641322808" class="col-md-4 col-form-label">Upload
								Document-If Any</label>
							<div class="col-md-8">
								<!-- <input TYPE="file" class="form-control"
									NAME="file-1547641322808" ID="doc"> <input
									TYPE="hidden" ID="filepath"> -->
									<input type="file" class="form-control-uniform-custom" name="file-1547641322808" ID="doc">
								  <input TYPE="hidden" ID="filepath"> 
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label class="col-md-4 col-form-label"> </label>
							<div class="col-md-8"></div>
						</div>
					</fieldset>
				</div>
			</div>
		</div>
	</div>

	<div class="card" ID="add0">
		<div class="card-header header-elements-inline">
			<h5 class="card-title font-weight-bold">Project Nodal Officer</h5>
			<div class="header-elements">
				<div class="list-icons">
					<a class="list-icons-item" DATA-ACTION="collapse"></a>
				</div>
			</div>
		</div>
		<div class="card-body" id="card">
			<div class="row">
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="select-1547641465506" class="col-md-4 col-form-label">Name</label>
							<div class="col-md-8">
								<select class="form-control nodalOfficerName"
									NAME="select-1547641465506" ID="nodalOfficerName">
										<option VALUE="option-1" SELECTED="true"
										ID="select-1547641465506-0">Select Nodal Officer.</option>
								<!-- 	<option VALUE="option-1" SELECTED="true"
										ID="select-1547641465506-0">Option 1</option>
									<option VALUE="option-2" ID="select-1547641465506-1">Option
										2</option>
									<option VALUE="option-3" ID="select-1547641465506-2">Option
										3</option> -->
								 <c:forEach items ="${nodalOfficerName.serviceResponse.userDetail}" var = "nodalOfficerName">
								<option value="${nodalOfficerName.userId}">${nodalOfficerName.userName}</option>
								</c:forEach>  
								</select>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="text-1547641493855" class="col-md-4 col-form-label">Land
								Line Number</label>
							<div class="col-md-8">
								<input TYPE="text" class="form-control nodalOfficerLandline"
									NAME="text-1547641493855" MAXLENGTH="12"
									ID="nodalOfficerLandline">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="text-1547641613536" class="col-md-4 col-form-label">Designation</label>
							<div class="col-md-8">
								<input TYPE="text" class="form-control nodalOfficerDesignation"
									NAME="text-1547641613536" ID="nodalOfficerDesignation">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="text-1547641646157" class="col-md-4 col-form-label">Mobile
								Number</label>
							<div class="col-md-8">
								<input TYPE="text" class="form-control nodalOfficerMobile"
									NAME="text-1547641646157" MAXLENGTH="12"
									ID="nodalOfficerMobile">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="text-1547641764764" class="col-md-4 col-form-label">Postal
								Address</label>
							<div class="col-md-8">
								<input TYPE="text"
									class="form-control nodalOfficerPostalAddress"
									NAME="text-1547641764764" ID="nodalOfficerPostalAddress">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset>
						<div class="form-group row">
							<label FOR="text-1547641778215" class="col-md-4 col-form-label">E-mail
								Address</label>
							<div class="col-md-8">
								<input TYPE="text" class="form-control nodalOfficerEmail"
									NAME="text-1547641778215" ID="nodalOfficerEmail">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-12">
					<fieldset>
						<div class="col-md-12">
							<button TYPE="button" class="btn btn-success pull-right minus"
								NAME="minus" ID="minus" STYLE="display: none">Remove</button>
						</div>
					</fieldset>
				</div>

			</div>

		</div>
		<!-- 	keep this add button -->
		<div class="row">
			<div class="col-md-12">
				<fieldset>
					<div class="col-md-12">
						<button TYPE="button" class="btn btn-success pull-right"
							NAME="plus" ID="plus" ONCLICK="addRemoveExistNodalDiv();">Add</button>
					</div>
				</fieldset>
			</div>

		</div>
	</div>

	<div class="card">
		<div class="card-header header-elements-inline">
			<h5 class="card-title font-weight-bold">Project Nodal Officer</h5>
			<div class="header-elements">
				<div class="list-icons">
					<a class="list-icons-item" DATA-ACTION="collapse"></a>
				</div>
			</div>
		</div>
		<div class="card-body">
			<table style="width: 100%;" border="2" id="nodalInfoTable" class="nodTabInfo">
				
				<thead>
					<tr>
						<th style="display: none">hidden</th>
						<th>Name</th>
						<th>Land Line Number</th>
						<th>Designation</th>
						<th>Mobile Number</th>
						<th>Postal Address</th>
						<th>E-mail Address</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<td id="hiddenNodalNameId" class="hiddenNodNameId" style="display: none"></td>
						<td id="nodalName"></td>
						<td id="nodalLandLine"></td>
						<td id="nodalDesignation"></td>
						<td id="nodalMobile"></td>
						<td id="nodalPostal"></td>
						<td id="nodalEMail"></td>
					</tr>
				
				</tbody>
			</table>


		</div>
	</div>
	<div ID="addextra0" class="add1"></div>
	<!-- div class="row">
		<div class="col-md-12">
			<fieldset>
				<div class="col-md-12">
					<button TYPE="button" class="btn btn-success pull-right"
						NAME="plus" ID="plus" ONCLICK="addRemoveExistNodalDiv();">Add</button>
				</div>
			</fieldset>
		</div>

	</div> -->


	<div class="row">
		<div class="col-md-12">
			<fieldset>
				<div class="col-md-12">
					<br> <br>
					<button TYPE="button" class="btn btn-success pull-right"
						NAME="addconcept" ID="addconcept" ONCLICK="saveConceptDetails();">Save
						Concept Details</button>
				</div>
			</fieldset>
		</div>
		<input TYPE="hidden" VALUE="0" ID="countervar" />
	</div>

	<select NAME="ages" STYLE="display: none">

		<c:forEach items="${master.serviceResponse.commonMastersList.agency}"
			var="agency">
			<option VALUE="${agency.masterId}">${agency.masterName}</option>
		</c:forEach>

	</select>
</form>
<script TYPE="text/javascript">
	$(document)
			.ready(
					function() {
							   /* for (var i in arr)
					             console.log( arr[i]. + "<------------->" + arr[i]. ); */
							$('.js-example-basic-multiple').select2();
						var agenciesList = "${agencies}";
						var arrayAgenies = agenciesList.split(',');
						for (var i = 0; i < arrayAgenies.length; i++) {

							var newOption = new Option($(
									"select[name='ages'] option[value="
											+ arrayAgenies[i] + "]").text(),
									arrayAgenies[i], true, true);
							$("#selects22").append(newOption).trigger('change');
						}
						var user = JSON
								.parse(localStorage.getItem('user_data'));
						var orgCode = user.organisationId.masterUniquecode;
						var userStateValue = user.departmentId.stateId.masterUniqueCode;
						var portType = user.organisationId.masterName;
						var portTypeId = user.organisationId.masterUniquecode;
						
						if (orgCode == 4 || orgCode == 8) {
							$("#stateId").attr('disabled', true);
							$("#portName").attr('disabled', true);
							$("#stateId").val(userStateValue).trigger('change');

							var newOption = new Option(portType, portTypeId,
									true, true);
							$("#portType").append(newOption).attr('selected',
									'selected');
							$("#portType").attr('disabled', true);

						}
						if (orgCode == 3) {

							alert('since this user belongs to a line ministry, we will disable states for them.');
							$(".stateId").hide();
						}
					//	alert("Hellloooo");
						//populate nodalOfficer email,mobile number,desgination when change nodal officer
						//
						 
					});
	$("#nodalOfficerName")
	.change(
			function() {
				var nodOfficeName=$(this).children("option:selected").val();
				console.log($(this).children("option:selected").val());
				//var nodal="${nodalOfficerName}";
				//alert(nodOfficeName);
				getRequest('/api/concept/fetchNodalByOrganisationId/'+36,function nodalOfficerId(data){
				//	debugger;
					//alert(nodOfficeName);
					var jsonData=data.serviceResponse.userDetail;
					console.log(jsonData);
					for (var i = 0; i < jsonData.length; i++) {
						//console.log(jsonData[i].userId);
						if(nodOfficeName==jsonData[i].userId){
							console.log(jsonData[i].email);
							$("#nodalOfficerDesignation").val(jsonData[i].designation);
							$("#nodalOfficerMobile").val(jsonData[i].mobileNumber);
							$("#nodalOfficerEmail").val(jsonData[i].email);
						}
						
					}
				})
				//alert(nodal);
	});
/* 	$("#select-1547641465506-0")
	.change(function(){
		$("input:text").val('');
		$("input:email").val('');
	});
	 */
</script>


