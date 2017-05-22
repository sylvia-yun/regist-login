package com.ming.Utils;

import java.util.HashMap;
import java.util.Map;

import com.ming.domain.User;

public class CheckUtils {
	
	public static Map<String,String> checkUser(User u){
		
		//初始化
		Map<String, String> map = new HashMap<String, String>();
		//验证
		if(u.getName()==null ||" ".equals(u.getName())){
			map.put("name", u.getName());
		}
		if(u.getPassword()==null ||" ".equals(u.getPassword())){
			map.put("password", u.getPassword());
		}
		return map;
	}

}
