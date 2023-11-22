package com.kdt.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.MessageDAO;
import com.kdt.dto.ChattingRoomDTO;
import com.kdt.dto.MemberDTO;
import com.kdt.dto.MessageDTO;
import com.kdt.dto.ParticipantDTO;
import com.kdt.dto.RoomInfoDTO;
import com.kdt.dto.RoomProfileDTO;

@Service
public class MessageService {

	@Autowired
	private MessageDAO dao;
	
	public void createRoomAndAddParticipants(String room_name, List<String> list) {

		ChattingRoomDTO dto = new ChattingRoomDTO();
		ParticipantDTO pdto = new ParticipantDTO();
		dto.setRoom_name(room_name);
		dto = dao.createRoom(dto);
		pdto.setRoom_seq(dto.getSeq());
		for (String user_id : list) {
			pdto.setUser_id(user_id);
			dao.addParticipants(pdto);
		}
		dao.initRoom(new MessageDTO(0,"System",dto.getSeq(), dao.getName(list.get(list.size()-1)) +  "님이 채팅방을 생성했습니다.",null,false));
	}
	
	public List<RoomInfoDTO> getRoomInfo(String id) {
		return dao.getRoomInfo(id);
	}
	
	
	public List<RoomProfileDTO> getProfiles(String id) {
		return dao.getProfiles(id);
	}
	
	public List<MessageDTO> getRecentMessage(String id) {
		return dao.getRecentMessage(id);
	}
	
	public List<MessageDTO> getMessagesByRoomSeq(String room_seq) {
		return dao.getMessagesByRoomSeq(room_seq);
	}
	
	public int readMessage(ParticipantDTO dto) {
		return dao.readMessage(dto);
	}
	
	public MessageDTO WSInsert(int room_seq, int id, String contents) {
		String strID = String.valueOf(id);
		return dao.WSInsert(new MessageDTO(0, strID, room_seq, contents, null , false));
	}
	
	public List<String> WSgetParticipants(int room_seq) {
		return dao.selectParticipants(room_seq);
	}
	
	public int setUnreadedState(int room_seq) {
		return dao.setUnreadedState(room_seq);
	}
	
	public MessageDTO leaveRoom(String id, String room_seq) {
		Timestamp date=new Timestamp(System.currentTimeMillis());
		
		String name = dao.getName(id);
		MessageDTO dto = new MessageDTO(0,"System",Integer.parseInt(room_seq), name + "님이 채팅방을 나갔습니다.",date,false);
		dao.initRoom(dto);
		dao.leaveRoom(new ParticipantDTO(Integer.parseInt(room_seq), id, false));
		return dto;
	}
	
	public List<MemberDTO> getMemberInfo() {
		return dao.getMemberInfo();
	}
}
