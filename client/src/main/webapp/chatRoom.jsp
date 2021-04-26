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

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<style type="text/css">
	.container {
		display: flex;
		flex-direction: column;
		height: 100%;
		width: 100%;
	}
	
	.chatList {
	    position: relative;
		border: 1px solid lightgray;
		background-color: white;
		display: flex;
		align-items: center;
		padding: 10px;
		width: 90%;
		border-radius: 10px;
		margin-bottom: 20px;
		cursor: pointer;
	}
	
	.userInfo {
		padding: 5px;
		margin-left: 10px;
	}
	
	#timestamp {
		font-size: 10px;
		color: gray;
	}
	
	.msgNum {
		position: absolute;
		background-color: darkgreen;
		width: 35px;
		height: 35px;
		border-radius: 50%;
		float: right;
		right: 20px;
	}
	
	.msgNum span {
		position: absolute;
		left: 37%;
		bottom: 25%;
		color: white;
	}
</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript">
	function chatting(idx, roomId) {
		var guestId = document.getElementsByClassName('username')[idx].innerText;
		
		location.href = "chat.do?command=chatting&guestId=" + guestId + "&roomId="+ roomId;
	};
	
	
</script>

</head>
<body>


	<h3>채팅 방</h3>

	<div class="container">
		<c:choose>
			<c:when test="${empty chatRoomList }">
				<p>채팅이 없습니다.</p>
			</c:when>

			<c:otherwise>
				<c:forEach items="${chatRoomList }" var="dto" varStatus="status">
					<div class="chatList"
						onclick="chatting(${status.index },'${dto.roomid }')">
						
						<div class="avatar">
							<i class="fas fa-user-circle fa-3x"></i>
						</div>

						<div class="userInfo">
							<span class="username">${user.userid == dto.creatorid ? dto.guestid : dto.creatorid }</span>
							<span id="timestamp">timestamp</span>
							<p>This is a message</p>
						</div>

						<%--
						<div class="msgNum">
							<span>5</span>
						</div>
						
						 --%>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>




</body>
</html>