const socket = io("http://localhost:5000");

const videoGrid = document.getElementById('video-grid');
const chatForm = document.getElementById('chat-form');
const chatMessages = document.querySelector('.chat-messages');

let myVideoStream;
const myVideo = document.createElement('video');
myVideo.muted = true;

let text = document.getElementById('chat_message');


const { username, roomId } = Qs.parse(location.search, {
	ignoreQueryPrefix: true
});

history.replaceState({}, null, location.pathname);

var peer = new Peer();

const peers = {};

/*
	var peer = new Peer({
			config: {'iceServers': [
			{ url: 'stun:stun.l.google.com:19302' },
			{ url: 'turn:homeo@turn.bistri.com:80', credential: 'homeo' }
			]} 
	});
 */


navigator.mediaDevices.getUserMedia({
	audio: true,
	video: true
})
	.then((stream) => {
		myVideoStream = stream;
		addVideoStream(myVideo, stream);

		peer.on('call', call => {
			call.answer(stream);

			const video = document.createElement('video');

			call.on('stream', userVideoStream => {
				addVideoStream(video, userVideoStream);
			});
		});


		socket.on('user-connected', (userId) => {
			setTimeout(function() {
				connectToNewUser(userId, stream);
			}, 500);
		});
	});


peer.on('open', (userId) => {
	socket.emit('join-room', userId, { username, roomId });
	
	chatForm.addEventListener('submit', (e) => {
			e.preventDefault();

			// Get message text
			const msg = e.target.elements.msg.value;

			// Emit message to server
			socket.emit('chatMessage', userId, msg);

			// Clear input
			e.target.elements.msg.value = '';
			e.target.elements.msg.focus();

		});
});

socket.on('message', message => {
	outputMessage(message);
	
	chatMessages.scrollTop = chatMessages.scrollHeight;
});


socket.on('user-disconnected', (userId) => {
	if (peers[userId]) peers[userId].close();
});


const connectToNewUser = (userId, stream) => {
	const call = peer.call(userId, stream);
	const video = document.createElement('video');

	call.on('stream', (userVideoStream) => {
		addVideoStream(video, userVideoStream);
	});

	call.on('close', () => {
		video.remove();
	});

	peers[userId] = call;
};

const addVideoStream = (video, stream) => {
	video.srcObject = stream;

	video.addEventListener('loadedmetadata', () => {
		video.play();
	});

	videoGrid.append(video);
};

const outputMessage = (message) => {
	const div = document.createElement('div');
	div.classList.add('message');
	div.innerHTML = `<p class="meta"> ${message.username} <span>${message.time}</span></p>
					 <p class="text">${message.text}</p>
	`;

	document.querySelector('.chat-messages').appendChild(div);
};

const muteUnmute = () => {
  const enabled = myVideoStream.getAudioTracks()[0].enabled;
  if (enabled) {
    myVideoStream.getAudioTracks()[0].enabled = false;
    setUnmuteButton();
  } else {
    setMuteButton();
    myVideoStream.getAudioTracks()[0].enabled = true;
  }
};

const playStop = () => {
  let enabled = myVideoStream.getVideoTracks()[0].enabled;
  if (enabled) {
    myVideoStream.getVideoTracks()[0].enabled = false;
    setPlayVideo();
  } else {
    setStopVideo();
    myVideoStream.getVideoTracks()[0].enabled = true;
  }
};

const setMuteButton = () => {
  const html = `
    <i class="fas fa-microphone"></i>
    <span>Mute</span>
  `;
  document.querySelector('.main__mute_button').innerHTML = html;
};

const setUnmuteButton = () => {
  const html = `
    <i class="unmute fas fa-microphone-slash"></i>
    <span>Unmute</span>
  `;
  document.querySelector('.main__mute_button').innerHTML = html;
};

const setStopVideo = () => {
  const html = `
    <i class="fas fa-video"></i>
    <span>Stop Video</span>
  `;
  document.querySelector('.main__video_button').innerHTML = html;
};

const setPlayVideo = () => {
  const html = `
  <i class="stop fas fa-video-slash"></i>
    <span>Play Video</span>
  `;
  document.querySelector('.main__video_button').innerHTML = html;
};


//Prompt the user before leave chat room
document.getElementById('leave-btn').addEventListener('click', () => {
	const leaveRoom = confirm('강의실을 나가시겠습니까?');
	if (leaveRoom) {
		window.location = 'index.html';
	} else {
	}
});

