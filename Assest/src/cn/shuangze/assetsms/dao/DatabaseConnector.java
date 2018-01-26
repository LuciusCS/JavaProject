/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.shuangze.assetsms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Administrator
 */
public class DatabaseConnector {
    
    
     public static void main(String args[]) {  
    try {  
      Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动类，引号中是驱动类的路径   
     System.out.println("Success loading Mysql Driver!");  
     }  
    catch (Exception e) {  
      System.out.print("Error loading Mysql Driver!");  
      e.printStackTrace();  
    }  
    try {  
      Connection connect = DriverManager.getConnection(                 
          "jdbc:mysql://localhost:3306/database","root","123456l");  
           //连接URL为   jdbc:mysql//服务器地址/端口号/数据库名 ，后面的2个参数分别是登陆用户名和密码  

          //获取数据库连接getConnection，  
  
  
      System.out.println("Success connect Mysql server!");  
             System.out.println(connect);  
      Statement stmt = connect.createStatement();  
      ResultSet rs = stmt.executeQuery("select * from assetsstatus");  
        
             System.out.println(rs.next());  
                                                              //testtable 为你表的名称  
     while (rs.next()) {  
        System.out.println(rs.getInt("StatusID"));  
      }
      }  
     catch (Exception e) {  
      System.out.print("get data error!");  
      e.printStackTrace();  
    }  
  }  
}
