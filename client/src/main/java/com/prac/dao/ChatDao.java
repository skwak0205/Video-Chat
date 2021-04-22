package com.prac.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.prac.dto.ChatDto;
import com.prac.dto.ChatMessageDto;

public class ChatDao extends SqlMapConfig {
	private String namespace = "chat.mapper.";
	
	public int createChat(ChatDto dto) {
		int res = 0;
		
		try(SqlSession session = getSqlSessionFactory().openSession(true) ) {
			res = session.insert(namespace+"createChat", dto);
		}
		
		return res;
	}
	
	public ChatDto checkChatRoom(String roomId) {
		ChatDto chkRoom = null;
		
		try(SqlSession session = getSqlSessionFactory().openSession(false)) {
			chkRoom = session.selectOne(namespace+"checkChatRoom", roomId);
		}
		
		return chkRoom;
	}
	
	public List<ChatMessageDto> showMessage(String roomId) {
		List<ChatMessageDto> list = new ArrayList<ChatMessageDto>();
		
		try(SqlSession session = getSqlSessionFactory().openSession(false)) {
			list = session.selectList(namespace + "showMessage", roomId);
		}
		
		return list;
	}
	
	public List<ChatDto> showChatRoom(String guestId) {
		List<ChatDto> list = new ArrayList<ChatDto>();
		
		try(SqlSession session = getSqlSessionFactory().openSession(false)) {
			list = session.selectList(namespace+"showChatRoom", guestId);
		}
		
		return list;
	}
	
	public int saveMsg(ChatMessageDto dto) {
		int res = 0;
		
		try(SqlSession session = getSqlSessionFactory().openSession(true)) {
			res = session.insert(namespace+"saveMsg", dto);
		}
		
		return res;
	}
}
