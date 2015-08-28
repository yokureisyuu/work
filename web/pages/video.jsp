<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title>千秋建筑装饰</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=0.5,minimum-scale=0.25,maximum-scale=2.0,user-scalable=yes" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="千秋建筑装饰" />
<meta http-equiv="description" content="千秋建筑装饰" />
<link rel=stylesheet type=text/css
	href="${pageContext.request.contextPath}/css/scenery_detail.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/touchScroll.dev.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ckplayer/ckplayer.js"></script>
</head>
<body>
	<div class="roomtypebar" align="center" id="roomtypebar">
		<span style="float: left; border-right: 1px outset #663399"
			onclick="toback()" class="back">&nbsp;&nbsp;<img
			src="${pageContext.request.contextPath}/images/frontback.png"
			style="width: 25px">&nbsp;&nbsp;</span>
		视频
		<span style="float: right" style="width:25px">&nbsp;&nbsp;&nbsp;</span>
	</div>
		<div id="tar"
		style="position: absolute; z-index: 1; top: 40px; left: 0px; bottom: 40px; right: 0px; background: FFFFFF; overflow: hidden; padding-left: 10px; padding-right: 10px; padding-top: 5px;"
		align="center">
		<div class="con">
			<div class="namerow" style="border: 0px">
				<span style="float: left">视频</span>
			</div>
			<div id="div_video"
				style="width: 300px; max-height: 300px; margin-left: auto; margin-bottom: 10px; margin-right: auto;">
			</div>
			</br>
		</div>
	</div>
	<script type="text/javascript">
		$().ready(
				function() {
					top.showloading(false);
					top.showshade(false);
					var ivoice = "<s:property value='#request.video'/>";
					if (fileTypeCheck(ivoice, ".mp4")) {
						videoBuild(ivoice, 'div_video',
								'300px', '200px');
					} else {
						top.showpop(true, "当前景区暂无视频播放");
					}
				});


		function videoBuild(ivideo, idiv, iwidth, iheight) {
			var flashvars = {
				f : ivideo,
				c : 0,
				b : 1,
				x : ''
			};//b:1去掉后可js
			var params = {
				bgcolor : '#FFF',
				allowFullScreen : true,
				allowScriptAccess : 'always'
			};
			CKobject.embedSWF(
					'${pageContext.request.contextPath}/ckplayer/ckplayer.swf',
					idiv, 'ckplayer_a1', iwidth, iheight, flashvars, params);
			var video = [ flashvars.f + '->video/mp4' ];
			var support = [ 'iPad', 'iPhone', 'ios', 'android+false',
					'msie10+false' ];
			CKobject.embedHTML5(idiv, 'ckplayer_a1', iwidth, iheight, video,
					flashvars, support);
		}

		function fileTypeCheck(filepath, suppotFile) {
			//var suppotFile=".mp3.amr";
			if (filepath != "") {
				if (filepath.lastIndexOf(".") == -1) {
					return false;
				} else {
					var fileType = (filepath.substring(filepath
							.lastIndexOf("."), filepath.length)).toLowerCase();
					if (suppotFile.indexOf(fileType) == -1) {
						return false;
					}
					return true;
				}
			} else {
				return false;
			}
		}

		function toback() {
			top.showloading(true);
			history.back(-1);
		}
		function toggletype() {
			$('.selectMenu').toggle();
		}
	</script>
</body>
</html>
