$(document).ready(function() {
    getHotels();
});


function getHotels()
{
	$.ajax({
		url : "/hotels",
		type : "GET",
		dataType : 'json',
		success : function(response) {
			
		},
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});
}