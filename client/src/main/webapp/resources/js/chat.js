/**
 * 
 */
'use strict';


const socket = io("http://localhost:5000");

var text, time;

const {roomId} = Qs.parse(location.search, {
	ignoreQueryPrefix: true
});

//history.replaceState({}, null, location.pathname);

const userId = document.querySelector('#userId').value;
const guestId = document.querySelector('#guestId').value;
const chatList = document.querySelector('.chatting-list');
const displayContainer = document.querySelector('.display-container');
const chatInput = document.querySelector('.chatting-input');
const sendBtn = document.querySelector('.send-button');


socket.emit('joinChatRoom',  {userId, roomId} );

socket.on('message', message => {	
	outputMessage(message);
	
	//chatList.scrollTop = chatList.scrollHeight;
	displayContainer.scrollTo(0, displayContainer.scrollHeight);
});

chatInput.addEventListener('keypress', (e) => {
  if (e.keyCode === 13) {
    send();
  }
});

sendBtn.addEventListener('click', () => {
  send();
});


function send(){
	text = chatInput.value;

	moment.tz.setDefault("Asia/Seoul"); 
	time = moment().format("YYYY-MM-DD HH:mm:ss");
	
	
	const msg = chatInput.value;
	
	socket.emit('chatMessage', msg, time);
	
	chatInput.value = '';
	chatInput.focus();
}


function outputMessage(message) {
	
	const li = document.createElement('li');
	
    li.classList.add(userId === message.userId ? 'sent' : 'received');

	li.innerHTML = 
	`<span class="profile">
        <span class="user">${message.userId}</span>
        <img
          class="image"
          src="https://placeimg.com/50/50/any"
          alt="any"
        />
      </span>

      <span class="message"> ${message.text} </span>

      <span class="time">${message.time}</span>`;
	
	chatList.appendChild(li);
	
};

function getParameterValues() {
	return "?command=sendMsg&roomId="+roomId+"&fromId="+userId+"&toId="+guestId+"&text="+text+"&time="+time;
}

$(function(){
	/*
	$(".send-button").click(function(){
		var queryString = getParameterValues();
		
		$.ajax({
			url: "chat.do"+queryString,
			success: function(){
				console.log("성공");
			},
			error: function(){
				console.log("실패");
			}
		});
	});
	*/
	

	$('.chatting-input').keypress(function(event){
     if ( event.which == 13 ) {
        var queryString = getParameterValues();
		
		$.ajax({
			url: "chat.do"+queryString,
		
			success: function(){
				console.log("성공");
			},
			error: function(){
				console.log("실패");
			}
		});
     }
	});

})

