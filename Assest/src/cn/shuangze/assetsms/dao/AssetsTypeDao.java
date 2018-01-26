/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.shuangze.assetsms.dao;

import cn.shuangze.assetsms.entity.AssetsType;
import cn.shuangze.assetsms.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jinyu
 */
public interface AssetsTypeDao {
	public void save(Connection connection,AssetsType assetsType)throws SQLException;
	
	public void update(Connection connection,AssetsType assetsType)throws SQLException;
	
	public void delete(Connection connection,AssetsType assetsType)throws SQLException;
	
	//获取Person的所有信息
	public ResultSet getInfo(Connection connection)throws SQLException;
	
}
