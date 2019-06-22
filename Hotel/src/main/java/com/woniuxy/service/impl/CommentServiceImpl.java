package com.woniuxy.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.CommentDAO;
import com.woniuxy.pojo.Comment;
import com.woniuxy.service.CommentService;
@Service("commentService")
public class CommentServiceImpl implements CommentService{
private CommentDAO commentDAO;

	public CommentDAO getCommentDAO() {
	return commentDAO;
}

public void setCommentDAO(CommentDAO commentDAO) {
	this.commentDAO = commentDAO;
}

	@Override
	public List<Comment> allComments() {
		// TODO Auto-generated method stub
		return null;
	}

}
