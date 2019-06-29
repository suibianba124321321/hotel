package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.woniuxy.pojo.Comment;

public interface CommentDAO {
@Select("select * from comment where typeid=#{typeid} and flag=0")
public List<Comment> allComments(int typeid);
@Insert("insert into comment (typeid,loginid,description) values(#{typeid},#{loginid},#{description})")
public void addComment(Comment comment);
	
}
