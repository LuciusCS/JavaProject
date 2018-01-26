package cn.shuangze.assetsms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.shuangze.assetsms.entity.Person;

public class PersonDaoMpl implements PersonDao {

	@Override
	public void save(Connection connection, Person person) throws SQLException {
		
		String sql = "INSERT INTO person(name,sex,dept,job,other) VALUES(?,?,?,?,?)";
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setString(1, person.getName());
		pStatement.setString(2, person.getSex());
		pStatement.setString(3, person.getDept());
		pStatement.setString(4, person.getJob());
		pStatement.setString(5, person.getOther());
		pStatement.execute();
		
	}

	@Override
	public void update(Connection connection, Person person) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE person SET name=?,"
				+ "sex=?,dept=?,job=?,"
				+ "other=? WHERE name=?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, person.getName());
		pStatement.setString(2, person.getSex());
		pStatement.setString(3, person.getDept());
		pStatement.setString(4, person.getJob());
		pStatement.setString(5, person.getOther());
		pStatement.setString(6, person.getName());
		pStatement.executeUpdate();
	}

	@Override
	public void delete(Connection connection, Person person) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "DELETE FROM person WHERE name=?";
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setString(1, person.getName());
		pStatement.execute();
		
	}

	@Override
	public ResultSet getInfo(Connection connection) throws SQLException {
		String sql = "SELECT *FROM person";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		return pStatement.executeQuery();
	}

    public static void main(String[] args) {
	  Person person=new Person("sfsd","sdf","ssf","sdf","sdfs");
	  Connection connection=ConnectionFactory.getInstance().makeConnection();
	  PersonDaoMpl personDaoMpl=new PersonDaoMpl();
	  try {
		  ResultSet rs= personDaoMpl.getInfo(connection);
		  
		  while (rs.next()) {  
		        System.out.println(rs.getString("name"));  
		      }
		  
	} catch (Exception e) {
		// TODO: handle exception
		System.out.print(e.getMessage());
	}

    }

}
