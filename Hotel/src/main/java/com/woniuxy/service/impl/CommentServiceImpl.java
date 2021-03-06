package com.woniuxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.CommentDAO;
import com.woniuxy.pojo.Comment;
import com.woniuxy.service.CommentService;
@Service("commentService")
public class CommentServiceImpl implements CommentService{
@Resource
private CommentDAO commentDAO;

	public CommentDAO getCommentDAO() {
	return commentDAO;
}

public void setCommentDAO(CommentDAO commentDAO) {
	this.commentDAO = commentDAO;
}

	@Override
	public List<Comment> allComments(int typeid) {
		
		return commentDAO.allComments(typeid);
	}

	@Override
	public void addComment(Comment comment) {
		
		commentDAO.addComment(comment);
	}

}
