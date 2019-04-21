fillSelect();

function fillSelect(){
	$.ajax({
		type : 'GET',
		url : "/rentacars/"+ localStorage.getItem("userCompanyId")+"/branchs",
		dataType : "json",
		beforeSend: function (xhr) {
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : fillOptions,
		error : function(data) {
			alert(data);
		}
	});
}

function fillOptions(data){
	
	var opts = data == null ? []
	: (data instanceof Array ? data : [ data ]);
	
	var select = $('#selectbranch');
	
	$.each(opts, function(index, branch) {

		var option = $('<option name="' + branch.id + '">' + branch.name + '<option>');
		select.append(option);
	});
}

function openDiv(evt, divName) {
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}
	$('#' + divName).slideToggle(1000);
	evt.currentTarget.className += " active";
}

$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	localStorage.clear();
	window.location.href = "login.html";
});

$(document).on('submit', '#addVehicleForm', function(e) {

	e.preventDefault();

	var formData = getFormData("#addVehicleForm");
	var jsonData = JSON.stringify(formData);
	console.log("Token sent " + getJwtToken());
	$.ajax({
		url : "/branchs/" + "",
		type : "POST",
		contentType : "application/json",
		data : jsonData,
		dataType : 'json',
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : function(response) {
			alert("Vehicle saved :)");
			window.location.href = "vehicles.html";
		},
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});

});

$(document).on('submit', '#addBranchForm', function(e) {

	e.preventDefault();

	var formData = getFormData("#addBranchForm");
	var jsonData = JSON.stringify(formData);
	console.log("Token sent " + getJwtToken());
	$.ajax({
		url : "/rentacars/" + localStorage.getItem("userCompanyId") + "/branchs",
		type : "POST",
		contentType : "application/json",
		data : jsonData,
		dataType : 'json',
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : findBranchs,
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});

});

$(document).on('click', '#mybranchsbtn', function(e) {
	findBranchs();
});


function findBranchs() {
	
	$.ajax({
		type : 'GET',
		url : "/users/myprofile",
		dataType : "json",
		beforeSend: function (xhr) {
	        /* Authorization header */
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : function(data){
			//localStorage.setItem(data.);
			console.log(data);
		},
		error : function(data) {
			alert(data);
		}
	});
	
	$.ajax({
		type : 'GET',
		url : "/rentacars/"+ localStorage.getItem("userCompanyId")+"/branchs",
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

function fillTable(data) {
	var br_list = data == null ? []
			: (data instanceof Array ? data : [ data ]);
	var table = $('#branchTable');
	$('#branchTable').empty();
	$('#branchTable')
			.append(
					'<tr><th>Id</th><th>Name</th><th>Address</th></tr>');

	$.each(br_list, function(index, branch) {

		var tr = $('<tr></tr>');
		var form = $('<td><form class="bformsedit" id="form' + branch.id
				+ '"><input name="ident" value=' + branch.id
				+ ' readonly></form></td><td><input name="name" form="form'
				+ branch.id + '" value="' + branch.name
				+ '"></td><td><input name="address" form="form' + branch.id
				+ '" value="' + branch.address
				+ '"></td><td><input type="submit" value="Update" form="form' + branch.id
				+ '" id="bform' + branch.id
				+ '"></td><td><button class="bdelBtns" id="bdelBtn' + branch.id
				+ '">Delete</button></td>');
		tr.append(form);
		table.append(tr);
	}

	);

	$('.bdelBtns').on('click', function(e) {
		e.preventDefault();
		var iden = this.id.substring(7);
		console.log(iden);

		$.ajax({
			type : 'post',
			url : "/branchs/delete/" + iden,
			success : function(response) {
				// alert("Vehicle deleted :)");
				window.location.href = "branchs.html";
			},
			error : function(data) {
				alert(data);
			}
		});

	});

	$('.bformsedit').on('submit', function(e) {
		e.preventDefault();
		var iden = this.id;
		// var formData = getFormData(iden);

		var formData = {};
		var s_data = $('#' + this.id).serializeArray();

		for (var i = 0; i < s_data.length; i++) {
			var record = s_data[i];
			if (record.name === "ident") {
				formData["id"] = record.value;
			} else {
				formData[record.name] = record.value;
			}
		}

		var jsonData = JSON.stringify(formData);
		$.ajax({
			type : 'post',
			url : "/branchs/edit",
			contentType : 'application/json',
			dataType : 'json',
			data : jsonData,
			success : findBranchs,
			error : function(data) {
				alert(data);
			}
		});
	});

}

