package com.prac.dao;

import org.apache.ibatis.session.SqlSession;

import com.prac.dto.UserDto;

public class UserDao extends SqlMapConfig {
	private String namespace = "user.mapper.";
	
	public UserDto login(UserDto dto) {
		UserDto user = null;
		
		try (SqlSession session = getSqlSessionFactory().openSession(false)) {
			user = session.selectOne(namespace + "login", dto);
			
		}
		
		return user;
	}
}
