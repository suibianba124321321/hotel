package com.woniuxy.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Comment;

@Controller
@RequestMapping("/comment")
public class CommentController {
@RequestMapping("/all")	
@ResponseBody
 public List<Comment> all(){
	
	
	
	return null;
	
	
	
}
}
