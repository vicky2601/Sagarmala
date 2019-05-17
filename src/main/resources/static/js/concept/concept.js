

function checkcheckbox() {
	
 
	$("input[name=fundtype]").change(function() {
		
		console.log($(this).val());
		switch ($(this).val()) {
		
		case 'Single Funded':
			$("#singleAgency").show();
			$("#multipleAgency").hide();
			$("#addconcept").prop('disabled', true);
			break;
		case 'Multiple Funded':
			$("#multipleAgency").show();
			$("#singleAgency").hide();
			$("#addconcept").prop('disabled', true);
			break;
		case 'Non Funded':
			$("#singleAgency").hide();
			$("#multipleAgency").hide();
			$("#addconcept").prop('disabled', false);
			break;
		default:

		}

	});
}

function altervalues() {

	$(".selects").click(function() {
		// alert("Welcome");

		if ($(this).val() != "default") {
			// alert($(this).val());
			console.log($(this).val());
			$("#addconcept").prop('disabled', false);
		} else {
			$("#addconcept").prop('disabled', true);
		}

	});

	$('.selects2').change(function() {
		// alert('Welcome',"${master.serviceResponse.commonMastersList.agency.masterId}");
		var op = $(this).val();
		console.log(op);
		// debugger;
		if ($(this).val() != "" &&  $(this).val().length>1) {
			// alert($(this).val());
			$("#addconcept").prop('disabled', false);
		} else {
			// alert($(this).val());
			$("#addconcept").prop('disabled', true);
		}

	});

}

function saveConcept() {
	var agencies = '';
	var projecttype = $("input[name=fundtype]:checked").val();
	if ($("input[name=fundtype]:checked").val() == 'Single Funded') {
		agencies = $("#singleSelect").val();

	}

	if ($("input[name=fundtype]:checked").val() == 'Multiple Funded') {

		agencies += $("#multiSelect").val();
	}
	if ($("input[name=fundtype]:checked").val() == 'Non Funded') {

		agencies =null;
	}
	var conceptData = {

			"projectType" : projecttype,
			"idFundingAgencies" : agencies
	};

	postRequest("/api/concept/saveconcept", JSON.stringify(conceptData),
			function showAddConceptDetails(data) {

		getRequest('/addconceptdetails/' + data + '/' + projecttype
				+ '/' + agencies, listPage)

	});
}

function saveConceptDetails() {
	
	var nodalId=new Array();
	var nodalName=new Array();
	var nodalLandline=new Array();
	var nodalDesgin=new Array();
	var nodalMob=new Array();
	var nodalPost=new Array();
	var nodalEmail=new Array();
	debugger;
	/* $(".nodTabInfo").each(function(){
		 var name = $("#nodalInfoTable").children("#nodalName").text();
        // var value = $('.attrValue', b).text();
		 alert(name);
		 nodalId.push(name);
	 });*/
	 var table = $("table tbody");

	    table.find('tr').each(function (i) {
	    	 var $tds = $(this).find('td');
	    	 var count=$('tr').length;
	    	 var  nodalOffId;
	    	 var  nodalOffName;
	    	 var nodalOffiDesg ;
	    	 var  nodalOffMob;
	    	 var nodalOffPost;
	    	 var  nodaloffEmail
		    if($("tr").index(this)!=0){
		    	 nodalOffName = $tds.eq(0).text();
	    	     nodalOffId = $tds.eq(1).text();
		    	 nodalOffiLandLn=$tds.eq(2).text();
		    	 nodalOffiDesg = $tds.eq(3).text();
		    	 nodalOffMob=$tds.eq(4).text();
		    	 nodalOffPost=$tds.eq(5).text();
		    	 nodaloffEmail=$tds.eq(6).text();
		    	 
		    	 
		    	 
		    	 nodalId.push(nodalOffId);
		    	 nodalName.push(nodalOffName);
		    	 nodalLandline.push(nodalOffiLandLn);
		    	 nodalDesgin.push(nodalOffiDesg);
		    	 nodalMob.push(nodalOffMob);
		    	 nodalPost.push(nodalOffPost);
		    	 nodalEmail.push(nodaloffEmail);
		    	
		    }
	    	    
	        
	            /*nodalId.push(nodalOffId);
	            nodalName.push(nodalOffName);
	            nodalDesgin.push(nodalOffiDesg);
	            nodalMob.push(nodalOffMob);
	            nodalPost.push(nodalOffPost);
	            nodalEmail.push(nodaloffEmail);*/
	        // do something with productId, product, Quantity
	      
	       
	         });
	   
	    var nodalOfficerId= nodalId;
	    var nodalOfficerName=nodalName;
	    var nodalOfiicerLandLine=nodalLandline;
	    var nodalOfficerDesg=nodalDesgin;
	    var nodalOfficerMob=nodalMob;
	    var nodalOfficerPost=nodalPost;
	    var nodalOfficerEmail=nodalEmail;
	   /* alert(JSON.stringify(nodalName));
	    alert(JSON.stringify(nodalDesgin));
	    alert(JSON.stringify(nodalMob));
	    alert(nodalOfficerEmail);
	
	    alert(JSON.stringify(nodalPost));*/
		
 alert("save concept details");
 debugger;
	var nodalOfficerNamesArray = $(".nodalOfficerName");
	var nodalOfficerNames='';
	var nodalOfficerLandlineArray=$(".nodalOfficerLandline")
	// alert('nodalOfficerLandline :-',nodalOfficerLandlineArray.length);
	var nodalOfficerLandlines='';
	var projLocName='';
	var sep;
	var projectLocation=$("#projectLocation").val();
	var projName= $("#projectName").val();
	var estimatedCost=$("#estimatedCost").val();
	var nodalOfficerLandlineArray = $(".nodalOfficerLandline");
	var nodalOfficerDesignationArray=$('.nodalOfficerDesignation');
	var nodalOfficerMobileArray=$('.nodalOfficerMobile');
	var nodalOfficerPostalAddressArray=$('.nodalOfficerPostalAddress');
	var nodalOfficerEmailArray=$('.nodalOfficerEmail');
	var stateNames = '';
	var check=true;
	//project Location seperator
	for (var i = 0; i < projectLocation.length; i++) {
		if (i == projectLocation.length - 1) {
			sep = ''
		} else {
			sep = ','
		}
		projLocName += projectLocation[i] + sep;
	}
	var stateIds = $("#stateId").val();
	//state name seperator.
	for (var i = 0; i < stateIds.length; i++) {
		if (i == stateIds.length - 1) {
			sep = ''
		} else {
			sep = ','
		}
		stateNames += stateIds[i] + sep;
	}
	
	$(".error").remove();
	if(projName=="" || projName.length<1 ||projName==null){
		$("#projectName").after(
		'<font color="red"><span class="error">Please must be filled project name.</span></font>');

		return false;
	}
	if($("#projectBrief").val().length<1||$("#projectBrief").val()==""||$("#projectBrief").val()==null){
		$("#projectBrief")
		.after(
		'<font color="red"><span class="error">Please must be filled Project Brief.</span></font>');

		return false;
	} 
	if($("#aimsAndObjectives").val().length<1||$("#aimsAndObjectives").val()==""||$("#aimsAndObjectives").val()==null){
		$("#aimsAndObjectives")
		.after(
		'<font color="red"><span class="error">Please must be filled Objective.</span></font>');

		return false;
	} 
	if($("#scopeOfWork").val().length<1||$("#scopeOfWork").val()==""||$("#scopeOfWork").val()==null){
		$("#scopeOfWork")
		.after(
		'<font color="red"><span class="error">Please must be filled scope Of Work.</span></font>');

		return false;
	} 

	if(estimatedCost==''||estimatedCost<1 || estimatedCost==null){
		$("#estimatedCost").after(
		'<font color="red"><span class="error">Please must be filled estimated Cost.</span></font>');

		return false;
	}

	/*if($("#nodalOfficerLandline").val()==null || $("#nodalOfficerLandline").val().length<10){
		$("#nodalOfficerLandline").after(
		'<font color="red"><span class="error">Please must filled nodal officer landline.</span></font>');

		return false;
	}
	if($("#nodalOfficerDesignation").val()==null || $("#nodalOfficerDesignation").val().length<1){
		$("#nodalOfficerDesignation").after(
		'<font color="red"><span class="error">Please must filled nodal officer designation.</span></font>');

		return false;
	}

	if($("#nodalOfficerMobile").val()==null || $("#nodalOfficerMobile").val().length<10){
		$("#nodalOfficerMobile").after(
		'<font color="red"><span class="error">Please must filled nodal officer Mobile.</span></font>');

		return false;
	}
	if($("#nodalOfficerPostalAddress").val()==null || $("#nodalOfficerPostalAddress").val().length<20){
		$("#nodalOfficerPostalAddress").after(
		'<font color="red"><span class="error">Please must filled nodal officer PostalAddress required 20 characters.</span></font>');

		return false;
	}


	if($("#nodalOfficerEmail").val()==null || $("#nodalOfficerEmail").val().length<1){
		$("#nodalOfficerEmail").after(
		'<font color="red"><span class="error">Please must filled nodal officer email.</span></font>');

		return false;
	}*/
	// alert(nodalOfficerMobile);
	// projectLocations = [];
	
	console.log("inside update conceptDetails");
	var conceptdetailsdata = {
			"projectName" : projName,
			"projectType" : $("#projectType").val(),
			"stateId" : stateNames,
			"portType" : $("#portType").val(),
			"portName" : $("#portName").val(),
			"projectLocation":projLocName,
			"projectBrief" : $("#projectBrief").val(),
			"aimsAndObjectives" : $("#aimsAndObjectives").val(),
			"scopeOfWork" : $("#scopeOfWork").val(),
			"estimatedCost" : $("#estimatedCost").val(),
			"projectProponent" : $("#projectProponent").val(),
			"conceptId" : $("#conceptid").val(),
			"startDate" : $("#expectedStartDate").val(),
			"endDate" : $("#expectedEndDate").val(),
			"document" : $("#filepath").val(),
			"nodalOfficerName" : setDataFromMultipleRows(nodalOfficerId),
			"nodalOfficerLandline" : setDataFromMultipleRows(nodalOfiicerLandLine),
			"nodalOfficerDesignation" : setDataFromMultipleRows(nodalOfficerDesg),
			"nodalOfficerMobile" : setDataFromMultipleRows(nodalOfficerMob),
			"nodalOfficerPostalAddress" : setDataFromMultipleRows(nodalOfficerPost),
			"nodalOfficerEmail" : setDataFromMultipleRows(nodalOfficerEmail),
			"id" : $("#id").val()

	};

	var conceptData = JSON.stringify(conceptdetailsdata);
	console.log("CONCEPT DATA" + conceptData);
	postRequest("/api/concept/updateconceptwithconceptdetails", conceptData,
			function conceptSavedAlert(data) {

		alert("concept and its related details saved successfully")
		getRequest('/conceptlist',listPage);

	});

}

function setDataFromMultipleRows(arrayVar) {
	var sep;
	var returnVal = '';
	for (var i = 0; i < arrayVar.length; i++) {

		if (i == arrayVar.length - 1) {
			sep = ''
		} else {
			sep = '^'
		}

		returnVal += ($(arrayVar[i]).val() + sep);

	}
	return returnVal;
}

$("#doc")
.change(
		function() {

			var ext = $(this).val().split('.').pop().toLowerCase();
			if ($.inArray(ext, [ 'pdf' ]) == -1) {
				alert('invalid extension!');
				$(this).val("");
			}

			else {
				var $file = document.getElementById('doc'), $formData = new FormData();

				if ($file.files.length > 0) {
					for (var i = 0; i < $file.files.length; i++) {
						$formData.append("file", $file.files[i]);
					}
				}

				postRequestWithFile(
						"/api/concept/uploaduserdoctoserver",
						$formData, function(data) {

							$("#filepath").val(data);
						})
			}
		})




		$('#stateId').on('change', function() {

			var data = $('#stateId').select2('data')
			//alert(data.length);
			for (var i = 0; i < data.length; i++) {
				getRequest(
						'/api/master/fetchPortByState/' +data[i].id,
						function populateComboBox(data) {

							$('#portName').val('default').attr(
									'selected', 'false');
							if ($("#portName option[value='"
									+ data.serviceResponse.portList[0].portId
									+ "']").length > 0) {
								$("#portName option[value='"+data.serviceResponse.portList[0].portId+"']").remove();
							}
							$('<option selected="true">')
							.val(
									JSON
									.stringify(data.serviceResponse.portList[0].portId))
									.text(
											data.serviceResponse.portList[0].portName)
											.appendTo('#portName');

						});
				if(i==data.length-1){
					getRequest(
							'/api/master/fetchLocationByState/' +data[i].id,
							function populateComboBox1(data) {
								var json_data=data.serviceResponse.locList;
								for(var j=0;j<json_data.length;j++){
									console.log(json_data[j].locationId);
									console.log(json_data[j].locationName);
										$("#projectLocation option[value='default']").prop("selected", false);
										$('<option selected="true">')
										.val(
												JSON
												.stringify(json_data[j].locationId))
												.text(
														json_data[j].locationName)
														.appendTo('#projectLocation');
							}	
						});

					
				}
				
			}

		});
/*$('select').on('select2:unselecting', function (e) {
    var id = e.params.args.data.id; //your id
    alert('Are You Sure ?');
    e.preventDefault();
  // Do something with your id
});*/

function addRemoveExistNodalDiv() {

	/*$("#nodalName").html($('#nodalOfficerName').find(":selected").text());
	$("#hiddenNodalNameId").html($('#nodalOfficerName').find(":selected").val());
	$("#nodalLandLine").html($("#nodalOfficerLandline").val())
	$("#nodalDesignation").html($("#nodalOfficerDesignation").val())
	$("#nodalMobile").html($("#nodalOfficerMobile").val())
	$("#nodalPostal").html($("#nodalOfficerPostalAddress").val())
	$("#nodalEMail").html($("#nodalOfficerEmail").val())*/
	//alert($("#nodalInfoTable tr").length);
	$('#nodalInfoTable tr:last').after('<tr><td>'+$('#nodalOfficerName').find(":selected").text()+'</td><td style="display:none">'+$('#nodalOfficerName').find(":selected").val()+'</td>'
		+'<td>'+$("#nodalOfficerLandline").val()+'</td>'	
		+'<td>'+$("#nodalOfficerDesignation").val()+'</td>'	
		+'<td>'+$("#nodalOfficerMobile").val()+'</td>'	
		+'<td>'+$("#nodalOfficerPostalAddress").val()+'</td>'	
		+'<td>'+$("#nodalOfficerEmail").val()+'</td>'	
		+'</tr>');
/*alert($("#nodalOfficerName").children("option:selected").val())*/
	//if($(this).children("option:selected").val()!='option-1'){
		$("#nodalOfficerName option[value="+$('#nodalOfficerName').find(":selected").val()+"]").remove();
	//}
	
		$('#card').find("input:text").val('');
//	$("input:email").val('');
}

$(document).on('click', 'button', function() {
	// $(this).closest(".card").remove();
	var name = $(this).attr('name');

	if (name === 'minus') {
		if ($(this).attr('id') != 'minus') {
			$(this).closest('div.card').remove();
		}
		$("#countervar").val($("#countervar").val() - 1)

	}
});


$("#nodalOfficerDesignation").focusin(function(){
	$(".error").hide();
});

$("#nodalOfficerDesignation").focusout(function(){
	if($(this).val() === ""){
		$(".error").show();
	}else{
		$(".error").hide();
	}
});
$("#projectName").keyup(function(){
	$(".error").remove();
	var regix = /^([A-Za-z0-9]+[\.?\,?\s?]?)*$/g
		// $(".error").remove();
		if($("#projectName").val()=="" || $("#projectName").val().length<1 ||$("#projectName").val()==null){
			$("#projectName").after(
			'<font color="red"><span class="error">Please must be filled project name.</span></font>');
			return false;
		}
	if (!regix.test($("#projectName").val())) {
		$("#projectName")
		.after(
		'<font color="red"><span class="error">Please Enter valid project name.</span></font>');
		return false;
	}else{
		$(".error").remove();
	}
});
$("#estimatedCost").keyup(function(){
	$(".error").remove();
	var regix=/^[0-9]*$/g
		if(estimatedCost==''||estimatedCost<1 || estimatedCost==null){
			$("#estimatedCost").after(
			'<font color="red"><span class="error">Please must be filled estimated Cost.</span></font>');
			return false;
		}	
	if (!regix.test($("#estimatedCost").val())) {
		$("#estimatedCost")
		.after(
		'<font color="red"><span class="error">Please invalid estimated Cost.</span></font>');
		return false;
	}
});
$("#projectBrief").focusout(function(){
	$(".error").remove();
	var projBref=$(this).val();
	if(projBref.length<1){
		$("#projectBrief")
		.after(
		'<font color="red"><span class="error">Please must be filled Project Brief.</span></font>');
		return false;
	} 
});
$("#aimsAndObjectives").focusout(function(){
	$(".error").remove();
	var projBref=$(this).val();
	if(projBref.length<1){
		$("#aimsAndObjectives")
		.after(
		'<font color="red"><span class="error">Please must be filled Objective.</span></font>');
		return false;
	} 
});
$("#scopeOfWork").focusout(function(){
	$(".error").remove();
	var projBref=$(this).val();
	if(projBref.length<1){
		$("#scopeOfWork")
		.after(
		'<font color="red"><span class="error">Please must be filled Scope of work.</span></font>');
		return false;
	} 
});
$("#nodalOfficerLandline").keyup(function(){
	$(".error").remove();
	var regix=/^(0)?[1-9]{3}[\-]?([0-9]{6})$/

		if($("#nodalOfficerLandline").val()==null || $("#nodalOfficerLandline").val().length<10){
			$("#nodalOfficerLandline").after(
			'<font color="red"><span class="error">Please must filled nodal officer landline.</span></font>');
			return false;
		}
	if (!regix.test($("#nodalOfficerLandline").val())) {
		$("#nodalOfficerLandline")
		.after(
		'<font color="red"><span class="error">Please invalid nodal Officer Landline.</span></font>');
		return false;
	}
});
$("#nodalOfficerDesignation").keyup(function(){
	// $(".error").hide();
	$(".error").remove();
	var regix=/^[A-Za-z0-9]([A-Za-z0-9]+[\.?\,?\-?]?\s?)*$/g
		if($("#nodalOfficerDesignation").val()=='' ||$("#nodalOfficerDesignation").val().length<1){
			$("#nodalOfficerDesignation").after(
			'<font color="red"><span class="error">Please must filled nodal officer designation.</span></font>');
			return false;
		}
		if (!regix.test($("#nodalOfficerDesignation").val())) {
			$("#nodalOfficerDesignation")
			.after(
			'<font color="red"><span class="error">Please invalid nodal officer designation.</span></font>');
			return false;
		}else{
			$(".error").remove();
		}
});
		$("#nodalOfficerMobile").keyup(function(){
			$(".error").remove();
			var regix=/^(0)?[7-9]([0-9]{9})$/
				if($("#nodalOfficerMobile").val()==null || $("#nodalOfficerMobile").val().length<10){
					$("#nodalOfficerMobile").after(
					'<font color="red"><span class="error">Please must filled nodal officer Mobile.</span></font>');
					return false;
				}
				if (!regix.test($("#nodalOfficerMobile").val())) {
					$("#nodalOfficerMobile")
					.after(
					'<font color="red"><span class="error">Please invalid nodal Officer Mobile.</span></font>');
					return false;
				}
		});
				$("#nodalOfficerPostalAddress").keyup(function(){
					// $(".error").hide();
					$(".error").remove();
					var regix=/^([A-Za-z0-9]+[\.?\,?\#?\@?\/?\(?\)?\*?\s?]?)*$/g
						if($("#nodalOfficerPostalAddress").val()==null || $("#nodalOfficerPostalAddress").val().length<20){
							$("#nodalOfficerPostalAddress").after(
							'<font color="red"><span class="error">Please must filled nodal officer PostalAddress required 20 characters.</span></font>');
							return false;
						}
						if (!regix.test($("#nodalOfficerPostalAddress").val())) {
							$("#nodalOfficerPostalAddress")
							.after(
							'<font color="red"><span class="error">Please Enter valid nodal officer Postal Address.</span></font>');
							return false;
						}
				});
						$("#nodalOfficerEmail").keyup(function(){
							$(".error").remove();
							var regix=/^([a-zA-Z0-9]+[\_?\.?]?)*\@(([a	-zA-Z0-9])+\.)+([a-zA-Z]{2,4})+$/
								if($("#nodalOfficerEmail").val()==null || $("#nodalOfficerEmail").val().length<1){
									$("#nodalOfficerEmail").after(
									'<font color="red"><span class="error">Please must filled nodal officer email.</span></font>');
									return false;
								} 
								if (!regix.test($("#nodalOfficerEmail").val())) {
									$("#nodalOfficerEmail")
									.after(
									'<font color="red"><span class="error">Please Enter valid nodal officer email.</span></font>');
									return false;
								}
						});
					//	fetch concept details.
								/*function conceptlist(){


									getRequest('/api/concept/conceptlist',(data)=>{
										console.log(data);
										console.log("Concept list" + JSON.stringify(data));
										alert(data);
										var flow_table_head="";
										var flow_table_foot="";
										alert('welcome concept list');
										flow_table_head="<thead class=\"heading\"><tr class=\"bg-blue\"><th>Concept Id</th><th>Project Type</th><th>Funding Agencies</th><th>Project Name</th><th>State</th><th>Port-Major/Non-Major</th><th>Port Name</th><th>Project Location</th><th>Project Bref</th><th>Aims of objective</th><th>Scope of work</th><th>Estimate Cost</th><th>Start Date</th><th>End Date</th><th>Document</th><th>Edit</th></tr></thead>";
										flow_table_foot="<tfoot class=\"heading\"><tr class=\"bg-blue\"><th>Concept Id</th><th>Project Type</th><th>Funding Agencies</th><th>Project Name</th><th>State</th><th>Port-Major/Non-Major</th><th>Port Name</th><th>Project Location</th><th>Project Bref</th><th>Aims of objective</th><th>Scope of work</th><th>Estimate Cost</th><th>Start Date</th><th>End Date</th><th>Document</th><th>Edit</th></tr></tfoot>";
										var flow_details_record = "<tbody>";
										console.log('data :-',data.serviceResponse.conceptList);
										console.log('size :-',data.serviceResponse.totalSize);

										if(data.serviceResponse.customResponse.responseCode==200 && data.serviceResponse.totalSize>0){
											$("#conceptlist").append(flow_table_head);

											data.serviceResponse.conceptList.forEach(x=>{

												$("#conceptlist").append(`
														<tbody>
														<tr>
														<td>`+x.conceptId+`</td>
														<td>`+x.projectType+`</td>
														<td>`+x.idFundingAgencies+`</td>
														<td>`+x.projectName+`</td>
														<td>`+x.stateId+`</td>
														<td>`+x.portType+`</td>
														<td>`+x.portName+`</td
														<td>`+x.projectLocation+`</td>
														<td>`+x.projectBrief+`</td>
														<td>`+x.aimsAndObjectives+`</td>
														<td>`+x.scopeOfWork+`</td>
														<td>`+x.estimatedCost+`</td>
														<td>`+x.projectProponent+`</td>
														<td>`+x.startDate+`</td
														<td>`+x.endDate+`</td
												`);
											});
											$("#conceptlist").DataTable({"bDestroy":true});
											// $("#conceptlist").append(flow_table_foot);

										}


									});
								}
*/
						
						