<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function myfunction() {
	
	 new TouchScroll({
		id : 'wrapper',
		'width' : 2,
		mouseWheel : true,
		keyPress : true,
		'opacity' : 0.5,
		color : '#000',
		minLength : 5
	});
	
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

function dispVideo(video, videoDiv) {
	var ivideo = video;
	if (fileTypeCheck(ivideo, ".mp4")) {
		videoBuild(ivideo, videoDiv, '300px', '200px');
	} 

}


$(".play-audio").click(function() {
	var audio = document.getElementById('media_voice');
	if (fileTypeCheck(audio.currentSrc, ".mp3")) {
		audio.play();
		$(".play-audio").hide();
		$(".playing").show();
		audio.addEventListener('ended', function() {
			$(".play-audio").show();
			$(".playing").hide();
		}, false);
	} 
});

$(".playing").click(function() {
	var playaudio = document.getElementById('media_voice');
	playaudio.pause();
	$(".play-audio").show();
	$(".playing").hide();
});

</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<link rel="shortcut icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/images/icon/favicon.ico">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>千秋建筑装饰</title>

<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/touchScroll.dev.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/touchslider.dev.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ckplayer/ckplayer.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

<!--[if lt IE 9]>
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
	margin-bottom: 6px height:       auto !important
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
		<label id="title"> <s:property
				value="#request.sceneryone.sceneryName" />
		</label>
	</div>
	<div id="wrapper" align="center">
		<div class="rich_media">
			<div class="rich_media_inner">
				<s:iterator var="ent" value="#request.productList" status="statu">
					<div id="line"
						style="width: 340px; height: 2px; margin-left: auto; margin-right: auto;">
					</div>
					<s:if test="%{#ent.displayOrder=='产地特点'}">
						<p>
							<img src="/yzywPath/upload/qrcode/title1.png" width="100%" alt="" />
						</p>
					</s:if>
					<s:if test="%{#ent.displayOrder=='产品特点'}">
						<p>
							<img src="/yzywPath/upload/qrcode/title2.png" width="100%" alt="" />
						</p>
					</s:if>
					<s:if test="%{#ent.displayOrder=='营养价值'}">
						<p>
							<img src="/yzywPath/upload/qrcode/title3.png" width="100%" alt="" />
						</p>
					</s:if>
					<s:if test="%{#ent.displayOrder=='食用方法'}">
						<p>
							<img src="/yzywPath/upload/qrcode/title4.png" width="100%" alt="" />
						</p>
					</s:if>
					<s:if test="%{#ent.displayOrder=='产品介绍'}">
						<p>
							<img src="/yzywPath/upload/qrcode/title5.png" width="100%" alt="" />
						</p>
					</s:if>
					<div id="line"
						style="width: 340px; height: 2px; margin-left: auto; margin-right: auto;">
					</div>
					<s:if test="%{#ent.video1Name!=''}">
						<div id="line"
							style="width: 340px; height: 2px; margin-left: auto; margin-right: auto;">
						</div>
						<div id="div_line1"
							style="width: 300px; height: 20px; margin-left: auto; margin-right: auto;">
							<s:property value='#ent.video1Name' />
						</div>
						<div id="line"
							style="width: 340px; height: 2px; margin-left: auto; margin-right: auto;">
						</div>

						<div id="div_video1"
							style="width: 340px; height: 200px; margin-left: auto; margin-right: auto;">
							
							
							<script type="text/javascript">
								var ivoice = '<s:property value="#ent.video1" />';
								if (fileTypeCheck(ivoice, ".mp4")) {
									videoBuild(ivoice, 'div_video1', '340px', '200px');
								}
							</script>
						</div>
	
						<div id="line"
							style="width: 340px; height: 2px; margin-left: auto; margin-right: auto;">
						</div>
						<div id="desc" style="width: 340px;">
							<p style="text-align: left; text-indent: 2EM;">
								<s:property value='#ent.video1Desc' />
							</p>
						</div>
					</s:if>
					<div id="line"
						style="width: 340px; height: 8px; margin-left: auto; margin-right: auto;">
					</div>
					<div id="test<s:property value="#ent.id" />"
						style="width: 340px; display: none;">

						<s:property value="#ent.productDesc" />
						<div id="line"
							style="width: 340px; height: 2px; margin-left: auto; margin-right: auto;">
						</div>
					</div>
				</s:iterator>
			</div>
		</div>
	</div>

	<script type="text/javascript">
$(document)
.ready(
		function() {
			

			var tt = $('#test2').html();
			tt = HtmlDecode(tt);
			$('#test2').html(tt).show();

			var tt = $('#test3').html();
			tt = HtmlDecode(tt);
			$('#test3').html(tt).show();

			var tt = $('#test4').html();
			tt = HtmlDecode(tt);
			$('#test4').html(tt).show();

			var tt = $('#test5').html();
			tt = HtmlDecode(tt);
			$('#test5').html(tt).show();

		});
		
	wx.config({
		debug : false,
		appId : '<s:property value="appId" />',
		timestamp : <s:property value="timestamp" />,
		nonceStr : '<s:property value="nonceStr" />',
		signature : '<s:property value="signature" />',
		jsApiList : [ 'checkJsApi', 'onMenuShareTimeline',
				'onMenuShareAppMessage', 'onMenuShareQQ' ]
	});

	wx.ready(function() {
		// 获取“分享给朋友”按钮点击状态及自定义分享内容接口
		wx.onMenuShareAppMessage({
			title : '<s:property value="#request.sceneryone.sceneryName" />', // 分享标题
			desc : '<s:property value="#request.sceneryone.sceneryDesc" />', // 分享描述
			link : '<s:property value="linkUrl" />',
			imgUrl : '<s:property value="imgUrl" />', // 分享图标
			type : 'link', // 分享类型,music、video或link，不填默认为link
			dataUrl : '', // 如果type是music或video，则要提供数据链接，默认为空
			success : function() {
				// 用户确认分享后执行的回调函数
				//alert("源之原味感谢您！");
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
			}
		});

		// 获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
		wx.onMenuShareTimeline({
			title : '<s:property value="#request.sceneryone.sceneryName" />', // 分享标题
			link : '<s:property value="linkUrl" />',
			imgUrl : '<s:property value="imgUrl" />', // 分享图标
			success : function() {
				// 用户确认分享后执行的回调函数
				//alert("源之原味感谢您！");
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
			}
		});

		// 获取“分享到QQ”按钮点击状态及自定义分享内容接口
		wx.onMenuShareQQ({
			title : '<s:property value="#request.sceneryone.sceneryName" />', // 分享标题
			desc : '<s:property value="#request.sceneryone.sceneryDesc" />', // 分享描述
			link : '<s:property value="linkUrl" />',
			imgUrl : '<s:property value="imgUrl" />', // 分享图标
			success : function() {
				// 用户确认分享后执行的回调函数
				//alert("源之原味感谢您！");
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
			}
		});
	});	
</script>
</body>
</html>