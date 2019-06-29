package com.woniuxy.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.woniuxy.pojo.Room;
import com.woniuxy.pojo.Type;
import com.woniuxy.service.RoomService;
import com.woniuxy.service.RoomTypeService;

@Controller
@RequestMapping("/type")
public class RoomTypeController {
	@Resource
	private RoomTypeService roomTypeService;
	@Resource
	private RoomService roomService;
	

	
	/**
	 * 新增房间类型
	 * @param type
	 */
	@ResponseBody
    @RequestMapping("/insert")
	public String insert(
			Integer type_id,String type,BigDecimal price,String description,
			BigDecimal deposit,Integer number,MultipartFile headImg,HttpServletRequest request)throws IllegalStateException, IOException{
		
		//获取文件名
				String fileName = headImg.getOriginalFilename();//文件名
				System.out.println(fileName);
				
				//获取当前项目的路径
				String path = request.getServletContext().getRealPath("");
				System.out.println(path);
				File currentPjo = new File(path);//当前项目的根路径
				//获取上一级目录
				File webapps = currentPjo.getParentFile();
				//获取保存文件的文件夹
				File upload = new File(webapps, "upload");
				//判断文件夹是否存在
				if (!upload.exists()) {
					upload.mkdirs();
				}
				//获取新文件名
				String newFileName = UUID.randomUUID().toString()+
						fileName.substring(fileName.lastIndexOf("."));
				//获取新文件的路径
				String newFilePath = upload.getAbsolutePath()+File.separator+newFileName;
				//得到新文件的file对象
				File newFile = new File(newFilePath);
				//保存到本地
				headImg.transferTo(newFile);
		    
				//newFilePath=newFilePath.replace("D:\\hotil\\hotel\\Hotel\\src\\main\\upload\\","../../upload/");
				
				
				
				Type newtype = new Type();
		
				newtype.setDeposit(deposit);
				newtype.setDescription(description);
				newtype.setNumber(number);
			
				newtype.setPrice(price);
				newtype.setType(type);
				newtype.setImg("file://"+newFilePath);
		

		roomTypeService.insert(newtype);
		
		System.out.println(newtype);
		//通过房间类型找到房间类型id
		Type type2 = roomTypeService.findTypeByType(newtype);
		System.out.println(type2);
	
		for(int i=0;i<number;i++){
			String id=type2.getType_id()+"00"+(i+1);
			Integer room_id=Integer.valueOf(id);
			roomService.insert(new Room().add(room_id, type2.getType_id(), 0));
		}
		return "添加成功";
	}
	
	/**
	 * 删除房间类型
	 * @param type
	 */
	@ResponseBody
    @RequestMapping("/deleteById")
	public String deleteBytype_id(Type type){
		roomTypeService.deleteBytype_id(type);
		return "删除成功";
	}
	
	/**
	 * 查询某一类型
	 * @param type
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/findById")
	public Type findOneBytype_id(Type type){
		Type type1 = roomTypeService.findOneBytype_id(type);
		return type1;
	}
	
	/**
	 * 查询所有类型
	 * @param type
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/all")
    public List<Type> findByRoomID(){
		List<Type> all = roomTypeService.findAll();
    	return all;
    }
	
	/**
	 * 修改房间价格通过房间类型ID
	 * @param type
	 */
	@ResponseBody
    @RequestMapping("/updatePrice")
	public void updatePriceByType_id(Type type){
		roomTypeService.updatePriceByType_id(type);
	}
	
	/**
	 * 修改房间数量
	 * @param type
	 */
	@ResponseBody
    @RequestMapping("/updateNumber")
	public void updateNumberByType_id(Type type){
		roomTypeService.updateNumberByType_id(type);
	}
	
	/**
	 * 修改房间类型描述
	 * @param type
	 */
	@ResponseBody
    @RequestMapping("/updateDescription")
	public void updateNumberBydescription(Type type){
		roomTypeService.updateDescriptionByType_id(type);
	}
	
	@ResponseBody
    @RequestMapping("/updateRoom")
	public void updateRoom(Type type){
		System.out.println(type);
		roomTypeService.updateDescriptionByType_id(type);
		roomTypeService.updateNumberByType_id(type);
		roomTypeService.updatePriceByType_id(type);
	}

}
