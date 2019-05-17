<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	

<div class="card">
	<div class="card-header header-elements-inline">
		<h5 class="card-title font-weight-bold">Add Concepts</h5>
		<div class="header-elements">
			<div class="list-icons">
				<a class="list-icons-item" data-action="collapse"></a>
			</div>
		</div>
	</div>
 
	<div class="card-body">
		<form id="rendered-form">
		  <div class="offset-4">
			<div class="row">
			 <div class="col-md-12">
					<fieldset> 
								<div class="form-group mb-3 mb-md-2">
									<!-- <label class="d-block font-weight-semibold">Left inline radios</label> -->
									<div class="custom-control custom-radio custom-control-inline"> 
										<input name="fundtype" access="true" role="1" id="single-funded-0" required="required" aria-required="true" value="Single Funded" type="radio" class="custom-control-input" onclick="checkcheckbox();" >
										<label class="custom-control-label" for="single-funded-0">Single Funded</label>
									</div> 
									<div class="custom-control custom-radio custom-control-inline">
										<input name="fundtype" access="true" role="1" id="single-funded-1" required="required" aria-required="true" value="Multiple Funded" type="radio" class="custom-control-input" onclick="checkcheckbox();" >
										<label class="custom-control-label" for="single-funded-1">Multiple Funded</label>
									</div>
									<div class="custom-control custom-radio custom-control-inline">
										<input name="fundtype" access="true" role="1" id="single-funded-2" required="required" aria-required="true" value="Non Funded" type="radio" class="custom-control-input" onclick="checkcheckbox();">
										<label class="custom-control-label" for="single-funded-2">Non Funded</label>
									</div>
								</div>
					</fieldset>
				</div> 
		   <div class="col-md-8" id="singleAgency" style="display: none">
		      <fieldset> 
					 <div class="form-group mb-3 mb-md-2">
								<label for="select-1547618667557" class="fb-select-label">Select The Funding Agencies<span class="fb-required">*</span> </label> 
								<select class="form-control selects" name="select-1547618667557" access="true" role="1" id="singleSelect" required="required" aria-required="true" onchange="altervalues();">
									<option value="default" selected id="select-1547618667557-0">Choose an agency to continue</option>
									<c:forEach	items="${master.serviceResponse.commonMastersList.agency}" var="agency">
										 <option value="${agency.masterId}">${agency.masterName}</option>
								    </c:forEach>
								</select>
					 </div>
			 </fieldset>
	      </div>
			<div class="col-md-8" id="multipleAgency" style="display: none">
			 <fieldset> 
					 <div class="form-group mb-3 mb-md-2">
				    <label for="select-1547618813968" class="fb-select-label">Multiple Agencies</label>
				   <%--  <select class="form-control select" name="select-1547618813968[]" multiple="multiple" id="multiSelect" onchange="altervalues();" data-fouc>
						<option value="default" id="select-1547618667557-0" selected="selected">Choose an agency or multiple agencies to continue</option>
						<c:forEach	items="${master.serviceResponse.commonMastersList.agency}"	var="agency">
								 <option value="${agency.masterId}">${agency.masterName}</option>
						 </c:forEach> 
					</select> --%>
					<select class="js-example-basic-multiple selects2" id="multiSelect"   multiple="multiple" onchange="altervalues()">
					  <option value="default" id="select-1547618667557-0" selected="selected">Choose an agency or multiple agencies to continue</option>
                       <c:forEach	items="${master.serviceResponse.commonMastersList.agency}"	var="agency" >
								 <option value="${agency.masterId}">${agency.masterName}</option>
						 </c:forEach>  
						 	 
</select>
<!--  <select multiple="multiple" id="my-select" name="my-select[]">
    
    </select> -->
					</div>
			</fieldset>
			</div>
			<div class="col-md-12">
			 <fieldset> 
					 <div class="form-group mb-3 mb-md-2">
				<button type="button" class="btn btn-info" name="addconcept" id="addconcept" disabled="disabled" onclick="saveConcept();">Add Concept Details</button>
				</div>
				</fieldset>
			</div>
			</div>
			</div>
		</form>
	</div>
</div> 
<script>
$(document).ready(function() {
    $('.js-example-basic-multiple').select2();
   // $('#my-select').multiSelect()
});
</script>
<!-- <div>
	<form id="rendered-form">
		<div class="rendered-form">
			<div class="fb-radio-group form-group field-single-funded">
				<label for="single-funded" class="fb-radio-group-label">project
					type<span class="fb-required">*</span>
				</label>
				<div class="radio-group">
					<div class="fb-radio-inline">
						<input name="fundtype" access="true" role="1" id="single-funded-0"
							required="required" aria-required="true" value="singlefunded"
							type="radio" class="radio-group" onclick="checkcheckbox();" > <label for="single-funded-0" >Single
							Funded</label>
					</div>
					<div class="fb-radio-inline">
						<input name="fundtype" access="true" role="1" id="single-funded-1"
							required="required" aria-required="true" value="multiplefunded"
							type="radio" class="radio-group" onclick="checkcheckbox();" ><label for="single-funded-1">Multiple
							Funded</label>
					</div>
					<div class="fb-radio-inline">
						<input name="fundtype" access="true" role="1" id="single-funded-2"
							required="required" aria-required="true" value="nonfunded"
							type="radio" class="radio-group" onclick="checkcheckbox();"><label for="single-funded-2">Non
							Funded</label>
					</div>
				</div>
			</div>
			
		</div>
	</form>
</div> -->

 