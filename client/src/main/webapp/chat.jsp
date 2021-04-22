<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%
response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="resources/css/chat.css" />

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment-timezone/0.5.33/moment-timezone.min.js"></script>

</head>
<body>
	<div class="wrapper">
		<div class="user-container">
			<label for="user">대화명</label> 
			
			<input type="text" id="userId" value="${user.userid}" readonly /> 
				
			<label for="guest">상대방</label> 
			
			<input type="text" id="guestId" value="${guestId}" readonly />
		</div>

		<div class="display-container">
		
			<c:choose>
				<c:when test="${empty list }">
					<ul class="chatting-list"></ul>
				</c:when>
				
				<c:otherwise>
					<c:forEach items="${list }" var="dto">
						<li class=${dto.senderid == user.userid ? 'sent' : 'received' }>						
							<span class="profile">
		        				<span class="user">${dto.senderid}</span>
						        <img
						          class="image"
						          src="https://placeimg.com/50/50/any"
						          alt="any"
						        />
		      				</span>
		
		      				<span class="message"> ${dto.message} </span>
		
		      				<span class="time">${dto.senddate}</span>
	      				</li>
					</c:forEach>
					
					<ul class="chatting-list"></ul>
				</c:otherwise>
			</c:choose>
		
		</div>

		<div class="input-container">
			<span>
          		<input type="text" id="msg" class="chatting-input" />
          		<button class="send-button">전송</button>
        	</span>
		</div>
	</div>

	<script src="http://localhost:5000/socket.io/socket.io.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/qs/6.10.1/qs.min.js"></script>
	<script type="text/javascript" src="resources/js/chat.js"></script>
</body>
</html>