package cn.shuangze.assetsms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.shuangze.assetsms.entity.Assets;

public class AssetsDaoMpl implements AssestDao {

	@Override
	public void save(Connection connection, Assets assets) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO assets(name,typeid,model,price,buydate,status,other) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setString(1, assets.getName());
		pStatement.setInt(2, 1);
		pStatement.setString(3, assets.getModel());
		pStatement.setString(4, assets.getPrice());
		pStatement.setString(5, assets.getDate());

		pStatement.setInt(6, assets.getStatus());
		pStatement.setString(7, assets.getOther());
		pStatement.execute();
	}

	@Override
	public void update(Connection connection, Assets assets) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "update assets set name=?,typeid=?,model=?,price=?,buydate=?,status=?,other=? where name=?";
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setString(1, assets.getName());
		pStatement.setInt(2, 1);
		pStatement.setString(3, assets.getModel());
		pStatement.setString(4, assets.getPrice());
		pStatement.setString(5, assets.getDate());

		pStatement.setInt(6, assets.getStatus());
		pStatement.setString(7, assets.getOther());
		pStatement.setString(8, assets.getName());
		pStatement.execute();

	}

	@Override
	public void delete(Connection connection, Assets assets) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM assets WHERE name=?";
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setString(1, assets.getName());
		pStatement.execute();

	}

	@Override
	public void borrow(Connection connection, Assets assets) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update assets set status=? where name=?";
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setInt(1, 2);
		pStatement.setString(2, assets.getName());
		pStatement.executeUpdate();

	}

	@Override
	public void returnAssets(Connection connection, Assets assets) throws SQLException {
		String sql = "update assets set status=? where name=?";
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setInt(1, 3);
		pStatement.setString(2, assets.getName());
		pStatement.executeUpdate();

	}

	@Override
	public ResultSet getInfo(Connection connection) throws SQLException {
		String sql = "SELECT *FROM assets";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		return pStatement.executeQuery();
	}
	
	@Override
	public ResultSet getBorrowedInfo(Connection connection) throws SQLException {
		String sql = "SELECT *FROM assets where status = ?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setInt(1, 2);
		return pStatement.executeQuery();
	}

	@Override
	public ResultSet getReturenedInfo(Connection connection) throws SQLException {
		String sql = "SELECT *FROM assets where status = ?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setInt(1, 3);
		return pStatement.executeQuery();
	}

	@Override
	public ResultSet getAllowBorrowedInfo(Connection connection) throws SQLException {
		String sql = "SELECT *FROM assets where status = ?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setInt(1, 3);
		return pStatement.executeQuery();
	}
	


	@Override
	public ResultSet getRuinedInfo(Connection connection) throws SQLException {
		String sql = "SELECT *FROM assets where status = ?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setInt(1, 4);
		return pStatement.executeQuery();
	}

	
	

	public static void main(String[] args) {
//		Assets assets=new Assets("ASD", "ASDA", "SAD", 1, "ASD", "123", 1);
//		  Connection connection=ConnectionFactory.getInstance().makeConnection();
//		  AssetsDaoMpl assetDaoMpl=new AssetsDaoMpl();
//		  try {
//			assetDaoMpl.returnAssets(connection, assets);
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.print(e.getMessage());
//		}
		Connection connection = ConnectionFactory.getInstance().makeConnection();
		AssetsDaoMpl assetsDaoMpl=new AssetsDaoMpl();
		ResultSet rSet;
		
		ArrayList<Assets> assetss = new ArrayList();
		try {
			rSet = assetsDaoMpl.getReturenedInfo(connection);
		while (rSet.next()) {
		    Assets assets=new Assets(rSet.getString("name"), rSet.getString("model"), rSet.getString("price"), rSet.getInt("status"),rSet.getString("other"), rSet.getString("buydate"), rSet.getInt("typeid"));
		    assetss.add(assets);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(assetss.size());
	}
	
}
