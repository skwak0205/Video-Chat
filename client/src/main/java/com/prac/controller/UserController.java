package com.prac.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prac.biz.ChatBiz;
import com.prac.biz.UserBiz;
import com.prac.biz.VideoBiz;
import com.prac.dto.ChatDto;
import com.prac.dto.UserDto;
import com.prac.dto.VideoDto;


@WebServlet("/user.do")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		UserBiz biz = new UserBiz();
		VideoBiz videoBiz = new VideoBiz();
		ChatBiz chatBiz = new ChatBiz();
		HttpSession session = request.getSession();
		
		
		if (command.equals("login")) {
			String userid = request.getParameter("userid");
			String userpw = request.getParameter("userpw");
			
			UserDto dto = new UserDto();
			dto.setUserid(userid);
			dto.setUserpw(userpw);
			
			UserDto user = biz.login(dto);
			
			if (user != null) {
				session.setAttribute("user", user);
				session.setMaxInactiveInterval(10 * 60);
				
				if (user.getUserrole().equals("MENTEE")) {
					List<VideoDto> list = videoBiz.showVideoRoom(user.getUserid());
					
					List<UserDto> mentorList = biz.mentorList();
					
					request.setAttribute("list", list);
					request.setAttribute("mentorList", mentorList);
					
					dispatch(request, response, "mentee.jsp");
				
				} else if (user.getUserrole().equals("MENTOR")) {
					List<ChatDto> list = chatBiz.showChatRoom(user.getUserid());
					
					List<UserDto> menteeList = biz.menteeList();
					
					request.setAttribute("list", list);
					request.setAttribute("menteeList", menteeList);
										
					dispatch(request, response, "mentor.jsp");
				}
			} else {
				jsResponse(response, "로그인 실패", "index.html");
			}
		
		} else if (command.equals("logout")) {
			session.invalidate();
			response.sendRedirect("index.html");
		
		} 
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		// forward or include 때문에 사용
		// 요청을 받아 해당 요청을 전달해 주거나 추가하는 역할
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

	private void jsResponse(HttpServletResponse response, String msg, String url) throws IOException {
		String responseText = "<script type='text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url
				+ "';" + "</script>";

		response.getWriter().print(responseText);
	}

}
