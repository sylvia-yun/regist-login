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
import org.apache.commons.beanutils.BeanUtilsBean;

import com.ming.Utils.CheckUtils;
import com.ming.domain.User;
import com.ming.service.UserService;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//编码一致
		request.setCharacterEncoding("UTF-8");
		//1、获取到信息
		User u = new User();
		try {
			//将数据封装到bean对象中
			BeanUtils.populate(u, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、校验数据，将错误信息放置在输入信息后面
		Map<String,String> errors = CheckUtils.checkUser(u);
		if(errors.size()>0){
			//说明 有错误信息,保存到request对象中，转发到登录页面
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		//3、调用service保存
		try {
			us.regist(u);
			
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		
		//4、根据结果跳转到相应界面

		response.sendRedirect(request.getContextPath()+"/login.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
