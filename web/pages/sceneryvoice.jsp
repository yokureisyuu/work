<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title>千秋建筑装饰</title>
</head>
<body onload="myfunction();">
	<div id="tar"
		style="position: absolute; z-index: 1; top: 10px; left: 0px; bottom: 10px; right: 0px; background: FFFFFF; overflow: hidden; padding-left: 10px; padding-right: 10px; padding-top: 5px;"
		align="center">
		<div class="con">
			<div class="namerow" style="border: 0px">
				<span style="float: left">语音</span>
			</div>
			<div id="bugle" style="width: 300px; height: 100px;">
				<img id="voice_play_top" class="voice_play"
					src="${pageContext.request.contextPath}/images/audio_play.png"
					style="margin-top: 10px; width: 80px; height: 80px;" title='播放音频' />
				<img id="voice_stop_top" class="voice_stop"
					src="${pageContext.request.contextPath}/images/audio_stop.png"
					style="margin-top: 10px; width: 80px; height: 80px; display: none;"
					title='关闭音频' onclick="voiceStop()" /> <label id="mark">加载中......</label>
			</div>
			<div id="ivs_player"
				style="width: 300px; height: 30px; visibility: hidden;"></div>
			<div class="namerow" style="border: 0px">
				<span style="float: left">产品详情</span>
			</div>
			<div id="detail" align="left"
				style="display: none; padding-bottom: 10px;">
				<s:property value='#request.sceneryvoice.sceneryDetail' />
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	//全局变量
	var playType;
	$()
			.ready(
					function() {
						var tt = $('#detail').html();
						tt = HtmlDecode(tt);
						$('#detail').html(tt).show();
						var voice = "<s:property value='#request.sceneryvoice.voice'/>";
						if (voice == '' || voice == null
								|| voice == 'undefined') {
							
							$("#bugle").hide();
							$("#ivs_player").css('visibility', 'visible');
							document.getElementById("ivs_player").innerHTML = "<span>当前景区暂无语音播放</span>";
						} else {
							voiceBuild(voice);
							if (playType == "0") {
								var media = document.getElementById("media");
								media.addEventListener("canplaythrough", function() {
									voicePlay();
									$("#voice_play_top").bind("click",
											function() {
												voicePlay();
											});
								});
								media.addEventListener("ended",function(){
						 			$("#voice_play_top").show();
									$("#voice_stop_top").hide();
									$("#mark").text("");
								});	
							} else if (playType == "1") {
								var flash = document.getElementById("flash");
								if (Math.abs(flash.PercentLoaded()) == 100) {
									$("#mark").hide();
									flash.Play();
									$("#voice_play_top").hide();
									$("#voice_stop_top").show();
								}

							}
						}

					});

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
	function voiceBuild(ivoice) {
		if (fileTypeCheck(ivoice, ".mp3")) {
			if (!checkHtml5()) {
				var htmlstr = '<object id="flash" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="300px" height="30px" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">';
				htmlstr += '<param name="movie" value="${pageContext.request.contextPath}/jmp3/singlemp3player.swf?file='
						+ ivoice
						+ '&showDownload=false&autoStart=false&repeatPlay=true&songVolume=100"/>';
				htmlstr += '<param name="wmode" value="transparent" />';
				htmlstr += '<param name="allowScriptAccess" value="always" />';
				htmlstr += '<embed wmode="transparent" width="300px" height="30px" src="${pageContext.request.contextPath}/jmp3/singlemp3player.swf?file='
						+ ivoice
						+ '&showDownload=false&autoStart=true&repeatPlay=true&songVolume=100"';
				htmlstr += 'type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />';
				htmlstr += '</object>';
				$("#ivs_player").html(htmlstr);
				playType = "1";
			} else {
				var htmlstr = '<audio id="media" autoplay="false" preload="auto" controls="true" src="'+ivoice+'" style="width:300px;height:30px"></audio>';
				$("#ivs_player").html(htmlstr);
				playType = "0";
			}
		} else {
			playType = "2";
			$("#voice_play_top").hide();
			document.getElementById("ivs_player").innerHTML = "<span>当前景区暂无语音播放</span>";
		}
	}

	function voicePlay() {
		if (playType == "0") {
			$("#mark").text("正在播放...");
			document.getElementById("media").play();
		} else if (playType == "1") {
			document.getElementById("flash").Play();
		}
		$("#voice_play_top").hide();
		$("#voice_stop_top").show();
	}
	function voiceStop() {
		if (playType == "0") {
			$("#mark").text("");
			document.getElementById("media").pause();
		} else if (playType == "1") {
			var flash = document.getElementById("flash");
			flash.IsPlaying() ? flash.StopPlay() : flash.Play();
		}
		$("#voice_play_top").show();
		$("#voice_stop_top").hide();
	}

	function checkHtml5() {
		var a = document.createElement('audio');
		return !!(a.canPlayType && a.canPlayType('audio/mpeg;').replace(/no/,
				''));
	}
	function HtmlDecode(text) {
		return text.replace(/&amp;/g, '&').replace(/&quot;/g, '/"').replace(
				/&lt;/g, '<').replace(/&gt;/g, '>');
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
</html>