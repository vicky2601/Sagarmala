function onClickMasterfunction(data) {
	var idArr = data.split("^");
var data;
	switch (idArr[0]) {
	case "departmentedit":
		// console.log(JSON.stringify(idArr[1]));
		console.log(JSON.stringify(idArr[1]));
		getRequest('/edit-department/' + idArr[1], listPage);
		break;
	case "agencyedit":
		getRequest('/edit-agency/' + idArr[1], listPage);
		break;
	case "lineministryedit":
		getRequest('/edit-lineministry/' + idArr[1], listPage);
		break;
	case "fundingagencyedit":
		getRequest('/edit-funding-agency/' + idArr[1], listPage);
		break;
	case "locationmasteredit":
		getRequest('/edit-location-master/' + idArr[1], listPage);
		break;
	case "organisationmasteredit":
		getRequest('/api/master/edit-organisation-master/' + idArr[1], editOrganisation);
		break;
	}
}
/*
 * For Department Master Save Or Update
 */
function createDepartment() {
	var stateId = $('#state').val();
	var departmentName = $('#departmentName').val();
	var user = JSON.parse(localStorage.getItem('user_data'));
	var masterTypeCode = "department";
	var departmentId = $('#departmentId').val();
	var status = $('#status').val();
	$(".error").remove();
	if (stateId.length < 1) {
		$('#state')
				.after(
						'<font color="red"><span class="error">Select state is required</span></font>');
		check = false;
	} else if (departmentName.length < 1) {
		$('#departmentName')
				.after(
						'<font color="red"><span class="error">Department is required</span></font>');
		check = false;
	} else {
		var data = {

			"stateId" : stateId,
			"masterName" : departmentName,
			"createdBy" : user.userId,
			"updatedBy" : user.userId,
			"masterTypeCode" : masterTypeCode,
			"masterId" : departmentId,
			"isActive" : status
		};
		console.log(data);
		postRequest(
				'/api/master/createOrUpdateMasters',
				JSON.stringify(data),
				function(result) {
					console.log("My Log Error ---  " + JSON.stringify(result));
					alert(result.serviceResponse.customResponse.responseDescription);
					if (result.serviceResponse.customResponse.responseCode == 200) {
						$('#modal_form_horizontal').modal('toggle');
						getRequest('/department-list', listPage);
					}
				});
	}
}

function listDepartmentData(noofpage, noofdata) {
	var passdata = {
		"stateId" : $("#stateId").val(),
		"startResult" : noofpage,
		"maxResult" : noofdata,
		"masterName" : $("#departmentId").val().trim() == "" ? "0" : $(
				"#departmentId").val().trim()
	}
	console.log(JSON.stringify(passdata));
	var url = '/api/master/fetchDepartmentMasterslist/'
			+ JSON.stringify(passdata);
	console.log(encodeURIComponent(url));
	getRequest(url, fillDepartmentTable);
}

function fillDepartmentTable(data) {
	console.log(data);
	console.log("My " + JSON.stringify(data));
	var flow_sl_cnt = 0;
	var flow_table_head = "";
	var flow_details_record = "<tbody>";
	var footer = "<tfoot><tr><td colspan='5'><ul class=\"pagination\">";
	$("#sizeoflist").val(data.length);
	flow_table_head = "<thead class=\"heading\"><tr \"bg-blue\"><th>S.No.</th><th>State</th><th>Department</th><th>Edit</th><th>Status</th></tr></thead>";
	var inputfield = "";
	console.log(data.serviceResponse.AllData);
	for (var i = 0; i < data.serviceResponse.departmentList.length; i++) {
		var status = (data.serviceResponse.departmentList[i].isActive == 1) ? "checked"
				: "";
		flow_sl_cnt++;
		flow_details_record = flow_details_record
				+ "<tr><td>"
				+ (i + 1)
				+ "</td><td>"
				+ data.serviceResponse.departmentList[i].stateId.masterName
				+ "</td><td>"
				+ data.serviceResponse.departmentList[i].departmentName
				+ "</td><td align=\"center\"><a class='btn btn-info' href=\"javascript:void(0);\" onclick=\"onClickMasterfunction('departmentedit^"
				+ data.serviceResponse.departmentList[i].departmentId
				+ "');\"><i class=\"fa fa-edit\"></i></a></td><td align=\"center\"><label class=\"switch\"> <input type=\"checkbox\" onclick=\"setActiveOrDeActiveDept(this,'"
				+ data.serviceResponse.departmentList[i].isActive + "','"
				+ data.serviceResponse.departmentList[i].departmentId
				+ "');\" " + status
				+ "><span class=\"slider round\"> </span> </label> </td></tr>";
	}
	if (flow_details_record != "<tbody>") {
		flow_details_record = flow_details_record + "</tbody>"
		flow_details_record = flow_table_head + flow_details_record;
		$("#btn_disciplinesection").show("fast");
	} else if (flow_details_record == "<tbody>") {
		flow_details_record = flow_details_record + "/<tbody>"
		flow_details_record = flow_table_head
				+ "<tr><td colspan='5' class='text-center'><span class='label label-danger'> There is no data available </span></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td></tr>";
		$("#btn_disciplinesection").hide("fast");
	}
	$("#table_details").html(flow_details_record);
	$('#table_details').DataTable({
		"bDestroy" : true
	});
}

function setActiveOrDeActiveDept(checkboxElem, data, id) {
	if (checkboxElem.checked) {
		updateDeptData("1", id);
	} else {
		updateDeptData("0", id);
	}
}

function updateDeptData(checkStatus, id) {
	var user = JSON.parse(localStorage.getItem('user_data'));
	var masterTypeCode = "department";
	var form = {
		"masterId" : id,
		"isActive" : parseInt(checkStatus),
		"masterTypeCode" : masterTypeCode,
		"createdBy" : user.userId,
		"updatedBy" : user.userId,
	}
	var data = JSON.stringify(form);
	postRequest('/api/master/createOrUpdateMasters', data, addDept);
}

function addDept(data) {
	alert(data.serviceResponse.customResponse.responseDescription);
}

function verifyifsameDepartment() {

	$(document).on('keyup', $(this), function(el) {
		if ($("#hiddenval").val() != $("#departmentName").val()) {
			$("#updateButton").prop('disabled', false);
		} else {
			$("#updateButton").prop('disabled', true);
		}
	});
}

/*
 * For Agency Master Save Or Update
 */
function createAgency() {
	alert("Agency");
	var stateId = $('#states').val();
	var agencyName = $('#agencyName').val();
	var user = JSON.parse(localStorage.getItem('user_data'));
	var masterTypeCode = "agency";
	var agencyId = $('#agencyId').val();
	var status = $('#status').val();

	$(".error").remove();
	if (stateId.length < 1) {
		$('#states')
				.after(
						'<font color="red"><span class="error">Select state is required</span></font>');
		check = false;
	} else if (agencyName.length < 1) {
		$('#agencyName')
				.after(
						'<font color="red"><span class="error">Agency is required</span></font>');
		check = false;
	} else {
		var data = {

			"stateId" : stateId,
			"masterName" : agencyName,
			"createdBy" : user.userId,
			"updatedBy" : user.userId,
			"masterTypeCode" : masterTypeCode,
			"masterId" : agencyId,
			"isActive" : status
		};
		console.log(data);
		postRequest(
				'/api/master/createOrUpdateMasters',
				JSON.stringify(data),
				function(result) {
					console.log("My Log Error ---  " + JSON.stringify(result));
					alert(result.serviceResponse.customResponse.responseDescription);
					if (result.serviceResponse.customResponse.responseCode == 200) {
						$('#modal_form_horizontal').modal('toggle');
						getRequest('/agency-list', listPage);
					} else {
						window.location.reload();
					}
				});
	}
}

function listAgencyData(noofpage, noofdata) {
	var passdata = {
		"stateId" : $("#byState").val(),
		"startResult" : noofpage,
		"maxResult" : noofdata,
		"masterName" : $("#byAgency").val().trim() == "" ? "0" : $("#byAgency")
				.val().trim()
	}
	console.log(JSON.stringify(passdata));
	var url = '/api/master/fetchAgencyMasterslist/' + JSON.stringify(passdata);
	console.log(encodeURIComponent(url));
	getRequest(url, fillAgencyTable);
}

function fillAgencyTable(data) {
	console.log(data);
	console.log("My " + JSON.stringify(data));
	var flow_sl_cnt = 0;
	var flow_table_head = "";
	var flow_details_record = "<tbody>";
	var footer = "<tfoot><tr><td colspan='5'><ul class=\"pagination\">";

	$("#sizeoflist").val(data.length);
	flow_table_head = "<thead class=\"heading\"><tr><th>S.No.</th><th>State</th><th>Implementation Agency</th><th>Edit</th><th>Status</th></tr></thead>";
	var inputfield = "";
	for (var i = 0; i < data.serviceResponse.agencyListData.length; i++) {
		var status = (data.serviceResponse.agencyListData[i].isActive == 1) ? "checked"
				: "";
		flow_sl_cnt++;
		flow_details_record = flow_details_record
				+ "<tr><td>"
				+ (i + 1)
				+ "</td><td>"
				+ data.serviceResponse.agencyListData[i].stateId.masterName
				+ "</td><td>"
				+ data.serviceResponse.agencyListData[i].masterName
				+ "</td><td align=\"center\"><a class='btn btn-info' href=\"javascript:void(0);\" onclick=\"onClickMasterfunction('agencyedit^"
				+ data.serviceResponse.agencyListData[i].masterId
				+ "');\"><i class=\"fa fa-edit\"></i></a></td><td align=\"center\"><label class=\"switch\"> <input type=\"checkbox\" onclick=\"setActiveOrDeActiveAgency(this,'"
				+ data.serviceResponse.agencyListData[i].isActive + "','"
				+ data.serviceResponse.agencyListData[i].masterId + "');\" "
				+ status
				+ "><span class=\"slider round\"> </span> </label> </td></tr>";
	}
	if (flow_details_record != "<tbody>") {
		flow_details_record = flow_details_record + "/<tbody>"
		flow_details_record = flow_table_head + flow_details_record;
		$("#btn_disciplinesection").show("fast");
	} else if (flow_details_record == "<tbody>") {
		flow_details_record = flow_details_record + "/<tbody>"
		flow_details_record = flow_table_head
				+ "<tr><td colspan='5' class='text-center'><span class='label label-danger'> There is no data available </span></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td></tr>";
		$("#btn_disciplinesection").hide("fast");
	}
	$("#table_details").html(flow_details_record);
	$('#table_details').DataTable({
		"bDestroy" : true
	});

}

function setActiveOrDeActiveAgency(checkboxElem, data, id) {
	if (checkboxElem.checked) {
		updateAgencyData("1", id);
	} else {
		updateAgencyData("0", id);
	}
}

function updateAgencyData(checkStatus, id) {
	var user = JSON.parse(localStorage.getItem('user_data'));
	var masterTypeCode = "agency";
	var form = {
		"masterId" : id,
		"isActive" : parseInt(checkStatus),
		"masterTypeCode" : masterTypeCode,
		"createdBy" : user.userId,
		"updatedBy" : user.userId
	}
	var data = JSON.stringify(form);
	postRequest('/api/master/createOrUpdateMasters', data, addAgency);
}

function addAgency(data) {
	alert(data.serviceResponse.customResponse.responseDescription);
}

function verifyifsameAgency() {

	$(document).on('keyup', $(this), function(el) {
		if ($("#hiddenval").val() != $("#agencyName").val()) {
			$("#updateButton").prop('disabled', false);
		} else {
			$("#updateButton").prop('disabled', true);
		}
	});

}

/*
 * For Lineministry Save or Update
 */
function createLineministry() {
	var lineministryName = $('#lineministryName').val();
	var user = JSON.parse(localStorage.getItem('user_data'));
	var masterTypeCode = "lineministry";
	var lineministryId = $('#lineministryId').val();
	var status = $('#status').val();

	$(".error").remove();
	if (lineministryName.length < 1) {
		$('#lineministryName')
				.after(
						'<font color="red"><span class="error">Central Ministry is required</span></font>');
		check = false;
	} else {
		var data = {

			"masterName" : lineministryName,
			"createdBy" : user.userId,
			"updatedBy" : user.userId,
			"masterTypeCode" : masterTypeCode,
			"masterId" : lineministryId,
			"isActive" : status
		};
		console.log(data);
		postRequest(
				'/api/master/createOrUpdateMasters',
				JSON.stringify(data),
				function(result) {
					console.log("My Log Error ---  " + JSON.stringify(result));
					alert(result.serviceResponse.customResponse.responseDescription);
					if (result.serviceResponse.customResponse.responseCode == 200) {
						$('#modal_form_horizontal').modal('toggle');
						getRequest('/lineministry-list', listPage);
					}
				});
	}
}

function listLineMinistryData(noofpage, noofdata) {
	var lName = $.trim($("#byLineministryName").val());
	var passdata = {
		"startResult" : noofpage,
		"maxResult" : noofdata,
		"masterName" : lName == " " ? "0" : lName

	}
	console.log(JSON.stringify(passdata));
	var url = '/api/master/fetchCommonMasterslist/' + JSON.stringify(passdata);
	console.log(encodeURIComponent(url));
	getRequest(url, fillLineministryTable);
}

function fillLineministryTable(data) {
	console.log(data);
	console.log("My " + JSON.stringify(data));
	var flow_sl_cnt = 0;
	var flow_table_head = "";
	var flow_details_record = "<tbody>";
	var footer = "<tfoot><tr><td colspan='4'><ul class=\"pagination\">";

	$("#sizeoflist").val(data.length);
	flow_table_head = "<thead class=\"heading\"><tr><td>S.No.</td><td>Central Ministry</td><td>Edit</td><td>Status</td></tr></thead>";
	var inputfield = "";
	for (var i = 0; i < data.serviceResponse.commonMastersData.length; i++) {
		var status = (data.serviceResponse.commonMastersData[i].isActive == 1) ? "checked"
				: "";
		flow_sl_cnt++;
		flow_details_record = flow_details_record
				+ "<tr><td>"
				+ (i + 1)
				+ "</td><td>"
				+ data.serviceResponse.commonMastersData[i].masterName
				+ "</td><td align=\"center\"><a class='btn btn-info' href=\"javascript:void(0);\" onclick=\"onClickMasterfunction('lineministryedit^"
				+ data.serviceResponse.commonMastersData[i].masterId
				+ "');\"><i class=\"fa fa-edit\"></i></a></td><td align=\"center\"><label class=\"switch\"> <input type=\"checkbox\" onclick=\"setActiveOrDeActiveLineministry(this,'"
				+ data.serviceResponse.commonMastersData[i].isActive + "','"
				+ data.serviceResponse.commonMastersData[i].masterId + "');\" "
				+ status
				+ "><span class=\"slider round\"> </span> </label> </td></tr>";
	}
	if (flow_details_record != "<tbody>") {
		flow_details_record = flow_details_record + "/<tbody>"
		flow_details_record = flow_table_head + flow_details_record;
		$("#btn_disciplinesection").show("fast");
	} else if (flow_details_record == "<tbody>") {
		flow_details_record = flow_details_record + "/<tbody>"
		flow_details_record = flow_table_head
				+ "<tr><td colspan='4' class='text-center'><span class='label label-danger'> There is no data available </span></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td></tr>";
		$("#btn_disciplinesection").hide("fast");
	}
	$("#table_details").html(flow_details_record);
	$('#table_details').DataTable({
		"bDestroy" : true
	});
}

function setActiveOrDeActiveLineministry(checkboxElem, data, id) {
	if (checkboxElem.checked) {
		updateLineministryData("1", id);
	} else {
		updateLineministryData("0", id);
	}
}

function updateLineministryData(checkStatus, id) {
	var user = JSON.parse(localStorage.getItem('user_data'));
	var masterTypeCode = "lineministry";
	var form = {
		"masterId" : id,
		"isActive" : parseInt(checkStatus),
		"masterTypeCode" : masterTypeCode,
		"createdBy" : user.userId,
		"updatedBy" : user.userId
	}
	var data = JSON.stringify(form);
	postRequest('/api/master/createOrUpdateMasters', data, addLineministry);
}

function addLineministry(data) {
	alert(data.serviceResponse.customResponse.responseDescription);
}

function verifyifsameLineministry() {

	$(document).on('keyup', $(this), function(el) {
		if ($("#hiddenval").val() != $("#lineministryName").val()) {
			$("#updateButton").prop('disabled', false);
		} else {
			$("#updateButton").prop('disabled', true);
		}
	});
}

/*
 * For FundingAgency Master Save Or Update
 */
function createFundingAgencyMaster() {
	var stateId = $('#states').val();
	var agencyName = $('#agencyName').val();
	var user = JSON.parse(localStorage.getItem('user_data'));
	var masterTypeCode = "fundingagency";
	var agencyId = $('#agencyId').val();
	var status = $('#status').val();

	$(".error").remove();
	if (stateId.length < 1) {
		$('#states')
				.after(
						'<font color="red"><span class="error">Select state is required</span></font>');
		check = false;
	} else if (agencyName.length < 1) {
		$('#agencyName')
				.after(
						'<font color="red"><span class="error">Funding Agency is required</span></font>');
		check = false;
	} else {
		var data = {

			"stateId" : stateId,
			"masterName" : agencyName,
			"createdBy" : user.userId,
			"updatedBy" : user.userId,
			"masterTypeCode" : masterTypeCode,
			"masterId" : agencyId,
			"isActive" : status
		};
		console.log(data);
		postRequest(
				'/api/master/createOrUpdateMasters',
				JSON.stringify(data),
				function(result) {
					console.log("My Log Error ---  " + JSON.stringify(result));
					alert(result.serviceResponse.customResponse.responseDescription);
					if (result.serviceResponse.customResponse.responseCode == 200) {
						$('#modal_form_horizontal').modal('toggle');
						getRequest('/fundingAgency-list', listPage);
					}
				});
	}
}

function listFundingAgencyData(noofpage, noofdata) {
	var passdata = {
		"stateId" : $("#byState").val(),
		"startResult" : noofpage,
		"maxResult" : noofdata,
		"masterName" : $("#byAgency").val().trim() == "" ? "0" : $("#byAgency")
				.val().trim()
	}
	console.log(JSON.stringify(passdata));
	var url = '/api/master/fetchFundingAgencyMasterslist/'
			+ JSON.stringify(passdata);
	console.log(encodeURIComponent(url));
	getRequest(url, fillFundingAgencyTable);
}

function fillFundingAgencyTable(data) {
	console.log(data);
	console.log("My " + JSON.stringify(data));
	var flow_sl_cnt = 0;
	var flow_table_head = "";
	var flow_details_record = "<tbody>";
	var footer = "<tfoot><tr><td colspan='5'><ul class=\"pagination\">";

	$("#sizeoflist").val(data.length);
	flow_table_head = "<thead class=\"heading\"><tr><th>S.No.</th><th>State</th><th>Funding Agency</th><th>Edit</th><th>Status</th></tr></thead>";
	var inputfield = "";
	for (var i = 0; i < data.serviceResponse.fundingAgencyListData.length; i++) {
		var status = (data.serviceResponse.fundingAgencyListData[i].isActive == 1) ? "checked"
				: "";
		flow_sl_cnt++;
		flow_details_record = flow_details_record
				+ "<tr><td>"
				+ (i + 1)
				+ "</td><td>"
				+ data.serviceResponse.fundingAgencyListData[i].stateId.masterName
				+ "</td><td>"
				+ data.serviceResponse.fundingAgencyListData[i].masterName
				+ "</td><td align=\"center\"><a class='btn btn-info' href=\"javascript:void(0);\" onclick=\"onClickMasterfunction('fundingagencyedit^"
				+ data.serviceResponse.fundingAgencyListData[i].masterId
				+ "');\"><i class=\"fa fa-edit\"></i></a></td><td align=\"center\"><label class=\"switch\"> <input type=\"checkbox\" onclick=\"setActiveOrDeActiveFundingAgency(this,'"
				+ data.serviceResponse.fundingAgencyListData[i].isActive
				+ "','"
				+ data.serviceResponse.fundingAgencyListData[i].masterId
				+ "');\" " + status
				+ "><span class=\"slider round\"> </span> </label> </td></tr>";
	}
	if (flow_details_record != "<tbody>") {
		flow_details_record = flow_details_record + "/<tbody>"
		flow_details_record = flow_table_head + flow_details_record;
		$("#btn_disciplinesection").show("fast");
	} else if (flow_details_record == "<tbody>") {
		flow_details_record = flow_details_record + "/<tbody>"
		flow_details_record = flow_table_head
				+ "<tr><td colspan='5' class='text-center'><span class='label label-danger'> There is no data available </span></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td></tr>";
		$("#btn_disciplinesection").hide("fast");
	}
	$("#table_details").html(flow_details_record);
	$('#table_details').DataTable({
		"bDestroy" : true
	});
}

function setActiveOrDeActiveFundingAgency(checkboxElem, data, id) {
	if (checkboxElem.checked) {
		updateFundingAgencyData("1", id);
	} else {
		updateFundingAgencyData("0", id);
	}
}

function updateFundingAgencyData(checkStatus, id) {
	var user = JSON.parse(localStorage.getItem('user_data'));
	var masterTypeCode = "fundingagency";
	var form = {
		"masterId" : id,
		"isActive" : parseInt(checkStatus),
		"masterTypeCode" : masterTypeCode,
		"createdBy" : user.userId,
		"updatedBy" : user.userId
	}
	var data = JSON.stringify(form);
	postRequest('/api/master/createOrUpdateMasters', data,
			addFundingAgencyMaster);
}

function addFundingAgencyMaster(data) {
	alert(data.serviceResponse.customResponse.responseDescription);
}

function verifyifsameFundingAgency() {

	$(document).on('keyup', $(this), function(el) {
		if ($("#hiddenval").val() != $("#agencyName").val()) {
			$("#updateButton").prop('disabled', false);
		} else {
			$("#updateButton").prop('disabled', true);
		}
	});

}

/*
 * For Location Master Save Or Update
 */
function createLocationMaster() {
	var stateId = $('#states').val();
	var locationName = $('#locationName').val();
	var user = JSON.parse(localStorage.getItem('user_data'));
	var masterTypeCode = "locationmaster";
	var locationId = $('#locationId').val();
	var status = $('#status').val();

	$(".error").remove();
	if (stateId.length < 1) {
		$('#states')
				.after(
						'<font color="red"><span class="error">Select state is required</span></font>');
		check = false;
	} else if (locationName.length < 1) {
		$('#locationName')
				.after(
						'<font color="red"><span class="error">Location is required</span></font>');
		check = false;
	} else {
		var data = {

			"stateId" : stateId,
			"masterName" : locationName,
			"createdBy" : user.userId,
			"updatedBy" : user.userId,
			"masterTypeCode" : masterTypeCode,
			"masterId" : locationId,
			"isActive" : status
		};
		console.log(data);
		postRequest(
				'/api/master/createOrUpdateMasters',
				JSON.stringify(data),
				function(result) {
					console.log("My Log Error ---  " + JSON.stringify(result));
					alert(result.serviceResponse.customResponse.responseDescription);
					if (result.serviceResponse.customResponse.responseCode == 200) {
						$('#modal_form_horizontal').modal('toggle');
						getRequest('/locationMaster-list', listPage);
					}
				});
	}
}

function listLocationMasterData(noofpage, noofdata) {
	var passdata = {
		"stateId" : $("#byState").val(),
		"startResult" : noofpage,
		"maxResult" : noofdata,
		"masterName" : $("#bylocation").val().trim() == "" ? "0" : $(
				"#bylocation").val().trim()
	}
	console.log(JSON.stringify(passdata));
	var url = '/api/master/fetchLocationMasterslist/'
			+ JSON.stringify(passdata);
	console.log(encodeURIComponent(url));
	getRequest(url, fillLocationMasterTable);
}

function fillLocationMasterTable(data) {
	console.log(data);
	console.log("My " + JSON.stringify(data));
	var flow_sl_cnt = 0;
	var flow_table_head = "";
	var flow_details_record = "<tbody>";
	var footer = "<tfoot><tr><td colspan='5'><ul class=\"pagination\">";

	$("#sizeoflist").val(data.length);
	flow_table_head = "<thead class=\"heading\"><tr><th>S.No.</th><th>State</th><th>Location</th><th>Edit</th><th>Status</th></tr></thead>";
	var inputfield = "";
	for (var i = 0; i < data.serviceResponse.locationListData.length; i++) {
		var status = (data.serviceResponse.locationListData[i].isActive == 1) ? "checked"
				: "";
		flow_sl_cnt++;
		flow_details_record = flow_details_record
				+ "<tr><td>"
				+ (i + 1)
				+ "</td><td>"
				+ data.serviceResponse.locationListData[i].stateId.masterName
				+ "</td><td>"
				+ data.serviceResponse.locationListData[i].locationName
				+ "</td><td align=\"center\"><a class='btn btn-info' href=\"javascript:void(0);\" onclick=\"onClickMasterfunction('locationmasteredit^"
				+ data.serviceResponse.locationListData[i].locationId
				+ "');\"><i class=\"fa fa-edit\"></i></a></td><td align=\"center\"><label class=\"switch\"> <input type=\"checkbox\" onclick=\"setActiveOrDeActiveLocationMaster(this,'"
				+ data.serviceResponse.locationListData[i].isActive + "','"
				+ data.serviceResponse.locationListData[i].locationId
				+ "');\" " + status
				+ "><span class=\"slider round\"> </span> </label> </td></tr>";
	}
	if (flow_details_record != "<tbody>") {
		flow_details_record = flow_details_record + "/<tbody>"
		flow_details_record = flow_table_head + flow_details_record;
		$("#btn_disciplinesection").show("fast");
	} else if (flow_details_record == "<tbody>") {
		flow_details_record = flow_details_record + "/<tbody>"
		flow_details_record = flow_table_head
				+ "<tr><td colspan='5' class='text-center'><span class='label label-danger'> There is no data available </span></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td></tr>";
		$("#btn_disciplinesection").hide("fast");
	}
	$("#table_details").html(flow_details_record);
	$('#table_details').DataTable({
		"bDestroy" : true
	});
}

function setActiveOrDeActiveLocationMaster(checkboxElem, data, id) {
	if (checkboxElem.checked) {
		updateLocationMasterData("1", id);
	} else {
		updateLocationMasterData("0", id);
	}
}

function updateLocationMasterData(checkStatus, id) {
	var user = JSON.parse(localStorage.getItem('user_data'));
	var masterTypeCode = "locationmaster";
	var form = {
		"masterId" : id,
		"isActive" : parseInt(checkStatus),
		"masterTypeCode" : masterTypeCode,
		"createdBy" : user.userId,
		"updatedBy" : user.userId
	}
	var data = JSON.stringify(form);
	postRequest('/api/master/createOrUpdateMasters', data, addLocationMaster);
}

function addLocationMaster(data) {
	alert(data.serviceResponse.customResponse.responseDescription);
}

function verifyifsameLocationMaster() {

	$(document).on('keyup', $(this), function(el) {
		if ($("#hiddenval").val() != $("#locationName").val()) {
			$("#updateButton").prop('disabled', false);
		} else {
			$("#updateButton").prop('disabled', true);
		}
	});

}

/**
 * @developer: Abhishek Tyagi
 * @createdOn: 19/03/2019
 * @purpose: For add new organisation details.
 */
function createOrganization() {
	var OrganisationName = $('#OrganisationName').val();	
	if( $('#editOrganisationName').val()){
		OrganisationName = $('#editOrganisationName').val();
	}
	var user = JSON.parse(localStorage.getItem('user_data'));
	var masterTypeCode = "organization";
	var OrganisationId = $('#OrganisationId').val();
	if( $('#hiddenOrganisationId').val()){
		OrganisationId = $('#hiddenOrganisationId').val();
	}
	var status = $('#status').val();
	if( $('#hiddenOrganisationId').val()){
		status = $('#hiddenOrganisationStatus').val();
	}
	$(".error").remove();
	if (OrganisationName.length < 1) {
		$('#OrganisationName')
				.after(
						'<font color="red"><span class="error">Organisation name is required</span></font>');
		check = false;
	} else {
		var data = {

			"masterName" : OrganisationName,
			"createdBy" : user.userId,
			"updatedBy" : user.userId,
			"masterTypeCode" : masterTypeCode,
			"masterId" : OrganisationId,
			"isActive" : status
		};
		console.log(data);
		postRequest(
				'/api/master/createOrUpdateMasters',
				JSON.stringify(data),
				function(result) {
					console.log("My Log Error ---  " + JSON.stringify(result));
					alert(result.serviceResponse.customResponse.responseDescription);
					if (result.serviceResponse.customResponse.responseCode == 200) {
						$('#modal_form_horizontal').modal('toggle');
						getRequest('/organizationMaster-list', listPage);
					}
				});
	}
}
/**
 * @developer: Abhishek Tyagi
 * @createdOn: 19/03/2019
 * @purpose: for fetching common masters list of organisation.
 */
function listOrganisationData(noofpage, noofdata) {
	var lName = $.trim($("#byOrganisationName").val());
	var passdata = {
		"startResult" : noofpage,
		"maxResult" : noofdata,
		"masterName" : lName == " " ? "0" : lName

	}
	console.log(JSON.stringify(passdata));
	var url = '/api/master/fetchOrganisationMasterslist/'
			+ JSON.stringify(passdata);
	console.log(encodeURIComponent(url));
	getRequest(url, OrganisationListTable);
}
/**
 * @developer: Abhishek Tyagi
 * @createdOn: 20/03/2019
 * @purpose: Display the organisation list in table format.
 */
function OrganisationListTable(data) {
	console.log(data);
	console.log("My " + JSON.stringify(data));
	var flow_sl_cnt = 0;
	var flow_table_head = "";
	var flow_details_record = "<tbody>";
	var footer = "<tfoot><tr><td colspan='4'><ul class=\"pagination\">";
	$("#sizeoflist").val(data.length);
	flow_table_head = "<thead class=\"heading\"><tr><td>S.No.</td><td>Organization</td><td>Edit</td><td>Status</td></tr></thead>";
	var inputfield = "";
	for (var i = 0; i < data.serviceResponse.organisationMasterListData.length; i++) {
		var status = (data.serviceResponse.organisationMasterListData[i].isActive == 1) ? "checked"
				: "";
		flow_sl_cnt++;
		flow_details_record = flow_details_record
		+ "<tr><td>"
		+ (i + 1)
		+ "</td><td>"
		+ data.serviceResponse.organisationMasterListData[i].masterName
		+ "</td><td align=\"center\"><a class='btn btn-info' href=\"javascript:void(0);\" data-toggle=\"modal\" data-target=\"#organizationeditbtn\" onclick=\"onClickMasterfunction('organisationmasteredit^"
		+ data.serviceResponse.organisationMasterListData[i].masterId
		+ "');\"><i class=\"fa fa-edit\"></i></a></td><td align=\"center\"><label class=\"switch\"> <input type=\"checkbox\" onclick=\"setActiveOrDeActiveOrganisation(this,'"
		+ data.serviceResponse.organisationMasterListData[i].isActive
		+ "','"
		+ data.serviceResponse.organisationMasterListData[i].masterId
		+ "');\" " + status
		+ "><span class=\"slider round\"> </span> </label> </td></tr>";
	}
	if (flow_details_record != "<tbody>") {
		flow_details_record = flow_details_record + "/<tbody>"
		flow_details_record = flow_table_head + flow_details_record;
		$("#btn_disciplinesection").show("fast");
	} else if (flow_details_record == "<tbody>") {
		flow_details_record = flow_details_record + "/<tbody>"
		flow_details_record = flow_table_head
				+ "<tr><td colspan='4' class='text-center'><span class='label label-danger'> There is no data available </span></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td></tr>";
		$("#btn_disciplinesection").hide("fast");
	}
	$("#table_details").html(flow_details_record);
	$('#table_details').DataTable({
		"bDestroy" : true
	});
}
/**
 * @developer: Abhishek Tyagi
 * @createdOn: 20/03/2019
 * @purpose: Call Active or deactive organisation method.
 */
function setActiveOrDeActiveOrganisation(checkboxElem, data, id) {
	if (checkboxElem.checked) {
		updateOrganisationData("1", id);
	} else {
		updateOrganisationData("0", id);
	}
}
/**
 * @developer: Abhishek Tyagi
 * @createdOn: 20/03/2019
 * @purpose: For update organisation data to activate and deactivate.
 */
function updateOrganisationData(checkStatus, id) {
	var user = JSON.parse(localStorage.getItem('user_data'));
	var masterTypeCode = "organization";
	var form = {
		"masterId" : id,
		"isActive" : parseInt(checkStatus),
		"masterTypeCode" : masterTypeCode,
		"createdBy" : user.userId,
		"updatedBy" : user.userId
	}
	var data = JSON.stringify(form);
	postRequest('/api/master/createOrUpdateMasters', data, addOrganisation);
}

function addOrganisation(data) {
	alert(data.serviceResponse.customResponse.responseDescription);
}
/**
 * @developer: Abhishek Tyagi
 * @createdOn: 25/03/2019
 * @purpose: For validate the organisation before Update.
 */
function validateOrganisation() {
	$(document).on('keyup', $(this), function(el) {
		if ($("#hiddenOrganisationName").val() != $("#editOrganisationName").val()) {
			$("#updateOrganisation").prop('disabled', false);
		} else {
			$("#updateOrganisation").prop('disabled', true);
		}
	});
}
/**
 * @developer: Abhishek Tyagi
 * @createdOn: 25/03/2019
 * @purpose: For get the details of existing Organisation to Update them.
 */
function editOrganisation(data){
	console.log(data);
	var organisationId=data.serviceResponse.organisationData.masterId;
	var organisationName=data.serviceResponse.organisationData.masterName;
	var organisationStatus=data.serviceResponse.organisationData.isActive;
	$('#editOrganisationName').val(organisationName);
	$('#hiddenOrganisationName').val(organisationName);
	$('#hiddenOrganisationId').val(organisationId);
	$('#hiddenOrganisationStatus').val(organisationStatus);
}
/**
 * @developer: Md Rashid Alam
 * @createdOn: 20/03/2019
 * @purpose: For save the Directorate of ports details.
 */
function createDirectorateOfPorts() {
	//alert('Directorate of ports');

	var stateId = $('#state01').val();
	var directorateOfPortName = $('#directorateOfPorts').val();
	var user = JSON.parse(localStorage.getItem('user_data'));
	var masterTypeCode = "directorateOfPorts";
	var directorateOfPortsId = $('#directorateOfPortsId').val();
	var status = $('#status').val();
	;
	var check = true;
	$(".error").remove();

	if (stateId < 1) {
		$('#state01')
				.after(
						'<font color="red"><span class="error">Select state is required</span></font>');
		check = false;
	} else if (directorateOfPortName.length < 1) {
		$('#directorateOfPorts')
				.after(
						'<font color="red"><span class="error">Directorate of ports name is required</span></font>');
		check = false;
	} else {
		var data = {

			"stateId" : stateId,
			"masterName" : directorateOfPortName,
			"createdBy" : user.userId,
			"updatedBy" : user.userId,
			"masterTypeCode" : masterTypeCode,
			"masterId" : directorateOfPortsId,

			"isActive" : status
		};
		console.log(data);
		postRequest(
				'/api/master/createOrUpdateMasters',
				JSON.stringify(data),
				function(result) {
					console.log("My Log Error ---  " + JSON.stringify(result));
					//alert(result.serviceResponse.customResponse.responseDescription);
					if (result.serviceResponse.customResponse.responseCode == 200) {
						$('#modal_form_horizontal').modal('toggle');
						console.log('saved successfully');
						console.log(result.serviceResponse.message);
						if(result.serviceResponse.message){
							 $(".text").html(result.serviceResponse.message);
							//dismissing the alert after 3 seconds.
							$alert=$('.alert');

							if($alert.length){
								setTimeout(function(){
									$alert.fadeOut('slow')
								},3000)
							}
						}
						
						getRequest('/directorateOfPorts-list', listPage);
					} else {
						window.location.reload();
					}
				});
	}
}
/**
 * @developer: Md Rashid Alam
 * @createdOn: 22/03/2019
 * @purpose: For list of directorateOfPorts Data details.
 */
function listdirectorateOfPortsData(noofpage, noofdata) {

	console.log(noofpage, "-----------" + noofdata);
	var passdata = {
		"stateId" : $("#byState01").val(),
		"startResult" : noofpage,
		"maxResult" : noofdata,
		"masterName" : $("#bydirectorateOfPorts").val().trim() == "" ? "0" : $(
				"#bydirectorateOfPorts").val().trim()
	}
	console.log(JSON.stringify(passdata));
	var url = '/api/master/fetchDirectorateOfPortsMasterslist/'
			+ JSON.stringify(passdata);
	console.log(encodeURIComponent(url));
	getRequest(url, filldirectorateOfPortsTable);
}
/**
 * @developer: Md Rashid Alam
 * @createdOn: 22/03/2019
 * @purpose: For list of directorateOfPorts Data details.
 */
function filldirectorateOfPortsTable(data) {
	console.log(data);
	console.log("My " + JSON.stringify(data));
	var flow_sl_cnt = 0;
	var flow_table_head = "";
	var flow_details_record = "<tbody>";
	var footer = "<tfoot><tr><td colspan='5'><ul class=\"pagination\">";

	$("#sizeoflist").val(data.length);
	flow_table_head = "<thead class=\"heading\"><tr><th>S.No.</th><th>State</th><th>Directorate of ports</th><th>Edit</th><th>Status</th></tr></thead>";
	var inputfield = "";
	for (var i = 0; i < data.serviceResponse.directorateOfPortsMastersListData.length; i++) {
		var status = (data.serviceResponse.directorateOfPortsMastersListData[i].isActive == 1) ? "checked"
				: "";
		flow_sl_cnt++;
		flow_details_record = flow_details_record
				+ "<tr><td>"
				+ (i + 1)
				+ "</td><td>"
				+ data.serviceResponse.directorateOfPortsMastersListData[i].stateId.masterName
				+ "</td><td>"
				+ data.serviceResponse.directorateOfPortsMastersListData[i].masterName
				+ "</td><td align=\"center\"><a class='btn btn-info' href=\"javascript:void(0);\" onclick=\"onClickMasterfunction('directorateOfPortedit^"
				+ data.serviceResponse.directorateOfPortsMastersListData[i].masterId
				+ "');\"><i class=\"fa fa-edit\"></i></a></td><td align=\"center\"><label class=\"switch\"> <input type=\"checkbox\" onclick=\"setActiveOrDeActiveDirectorateOfPorts(this,'"
				+ data.serviceResponse.directorateOfPortsMastersListData[i].isActive + "','"
				+ data.serviceResponse.directorateOfPortsMastersListData[i].masterId + "');\" "
				+ status
				+ "><span class=\"slider round\"> </span> </label> </td></tr>";
	}
	if (flow_details_record != "<tbody>") {
		flow_details_record = flow_details_record + "/<tbody>"
		flow_details_record = flow_table_head + flow_details_record;
		$("#btn_disciplinesection").show("fast");
	} else if (flow_details_record == "<tbody>") {
		flow_details_record = flow_details_record + "/<tbody>"
		flow_details_record = flow_table_head
				+ "<tr><td colspan='5' class='text-center'><span class='label label-danger'> There is no data available </span></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td><td style='display:none'></td></tr>";
		$("#btn_disciplinesection").hide("fast");
	}
	$("#table_details").html(flow_details_record);
	$('#table_details').DataTable({
		"bDestroy" : true
	});
}
/**
 * @developer: Md Rashid Alam
 * @createdOn: 22/03/2019
 * @purpose: Call Active or deactive directorateOfPorts method.
 */
function setActiveOrDeActiveDirectorateOfPorts(checkboxElem, data, id) {
	if (checkboxElem.checked) {
		updateDirectorateOfPortsData("1", id);
	} else {
		updateDirectorateOfPortsData("0", id);
	}
}

/**
 * @developer: Md Rashid Alam
 * @createdOn: 20/03/2019
 * @purpose: For update directorateOfPorts data to activate and deactivate.
 */
function updateDirectorateOfPortsData(checkStatus, id) {
	var user = JSON.parse(localStorage.getItem('user_data'));
	var masterTypeCode = "directorateOfPorts";
	var form = {
		"masterId" : id,
		"isActive" : parseInt(checkStatus),
		"masterTypeCode" : masterTypeCode,
		"createdBy" : user.userId,
		"updatedBy" : user.userId
	}
	var data = JSON.stringify(form);
	postRequest('/api/master/createOrUpdateMasters', data, addDirectorateOfPorts);
}


function addDirectorateOfPorts(data) {
	console.log(data);
	//alert(data.serviceResponse.customResponse.responseDescription);
}
