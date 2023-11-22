package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.CalendarDTO;

@Repository
public class CalendarDAO {
	@Autowired
	private SqlSession ss;
	
	public int post(CalendarDTO dto) {
		return ss.insert("Calendar.insert", dto);
	}
	public List<CalendarDTO> selectAll(String id) {
		return ss.selectList("Calendar.selectAll", id);
	}
	public int delete(int seq) {
		return ss.delete("Calendar.delete", seq);
	}
	
	public List<CalendarDTO> selectOne(String id){
		return ss.selectList("Calendar.selectOne",id);
	}
	
	public int update(CalendarDTO dto) {
		return ss.update("Calendar.update",dto);
	}
}
