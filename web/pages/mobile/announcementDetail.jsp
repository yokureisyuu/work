<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="telephone=no" name="format-detection" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property
		value="#request.announcementDDO.announcementName" /></title>
<script src="template/js/jquery.js" type="text/javascript"></script>
<style>
#preloader {
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: #fefefe;
	z-index: 99;
	height: 100%;
}

#status {
	width: 100px;
	height: 100px;
	position: absolute;
	left: 50%;
	top: 50%;
	background: url(template/images/loadingimg.gif) no-repeat 50% 0;
	margin: -50px 0 0 -50px;
	text-align: center;
	line-height: 100px;
	font-size: 12px;
}
</style>

</head>
<body>
	<div id="preloader">
		<div id="status">正在加载中...</div>
	</div>
	<script type="text/javascript">
		jQuery(window).load(function() {
			jQuery("#status").fadeOut();
			jQuery("#preloader").fadeOut("slow");
		});
	</script>
	<div>${announcementDDO.announcementDesc}</div>
</body>
</html>