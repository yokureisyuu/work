<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script class="jsbin" src="js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<title>千秋建筑装饰有限公司管理系统</title>
<style>
.login-panel{
position: absolute;
top: 100px;
right: 150px;
width:230px;
height:200px;
padding:10px;
border-radius: 2px;
-moz-border-radius: 2px;
-webkit-border-radius: 2px;
box-shadow: 3px 3px 5px rgba(0,0,0,0.5);
-moz-box-shadow: 3px 3px 5px rgba(0,0,0,0.5);
-webkit-box-shadow: 3px 3px 5px rgba(0,0,0,0.5);
background-color: #FFF;}

.login{
display: inline-block;
display: inline-block;
margin-left:50px;
width: 120px;
margin-top:20px;
line-height: 25px;
background-color: #44b549;
color: #fff;
border-radius: 3px;
-moz-border-radius: 3px;
-webkit-border-radius: 3px;
text-align: center;
}
.bottom{
position:absolute;
bottom:10px;
left:0px;
right:0px;

}
}
</style>
</head>

<body>

<div style="width:100%;height:60px;background:url(images/back.png)"> <img src="images/top.jpg"/></div>
<div style="background:url(images/loginback.jpg);height:460px;">
<div class="login-panel">    
<h3>登录</h3>  
<form>
<table style="width:300px">
<tr>
<td><img src="images/user.jpg"/></td><td><input type='text' name="username"/></td>
</tr>
<tr>
<td><img src="images/pwd.jpg"/></td><td><input type='password' name="password"/></td>
</tr>
<tr>
<td colspan="2" align="left"><div style="color:#FF0000;font-size:14px;padding-left:50px;" id="errorinfo"></div></td>
</tr>
</table>

<a class="login" title="点击登录" href="javascript:;" id="login_button" onclick="login()">登录</a>
</form>
 </div>
</div>
<div  class="bottom" align="center">千秋建筑装饰有限公司管理系统V1.0</div>
</body>
</html>
<script>
function login(){

$('#errorinfo').html("登录中......");

$.ajax({
  	type: 'post',
 	url: "adminlogin",
	data: $("form").serialize(),
 	success: function(msg){
		if(msg=='success'){
				document.location.href="index";
		}else $('#errorinfo').html(msg).slideDown().delay(2000).slideUp();
	}
});
}

</script>