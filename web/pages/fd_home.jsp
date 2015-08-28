<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/touchScroll.dev.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/touchslider.dev.js"></script>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
		<meta http-equiv="expires" content="0"/>
			<title>千秋建筑装饰</title>
			<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	background-color: #d9d9d9;
	overflow: hidden;
	font-family: "Helvetica Neue", Helvetica, Arial;
	font-size: 15px;
}

* {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

a,img {
	border: 0;
}

#header {
	position: absolute;
	z-index: 2;
	top: 0;
	left: 0;
	width: 100%;
	height: 45px;
	line-height: 45px;
	padding: 0;
	color: #eee;
	text-align: center;
	border-bottom: #333333 solid 2px;
	background: #BF0F83;
	font-weight: bold;
}

#header a,#footer a {
	color: #f3f3f3;
	text-decoration: none;
	font-weight: bold;
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.5);
}

.relative {
	position: relative;
}

.margin_c {
	margin: 0 auto;
}

.absolute {
	position: absolute;
}

#wrapper {
	position: absolute;
	z-index: 1;
	top: 45px;
	bottom: 0px;
	left: 0;
	width: 100%;
	background: #d9d9d9;
	overflow: hidden;
}

/* swipe */
.swipe {
	width: 100%;
	height: 235px;
	overflow: hidden;
	position: relative;
}

.swipe ul {
	-webkit-transition: left 800ms ease-in 0;
	-moz-transition: left 800ms ease-in 0;
	-o-transition: left 800ms ease-in 0;
	-ms-transition: left 800ms ease-in 0;
	transition: left 800ms ease-in 0;
}

.swipe #pagenavi {
	position: absolute;
	left: 0;
	bottom: 10px;
	text-align: center;
	width: 100%;
}

.swipe #pagenavi a {
	width: 14px;
	height: 14px;
	line-height: 99em;
	background: #b5b5b5;
	-webkit-border-radius: 50%;
	-moz-border-radius: 50%;
	border-radius: 50%;
	margin: 0 4px;
	overflow: hidden;
	cursor: pointer;
	display: inline-block;
	*display: inline;
	*zoom: 1;
}

.swipe #pagenavi a.active {
	background: #C80002;
}

#scroller img {
	width: 100px;
	margin-top: 10px;
}
</style>
</head>

<body>

	<div>
		<div>
			<div id="header">
				<span style="float: left;" onclick="qrcodeShow()"><img
					src="images/qrcode.jpg" style="width: 45px; height: 45px;" /> </span>
				<s:property value="#session.store.storeName" />
				<span style="float: right; width: 60px">&nbsp;</span>
			</div>
			<div id="div_qrcode" class="relative margin_c"
				style="width: 320px; display: none;">
				<div align="center" class="absolute"
					style="z-index: 999; left: 8px; top: 55px; width: 300px; height: 300px; border: 2px solid #9d9484; background: #ffffff; text-align: center;">
					<img id="qr_img" src="" style="width: 300px; height: 300px;"
						onclick="$('#div_qrcode').slideUp()" />
				</div>
			</div>
			<div id="wrapper">
				<div class="swipe">
					<ul id="slider">
					</ul>
					<div id="pagenavi"></div>
				</div>
				<!--swipe end-->
				<div id="scroller" align="center">

					<div style="display: inline-block" align="center"
						onclick="goSceneIntro()">
						<img src="images/Home.png" />
						<h4>公司案例</h4>
					</div>
					<div style="display: inline-block" align="center"
						onclick="goToHere()">
						<img src="images/globe.png" />
						<h4>交通指引</h4>
					</div>
					<div style="display: inline-block" align="center"
						onclick="goIntro('1')">
						<img src="images/letter.png" />
						<h4>公司简介</h4>
					</div>
					<div style="display: inline-block" align="center"
						onclick="goIntro('2')">
						<img src="images/group-people.png" />
						<h4>企业文化</h4>
					</div>
					<div style="display: inline-block" align="center"
						onclick="goIntro('3')">
						<img src="images/news.png" />
						<h4>大事记</h4>
					</div>
					<div style="display: inline-block" align="center"
						onclick="goIntro('4')">
						<img style="width:160;height:160" src="images/microhone.png" />
						<h4>总经理致辞</h4>
					</div>
				</div>
				<!--scroller end-->
			</div>

		</div>
	</div>



	<script type="text/javascript">
		function qrcodeShow() {
			$('#div_qrcode').css("display", "block");
			$("#qr_img").attr("src",
					"${pageContext.request.contextPath}/images/qrcode.jpg");
			$('#div_qrcode').slideDown();
		}
		function goSceneIntro() {

			top.showloading(true);
			document.location.href = "scenerygetFrontList";
		}
		function goIntro(val) {
			top.showloading(true);
			document.location.href = "introfdget.action?type=" + val;
		}

		function goToHere() {
			top.showloading(true);
			document.location.href = "waytoHere";
		}

		$(document).ready(
				function() {
					top.showloading(false);
					top.showshade(false);
					handlePhotoInfo();
					document.ontouchmove = function() {
						return false;
					}
					var active = 0, as = document.getElementById('pagenavi')
							.getElementsByTagName('a');

					for ( var i = 0; i < as.length; i++) {
						(function() {
							var j = i;
							as[i].onclick = function() {
								t2.slide(j);
								return false;
							}
						})();
					}
					var t2 = new TouchSlider({
						id : 'slider',
						speed : 600,
						timeout : 6000,
						before : function(index) {
							as[active].className = '';
							active = index;
							as[active].className = 'active';
						}
					});
				});

		function handlePhotoInfo() {
			var photo1 = "<s:property value='#session.store.storeSwitchImage1'/>";
			var photo2 = "<s:property value='#session.store.storeSwitchImage2'/>";
			var photo3 = "<s:property value='#session.store.storeSwitchImage3'/>";
			var photo4 = "<s:property value='#session.store.storeSwitchImage4'/>";
			var photo5 = "<s:property value='#session.store.storeSwitchImage5'/>";
			var index = 0;
			if (photo1 != null && photo1 != '' && photo1 != 'undefined') {
				index++;
				dynamicHtml(photo1, index);
			}
			if (photo2 != null && photo2 != '' && photo2 != 'undefined') {
				index++;
				dynamicHtml(photo2, index);
			}
			if (photo3 != null && photo3 != '' && photo3 != 'undefined') {
				index++;
				dynamicHtml(photo3, index);
			}
			if (photo4 != null && photo4 != '' && photo4 != 'undefined') {
				index++;
				dynamicHtml(photo4, index);
			}
			if (photo5 != null && photo5 != '' && photo5 != 'undefined') {
				index++;
				dynamicHtml(photo5, index);
			}
		}

		function dynamicHtml(foodPic, index) {
			var ul = document.getElementById("slider");
			var li = document.createElement("li");
			var htmlstr = '<img width="100%" height="235" src="' + foodPic
					+ '" alt="" />';
			li.innerHTML = htmlstr;
			ul.appendChild(li);

			var div = document.getElementById("pagenavi");
			var a = document.createElement("a");
			a.href = "javascript:void(0);";
			a.value = index;

			if (index == 1) {
				$(a).addClass('active');
			}
			div.appendChild(a);
		}
	</script>
</body>
</html>