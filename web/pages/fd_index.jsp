<?xml version="1.0" encoding="UTF-8" ?>   
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>   
	<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel=stylesheet  type=text/css href="${pageContext.request.contextPath}/css/index.css?t=219">

	<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>

<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<meta HTTP-EQUIV="expires" CONTENT="0">
<title><s:property value="#request.store.storeName" /></title>
<script>
function showshade(onoff){
	$('#backshade').toggle(onoff);
		
}
function showloading(onoff){
$('#loading').toggle(onoff);
if(onoff)$('#backshade').toggle(onoff);

}
function showpop(onoff,msg){
$('#pop').toggle(onoff);
if(onoff){
$('#backshade').toggle(true);
$('#popcon').html(msg);
}


}

</script>
</head>

<body style="overflow:hidden;padding:0px;margin:0px;">

<iframe style="background-color:#006699;width:100%;overflow:hidden;border:0" src="indexforward" >

</iframe>
<div  class="backshade" align="center" id="backshade"></div>

<img src="${pageContext.request.contextPath}/images/ajax-loader.gif" id="loading" style="display:none"/>
<div class="pop" align="center" id="pop" >
<div id="popcon"></div>
<div class="button" id="close" onclick="toclose()">OK</div>
</div>

</body>
</html>
<script>
function toclose(){
	$('#backshade').hide();
			$('#pop').hide();
}
$(document).ready(function(){	
$('iframe').css('height',$(window).height());
$('iframe').css('width',$(window).width());
    var scrollTop = $(document).scrollTop();   
    var scrollLeft = $(document).scrollLeft(); 
	var top = ($(window).height() - $("#pop").height())/3;   
    var left = ($(window).width() - $("#pop").width())/2;   
    $("#pop").css( { position : 'absolute', 'top' : top + scrollTop, left : left + scrollLeft} );  	
	top = ($(window).height() - $("#loading").height())/3;   
    left = ($(window).width() - $("#loading").width())/2;   
	$("#loading").css( { position : 'absolute', 'top' : top + scrollTop, left : left + scrollLeft} );  
	$('#backshade').css({height:$(window).height(),width:$(window).width()});

});

</script>