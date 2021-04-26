package com.prac.dto;

import java.util.Date;

public class ChatMessageDto {
	private int seq;
	private String roomid;
	private String fromid;
	private String toid;
	private String message;
	private String chatread;
	private String senddate;
	
	public ChatMessageDto() {
		
	}

	public ChatMessageDto(int seq, String roomid, String fromid, String toid, String message, String chatread,
			String senddate) {
		this.seq = seq;
		this.roomid = roomid;
		this.fromid = fromid;
		this.toid = toid;
		this.message = message;
		this.chatread = chatread;
		this.senddate = senddate;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public String getFromid() {
		return fromid;
	}

	public void setFromid(String fromid) {
		this.fromid = fromid;
	}

	public String getToid() {
		return toid;
	}

	public void setToid(String toid) {
		this.toid = toid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getChatread() {
		return chatread;
	}

	public void setChatread(String chatread) {
		this.chatread = chatread;
	}

	public String getSenddate() {
		return senddate;
	}

	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}

	
	
	
}
