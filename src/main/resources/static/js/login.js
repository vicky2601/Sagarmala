var ipaddress;
$.getJSON('https://api.ipify.org?format=json', function(data) {
	ipaddress = data.ip;
	console.log(data.ip);
});
function signIn() {	
	
		var user_name = $('#user_name').val();	
		var pswd = $('#password').val().trim();		
		$(".error").remove();
		var check=true; 
		if (user_name.length < 1) {
			$('#user_name').after('<font color="red"><span class="error">User Name is required</span></font>');
			check = false; 
		} 
		if (pswd.length < 1) {
			$('#password').after('<font color="red" ><span	class="error">Password is required</span></font>');
			if(check) { 
				check = false; 
			}			 
		}
		else if (pswd.length < 8) {
			$('#password').after('<font color="red" ><span	class="error">Password must be between 8 - 15 characters long</span></font>');
			if(check) { 
				check = false; 
			}			 
		} 
		if(validatePassword('password') && pswd.length > 7){
			if(check) { 
				check = false; 
			}
		}
		if(validateCaptcha()){
			if(check){
				var form = $('#loginForm').serialize()
				+ "&grant_type=password&client_id=mhrd_sagar&client_secret=sagarmala@4321&ipaddress="
				+ ipaddress;	
				var url = $('#contextPathHolder').attr('data-contextPath')+"/oauth/token";				
				console.log('Post :- '+url)
				$.ajax({
					'url' : url,
					'type' : 'POST',
					"crossDomain" : true,
					"headers" : {
						'Authorization' : 'Basic bWhyZF9zYWdhcjpzYWdhcm1hbGFANDMyMQ==', 
						'Content-Type' : 'application/x-www-form-urlencoded'
					},
					"data" : form,
					'success' : function(result) {
						//alert('Success Login');
						$.sweetModal({
							content: 'Login Successfull',
							title: 'Access granted',
							icon: $.sweetModal.ICON_SUCCESS,
							buttons: [
								{
									label: 'Continue',
									classes: 'greenB',
									action: function() {
										localStorage.clear();
										window.localStorage.setItem("acess_data", JSON.stringify(result));
										getRequest("/api/user/userData", userData);
									}
								}
							]
						});
						
					},
					'error' : function(XMLHttpRequest, textStatus,errorThrown) {				
						//alert(XMLHttpRequest.responseJSON.error_description);
						console.log(XMLHttpRequest.responseJSON.error_description);
						$.sweetModal({
							content: XMLHttpRequest.responseJSON.error_description,
							title: 'Access Denied....',
							icon: $.sweetModal.ICON_ERROR,

							buttons: [
								{
									label: 'Try again',
									classes: 'redB'
								}
							]
						});
					}
				});
			}
		}
		else {
			$('#txtInput').after('<font color="red"><span class="error">Captcha Error!</span></font>');
		}		
}
function userData(data) {
	
	console.log("My user data = " + JSON.stringify(data));
	window.localStorage.setItem("user_data", JSON.stringify(data));
	if(data.isFirstAttempt == 1){
		getRequest('/changePassword/1',listPage);
	}
	else{
		getRequest("/loginSuccess", loginSuccessfully);
		getRequest("/api/master/commonMasterslist", loadMaster);
		setRoleAuth();	
	}
}
function loginSuccessfully(data) {
	
	
	$('body').html(data);	
}
function logout() {
	var access = JSON.parse(localStorage.getItem('acess_data'));
	getRequest("/api/user/logout/" + access.login, logoutData);
}
function logoutData(data) {
	console.log(data.serviceResponse.customResponse.responseDescription);
	if (data.serviceResponse.customResponse.responseCode == 200) {		
		homeScreen();
		$.cookie("JSESSIONID", null, {
			path : '/'
		});
		$.removeCookie('JSESSIONID', {
			path : '/'
		});
	}
}
function homeScreen() {
	localStorage.clear();
	location.replace($('#contextPathHolder').attr('data-contextPath')+"/");
}
function validateCaptcha() {
	var string1 = removeSpaces(document.getElementById('mainCaptcha').value);
	var string2 = removeSpaces(document.getElementById('txtInput').value).toUpperCase();
	console.log('str '+string1);
	console.log('str2 '+string2);
	if(string1 === string2) {
		return true;
	} else {
		return false;
	}
}

function removeSpaces(string) {
	return string.split(' ').join('');
}
function showPassword() {
	var x = document.getElementById("password");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}
function validatePassword(param) {
	$(".error").remove();
	var pwd = document.getElementById(param);	
	var pattern = new RegExp(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/);	
    if(pattern.test(pwd.value)){
    	return false;
    } else {
    	$('#'+param).after('<font color="red" ><span	class="error">Must contain at least one number and one special character and one uppercase and lowercase letter, and between 8 - 15 characters</span></font>');
    	return true;
    }    
}
function loadMaster(data){
	console.log('Master Data Load :- '+JSON.stringify(data));
}
function setRoleAuth(){

	var user = JSON.parse(localStorage.getItem('user_data'));
	roleId=user.role.roleId;
	
	console.log(roleId);
	if(roleId==-1){
		console.log('role hide')
		$( "#master" ).prop( "disabled", true );
		 
	}
	getRequest('/api/user/fetchRoleAuthorizationByRoleId/' + user.role.roleId, function setRoleInMemory(
			data) {
		console.log(' Role Auth Data :- ' + JSON.stringify(data));
		var roleAuth=data.serviceResponse.roleAuthList;
		var myArray=[];
		for(i=0;i<roleAuth.length;i++){
			//myArray.push(roleAuth[i].menuID)
			if(roleAuth[i].menuID==4){
				console.log('concept add is either visible or not ')
				if(roleAuth[i].addRight!=1){
					$('#add01').hide();
				}else if(roleAuth[i].viewRight!=1){
					$('#view02').hide();
				}
			}
		}
	//	console.log(myArray);
			//alert('concept add is either visible or not ')
		
		window.localStorage.setItem("role_auth_data",JSON.stringify(data.serviceResponse.roleAuthList));		
	});
}