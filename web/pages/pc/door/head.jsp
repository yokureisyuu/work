<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>罗布人村寨</title>
<link href="${pageContext.request.contextPath}/pages/pc/door/css/all.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/pages/pc/door/css/menu.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/pages/pc/door/css/css.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/pages/pc/door/pagination/pagination.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/pages/pc/door/pagination/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/pages/pc/door/pagination/jquery.pagination.js"></script>
<SCRIPT type=text/javascript>
	var _c = _h = 0;
	$.noConflict();
	jQuery().ready(function() {
		jQuery('#play  li').click(function() {
			var i = jQuery(this).attr('alt') - 1;
			clearInterval(_h);
			_c = i;
			//play();
			change(i);
		});
		jQuery("#pic img").hover(function() {
			clearInterval(_h);
		}, function() {
			play();
		});
		play();
	});
	function play() {
		_h = setInterval("auto()", 8000);
	}

	function change(i) {
		jQuery('#play li').css('background-color', '#000000').eq(i).css(
				'background-color', '#FF3000').blur();
		jQuery("#pic img").fadeOut('slow').eq(i).fadeIn('slow');
	}

	function auto() {
		_c = _c > 3 ? 0 : _c + 1;
		change(_c);
	}
</SCRIPT>
<style type="text/css">
.img_switch {
	margin: 0 auto;
	WIDTH: 900px;
	HEIGHT: 170px;
	overflow: hidden
}

.img_switch_content {
	HEIGHT: 170px;
	position: relative;
}

.img_switch_text {
	width: 262px;
	position: absolute;
	z-index: 10;
	bottom: 5px;
	left: 10px;
	HEIGHT: 15px;
	z-index: 10000 !important
}

.number_nav {
	DISPLAY: inline;
	FLOAT: left;
}

.number_nav UL {
	font: 12px Arial, Helvetica, sans-serif;
	padding: 0px;
	MARGIN: 0px;
	LIST-STYLE-TYPE: none;
	color: #fff;
}

.number_nav UL LI {
	float: left;
	font-weight: bold;
	background: #000;
	float: left;
	margin-right: 8px;
	width: 23px;
	cursor: pointer;
	line-height: 17px;
	height: 17px;
	text-align: center;
	filter: alpha(opacity =                               75);
	-moz-opacity: 0.75;
	opacity: 0.75;
}

#pic {
	OVERFLOW: hidden
}
</style>
</head>
<body>
	<div>
		<div class="dvSchPosition">
			<div
				style="float: left; clear: left; margin-left: 20px; margin-top: 5px;">
				<div id="logo">
					<a href=""><img style="width: 75px; height: 75px;"
						src="${pageContext.request.contextPath}/pages/pc/door/images/logo.png"
						align="absmiddle" /></a>
				</div>
				<div id="top_tel">
					<img src="${pageContext.request.contextPath}/pages/pc/door/images/tel.gif">
				</div>
			</div>
			<div
				style="float: right; clear: right; margin-right: 10px; margin-top: 50px;">
				<span style="color: #000">您好，欢迎来到千秋建筑装饰网！</span>
			</div>
		</div>
		<div style="width: 900px; height: 225px; margin: 0 auto;">
			<div style="width: 900px; height: 44px; margin: 0 auto;">
				<div class="meulist">
					<div class="l"></div>
					<div class="r"></div>
					<div class="middle">
						<div class="menu">
							<div class="menusel" onMouseOver="this.className='menu_on'"
								onMouseOut="this.className='menusel'">
								<h4>
									<a href="${pageContext.request.contextPath}/indexhome.action">首&nbsp;&nbsp;页</a>
								</h4>
							</div>
							<div class="menusel" onMouseOver="this.className='menu_on'"
								onMouseOut="this.className='menusel'">
								<h4>
									<a
										href="${pageContext.request.contextPath}/introfdget.action?type=3">千秋建筑装饰</a>
								</h4>
								<div class="menu_bg">
									<div class="typeul">
										<ul>
											<li><a
												href="${pageContext.request.contextPath}/introfdget.action?type=3">景区简介</a></li>
											<li><a
												href="${pageContext.request.contextPath}/introfdget.action?type=4">公&nbsp;&nbsp;告</a></li>
											<li><a
												href="${pageContext.request.contextPath}/introfdget.action?type=2">招商引资</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="menusel" onMouseOver="this.className='menu_on'"
								onMouseOut="this.className='menusel'">
								<h4>
									<a
										href="${pageContext.request.contextPath}/fdview/sceneryspot_list.jsp">景区介绍</a>
								</h4>
								<div class=" menu_bg">
									<div class="typeul">
										<ul>
											<li><a
												href="${pageContext.request.contextPath}/fdview/sceneryspot_list.jsp">景点介绍</a></li>
											<li><a
												href="${pageContext.request.contextPath}/fdview/food_list.jsp">特产风味</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="menusel" onMouseOver="this.className='menu_on'"
								onMouseOut="this.className='menusel'">
								<h4>
									<a
										href="${pageContext.request.contextPath}/fdview/activitylist.jsp">节庆与活动</a>
								</h4>
								<div class=" menu_bg">
									<div class="typeul">
										<ul>
											<li><a
												href="${pageContext.request.contextPath}/fdview/activitylist.jsp">最新活动</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="menusel" onMouseOver="this.className='menu_on'"
								onMouseOut="this.className='menusel'">
								<h4>
									<a
										href="${pageContext.request.contextPath}/introfdget.action?type=5">旅游服务</a>
								</h4>
								<div class="menu_bg">
									<div class="typeul">
										<ul>
											<li><a
												href="${pageContext.request.contextPath}/introfdget.action?type=5">开放时间</a></li>
											<li><a
												href="${pageContext.request.contextPath}/introfdget.action?type=6">票务信息</a></li>
											<li><a
												href="${pageContext.request.contextPath}/wayfdget.action">交通指南</a></li>
											<li><a
												href="${pageContext.request.contextPath}/wisdommapfdget.action">智慧地图</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="menusel" onMouseOver="this.className='menu_on'"
								onMouseOut="this.className='menusel'">
								<h4>
									<a href="${pageContext.request.contextPath}/fdview/suggest.jsp">关于我们</a>
								</h4>
								<div class=" menu_bg">
									<div class="typeul">
										<ul>
											<li><a
												href="${pageContext.request.contextPath}/fdview/suggest.jsp">意见建议</a></li>
											<li><a
												href="${pageContext.request.contextPath}/introfdget.action?type=1">景区地址</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="menusel" onMouseOver="this.className='menu_on'"
								onMouseOut="this.className='menusel'">
								<h4 class="last">
									<a href="#weixin">微&nbsp;&nbsp;信</a>
								</h4>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div
				style="width: 900px; height: 178px; margin: 0 auto; position: relative;">
				<table border="0" cellpadding="0" cellspacing="0" width="900">
					<tbody>
						<tr>
							<td><img
								src="${pageContext.request.contextPath}/pages/pc/door/images/home_pic_top.gif"
								height="6" width="900"></td>
						</tr>
					</tbody>
				</table>
				<table
					style="background: url(${pageContext.request.contextPath}/pages/pc/door/images/home_pic_left.gif) repeat-y scroll left top transparent;"
					border="0" cellpadding="0" cellspacing="0" width="900">
					<tbody>
						<tr>
							<td align="center" valign="top">
								<div class="img_switch">
									<div class="img_switch_content" id="pic">
										<img width="890" height="170"
											src="${pageContext.request.contextPath}/pages/pc/door/images/homepic1.jpg">
										<img width="890" height="170"
											src="${pageContext.request.contextPath}/pages/pc/door/images/homepic2.jpg">
										<img width="890" height="170"
											src="${pageContext.request.contextPath}/pages/pc/door/images/homepic3.jpg">
										<img width="890" height="170"
											src="${pageContext.request.contextPath}/pages/pc/door/images/homepic4.jpg">
										<img width="890" height="170"
											src="${pageContext.request.contextPath}/pages/pc/door/images/homepic5.jpg">
										<div class="img_switch_text">
											<div class="number_nav">
												<ul id="play">
													<li alt="1" style="background: #f00;">1</li>
													<li alt="2">2</li>
													<li alt="3">3</li>
													<li alt="4">4</li>
													<li alt="5">5</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="900">
					<tbody>
						<tr>
							<td><img
								src="${pageContext.request.contextPath}/pages/pc/door/images/home_pic_down.gif"
								height="6" width="900"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>