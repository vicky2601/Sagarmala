window.addEventListener('beforeunload', function (e) {
  // Cancel the event
  e.preventDefault();
  // Chrome requires returnValue to be set
  e.returnValue = 'Sure?';
});

function loginIdValidate(val) {
    var clean = val.replace(/[0-9]+/gi, "");
    if (clean.length <= 4) {
        return true;
    } else {
        return false;
    }
}
function generateCaptcha() {
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$&";
    for (var i = 0; i < 6; i++) {
        text += possible.charAt(Math.floor(Math.random() * possible.length));
    }
    document.getElementById("mainCaptcha").innerHTML = text
    document.getElementById("mainCaptcha").value = text
}
function forgetPage(data) {
    $('body').removeClass("cpais-login-bg");
    $('body').html(data);
}
function forgetRequest() {
    var user_name = $('#user_name').val();
    $(".error").remove();
    if (user_name.length < 1) {
        $('#user_name')
            .after(
            '<font color="red"><span class="error">User Name is required</span></font>');
        check = false;
    } else {
        var data = {
            "userName": user_name
        };
        console.log(data);

        $.ajax({
                'url': $('#contextPathHolder').attr('data-contextPath')
                + '/api/user/forgetPassword',
                'type': 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                "data": JSON.stringify(data),
                'success': function(result) {
                    console.log("My Log Error ---  "
                        + JSON.stringify(result));
                    alert(result.serviceResponse.customResponse.responseDescription);
                    if (result.serviceResponse.customResponse.responseCode == 200) {
                        homeScreen();
                    } else {
                        // getRequestWithoutToken('/forget', forgetPage);
                    }
                },
                'error': function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log("My Log Error ---  " + textStatus);
                    console.log("My Log Error ---  " + errorThrown);
                    console.log("My Log Error ---  "
                        + JSON.stringify(XMLHttpRequest));
                    getRequestWithoutToken('/forget', forgetPage);
                }
            });
    }
}
function resetPassword() {
    var user_name = $('#username').val();
    var password = $('#pass').val();
    var newpassword = $('#newPass').val();
    console.log(password + ' ** ' + newpassword + ' ' + user_name);
    $(".error").remove();
    if (password.length < 1) {
        $('#newPass')
            .after(
            '<font color="red"><span class="error">password is required</span></font>');
        check = false;
    } else if (password != newpassword) {
        $('#newPass')
            .after(
            '<font color="red"><span class="error">password should be same</span></font>');
        check = false;
    } else {
        var data = {
            "userName": user_name,
            "newPassword": newpassword
        };
        console.log(data);
        alert(data);
        $
            .ajax({
                'url': $('#contextPathHolder').attr('data-contextPath')
                + '/api/user/resetPassword',
                'type': 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                "data": JSON.stringify(data),
                'success': function(result) {
                    console.log("My Log Error ---  "
                        + JSON.stringify(result));
                    alert(result.serviceResponse.customResponse.responseDescription);
                    if (result.serviceResponse.customResponse.responseCode == 200) {
                        homeScreen();
                    } else {
                        // getRequestWithoutToken('/forget', forgetPage);
                    }
                },
                'error': function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log("My Log Error ---  " + textStatus);
                    console.log("My Log Error ---  " + errorThrown);
                    console.log("My Log Error ---  "
                        + JSON.stringify(XMLHttpRequest));
                    alert(JSON.stringify(XMLHttpRequest));
                }
            });
    }
}
function listPage(data) {
    $('body').html(data);
}
function listRoledata(noofpage, noofdata) {
    var passdata = {
        "roleName": $("#rolename").val() != '' ? $("#rolename").val() : '0',
        "roleTpyeId": $("#roletype").val(),
        "levelId": $("#level").val(),
        "organisationId": $("#department").val(),
        "status": $("#status").val(),
        "startResult": noofpage,
        "maxResult": noofdata
    }
    var url = '/api/user/rolelist/' + JSON.stringify(passdata);
    console.log(encodeURIComponent(url));
    getRequest(url, fillRoleTable);
}
function fillRoleTable(data) {
    console.log(data);
    console.log("My " + JSON.stringify(data));
    var flow_sl_cnt = 0;
    var flow_table_head = "";
    var flow_details_record = "<tbody>";
    var footer = "<tfoot><tr><td colspan='10'><ul class=\"pagination\">";
    for (var i = 0; i <= data.serviceResponse.totalSize / 10; i++) {
        footer = footer
            + "<li><a href=\"javascript:void(0);\" onclick=\"listRoledata("
            + (i * 10) + ",10)\">" + (i + 1) + "</a></li>";
    }
    footer = footer + "</ul></td></tr></tfoot>";
    $("#sizeoflist").val(data.length);
    flow_table_head = "<thead class=\"heading\"><tr class=\"bg-blue\"><th>S.No.</th><th>Level</th><th>Department</th><th>Role Name</th><th>Role Type</th><th>Edit</th><th>Status</th></tr></thead>";
    var inputfield = "";
    console.log(data.serviceResponse.AllData);
    for (var i = 0; i < data.serviceResponse.AllData.length; i++) {
        var status = (data.serviceResponse.AllData[i].isActiv == 1) ? "checked"
            : "";
        flow_sl_cnt++;
        flow_details_record = flow_details_record
            + "<tr><td>"
            + (i + 1)
            + "</td><td>"
            + data.serviceResponse.AllData[i].levelId.masterName
            + "</td><td>"
            + data.serviceResponse.AllData[i].deparmentId.masterName
            + "</td><td>"
            + data.serviceResponse.AllData[i].roleName
            + "</td><td> "
            + data.serviceResponse.AllData[i].roleTpyeId.masterName
            + "</td><td align=\"center\"><a class='btn btn-info' href=\"javascript:void(0);\" onclick=\"onClickfunction('roleedit^"
            + data.serviceResponse.AllData[i].roleId
            + "');\"><i class=\"fa fa-edit\"></i></a></td><td align=\"center\"><label class=\"switch\"> <input type=\"checkbox\" onclick=\"doalert(this,'"
            + data.serviceResponse.AllData[i].isActiv + "','"
            + data.serviceResponse.AllData[i].roleId + "');\" " + status
            + "><span class=\"slider round\"> </span> </label> </td></tr>";
    }
    if (flow_details_record != "<tbody>") {
        flow_details_record = flow_details_record + "/<tbody>"
        flow_details_record = flow_table_head + flow_details_record;
        $("#btn_disciplinesection").show("fast");
    } else if (flow_details_record == "<tbody>") {
        flow_details_record = flow_details_record + "/<tbody>"
        flow_details_record = flow_table_head
            + "<tr><td class=\"text-center\" colspan=\"8\"><span class=\"label label-danger\"> There are no data available </span></td></tr>";
        $("#btn_disciplinesection").hide("fast");
    }
    $("#table_details").html(flow_details_record + footer);
}
function listUserData(noofpage, noofdata) {
    var user = JSON.parse(localStorage.getItem('user_data'));
    var miPorts = $("#minnorPortId").val();
    var miList = [];
    if (miPorts.length != 0) {
        for (var i = 0; i < miPorts.length; i++) {
            if (miPorts[i] != 0) {
                // miList.push(parseInt(miPorts[i], 10));
                miList.push({
                    "masterId": parseInt(miPorts[i], 10)
                });
            }
        }
    }
    var passdata = {
        "organisationId": $("#organisationId").val(),
        "roleId": $("#role").val(),
        "stateId": $("#state").val(),
        "lineMinistryId": $("#lineMinistry").val(),
        "portId": $("#port").val(),
        "maritimeBoardId": $("#maritimeboard").val(),
        "agencyId": $("#agency").val(),
        "departmentId": $("#departmentId").val(),
        "status": $("#status").val(),
        "startResult": noofpage,
        "maxResult": noofdata,
        "searchByUserId": user.userId,
        "minnorPortList": miList
    }
    // var url = '/api/user/userlist/' + JSON.stringify(passdata);
    // console.log(encodeURIComponent(url));
    // getRequest(url, fillUserTable);
    postRequest('/api/user/userlist/search', JSON.stringify(passdata),fillUserTable);
}
function fillUserTable(data) {
    console.log(data);
    var user = JSON.parse(localStorage.getItem('user_data'));
    console.log("My " + JSON.stringify(data));
    var flow_sl_cnt = 0;
    var flow_table_head = "";
    var flow_details_record = "<tbody>";
    var footer = "<tfoot><tr><td colspan='14'><ul class=\"pagination\">";
    /*
	 * for (var i = 0; i <= data.serviceResponse.totalSize / 10; i++) { footer =
	 * footer + "<li><a href=\"javascript:void(0);\" onclick=\"listUserData(" +
	 * (i * 10) + ",10)\">" + (i + 1) + "</a></li>"; }
	 */
    footer = footer + "</ul></td></tr></tfoot>";
    $("#sizeoflist").val(data.length);
    if (user.role.roleId == -1) {
        flow_table_head = "<thead class=\"heading\"><tr class=\"bg-blue\"><th>S.No.</th><th>Login ID</th><th>User Name</th><th>Organization</th><th>Role</th><th>State</th><th>Port</th><th>Maritime Board</th>   <th>Line Ministry</th><th>Department</th><th>Agency</th><th>Edit</th><th>Status</th><th>View</th><th>Reset password/Details</th></tr></thead>";
    } else {
        flow_table_head = "<thead class=\"heading\"><tr class=\"bg-blue\"><th>S.No.</th><th>Login ID</th><th>User Name</th><th>Organization</th><th>Role</th><th>State</th><th>Port</th><th>Maritime Board</th>   <th>Line Ministry</th><th>Department</th><th>Agency</th><th>Edit</th><th>Status</th><th>View</th><th>Reset password/Details</th></tr></thead>";
    }

    var inputfield = "";
    console.log(data.serviceResponse.AllData);
    for (var i = 0; i < data.serviceResponse.AllData.length; i++) {
        var status = (data.serviceResponse.AllData[i].isActive == 1) ? "checked"
            : "";
        /*
		 * if (user.role.roleId != -1) { status = status + " disabled"; }
		 */
        flow_sl_cnt++;
        flow_details_record = flow_details_record
            + "<tr><td>"
            + (i + 1)
            + "</td><td>"
            + data.serviceResponse.AllData[i].userName
            + "</td><td>"
            + data.serviceResponse.AllData[i].name
            + "</td><td>"
            + data.serviceResponse.AllData[i].organisationId.masterName
            + "</td><td> "
            + data.serviceResponse.AllData[i].role.roleName
            + "</td><td>"
            + data.serviceResponse.AllData[i].state.masterName
            + "</td><td>"
            + data.serviceResponse.AllData[i].port.portName
            + "</td><td>"
            + data.serviceResponse.AllData[i].maritimeBoardId.maritimeBoardName
            + "</td><td>"
            + data.serviceResponse.AllData[i].lineMinistryId.masterName
            + "</td><td>"
            + data.serviceResponse.AllData[i].departmentId.departmentName
            + "</td><td> "
            + data.serviceResponse.AllData[i].agency.masterName
            + "</td><td align=\"center\"><a class='btn btn-warning btn-sm' href=\"javascript:void(0);\" onclick=\"onClickfunction('useredit^"
            + data.serviceResponse.AllData[i].userName
            + "');\"><i class=\"fa fa-edit\"></i></a></td><td align=\"center\"><label class=\"switch\"> <input type=\"checkbox\" onclick=\"activeorDeactiveUser(this,'"
            + data.serviceResponse.AllData[i].isActive
            + "','"
            + data.serviceResponse.AllData[i].userId
            + "');\" "
            + status
            + "><span class=\"slider round\"> </span> </label> </td><td align=\"center\"><a class='btn btn-info btn-sm' href=\"javascript:void(0);\" onclick=\"onClickfunction('userview^"
            + data.serviceResponse.AllData[i].userName
            + "');\"><i class=\"fa fa-eye\"></i></a></td>";
        // if (user.role.roleId == -1) {
        flow_details_record = flow_details_record
            + "<td align=\"center\"><a class='btn btn-info btn-sm' href=\"javascript:void(0);\" onclick=\"onClickfunction('userresetpassword^"
            + data.serviceResponse.AllData[i].userName
            + "');\"><i class=\"fa fa-refresh\"></i></a></td></tr>";
        /*
		 * } else { flow_details_record = flow_details_record + "</tr>"; }
		 */
    }
    if (flow_details_record != "<tbody>") {
        flow_details_record = flow_details_record + "</tbody>"
        flow_details_record = flow_table_head + flow_details_record;
        $("#btn_disciplinesection").show("fast");
        $("#table_details").html(flow_details_record);
        var table = $('#table_details').DataTable({	"dom": '<"top"i>rt<"bottom"flp><"clear">', "bDestroy": true });
       
    } else if (flow_details_record == "<tbody>") {
        flow_details_record = flow_details_record
            + "<tr><td class=\"text-center\" colspan=\"15\"><span class=\"label label-danger\"> There are no data available </span></td></tr></tbody>";
         flow_details_record = flow_table_head + flow_details_record;
        $("#btn_disciplinesection").hide("fast");
        $("#table_details").html(flow_details_record);
    }
}
function onClickfunction(data) {
    var idArr = data.split("^");
    switch (idArr[0]) {
        case "useredit":
            console.log(JSON.stringify(idArr[1]));
            getRequest('/user-action/edit/' + idArr[1], listPage);
            break;
        case "roleedit":
            console.log(JSON.stringify(idArr[1]));
            getRequest('/edit-role/' + idArr[1], listPage);
            break;
        case "assignedit":
            console.log(JSON.stringify(idArr[1]));
            getRequest('/edit-assign/' + idArr[1], listPage);
            setTimeout(function() { // calls click event after a certain time
                populateparentChildMenu(idArr[1]);
            }, 500);

            break;
        case "userview":
            console.log(JSON.stringify(idArr[1]));
            getRequest('/user-action/view/' + idArr[1], listPage);
            break;
        case "roleUpdate":
            console.log(JSON.stringify(idArr[1]));

            // getRequest('/user-action/view/' + idArr[1], listPage);
            break;
        case "userresetpassword":
            console.log(JSON.stringify(idArr[1]));
            getRequest('/user-action/reset/' + idArr[1], listPage);
            // getRequest('/user-action/view/' + idArr[1], listPage);
            break;
        case "userViewForAssignedProject":
            console.log(JSON.stringify(idArr[1]));
            getRequest('/api/user/viewAssignProject/' + idArr[1], viewAssignProject);
            break;
        case "userEditForAssignedProject":
            // console.log(JSON.stringify(idArr[1]));
            getRequest('/user-action/editAssignProject/' + idArr[1], listPage);
            break;
    }
}
function saveRoleData() {
    var user = JSON.parse(localStorage.getItem('user_data'));
    var roleId = $("#roleId").val();
    var id;
    var isEnable = 1;
    if (typeof roleId !== 'undefined') {
        id = roleId;
        isEnable = parseInt($("#isActive").val());
    } else {
        id = 0;
    }
    var form = {
        "roleId": id,
        "roleName": $("#addRoleName").val(),
        "levelId": {
            "masterId": parseInt($("#addRolelevelId").val())
        },
        "isActiv": isEnable,
        "isDeleted": 0,
        "createdBy": user.userId,
        "updatedBy": user.userId,
        "roleTpyeId": {
            "masterId": parseInt($("#addRoleroleTypeId").val())
        },
        "deparmentId": {
            "masterId": parseInt($("#addRoledepartmentId").val())
        },
        "roleDescription": $("textarea#addRoleDescription").val(),
        "status": 1,
        // "remarks":"xyz"
    }
    var data = JSON.stringify(form);

    postRequest('/api/user/createOrUpdateRole', data, addRole);
}

function addRole(result) {
    console.log(JSON.stringify(result));
    alert(result.serviceResponse.custumresponse.responseDescription);
}

function addUserData(data){ 
    var orgId = $('#organisationId').val().trim();
    $(".error").remove();
    if(orgId!=0){
    var oldMobile=$("#mobilealt").val();
    var newMobile=$("#mobile_number").val();
    var oldEmail=$("#emailalt").val();
    var newEmail=$("#email").val();
    var otp;
    var statement;
    var type;
    var email;
    var phone;
    var user = JSON.parse(localStorage.getItem('user_data'));
    // var level = $('#levelId').val().trim();
    var organisationId = $('#organisationId').val().trim();
    var role = $('#roleId').val().trim();
    var lineMinistryId = $('#lineMinistryId').val().trim();
    var state = $('#stateId').val().trim();
    var departmentId = $('#departmentId').val();
    var port = $('#portId').val();
    var boardId = $('#boardId').val();
    var agency = $('#agencyId').val();
    // var govtAgencyId = $('#govtAgencyId').val().trim();
    var assignProjectId = $('#assignProjectId').val();
    var assignMinnorId = $('#minnorPortId').val();

    var designation = $('#designation').val().trim();
    var loginId = $('#userName').val().trim();
    var name = $('#name').val().trim();
    var mobile = $('#mobile_number').val().trim();
    var email = $('#email').val().trim();
    var passdata;
    var check = true;
    var proval = [];
    var miPortList = [];
    // $(".error").remove();
    if (organisationId == 0) {
        console.log('department data --> ');
        $('#organisationId')
                .after(
                        '<font color="red"><span class="error">Please Select Department</span></font>');
        if (check) {
            check = false;

        }
    }
    if (role == 0) {
        console.log('roleId data --> ');
        $('#roleId')
                .after(
                        '<font color="red"><span class="error">Please Select Role</span></font>');
        if (check) {
            check = false;

        }
    }
    if (organisationId != 34 && organisationId != 35) { // Not for both Minister
        // Office
        // (MoS), Senior Officials of
        // MoS/SDCL)
        /*
		 * if (assignProjectId.length == 0) { $('#assignProjectId') .after( '<font
		 * color="red"><span class="error">Please Select Assign Projects</span></font>');
		 * console.log('assignProjectId data --> '); if (check) { check = false;
		 *  } } else { for (var i = 0; i < assignProjectId.length; i++) {
		 * proval.push({ "projectId" : assignProjectId[i] }); }
		 * console.log('proval :-' + JSON.stringify(proval)); }
		 */
    }
    if (organisationId == 36) { // Line Ministry
        if (lineMinistryId == 0) {
            console.log('lineMinistryId data --> ');
            $('#lineMinistryId')
                    .after(
                            '<font color="red"><span class="error">Please Select Line Ministry</span></font>');
            if (check) {
                check = false;

            }
        }
    } else if (organisationId == 37) { // Major Port Official
        if (state == 0) {
            console.log('stateId data --> ');
            $('#stateId')
                    .after(
                            '<font color="red"><span class="error">Please Select State</span></font>');
            if (check) {
                check = false;

            }
        }
        if (port == 0) {
            console.log('portId data --> ');
            $('#portId')
                    .after(
                            '<font color="red"><span class="error">Please Select Port</span></font>');
            if (check) {
                check = false;

            }
        }
    } else if (organisationId == 38 && boardId != 0) { // Maritime Board
        // Official
        if (state == 0) {
            console.log('stateId data board--> ');
            $('#stateId')
                    .after(
                            '<font color="red"><span class="error">Please Select State</span></font>');
            if (check) {
                check = false;

            }
        }
        if (boardId == 0) {
            console.log('boardId data --> ');
            $('#boardId')
                    .after(
                            '<font color="red"><span class="error">Please Select Maritime Board</span></font>');
            if (check) {
                check = false;

            }
        }
    } else if (organisationId == 39 && state != 0) { // State official
        if (state == 0) {
            console.log('stateId data state--> ');
            $('#stateId')
                    .after(
                            '<font color="red"><span class="error">Please Select State</span></font>');
            if (check) {
                check = false;

            }
        }

        if (departmentId == 0) {
            console.log('departmentId data --> ');
            $('#departmentId')
                    .after(
                            '<font color="red"><span class="error">Please Select Sub Department</span></font>');
            if (check) {
                check = false;

            }
        }
    } else if (organisationId == 40) { // Implementation Agency User
        if (agency == 0) {
            console.log('agencyId data --> ');
            $('#agencyId')
                    .after(
                            '<font color="red"><span class="error">Please Select Agency</span></font>');
            if (check) {
                check = false;

            }
        }
    }   
    else if (organisationId == 41) { // Minner Port Official
        if (state == 0) {
            console.log('stateId data --> ');
            $('#stateId')
                .after(
                '<font color="red"><span class="error">Please Select State</span></font>');
            if (check) {
                check = false;

            }
        }
        if (assignMinnorId.length == 0) {
            console.log('Minner Port data --> ');
            $('#minnorPortId')
                .after(
                '<font color="red"><span class="error">Please Select Minnor Port</span></font>');
            if (check) {
                check = false;

            }
        }
        else {
            // alert(assignMinnorId.length);
            for (var i = 0; i < assignMinnorId.length; i++) {
                miPortList.push({
                    "masterId": assignMinnorId[i]
                });
            }
            console.log('miPortList :-' + JSON.stringify(miPortList));
        }
    }

    if (designation.length == 0) {
        console.log('designationId data --> ');
        $('#designation')
            .after(
            '<font color="red"><span class="error">Please Enter Designation</span></font>');
        if (check) {
            check = false;

        }
    }
    if (loginId == '') {
        console.log('loginId data --> ');
        $('#userName')
            .after(
            '<font color="red"><span class="error">Please Enter Login Id</span></font>');
        if (check) {
            check = false;

        }
    } else if (!loginIdValidate(loginId)) {
        console.log('loginId data --> ');
        $('#userName')
            .after(
            '<font color="red"><span class="error">Please Enter Correct Format LoginId</span></font>');
        if (check) {
            check = false;

        }
    }
    if (name == '') {
        console.log('department data --> ');
        $('#name')
            .after(
            '<font color="red"><span class="error">Please Enter Correct Name</span></font>');
        if (check) {
            check = false;

        }
    }
    if (mobile.length < 10
        || !(mobile.startsWith("7") || mobile.startsWith("8") || mobile
            .startsWith("9"))) {
        console.log('department data --> ');
        $('#mobile_number')
            .after(
            '<font color="red"><span class="error">Please Enter Correct Mobile</span></font>');
        if (check) {
            check = false;

        }
    }
    if (email == '') {
        console.log('department data --> ');
        $('#email')
            .after(
            '<font color="red"><span class="error">Please Enter Email</span></font>');
        if (check) {
            check = false;

        }
    } else if (!validateEmail(email)) {
        console.log('department data --> ');
        $('#email')
            .after(
            '<font color="red"><span class="error">Please Enter Correct Email</span></font>');
        if (check) {
            check = false;

        }
    }

    console.log('user data --> ' + check);
    if (check) {
        console.log('user data --> ');
        var userId;
        var isActive;
        var isFirstAttempt;
        if (data == 'add') {
            userId = 0;
            isActive = 1;
            isFirstAttempt = 1;

        } else {
            userId = $("#userId").val();
            isActive = $("#status").val();
            isFirstAttempt = $("#isfirstattempt").val();
        }
        passdata = {
            /*
			 * "levelId" : { "masterId" : $("#levelId").val() },
			 */
            "organisationId": {
                "masterId": organisationId
            },
            "role": {
                "roleId": (role == 0 || role == null) ? -1 : role
            },
            "lineMinistryId": {
                "masterId": (lineMinistryId == 0 || lineMinistryId == null) ? -1
                    : lineMinistryId
            },
            "state": {
                "masterId": (state == 0 || state == null) ? -1 : state
            },
            "departmentId": {
                "departmentId": (departmentId == 0 || departmentId == null) ? -1
                    : departmentId
            },
            "port": {
                "portId": (port == 0 || port == null) ? -1 : port
            },
            "maritimeBoardId": {
                "maritimeBoardId": (boardId == 0 || boardId == null) ? -1
                    : boardId,
            },
            "agency": {
                "masterId": (agency == 0 || agency == null) ? -1 : agency
            },
            "projects": proval,
            "designation": designation,
            "userName": loginId,
            "isLoginUserNameEditable": $("#checkLoginIdEditable").is(
                ':checked') ? 1 : 0,
            "name": name,
            "mobileNumber": mobile,
            "email": email,
            "createdBy": user.userId,
            "updatedBy": user.userId,
            "userId": userId,
            "isActive": isActive,
            "isFirstAttempt": isFirstAttempt,
            "minorPorts": miPortList
        }
        console.log('user data --> ' + JSON.stringify(passdata))
        postRequest('/api/user/saveUserDetails', JSON.stringify(passdata),
            showStatusUser);
    }

   
        }
     else {
        $('#organisationId').after('<font color="red"><span class="error">Please Select Organization</span></font>');
        // alert("Please Select Organization First");
    }
}
 /*
	 * if(newMobile!=oldMobile||newEmail!=oldEmail) { if(newMobile!=oldMobile) {
	 * statement="please enter the otp sent on your mobile number :"+newMobile;
	 * type="phone"; param=newMobile; } if(newEmail!=oldEmail) {
	 * statement="please enter the otp sent on your email id :"+newEmail;
	 * type="mail" param=newEmail; } } }
	 */
   /*
	 * getRequest("/api/user/generateOtp/"+type+'/'+param, function
	 * returnOtp(data){ var x; otp=data; console.log(otp); // var
	 * x=prompt(statement); $.sweetModal.prompt( statement, null, otp,
	 * function(val) { x=val; if(val!=null||val!='') {
	 * 
	 * getRequest('/api/user/validateOtp/'+x, function otpValidator(data){
	 * 
	 * if(data=='SUCCESS') {
	 *  } else { $.sweetModal({ content: 'Invalid OTP.', title: 'Invalid OTP.',
	 * icon: $.sweetModal.ICON_ERROR, buttons: [ { label: 'Close', classes:
	 * 'redB' } ] }); }
	 * 
	 * }); }
	 * 
	 * });
	 * 
	 * });
	 */
    
 
   
// }

function isOtpValid(newMobile, oldMobile, newEmail, oldEmail) {
    var status;
    if (newMobile != oldMobile) {
        getRequest("/api/user/generateOtp", function returnOtp(data) {
            console.log(data);
            var x = prompt("please enter the otp sent on your mobile number :" + newMobile)
            if (x) {

                getRequest('/api/user/validateOtp/' + data, function otpValidator(data) {
                    if (data == 'SUCCESS') {

                        status = 1;
                    }
                    else {
                        status = 0;
                    }


                });
            }
        });
    }

    return status;
}

function showStatusUser(data) {
    console.log("My Log Error ---  " + JSON.stringify(data));
    alert(data.serviceResponse.customResponse.responseDescription);
    if (data.serviceResponse.customResponse.responseCode == 200) {
        getRequest('/user-list', listPage);
        // document.getElementById('adduserform').reset();
        // $('#adduserform').reset();
    } else {
        // getRequestWithoutToken('/forget', forgetPage);
    }
}


function listRoleAuthorizedata(noofpage, noofdata) {
  
    var userRole;
    if ($("#roleId").val() == 0) {
        var user = JSON.parse(localStorage.getItem('user_data'));
        userRole = user.role.roleId;
    }
    else {
        userRole = 0;
    }
    var passdata = {
        "roleId": $("#roleId").val(),
        "roleName": $("#roleId option:selected").text(),
        "organisationId": $("#organisationId").val(),
        "status": 1,
        "startResult": noofpage,
        "maxResult": noofdata,
        "userRoleId": userRole
    }
    // alert("roleId:"+$("#roleId").val()+"
	// organisationId:"+$("#organisationId").val());
    var url = '/api/user/rolelist/' + JSON.stringify(passdata);
    console.log(encodeURIComponent(url));
    getRequest(url, fillRoleAuthorizeTable);
}
function fillRoleAuthorizeTable(data) {
    console.log(data);
    console.log("My Role Authorization" + JSON.stringify(data));
    var flow_sl_cnt = 0;
    var flow_table_head = "";
    var flow_details_record = "<tbody>";
    var footer = "<tfoot><tr><td colspan='10'><ul class=\"pagination\">";
    for (var i = 0; i <= data.serviceResponse.totalSize / 10; i++) {
        footer = footer
            + "<li><a href=\"javascript:void(0);\" onclick=\"listRoleAuthorizedata("
            + (i * 10) + ",10)\">" + (i + 1) + "</a></li>";
    }
    footer = footer + "</ul></td></tr></tfoot>";
    $("#sizeoflist").val(data.length);
    flow_table_head = "<thead class=\"heading\"><tr class=\"bg-blue\"><th>S.No.</th><th>Organisation</th><th>Role Name</th><th>Assign</th></tr></thead>";
    var inputfield = "";
    console.log(data.serviceResponse.AllData);
    for (var i = 0; i < data.serviceResponse.AllData.length; i++) {
        flow_sl_cnt++;
        flow_details_record = flow_details_record
            + "<tr><td>"
            + (i + 1)
            + "</td><td>"
            + data.serviceResponse.AllData[i].organisation.masterName
            + "</td><td> "
            + data.serviceResponse.AllData[i].roleMaster.roleName
            + "</td><td align=\"center\"><a class='btn btn-info' href=\"javascript:void(0);\" onclick=\"onClickfunction('assignedit^"
            + data.serviceResponse.AllData[i].orgRoleId
            + "');\"><i class=\"fa fa-edit\"></i></a></td></tr>";
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
    $("#table_details").DataTable({
    	// "dom": '<"top"i>rt<"bottom"flp><"clear">'
    		"bDestroy" : true
    });
}
function resetField(val) {
    document.getElementById(val).reset();
}



function changePassword() {
    var new_password = $('#new_password').val();
    var conf_password = $('#conf_password').val();
    var old_password = $('#current_password').val();

    var user = JSON.parse(localStorage.getItem('user_data'));
    var username = user.userName.trim();
    var check = true;

    console.log(old_password + ' ** ' + new_password + ' ' + username);
    $(".error").remove();
    if (old_password.length < 1) {
        $('#current_password')
            .after(
            '<font color="red"><span class="error">password is required</span></font>');
        if (check) {
            check = false;
        }
    } else if (validatePassword('current_password') && old_password.length > 7) {
        if (check) {
            check = false;
        }
    }
    if (new_password.length < 1) {
        $('#new_password')
            .after(
            '<font color="red"><span class="error">password is required</span></font>');
        if (check) {
            check = false;
        }
    } else if (new_password == old_password) {
        $('#current_password')
            .after(
            '<font color="red"><span class="error">new password should not be same to old password</span></font>');
        console.log(old_password + ' *iffff* ' + new_password + ' ' + username);
        if (check) {
            check = false;
        }
    } else if (validatePassword('new_password') && new_password.length > 7) {
        if (check) {
            check = false;
        }
    }
    if (conf_password.length < 1) {
        $('#conf_password')
            .after(
            '<font color="red"><span class="error">password is required</span></font>');
        if (check) {
            check = false;
        }
    } else if (new_password != conf_password) {
        $('#conf_password')
            .after(
            '<font color="red"><span class="error">password should be same</span></font>');
        if (check) {
            check = false;
        }
    }
    console.log(old_password + ' **&&& ' + new_password + ' ' + username);

    console.log(old_password + ' *@@@@* ' + new_password + ' ' + username);
    if (check) {
        var data = {
            "newPassword": new_password,
            "confirmPassword": conf_password,
            "oldPassword": old_password,
            "userName": username

        };
        console.log(data);
        postRequest('/api/user/changePassword', JSON.stringify(data),
            changeResponse);
    }
    console.log(old_password + ' **(( ' + new_password + ' ' + username);
}
function changeResponse(data) {
    console.log("My Log Error ---  " + JSON.stringify(data));
    alert(data.serviceResponse.customResponse.responseDescription);
    if (data.serviceResponse.customResponse.responseCode == 200) {
        homeScreen();
    } else {
    }
}

function doalert(checkboxElem, data, id) {
    if (checkboxElem.checked) {
        updateRoleData("1", id);
    } else {
        updateRoleData("0", id);
    }
}

function updateRoleData(checkStatus, id) {
    var user = JSON.parse(localStorage.getItem('user_data'));
    user.userId;
    var form = {
        "roleId": parseInt(id),
        "isActiv": parseInt(checkStatus),
        "status": 1,
        "isDeleted": 0,
        "createdBy": user.userId,
        "updatedBy": user.userId,
        // "remarks":"xyz"
    }
    var data = JSON.stringify(form);
    alert("done " + data);

    postRequest('/api/user/createOrUpdateRole', data, addRole);
}




function addauthorization() {
	
    var truecount = 0;
    var departmentId;
    var roleId;
    var menuId = [];
    var addperm;
    var editperm;
    var viewperm;
    var queryperm;
    var deleteperm;
    var rightsjson = {};
    var rights = [];
    var authId = [];
    $(".menuids").each(function() {
        authId.push($(this).val());
    });
   console.log(authId);
    $(".childMenuId").each(function() {
        menuId.push($(this).val());
    });

    addperm = ($("input[id='addpermisiion']").map(function() {
        if ($(this).prop("checked")) {
            truecount++;
            return 1;
        } else {
            return 0;
        }
    }).get());

    editperm = ($("input[id='editpermisiion']").map(function() {
        if ($(this).prop("checked")) {
            truecount++;
            return 1;
        } else {
            return 0;
        }
    }).get());
    viewperm = ($("input[id='viewpermisiion']").map(function() {
        if ($(this).prop("checked")) {
            truecount++;
            return 1;
        } else {
            return 0;
        }
    }).get());
    queryperm = ($("input[id='querypermisiion']").map(function() {
        if ($(this).prop("checked")) {
            truecount++;
            return 1;
        } else {
            return 0;
        }
    }).get());
    deleteperm = ($("input[id='deletepermisiion']").map(function() {
        if ($(this).prop("checked")) {
            truecount++;
            return 1;
        } else {
            return 0;
        }
    }).get());

    for (var i = 0; i < menuId.length; i++) {
        rightsjson = {
            "addRight": addperm[i],
            "editRight": editperm[i],
            "viewRight": viewperm[i],
            "query": queryperm[i],
            "deleteRight": deleteperm[i],
            "menuID": menuId[i],
            "roleID": {
                "orgRoleId": $("#roleId").val()
            },
            "id": $('#dropdown').children('option').eq(i).val()
        }
        rights.push(rightsjson);
    }

    console.log(JSON.stringify(rights));
    console.log("truecount" + truecount);
    if (truecount == 0) {
        alert("please check atleast one checkbox")
    } else {
        postRequest('/api/user/createRoleAuthorization',
            JSON.stringify(rights), showExecutionStatus);
    }

}

function showExecutionStatus(data) {
    console.log("My Log Error ---  " + JSON.stringify(data));

    if (data.serviceResponse.customResponse.responseCode == 200) {
        showRoleAuthLists();
    } else {
    }
}
function showRoleAuthLists() {

    getRequest('/role-authorise-list', listPage);
}
function forgetUsername() {
    var user_name = $('#user_name').val();
    $(".error").remove();
    if (user_name.length < 1) {
        $('#user_name')
            .after(
            '<font color="red"><span class="error">Email Or Mobile Number is required</span></font>');
        check = false;
    } else {
        var data = {
            "fogetLoginId": user_name
        };
        console.log(data);
        alert(data);
        $
            .ajax({
                'url': '/api/user/sendUserNameAtForgetLoginId',
                'type': 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                "data": JSON.stringify(data),
                'success': function(result) {
                    console.log("My Log Error ---  "
                        + JSON.stringify(result));
                    alert(result.serviceResponse.customResponse.responseDescription);
                    if (result.serviceResponse.customResponse.responseCode == 200) {
                        homeScreen();
                    } else {
                        // getRequestWithoutToken('/forget', forgetPage);
                    }
                },
                'error': function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log("My Log Error ---  " + textStatus);
                    console.log("My Log Error ---  " + errorThrown);
                    console.log("My Log Error ---  "
                        + JSON.stringify(XMLHttpRequest));
                    alert(JSON.stringify(XMLHttpRequest));
                    getRequestWithoutToken('/forget', forgetPage);
                }
            });
    }
}
function hideShowUserFieldForEditView() {
    var department = $('#organisationId').val().trim();
    if (department == 34 || department == 35) { // Minister Office (MoS), Senior
        // Officials of MoS/SDCL)
        $('#lineMinistryDiv').hide();
        $('#departmentDiv').hide();
        $('#stateDiv').hide();
        $('#portDiv').hide();
        $('#assignMiPortDev').hide();
        $('#agencyDiv').hide();
        $('#boardDiv').hide();
        $('#govtAgencyDiv').hide();
        // $('#assignDiv').hide();
        // $('#depDiv').show();
    } else if (department == 36) { // Line Ministry
        // $('#depDiv').hide();
        $('#departmentDiv').hide();
        $('#stateDiv').hide();
        $('#portDiv').hide();
        $('#assignMiPortDev').hide();
        $('#boardDiv').hide();
        $('#agencyDiv').hide();
        $('#govtAgencyDiv').hide();

        $('#lineMinistryDiv').show();
        // $('#assignDiv').show();
    } else if (department == 37) { // Major Port Official
        $('#departmentDiv').hide();
        $('#lineMinistryDiv').hide();
        $('#agencyDiv').hide();
        // $('#depDiv').hide();
        $('#boardDiv').hide();
        $('#govtAgencyDiv').hide();
        $('#assignMiPortDev').hide();

        $('#stateDiv').show();
        $('#portDiv').show();
        // $('#assignDiv').show();
    }
    else if (department == 41) { // Major Port Official
        $('#departmentDiv').hide();
        $('#lineMinistryDiv').hide();
        $('#agencyDiv').hide();
        // $('#depDiv').hide();
        $('#boardDiv').hide();
        $('#govtAgencyDiv').hide();
        $('#portDiv').hide();

        $('#stateDiv').show();
        $('#assignMiPortDev').show();
        // $('#assignDiv').show();
    } else if (department == 38) { // Maritime Board Official
        $('#departmentDiv').hide();
        $('#lineMinistryDiv').hide();
        $('#agencyDiv').hide();
        // $('#depDiv').hide();
        $('#portDiv').hide();
        $('#assignMiPortDev').hide();
        $('#govtAgencyDiv').hide();

        $('#stateDiv').show();
        $('#boardDiv').show();
        // $('#assignDiv').show();
    } else if (department == 39) { // State official
        // $('#depDiv').hide();
        $('#lineMinistryDiv').hide();
        $('#portDiv').hide();
        $('#assignMiPortDev').hide();
        $('#agencyDiv').hide();
        $('#boardDiv').hide();
        $('#govtAgencyDiv').hide();

        $('#stateDiv').show();
        $('#departmentDiv').show();
        // $('#assignDiv').show();
    } else if (department == 40) { // Implementation Agency User
        // $('#depDiv').hide();
        $('#lineMinistryDiv').hide();
        $('#stateDiv').hide();
        $('#portDiv').hide();
        $('#assignMiPortDev').hide();
        $('#departmentDiv').hide();
        $('#boardDiv').hide();
        $('#govtAgencyDiv').hide();
        // $('#assignDiv').show();
        $('#stateDiv').show();
        $('#agencyDiv').show();
    } else { // For All Show
        console.log('else coming');
        $('#depDiv').show();
        $('#lineMinistryDiv').show();
        $('#departmentDiv').show();
        $('#stateDiv').show();
        $('#portDiv').show();
        $('#assignMiPortDev').show();
        $('#agencyDiv').show();
        $('#boardDiv').show();
        $('#govtAgencyDiv').show();
        // $('#assignDiv').show();
    }
}

function hideShowUserField() {
    var department = $('#organisationId').val().trim();
    if (department == 34 || department == 35) { // Minister Office (MoS), Senior
        // Officials of MoS/SDCL)
        hideRefresgAllFieldForAddUser();
        // $('#assignDiv').hide();
        // $('#depDiv').show();
    } else if (department == 36) { // Line Ministry
        // $('#depDiv').hide();
        hideRefresgAllFieldForAddUser();

        $('#lineMinistryDiv').show();
        // $('#assignDiv').show();
    } else if (department == 37) { // Major Port Official
        hideRefresgAllFieldForAddUser();

        $('#stateDiv').show();
        $('#portDiv').show();
        // $('#assignDiv').show();
    }
    else if (department == 41) { // Major Port Official
        hideRefresgAllFieldForAddUser();

        $('#stateDiv').show();
        $('#assignMiPortDev').show();
        // $('#assignDiv').show();
    } else if (department == 38) { // Maritime Board Official
        hideRefresgAllFieldForAddUser();

        $('#stateDiv').show();
        $('#boardDiv').show();
        // $('#assignDiv').show();
    } else if (department == 39) { // State official
        // $('#depDiv').hide();
        hideRefresgAllFieldForAddUser();

        $('#stateDiv').show();
        $('#departmentDiv').show();
        // $('#assignDiv').show();
    } else if (department == 40) { // Implementation Agency User
        hideRefresgAllFieldForAddUser();
        // $('#assignDiv').show();
        $('#stateDiv').show();
        $('#agencyDiv').show();
    } else { // For All Show
        console.log('else coming');
        showRefreshAllFieldForAddUser();
        /*
		 * $('#depDiv').show(); $('#lineMinistryDiv').show();
		 * $('#departmentDiv').show(); $('#stateDiv').show();
		 * $('#portDiv').show(); $('#assignMiPortDev').show();
		 * $('#agencyDiv').show(); $('#boardDiv').show();
		 * $('#govtAgencyDiv').show();
		 */
        // $('#assignDiv').show();
    }
}


function showRefreshAllFieldForAddUser(){    
        $('#depDiv').show();
        $('#lineMinistryDiv').show();
        $('#departmentDiv').show();
        $('#stateDiv').show();
        $('#portDiv').show();
        $('#assignMiPortDev').show();
        $('#agencyDiv').show();
        $('#boardDiv').show();
        $('#govtAgencyDiv').show();
    
        setFirstvalueForAddUser();
    }
function hideRefresgAllFieldForAddUser(){    
        // $('#depDiv').hide();
        $('#lineMinistryDiv').hide();
        $('#departmentDiv').hide();
        $('#stateDiv').hide();
        $('#portDiv').hide();
        $('#assignMiPortDev').hide();
        $('#agencyDiv').hide();
        $('#boardDiv').hide();
       // $('#govtAgencyDiv').hide();
         setFirstvalueForAddUser();
    }
function setFirstvalueForAddUser(){
        $("select#roleId").prop('selectedIndex', 0);
        $("select#lineMinistryId").prop('selectedIndex', 0);
        $("select#departmentId").prop('selectedIndex', 0);
        $("select#stateId").prop('selectedIndex', 0);
        $("select#portId").prop('selectedIndex', 0);
        $("select#minnorPortId").prop('selectedIndex', -1);
        $("select#agencyId").prop('selectedIndex', 0);
        $("select#boardId").prop('selectedIndex', 0);
    
        $("#designation").val("");
        $("#userName").val("");
        $("#name").val("");
        $("#mobile_number").val("");
        $("#email").val("");
         
        // $("select#govtAgencyDiv").prop('selectedIndex', 0);
    }
function activeorDeactiveUser(checkboxElem, data, id) {
    var r = confirm("The selected user will be deactivated. Are you Sure?");
    if (r == true) {
        var checkStatus;
        if (checkboxElem.checked) {
            checkStatus = 1;
        } else {
            checkStatus = 0;
        }
        var user = JSON.parse(localStorage.getItem('user_data'));
        var form = {
            "userId": parseInt(id),
            "isActive": parseInt(checkStatus),
            "createdBy": user.userId,
            "updatedBy": user.userId
        }
        var data = JSON.stringify(form);
        console.log("done " + data);
        postRequest('/api/user/activeorDeactiveUserDetail', data,
            showStatusUser);
    } else {
        alert('Cancel Successfully');
        if (checkboxElem.checked) {
            checkboxElem.checked = false;
        } else {
            checkboxElem.checked = true;
        }
    }
}
function getRoleDropDownByDepartment(depid, id) {
    var user = JSON.parse(localStorage.getItem('user_data'));
    var param = document.getElementById(depid);
    console.log(param.value + '  *** ' + param.id);
    if (param.value != 0) {
        getRequest('/api/master/fetchRoleByOrganisation/' + param.value + '/'
            + user.role.roleId, function fillRoleByDepartment(data) {
                console.log(id + ' Role Data :- ' + JSON.stringify(data));
                $("#" + id).empty();
                $("#" + id).append($("<option />").val(0).text('Select Role'));
                $.each(data.serviceResponse.roleList, function(i, data) {
                    $("#" + id).append(
                        $("<option />").val(data.roleId).text(data.roleName));
                });
            });
    } else {
        getRequest('/api/master/fetchRoleByOrganisation/' + param.value + '/'
            + user.role.roleId, function fillRoleByDepartment(data) {
                console.log(id + ' Role Data :- ' + JSON.stringify(data));
                $("#" + id).empty();
                $("#" + id).append($("<option />").val(0).text('Select Role'));
                $.each(data.serviceResponse.roleList, function(i, data) {
                    $("#" + id).append(
                        $("<option />").val(data.roleId).text(data.roleName));
                });
            });
    }

}
function fillSubDepartmentMaritimeBoardByState(stateid, departmentId,
    maritimeboardid, organisationId, portid,minnerPortId,agncyId) {
    var user = JSON.parse(localStorage.getItem('user_data'));
    var param = document.getElementById(stateid);
    console.log(param.value + '  *** ' + param.id);
    var depval = $('#' + organisationId).val();
    console.log('department id ' + depval);
    if (depval == 38) { // Maritime Board Official

        getRequest('/api/master/fetchMaritimeBoardByState/' + param.value,
            function fillMaritimeBoard(data) {
                console.log(maritimeboardid + ' Maritime Data :- '
                    + JSON.stringify(data));
                $("#" + maritimeboardid).empty();
                $("#" + maritimeboardid).append(
                    $("<option />").val(0)
                        .text('Select Maritime Board'));
                $.each(data.serviceResponse.maritimeList,
                    function(i, data) {
                        $("#" + maritimeboardid).append(
                            $("<option />").val(
                                data.maritimeBoardId).text(
                                data.maritimeBoardName));
                    });
            });
    } else if (depval == 39) { // State official --> fill Sub Department

        getRequest('/api/master/fetchDepartmentByState/' + param.value,
            function fillSubDepartment(data) {
                console.log(departmentId + ' Sub-Department Data :- '
                    + JSON.stringify(data));
                $("#" + departmentId).empty();
                $("#" + departmentId).append(
                    $("<option />").val(0).text('Select Department'));
                $.each(data.serviceResponse.departmentList, function(i,
                    data) {
                    $("#" + departmentId).append(
                        $("<option />").val(data.departmentId).text(
                            data.departmentName));
                });
                if(user.role.roleId != -1 && user.departmentId.departmentId!=-1 && user.state.masterId!=-1){
                    $("#" + departmentId).val(user.departmentId.departmentId);
                    document.getElementById(departmentId).disabled = true;
                  }
            });
                    
    } else if (depval == 37) { // Major Port Official

        getRequest('/api/master/fetchPortByState/' + param.value,
            function fillSubDepartment(data) {
                console.log(departmentId + ' Major port Data :- '
                    + JSON.stringify(data));
                $("#" + portid).empty();
                $("#" + portid).append(
                    $("<option />").val(0).text('Select Major Port'));
                $.each(data.serviceResponse.portList, function(i, data) {
                    $("#" + portid).append(
                        $("<option />").val(data.portId).text(
                            data.portName));
                });
            });
    }

    else if (depval == 41) { // Maritime Board Official
        getRequest('/api/master/fetchMinnorPortsByState/' + param.value,
            function fillMinnorPorts(data) {
                console.log(minnerPortId + ' MinnerPorts Data :- '
                    + JSON.stringify(data));
                $("#" + minnerPortId).empty();
                $("#" + minnerPortId).append(
                    $("<option />").val(0)
                        .text('Select Minner Ports'));
                $.each(data.serviceResponse.minnerPorts,
                    function(i, data) {
                        $("#" + minnerPortId).append(
                            $("<option />").val(
                                data.masterId).text(
                                data.masterName));
                    });
            });
        // $("#userName").val
    }
        
        else if (depval == 40) { // Maritime Board Official
        getRequest('/api/master/fetchAgencyByState/' + param.value,
            function fillAgency(data) {
                console.log(agncyId + ' Agency Data :- '
                    + JSON.stringify(data));
                $("#" + agncyId).empty();
                $("#" + agncyId).append(
                    $("<option />").val(0)
                        .text('Select Agency'));
                $.each(data.serviceResponse.agency,
                    function(i, data) {
                        $("#" + agncyId).append(
                            $("<option />").val(
                                data.masterId).text(
                                data.masterName));
                    });
            });
        // $("#userName").val
    }
    else {
        getRequest('/api/master/fetchMaritimeBoardByState/' + param.value,
            function fillMaritimeBoard(data) {
                console.log(maritimeboardid + ' Maritime Data :- '
                    + JSON.stringify(data));
                $("#" + maritimeboardid).empty();
                $("#" + maritimeboardid).append(
                    $("<option />").val(0)
                        .text('Select Maritime Board'));
                $.each(data.serviceResponse.maritimeList,
                    function(i, data) {
                        $("#" + maritimeboardid).append(
                            $("<option />").val(
                                data.maritimeBoardId).text(
                                data.maritimeBoardName));
                    });
            });
        getRequest('/api/master/fetchPortByState/' + param.value,
            function fillSubDepartment(data) {
                console.log(departmentId + ' Major port Data :- '
                    + JSON.stringify(data));
                $("#" + portid).empty();
                $("#" + portid).append(
                    $("<option />").val(0).text('Select Major Port'));
                $.each(data.serviceResponse.portList, function(i, data) {
                    $("#" + portid).append(
                        $("<option />").val(data.portId).text(
                            data.portName));
                });
            });
        getRequest('/api/master/fetchDepartmentByState/' + param.value,
            function fillSubDepartment(data) {
                console.log(departmentId + ' Sub-Department Data :- '
                    + JSON.stringify(data));
                $("#" + departmentId).empty();
                $("#" + departmentId).append(
                    $("<option />").val(0).text('Select Department'));
                $.each(data.serviceResponse.departmentList, function(i,
                    data) {
                    $("#" + departmentId).append(
                        $("<option />").val(data.departmentId).text(
                            data.departmentName));
                });
            });
        getRequest('/api/master/fetchMinnorPortsByState/' + param.value,
            function fillMinnorPorts(data) {
                console.log(minnerPortId + ' MinnerPorts Data :- '
                    + JSON.stringify(data));
                $("#" + minnerPortId).empty();
                $("#" + minnerPortId).append(
                    $("<option />").val(0)
                        .text('Select Minner Ports'));
                $.each(data.serviceResponse.minnerPorts,
                    function(i, data) {
                        $("#" + minnerPortId).append(
                            $("<option />").val(
                                data.masterId).text(
                                data.masterName));
                    });
            });
        
        getRequest('/api/master/fetchAgencyByState/' + param.value,
            function fillAgency(data) {
                console.log(agncyId + ' Agency Data :- '
                    + JSON.stringify(data));
                $("#" + agncyId).empty();
                $("#" + agncyId).append(
                    $("<option />").val(0)
                        .text('Select Agency'));
                $.each(data.serviceResponse.agency,
                    function(i, data) {
                        $("#" + agncyId).append(
                            $("<option />").val(
                                data.masterId).text(
                                data.masterName));
                    });
            });
    }
}
function getLoginId() {
    var department = $("#organisationId").val();
    var url = '';
    $('#userName').val('');
    if (department == 34 || department == 35) { // Minister Office (MoS), Senior
        // Officials of MoS/SDCL)
        url = {
            "organisationId": department
        }
    } else if (department == 36 && $('#lineMinistryId').val() != 0) { // Line
        // Ministry
        url = {
            "organisationId": department,
            "lineMinistryId": $("#lineMinistryId").val()
        }
    } else if (department == 37 && $('#portId').val() != 0) { // Major Port
        // Official
        url = {
            "organisationId": department,
            "portId": $("#portId").val()
        }
    } else if (department == 38 && $('#boardId').val() != 0) { // Maritime
        // Board
        // Official
        url = {
            "organisationId": department,
            "maritimeBoardId": $("#boardId").val(),
        }
    } else if (department == 39 && $('#stateId').val() != 0) { // State
        // official
        url = {
            "organisationId": department,
            "stateId": $("#stateId").val()
        }
    } else if (department == 40 && $('#agencyId').val() != 0) { // Implementation
        // Agency User
        url = {
            "organisationId": department,
            "agencyId": $("#agencyId").val()
        }
    }

    else if (department == 41 && $('#stateId').val() != 0) { // Implementation
        // Agency User
        url = {
            "organisationId": department,
            "minnorPortId": 8
        }
    }

    if (url != '') {
        getRequest('/api/user/generateLoginId/' + JSON.stringify(url),
            function fillLoginId(data) {
                console.log(' Login Id Data :- ' + JSON.stringify(data));
                $("#userName").val(data.serviceResponse.loginid);
            });
    }
}

function downloadUserExcel(noofpage, noofdata) {
    var user = JSON.parse(localStorage.getItem('user_data'));
    var passdata = {
        "organisationId": $("#organisationId").val(),
        "roleId": $("#role").val(),
        "stateId": $("#state").val(),
        "lineMinistryId": $("#lineMinistry").val(),
        "portId": $("#port").val(),
        "maritimeBoardId": $("#maritimeboard").val(),
        "agencyId": $("#agency").val(),
        "departmentId": $("#departmentId").val(),
        "status": $("#status").val(),
        "startResult": noofpage,
        "maxResult": noofdata,
        "searchByUserId": user.userId
    }
    var url = '/api/user/downloadUserExcelFile/' + JSON.stringify(passdata);
    console.log(encodeURIComponent(url));
    getRequest(url, function downloadExcel(data) {
        console.log(' Response Data :- ' + JSON.stringify(data));
        if (data.serviceResponse.customResponse.responseCode == 200) {
            var filePath = data.serviceResponse.path;
            $('<form></form>').attr('action', filePath).appendTo('body')
                .submit().remove();
        } else {
            alert(data.serviceResponse.customResponse.responseDescription);
        }
    });
}
function downloadUserPdf(noofpage, noofdata) {
    var user = JSON.parse(localStorage.getItem('user_data'));
    var passdata = {
        "organisationId": $("#organisationId").val(),
        "roleId": $("#role").val(),
        "stateId": $("#state").val(),
        "lineMinistryId": $("#lineMinistry").val(),
        "portId": $("#port").val(),
        "maritimeBoardId": $("#maritimeboard").val(),
        "agencyId": $("#agency").val(),
        "departmentId": $("#departmentId").val(),
        "status": $("#status").val(),
        "startResult": noofpage,
        "maxResult": noofdata,
        "searchByUserId": user.userId
    }
    var url = '/api/user/downloadUserPdfFile/' + JSON.stringify(passdata);
    console.log(encodeURIComponent(url));
    getRequest(url, function downloadPdf(data) {
        console.log(' Response Pdf Data :- ' + JSON.stringify(data));
        if (data.serviceResponse.customResponse.responseCode == 200) {
            var filePath = data.serviceResponse.path;
            alert(filePath);
            window.open(filePath);
        } else {
            alert(data.serviceResponse.customResponse.responseDescription);
        }
    });
}

function populateparentChildMenu(roleid) {

    console.log('inside parent child...');
    var step = 0;

    getRequest(
            '/api/master/menu-list',
            function createtable(data) {

                console.log(JSON.stringify(data));
                console
                        .log("childnames are..."
                                + JSON
                                        .stringify(data.serviceResponse.parentandchildmenuList[0].childMenu[0].childName));

                var num = 1;
                console.log("total length"
                        + data.serviceResponse.parentandchildmenuList.length);
                for (var i = 0; i < data.serviceResponse.parentandchildmenuList.length; i++) {

                    $("#mainhead")
                            .append(
                                    ' <tr class=treegrid-'
                                            + (num++)
                                            + '> <td>'
                                            + data.serviceResponse.parentandchildmenuList[i].menuName
                                            + '</td><td class="hidden-xs"> </td>  <td class="hidden-xs hidden-sm"> </td>  <td class="hidden-xs hidden-sm hidden-md"> </td>'
                                            + '<td></td> <td></td>  <td></td> </tr>');
                    console
                            .log(i
                                    + 'child val:- '
                                    + JSON
                                            .stringify(data.serviceResponse.parentandchildmenuList[i]));
                    var par = num - 1;
                    var child = '';

                    for (var j = 0; j < data.serviceResponse.parentandchildmenuList[i].childMenu.length; j++) {
                        var value = $('#dropdown option').eq(step).val();
                        var idclass = 'treegrid-' + (num++)
                                + ' treegrid-parent-' + par;
                        console.log(idclass);
                        var statusAdd = ''
                        var statusEdit = ''
                        var statusView = ''
                        var statusApprove = ''
                        var statusReject = ''
                        if (data.serviceResponse.parentandchildmenuList[i].childMenu[j].hasAdd == 0) {
                            statusAdd = 'style=display:none';
                        }
                        if (data.serviceResponse.parentandchildmenuList[i].childMenu[j].hasEdit == 0) {
                            statusEdit = 'style=display:none';
                        }
                        if (data.serviceResponse.parentandchildmenuList[i].childMenu[j].hasView == 0) {
                            statusView = 'style=display:none';
                        }
                        if (data.serviceResponse.parentandchildmenuList[i].childMenu[j].hasApprove == 0) {
                            statusApprove = 'style=display:none';
                        }
                        if (data.serviceResponse.parentandchildmenuList[i].childMenu[j].hasReject == 0) {
                            statusReject = 'style=display:none';
                        }
                        child = child
                                + '<tr class="'
                                + idclass
                                + '"> <td><input type="hidden" class="childMenuId" value='
                                + data.serviceResponse.parentandchildmenuList[i].childMenu[j].childMenuId
                                + '></td>  <td class="childname">'
                                + data.serviceResponse.parentandchildmenuList[i].childMenu[j].childName
                                + '</td>'
                                + '<td style="display:none">'
                                + '<input type="text" value='
                                + $('#dropdown').children('option').eq(step++)
                                        .val()
                                + ' class="rowids">'
                                + '</td>'
                                + '<td class="lasttd"><input type="checkbox" id="addpermisiion" class="addpermisiion'
                                + step
                                + '" '
                                + statusAdd
                                + '></td>  <td><input type="checkbox" id="editpermisiion" class="editpermisiion'
                                + step
                                + '"  '
                                + statusEdit
                                + '></td>  <td><input type="checkbox" id="viewpermisiion" class="viewpermisiion'
                                + step
                                + '" '
                                + statusView
                                + '></td>  <td><input type="checkbox" id="querypermisiion" class="querypermisiion'
                                + step
                                + '" '
                                + statusApprove
                                + '></td> <td><input type="checkbox" id="deletepermisiion" class="deletepermisiion'
                                + step + '" ' + statusReject + '></td>';

                    }

                    $('.treegrid-' + par).after(child);
                }

            });

    setTimeout(function() {
        fillchecks(roleid)
    }, 500);

}
function sendOtp() {

    getRequest("/api/user/generateOtp", function showAlerts() {

        alert("otp sent .... please check");

    });
}

function enableDisableUserField() {
    var department = $('#organisationId').val().trim();
    if (department == 34 || department == 35) { // Minister Office (MoS), Senior
        document.getElementById("role").disabled = false;
        document.getElementById("state").disabled = true;
        document.getElementById("lineMinistry").disabled = true;
        document.getElementById("port").disabled = true;
        document.getElementById("minnorPortId").disabled = true;
        document.getElementById("maritimeboard").disabled = true;
        document.getElementById("agency").disabled = true;
        document.getElementById("departmentId").disabled = true;

    } else if (department == 36) { // Line Ministry
        // $('#depDiv').hide();
        document.getElementById("role").disabled = false;
        document.getElementById("state").disabled = true;
        document.getElementById("lineMinistry").disabled = false;
        document.getElementById("port").disabled = true;
        document.getElementById("maritimeboard").disabled = true;
        document.getElementById("agency").disabled = true;
        document.getElementById("departmentId").disabled = true;
        document.getElementById("minnorPortId").disabled = true;
    } else if (department == 37) { // Major Port Official

        document.getElementById("role").disabled = false;
        document.getElementById("state").disabled = false;
        document.getElementById("lineMinistry").disabled = true;
        document.getElementById("port").disabled = false;
        document.getElementById("maritimeboard").disabled = true;
        document.getElementById("agency").disabled = true;
        document.getElementById("departmentId").disabled = true;
        document.getElementById("minnorPortId").disabled = true;
    } else if (department == 38) { // Maritime Board Official
        document.getElementById("role").disabled = false;
        document.getElementById("state").disabled = false;
        document.getElementById("lineMinistry").disabled = true;
        document.getElementById("port").disabled = true;
        document.getElementById("maritimeboard").disabled = false;
        document.getElementById("agency").disabled = true;
        document.getElementById("departmentId").disabled = true;
        document.getElementById("minnorPortId").disabled = true;
    } else if (department == 39) { // State official
        // $('#depDiv').hide();
        document.getElementById("role").disabled = false;
        document.getElementById("state").disabled = false;
        document.getElementById("lineMinistry").disabled = true;
        document.getElementById("port").disabled = true;
        document.getElementById("maritimeboard").disabled = true;
        document.getElementById("agency").disabled = true;
        document.getElementById("departmentId").disabled = false;
        document.getElementById("minnorPortId").disabled = true;
    } else if (department == 40) { // Implementation Agency User
        // $('#depDiv').hide();
        document.getElementById("role").disabled = false;
        document.getElementById("state").disabled = false;
        document.getElementById("lineMinistry").disabled = true;
        document.getElementById("port").disabled = true;
        document.getElementById("maritimeboard").disabled = true;
        document.getElementById("agency").disabled = false;
        document.getElementById("departmentId").disabled = true;
        document.getElementById("minnorPortId").disabled = true;
    } 
      else if (department == 41) { // Implementation Agency User
        // $('#depDiv').hide();
        document.getElementById("role").disabled = false;
        document.getElementById("state").disabled = false;
        document.getElementById("lineMinistry").disabled = true;
        document.getElementById("port").disabled = true;
        document.getElementById("maritimeboard").disabled = true;
        document.getElementById("agency").disabled = true;
        document.getElementById("departmentId").disabled = true;
        document.getElementById("minnorPortId").disabled = false;
    }   
        else { // For All Show
        console.log('else coming');
        document.getElementById("role").disabled = false;
        document.getElementById("state").disabled = false;
        document.getElementById("lineMinistry").disabled = false;
        document.getElementById("port").disabled = false;
        document.getElementById("maritimeboard").disabled = false;
        document.getElementById("agency").disabled = false;
        document.getElementById("departmentId").disabled = false;
        document.getElementById("minnorPortId").disabled = false;
    }
}

function setUrserListFieldWithoutOrg(depid, departmentId, maritimeboard, port,
    lineMinistry, agency, state,status,minorPId) {
    var param = document.getElementById(depid);
    var user = JSON.parse(localStorage.getItem('user_data'));
    if(user.role.roleId != -1){
            if(user.lineMinistryId.masterId!=-1){
                $('#lineMinistry').val(user.lineMinistryId.masterId);
                document.getElementById("lineMinistry").disabled = true;
            }
       
        if(user.departmentId.departmentId!=-1 && user.state.masterId!=-1){
                $("#"+state).val(user.state.masterId);
                document.getElementById(state).disabled = true;
           
                $("#"+departmentId).val(user.departmentId.departmentId);
                document.getElementById(departmentId).disabled = true;
            }
    }
    else{
    if (param.value == 0) {
        getRequest(
            '/api/master/fetchAllPortsAndMBoardAndDep',
            function fillRoleByDepartment(data) {
                console.log('Data :- ' + JSON.stringify(data));
                $("#" + departmentId).empty();
                $("#" + departmentId).append(
                    $("<option />").val(0).text('Select Department'));
                $.each(data.serviceResponse.departmentList, function(i,
                    data) {
                    $("#" + departmentId).append(
                        $("<option />").val(data.departmentId).text(
                            data.departmentName));
                });

                $("#" + maritimeboard).empty();
                $("#" + maritimeboard).append(
                    $("<option />").val(0)
                        .text('Select Maritime Board'));
                $.each(data.serviceResponse.mBoard, function(i, data) {
                    $("#" + maritimeboard).append(
                        $("<option />").val(data.maritimeBoardId).text(
                            data.maritimeBoardName));
                });

                $("#" + port).empty();
                $("#" + port).append(
                    $("<option />").val(0).text('Select port'));
                $.each(data.serviceResponse.portList, function(i, data) {
                    $("#" + port).append(
                        $("<option />").val(data.portId).text(
                            data.portName));
                });

                $("#" + agency).empty();
                $("#" + agency).append(
                    $("<option />").val(0).text('Select Agency'));
                $.each(data.serviceResponse.agency, function(i, data) {
                    $("#" + agency).append(
                        $("<option />").val(data.masterId).text(
                            data.masterName));
                });

                $("#" + state).empty();
                $("#" + state).append(
                    $("<option />").val(0).text('Select State'));
                $.each(data.serviceResponse.states, function(i, data) {
                    $("#" + state).append(
                        $("<option />").val(data.masterId).text(
                            data.masterName));
                });

                $("#" + lineMinistry).empty();
                $("#" + lineMinistry).append(
                    $("<option />").val(0).text('Select LineMinistry'));
                $.each(data.serviceResponse.lineMinistryMaster, function(i,
                    data) {
                    $("#" + lineMinistry).append(
                        $("<option />").val(data.masterId).text(
                            data.masterName));
                });
                
                $("#" + minorPId).empty();
                $("#" + lineMinistry).append(
                $("<option />").val(0).text('Select Minnor Ports'));
                $.each(data.serviceResponse.miPorts, function(i,
                    data) {
                    $("#" + minorPId).append(
                        $("<option />").val(data.masterId).text(
                            data.masterName));
                });

                $("#" + status).empty();
                $("#" + status).append(
                    $("<option />").val(2).text('Select Status'));
                $("#" + status).append(
                    $("<option />").val(1).text('Active'));
                $("#" + status).append(
                    $("<option />").val(0).text('InActive'));

            });
        
    } else {
        $("#" + departmentId).empty();
        $("#" + departmentId).append(
            $("<option />").val(0).text('Select Department'));

        $("#" + maritimeboard).empty();
        $("#" + maritimeboard).append(
            $("<option />").val(0).text('Select Maritime Board'));

        $("#" + port).empty();
        $("#" + port).append($("<option />").val(0).text('Select Port'));

        $("#" + agency).empty();
        $("#" + agency).append($("<option />").val(0).text('Select Agency'));

        $("#" + state).empty();
        $("#" + state).append($("<option />").val(0).text('Select State'));

        $("#" + lineMinistry).empty();
        $("#" + lineMinistry).append(
            $("<option />").val(0).text('Select LineMinistry'));
        
        $("#" + minorPId).empty();
        $("#" + minorPId).append(
                $("<option />").val(0).text('Select Minnor Ports'));
        getRequest('/api/master/fetchAllPortsAndMBoardAndDep',
            function fillRoleByDepartment(data) {
                if (param.value == 34 || param.value == 35) { // Minister
																// Office (MoS),
																// Senior

                } else if (param.value == 36) { // Line Ministry
                    $("#" + lineMinistry).empty();
                    $("#" + lineMinistry).append(
                        $("<option />").val(0).text(
                            'Select LineMinistry'));
                    $.each(data.serviceResponse.lineMinistryMaster,
                        function(i, data) {
                            $("#" + lineMinistry).append(
                                $("<option />").val(data.masterId)
                                    .text(data.masterName));
                        });
                } else if (param.value == 37) { // Major Port Official
                    $("#" + state).empty();
                    $("#" + state).append(
                        $("<option />").val(0).text('Select State'));
                    $.each(data.serviceResponse.states, function(i, data) {
                        $("#" + state).append(
                            $("<option />").val(data.masterId).text(
                                data.masterName));
                    });

                    $("#" + port).empty();
                    $("#" + port).append(
                        $("<option />").val(0)
                            .text('Select Major Port'));
                    $.each(data.serviceResponse.portList,
                        function(i, data) {
                            $("#" + port).append(
                                $("<option />").val(data.portId)
                                    .text(data.portName));
                        });

                } else if (param.value == 38) { // Maritime Board Official
                    $("#" + state).empty();
                    $("#" + state).append(
                        $("<option />").val(0).text('Select State'));
                    $.each(data.serviceResponse.states, function(i, data) {
                        $("#" + state).append(
                            $("<option />").val(data.masterId).text(
                                data.masterName));
                    });

                    $("#" + maritimeboard).empty();
                    $("#" + maritimeboard).append(
                        $("<option />").val(0).text(
                            'Select Maritime Board'));
                    $.each(data.serviceResponse.mBoard, function(i, data) {
                        $("#" + maritimeboard).append(
                            $("<option />").val(data.maritimeBoardId)
                                .text(data.maritimeBoardName));
                    });
                } else if (param.value == 39) { // State official
                    $("#" + state).empty();
                    $("#" + state).append(
                        $("<option />").val(0).text('Select State'));
                    $.each(data.serviceResponse.states, function(i, data) {
                        $("#" + state).append(
                            $("<option />").val(data.masterId).text(
                                data.masterName));
                    });

                    $("#" + departmentId).empty();
                    $("#" + departmentId).append(
                        $("<option />").val(0)
                            .text('Select Department'));
                    $.each(data.serviceResponse.departmentList, function(i,
                        data) {
                        $("#" + departmentId).append(
                            $("<option />").val(data.departmentId)
                                .text(data.departmentName));
                    });
                } else if (param.value == 40) { // Implementation Agency User
                    // $('#depDiv').hide();
                    $("#" + state).empty();
                    $("#" + state).append(
                        $("<option />").val(0).text('Select State'));
                    $.each(data.serviceResponse.states, function(i, data) {
                        $("#" + state).append(
                            $("<option />").val(data.masterId).text(
                                data.masterName));
                    });

                    $("#" + agency).empty();
                    $("#" + agency).append(
                        $("<option />").val(0).text('Select Agency'));
                    $.each(data.serviceResponse.agency, function(i, data) {
                        $("#" + agency).append(
                            $("<option />").val(data.masterId).text(
                                data.masterName));
                    });
                } 
                 else if (param.value == 41) { // Implementation Agency User
                    // $('#depDiv').hide();
                    $("#" + state).empty();
                    $("#" + state).append(
                        $("<option />").val(0).text('Select State'));
                    $.each(data.serviceResponse.states, function(i, data) {
                        $("#" + state).append(
                            $("<option />").val(data.masterId).text(
                                data.masterName));
                    });

                    $("#" + minorPId).empty();
                    $("#" + minorPId).append(
                $("<option />").val(0).text('Select Minnor Ports'));
                    $.each(data.serviceResponse.miPorts, function(i, data) {
                        $("#" + minorPId).append(
                            $("<option />").val(data.masterId).text(
                                data.masterName));
                    });
                }  
                    else { // For All Show
                }
            });
        $("#" + status).empty();
        $("#" + status).append($("<option />").val(2).text('Select Status'));
        $("#" + status).append($("<option />").val(1).text('Active'));
        $("#" + status).append($("<option />").val(0).text('InActive'));
    }
}
 }
function resetPasswordAdmin() {
    var user_name = $('#userName').val();
    var newpassword = $('#newpassword').val();
    var confpassword = $('#confPassword').val();
    var mobilenumber = $('#phoneNumber').val();
    var email = $('#email').val();

    console.log(newpassword + ' ** ' + confpassword + ' ' + user_name);
    $(".error").remove();
    if (newpassword.length < 1) {
        $('#newpassword')
            .after(
            '<font color="red"><span class="error">password is required</span></font>');
        check = false;
    } else if (newpassword != confpassword) {
        $('#confPassword')
            .after(
            '<font color="red"><span class="error">password should be same</span></font>');
        check = false;
    } else {
        var data = {
            "userName": user_name,
            "newPassword": newpassword,
            "confirmPassword": confpassword,
            "phoneNumber": mobilenumber,
            "email": email
        };
        console.log(data);
        postRequest(
            '/api/user/resetPasswordBySuperAdmin',
            JSON.stringify(data),
            function(result) {
                console.log("My Log Error ---  " + JSON.stringify(result));
                alert(result.serviceResponse.customResponse.responseDescription);
                if (result.serviceResponse.customResponse.responseCode == 200) {

                } else {
                    // getRequestWithoutToken('/forget', forgetPage);
                }
            });

    }
}

function fillchecks(roleid) {

    getRequest('/api/user/findauthdata/' + roleid, function fillExistingTable(
        mydata) {
        var addrights = [];
        var editrights = [];
        var viewrights = [];
        var queryrights = [];
        var deleterights = [];
        var approveRights = [];
        var obj = jQuery.parseJSON(mydata);
        $.each(obj, function(key, value) {
            addrights.push(value.addRight);
            editrights.push(value.editRight);
            viewrights.push(value.viewRight);
            queryrights.push(value.query);
            deleterights.push(value.deleteRight);
            approveRights.push(value.approveRight);

        });

        console.log("add-rights" + addrights);
        for (var i = 0; i < addrights.length; i++) {
            if (addrights[i] == 1) {
                $('.addpermisiion' + (i + 1) + '').prop('checked', 'true');

            }
            if (editrights[i] == 1) {
                $('.editpermisiion' + (i + 1) + '').prop('checked', 'true');
            }

            if (viewrights[i] == 1) {
                $('.viewpermisiion' + (i + 1) + '').prop('checked', 'true');
            }
            if (queryrights[i] == 1) {
                $('.querypermisiion' + (i + 1) + '').prop('checked', 'true');
            }
            if (deleterights[i] == 1) {
                $('.deletepermisiion' + (i + 1) + '').prop('checked', 'true');
            }
            if (approveRights[i] == 1) {
                $('.addpermisiion' + (i + 1) + '').prop('checked', 'true');
            }

        }

    });
}

/* For AssignProject (ck) */
function listProjectAssignedUser(noofpage, noofdata) {
    var user = JSON.parse(localStorage.getItem('user_data'));
    var passdata = {
        "organisationId": $("#assignPListOrgId").val(),
        "roleId": $("#assignPListRole").val(),
        "startResult": noofpage,
        "maxResult": noofdata,
        "searchByUserId": user.userId,
        "status": 1
    }
    var url = '/api/user/userlist/' + JSON.stringify(passdata);
    console.log(encodeURIComponent(url));
    getRequest(url, fillUserForAssignedProject);
}
/* For AssignProject(ck) */
function fillUserForAssignedProject(data) {
    console.log("data: " + data);
    var user = JSON.parse(localStorage.getItem('user_data'));
    console.log("My " + JSON.stringify(data));
    var flow_sl_cnt = 0;
    var flow_table_head = "";
    var flow_details_record = "<tbody>";
    var footer = "<tfoot><tr><td colspan='6'><ul class=\"pagination\">";
    for (var i = 0; i <= data.serviceResponse.totalSize / 10; i++) {
        footer = footer
            + "<li><a href=\"javascript:void(0);\" onclick=\"listUserData("
            + (i * 10) + ",10)\">" + (i + 1) + "</a></li>";
    }
    footer = footer + "</ul></td></tr></tfoot>";
    if (user.role.roleId == -1) {
        flow_table_head = "<thead class=\"heading\"><tr class=\"bg-blue\"><th>S.No.</th><th>User Name</th><th>Organization</th><th>Role</th><th class=\"width-th-100 text-center\">Assigned Project</th><th class=\"width-th-100 text-center\">Add/Removed Projects</th></tr></thead>";
    } else {
        flow_table_head = "<thead class=\"heading\"><tr class=\"bg-blue\"><th>S.No.</th><th>User Name</th><th>Organization</th><th>Role</th><th class=\"width-th-100 text-center\">Assigned Project</th><th class=\"width-th-100 text-center\">Add/Removed Projects</th></tr></thead>";
    }

    var inputfield = "";
    console.log(data.serviceResponse.AllData);
    for (var i = 0; i < data.serviceResponse.AllData.length; i++) {
        var status = (data.serviceResponse.AllData[i].isActive == 1) ? "checked"
            : "";
        /*
		 * if (user.role.roleId != -1) { status = status + " disabled"; }
		 */
        flow_sl_cnt++;
        flow_details_record = flow_details_record
            + "<tr><td>"
            + (i + 1)
            + "</td><td>"
            + data.serviceResponse.AllData[i].userName
            + "</td><td>"
            + data.serviceResponse.AllData[i].organisationId.masterName
            + "</td><td> "
            + data.serviceResponse.AllData[i].role.roleName
            + "</td><td align=\"center\"><a class='btn btn-info' href=\"javascript:void(0);\" onclick=\"onClickfunction('userViewForAssignedProject^"
            + data.serviceResponse.AllData[i].userName
            + "');\"><i class=\"fa fa-eye\"></i></a></td>"
            + "</td><td align=\"center\"><a class='btn btn-info' href=\"javascript:void(0);\" onclick=\"onClickfunction('userEditForAssignedProject^"
            + data.serviceResponse.AllData[i].userName
            + "');\"><i class=\"fa fa-edit\"></i></a></td></tr>";
    }
    if (flow_details_record != "<tbody>") {
        flow_details_record = flow_details_record + "</tbody>"
        flow_details_record = flow_table_head + flow_details_record;
        $("#btn_disciplinesection").show("fast");
        $("#assignUsrPList_table").html(flow_details_record);
        var table = $('#assignUsrPList_table').DataTable({ "dom": '<"top"i>rt<"bottom"flp><"clear">', "bDestroy": true });
    } else if (flow_details_record == "<tbody>") {
        flow_details_record = flow_details_record + "<tr><td class=\"text-center\" colspan=\"6\"><span class=\"label label-danger\"> There are no data available </span></td></tr></tbody>"
        flow_details_record = flow_table_head + flow_details_record;
        $("#btn_disciplinesection").hide("fast");
        $("#assignUsrPList_table").html(flow_details_record);
    }

}
/* For AssignProject(ck) */
function fillAssignedProject(data1, data2) {
    // alert(data.userName);
    var user = JSON.parse(localStorage.getItem('user_data'));
    var flow_sl_cnt = 0;
    var flow_table_head = "";
    var flow_details_record = "<tbody>";
    var footer = "<tfoot><tr><td colspan='3'><ul class=\"pagination\">";
    /*
	 * for (var i = 0; i <= data1.length / 10; i++) { footer = footer + "<li><a
	 * href=\"javascript:void(0);\" onclick=\"listUserData(" + (i * 10) +
	 * ",10)\">" + (i + 1) + "</a></li>"; }
	 */
    footer = footer + "</ul></td></tr></tfoot>";
    // $("#sizeoflist").val(data.length);
    if (user.role.roleId == -1) {
        flow_table_head = "<thead class=\"heading\"><tr class=\"bg-blue\"><th>S.No.</th><th>Project Id</th><th>Project Name</th></tr></thead>";
    } else {
        flow_table_head = "<thead class=\"heading\"><tr class=\"bg-blue\"><th>S.No.</th><th>Project Id</th><th>Project Name</th></tr></thead>";
    }

    var inputfield = "";
    for (var i = 0; i < data1.length; i++) {
        // var status = (data.userDetail.projects[i].isActive == 1) ? "checked"
        // : "";
        /*
		 * if (user.role.roleId != -1) { status = status + " disabled"; }
		 */
        flow_sl_cnt++;
        flow_details_record = flow_details_record
            + "<tr><td>"
            + (i + 1)
            + "</td><td>"
            + data1[i]
            + "</td><td>"
            + data2[i]
            + "</td></tr>";
    }
    if (flow_details_record != "<tbody>") {
        flow_details_record = flow_details_record + "</tbody>"
        flow_details_record = flow_table_head + flow_details_record;
        $("#btn_disciplinesection").show("fast");
        $("#assignedUsrPList_table").html(flow_details_record);
        var table = $('#assignedUsrPList_table').DataTable({ "dom": '<"top"i>rt<"bottom"flp><"clear">', "bDestroy": true });
    } else if (flow_details_record == "<tbody>") {
        flow_details_record = flow_details_record + "<tr><td class=\"text-center\" colspan=\"3\"><span class=\"label label-danger\"> There are no data available </span></td></tr></tbody>"
        flow_details_record = flow_table_head + flow_details_record;
        $("#btn_disciplinesection").hide("fast");
        $("#assignedUsrPList_table").html(flow_details_record);
    }
}
/* For AssignProject(ck) */
function saveAssignProjects() {
    var user = JSON.parse(localStorage.getItem('user_data'));
    var uId = $("#userIdForAssignProject").val();
    var projectValues = [];
    var projectIdsOld = [];
    var projectIdsNew = [];
    var allAssignedProjects = document.getElementById("aProjectList");
    var allAssignedProjectsHidden = document.getElementById("aProjectListHidden");
    for (var i = 0; i < allAssignedProjects.options.length; i++) {
        if (allAssignedProjects.options[i].selected) {
            if (allAssignedProjects.options[i].value != 0) {
                projectValues.push({
                    "projectId": parseInt(allAssignedProjects.options[i].value, 10),
                    "projectName": allAssignedProjects.options[i].text,
                    "isActive": 1
                });
                projectIdsNew.push(allAssignedProjects.options[i].value);
            }
        }
    }

    for (var j = 0; j < allAssignedProjectsHidden.options.length; j++) {
        if (allAssignedProjectsHidden.options[j].selected) {
            if (allAssignedProjectsHidden.options[j].value != 0) {
                projectIdsOld.push(allAssignedProjectsHidden.options[j].value);
            }
        }
    }
    if (!compareArrays(projectIdsOld, projectIdsNew)) {
        passdata = {
            "userId": parseInt(uId, 10),
            "projects": projectValues,
            "updatedBy": user.userId,
            // "isActive" : 1,
        }
        console.log('user data --> ' + JSON.stringify(passdata))
        postRequest('/api/user/updateUserForAssignProject', JSON.stringify(passdata),
            showStatusForAssignProject);
    }
    else {
        alert("No Any Modification Fonud,Please Do Some Modification");
    }
}
/* For AssignProject used for compare two Array(ck) */
function compareArrays(arr1, arr2) {
    return $(arr1).not(arr2).length == 0 && $(arr2).not(arr1).length == 0
};
/* For AssignProject used (ck) */
function showStatusForAssignProject(data) {
    // console.log("My Log Error --- " + JSON.stringify(data));
    alert(data.serviceResponse.customResponse.responseDescription);
    if (data.serviceResponse.customResponse.responseCode == 200) {
        getRequest('/assignedProjectList', listPage);
        // document.getElementById('adduserform').reset();
        // $('#adduserform').reset();
    } else {
        // getRequestWithoutToken('/forget', forgetPage);
    }
}
/* For AssignProject used for Reset (ck) */
function resetAssignProjectOrgAndRole() {
    document.getElementById("assignPListOrgId").selectedIndex = "0";
    document.getElementById("assignPListRole").selectedIndex = "0";
}
/* For AssignProject used for View assigned Project to user (ck) */
function viewAssignProject(data) {
    var projectValueId = new Array();
    var projectValues = new Array();
    /*
	 * <c:forEach var="project" items=JSON.stringify(data.projects) >
	 * projectValueId.push("${project.projectId}");
	 * projectValues.push("${project.projectName}"); </c:forEach>
	 */    
    $(document).ready(function() {
        var i;
        for (i = 0; i < data.projects.length; i++) {
            projectValueId.push(data.projects[i].projectId);
            projectValues.push(data.projects[i].projectName);
        }
        fillViewAssignProject(projectValueId, projectValues);
        $("#modal_large").modal();
    });
}
/* For AssignProject used for View assigned Project to user (ck) */
function fillViewAssignProject(data1, data2) {
    var user = JSON.parse(localStorage.getItem('user_data'));
    var flow_sl_cnt = 0;
    var flow_table_head = "";
    var flow_details_record = "<tbody>";
    var footer = "<tfoot><tr><td colspan='3'><ul class=\"pagination\">";
    /*
	 * for (var i = 0; i <= data1.length / 10; i++) { footer = footer + "<li><a
	 * href=\"javascript:void(0);\" onclick=\"listUserData(" + (i * 10) +
	 * ",10)\">" + (i + 1) + "</a></li>"; }
	 */
    footer = footer + "</ul></td></tr></tfoot>";
    // $("#sizeoflist").val(data.length);
    if (user.role.roleId == -1) {
        flow_table_head = "<thead class=\"heading\"><tr class=\"bg-blue\"><th>S.No.</th><th>Project Id</th><th>Project Name</th></tr></thead>";
    } else {
        flow_table_head = "<thead class=\"heading\"><tr class=\"bg-blue\"><th>S.No.</th><th>Project Id</th><th>Project Name</th></tr></thead>";
    }

    var inputfield = "";
    // console.log(data.serviceResponse.AllData);
    for (var i = 0; i < data1.length; i++) {
        // var status = (data.userDetail.projects[i].isActive == 1) ? "checked"
        // : "";
        /*
		 * if (user.role.roleId != -1) { status = status + " disabled"; }
		 */
        flow_sl_cnt++;
        flow_details_record = flow_details_record
            + "<tr><td>"
            + (i + 1)
            + "</td><td>"
            + data1[i]
            + "</td><td>"
            + data2[i]
            + "</td></tr>";

        // if (user.role.roleId == -1) {
        /*
		 * flow_details_record = flow_details_record + "<td align=\"center\"><a
		 * class='btn btn-info' href=\"javascript:void(0);\"
		 * onclick=\"onClickfunction('userresetpassword^" +
		 * data.serviceResponse.AllData[i].userName + "');\"><i class=\"fa
		 * fa-edit\"></i></a></td></tr>";
		 */
        /*
		 * } else { flow_details_record = flow_details_record + "</tr>"; }
		 */
    }
    if (flow_details_record != "<tbody>") {
        flow_details_record = flow_details_record + "</tbody>"
        flow_details_record = flow_table_head + flow_details_record;
        $("#btn_disciplinesection").show("fast");
        $("#viewUsrPList_table").html(flow_details_record);
        var table = $('#viewUsrPList_table').DataTable({ "bDestroy": true });
    } else if (flow_details_record == "<tbody>") {
        flow_details_record = flow_details_record + "<tr><td class=\"text-center\" colspan=\"3\"><span class=\"label label-danger\"> There are no data available </span></td></tr></tbody>"
        flow_details_record = flow_table_head + flow_details_record;
        $("#btn_disciplinesection").hide("fast");
        $("#viewUsrPList_table").html(flow_details_record);
    }
}
function editProfileByPhoneAndEmail(data) {
	var orgId = $('#organisationId').val().trim();
	if (orgId != 0) {
		var oldMobile = $("#mobilealt").val();
		var newMobile = $("#mobile_number").val();
		var oldEmail = $("#emailalt").val();
		var newEmail = $("#email").val();
		var otp;
		var statement;
		var type;
		var email;
		var phone;
		var user = JSON.parse(localStorage.getItem('user_data'));
		// var level = $('#levelId').val().trim();
		var organisationId = $('#organisationId').val().trim();
		var role = $('#roleId').val().trim();
		var lineMinistryId = $('#lineMinistryId').val().trim();
		var state = $('#stateId').val().trim();
		var departmentId = $('#departmentId').val();
		var port = $('#portId').val();
		var boardId = $('#boardId').val();
		var agency = $('#agencyId').val();
		// var govtAgencyId = $('#govtAgencyId').val().trim();
		var assignProjectId = $('#assignProjectId').val();
		var assignMinnorId = $('#minnorPortId').val();

		var designation = $('#designation').val().trim();
		var loginId = $('#userName').val().trim();
		var name = $('#name').val().trim();
		var mobile = $('#mobile_number').val().trim();
		var email = $('#email').val().trim();
		var passdata;
		var check = true;
		var proval = [];
		var miPortList = [];
		$(".error").remove();

		if (designation.length == 0) {
			console.log('designationId data --> ');
			$('#designation')
			.after(
			'<font color="red"><span class="error">Please Enter Designation</span></font>');
			if (check) {
				check = false;

			}
		}
		if (loginId == '') {
			console.log('loginId data --> ');
			$('#userName')
			.after(
			'<font color="red"><span class="error">Please Enter Login Id</span></font>');
			if (check) {
				check = false;

			}
		} else if (!loginIdValidate(loginId)) {
			console.log('loginId data --> ');
			$('#userName')
			.after(
			'<font color="red"><span class="error">Please Enter Correct Format LoginId</span></font>');
			if (check) {
				check = false;

			}
		}
		if (name == '') {
			console.log('department data --> ');
			$('#name')
			.after(
			'<font color="red"><span class="error">Please Enter Correct Name</span></font>');
			if (check) {
				check = false;

			}
		}
		if (mobile.length < 10
				|| !(mobile.startsWith("7") || mobile.startsWith("8") || mobile
						.startsWith("9"))) {
			console.log('department data --> ');
			$('#mobile_number')
			.after(
			'<font color="red"><span class="error">Please Enter Correct Mobile</span></font>');
			if (check) {
				check = false;

			}
		}
		if (email == '') {
			console.log('department data --> ');
			$('#email')
			.after(
			'<font color="red"><span class="error">Please Enter Email</span></font>');
			if (check) {
				check = false;

			}
		} else if (!validateEmail(email)) {
			console.log('department data --> ');
			$('#email')
			.after(
			'<font color="red"><span class="error">Please Enter Correct Email</span></font>');
			if (check) {
				check = false;

			}
		}

		console.log('user data --> ' + check);
		if (check) {
			console.log('user data --> ');
			var userId;
			var isActive;
			var isFirstAttempt;
			
			if (data == 'add') {
				userId = 0;
				isActive = 1;
				isFirstAttempt = 1;

			} else {
				userId = $("#userId").val();
				isActive = $("#status").val();
				isFirstAttempt = $("#isfirstattempt").val();
			}
			passdata = {
					/*
					 * "levelId" : { "masterId" : $("#levelId").val() },
					 */
					"organisationId" : {
						"masterId" : organisationId
					},
					"role" : {
						"roleId" : (role == 0 || role == null) ? -1 : role
					},
					"lineMinistryId" : {
						"masterId" : (lineMinistryId == 0 || lineMinistryId == null) ? -1
								: lineMinistryId
					},
					"state" : {
						"masterId" : (state == 0 || state == null) ? -1 : state
					},
					"departmentId" : {
						"departmentId" : (departmentId == 0 || departmentId == null) ? -1
								: departmentId
					},
					"port" : {
						"portId" : (port == 0 || port == null) ? -1 : port
					},
					"maritimeBoardId" : {
						"maritimeBoardId" : (boardId == 0 || boardId == null) ? -1
								: boardId,
					},
					"agency" : {
						"masterId" : (agency == 0 || agency == null) ? -1 : agency
					},
					"projects" : proval,
					"designation" : designation,
					"userName" : loginId,
					"isLoginUserNameEditable" : $("#checkLoginIdEditable").is(
					':checked') ? 1 : 0,
							"name" : name,
							"mobileNumber" : mobile,
							"email" : email,
							"createdBy" : user.userId,
							"updatedBy" : user.userId,
							"userId" : userId,
							"isActive" : isActive,
							"isFirstAttempt" : isFirstAttempt,
							"minorPorts" : miPortList
			}
			console.log('user data --> ' + JSON.stringify(passdata))

			if (newMobile != oldMobile || newEmail != oldEmail) {
				if (newMobile != oldMobile) {
					statement = "please enter the otp sent on your mobile number :"
						+ newMobile;
					type = "phone";
					param = newMobile;

				}
				if (newEmail != oldEmail) {
					statement = "please enter the otp sent on your email id :"
						+ newEmail;
					type = "mail"
						param = newEmail;

				}
			}
			getRequest(
					"/api/user/generateOtp/" + type + '/' + param,
					function returnOtp(data) {
						var x;
						otp = data;
						console.log(otp); // var x=prompt(statement);
						$.sweetModal
						.prompt(
								statement,
								null,
								otp,
								function(val) {
									x = val;
									if (val != null || val != '') {

										getRequest(
												'/api/user/validateOtp/'
												+ x,
												function otpValidator(
														data) {

													if (data == 'SUCCESS') {


														postRequest('/api/user/saveUserDetails',
																JSON.stringify(passdata), showStatusUser);

													}

													else {
														$
														.sweetModal({
															content : 'Invalid OTP.',
															title : 'Invalid OTP.',
															icon : $.sweetModal.ICON_ERROR,
															buttons : [ {
																label : 'Close',
																classes : 'redB'
															} ]
														});
													}

												});
									}

								});

					});

		}

	}
}
function refreshValidateMsg(){
    
   // $('.error').remove();
   /*
	 * var oldMobile=$("#mobilealt").val(); var
	 * newMobile=$("#mobile_number").val(); var oldEmail=$("#emailalt").val();
	 * var newEmail=$("#email").val(); var organisationId =
	 * $('#organisationId').val().trim(); var role = $('#roleId').val().trim();
	 * var lineMinistryId = $('#lineMinistryId').val().trim(); var state =
	 * $('#stateId').val().trim(); var departmentId = $('#departmentId').val();
	 * var port = $('#portId').val(); var boardId = $('#boardId').val(); var
	 * agency = $('#agencyId').val(); var assignProjectId =
	 * $('#assignProjectId').val(); var assignMinnorId =
	 * $('#minnorPortId').val();
	 * 
	 * var designation = $('#designation').val().trim(); var loginId =
	 * $('#userName').val().trim(); var name = $('#name').val().trim(); var
	 * mobile = $('#mobile_number').val().trim(); var email =
	 * $('#email').val().trim(); if (organisationId == 0) { $('#organisationId')
	 * .after( '<font color="red"><span class="error">Please Select Department</span></font>'); }
	 * if (role == 0) { $('#roleId') .after( '<font color="red"><span
	 * class="error">Please Select Role</span></font>'); }
	 * 
	 * if (organisationId == 36) { // Line Ministry if (lineMinistryId == 0) {
	 * $('#lineMinistryId') .after( '<font color="red"><span
	 * class="error">Please Select Line Ministry</span></font>'); } } else if
	 * (organisationId == 37) { // Major Port Official if (state == 0) {
	 * $('#stateId') .after( '<font color="red"><span class="error">Please
	 * Select State</span></font>'); } if (port == 0) { console.log('portId
	 * data --> '); $('#portId') .after( '<font color="red"><span
	 * class="error">Please Select Port</span></font>'); } } else if
	 * (organisationId == 38) { // Maritime Board if (state == 0) {
	 * $('#stateId') .after( '<font color="red"><span class="error">Please
	 * Select State</span></font>'); } if(boardId == 0){ $('#boardId') .after( '<font
	 * color="red"><span class="error">Please Select Maritime Board</span></font>'); }
	 *  } else if (organisationId == 39) { // State official if (state == 0) {
	 * $('#stateId') .after( '<font color="red"><span class="error">Please
	 * Select State</span></font>'); }
	 * 
	 * if (departmentId == 0) { $('#departmentId') .after( '<font color="red"><span
	 * class="error">Please Select Sub Department</span></font>'); } } else if
	 * (organisationId == 40) { // Implementation Agency User if (state == 0) {
	 * $('#stateId') .after( '<font color="red"><span class="error">Please
	 * Select State</span></font>'); } if (agency == 0) { $('#agencyId')
	 * .after( '<font color="red"><span class="error">Please Select Agency</span></font>'); } }
	 * else if (organisationId == 41) { // Minner Port Official if (state == 0) {
	 * $('#stateId') .after( '<font color="red"><span class="error">Please
	 * Select State</span></font>'); } if (assignMinnorId.length == 0) {
	 * console.log('Minner Port data --> '); $('#minnorPortId') .after( '<font
	 * color="red"><span class="error">Please Select Minnor Port</span></font>'); }
	 *  }
	 */
    
    }
function resetUserPassword() {
	var user_name = $('#useNameValue').val();
	var password = $('#newPassword').val();
    var newpassword = $('#confpassword').val();
    console.log(password + ' ** ' + newpassword + ' ' + user_name);
    $(".error").remove();
    if (password.length < 1) {
        $('#newPassword')
            .after(
            '<font color="red"><span class="error">password is required</span></font>');
        check = false;
    }else if (password.length < 8) {
        $('#newPassword')
        .after('<font color="red" ><span class="error">Password must be at least 8 characters long</span></font>');
    check = false;
    }else if (newpassword.length < 1) {
        $('#confpassword')
        .after('<font color="red"><span class="error">password is required</span></font>');
    check = false;
    }else if (newpassword.length < 8) {
        $('#confpassword')
        .after('<font color="red" ><span class="error">Password must be at least 8 characters long</span></font>');
    check = false;
    } else if (password != newpassword) {
        $('#confpassword')
            .after(
            '<font color="red"><span class="error">password should be same</span></font>');
        check = false;
    } else {
        var data = {
            "userName": user_name,
            "newPassword": newpassword
        };
        console.log(data);
        alert(data);
        $
            .ajax({
                'url': $('#contextPathHolder').attr('data-contextPath')
                + '/api/user/resetPassword',
                'type': 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                "data": JSON.stringify(data),
                'success': function(result) {
                    console.log("My Log Error ---  "
                        + JSON.stringify(result));
                    alert(result.serviceResponse.customResponse.responseDescription);
                    if (result.serviceResponse.customResponse.responseCode == 200) {
                        homeScreen();
                    } else {
                        // getRequestWithoutToken('/forget', forgetPage);
                    }
                },
                'error': function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log("My Log Error ---  " + textStatus);
                    console.log("My Log Error ---  " + errorThrown);
                    console.log("My Log Error ---  "
                        + JSON.stringify(XMLHttpRequest));
                    alert(JSON.stringify(XMLHttpRequest));
                }
            });
    }
}