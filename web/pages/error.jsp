<?xml version="1.0" encoding="UTF-8" ?>   
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>   
	<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/touchScroll.js"></script>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<meta HTTP-EQUIV="expires" CONTENT="0">
<title>页面错误</title>
<style>
body{

	margin:0px;
	padding:0px;
	background-color:#dfe3e6;
	overflow:hidden;
	font-family: "Helvetica Neue",Helvetica,Arial;
font-size:15px;
}
.bar{
height:40px;
border-bottom:#333333 solid 2px;
background:#2c9cc8;
color:#FFF;
line-height:40px;
font-weight:bold;
}
.block{
border-radius:6px;
background-color:#ffffff;
border:1px  #999999 solid;
width:100%;

}
</style>
</head>

<body onhashchange="toback()">
<div class="bar" align="center">页面错误</div>

<div style="margin:10px;">
<div class="block" style="height:40px;line-height:40px;font-weight:bold" align="center">	
	<s:property value='#request.errorinfo'/>
</div>	
</div>
</body>
</html>


<script>

function toback(){
top.showloading(true);
history.back(-1);
}
function tohome(){
top.showloading(true);
document.location.href="index";
}

$(document).ready(function(){

	top.showloading(false);
	top.showshade(false);
	
	// t1=new TouchScroll({id:'scroll','width':5,'opacity':0.7,color:'#555',minLength:20});		
});
</script>
