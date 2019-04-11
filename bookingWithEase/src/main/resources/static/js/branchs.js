findBranchs();

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
