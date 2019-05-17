<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <!-- <div id="modal_form_horizontal" class="modal fade" tabindex="-1"> -->
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Update Department</h5>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<form action="#" class="form-horizontal">
							<input type="hidden" id="departmentId" name="departmentId" value="${department.serviceResponse.department.departmentId}" />
							<input type="hidden" id="status" name="status" value="${department.serviceResponse.department.isActive}" />
								<div class="modal-body">
                                    <div class="form-group row">
										<label class="col-form-label col-sm-3">State</label>
										<div class="col-sm-9">
											<select class="form-control" id="state">
											     <option value="">Select</option>
											     <c:forEach
										items="${master.serviceResponse.commonMastersList.state}"
										var="state">
										<option value="${state.masterId}" ${state.masterId == department.serviceResponse.department.stateId.masterId ? 'selected' : ''}>${state.masterName}</option>
									</c:forEach>
								            </select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-form-label col-sm-3">Department<span class="compulsory-field">*</span></label>
										<div class="col-sm-9">
											<input type="text" value="${department.serviceResponse.department.departmentName}" placeholder=" " class="form-control" id="departmentName" onkeypress="verifyifsameDepartment();" onkeydown="validateMastersName(event)" ondrag="return false"
									ondrop="return false">
											<input type="hidden" id="hiddenval" value="${department.serviceResponse.department.departmentName}">
										</div>
									</div> 
								</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-link" onclick="getRequest('/department-list', listPage);" data-dismiss="modal">Cancel</button>
									<button type="button" class="btn bg-primary" onclick="createDepartment()" disabled="disabled" id="updateButton">Update </button>
								</div>
							</form>
						</div>
					</div>
			<!-- 	</div>  -->

 
 