<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>千秋建筑装饰</title>
<style type="text/css">
.relative {
	position: relative;
}

.margin_c {
	margin: 0 auto;
}

.absolute {
	position: absolute;
}
</style>
</head>
<body>
	<%@include file="head.jsp"%>
	<s:include value="body.jsp"></s:include>
	<div style="width: 280px; display: none;" class="relative margin_c"
		id="div_qrcode">
		<div align="center"
			style="z-index: 999; bottom: 0px; width: 258px; height: 258px; border: 1px solid #9d9484; background: #ffffff; text-align: center;"
			class="absolute">
			<img onclick="jQuery('#div_qrcode').slideUp()"
				style="width: 258px; height: 258px;" src="" id="qr_img">
		</div>
	</div>
	<div style="width: 900px; margin: 0 auto;">
		<table align="center" border="0" cellpadding="0" cellspacing="0"
			width="900">
			<tbody>
				<tr>
					<td><img
						src="${pageContext.request.contextPath}/pages/pc/door/images/bot2.jpg"
						height="127" width="900"></td>
				</tr>
			</tbody>
		</table>
		<table class="banquan" align="center" border="0" cellpadding="0"
			cellspacing="0" width="900" style="margin: auto;">
			<tbody>
				<tr>
					<td align="left" height="71" valign="top" width="70"><img
						src="${pageContext.request.contextPath}/pages/pc/door/images/bg-bot2.gif"
						height="40" width="41"></td>
					<td class="banquanC" valign="top" width="480"><a
						href="${pageContext.request.contextPath}/introfdget.action?type=3">景区简介</a>
						| <a
						href="${pageContext.request.contextPath}/introfdget.action?type=4">公告</a>
						| <a
						href="${pageContext.request.contextPath}/fdview/sceneryspot_list.jsp">景点介绍</a>
						| <a
						href="${pageContext.request.contextPath}/fdview/food_list.jsp">特产风味</a>
						| <a
						href="${pageContext.request.contextPath}/fdview/activitylist.jsp">最新活动</a>
						| <a href="${pageContext.request.contextPath}/wayfdget.action">交通指南</a>
						| <a
						href="${pageContext.request.contextPath}/wisdommapfdget.action">智慧地图</a>
						| <a href="${pageContext.request.contextPath}/fdview/suggest.jsp">意见建议</a>
						| <a
						href="${pageContext.request.contextPath}/introfdget.action?type=1">景区地址</a>
						<span style="display: block; padding-top: 4px;">
							新ICP备05003425号 上海恺升技术支持 </span></td>
					<td align="left" width="100"><img id="weixin"
						onclick="qrcodeShow()"
						src="${pageContext.request.contextPath}/pages/pc/door/images/weixin.png"
						height="71" width="86" /></td>
					<td align="left" width="100"><a
						href="http://www.bz110.gov.cn/" target="_blank"><img
							src="${pageContext.request.contextPath}/pages/pc/door/images/jingcha.gif"
							height="71" width="86"></a></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
<script type="text/javascript">
	function qrcodeShow() {
		jQuery('#div_qrcode').css("display", "block");
		jQuery("#qr_img").attr("src",
				"${pageContext.request.contextPath}/pages/pc/door/images/qrcode.jpg");
		jQuery('#div_qrcode').slideDown();
	}
</script>
</html>