<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'regist.jsp' starting page</title>
    
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
      <form action="/Day11/registServlet" method="post" name="form1" >
           <table>
                <tr> 
                     <th>用户登录</th>
                </tr>
                <tr> 
                     <td>用户名：<input type="text" name="name" ><font color="red">${requstScope.errors.name}</font></td>
                </tr>
                <tr> 
                     <td>密码：<input type="password" name="password" ><font color="red">${requstScope.errors.password}</td>
                </tr>
                <tr> 
                     <td>email：<input type="text" name="email" ></td>
                </tr>
                <tr> 
                     <td><input type="button" name="button" value="登录" ></td>
                </tr>
           </table>
     </form>
     <font>${requestScope.error}</font>
  </body>
</html>
