$(document).on('submit', '#loginform', function(e) {
	
	e.preventDefault();
	
	var formData = getFormData("#loginform");
	var jsonData = JSON.stringify(formData);

	$.ajax({
		url : "/users/login",
		type : "POST",
		contentType : "application/json",
		data : jsonData,
		dataType : 'json',
		success : function(response) {
			saveUser(response);
			//window.location.href = "rentacar.html";
		},
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});

});

function saveUser(data){
	console.log(data);
	if(sessionStorage){
	    // Store data
		sessionStorage.setItem("id", data.id);
	    sessionStorage.setItem("username", data.username);
	    sessionStorage.setItem("firstname", data.firstName);
	    sessionStorage.setItem("lastname", data.lastName);
	    sessionStorage.setItem("role", data.authorities[0].authority);
	    // Retrieve data
	   // alert("Hi, " + sessionStorage.getItem("firstname") + " " + sessionStorage.getItem("lastname"));
	    
	    if(sessionStorage.getItem("role") === "ROLE_ADMINRAC"){
	    	window.location.replace("homePageRAC.html");
	    }else if(sessionStorage.getItem("role") === "ROLE_ADMINHOTEL"){
	    	window.location.replace("homePageHotel.html");
	    }else if(sessionStorage.getItem("role") === "ROLE_ADMINAIRLINE"){
	    	window.location.replace("homePageAirline.html");
	    }
	    
	} else{
	    alert("Sorry, your browser do not support session storage.");
	}
}