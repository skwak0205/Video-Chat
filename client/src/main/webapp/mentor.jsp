<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	.chat-icon {
		margin-left: 20px;
		cursor:pointer;
	}
	

</style>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<script type="text/javascript">
		function chatting(idx, username){	
			var menteeId = document.getElementsByName("menteeId")[idx];
			
			
			if(menteeId.value !=null) {
				var roomId = username+"_"+menteeId.value;
				
				open("chat.do?command=chatting&guestId="+menteeId.value+"&roomId="+roomId, "", "width=500, height=500");
			}
	
		}
		
		function chatRoom(){
			open("chat.do?command=chatRoom", "", "width=500, height=500");
		}
	
	
</script>



</head>
<body>
	<h1>Welcome Mentor ${user.userid}</h1>
	
	<button onclick="location.href='video.do?command=videoCreate'">화상 강의 방 만들기</button>
	
	<span onclick="chatRoom()" class="chat-icon"><i class="fab fa-rocketchat fa-3x" ></i></span>
	
	<h3>멘티 목록</h3>
	<table border="1">
		<col width="100" />
		<col width="100" />
		<col width="150" />
		<col width="150" />
		<col width="50"  />
		
		<tr>
			<th>Mentee ID</th>
			<th>Mentee 이름</th>
			<th>Mentee 주소</th>
			<th>Mentee 이메일</th>
			<th></th>
		</tr>
		
		<c:choose>
			<c:when test="${empty menteeList }">
				<tr>
					<th colspan="5">-----------멘토가 없습니다.---------------</th>
				</tr>
			</c:when>
			
			<c:otherwise>
				<c:forEach items="${menteeList }" var="dto" varStatus="status">
					<tr>
						<td><input type="text" name="menteeId" value="${dto.userid }" readonly /></td>
						<td>${dto.username }</td>
						<td>${dto.useraddr }</td>
						<td>${dto.useremail }</td>
						<td><input type="button" value="채팅" onclick="chatting(${status.index }, '${user.userid }')" /></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>

	<p>
		<a href="user.do?command=logout">로그아웃</a>
	</p>
</body>
</html>