<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>千秋建筑装饰</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ckplayer/ckplayer.js"></script>
<style type="text/css">
a:link {
	text-decoration: none;
	color: #000;
}

a:visited {
	text-decoration: none;
	color: #000;
}

a:hover {
	text-decoration: none;
	color: #9d9484;
}

a:active {
	text-decoration: none;
	color: #000;
}

img {
	border: 0px;
}

.layoutContainer {
	float: left;
	font-size: 12px;
	background-color: #D4C8AF;
	margin: 0px;
	padding: 0px;
	list-style-type: none;
	text-decoration: none;
}

.mainContainer {
	width: 608px;
	height: 150px;
	margin-left: 11px;
	margin-right: 11px;
	overflow: auto;
	overflow-x: hidden;
	overflow-y: hidden;
	position: relative;
}

.bigImgContainer {
	float: left;
}

.imgContainer {
	float: left;
}

.imgItem {
	width: 140px;
	height: 130px;
	float: left;
	margin: 10px 6px 10px 6px;
	border: 0;
}

.imgItem_img {
	width: 140px;
	height: 100px;
	float: left;
}

.imgItem_img img {
	width: 136px;
	height: 100px;
	float: left;
	padding: 1px;
	border:1px solid #9d9484;
}

.imgItem_div {
	width: 138px;
	height: 30px;
	float: left;
	line-height: 30px;
	text-align: center;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}
</style>
</head>
<body>
	<div style="width: 900px; margin: 0 auto; padding: 0 auto;">
		<table align="center" border="0" cellpadding="0" cellspacing="0"
			border-collapse="collapse" width="900px"
			style="background: url(${pageContext.request.contextPath}/pages/pc/door/images/bg.gif) repeat-y">
			<tr>
				<td>
					<div class="center_kuai4">
						<div class="center_kuai4_top">
							<ul>
								<li>
									<div>新闻动态</div>
								</li>
								<li><span><a
										href="${pageContext.request.contextPath}/fdview/activitylist.jsp">更多>></a></span></li>
							</ul>
						</div>
						<div class="center_kuai4_l">
							<div id="div_video"
								style="float: left; margin-top: 2px; margin-left: 2px; width: 238px; height: 176px;">
								<div id="box_video"></div>
							</div>
						</div>
						<div class="center_kuai4_r">
							<div class="center_kuai4_r_list">
								<ul>
									<s:iterator var="ent" value="#request.home.activityList"
										status="statu">
										<li>
											<div>
												· <a
													href="${pageContext.request.contextPath}/discountfdgetDetail1.action?id=<s:property value='#ent.id'/>"
													title="<s:property value='#ent.title'/>" target="_blank"><s:property
														value='#ent.title' /></a>
											</div>
										</li>
										<li><span><s:property value='#ent.updateDate' /></span></li>
									</s:iterator>
								</ul>
							</div>

						</div>
					</div>
				</td>
				<td>
					<div class="center_kuai5">
						<div class="center_kuai5_top">游园信息</div>
						<div class="center_kuai5_xinxi" style="display: none;">
							<s:property value='#request.home.serviceInfo' />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="center_kuai6">
						<div class="center_kuai6_top">
							<ul>
								<li>
									<div>推荐景点</div>
								</li>
								<li><span><a
										href="${pageContext.request.contextPath}/fdview/sceneryspot_list.jsp">
											更多>></a></span></li>
							</ul>
						</div>
						<div
							style="float: left; margin-top: 6px; margin-left: 10px; margin-right: 10px; border: 1px dashed #9d9484; width: 630px; height: 150px; position: relative;">
							<table class="layoutContainer" border="0" cellpadding="0"
								cellspacing="0">
								<tr>
									<td>
										<div class="mainContainer">
											<div id="bigImgContainer" class="bigImgContainer">
												<div class="imgContainer">
													<s:iterator var="ent" value="#request.home.scenerySpotList"
														status="statu">
														<div class="imgItem">
															<div class="imgItem_img">
																<a
																	href="${pageContext.request.contextPath}/sceneryspotfdgetDetail.action?id=<s:property value='#ent.id'/>"
																	target="_blank"> <img
																	src="<s:property value='#ent.sceneryHead'/>" />
																</a>
															</div>
															<div class="imgItem_div">
																<a
																	href="${pageContext.request.contextPath}/sceneryspotfdgetDetail.action?id=<s:property value='#ent.id'/>"
																	target="_blank"><s:property
																		value='#ent.sceneryName' /></a>
															</div>
														</div>
													</s:iterator>
												</div>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</td>
				<td>
					<div class="centet_kuai7">
					   <div class="center_kuai7_top">智慧地图导览</div>
						<div
							style="float: left; margin-left: 10px; width: 230px; height: 165px;">
							<a href="${pageContext.request.contextPath}/wisdommapfdget.action"> <img alt="图片"
								src="${pageContext.request.contextPath}/pages/pc/door/images/wisdomMap.png"
								style="width: 230px; height: 165px;">
							</a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="center_kuai8">
						<div class="center_kuai8_top">
							<ul>
								<li>
									<div>推荐特产</div>
								</li>
								<li><span><a
										href="${pageContext.request.contextPath}/fdview/food_list.jsp">
											更多>></a></span></li>
							</ul>
						</div>
						<div
							style="float: left; margin-top: 6px; margin-left: 10px; margin-right: 10px; border: 1px dashed #9d9484; width: 630px; height: 150px; position: relative;">
							<table class="layoutContainer" border="0" cellpadding="0"
								cellspacing="0">
								<tr>
									<td>
										<div class="mainContainer">
											<div id="bigImgContainer" class="bigImgContainer">
												<div class="imgContainer">
													<s:iterator var="ent" value="#request.home.foodList"
														status="statu">
														<div class="imgItem">
															<div class="imgItem_img">
																<a
																	href="${pageContext.request.contextPath}/sceneryspotfdgetDetail.action?id=<s:property value='#ent.id'/>"
																	target="_blank"> <img
																	src="<s:property value='#ent.foodHead'/>" />
																</a>
															</div>
															<div class="imgItem_div">
																<a
																	href="${pageContext.request.contextPath}/sceneryspotfdgetDetail.action?id=<s:property value='#ent.id'/>"
																	target="_blank"><s:property value='#ent.foodName' /></a>
															</div>
														</div>
													</s:iterator>
												</div>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</td>
				<td>
					<div class="centet_kuai9">
						<div
							style="float: left;margin-top:10px; margin-left: 10px; width: 230px; height: 180px;">
							<img alt="微商城二维码"
								src="${pageContext.request.contextPath}/pages/pc/door/images/buyTicket.png"
								style="width: 230px; height: 180px;">
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript">
jQuery().ready(
			function() {
				videoBuild("<s:property value='#request.home.sceneryVideo'/>",
						'div_video', 'box_video', 238, 176);
				var tt = jQuery('.center_kuai5_xinxi').html();
				tt = HtmlDecode(tt);
				jQuery('.center_kuai5_xinxi').html(tt).show();
			});

	function videoBuild(ivideo, idiv, ibox, iwidth, iheight) {
		flag_fix = 1;
		if (fileTypeCheck(ivideo, ".flv.f4v.mp4")) {
			if (CKobject.getObjectById('ckplayer_a1')) {
				var iplayer = CKobject.getObjectById('ckplayer_a1');
				iplayer.ckplayer_newaddress('{f->' + ivideo + '}');
				return;
			}
			var flashvars = {
				f : ivideo,
				c : 0
			};
			var params = {
				bgcolor : '#FFF',
				allowFullScreen : true,
				allowScriptAccess : 'always'
			};
			CKobject.embedSWF(
					'${pageContext.request.contextPath}/ckplayer/ckplayer.swf',
					ibox, 'ckplayer_a1', iwidth, iheight, flashvars, params);
			if (fileTypeCheck(flashvars.f, ".mp4")) {
				var video = [ flashvars.f + '->video/mp4' ];
				var support = [ 'iPad', 'iPhone', 'ios', 'android+false',
						'msie10+false' ];
				CKobject.embedHTML5(idiv, 'ckplayer_a1', iwidth, iheight,
						video, flashvars, support);
			}
		} else {
			jQuery("#box_video").html('抱歉，暂无视频播放');
		}
	}

	function HtmlDecode(text) {
		return text.replace(/&amp;/g, '&').replace(/&quot;/g, '/"').replace(
				/&lt;/g, '<').replace(/&gt;/g, '>');
	}

	function fileTypeCheck(filepath, suppotFile) {
		if (filepath != "") {
			if (filepath.lastIndexOf(".") == -1) {
				return false;
			} else {
				var fileType = (filepath.substring(filepath.lastIndexOf("."),
						filepath.length)).toLowerCase();
				if (suppotFile.indexOf(fileType) == -1) {
					return false;
				}
				return true;
			}
		} else {
			return false;
		}
	}
</script>
</html>