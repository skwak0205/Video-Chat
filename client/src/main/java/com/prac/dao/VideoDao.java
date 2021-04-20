package com.prac.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.prac.dto.VideoDto;

public class VideoDao extends SqlMapConfig {

	private String namespace = "video.mapper.";
	
	public int createRoom(VideoDto dto) {
		int res = 0;
		
		try(SqlSession session = getSqlSessionFactory().openSession(true)) {
			res = session.insert(namespace + "createRoom", dto);
		}
		
		return res;
	}
	
	public List<VideoDto> showVideoRoom(String menteeid) {
		List<VideoDto> list = new ArrayList<VideoDto>();
		
		try(SqlSession session = getSqlSessionFactory().openSession(false)) {
			list = session.selectList(namespace+"showVideoRoom", menteeid);
		}
		
		return list;
		
	}
	
	
}
