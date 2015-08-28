<?xml version="1.0" encoding="UTF-8" ?>   
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>   
	<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel=stylesheet  type=text/css href="${pageContext.request.contextPath}/css/aboutus.css?a=112">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/touchScroll.dev.js"></script>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<meta HTTP-EQUIV="expires" CONTENT="0">
<title></title>
<style>
body{

	margin:0px;
	padding:0px;
	background-color:#dfdfdf;
	overflow:hidden;
	font-family: "Helvetica Neue",Helvetica,Arial;
font-size:15px;
}
.bar{
height:40px;
border-bottom:#333333 solid 2px;
background:#BF0F83;
color:#FFF;
line-height:40px;
font-weight:bold;
}
.block{

width:100%;
min-height:100px;
height:100%;

padding:20px;
}
#wrapper{
position:absolute;
top:40px
;bottom:0px;
left:0;
background:#d9d9d9;
right:0px;

}
.orderdetail{

-webkit-box-shadow: 0 0 10px rgba(0,0,0,0.6);
-moz-box-shadow: 0 0 10px rgba(0,0,0,0.6);
box-shadow: 0 0 10px rgba(0,0,0,0.6);
-webkit-border-radius: 6px;
-moz-border-radius: 6px;
-ms-border-radius: 6px;
-o-border-radius: 6px;
border-radius: 6px;
padding: 0px 20px;
background:#ffffff;
margin-top:10px;
margin-bottom:10px;
}
</style>
</head>

<body onload="myfunction()">
<div class="bar" align="center"><label id="title"></label></div>
<div  id="wrapper"  align="center">
<div class="orderdetail" align="left">
<div><s:hidden name="#request.fdintro.type" id="type"/></div>
<div  align="left" id="test" style="display:none;padding:5px;" >	
	<s:property value="#request.fdintro.detail"/>
</div>	

</div>
</div>
</body>
</html>

<script>

function toback(){

history.back(-1);
}

$(document).ready(function(){	

function replaceAll(str)  
{  
    if(str!=null)  
    str = str.replace(/word/g,"Excel");  
    return str;  
} 
    var type = $('#type').val();
  
	if(type == 1){
		$("#title").html("公司简介");
	}else if(type == 2){
		$("#title").html("企业文化");
	}else if(type == 3){
		$("#title").html("大事记");
	}else if(type == 4){
		$("#title").html("总经理致辞");
	}
	var tt =$('#test').html();
	tt=HtmlDecode(tt);	
	$('#test').html(tt).show();
	$('.orderdetail').css('width',$(window).width()-60);
	top.showloading(false);
	top.showshade(false);
	
});
function myfunction(){
	document.ontouchmove=function(){return false;}
var t1=new TouchScroll({id:'wrapper','width':2,mouseWheel:true,keyPress:true,'opacity':0.5,color:'#000',minLength:5});
document.title = "千秋建筑装饰";
}
function HtmlDecode(text) 
{ 
return text.replace(/&amp;/g, '&').replace(/&quot;/g, '/"').replace(/&lt;/g, '<').replace(/&gt;/g, '>'); 
}
</script>

