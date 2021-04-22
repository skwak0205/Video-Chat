package com.prac.dto;

import java.util.Date;

public class ChatDto {
	private int chatno;
	private String roomid;
	private String creatorid;
	private String guestid;
	private Date regdate;
	
	public ChatDto() {
		
	}

	public ChatDto(int chatno, String roomid, String creatorid, String guestid, Date regdate) {
		this.chatno = chatno;
		this.roomid = roomid;
		this.creatorid = creatorid;
		this.guestid = guestid;
		this.regdate = regdate;
	}

	public int getChatno() {
		return chatno;
	}

	public void setChatno(int chatno) {
		this.chatno = chatno;
	}

	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public String getGuestid() {
		return guestid;
	}

	public void setGuestid(String guestid) {
		this.guestid = guestid;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}
