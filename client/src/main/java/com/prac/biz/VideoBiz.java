package com.prac.biz;

import java.util.List;

import com.prac.dao.VideoDao;
import com.prac.dto.VideoDto;

public class VideoBiz {
	private VideoDao dao = new VideoDao();
	
	public int createRoom(VideoDto dto) {
		return dao.createRoom(dto);
	}
	
	public List<VideoDto> showVideoRoom(String menteeid) {
		return dao.showVideoRoom(menteeid);
	}
}
