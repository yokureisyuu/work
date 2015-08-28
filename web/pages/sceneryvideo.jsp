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
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/touchScroll.dev.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/touchslider.dev.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ckplayer/ckplayer.js"></script>
<%-- <script src="http://192.168.1.107:8081/target/target-script-min.js#anonymous" type="text/javascript"> </script> --%>
</head>
<body onload="myfunction()">
	<div id="tar"
		style="position: absolute; z-index: 1; top: 10px; left: 0px; bottom: 10px; right: 0px; background: FFFFFF; overflow: hidden; padding-left: 10px; padding-right: 10px; padding-top: 5px;"
		align="center">
		<div class="con">
			<div class="namerow" style="border: 0px">
				<span style="float: left">视频</span>
			</div>
			<div id="div_video"
				style="width: 300px; height: 200px; margin-left: auto; margin-right: auto;">
			</div>
			<div class="namerow" style="border: 0px">
				<span style="float: left">产品详情</span>
			</div>
			<div id="detail" align="left" style="display:none;padding-bottom: 10px;">
				<s:property value='#request.sceneryvideo.sceneryDetail' />
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$()
				.ready(
						function() {
							var tt = $('#detail').html();
							tt = HtmlDecode(tt);
							$('#detail').html(tt).show();
							var ivoice = "<s:property value='#request.sceneryvideo.video'/>";
							if (fileTypeCheck(ivoice, ".mp4")) {
								videoBuild(ivoice, 'div_video', '300px',
										'200px');
							} else {
								document.getElementById("div_video").innerHTML = "<span>当前产品暂无视频播放</span>";
							}
						});

		function videoBuild(ivideo, idiv, iwidth, iheight) {
			var flashvars = {
				f : ivideo,
				c : 0,
				b : 1,
				x : ''
			};
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
		function HtmlDecode(text) {
			return text.replace(/&amp;/g, '&').replace(/&quot;/g, '/"')
					.replace(/&lt;/g, '<').replace(/&gt;/g, '>');
		}
		function myfunction() {
			document.ontouchmove = function() {
				return false;
			}
			var t1 = new TouchScroll({
				id : 'tar',
				'width' : 2,
				mouseWheel : true,
				keyPress : true,
				'opacity' : 0.5,
				color : '#000',
				minLength : 5
			});
		}
	</script>
</body>
</html>
