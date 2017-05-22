package com.ming.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ming.domain.User;
import com.ming.service.UserService;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService us = new UserService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1、校验用户是否已经登录
		User u = (User) request.getSession().getAttribute("user");
		     //如果没有，转发到登录页面
		if(u==null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
		//2、调用service方法查询用户列表
		List<User> list = us.getALLUser();
		//3、将用户列表放入到request域中，将列表呈现在list.JSP中
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/page/list.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
