package com.ming.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.ming.Utils.CheckUtils;
import com.ming.domain.User;
import com.ming.service.UserService;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		//1、封装数据
		User u = new User();
		try {
			BeanUtils.populate(u, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、进行非空校验，用户名与密码的校验
		Map<String, String> errors = CheckUtils.checkUser(u);
		if(errors.size()>0){
			//表明发生了错误
			request.setAttribute("errors", errors);
			//将错误信息显示到JSP中
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			//表示不再执行下面的步骤
			return;
			
		}
		//3、调用service
		User exitU=null;
		 try {
			 exitU = us.login(u);
			 
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		 
		//4、向session中加入登录标识
		 request.getSession().setAttribute("user", exitU);
		//5、重定向到登录列表显示界面
		 response.sendRedirect(request.getContextPath()+"listServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
