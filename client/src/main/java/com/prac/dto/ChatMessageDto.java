package com.prac.dto;

import java.util.Date;

public class ChatMessageDto {
	private int seq;
	private String roomid;
	private String senderid;
	private String message;
	private String senddate;
	
	public ChatMessageDto() {
		
	}

	public ChatMessageDto(int seq, String roomid, String senderid, String message, String senddate) {
		this.seq = seq;
		this.roomid = roomid;
		this.senderid = senderid;
		this.message = message;
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

	public String getSenderid() {
		return senderid;
	}

	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSenddate() {
		return senddate;
	}

	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}
	
	
}
