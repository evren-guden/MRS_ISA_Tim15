findVehicles();

function fillSelect() {
	$.ajax({
		type : 'GET',
		url : "/rentacars/" + localStorage.getItem("userCompanyId")
				+ "/branchs",
		dataType : "json",
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : fillOptions,
		error : function(data) {
			alert(data);
		}
	});
}

function fillOptions(data) {

	var opts = data == null ? [] : (data instanceof Array ? data : [ data ]);

	var select = $(localStorage.getItem('selectId'));

	$.each(opts, function(index, branch) {

		var option = $('<option name="' + branch.id + '" value="' + branch.id
				+ '">' + branch.name + '<option>');
		select.append(option);
	});
	localStorage.removeItem('selectId');
}

function openDiv(evt, divName) {

	if (divName === 'addVehicleDiv') {
		localStorage.setItem("selectId", '#selectbranch');
		fillSelect();
	}

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
	logout();
});

$(document).on('submit', '#addVehicleForm', function(e) {

	e.preventDefault();
	var formData = getFormData("#addVehicleForm");
	var jsonData = JSON.stringify(formData);
	console.log("Token sent " + getJwtToken());
	var iden = $('#selectbranch').val();
	if (iden === ""){
		alert ('branch null');
		return;
	}
	$.ajax({
		url : "/branchs/" + iden + "/vehicles",
		type : "POST",
		contentType : "application/json",
		data : jsonData,
		dataType : 'json',
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : function(response, HttpStatus) {
			console.log(HttpStatus);
			findVehicles();
		},
		statusCode: {
		    409: function() {
		        alert('Already exists');
		    },
		    422: function() {
		        alert('Please enter all required fields');
		    },
			500: function() {
		        alert('Internal server error');
		    }
		},
	});

});

$(document).on('submit', '#editVehicleForm', function(e) {

	e.preventDefault();

	var formData = getFormData("#editVehicleForm");
	var jsonData = JSON.stringify(formData);

	$.ajax({
		url : "/vehicles",
		type : "PUT",
		contentType : "application/json",
		data : jsonData,
		dataType : 'json',
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : findVehicles,
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});

});

$(document).on(
		'submit',
		'#addBranchForm',
		function(e) {

			e.preventDefault();

			var formData = getFormData("#addBranchForm");
			var jsonData = JSON.stringify(formData);
			console.log("Token sent " + getJwtToken());
			$.ajax({
				url : "/rentacars/" + localStorage.getItem("userCompanyId")
						+ "/branchs",
				type : "POST",
				contentType : "application/json",
				data : jsonData,
				dataType : 'json',
				beforeSend : function(xhr) {
					xhr.setRequestHeader("Authorization", "Bearer "
							+ getJwtToken());
				},
				success : findBranchs,
				error : function(response) {
					alert("Something went wrong! :(");
				}
			});

		});

$(document).on('submit', '#editBranchForm', function(e) {

	e.preventDefault();

	var formData = getFormData("#editBranchForm");
	var jsonData = JSON.stringify(formData);

	$.ajax({
		url : "/branchs",
		type : "PUT",
		contentType : "application/json",
		data : jsonData,
		dataType : 'json',
		beforeSend : function(xhr) {
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
		url : "/rentacars/" + localStorage.getItem("userCompanyId")
				+ "/branchs",
		dataType : "json",
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : fillBranchTable,
		error : function(data) {
			alert(data);
		}
	});
}

function fillBranchTable(data) {
	var br_list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$('#tableCap').html("My branchs");
	$('#branchSearch').empty();
	var table = $('#branchTable');
	$('#branchTable').empty();
	$('#branchTable').append(
			'<tr><th>Id</th><th>Name</th><th>Address</th></tr>');

	$
			.each(
					br_list,
					function(index, branch) {

						var tr = $('<tr></tr>');
						var form = $('<td>'
								+ branch.id
								+ '</td><td>'
								+ branch.name
								+ '</td><td>'
								+ branch.address
								+ '</td><td><button class="beditBtns" id="beditbtn'
								+ branch.id
								+ '">Edit</button></td><td><button class="bdelBtns" id="bdelBtn'
								+ branch.id + '">Delete</button></td>');
						tr.append(form);
						table.append(tr);
					});

	$('.bdelBtns').on(
			'click',
			function(e) {
				e.preventDefault();
				var iden = this.id.substring(7);
				console.log(iden);

				$.ajax({
					type : 'delete',
					url : "/branchs/" + iden,
					beforeSend : function(xhr) {
						xhr.setRequestHeader("Authorization", "Bearer "
								+ getJwtToken());
					},
					success : function(response) {
						findBranchs();
					},
					error : function(data) {
						alert(data);
					}
				});

			});

	$('.beditBtns').on(
			'click',
			function(e) {
				e.preventDefault();
				var iden = this.id;
				openDiv(event, 'editBranchDiv');
				localStorage.setItem("editBranchId", iden);

				$.ajax({
					type : 'GET',
					url : "/branchs/" + iden.substring(8),
					dataType : "json",
					beforeSend : function(xhr) {
						xhr.setRequestHeader("Authorization", "Bearer "
								+ getJwtToken());
					},
					success : function(data) {
						$('#editIdBr').val(data.id);
						$('#editNameBr').val(data.name);
						$('#editAddrBr').val(data.address);
					},
					error : function(data) {
						alert(data);
					}
				});

			});

}

$(document).on('click', '#myvehiclesbtn', function(e) {
	findVehicles();
});

function findVehicles() {

	$.ajax({
		type : 'GET',
		url : "/vehicles",
		dataType : "json",
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : fillVehicleTable,
		error : function(data) {
			alert(data);
		}
	});
}

function fillVehicleTable(data) {
	var vh_list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$('#tableCap').html("My Vehicles");
	$('#branchSearch').empty();
	var select = $('<select id="selectBranchSearch"><option> </option></select>')
	$('#branchSearch').append('Branch: ');
	$('#branchSearch').append(select);
	localStorage.setItem("selectId", '#selectBranchSearch')
	fillSelect();

	$('#selectBranchSearch').change(
			function() {
				var value = $(this).val();
				if (!$.trim(value)) {
					findVehicles();
				} else {
					$.ajax({
						type : 'GET',
						url : "/branchs/" + value + "/vehicles",
						dataType : "json",
						beforeSend : function(xhr) {
							xhr.setRequestHeader("Authorization", "Bearer "
									+ getJwtToken());
						},
						success : fillVehicleTable,
						error : function(data) {
							alert(data);
						}
					});
				}
			});

	var table = $('#branchTable');
	$('#branchTable').empty();
	$('#branchTable')
			.append(
					'<tr><th>Id</th><th>Registration number</th><th>Type</th><th>Gear</th><th>Color</th></tr>');

	$
			.each(
					vh_list,
					function(index, vehicle) {

						var tr = $('<tr></tr>');
						var form = $('<td>'
								+ vehicle.id
								+ '</td><td>'
								+ vehicle.registrationNumber
								+ '</td><td>'
								+ vehicle.type
								+ '</td><td>'
								+ vehicle.gear
								+ '</td><td>'
								+ vehicle.color
								+ '</td><td><button class="veditBtns" id="veditbtn'
								+ vehicle.id
								+ '">Edit</button></td><td><button class="vdelBtns" id="vdelBtn'
								+ vehicle.id + '">Delete</button></td>');
						tr.append(form);
						table.append(tr);
					});

	$('.vdelBtns').on(
			'click',
			function(e) {
				e.preventDefault();
				var iden = this.id.substring(7);
				console.log(iden);

				$.ajax({
					type : 'delete',
					url : "/vehicles/" + iden,
					beforeSend : function(xhr) {
						xhr.setRequestHeader("Authorization", "Bearer "
								+ getJwtToken());
					},
					success : function(response) {
						findVehicles();
					},
					error : function(data) {
						alert(data);
					}
				});

			});

	$('.veditBtns').on(
			'click',
			function(e) {
				e.preventDefault();
				var iden = this.id;
				openDiv(event, 'editVehicleDiv');

				$.ajax({
					type : 'GET',
					url : "/vehicles/" + iden.substring(8),
					dataType : "json",
					beforeSend : function(xhr) {
						xhr.setRequestHeader("Authorization", "Bearer "
								+ getJwtToken());
					},
					success : function(data) {
						$('#editIdVh').val(data.id);
						$('#editRegVh').val(data.registrationNumber);
						$('#editTypeVh').val(data.type);
						$('#editGearVh').val(data.gear);
						$('#editColorVh').val(data.color);
					},
					error : function(data) {
						alert(data);
					}
				});

			});

}
