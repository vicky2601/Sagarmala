function allowOnlyAlphabet(e) {
	if (e.ctrlKey || e.altKey) {
		e.preventDefault();
	} else {
		var key = e.keyCode;
		if (!((key == 9) || (key == 8) || (key == 32) || (key == 46)
				|| (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
			e.preventDefault();
		}
	}
}
function allowOnlyNumber(e) {
	if (e.shiftKey || e.ctrlKey || e.altKey) {
		e.preventDefault();
	} else {
		var key = e.keyCode;
		if (!((key == 9) || (key == 8) || (key == 32) || (key == 46)
				|| (key >= 35 && key <= 40) || (key >= 48 && key <= 57))) {
			e.preventDefault();
		}
	}
}
function validateEmail(email) {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}
function validateEmails(email) {
	var re = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	return re.test(email);
}
function allowOnlyAlphaNumberic(e) {
	var k = e.keyCode || e.which;
	var ok = k >= 65 && k <= 90 || // A-Z
	k >= 96 && k <= 105 || // a-z
	k >= 35 && k <= 40 || // arrows
	k == 9 || // tab
	k == 46 || // del
	k == 8 || // backspaces
	(!e.shiftKey && k >= 48 && k <= 57); // only 0-9 (ignore SHIFT options)

	if (!ok || (e.ctrlKey && e.altKey)) {
		e.preventDefault();
	}
}
function loginIdValidate(val) {
	var clean = val.replace(/[0-9]+/gi, "");
	if (clean.length <= 4) {
		return true;
	} else {
		return false;
	}
}

function validateMastersName(e) {
	var k = e.keyCode || e.which;
	var ok = k >= 65 && k <= 90 || // A-Z
	k >= 97 && k <= 122 || // a-z
	k == 9 || // tab
	k == 190 || // dot
	k == 46 || // dot
	k == 8 || // backspaces
	k == 32 || // space
	(!e.shiftKey && k >= 48 && k <= 57); // only 0-9 (ignore SHIFT options)

	if (!ok || (e.ctrlKey && e.altKey)) {
		e.preventDefault();
	}
}
function validateConceptProjName(e) {
	// $(".error").remove();
	var flag=true;
	var k = e.keyCode || e.which;
	var ok = k >= 65 && k <= 90 || //A-Z
	k >= 97 && k <= 122 || //a-z
	k == 44 || //comma
	k == 46 || //dot
	k == 190 || //dot
	k == 8 || //backspace
	k == 32 || //space
	(!e.shiftKey && k >= 48 && k <= 57); // only 0-9 (ignore SHIFT options)
	//$(".error").remove();
	if (!ok || (e.ctrlKey && e.altKey)) {
		e.preventDefault();
		$("#projectName")
		.after(
		'<font color="red"><span class="error">Please Enter valid project name.</span></font>');
		flag=false;
	}
	if(flag==false){
		 $(".error").remove();
	}

}
