<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 	
<!-- /horizontal form modal -->
 
  <div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Update Funding Agency</h5>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<form action="#" class="form-horizontal">
							<input type="hidden" id="agencyId" name="agencyId" value="${fundingagency.serviceResponse.fundingAgencyData.masterId}" />
							<input type="hidden" id="status" name="status" value="${fundingagency.serviceResponse.fundingAgencyData.isActive}" />
				        	<div class="modal-body">
                                    <div class="form-group row">
										<label class="col-form-label col-sm-3">State</label>
										<div class="col-sm-9">
											<select class="form-control" id="states">
											     <option value="">Select</option>
											     <c:forEach
										items="${master.serviceResponse.commonMastersList.state}"
										var="state">
										<option value="${state.masterId}" ${state.masterId == fundingagency.serviceResponse.fundingAgencyData.stateId.masterId ? 'selected' : ''}>${state.masterName}</option>
									</c:forEach>
							   </select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-form-label col-sm-3">Funding Agency<span class="compulsory-field">*</span>
										</label>
									<div class="col-sm-9">
								<input type="text" value="${fundingagency.serviceResponse.fundingAgencyData.masterName}"placeholder=" " class="form-control" id="agencyName" onkeypress="verifyifsameFundingAgency()" onkeydown="validateMastersName(event)" ondrag="return false"
									ondrop="return false">
							<input type="hidden" id="hiddenval" value="${fundingagency.serviceResponse.fundingAgencyData.masterName}">
							</div>
									</div> 
								</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-link" onclick="getRequest('/fundingAgency-list', listPage);" data-dismiss="modal">Cancel</button>
					                <button type="button" class="btn bg-primary" onclick="createFundingAgencyMaster()" disabled="disabled" id="updateButton">Update</button>
								</div>
							</form>
						</div>
					</div>
