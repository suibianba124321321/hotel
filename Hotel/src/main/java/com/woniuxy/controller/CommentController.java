package com.woniuxy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Comment;
import com.woniuxy.service.CommentService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
@Resource
private CommentService commentService;
	
public CommentService getCommentService() {
	return commentService;
}

public void setCommentService(CommentService commentService) {
	this.commentService = commentService;
}

@RequestMapping("/all")	
@ResponseBody
 public List<Comment> all(int typeid){
	List<Comment> allComments = commentService.allComments(typeid);
	return allComments;
	
	
}
}
