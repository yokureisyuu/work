<?xml version="1.0" encoding="UTF-8" ?>   
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>   
	<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel=stylesheet  type=text/css href="${pageContext.request.contextPath}/css/login.css?a=112">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/touchScroll.dev.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=54c2fb9a3cc9a9bed664d915e429d7c3"></script>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<meta HTTP-EQUIV="expires" CONTENT="0">
<title>交通指引</title>
<style>
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
<div class="bar" align="center"><span style="float:left;border-right:1px outset #663399" onclick="toback()">&nbsp;&nbsp;<img src="images/frontback.png" style="width:25px">&nbsp;&nbsp;</span>交通指引<span style="float:right;width:60px">&nbsp;</span></div>
<div  id="wrapper"  align="center">
<div class="orderdetail" align="left">
<div style="height:40px;line-height:40px;font-weight:bold">地址:<s:property value='#request.fdway.address'/></div>
<div style="width:100%;height:300px;border:#ccc solid 1px;" id="dituContent" align="center"></div>

<div id="detail" style="display:none;" align="center"><s:property value='#request.fdway.detail'/></div>
</div>
</div>
</body>
</html>

<script>

function toback(){
top.showloading(true);
history.go(-1);
}
    function initMap(){
        createMap();//创建地图
        setMapEvent();//设置地图事件
        addMapControl();//向地图添加控件
    }
    
    //创建地图函数：
    function createMap(){
        var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
        var point = new BMap.Point(<s:property value='#request.fdway.longitude'/>,<s:property value='#request.fdway.latitude'/>);//定义一个中心点坐标
    	var myIcon =new BMap.Icon("${pageContext.request.contextPath}/images/mark.png",
    			new BMap.Size(32, 23)     //图片的偏移量。为了是图片底部中心对准坐标点。  
			);
        var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注，为要查询的地方对应的经纬度
    	map.addOverlay(marker);
        map.centerAndZoom(point,18);//设定地图的中心点和坐标并将地图显示在地图容器中
        window.map = map;//将map变量存储在全局
        marker.addEventListener("click", function() {
         	var latitude = "<s:property value='#request.fdway.latitude'/>";
        	var longitude = "<s:property value='#request.fdway.longitude'/>";
        	var address = "<s:property value='#request.fdway.address'/>";
        	document.location.href = "http://api.map.baidu.com/marker?location="+ latitude +","+ longitude +"&title="+ address +"&content=&output=html&src=微信";
        });
    }
    
    //地图事件设置函数：
    function setMapEvent(){
        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        map.enableKeyboard();//启用键盘上下左右键移动地图
    }
    
    //地图控件添加函数：
    function addMapControl(){
        //向地图中添加缩放控件
	var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
	map.addControl(ctrl_nav);
        //向地图中添加缩略图控件
	var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
	//map.addControl(ctrl_ove);
        //向地图中添加比例尺控件
	var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
	//map.addControl(ctrl_sca);
    }
    
 function replaceAll(str)  
{  
    if(str!=null)  
    str = str.replace(/word/g,"Excel")  
    return str;  
}    
   
$(document).ready(function(){	

	top.showloading(false);
	top.showshade(false);
	var tt =$('#detail').html();
	tt=HtmlDecode(tt);
	$('#detail').html(tt).show();	
	initMap();//创建和初始化地图
// 	tt=tt.replace(new RegExp("&lt;","gm"),"<") 
// 	tt=tt.replace(new RegExp("&gt;","gm"),">") 
// 	tt=tt.replace(new RegExp("&nbsp;","gm")," ") 	
	$('.orderdetail').css('width',$(window).width()-60);
	//var t1=new TouchScroll({id:'wrapper','width':5,'opacity':0.7,color:'#555',minLength:20});
	//var t1=new TouchScroll({id:'wrapper','width':5,'opacity':0.5,color:'#000',minLength:5});
});
function myfunction(){
document.ontouchmove=function(){return false;}
var t1=new TouchScroll({id:'wrapper','width':2,mouseWheel:true,keyPress:true,'opacity':0.5,color:'#000',minLength:5});
}
function HtmlDecode(text) 
{ 
return text.replace(/&amp;/g, '&').replace(/&quot;/g, '/"').replace(/&lt;/g, '<').replace(/&gt;/g, '>'); 
}
</script>


