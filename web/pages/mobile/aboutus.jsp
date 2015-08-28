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
<title>公司地址</title>

<link href='template/images/wei/store/store.css' rel='stylesheet'
	type='text/css' />
<script src="template/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=1.5&ak=2746f5681a98468753cdb64a16c5b3d4"></script>
<script src="template/js/store.js" type="text/javascript"></script>
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
<style>
ul,li {
	padding: 0;
	margin: 0;
	list-style: none;
}

.addWrap {
	width: 100%;
	background: #fff;
	margin: auto;
	position: relative;
}

.addWrap .swipe {
	height: 150px;
	overflow: hidden;
	visibility: hidden;
	position: relative;
}

.addWrap .swipe-wrap {
	overflow: hidden;
	position: relative;
}

.addWrap .swipe-wrap img {
	width: 100%;
}

.addWrap .swipe-wrap>div {
	float: left;
	width: 100%;
	position: relative;
}

#position {
	position: absolute;
	padding: 0;
	margin: 0;
	bottom: 8px;
	right: 0px;
	text-align: center;
	z-index: 10;
}

#position li {
	width: 10px;
	height: 10px;
	margin: 0 3px;
	display: inline-block;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	background-color: #AFAFAF;
}

#position li.cur {
	background-color: #ffffff;
}

#position li a {
	position: absolute;
	left: 0px;
	width:
}

#addtitle {
	position: absolute;
	bottom: 0px;
	width: 100%;
	height: 30px;
	overflow: hidden;
	line-height: 30px;
	font-size: 14px;
	background: rgba(0, 0, 0, 0.5);
	cursor: pointer;
}

#addtitle li {
	width: 100%;
	height: 30px;
	color: #fff;
	text-indent: 1em;
	display: none;
}

#addtitle a {
	text-decoration: none;
	color: #fff;
}

#addtitle .cur {
	display: block;
}
</style>
<style>
.ant-frame-share {
	margin: 0 auto;
	width: 80%;
	padding-top: 10px;
	padding-bottom: 10px;
}

.ant-button-share {
	cursor: pointer;
	border: 0;
	color: #fff;
	text-align: center;
	border-radius: 3px;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	box-shadow: 0 2px 3px #999;
	-moz-box-shadow: 0 2px 3px #999;
	-webkit-box-shadow: 0 2px 3px #999;
	font-weight: bold;
	height: 32px;
	line-height: 32px;
	font-size: 14px;
	display: inline-block;
	width: 45%;
}

.ant-button-share.friend {
	background: #0ade00;
	background: -moz-linear-gradient(top, #0ade00, #1aa200);
	background: -ms-linear-gradient(top, #0ade00, #1aa200);
	background: -webkit-gradient(linear, 0 0, 0 100%, from(#0ade00),
		to(#1aa200) )
}

.ant-button-share.quan {
	float: right;
	background: #3db8e5;
	background: -moz-linear-gradient(top, #3db8e5, #4160e2);
	background: -ms-linear-gradient(top, #3db8e5, #4160e2);
	background: -webkit-gradient(linear, 0 0, 0 100%, from(#3db8e5),
		to(#4160e2) )
}

.sharehelper {
	position: fixed;
	top: 0;
	right: 0;
	width: 100%;
	height: 100%;
	background: url(template/images/share.png) center top no-repeat;
	background-size: 100% auto;
	z-index: 99999;
	display: none;
}

.ant-masklayer.black {
	opacity: .8;
	background-color: #000;
}

.ant-masklayer {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	z-index: 88888;
	opacity: 0;
	margin: 0;
	padding: 0;
	display: none;
}
</style>
<script>
	var baidumap = "2746f5681a98468753cdb64a16c5b3d4";
	$(document).ready(stores.auth_init);
</script>
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

	
	<script src="template/js/slides.min.jquery.js"></script>
	<div class="addWrap">
		<div class="swipe" id="mySwipe">
			<div class="swipe-wrap">

				<div>
					<a
						href="http://api.map.baidu.com/marker?location=31.466508,121.123862&title=%e5%8d%83%e7%a7%8b%e5%bb%ba%e7%ad%91%e8%a3%85%e9%a5%b0&name=%e5%8d%83%e7%a7%8b%e5%bb%ba%e7%ad%91%e8%a3%85%e9%a5%b0&content=&output=html"
						title="<s:property value='#session.store.storeName' />"><img
						src="images/brand.jpg" height="160"></a>
				</div>
				
				<div>
					<a
						href="http://api.map.baidu.com/marker?location=31.466508,121.123862&title=%e5%8d%83%e7%a7%8b%e5%bb%ba%e7%ad%91%e8%a3%85%e9%a5%b0&name=%e5%8d%83%e7%a7%8b%e5%bb%ba%e7%ad%91%e8%a3%85%e9%a5%b0&content=&output=html"
						title="<s:property value='#session.store.storeName' />"><img
						src="images/brand.jpg" height="160"></a>
				</div>

			</div>
		</div>
		<ul id="addtitle">

			<li><a
				href="http://api.map.baidu.com/marker?location=31.466508,121.123862&title=%e5%8d%83%e7%a7%8b%e5%bb%ba%e7%ad%91%e8%a3%85%e9%a5%b0&name=%e5%8d%83%e7%a7%8b%e5%bb%ba%e7%ad%91%e8%a3%85%e9%a5%b0&content=&output=html"><s:property
						value="#session.store.storeName" /></a></li>

		</ul>
		<ul id="position">

			<li></li>

		</ul>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#addtitle li:first').addClass("cur");
			$('#position li:first').addClass("cur");
			var titles = document.getElementById('addtitle')
					.getElementsByTagName('li');
			var bullets = document.getElementById('position')
					.getElementsByTagName('li');
			 Swipe(document.getElementById('mySwipe'), {
				auto : 2000,
				continuous : true,
				disableScroll : false,
				callback : function(pos) {
					var i = bullets.length;
					while (i--) {
						bullets[i].className = ' ';
						titles[i].className = ' ';
					}
					bullets[pos].className = 'cur';
					titles[pos].className = 'cur';
				}
			});
		});
	</script>


	<dl id="your_address" style="display: none;">
		<dt>您现在的位置</dt>
		<dd></dd>
	</dl>
	<div id="stores">
		,
		<div class="store " lng="121.123862" lat="31.466508">
			<h1>
				<a
					href="http://api.map.baidu.com/marker?location=31.466508,121.123862&title=%e5%8d%83%e7%a7%8b%e5%bb%ba%e7%ad%91%e8%a3%85%e9%a5%b0&name=%e5%8d%83%e7%a7%8b%e5%bb%ba%e7%ad%91%e8%a3%85%e9%a5%b0&content=&output=html"><span
					class="store_name"><s:property
							value="#session.store.storeName" /></span> <span class="dis">0m</span>
				</a>
			</h1>
			<div class="item ">
				<div class="address_ico"></div>
				<div class="address">
					<a
						href="http://api.map.baidu.com/marker?location=31.466508,121.123862&title=%e5%8d%83%e7%a7%8b%e5%bb%ba%e7%ad%91%e8%a3%85%e9%a5%b0&name=%e5%8d%83%e7%a7%8b%e5%bb%ba%e7%ad%91%e8%a3%85%e9%a5%b0&content=&output=html"
						data-transition="flip"><s:property
							value="#session.store.storeName" /></a>
				</div>
				<div class="lbs">
					<a
						href="http://api.map.baidu.com/marker?location=31.466508,121.123862&title=%e5%8d%83%e7%a7%8b%e5%bb%ba%e7%ad%91%e8%a3%85%e9%a5%b0&content=&output=html"
						data-transition="flip"></a>
				</div>
			</div>
			<div class="item  last ">
				<div class="tel_ico"></div>
				<ul class="telephone">
					<li><a
						href='tel:<s:property value="#request.store.storeComm"/>'><s:property
								value="#request.store.storeComm" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
				</ul>




				<div class="clear"></div>
			</div>

		</div>

		<div class="content">
			<div class="contenttext">
				地址:
				<s:property value='#request.fdway.address' />
				${fdway.detail}


			</div>
		</div>


		
		<div class="sharehelper"
			onclick="$('.sharehelper').hide();$('.ant-masklayer').hide();"></div>
		<div class="ant-frame-share">
			<div class="ant-button-share friend">发送给好友</div>
			<div class="ant-button-share quan">分享到朋友圈</div>
		</div>
		<div class="ant-masklayer black" style=""></div>
		<script src="template/js/share.js"></script>
		<script>
			//$($('#stores .store').get(0)).addClass('nearest').prependTo($('#stores'));
		</script>
	</div>
</body>
</html>