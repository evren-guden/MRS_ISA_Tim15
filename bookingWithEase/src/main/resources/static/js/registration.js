$(document).on('submit', '#registrationform', function(e) {

	e.preventDefault();

	var formData = getFormData("#registrationform");
	var jsonData = JSON.stringify(formData);

	$.ajax({
		url : "/users/registration",
		type : "POST",
		contentType : "application/json",
		data : jsonData,
		dataType : 'json',
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Authorization", "Bearer "
					+ getJwtToken());
		},
		success : function() {
			window.location.href="login.html";
		},
		statusCode : {
			401 : function() {
				alert('Bad credentials');
			},
			422 : function() {
				alert('Please enter all required fields');
			}
		}
	});

});