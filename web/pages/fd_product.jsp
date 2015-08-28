<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel=stylesheet type=text/css
	href="${pageContext.request.contextPath}/css/aboutus.css?a=112">
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/touchScroll.dev.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/ckplayer/ckplayer.js"></script>
	<meta name="viewport"
		content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
			<meta HTTP-EQUIV="expires" CONTENT="0">
				<title></title> <!--[if lt IE 9]>
<style>
.rich_media{width:740px;margin-left:auto;margin-right:auto}.rich_media_inner{padding:20px;background-color:#fff}.rich_media_meta{max-width:none}.rich_media_content{min-height:350px}.rich_media_title{padding-bottom:10px;margin-bottom:5px;border-bottom:1px solid #e7e7eb}.discuss_container{width:740px;margin-left:auto;margin-right:auto}.discuss_container.editing .frm_textarea_box{margin:0}.frm_textarea_box{position:relative}.frm_textarea_box:before{content:" ";position:absolute;left:0;top:0;width:1px;height:200%;border-left:1px solid #e7e6e4;-webkit-transform-origin:0 0;transform-origin:0 0;-webkit-transform:scale(0.5);transform:scale(0.5)}.frm_textarea_box:after{content:" ";position:absolute;left:0;top:0;width:1px;height:200%;border-left:1px solid #e7e6e4;-webkit-transform-origin:0 0;transform-origin:0 0;-webkit-transform:scale(0.5);transform:scale(0.5);left:auto;right:-2px}.rich_media_meta.nickname{max-width:none}.rich_tips.with_line .tips{background-color:#fff}.rich_media_inner{border:1px solid #d9dadc;border-top-width:0}body{background-color:#e7e8eb;font-family:"Helvetica Neue",Helvetica,"Hiragino Sans GB","Microsoft YaHei","?￠èí??oú",Arial,sans-serif}.rich_media{position:relative}.rich_media_inner{padding-bottom:100px}.rich_media_inner{position:relative}.qr_code_pc_outer{display:block!important;position:fixed;left:0;right:0;top:20px;color:#717375;text-align:center}.qr_code_pc_inner{position:relative;width:740px;margin-left:auto;margin-right:auto}.qr_code_pc{position:absolute;right:-145px;top:0;padding:16px;border:1px solid #d9dadc;background-color:#fff}.qr_code_pc p{font-size:14px;line-height:20px}.qr_code_pc_img{width:102px;height:102px}
</style>
<![endif]-->

				<style>
html {
	-ms-text-size-adjust: 100%;
	-webkit-text-size-adjust: 100%;
	line-height: 1.6
}

body {
	-webkit-touch-callout: none;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	background-color: #f3f3f3;
	line-height: inherit
}

h1,h2,h3,h4,h5,h6 {
	font-weight: 400;
	font-size: 16px
}

* {
	margin: 0;
	padding: 0;
	font-style: normal
}

a {
	color: #607fa6;
	text-decoration: none
}

.rich_media_inner {
	font-size: 15px;
	word-wrap: break-word;
	-webkit-hyphens: auto;
	-ms-hyphens: auto;
	hyphens: auto
}

.rich_media_area_primary {
	position: relative;
	padding: 10px 15px 15px;
	background-color: #fff
}

.rich_media_area_primary:before {
	content: " ";
	position: absolute;
	left: 0;
	top: 0;
	width: 200%;
	height: 1px;
	border-top: 1px solid #e5e5e5;
	-webkit-transform-origin: 0 0;
	transform-origin: 0 0;
	-webkit-transform: scale(0.5);
	transform: scale(0.5);
	top: auto;
	bottom: -2px
}

.rich_media_area_extra {
	padding: 0 15px 0
}

.rich_media_title {
	margin-bottom: 12px;
	line-height: 1.4;
	font-weight: 400;
	font-size: 24px
}

.rich_media_meta_list {
	overflow: hidden;
	margin-bottom: 18px;
	line-height: 20px
}

.rich_media_meta {
	float: left;
	margin-right: 8px;
	margin-bottom: 10px
}

.rich_media_meta_tag {
	position: relative;
	padding: 0 .5em 0 1.5em;
	border-top-left-radius: 36% 100%;
	-moz-border-radius-topleft: 36% 100%;
	-webkit-border-top-left-radius: 36% 100%;
	border-top-right-radius: 36% 100%;
	-moz-border-radius-topright: 36% 100%;
	-webkit-border-top-right-radius: 36% 100%;
	border-bottom-left-radius: 36% 100%;
	-moz-border-radius-bottomleft: 36% 100%;
	-webkit-border-bottom-left-radius: 36% 100%;
	border-bottom-right-radius: 36% 100%;
	-moz-border-radius-bottomright: 36% 100%;
	-webkit-border-bottom-right-radius: 36% 100%;
	color: #fff;
	background-color: #b9b9b9
}

.icon_meta_copyright {
	width: 1em;
	position: absolute;
	top: 50%;
	margin-top: -8px;
	left: 2px
}

.rich_media_meta_text {
	color: #8c8c8c
}

.rich_media_meta_nickname {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	max-width: 9em
}

.rich_media_content {
	overflow: hidden;
	color: #3e3e3e
}

.rich_media_content * {
	max-width: 100% !important;
	box-sizing: border-box !important;
	-webkit-box-sizing: border-box !important;
	word-wrap: break-word !important
}

.rich_media_content p {
	clear: both;
	min-height: 1em;
	white-space: pre-wrap
}

.rich_media_content .list-paddingleft-2 {
	padding-left: 30px
}

.rich_media_content blockquote {
	margin: 0;
	padding-left: 10px;
	border-left: 3px solid #dbdbdb
}

img {
	width: 100%;
	margin-bottom: 6px height:   auto !important
}

body {
	margin: 0px;
	padding: 0px;
	background-color: #dfdfdf;
	overflow: hidden;
	font-family: "Helvetica Neue", Helvetica, Arial;
	font-size: 15px;
}

.bar {
	height: 40px;
	border-bottom: #333333 solid 2px;
	background: #2c9cc8;
	color: #FFF;
	line-height: 40px;
	font-weight: bold;
}

.block {
	width: 100%;
	min-height: 100px;
	height: 100%;
	padding: 20px;
}

#wrapper {
	position: absolute;
	top: 40px;
	bottom: 0px;
	left: 0;
	background: #d9d9d9;
	right: 0px;
}

.orderdetail {
	-webkit-box-shadow: 0 0 10px rgba(0, 0, 0, 0.6);
	-moz-box-shadow: 0 0 10px rgba(0, 0, 0, 0.6);
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.6);
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	-ms-border-radius: 6px;
	-o-border-radius: 6px;
	border-radius: 6px;
	padding: 0px 20px;
	background: #ffffff;
	margin-top: 10px;
	margin-bottom: 10px;
}
</style>
</head>

<body onload="myfunction()">

	<div class="bar" align="center">
		<label id="title"></label>
	</div>
	
	
	<div id="wrapper" align="center">
	
		<div class="rich_media">
			<div class="rich_media_inner">
			
		
			<div id="div_video1"
					style="width: 300px; height: 8px; margin-left: auto; margin-right: auto;">
				</div>
			
				<div id="div_video"
					style="width: 340px; height: 200px; margin-left: auto; margin-right: auto;">
				</div>
				
				<div id="div_video1"
					style="width: 300px; height: 8px; margin-left: auto; margin-right: auto;">
				</div>
				<div id="test" style="width: 340px;display: none;">
					<s:hidden name="#request.fdintro.type" id="type" />
					<s:property value="#request.fdintro.detail" />
				</div>
			</div>
		</div>
	</div>

</body>
</html>

<script>
	function toback() {

		history.back(-1);
	}

	$(document).ready(function() {

		function replaceAll(str) {
			if (str != null)
				str = str.replace(/word/g, "Excel");
			return str;
		}
		var type = $('#type').val();
		if (type == 1) {
			$("#title").html("公司简介");
		} else if (type == 2) {
			$("#title").html("企业文化");
		} else if (type == 3) {
			$("#title").html("大事记");
		} else {
			$("#title").html("巴什拜羔羊");
			
			var ivoice = "/yzywPath/upload/video/1423719684351.mp4";
			if (fileTypeCheck(ivoice, ".mp4")) {
				videoBuild(ivoice, 'div_video', '340px', '200px');
			} else {
				document.getElementById("div_video").innerHTML = "<span>当前产品暂无视频播放</span>";
			}
			
		}
		var tt = $('#test').html();
		tt = HtmlDecode(tt);
		$('#test').html(tt).show();
		$('.orderdetail').css('width', $(window).width() - 60);
		top.showloading(false);
		top.showshade(false);
		
		

	});
	function myfunction() {
		document.ontouchmove = function() {
			return false;
		}
		var t1 = new TouchScroll({
			id : 'wrapper',
			'width' : 2,
			mouseWheel : true,
			keyPress : true,
			'opacity' : 0.5,
			color : '#000',
			minLength : 5
		});
		document.title = "千秋建筑装饰";
	}
	function HtmlDecode(text) {
		return text.replace(/&amp;/g, '&').replace(/&quot;/g, '/"').replace(
				/&lt;/g, '<').replace(/&gt;/g, '>');
	}



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

