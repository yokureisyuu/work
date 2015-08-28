<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel=stylesheet type=text/css
	href="${pageContext.request.contextPath}/css/scenery_list.css">
	<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
	<meta name="viewport"
		content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
			<meta HTTP-EQUIV="expires" CONTENT="0">
				<title>千秋建筑装饰</title>
</head>

<body>

	<div class="menutypebar" align="center" id="menutypebar">
	<span style="float:left;border-right:1px outset #663399" onclick="toback()" class="back">&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/images/frontback.png" style="width:25px">&nbsp;&nbsp;</span>
                 画册
    <span style="float:right" style="width:25px">&nbsp;&nbsp;</span>		
	</div>

	<div class="gonggao">
		<marquee style="margin-left:5px;margin-right:5px;"> <s:property
			value="#session.store.storeDiscountInfo" /></marquee>
	</div>
	<div
		style="padding: 3px; position: absolute; z-index: 1; top: 70px; left: 0px; bottom: 0px; right: 0px; background: FFFFFF; overflow: auto;">
		<table>
			<s:iterator var="ent" value="#request.scenerys" status="statu">
				<tr onclick="goDetail(<s:property value='#ent.id' />)">
					<td width="90px" align="center"><img
						src="<s:property value='#ent.sceneryHead'/>" /></td>
					<td align="left" valign="top">
						<div class="title">
							<s:property value="#ent.sceneryName" />
						</div>
						<div>
						
							${ent.sceneryDesc}
						</div>
					</td>
				</tr>
				</div>
			</s:iterator>
		</table>
	</div>
</body>
</html>

<script>
function goDetail(id){
top.showloading(true);
document.location.href="scenerygetFrontOneDetail?id="+id;
}
function toback(){
top.showloading(true);
history.back(-1);
}
$(document).ready(function(){	
top.showshade(false);
top.showloading(false);

});
</script>


