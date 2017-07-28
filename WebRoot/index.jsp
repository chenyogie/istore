<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>

	<h1 align="center">istore</h1>
	
	<br>
	<br>
	
	<hr>
	
	<br>
	<br>
	
	<!-- 
		判断是否是已经登录的用户
		如果是： 则提示 欢迎XXX回来
		如果否： 则提示 欢迎游客登录， 并且提示注册 或者 登录	
	 -->
	 
	<!-- 用户不存在 -->
	<c:if test="${sessionScope.user == null }">
		游客 欢迎光临 请
		<a href="/istore/regist.jsp">注册</a>
		<a href="/istore/login.jsp">登录</a>
	</c:if>
	
	<!-- 用户存在 -->
	<c:if test="${sessionScope.user != null }">
		${sessionScope.user.username }, 欢迎回来.
		
		<a href="addProduct.jsp">添加商品</a>
		<a href="${pageContext.request.contextPath }/ListProductServlet">商品列表</a>
		<a href="${pageContext.request.contextPath }/cart.jsp">查看购物车</a>
		
		<a href="${pageContext.request.contextPath }/LogoutServlet">安全退出</a>
	</c:if>
	
	
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>