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
						<td><a href="video.do?command=joinRoom&username=${user.userid }&roomId=${dto.roomid} ">${dto.roomid }</a></td>
						<td>${dto.regdate }</td>
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