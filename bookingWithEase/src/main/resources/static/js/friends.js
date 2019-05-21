findFriends();

function findFriends() {
	$.ajax({
		type : 'GET',
		/* url : localStorage.getItem('showFlt') != null?"/flights/airline/" + localStorage.getItem('showFlt'): "/flights", */
		url : "/users/friends/" + 100,
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
	var friends_list = data == null ? []
			: (data instanceof Array ? data : [ data ]);

	var friendsDiv = $('#friendsDiv');
	friendsDiv.empty();
	
	var friendDiv = "";
	friendDiv += '<h2>All friends:</h2>';
	friendDiv += '<table id=friendTable><tr><td>First name</td><td>Last name</td><td>Invite</td></tr>';
	
	var counter = 0;
	
	$.each(friends_list, function(index, friend) {
		var friendTr = '<tr><td class=friendsRow>' + friend.firstName
				+ '</td><td class=friendsRow>' + friend.lastName
				+ '</td><td class=friendsRow><input type=checkbox name=fri' + counter + ' value=fr' + counter + '></td></tr>';
				
		counter++;
		friendDiv += friendTr;
	
	});
			
	friendDiv += '</table></br>';
	friendsDiv.append(friendDiv);
	
	friendsDiv.append('<button onclick="back();">Back</button>');
	friendsDiv.append('<button onclick="next();">Next</button>');
}

function back() {
	window.location.href = "seats.html";
}

function next() {
	window.location.href = "passengers.html";
}