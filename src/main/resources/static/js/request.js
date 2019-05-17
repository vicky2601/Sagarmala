function getRequest(url, handleData) {
	var access = JSON.parse(localStorage.getItem('acess_data'));

	$.ajax({
		'url' : $('#contextPathHolder').attr('data-contextPath') + url,
		'type' : 'GET',
		"crossDomain" : true,
		"headers" : {
			'Authorization' : 'Bearer ' + access.access_token,
			'Content-Type' : 'application/json'
		},
		'success' : handleData,
		'error' : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("My Log Error ---  " + textStatus);
			console.log("My Log Error ---  " + errorThrown);
			console.log(JSON.stringify(XMLHttpRequest));
			console.log("My Log Error ---  "
					+ XMLHttpRequest.responseJSON.error_description);
			if (XMLHttpRequest.responseJSON.error_description
					.includes("Access token expired")) {
				getRefreshtoken(url, handleData);
			}
		}
	});
}

function postRequest(url, data, handleData) {
	var access = JSON.parse(localStorage.getItem('acess_data'));
	$.ajax({
		'url' : $('#contextPathHolder').attr('data-contextPath') + url,
		'type' : 'POST',
		"crossDomain" : true,
		"headers" : {
			'Authorization' : 'Bearer ' + access.access_token,
			'Content-Type' : 'application/json'
		},
		"data" : data,
		'success' : handleData,
		'error' : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("My Log Error ---  " + textStatus);
			console.log("My Log Error ---  " + errorThrown);
			console.log("My Log Error ---  "
					+ XMLHttpRequest.responseJSON.error_description);
			if (XMLHttpRequest.responseJSON.error_description
					.includes("Access token expired")) {
				postRefreshtoken(url, data, handleData);
			}
		}
	});
}
function getRequestWithoutToken(url, handleData) {
	$.ajax({
		'url' : $('#contextPathHolder').attr('data-contextPath') + url,
		'type' : 'GET',
		'success' : handleData,
		'error' : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("My Log Error ---  " + textStatus);
			console.log("My Log Error ---  " + errorThrown);
			console.log("My Log Error ---  "
					+ XMLHttpRequest.responseJSON.error_description);
		}
	});
}
function redirectPage(url) {
	var access = JSON.parse(localStorage.getItem('acess_data'));
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", url, true);
	xmlhttp.setRequestHeader('Authorization', 'Bearer ' + access.access_token);
	xmlhttp.send();
}
function getRefreshtoken(url, handleData) {
	var access = JSON.parse(localStorage.getItem('acess_data'));
	var form = "grant_type=refresh_token&refresh_token=" + access.refresh_token;
	$.ajax({
		'url' : $('#contextPathHolder').attr('data-contextPath')
				+ "/oauth/token",
		'type' : 'POST',
		"crossDomain" : true,
		"headers" : {
			'Authorization' : 'Basic bWhyZF9zYWdhcjpzYWdhcm1hbGFANDMyMQ==',
			'Content-Type' : 'application/x-www-form-urlencoded'
		},
		"data" : form,
		'success' : function(result) {
			// alert('Success Login');
			window.localStorage.setItem("acess_data", JSON.stringify(result));
			getRequest(url, handleData);
		},
		'error' : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert(XMLHttpRequest.responseJSON.error_description);
			console.log(XMLHttpRequest.responseJSON.error_description);
			alert('Session Expired..');
			homeScreen();
		}
	});
}
function postRefreshtoken(url, data, handleData) {
	var access = JSON.parse(localStorage.getItem('acess_data'));
	var form = "grant_type=refresh_token&refresh_token=" + access.refresh_token;
	$.ajax({
		'url' : $('#contextPathHolder').attr('data-contextPath')
				+ "/oauth/token",
		'type' : 'POST',
		"crossDomain" : true,
		"headers" : {
			'Authorization' : 'Basic bWhyZF9zYWdhcjpzYWdhcm1hbGFANDMyMQ==',
			'Content-Type' : 'application/x-www-form-urlencoded'
		},
		"data" : form,
		'success' : function(result) {
			// alert('Success Login');
			window.localStorage.setItem("acess_data", JSON.stringify(result));
			postRequest(url, data, handleData);
		},
		'error' : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert(XMLHttpRequest.responseJSON.error_description);
			console.log(XMLHttpRequest.responseJSON.error_description);
			alert('Session Expired..');
			homeScreen();
		}
	});
}

function postRequestWithFile(url, data, handleData) {
	var access = JSON.parse(localStorage.getItem('acess_data'));
	alert(access.access_token)
	$.ajax({
		'url' : $('#contextPathHolder').attr('data-contextPath') + url,
		"type" : "POST",
		"enctype" : 'multipart/form-data',
		"crossDomain" : true,
		"headers" : {
			'Authorization' : 'Bearer ' + access.access_token
		},
		"data" :data,
		"processData" : false,
		"contentType" : false,
		"cache" : false,
		"timeout" : 600000,
		'success' : handleData,
		'error' : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("My Log Error ---  " + textStatus);
			console.log("My Log Error ---  " + errorThrown);
			console.log("My Log Error ---  "
					+ XMLHttpRequest.responseJSON.error_description);
			if (XMLHttpRequest.responseJSON.error_description
					.includes("Access token expired")) {
				postRefreshtoken(url, data, handleData);
			}
		}
	});
}