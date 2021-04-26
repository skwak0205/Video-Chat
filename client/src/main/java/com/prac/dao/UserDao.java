package com.prac.dao;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<UserDto> mentorList() {
		List<UserDto> list = new ArrayList<UserDto>();
		
		try (SqlSession session = getSqlSessionFactory().openSession(false)) {
			list = session.selectList(namespace+"mentorList");
		}
		
		return list;
	}
	
	public List<UserDto> menteeList() {
		List<UserDto> list = new ArrayList<UserDto>();
		
		try (SqlSession session = getSqlSessionFactory().openSession(false)) {
			list = session.selectList(namespace+"menteeList");
		}
		
		return list;
	}
}
