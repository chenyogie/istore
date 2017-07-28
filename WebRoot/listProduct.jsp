<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list products</title>
</head>
<body>

	<h1 align="center">istore -- produsts</h1>
	
	<div align="center">
	
		<table width="80%">
			<c:forEach items="${requestScope.list }" var="product" step="1">
				<tr>
					<td>
						<a href="/istore/ProductInfoServlet?id=${product.id }">
							<img alt="${product.name }" src="/istore/ImgServlet?imgurl=${product.imgurls }">
						</a>
					</td>
					<td>
						${product.name }						
					</td>
					<td>
						${product.category }
					</td>
					<td>
						${product.price }
					</td>
					<td>
						<c:if test="${product.productnumber > 0 }">
							<font color='blue'>有货</font>	
						</c:if>
						<c:if test="${product.productnumber <= 0 }">
							<font color='red'>缺货</font>
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="5"><hr width="85%"></td>
				</tr>
			</c:forEach>
		</table>
	
	</div>
	


</body>
</html>