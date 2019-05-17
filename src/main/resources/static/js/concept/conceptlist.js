function onClickConceptfunction(data) {
    var idArr = data.split("^");
  // alert("welcome "+idArr[0]);
   //alert("welcome "+idArr[1]);
   switch (idArr[0]) {
   case "conceptedit":
		getRequest('/edit-concept/' + idArr[1], listPage);
		break;
   }

}
/**
 * @author: Abhishek Tyagi
 * @purpose: Fetch concept details and display list in concept list table.
 * @createdOn: 03/14/2019
 * @param: data
 * @ModifyBy:
 */
function conceptlist(data){
    getRequest('/api/concept/conceptlist',(data)=>{
    	console.log(data);
    	console.log("Concept list" + JSON.stringify(data));
    	var flow_table_head="";
    	var flow_table_foot="";
    	flow_table_head="<thead class=\"heading\"><tr class=\"bg-blue\"><th>Concept Id</th><th>Project Type</th><th>Funding Agencies</th><th>Project Name</th><th>State</th><th>Port-Major/Non-Major</th><th>Port Name</th><th>Project Bref</th><th>Aims of objective</th><th>Scope of work</th><th>Estimate Cost</th><th>Project Proponent</th><th>Start Date</th><th>End Date</th><th>Document</th><th>Project Location</th></tr></thead>";
    	flow_table_foot="<tfoot class=\"heading\"><tr class=\"bg-blue\"><th>Concept Id</th><th>Project Type</th><th>Funding Agencies</th><th>Project Name</th><th>State</th><th>Port-Major/Non-Major</th><th>Port Name</th><th>Project Bref</th><th>Aims of objective</th><th>Scope of work</th><th>Estimate Cost</th>><th>Project Proponent</th><th>Start Date</th><th>End Date</th><th>Document</th><th>Project Location</th></tr></tfoot>";
    	 var flow_details_record = "<tbody>";
    	 console.log('data :-',data.serviceResponse.conceptList);
    	 console.log('size :-',data.serviceResponse.totalSize);    	
    	 if(data.serviceResponse.customResponse.responseCode==200 && data.serviceResponse.totalSize>0){
    		 $("#conceptlist").append(flow_table_head);
        	 data.serviceResponse.conceptList.forEach(x=>{
        		 x=convertObjectValuesRecursive(x);
        		 var agenciesList=x.idFundingAgencies;
        		 var stateList=x.stateId;
        		 var portList=x.portName;
        		 var projLocList=x.projectLocation;
        		 var propenentList=x.projectProponent;
        		 var stateArray;
        		 var proLocArray;
        		 var propenentArray;
        		//For State
        		 var states=new Array();
        		 if(stateList!=null && stateList!=""){
        			stateArray = stateList.split(',');
        			for(var i = 0; i < stateArray.length; i++){
        				states[i]=$("#state1 option[value="+stateArray[i]+"]").text()
        			}
        	     }
        		//for ports
        		 var ports=new Array();        		
        		if(portList!=null && portList!=""){
        			var portArray = portList.split(',');
        			for(var i = 0; i < portArray.length; i++){
        				ports[i]=$("#port1 option[value="+portArray[i]+"]").text()
        			}
        		}
        		//for project Propenents 
        		var projProps=new Array();
        		if(propenentList!=null && propenentList!=""){
        			propenentArray=propenentList.split(',');
        			for(var i = 0; i < propenentArray.length; i++){
        				projProps[i]=$("#props option[value="+propenentArray[i]+"]").text()
        			}
        		}
        		//for project Location.
        		var projLocs=new Array();
        		if(projLocList!=null && projLocList!=""){
        			proLocArray=projLocList.split(',');
        			for(var i = 0; i < proLocArray.length; i++){
           			 	projLocs[i]=$("#locs option[value="+proLocArray[i]+"]").text()
           		 	}
        		}
        		//for agencies
        		 var agencies=new Array();
        		 if(agenciesList!=null && agenciesList!=""){
        			 var arrayAgencies = agenciesList.split(',');
            		 for(var i = 0; i < arrayAgencies.length; i++){
            			 agencies[i]=$("#ages option[value="+arrayAgencies[i]+"]").text()
            		 }
        		 }
        		 //for Estimate cost        		 
        		 if(x.estimatedCost=="0"){
        			 x.estimatedCost="";
        		 }
        		 else{
        			 x.estimatedCost=x.estimatedCost+'.00';
        		 }
        		 //for start and end date       		 
        		 if(x.startDate=="1600-01-01"&&x.endDate=="1600-01-01"){
        			 x.startDate="";
        			 x.endDate="";
        		 }      	
        		 $("#conceptlist").append(`
			              <tbody>
			                <tr>
			                 <td><a  href="javascript:void(0);" onclick="onClickConceptfunction('conceptedit^`+x.conceptId+`');">
			                   `+x.conceptId+`
			                  </a></td>
			                  <td>`+x.projectType+`</td>
			                  <td id="agenc">`+agencies+`</td>
			                  <td>`+x.projectName+`</td>
			                  <td>`+states+`</td>
			                  <td>`+x.portType+`</td>
			                  <td>`+ports+`</td>
			                  <td>`+x.projectBrief+`</td>
			                  <td>`+x.aimsAndObjectives+`</td>
			                  <td>`+x.scopeOfWork+`</td>
			                  <td>`+ x.estimatedCost+`</td>
			                  <td>`+projProps+`</td>
			                  <td>`+x.startDate+`</td>
			                  <td>`+x.endDate+`</td>
			                  <td>`+x.document+`</td>
			                  <td>`+projLocs+`</td>
			                  `);
        	 });
        	
        	 $("#conceptlist").DataTable({
        		 "bDestroy":true,
        		 "lengthMenu":[[5,10,25,-1],['5 Records','10 Records','25 Records','ALL']],
        		 "pageLength":5
        	 });
        	 
        	 
    	 }
    	
    	 
    });
}
/**
 * @author: Abhishek Tyagi
 * @purpose: Replace all json null value with blank..
 * @createdOn: 03/14/2019
 * @param: data
 * @ModifyBy:
 */
function convertObjectValuesRecursive(data) {
	data = {...data};
	Object.keys(data).forEach((key) => {
		if (data[key] == null) {
			data[key] = "";
		} else if (typeof data[key] == 'object' && !Array.isArray(data[key])) {
			data[key] = convertObjectValuesRecursive(data[key]);
		}
	});
	return data;
}

function rejectConcept(){
	$.confirm({
	    title: 'Revive!!!',
	    content: 'Do u want to revive!!!',
	    
	    buttons: {
	        yes: function () {
	        	console.log('welcome');
	            alert('welcome');
	        },
	        no: function () {
	        	console.log('Hiii');
	            alert('Hiii');
	        }/*,
	        somethingElse: {
	            text: 'Something else',
	            btnClass: 'btn-blue',
	            keys: ['enter', 'shift'],
	            action: function(){
	                $.alert('Something else?');
	            }
	        }*/
	    }
	});
	           
}
$( "#approve" ).click(function() {
	 var userdata=JSON.parse(localStorage.getItem('user_data'));
	var status=$("#status").val();
	//alert($("#id").val())
	var id=$("#id").val();
	
	var date=new Date();
	var remark = prompt("Enter Remark*");
	if(remark!=null){
		var passData={
				"id":id,
				"status":'A',
				"remark":remark,
				"updatedBy": userdata.userName
				//"updateDate":date
		};
		console.log(passData);
		postRequest('/api/concept/updatestatusAndRemarkById', JSON.stringify(passData), 
				function changeStatus(data){
			alert("change status successfully");
		});
		//alert(passData);
	}else{
		alert("Please Enter Remark !!!");
	}
	
});
/****
 ** Send for Revision.
 ****/
$( "#revision" ).click(function() {
	 var userdata=JSON.parse(localStorage.getItem('user_data'));
	var status=$("#status").val();
	//alert($("#id").val())
	var id=$("#id").val();
	
	var date=new Date();
	var remark = prompt("Enter Remark*");
	if(remark!=null){
		var passData={
				"id":id,
				"status":'R',
				"remark":remark,
				"updatedBy": userdata.userName
				//"updateDate":date
		};
		console.log(passData);
		postRequest('/api/concept/updatestatusAndRemarkById', JSON.stringify(passData), 
				function changeStatus(data){
			alert("change status successfully");
		});
		//alert(passData);
	}else{
		alert("Please Enter Remark !!!");
	}
	
});
/*function changeStatus(){
	var userdata=JSON.parse(localStorage.getItem('user_data'));
	 var status=$("#status").val();
}
*/