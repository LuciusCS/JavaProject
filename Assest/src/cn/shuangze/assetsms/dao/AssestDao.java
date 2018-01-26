package cn.shuangze.assetsms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.shuangze.assetsms.entity.Assets;
import cn.shuangze.assetsms.entity.Person;

public interface AssestDao {

	
	public void save(Connection connection,Assets assets)throws SQLException;
	
	public void update(Connection connection,Assets assets)throws SQLException;
	
	public void delete(Connection connection,Assets assets)throws SQLException;
	
	public void borrow(Connection connection,Assets assets)throws SQLException;
	
	public void returnAssets(Connection connection,Assets assets )throws SQLException;
	
	//获取资产的所有信息
	public ResultSet getInfo(Connection connection)throws SQLException;
	
	//获取已借取资产所有信息
	public ResultSet getBorrowedInfo(Connection connection)throws SQLException;
	
	//获取已归还资产所有信息
	public ResultSet getReturenedInfo(Connection connection)throws SQLException;
	
	//获取可借资产所有信息
	public ResultSet getAllowBorrowedInfo(Connection connection)throws SQLException;
	
	//获取已经报废的商品
	public ResultSet getRuinedInfo(Connection connection)throws SQLException;
	
}
