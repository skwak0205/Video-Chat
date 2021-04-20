package com.prac.dto;

import java.util.Date;

public class VideoDto {
	private int roomno;
	private String roomid;
	private String mentorid;
	private String menteeid;
	private Date regdate;

	public VideoDto() {

	}

	public VideoDto(int roomno, String roomid, String mentorid, String menteeid, Date regdate) {
		this.roomno = roomno;
		this.roomid = roomid;
		this.mentorid = mentorid;
		this.menteeid = menteeid;
		this.regdate = regdate;
	}

	public int getRoomno() {
		return roomno;
	}

	public void setRoomno(int roomno) {
		this.roomno = roomno;
	}

	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public String getMentorid() {
		return mentorid;
	}

	public void setMentorid(String mentorid) {
		this.mentorid = mentorid;
	}

	public String getMenteeid() {
		return menteeid;
	}

	public void setMenteeid(String menteeid) {
		this.menteeid = menteeid;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}
