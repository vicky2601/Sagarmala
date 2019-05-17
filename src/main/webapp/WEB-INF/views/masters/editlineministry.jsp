<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 	
<!-- /horizontal form modal -->
 
  <div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Update Central Ministry</h5>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<form action="#" class="form-horizontal">
							<input type="hidden" id="lineministryId" name="lineministryId" value="${lineministry.serviceResponse.commonMastersData.masterId}" />
							<input type="hidden" id="status" name="status" value="${lineministry.serviceResponse.commonMastersData.isActive}" />
				        	<div class="modal-body">
									<div class="form-group row">
										<label class="col-form-label col-sm-3">Central Ministry</label>
										<div class="col-sm-9">
											<input type="text" id=lineministryName value="${lineministry.serviceResponse.commonMastersData.masterName}" placeholder="Kopyov" class="form-control" onkeypress="verifyifsameLineministry();" onkeydown="validateMastersName(event)" ondrag="return false"
									ondrop="return false">
											<input type="hidden" id="hiddenval" value="${lineministry.serviceResponse.commonMastersData.masterName}">
										</div>
									</div> 
								</div>
									<div class="modal-footer">
									<button type="button" class="btn btn-link" onclick="getRequest('/lineministry-list', listPage);" data-dismiss="modal">Cancel</button>
					                <button type="button" class="btn bg-primary" onclick="createLineministry()" disabled="disabled" id="updateButton">Update</button>
								</div>
							</form>
						</div>
					</div>
