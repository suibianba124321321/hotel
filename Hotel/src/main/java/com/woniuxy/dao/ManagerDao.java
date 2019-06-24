package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.pojo.Manager;
import com.woniuxy.pojo.Room;

public interface ManagerDao {
	/**
	 * 根据账号查询 操作员
	 * @param account
	 * @return
	 */
	@Select("select * from manager where account=#{account} and falg=0")
	Manager findOneByAccount(String account);
	
	/**
	 * 根据账号查询 操作员
	 * @param account
	 * @return
	 */
	@Select("select * from manager where manager_id=#{manager_id} and falg=0")
	Manager findOneByID(Manager manager);
	
	/**
	 * 查询所有的操作员
	 * @return
	 */
	@Select("select * from manager where falg=0")
	public List<Manager> findAll();
	
	/**
	 * 新增   默认falg=0
	 * @param manager
	 */
	@Insert("insert into manager(account,role_id,pwd,falg,idcard,tel) values(#{account},#{role_id},#{pwd},0,#{idcard},#{tel})")
	public void insert(Manager manager);
	
	/**
	 * 根据账号改密码
	 * @param room
	 */
	@Update("update manager set pwd=#{pwd} where account=#{account} and falg=0")
	public void updatePwd(Manager manager);
	
	/**
	 * 删除   软删除
	 * @param room
	 */
	@Update("update manager set falg=1 where account=#{account}")
	public void deleteByName(Manager manager);
	
	@Update("update manager set falg=1 where manager_id=#{manager_id}")
	public void deleteByID(Manager manager);
	
	
	@Update("update manager set pwd=#{pwd},idcard=#{idcard},role_id=#{role_id},tel=#{tel} where account=#{account} and falg=0")
	void updateAll(Manager manager);

}
