package com.woniuxy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Comment;
import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Login;
import com.woniuxy.pojo.Order;
import com.woniuxy.pojo.Type;
import com.woniuxy.service.CommentService;
import com.woniuxy.service.ItemService;
import com.woniuxy.service.LoginService;
import com.woniuxy.service.OrderService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
@Resource	
private OrderService orderService;

	public OrderService getOrderService() {
	return orderService;
}

public void setOrderService(OrderService orderService) {
	this.orderService = orderService;
}
	@Resource
	private ItemService itemService;
	
public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
@Resource
private CommentService commentService;

@Resource
private LoginService LoginService;

public LoginService getLoginService() {
	return LoginService;
}

public void setLoginService(LoginService loginService) {
	LoginService = loginService;
}

public CommentService getCommentService() {
	return commentService;
}

public void setCommentService(CommentService commentService) {
	this.commentService = commentService;
}

@RequestMapping("/all")	
@ResponseBody
 public List<Comment> all(int typeid){
	//通过typeid找到所有评论 再通过所有评论找到loginid 然后再通过lgoinid 将所有的login封装进集合
	List<Comment> allComments = commentService.allComments(typeid);
	for (Comment comment : allComments) {
		Login login = LoginService.findLoginByid(comment.getLoginid());
		comment.setLogin(login);
	}
	
	return allComments;	
}
@RequestMapping("/getType")	
@ResponseBody
public Map oneType(Integer orderId){
	//通过订单id找到对应的房间类型id
	List<Item> allItem = itemService.allItem();
	Item item = allItem.get(0);
	Type type = item.getType();
	Order order = orderService.findOrderById(orderId);
	Map<String, Object> map=new HashMap<String, Object>();
	map.put("order", order);
	map.put("type", type);
	return map;
}
@RequestMapping("/submit")	
@ResponseBody
public String commentSubmit(Comment comment,HttpSession session){
 String result="请输入内容好吗";
 System.out.println(comment);
 String description = comment.getDescription();
 if(description==null||description.equals("")){
	 return result;
 }
 Object object = session.getAttribute("login");
 Login login=(Login) object;
 comment.setLoginid(login.getLogin_id());
 commentService.addComment(comment);

	return "评论成功";
}


}
