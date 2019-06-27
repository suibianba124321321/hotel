package com.woniuxy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Item;
import com.woniuxy.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Resource
	private ItemService itemService;
	
	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	@RequestMapping("/items")
	@ResponseBody
	public List<Item> items(Integer id){
		
		return itemService.findItemsByOrderid(id);
	}
	



	@RequestMapping("/inroom")
	@ResponseBody
	public String inRoom(Item item){
		String msg=itemService.inRoom(item);
		return msg;
	}
	
	@RequestMapping("/outroom")
	@ResponseBody
	public String outRoom(Item item){
		String msg=itemService.outRoom(item);
		return msg;
	}
	@RequestMapping("/itemszero")
	@ResponseBody
	public List<Item> itemsFlagEquelsZero(Integer id){
		
		return itemService.findItemsByOrdeIdAndflagEquelsZero(id);
	}
	
	@RequestMapping("/searchItems")
	@ResponseBody
	public List<Item> searchItem(String name,Integer state){
		List<Item> items=itemService.findItemsBySome(name,state);
		return items;
	}
	@RequestMapping("/cancel")
	@ResponseBody
	public String cancelSingleItem(Item item){
		String msg=itemService.cancelRoom(item);
		return msg;

	}
}
