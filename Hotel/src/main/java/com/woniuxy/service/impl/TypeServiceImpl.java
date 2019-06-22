package com.woniuxy.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.RoomDAO;
import com.woniuxy.dao.RoomDateDAO;
import com.woniuxy.dao.TypeDAO;
import com.woniuxy.pojo.Room;
import com.woniuxy.pojo.Type;
import com.woniuxy.service.TypeService;
@Service("typeService")
public class TypeServiceImpl implements TypeService{

	@Resource
	private TypeDAO typeDAO;
	@Resource
	private RoomDAO roomDAO;
	@Resource
	private RoomDateDAO roomDateDAO;
	
	public TypeDAO getTypeDAO() {
		return typeDAO;
	}

	public void setTypeDAO(TypeDAO typeDAO) {
		this.typeDAO = typeDAO;
	}

	public RoomDAO getRoomDAO() {
		return roomDAO;
	}

	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}

	public RoomDateDAO getRoomDateDAO() {
		return roomDateDAO;
	}

	public void setRoomDateDAO(RoomDateDAO roomDateDAO) {
		this.roomDateDAO = roomDateDAO;
	}

	@Override
	public List<Type> show(String inTime ,String outTime) {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		//获取预订时间段
		List<String> inDates=new ArrayList<>();
		
		Date start=null;
		Date end=null;
		try {
			start=sd.parse(inTime);
			end=sd.parse(outTime);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		while(start.getTime()!=end.getTime()){
			
			inDates.add(sd.format(start));
			start=new Date(start.getTime()+24*60*60*1000);
		}
		List<Type> types=typeDAO.findAllType();
		
		for(int k=0;k<types.size();k++){
			Type type=types.get(k);
			//可入住的房间数量
			int number=0;
			//获取该类型所有房间
			List<Room> rooms=roomDAO.findRoomByType(type.getType_id());
			for(int i=0;i<rooms.size();i++){
				Room room=rooms.get(i);
				//获取该房间的时间信息
				List<String> dates=roomDateDAO.findDateByRoomid(room);
				
				//房间可入住，数量加1
				if(dates==null){
					number++;
					continue;
				}
				//房间是否可住标志
				boolean flag=true;
				//有时间信息，就与订单时间比较，筛选符合的房间，完全不包含订单时间
				for(int j=0;j<inDates.size();j++){
					
					//获取比较的时间
					String date=dates.get(i);
					//判断当前房间的时间信息中是否包含目标时间
					if(dates.contains(date)){
						
						//房间不可住
						flag=false;
					}
				}
				//房间可入住，数量加1
				if(flag==true){
					
					number++;
				}
			}
			//设置可入住房间数
			types.get(k).setNumber(number);
		}
		
		return types;
	}

	
	//房间类型id查找到房间
	@Override
	public Type findTypeByid(int id) {
		
		return typeDAO.findTypeByid(id);
	}

}
