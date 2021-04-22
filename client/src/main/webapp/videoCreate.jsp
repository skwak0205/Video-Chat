<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
	
	
	<link rel="stylesheet" href="resources/css/main.css">
	
	<script type="text/javascript" src="resources/js/createVideo.js"></script>

</head>

<body>
	<h1>화상 강의 방 만들기</h1>

	<form action="video.do" method="post">
		<input type="hidden" name="command" value="createRoom" />

		<div class="room">
			<input type='text' name='mentorId' value="${user.userid }" readonly /> 
			
			<input type='text' name='menteeId' placeholder='참여할 멘티 id를 쓰세요.' /> 
			
			<input type='text' size='40' name='roomId' id='uuid' />
			
			<input type="button" value="방 만들기" onclick="show_uuid()" />
		</div>

		<input type="submit" value="시작하기" />
	

	</form>


</body>
</html>