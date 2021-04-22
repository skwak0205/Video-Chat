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

	<script type="text/javascript">
		function chatting(idx, username){	
			var mentorId = document.getElementsByName("mentorId")[idx];
			
			
			if(mentorId.value !=null) {
				var roomId = mentorId.value+"_"+username;
				
				open("chat.do?command=chatting&guestId="+mentorId.value+"&roomId="+roomId, "", "width=500, height=500");
			}
	
		}
	
	</script>

</head>

<body>
	<h1>Welcome Mentee ${user.userid}</h1>
	
	<h3>화상 강의 방</h3>
	<table border="1">
		<col width="150" />
		<col width="500" />
		<col width="250" />
		<col width="100" />
		
		<tr>
			<th>Mentor ID</th>
			<th>Room ID</th>
			<th>생성된 날짜</th>
		</tr>
		
		<c:choose>
			<c:when test="${empty list }">
				<tr>
					<th colspan="4">-----------생성된 방이 없습니다.---------------</th>
				</tr>
			</c:when>
			
			<c:otherwise>
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.mentorid }</td>
						<td><a href="video.do?command=joinRoom&userId=${user.userid }&roomId=${dto.roomid} ">${dto.roomid }</a></td>
						<td>${dto.regdate }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	
	<h3>멘토 목록</h3>
	<table border="1">
		<col width="100" />
		<col width="100" />
		<col width="150" />
		<col width="150" />
		<col width="50"  />
		
		<tr>
			<th>Mentor ID</th>
			<th>Mentor 이름</th>
			<th>Mentor 주소</th>
			<th>Mentor 이메일</th>
			<th></th>
		</tr>
		
		<c:choose>
			<c:when test="${empty mentorList }">
				<tr>
					<th colspan="5">-----------멘토가 없습니다.---------------</th>
				</tr>
			</c:when>
			
			<c:otherwise>
				<c:forEach items="${mentorList }" var="dto" varStatus="status">
					<tr>
						<td><input type="text" name="mentorId" value="${dto.userid }" readonly /></td>
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