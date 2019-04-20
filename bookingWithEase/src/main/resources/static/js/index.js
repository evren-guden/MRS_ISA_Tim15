$(document).on('click', '#loginbutton', function(e){
    e.preventDefault();
    window.location.href = "login.html";
});


$(document).on('click', '#new_company', function(e){
    e.preventDefault();
    window.location.href = "companyRegistration.html";
});

$(document).on('click', '#new_admin', function(e){
    e.preventDefault();
    window.location.href = "adminRegistration.html";
});

$(document).on('click', '#users', function(e){
    e.preventDefault();
    window.location.href = "users.html";
});

$(document).on('click', '#airlines', function(e){
    e.preventDefault();
    window.location.href = "airlines.html";
});

$(document).on('click', '#hotels', function(e){
    e.preventDefault();
    window.location.href = "hotels.html";
});

$(document).on('click', '#rent-a-car', function(e){
    e.preventDefault();
    window.location.href = "rentacar.html";
});

$(document).on('click', '#vehicles', function(e){
    e.preventDefault();
    window.location.href = "vehicles.html";
});

$(document).on('click', '#flights', function(e){
    e.preventDefault();
    window.location.href = "flights.html";
});

function homepage()
{
	window.location.href = "index.html";
}

function login_clicked()
{
	window.location.href = "login.html";
}

function registration_clicked()
{
	alert("registration");
}

