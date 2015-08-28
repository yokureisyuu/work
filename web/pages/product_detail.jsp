<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel=stylesheet type=text/css
	href="${pageContext.request.contextPath}/css/scenery_detail.css">
	<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/touchScroll.dev.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/touchslider.dev.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/ckplayer/ckplayer.js"></script>
	<meta name="viewport"
		content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
	<meta HTTP-EQUIV="expires" CONTENT="0">
	<title>产品推广</title><!--[if lt IE 9]>
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
			
		
		<s:iterator var="ent" value="#request.productList" status="statu">
		
		<div class="bar" align="center">
		<label id="title"><s:property value='#ent.video1Name' /></label>
	</div>
			
			<s:property value="#ent.video1" />
			<s:property value='#ent.video1Desc'/>
			
				<tr onclick="goDetail(<s:property value='#ent.video1Name' />)">
					<td width="90px" align="center"><img
						src="<s:property value='#ent.video1Desc'/>" /></td>
					<td align="left" valign="top">
						<div class="title">
							<s:property value="#ent.video1" />
						</div>
						<div>
							<s:property value="#ent.displayOrder" />
						</div>
					</td>
				</tr>
				</div>
			</s:iterator>
			
		
	
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
	
	
	
	
	
	<div class="roomtypebar" align="center" id="roomtypebar">
		<span style="float: left; border-right: 1px outset #663399"
			onclick="toback()" class="back">&nbsp;&nbsp;<img
			src="${pageContext.request.contextPath}/images/frontback.png"
			style="width: 25px">&nbsp;&nbsp;</span>
		<s:property value='#request.sceneryone.sceneryName' />
		<span style="float: right" style="width:25px">&nbsp;&nbsp;&nbsp;</span>
	</div>
	<div id="tar"
		style="position: absolute; z-index: 1; top: 40px; left: 0px; bottom: 0px; right: 0px; background: FFFFFF; overflow: hidden; padding-left: 10px; padding-right: 10px; padding-top: 5px;"
		align="center">
		<div class="con">
			<div class="swipe">
				<ul id="slider">
				</ul>
				<div id="pagenavi"></div>
			</div>
			
			<table>
			
			</table>
			
			<div
				style="align: center; width: 280px; margin-top: 10px; margin-bottom: 10px; overflow: hidden;">
				<div style="float: left;">
				<a href="javascript:void(0);" class="play-audio">语音</a> 
				<a href="javascript:void(0);" class="playing" style="display:none;"><span class="playing-icon"></span>播放中</a>
				</div>
				<div class="button"
					onclick="getVideo(<s:property value='#request.sceneryone.id' />)">视频</div>
			</div>
<!-- 			源产地 -->
			<div class="namerow" style="border: 0px">
				<span style="float: left">源产地</span>
				
	
			</div>
			视频
			<div id="div_video"
				style="width: 300px; height: 200px; margin-left: auto; margin-right: auto;">
			</div>
			语音
			
			<div
				style="align: center; width: 280px; margin-top: 10px; margin-bottom: 10px; overflow: hidden;">
				<div style="float:center ;">
				<a href="javascript:void(0);" class="play-audio">语音</a> 
				<a href="javascript:void(0);" class="playing" style="display:none;"><span class="playing-icon"></span>播放中</a>
				</div>
				
			</div>
			
			文字介绍
			
			<div id="detail" align="left"
				style="padding-bottom: 10px; display: none;">
				<s:property value='#request.sceneryone.sceneryDetail' />
			</div>
			
			
			<div class="namerow" style="border: 0px">
				<span style="float: left">产品</span>
			</div>
			<div class="namerow" style="border: 0px">
				<span style="float: left">乡土文化</span>
			</div>
			
			<div id="ivs_player" style="height: 80px; visibility: hidden;">
			<audio id="media_voice" preload="auto"  src="<s:property value='#request.sceneryone.voice' />" style="width:300px;height:30px"></audio>
			</div>
		</div>
	</div>
</body>
</html>
<script>
function toback(){
top.showloading(true);
history.back(-1);
}

$(document).ready(function(){
	top.showloading(false);
	top.showshade(false);	
	var tt =$('#detail').html();
	tt=HtmlDecode(tt);
	$('#detail').html(tt).show();	
	handlePhotoInfo();
	
	
	var ivoice = "<s:property value='#request.sceneryone.video'/>";
	if (fileTypeCheck(ivoice, ".mp4")) {
		videoBuild(ivoice, 'div_video', '300px',
				'200px');
	} else {
		document.getElementById("div_video").innerHTML = "<span>当前产品暂无视频播放</span>";
	}
	
});

$(".play-audio").click(function(){
    var audio = document.getElementById('media_voice');
	if (fileTypeCheck(audio.currentSrc, ".mp3")) {
	    audio.play();
	    $(".play-audio").hide();
	    $(".playing").show();
	    audio.addEventListener('ended', function () {  
	        $(".play-audio").show();
	        $(".playing").hide();     
	   }, false);		
} else {
	top.showpop(true,"当前景区暂无语音播放");
}     
});

$(".playing").click(function(){
    var playaudio = document.getElementById('media_voice');
    playaudio.pause();
    $(".play-audio").show();
    $(".playing").hide();
});

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
}
function getVideo(id){
 	top.showloading(true);
	document.location.href="scenerygetFrontVideo?id="+id;	
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

function handlePhotoInfo(){
	var photo1 = "<s:property value='#request.sceneryone.sceneryPic1'/>";
	var photo2 = "<s:property value='#request.sceneryone.sceneryPic2'/>";
	var photo3 = "<s:property value='#request.sceneryone.sceneryPic3'/>";
	var photo4 = "<s:property value='#request.sceneryone.sceneryPic4'/>";
	var photo5 = "<s:property value='#request.sceneryone.sceneryPic5'/>";
	var index = 0;
	if(photo1 != null && photo1 != '' && photo1 != 'undefined'){
		index ++;
		dynamicHtml(photo1,index);
	}
	if(photo2 != null && photo2 != '' && photo2 != 'undefined'){
		index ++;
		dynamicHtml(photo2,index);
	}
	if(photo3 != null && photo3 != '' && photo3 != 'undefined'){
		index ++;
		dynamicHtml(photo3,index);
	}
	if(photo4 != null && photo4 != '' && photo4 != 'undefined'){
		index ++;
		dynamicHtml(photo4,index);
	}
	if(photo5 != null && photo5 != '' && photo5 != 'undefined'){
		index ++;
		dynamicHtml(photo5,index);
	}
}

function dynamicHtml(foodPic,index){
	var ul = document.getElementById("slider");
	var li = document.createElement("li");
	var htmlstr = '<img width="100%" height="235" src="'+ foodPic +'" alt="" />';
	li.innerHTML = htmlstr;
	ul.appendChild(li);
	
	var div = document.getElementById("pagenavi");
	var a = document.createElement("a");
	    a.href = "javascript:void(0);";
	    a.value = index;
	if(index == 1){
		$(a).addClass('active');
	}
	div.appendChild(a);
}
function HtmlDecode(text) 
{ 
return text.replace(/&amp;/g, '&').replace(/&quot;/g, '/"').replace(/&lt;/g, '<').replace(/&gt;/g, '>'); 
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


</script>
