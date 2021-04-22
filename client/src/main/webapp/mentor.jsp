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
	
	function chatting( roomId, guestId){	
		open("chat.do?command=chatting&guestId="+guestId+"&roomId="+roomId, "", "width=500, height=500");
	}
</script>


</head>
<body>
	<h1>Welcome Mentor ${user.userid}</h1>
	
	<button onclick="location.href='video.do?command=videoCreate'">화상 강의 방 만들기</button>
	
	
	<h3>채팅 방</h3>
	<table border="1">
		<col width="150" />
		<col width="100" />
		<col width="250" />
		<col width="50"  />
		
		<tr>
			<th>채팅 방 ID</th>
			<th>보낸 사람</th>
			<th>방 생성된 시간</th>
			<th></th>
		</tr>
		
		<c:choose>
			<c:when test="${empty list }">
				<tr>
					<th colspan="3">-----------채팅이 없습니다.---------------</th>
				</tr>
			</c:when>
			
			<c:otherwise>
				<c:forEach items="${list }" var="dto" varStatus="status">
					<tr>
						<td>${dto.roomid }</td>
						<td>${dto.creatorid }</td>
						<td>${dto.regdate }</td>
						<td><input type="button" value="채팅" onclick="chatting('${dto.roomid }', '${dto.creatorid }')" /></td>
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