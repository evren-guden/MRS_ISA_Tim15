$(document).ready(function() {
	
	// ucitavanje podataka o letovima
	ucitajPodatke("../flights/deliveryAll", "flightsDisplay");
	
	
	// dodavanje leta
	$("#addFlightForm").submit(function(e) {
		e.preventDefault();
		
		let id = $("#ID").val(); //numFlight
		let startDestination = $("#startDestination").val();
		let endDestination = $("#endDestination").val();
		let dateFligh = $("#dateFligh").val();
		let dateLand = $("#dateLand").val();
		let lengthTravel = $("#lengthTravel").val();
		let transitions = $("#numberTransitions").val();
		
		let priceTicket = $("#price").val();
		
		
		
		if (id == "" || startDestination == "" || endDestination == "" || dateFligh == "" || dateLand == "" ||
				lengthTravel == "" || transitions == "" || price == "") {
			alert("Polja ne smeju biti prazna. Pokušajte ponovo.");
			return;
		}
		
		let flight = {
				id : id,
				startDestination:startDestination,
				endDestination:endDestination,
				dateTimeFligh: dateFligh,
				DateTimeLand: dateLand,
				lengthTravel: lengthTravel,
				numberTransitions: {transitions: transitions},
				priceTicket : price,
				reservation: []
		};
		
		$.ajax({
			type: "POST",
			url: "../flights/add",
			contentType : "application/json; charset=utf-8",
			data: JSON.stringify(flight),
			success: function(response) {
				if(response == true) {
					let tabelaLetova = $("#flightsDisplay");
					prikazi(flight, tabelaLetova);
				} else {
					alert("Već postoji let sa zadatim brojem leta. Pokušajte ponovo.");
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("AJAX error: " + errorThrown);
			}
		});
	});
	
})


function ucitajPodatke(putanjaController, idTabeleZaPrikaz) {
	let tabela = $("#" + idTabeleZaPrikaz);
	$.ajax({
		type: "GET",
		url: putanjaController,
		success: function(response) {
			$.each(response, function(i, podatak) {
				prikazi(podatak, tabela);
			});
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX error: " + errorThrown);
		}
	});
}

function prikazi(podatak, tabelaZaPrikaz) {
	let noviRed = $("<tr></tr>");
	noviRed.append("<td>" + podatak.id + "</td>");
	noviRed.append("<td>" + podatak.startDestination + "</td>");
	noviRed.append("<td>" + podatak.endDestination + "</td>");
	
	noviRed.append("<td>" + podatak.dateFligh + "</td>");
	noviRed.append("<td>" + podatak.dateLand + "</td>");
	noviRed.append("<td>" + podatak.lengthTravel + "</td>");
	noviRed.append("<td>" + podatak.numberTransitions.transitions + "</td>");
	noviRed.append("<td>" + podatak.priceTicket + "</td>");
	
	
	noviRed.append("<td>" + podatak. + "</td>");

	tabelaZaPrikaz.append(noviRed);
}