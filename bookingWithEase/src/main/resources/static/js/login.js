function showPswFunction(){
	 var x = document.getElementById("loginpsw");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
}

$(document).on('submit', '#loginform', function(e) {
	
	e.preventDefault();
	
	var formData = getFormData("#loginform");
	var jsonData = JSON.stringify(formData);

	$.ajax({
		url : "/auth/login",
		type : "POST",
		contentType : "application/json",
		data : jsonData,
		dataType : 'text',
		success : function(response) {
			saveToken(response);
		},
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});

});

function saveToken(data){
	console.log(data);
	if(localStorage){
		console.log(data);
		setJwtToken(data);

		$.ajax({
			type : 'GET',
			url : "/users/myprofile",
			dataType : "json",
			beforeSend: function (xhr) {
		        /* Authorization header */
		        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		    },
			success : function(data){
				if(data.authorities[0].authority === "ROLE_ADMINRAC"){
					window.location.href = "homePageRAC.html";
				}else if(data.authorities[0].authority === "ROLE_ADMINHOTEL"){
					window.location.href = "homePageHotel.html";
				}else if(data.authorities[0].authority === "ROLE_ADMINAIRLINE"){
					window.location.href = "homePageAirline.html";
				}else if(data.authorities[0].authority === "ROLE_ADMIN"){
					window.location.href = "homePageAdmin.html";
				}else if(data.authorities[0].authority === "ROLE_USER"){
					window.location.href = "homePageUser.html";
				}
			},
			error : function(data) {
				alert(data);
			}
		});
	} else{
	    alert("Sorry, your browser do not support session storage.");
	}
}