/**
 * 
 */

function guid() {
	function s4() {
		return ((1 + Math.random()) * 0x10000 | 0).toString(16)
			.substring(1);
	}
	return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4()
		+ s4() + s4();
};

function show_uuid() {

	document.getElementById('uuid').value = guid();
};

/*
function goToVideo() {
	var username = document.getElementsByName('username')[0].value;
	var roomId = document.getElementsByName('roomId')[0].value;

	location.href = "video.html?username=" + username + "&roomId=" + roomId;
};

*/