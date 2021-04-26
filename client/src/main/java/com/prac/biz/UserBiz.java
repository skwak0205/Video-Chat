package com.prac.biz;

import java.util.List;

import com.prac.dao.UserDao;
import com.prac.dto.UserDto;

public class UserBiz {
	private UserDao dao = new UserDao();
	
	public UserDto login(UserDto dto) {
		return dao.login(dto);
	}
	
	public List<UserDto> mentorList() {
		return dao.mentorList();
	}
	
	public List<UserDto> menteeList() {
		return dao.menteeList();
	}
}
