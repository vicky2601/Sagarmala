<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 	
<!-- /horizontal form modal -->
 
  <div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Update Agency</h5>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<form action="#" class="form-horizontal">
							<input type="hidden" id="agencyId" name="agencyId" value="${agency.serviceResponse.agencyData.masterId}" />
							<input type="hidden" id="status" name="status" value="${agency.serviceResponse.agencyData.isActive}" />
				        	<div class="modal-body">
                                    <div class="form-group row">
										<label class="col-form-label col-sm-3">State</label>
										<div class="col-sm-9">
											<select class="form-control" id="states">
											     <option value="">Select</option>
											     <c:forEach
										items="${master.serviceResponse.commonMastersList.state}"
										var="state">
										<option value="${state.masterId}" ${state.masterId == agency.serviceResponse.agencyData.stateId.masterId ? 'selected' : ''}>${state.masterName}</option>
									</c:forEach>
							   </select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-form-label col-sm-3">Implementation Agency<span class="compulsory-field">*</span>
										</label>
									<div class="col-sm-9">
								<input type="text" value="${agency.serviceResponse.agencyData.masterName}"placeholder=" " class="form-control" id="agencyName" onkeypress="verifyifsameAgency();" onkeydown="validateMastersName(event)" ondrag="return false"
									ondrop="return false">
							<input type="hidden" id="hiddenval" value="${agency.serviceResponse.agencyData.masterName}">
							</div>
									</div> 
								</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-link" onclick="getRequest('/agency-list', listPage);" data-dismiss="modal">Cancel</button>
					                <button type="button" class="btn bg-primary" onclick="createAgency()" disabled="disabled" id="updateButton">Update</button>
								</div>
							</form>
						</div>
					</div>
