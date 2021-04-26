package com.prac.biz;

import java.util.List;

import com.prac.dao.ChatDao;
import com.prac.dto.ChatDto;
import com.prac.dto.ChatMessageDto;

public class ChatBiz {
	private ChatDao dao = new ChatDao();
	
	public int createChat(ChatDto dto) {
		return dao.createChat(dto);
	}
	
	public ChatDto checkChatRoom(String roomId) {
		return dao.checkChatRoom(roomId);
	}
	
	public List<ChatMessageDto> showMessage(String roomId){
		return dao.showMessage(roomId);
	}
	
	public List<ChatDto> showChatRoom(String guestId) {
		return dao.showChatRoom(guestId);
	}
	
	public int saveMsg(ChatMessageDto dto) {
		return dao.saveMsg(dto);
	}
	
	public int readMsg(ChatMessageDto dto) {
		return dao.readMsg(dto);
	}
}
