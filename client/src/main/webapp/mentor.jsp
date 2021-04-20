<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome Mentor ${user.userid}</h1>
	
	<button onclick="location.href='video.do?command=videoCreate'">화상 강의 방 만들기</button>
	
	
	<p>
		<a href="user.do?command=logout">로그아웃</a>
	</p>
</body>
</html>