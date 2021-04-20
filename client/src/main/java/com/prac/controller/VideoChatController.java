package com.prac.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prac.biz.VideoBiz;
import com.prac.dto.UserDto;
import com.prac.dto.VideoDto;


@WebServlet("/video.do")
public class VideoChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("{" + command + "}");
		
		VideoBiz biz = new VideoBiz();
		
		HttpSession session = request.getSession();
		
		if (command.equals("videoCreate")) {			
			response.sendRedirect("videoCreate.jsp");
			
		} else if (command.equals("createRoom")) {
			UserDto user = (UserDto) session.getAttribute("user");
			
			String mentorid = user.getUserid();
			String menteeid = request.getParameter("menteeId");
			String roomid = request.getParameter("roomId");

			VideoDto dto = new VideoDto(0, roomid, mentorid, menteeid, null);
			
			int res = biz.createRoom(dto);
			
			if (res > 0) {

				jsResponse(response, "방 생성 성공", "video.do?command=joinRoom&username="+mentorid+"&roomId="+roomid);
				
			} else {
				jsResponse(response, "방 생성 실패", "video.do?command=videoCreate");
			}
			
		} else if (command.equals("joinRoom")) {
			
			dispatch(request, response, "videoChat.jsp");
			
		}
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
