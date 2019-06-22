package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.Comment;

public interface CommentService {
	//通过房间类型查询所有的评价
	public List<Comment> allComments(int typeid);
}
