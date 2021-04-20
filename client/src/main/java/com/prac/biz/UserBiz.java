package com.prac.biz;

import com.prac.dao.UserDao;
import com.prac.dto.UserDto;

public class UserBiz {
	private UserDao dao = new UserDao();
	
	public UserDto login(UserDto dto) {
		return dao.login(dto);
	}
}
