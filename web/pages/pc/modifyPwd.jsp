<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script class="jsbin" src="../js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
<title>无标题文档</title>
<style>
body{

	margin:0px;
	padding:0px;

	overflow-x:hidden;
	font-family: "Helvetica Neue",Helvetica,Arial;
font-size:12px;

}
.base tr{
height:40px;
font-size:12px;

}
.base tr td>input{
border:0px;
border-bottom:1px solid #000000;
width:300px;
padding-left:10px;

}

</style>
</head>

<body>
<div style='color:red;border:1px solid red;padding-left:10px;padding-right:10px;font-size:12px;background-color:#FFFFFF;margin-left:20px;padding-top:5px;padding-bottom:5px;position:absolute;display:none' id='errorinfo'></div>
<form>
<table class="base" align="center">

<tr>
<td>用户名:</td><td><input type="text" value="<s:property value='#session.user.name'/>" name="username" class="easyui-validatebox " data-options="required:true,validType:'username'"></td>
</tr>
<tr>
<td>联系电话:</td><td><input type="text" value="<s:property value='#session.user.phone'/>" name="phone" class="easyui-validatebox " data-options="required:true,validType:'phone'"></td>
</tr>
<tr>
<td>原密码:</td><td><input type="password" value="<s:property value='#session.user.password'/>" name="prepassword" class="easyui-validatebox " data-options="required:true"></td>
</tr>
<tr>
<td>新密码:</td><td><input type="password" value="" name="password" class="easyui-validatebox " data-options="required:true,validType:'pwd'" id="password"></td>
</tr>
<tr>
<td>新密码确认:</td><td><input type="password" value="" name="password2" class="easyui-validatebox " data-options="required:true,validType:'same'"></td>
</tr>
<tr>
<td colspan="2" align="center"><input type="button" value="修改" onclick="submitChange()" style="height:30px;font-weight:bold"></td>
</tr>
</table>

</form>

</body>
</html>
<script>
function submitChange(){

	$("form").form("submit",{
				url: "adminmodifyPwd",
				onSubmit: function(){   
					var valide= $("form").form('validate');
						 if(valide){
						 	$('#errorinfo').html("提交中......").slideDown();
							return true;
						 }else return false;
                          },  
				 success:function(data){ 
				 		if(data=="success"){
							$('#errorinfo').html("修改成功").slideDown().delay(2000).slideUp();	
							
						}else{
							$('#errorinfo').html(data).slideDown().delay(2000).slideUp();	
						}
				   }
			});

}
$(document).ready(function(){	

	conHeight = $('.west').height();
	var toph = ($(window).height() - $("#errorinfo").height())/5;   
   var  lefth = ($(window).width() - $("#errorinfo").width())/2;   
	var scrollTop = $(document).scrollTop();   
    var scrollLeft = $(document).scrollLeft(); 

	$("#errorinfo").css( { position : 'absolute', 'top' : 5+ scrollTop, left : lefth + scrollLeft} );  



});
</script>