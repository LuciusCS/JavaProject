/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.shuangze.assetsms.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Administrator
 */
public class ConnectionFactory {
    
    	private static final ConnectionFactory factory=new ConnectionFactory();
	public 	Connection connection;
        
        private ConnectionFactory(){
		
	}
	public static ConnectionFactory getInstance(){
		return factory;
	}
	public Connection makeConnection(){
		try {
			  connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/database","root","123456l");   
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return connection;
	}
      public static void main(String[] args) {
	Connection connection=ConnectionFactory.getInstance().makeConnection();
	System.out.println(connection);
       }
}
