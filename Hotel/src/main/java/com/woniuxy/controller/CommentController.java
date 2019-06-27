package com.woniuxy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Comment;
import com.woniuxy.pojo.Login;
import com.woniuxy.service.CommentService;
import com.woniuxy.service.LoginService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
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
}
