package com.ming.dao.impl;


import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PseudoColumnUsage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.catalina.valves.JDBCAccessLogValve;

import com.ming.dao.Userdao;
import com.ming.domain.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;


/*
 * 实现逻辑功能的类
 */
public class UserDaoImpl implements Userdao{

	
	
	//将用户注册时的信息保存到数据库中
	@Override
	public void save(User u) {

		//1、获得连接
		try {
			//注册事件
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//建立连接
			Connection conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Day11","root","1234");
			//2、准备sql语句                                     
			String sql="INSERT INTO 't_user' VALUES(?,?,?)";
			//3、获得preparestament对象s                         
			PreparedStatement ps =  (PreparedStatement) conn.prepareStatement(sql); 
			//4、设置属性
			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			//5、执行sql语句
			int row= ps.executeUpdate(sql);
			if(row!=1){
				throw new RuntimeException("保存失败");
			}
			//6、关闭资源
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("保存失败");
		}
		
		
	}

	
	//通过用户名来查询到用户在数据库保存的信息
	@Override
	public User getUserByname(String name) {

		
		User u=null;
		PreparedStatement ps=null;
		Connection conn = null;
		ResultSet rs = null ;
		//1、获得 连接
		try {
		    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		    conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/day11","root","1234");
			//2、准备SQL语句
			String sql = "SELECT * frome t_user where name=?";
			//3、获得preparestatemen对象
			 ps= (PreparedStatement) conn.prepareStatement(sql);
			//4、设置属性
			ps.setString(1, name);
			//5、执行sql语句
			 rs= (ResultSet) ps.executeQuery();
			//6、遍历信息，将结果集中的数据封装到user中
			if(rs.next()){
				u = new User();
				//将数据封装进去
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				
			}
			rs.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询失败！");
			
		}
		return u;
	   
	}


	//该方法用来将数据显示，保存到list中
	@Override
	public List<User> getALLUser() {
	
		List<User> list = new ArrayList<User>();
		
		User u=null;
		PreparedStatement ps=null;
		Connection conn = null;
		ResultSet rs = null ;
		//1、获得 连接
		try {
		    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		    conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/day11","root","1234");
			//2、准备SQL语句
			String sql = "SELECT * frome t_user where name=?";
			//3、获得preparestatemen对象
			 ps= (PreparedStatement) conn.prepareStatement(sql);
			//5、执行sql语句
			 rs= (ResultSet) ps.executeQuery();
			//6、遍历信息，将结果集中的数据封装到user中
			if(rs.next()){
				u = new User();
				//将数据封装进去
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				list.add(u);
			}
			rs.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询用户列表失败！");
			
		}
		return list;
	}

}
