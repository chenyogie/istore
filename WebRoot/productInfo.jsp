<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1 align="center">商品详情</h1>

	<div align="center">
	
		<table width="85%">
		
			<tr>
				<td width="50%">
					<img alt="${product.name }" src="/istore/ImgServlet?imgurl=${product.imgurl }">
				</td>
				<td>
					${product.name }
					<br>
					${product.category }
					<br>
					${product.price }
					<br>
					${product.description }
				</td>
				<td>
					<a href="/istore/AddToCartServlet?id=${product.id }">加入购物车</a>
				</td>
			</tr>
		
		</table>
	
	</div>

</body>
</html>