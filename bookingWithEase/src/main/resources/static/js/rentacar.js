
findRentacars();

function showHideSearch() {
	var x = document.getElementById("div-rac-search");
	if (x.style.display === "none") {
		x.style.display = "block";
		document.getElementById("showHideBtn").innerText = "Hide search";
	} else {
		x.style.display = "none";
		document.getElementById("showHideBtn").innerText = "Show search";
	}
}

function hello(){
	$.ajax({
		type : 'GET',
		url : "/users/myprofile",
		dataType : "json",
		beforeSend: function (xhr) {
	        /* Authorization header */
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : function(data){
			console.log(data);
			
		},
		error : function(data) {
			alert(data);
		}
	});
}

function findRentacars() {
	if(sessionStorage){

	    // Retreve data
	    //alert("Hi, " + sessionStorage.getItem("firstname") + " " + sessionStorage.getItem("lastname"));
}
	$.ajax({
		type : 'GET',
		url : "/rentacars",
		dataType : "json",
		beforeSend: function (xhr) {
	        /* Authorization header */
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : fillTable,
		error : function(data) {
			alert(data);
		}
	});
}

$(document).on('submit', '#form-src', function(e) {
	e.preventDefault();
	
	var formData = getFormData("#form-src");
	var jsonData = JSON.stringify(formData);
	$.ajax({
		type : 'POST',
		url :'/rentacars/search',
		contentType : 'application/json',
		dataType : 'json',
		data : jsonData,
		beforeSend: function (xhr) {
	        /* Authorization header */
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : fillTable
	});

});

function formToJSON() {
	var id = $('#src-rac-id').val();
	var name = $('#src-rac-name').val();
	var addr = $('#src-rac-addr').val();
	console.log(JSON.stringify({
		"id" : id,
		"name" : name,
		"address" : addr
	}));
	return JSON.stringify({
		"id" : id,
		"name" : name,
		"address" : addr
	});
}

function fillTable(data) {
	var rac_list = data == null ? []
			: (data instanceof Array ? data : [ data ]);
	$('#tab-rac').empty();
	$('#tab-rac').append('<tr><th>Id</th><th>Name</th><th>Address</th></tr>');
	$.each(rac_list, function(index, rac) {

		var tr = $('<tr></tr>');
		tr.append('<td>' + rac.id + '</td>' + '<td>'
				+ rac.name + '</td>' + '<td>'
				+ rac.address + '</td>');

		$('#tab-rac').append(tr);

	});

}