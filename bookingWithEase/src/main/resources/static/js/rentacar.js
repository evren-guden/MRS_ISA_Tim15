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

function hello() {
	$.ajax({
		type : 'GET',
		url : "/users/myprofile",
		dataType : "json",
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : function(data) {
			console.log(data);
		},
		error : function(data) {
			alert(data);
		}
	});
}

function findRentacars() {
	$.ajax({
		type : 'GET',
		url : "/rentacars",
		dataType : "json",
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : fillTable,
		error : function(data) {
			alert(data);
		}
	});
}

$(document).on('submit', '#formsrc', function(e) {
	e.preventDefault();

	var formData = getFormData("#formsrc");
	var jsonData = JSON.stringify(formData);
	$.ajax({
		type : 'POST',
		url : '/rentacars/search',
		contentType : 'application/json',
		dataType : 'json',
		data : jsonData,
		beforeSend : function(xhr) {
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

	var racsDiv = $('#racsDiv');
	racsDiv.empty();
	var counter = 0;

	$
			.each(
					rac_list,
					function(index,rac) {
						var racDiv = $('<div class="racDiv" id="racDiv_'
								+ counter
								+ '" style="bottom:'
								+ (60 - counter * 40)
								+ '%; top:'
								+ (3 + counter * 40)
								+ '%;"'
								+ '>'
								+ '<img src="../images/cars.jpg" height = 150 width= 150>'
								+ '<h3>' + rac.name + '</h3>');

						racDiv
								.append('<p>'
										+ rac.address
										+ '</p>'
										+ '<a href=""><img class="show_on_map" src="../images/show_on_map.png" height = 17 width= 18 ><div class="show_on_map">Show on map</div></a>'
										+ '</div>');
						racDiv
								.append('<div class="guest_ratings"> Guest ratings: '
										+ (rac.rating == null ? 0
												: rac.rating) + ' / 5 </div>');
						racDiv
								.append('<button id="show_vehicles_btn">Show vehicles</button>');

						counter++;
						racsDiv.append(racDiv);

					});

}