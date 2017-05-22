package com.ming.service;


import java.util.List;

import com.ming.dao.Userdao;
import com.ming.dao.impl.UserDaoImpl;
import com.ming.domain.User;

public class UserService{

	
	private Userdao ud= new UserDaoImpl();
	
	
	//检验用户名是否重复
	public void regist(User u){
	
		User exitU= ud.getUserByname(u.getName());
		if(exitU!=null){
			//说明用户名重复
			throw new RuntimeException("用户名重复！");
		}
		//倘若没有重复，保存用户名
		ud.save(u);
	}
	
	
	
	//登录方法，检查密码是否正确
	public User login(User u){
		//1、找到用户名
		User exitU = ud.getUserByname(u.getName());
		//2、验证是否为空
		if(exitU==null){
			throw new RuntimeException("用户名不存在！");
		}
		//3、验证是否正确
		if(!u.getName().equals(exitU.getPassword())){
			throw new RuntimeException("用户密码错误！");
		}
		//4、登录成功，返回user
		return exitU;
	}
	
	//将列表信息返回
	public List<User> getALLUser(){
		return ud.getALLUser();
	}
	
	
	
	
	
	
	
}
