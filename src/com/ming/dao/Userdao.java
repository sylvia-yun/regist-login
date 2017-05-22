package com.ming.dao;


import java.util.List;

import com.ming.domain.User;

/*
 * 自定义接口，实现方法调用
 */
public interface Userdao {
	
	//保存用户信息
	void save(User u);
	//根據用戶信息c查询数据库
	User getUserByname(String name);
	//得到用户所有的信息
	List<User> getALLUser();

}
