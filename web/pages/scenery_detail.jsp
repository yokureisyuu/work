<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucent" />
<title>绍兴微画剧</title>
<link rel="stylesheet"
	href="http://wxcdn.shaoxingtour.cn/Public/css/painting/parallax.css?v=1">
<link rel="stylesheet"
	href="template/images/common.css">
<link rel="stylesheet"
	href="http://wxcdn.shaoxingtour.cn/Public/css/painting/animation.css?v=1">
<!--移动端版本兼容 -->
<script type="text/javascript">
	if (window.screen.height <= 500) {
		document
				.write('<meta name="viewport" content="initial-scale=0.5,maximum-scale=0.5,minimum-scale=0.5" />');
	} else {
		var phoneWidth = parseInt(window.screen.width);
		var phoneScale = phoneWidth / 640;
		var ua = navigator.userAgent;
		if (/Android (\d+\.\d+)/.test(ua)) {
			var version = parseFloat(RegExp.$1);
			// andriod 2.3
			if (version > 2.3) {
				document
						.write('<meta name="viewport" content="width=640, minimum-scale = ' + phoneScale + ', maximum-scale = ' + phoneScale + ', target-densitydpi=device-dpi">');
				// andriod 2.3以上
			} else {
				document
						.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
			}
			// 其他系统
		} else {
			document
					.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
		}
	}
</script>
<!--移动端版本兼容 end -->
</head>
<body>
	<div class="wrapper">
		<div class="pages">
			<!-- 第一屏-->
			<section class="page page1">
				<div class="page-con">
					
					<div class="a-fadeinR pf_1_2" style="position: absolute; top: 40%;">
						<img
							src="<s:property value='#request.sceneryone.sceneryPic1'/>" />
					</div>
	
					<div class="controlArrow next"></div>
				</div>
			</section>
			<!-- 第二屏 -->
			<section class="page slide"
				style="background-image: url(<s:property value='#request.sceneryone.sceneryPic2'/>">
				<div class="controlArrow prev"></div>
				<div class="controlArrow next"></div>
			</section>
			
		



			<!-- 第三屏 -->
			<section class="page page3">
				<div class="page-con">
					<div class="a-fadeinT pf_3_1"
						style="position: absolute; top: 40px; left: 58px;">
						<img
							src="<s:property value='#request.sceneryone.sceneryPic3'/>" />
					</div>
					
				
					<div class="controlArrow prev"></div>
				</div>
			</section>
		</div>
	</div>
	
	<!--下面是竖屏提示,性能原因丢最后-->
	
	<div id="orientLayer" class="mod-orient-layer">
		<div class="mod-orient-layer__content">
			<i class="icon mod-orient-layer__icon-orient"></i>
			<div class="mod-orient-layer__desc">为了更好的体验，请使用竖屏浏览</div>
		</div>
	</div>
	<script
		src="http://wxcdn.shaoxingtour.cn/Public/js/painting/zepto.min.js"></script>
	<script
		src="http://wxcdn.shaoxingtour.cn/Public/js/painting/igrow.swiper.js"></script>
	<script
		src="http://wxcdn.shaoxingtour.cn/Public/js/painting/parallax.js"></script>

	<script>
		
		$('.pages').parallax({
			direction : 'horizontal', // horizontal (水平翻页)
			swipeAnim : 'cover', // cover (切换效果)
			drag : true, // 是否允许拖拽 (若 false 则只有在 touchend 之后才会翻页)
			loading : false, // 有无加载页
			indicator : false, // 有无指示点
			arrow : false, // 有无指示箭头
			/*
			 * 翻页后立即执行
			 * {int} 		index: 第几页
			 * {DOMElement} element: 当前页节点
			 * {String}		directation: forward(前翻)、backward(后翻)、stay(保持原页)
			 */
			onchange : function(index, element, direction) {
				// code here...
				// console.log(index, element, direction);
			},
			/*
			 * 横竖屏检测
			 * {String}		orientation: landscape、protrait
			 */
			orientationchange : function(orientation) {
				// console.log(orientation);
			}
		});
	</script>
	<script>
		//为了兼容屏幕小于500的
		if (window.screen.height <= 500) {
			$('.pf_1_5').css("bottom", "75px");
			$('.pf_3_1').css("top", "20px");
			$('.pf_3_1').css("left", "0px");
			$('.pf_3_1 img').css("width", "70%");
			$('.pf_3_2').css("top", "230px");
			$('.pf_3_2 img').css("width", "60%");
			$('.pf_3_4').css("top", "460px");
			$('.pf_1_6').css("top", "380px");
			$('.pf_3_5').css("bottom", "50px");
		}

		$("#share_weixin").on("click", function() {
			$('#box').show();
			$('#screen').show();
		});
		$('#box').on("click", function() {
			$('#screen').hide();
			$('#box').hide();
		});
		$('#screen').on("click", function() {
			$('#screen').hide();
			$('#box').hide();
		});
		$("#activity_rule").on("click", function() {

			$('.pop_box').show();
			var boxConfirmH = $('.box_confirm').height();
			$(".box_confirm").css("height", boxConfirmH + "px");
			$(".box_confirm").css("margin-top", '-' + boxConfirmH / 2 + "px")
		})
		$(".box_close").on("click", function() {
			$('#screen').hide();
			$('.pop_box').hide();
		});

		var orientLayer = document.getElementById("orientLayer");
		//判断横屏竖屏
		function checkDirect() {
			if (document.documentElement.clientHeight >= document.documentElement.clientWidth) {
				return "portrait";
			} else {
				return "landscape";
			}
		}
		//显示屏幕方向提示浮层
		function orientNotice() {
			var orient = checkDirect();
			if (orient == "portrait") {
				$("#orientLayer").fadeOut('slow');
			} else {
				orientLayer.style.display = "block";
			}
		}
		function init() {
			
			top.showloading(false);
			top.showshade(false);	

			
			orientNotice();
			window.addEventListener(
					"onorientationchange" in window ? "orientationchange"
							: "resize", function() {
						setTimeout(orientNotice, 100);
					})
		}
		init();
		
	
	</script>
</body>
</html>