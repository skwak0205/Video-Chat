package com.prac.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prac.biz.ChatBiz;
import com.prac.dto.ChatDto;
import com.prac.dto.ChatMessageDto;
import com.prac.dto.UserDto;


@WebServlet("/chat.do")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("("+command+")");
		
		ChatBiz biz = new ChatBiz();
		HttpSession session = request.getSession();
		
		if(command.equals("chatting")) {
			UserDto user = (UserDto) session.getAttribute("user");
			String creatorId = user.getUserid();
			
			String guestId = request.getParameter("guestId");
			String roomId = request.getParameter("roomId");
				
			ChatDto checkRoom = biz.checkChatRoom(roomId);
			
			if(checkRoom == null) {
				ChatDto dto = new ChatDto(0, roomId, creatorId, guestId, null);
				int res = biz.createChat(dto);
				
				if (res > 0) {
					request.setAttribute("guestId", guestId);
					dispatch(request, response, "chat.jsp");
				} else {
					jsResponse(response, "실패", "index.html");
				}
			} else {
				List<ChatMessageDto> list = biz.showMessage(checkRoom.getRoomid());
								
				request.setAttribute("list", list);
				request.setAttribute("guestId", guestId);
				dispatch(request, response, "chat.jsp");
			}
			
		} else if(command.equals("sendMsg")) {
			String roomId = request.getParameter("roomId");
			String senderId = request.getParameter("senderId");
			String text = request.getParameter("text");
			
			String time = request.getParameter("time");
			
			ChatMessageDto dto = new ChatMessageDto(0, roomId, senderId, text, time);
			int res = biz.saveMsg(dto);
			if (res <= 0) {
				System.out.println("실패");
			}
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

	private void jsResponse(HttpServletResponse response, String msg, String url) throws IOException {
		String responseText = "<script type='text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url
				+ "';" + "</script>";

		response.getWriter().print(responseText);
	}

}
