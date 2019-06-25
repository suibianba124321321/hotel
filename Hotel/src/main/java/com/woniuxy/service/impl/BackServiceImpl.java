package com.woniuxy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.MemberDAO;
import com.woniuxy.dao.OrderDAO;
import com.woniuxy.dao.RoomDAO;
import com.woniuxy.dao.RoomDateDAO;
import com.woniuxy.pojo.Member;
import com.woniuxy.pojo.Order;
import com.woniuxy.pojo.Room;
import com.woniuxy.service.BackService;
@Service("/backService")
public class BackServiceImpl implements BackService {
	@Resource
	private RoomDAO roomDAO;
	@Resource
	private MemberDAO memberDAO;
	@Resource
	private RoomDateDAO roomDateDAO;
	@Resource
	private OrderDAO orderDAO;
	@Override
	public Map<String, Object> number() {
		//所有房间
		List<Room> rooms=roomDAO.allRoom();
		//已住房间数量
		int number =0;
		for(int i=0;i< rooms.size();i++){
			Room room=rooms.get(i);
			if(room.getState()== 1){
				number++;
			}
		}
				
		//所有会员
		List<Member> members=memberDAO.allMember();
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		List<Integer> roomIds= roomDateDAO.findNowRoom(today);
		//预订房间数量
		int prenumber = roomIds.size()-number;
		Map<String, Object> map=new HashMap<>();
		int roomnumber=0;
		if(rooms !=null){
			roomnumber=rooms.size();
		}
		int membernumber=0;
		if(members!=null){
			membernumber=members.size();
		}
		map.put("roomnumber",roomnumber);
		map.put("membernumber",membernumber);
		map.put("prenumber", prenumber);
		map.put("number", number);
		
		return map;
	}
	
	
}
