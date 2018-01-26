package cn.shuangze.assetsms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.shuangze.assetsms.entity.AssetsType;

public class AssetTypeDaoMpl implements AssetsTypeDao {

	@Override
	public void save(Connection connection, AssetsType assetsType) throws SQLException {
		String sql = "INSERT INTO assetstype(b_type,s_type) VALUES(?,?)";
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setString(1, assetsType.getBigType());
		pStatement.setString(2, assetsType.getBigType());
		pStatement.execute();
		
	}

	@Override
	public void update(Connection connection, AssetsType assetsType) throws SQLException {
		String sql = "UPDATE assetstype SET b_type=?,"
				+ "s_type=?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, assetsType.getBigType());
		pStatement.setString(2, assetsType.getBigType());
		pStatement.executeUpdate();
		
	}

	@Override
	public void delete(Connection connection, AssetsType assetsType) throws SQLException {
		
		String sql = "DELETE FROM person WHERE b_type=?";
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setString(1, assetsType.getBigType());
		pStatement.execute();
	}

	@Override
	public ResultSet getInfo(Connection connection) throws SQLException {
		String sql = "SELECT *FROM assetstype";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		return pStatement.executeQuery();
	}

	public static void main(String[] args) {
		AssetsType assetsType=new AssetsType("123", "123");
		Connection connection=ConnectionFactory.getInstance().makeConnection();
		AssetTypeDaoMpl assetsTypeDaoMpl=new AssetTypeDaoMpl();
		try {
			assetsTypeDaoMpl.save(connection, assetsType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
