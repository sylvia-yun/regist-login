<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h2>用户列表</h2>
    <div>欢迎回来${sessionScope.user.name}!<a href="loginOutServlet">退出登录</a></div>
    <hr>
    <table>
      <tr>
           <th>id</th>
           <th>用户名</th>
           <th>邮箱</th>
      </tr>
      <c:forEach items="${requstScope.list }" var="user">
      
           <tr>
                <td>${pageScope.user.id}</td>
                <td>${pageScope.user.name}</td>
                <td>${pageScope.user.email}</td>
           </tr>
      </c:forEach>
      
    </table>
  </body>
</html>
