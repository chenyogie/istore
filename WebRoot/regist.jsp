<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>regist</title>

<script type="text/javascript">
	
		function changeImg(img){
			img.src = img.src + "?/time=" + new Date().getTime(); 
		}
		
		/*
		 * 表单校验， 当所有的校验都为true的时候才允许提交表单
		 */
		function checkForm(){
			
			// 完成非空校验
			
			var flag = true;
			
			flag = checkNull("username", "用户名不能为空") && flag;
			flag = checkNull("password", "密码不能为空") && flag;
			flag = checkNull("password2", "确认密码不能为空") && flag;
			flag = checkNull("nickname", "昵称不能为空") && flag;
			flag = checkNull("email", "邮箱不能为空") && flag;
			flag = checkNull("validate", "验证码不能为空") && flag;
			
			// 校验两次密码一致
			var pwd1 = document.getElementsByName("password")[0].value;
			var pwd2 = document.getElementsByName("password2")[0].value;
			
			if(pwd1 != pwd2){
				document.getElementById("password2_msg").innerHTML = "<font color='red'>两次密码不一致！！！</font>";
				
				flag = false;
			}
			
			// 邮箱校验
			// 邮箱格式： XX@xx。com
			var email = document.getElementsByName("email")[0].value;
			if(email != null && email != "" && !/^\w+@\w+(\.\w+)+$/.test(email)){
				document.getElementById("email_msg").innerHTML = "<font color='red'>邮箱格式不匹配！！！</font>";
				
				flag = false;
			}
			
			return flag;
		}
		
		/*
		 * 检查非空的函数
		 */
		function checkNull(name, msg){
			document.getElementById(name + "_msg").innerHTML = "";
			var objValue = document.getElementsByName(name)[0].value;
			
			if(objValue == null || objValue == ""){
				document.getElementById(name + "_msg").innerHTML = "<font color='red'>" + msg + "</font>";
				
				return false;
			}
			
			return true;
		}
	
	</script>

</head>
<body>


	<div align="center">
		<h1>istore -- 注册</h1>

		<br>

		<form action="${pageContext.request.contextPath }/RegistServlet"
			method="post" onsubmit="return checkForm()">

			<table>
				<tr>
					<td>用户名</td>
					<td><input type="text" name="username"></td>
					<td><span id="username_msg"></span></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input type="password" name="password"></td>
					<td><span id="password_msg"></span></td>
				</tr>
				<tr>
					<td>确认密码</td>
					<td><input type="password" name="password2"></td>
					<td><span id="password2_msg"></span></td>
				</tr>
				<tr>
					<td>昵称</td>
					<td><input type="text" name="nickname"></td>
					<td><span id="nickname_msg"></span></td>
				</tr>
				<tr>
					<td>电子邮箱</td>
					<td><input type="text" name="email"></td>
					<td><span id="email_msg"></span></td>
				</tr>
				<tr>
					<td>验证码</td>
					<td><input type="text" name="validate"></td>
					<td id="validate_msg">${msg }</td>
				</tr>
				<tr>
					<td><input type="submit" value="确认注册"></td>
					<td><img alt="验证码"
						src="${pageContext.request.contextPath }/ValidateServlet"
						onclick="changeImg(this)" style="cursor: pointer;"></td>
				</tr>
			</table>

		</form>

	</div>
















</body>
</html>