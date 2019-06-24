package com.woniuxy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Room;
import com.woniuxy.service.RoomService;

@Controller
@RequestMapping("/room")
public class RoomController {
    @Resource
	private RoomService roomService;
    
    @ResponseBody
    @RequestMapping("/findByRoomID")
    public Room findByRoomID(Room room){
    	Room rooms = roomService.findOneByroom_id(room.getRoom_id());
    	return rooms;
    }
    
    @ResponseBody
    @RequestMapping("/findByState")
    public List<Room> findByState(Room room){
    	List<Room> list = roomService.findBystate(room.getState());
    	return list;
    }
    
    @ResponseBody
    @RequestMapping("/findByTypeId")
    public List<Room> findByTypeId(Room room){
    	List<Room> list = roomService.findByType_id(room.getType_id());
    	return list;
    }
    
    @ResponseBody
    @RequestMapping("/all")
    public List<Room> all(){
    	List<Room> all = roomService.findAll();
    	return all;
    }
    @ResponseBody
    @RequestMapping("/updateByRoomID")
    public String updateByRoomID(Room room){
    	roomService.updateState(room);
    	return "success";
    }
    
    @ResponseBody
    @RequestMapping("/insert")
    public String insertRoom(Room room){
    	roomService.insert(room);
    	return "success";
    } 
    
    @ResponseBody
    @RequestMapping("/delete")
    public String deleteByRoomId(Room room){
    	roomService.deleteByroom_id(room.getRoom_id());
    	return "success";
    } 
    
    /**
     * 修改房间状态通过房间id
     * @param room
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateStateByRoomId")
    public String updateStateByRoomId(Room room){
    	roomService.updateState(room);
    	return "success";
    } 
    
    
}
