<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add product</title>

<script type="text/javascript">
	function checkprice(){
		var price = document.getElementsByName("price")[0].value;
		
		// is Not a Number
		if(isNaN(price)){
			alert("必须要写商品单价");	
			document.getElementsByName("price")[0].value = "";
			
			return false;
		} else if(price <= 0){
			alert("东西都给人家了， 还想再给人家钱吗");
			document.getElementsByName("price")[0].value = "";
			
			return false;
		} else {
			return true;
		}
	}
</script>

</head>
<body>

	<h1 align="center">istore -- add product</h1>
	
	<hr>
	
	<br>
	<br>

	<div align="center">
		<form action="${pageContext.request.contextPath }/AddProductServlet"
		 method="post" enctype="multipart/form-data" onsubmit="return checkprice()">
		
			<table width="50%" border="1" style="border-collapse: collapse;">
			
				<tr>
					<td>商品名称</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>商品类型</td>
					<td>
						<select name="category">
							<option value="生活用品">生活用品</option>
							<option value="数码电器">数码电器</option>
							<option value="美妆保健">美妆保健</option>
							<option value="户外运动">户外运动</option>
							<option value="礼品鲜花">礼品鲜花</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>商品单价</td>
					<td><input type="text" name="price" onblur="checkprice()"></td>
				</tr>
				<tr>
					<td>库存数量</td>
					<td><input type="text" name="productnumber"></td>
				</tr>
				<tr>
					<td>商品展示</td>
					<td><input type="file" name="imgurl"></td>
				</tr>
				<tr>
					<td>商品简介</td>
					<td><textarea rows="5" cols="30" name="description"></textarea></td>
				</tr>
				<tr align="center">
					<td>
						<input type="reset" value="重置数据">
					</td>
					<td>
						<input type="submit" value="提交数据">
					</td>
				</tr>
				
			
			</table>
		
		</form>
	</div>

























</body>
</html>