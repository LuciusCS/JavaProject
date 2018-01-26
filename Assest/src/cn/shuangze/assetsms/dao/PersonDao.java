package cn.shuangze.assetsms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.shuangze.assetsms.entity.Person;

public interface PersonDao {


	/**
	 * 与Person自身属性相关的编码
	 * @param connection
	 * @param user
	 * @throws SQLException
	 */
	public void save(Connection connection,Person person)throws SQLException;
	
	public void update(Connection connection,Person person)throws SQLException;
	
	public void delete(Connection connection,Person person)throws SQLException;
	
	//获取Person的所有信息
	public ResultSet getInfo(Connection connection)throws SQLException;
	
}
