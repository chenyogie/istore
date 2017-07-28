<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>my cart</title>

<script type="text/javascript">

	function changerBuyNumber(id, obj, oldnumber){
		// 判断输入的内容是否是数字
		if(!/^[1-9]\d*$/.test(obj.value)){
			alert("请输入正确的购买数字!!!");
			
			obj.value = oldnumber;
			
			return;
		}
		
		window.location.href="/istore/ChangeBuyNumberServlet?id="+ id +"&buynumber=" + obj.value;
	}

</script>

</head>
<body>

	<h1 align="center">istore -- 我的购物车</h1>
	
	<br>
	<hr>
	<br>
	
	<div align="center">
	
		<p align="right">
			<a href="/istore/ListProductServlet">返回到商品列表</a>
			<a href="/istore/ClearCartServlet">清空购物车</a>
		</p>

		<table border="1" style="width: 90%; border-collapse: collapse; text-align: center;">
		
			<tr>
				<th>商品图片</th>
				<th>商品名称</th>
				<th>商品种类</th>
				<th>商品单价</th>
				<th>购买数量</th>
				<th>库存状态</th>
				<th>商品总价</th>
				<th>删除操作</th>
			</tr>
			
			<c:set var="totalmoney" value="0"></c:set>
			<c:forEach items="${sessionScope.cartmap }" var="entry" step="1">
				<tr>
					<td>
						<img alt="${entry.key.name }" src="/istore/ImgServlet?imgurl=${entry.key.imgurls }">
					</td>
					<td>
						${entry.key.name }
					</td>
					<td>
						${entry.key.category }
					</td>
					<td>
						${entry.key.price }
					</td>
					<td>
						<input type="text" value="${entry.value }" style="width: 25px" onchange="changerBuyNumber('${entry.key.id }',this,'${entry.value }')">
					</td>
					<td>
						<c:if test="${entry.value <= entry.key.productnumber }">
							<font color="blue">有货</font>
						</c:if>
						<c:if test="${entry.value > entry.key.productnumber }">
							<font color="red">缺货</font>
						</c:if>
					</td>
					<td>
						<c:set var="totalmoney" value="${totalmoney + entry.key.price * entry.value }"></c:set>
						${entry.key.price * entry.value }
					</td>
					<td>
						<a href="/istore/DeleteFormCartServlet?id=${entry.key.id }">删除商品</a>
					</td>
				</tr>
			
			</c:forEach>
		
		</table>
	
	</div>
	
	<div align="right"><span style="color: red; font-weight: bold; font-size: 24px">结算金额：${totalmoney }</span></div>
</body>
</html>