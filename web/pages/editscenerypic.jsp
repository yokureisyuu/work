<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script class="jsbin"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<!--载入fckeditor类-->

<title>用JavaScript调用FCKeditor</title>
<style>
body {
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	font-family: "Helvetica Neue", Helvetica, Arial;
	font-size: 12px;
}

.base {
	padding: 0px;
	margin: 0px;
}

.base tr {
	height: 40px;
	font-size: 12px;
}

.base tr td>input {
	border: 0px;
	border-bottom: 1px solid #000000;
	width: 200px;
	padding-left: 10px;
}

.base tr td>div {
	border: 0px;
	border-bottom: 1px solid #000000;
	width: 200px;
	padding-left: 10px;
}
</style>
</head>
<body>
	<div id="toolbar" class="datagrid-toolbar"
		style="height: 30px; padding-top: 6px;">
		<div
			style='color: red; border: 1px solid red; padding: 5px; font-size: 12px; positon: absolute; z-index: 20; background-color: #FFFFFF; display: none'
			id='errorinfo'></div>
		<div
			style="float: left; padding-left: 10px; padding-top: 5px; font-weight: bold">
			当前产品:&nbsp;&nbsp;<span style="color: #FF0000"><s:property
					value="#request.scenerypic.sceneryName" /></span>
		</div>
	</div>
	<div style="overflow-y: scroll; height: 300px" id="content">
		<table style="width: 100%">
			<tr>
				<td align="center"
					style="width: 400px; font-size: 12px; font-weight: bold"
					valign="top" align="left"><input type="hidden" id="id"
					value="<s:property value='#request.scenerypic.id'/>">
					图片展示: </br> </br>
					<div style="margin-left: 10px; margin-right: 10px;">
						<img src="#" style="height: 130px; width: 250px;" id="img1" /></br>
						<form method="POST" enctype="multipart/form-data">
							<input type="file" name="image" id="photo1" /> <input
								type="hidden" name="index" value="1"> <input
								type="hidden" name="id"
								value="<s:property value='#request.scenerypic.id'/>">
							<input type="button" value="上传" onClick="upload(this,1)" />
						</form>
						<img src="#" style="height: 130px; width: 250px;" id="img2" /></br>
						<form method="POST" enctype="multipart/form-data">
							<input type="file" name="image" id="photo2" /> <input
								type="hidden" name="index" value="2"> <input
								type="hidden" name="id"
								value="<s:property value='#request.scenerypic.id'/>">
							<input type="button" value="上传" onClick="upload(this,2)" />
						</form>
						<img src="#" style="height: 130px; width: 250px;" id="img3" /></br>
						<form method="POST" enctype="multipart/form-data">
							<input type="file" name="image" id="photo3" /> <input
								type="hidden" name="index" value="3"> <input
								type="hidden" name="id"
								value="<s:property value='#request.scenerypic.id'/>">
							<input type="button" value="上传" onClick="upload(this,3)" />
						</form>
						<img src="#" style="height: 130px; width: 250px;" id="img4" /></br>
						<form method="POST" enctype="multipart/form-data">
							<input type="file" name="image" id="photo4" /> <input
								type="hidden" name="index" value="4"> <input
								type="hidden" name="id"
								value="<s:property value='#request.scenerypic.id'/>">
							<input type="button" value="上传" onClick="upload(this,4)" />
						</form>
						<img src="#" style="height: 130px; width: 250px;" id="img5" /></br>
						<form method="POST" enctype="multipart/form-data">
							<input type="file" name="image" id="photo5" /> <input
								type="hidden" name="index" value="5"> <input
								type="hidden" name="id"
								value="<s:property value='#request.scenerypic.id'/>">
							<input type="button" value="上传" onClick="upload(this,5)" />
						</form>
					</div></td>
			</tr>
		</table>
	</div>
</body>
</html>
<script>
	function upload(ele, index) {
		var ff;
		if (index == 1) {
			if (check(1))
				ff = $($("form")[0]);
			else
				return false;
		} else if (index == 2) {
			if (check(2))
				ff = $($("form")[1]);
			else
				return false;
		} else if (index == 3) {
			if (check(3))
				ff = $($("form")[2]);
			else
				return false;
		} else if (index == 4) {
			if (check(4))
				ff = $($("form")[3]);
			else
				return false;
		} else {
			if (check(5))
				ff = $($("form")[4]);
			else
				return false;
		}

		$(ele).attr('disabled', true);
		$('#errorinfo').html("提交中...").slideDown();
		ff.form("submit", {
			url : "sceneryaddPic",
			onSubmit : function() {
				return true;
			},
			success : function(data) {
				$(ele).attr('disabled', false);
				var img = ff.prev().prev();
				var src = img.attr('src');

				if (data == "success") {
					$('#errorinfo').html("上传成功").slideDown().delay(2000)
							.slideUp(function() {
								window.location.href = window.location.href;
							});
				} else {
					$('#errorinfo').html(data).show().delay(2000).hide();
				}
			}
		});
	}

	function check(index) {
		var photo = $("#photo" + index).val();
		if (photo == "") {
			alert("请选择照片");
			return false;
		}
		var fileType = photo.substring(photo.lastIndexOf(".") + 1);
		if (fileType != "gif" && fileType != "jpg" && fileType != "jpeg"
				&& fileType != "png" && fileType != "GIF" && fileType != "JPG"
				&& fileType != "PNG") {
			alert("上传图片格式错误");
			return false;
		}
		return true;
	}
	$(document)
			.ready(
					function() {
						var height = $(document).height();

						$('#content').css('height', height - 40);
						conHeight = $('.west').height();
						var toph = ($(window).height() - $("#errorinfo")
								.height()) / 5;
						var lefth = ($(window).width() - $("#errorinfo")
								.width()) / 2;
						var scrollTop = $(document).scrollTop();
						var scrollLeft = $(document).scrollLeft();
						$("#errorinfo").css({
							position : 'absolute',
							'top' : 5 + scrollTop,
							left : lefth + scrollLeft
						});
						$('#img1')
								.attr('src',
										"<s:property value='#request.scenerypic.sceneryPic1'/>");
						$('#img2')
								.attr('src',
										"<s:property value='#request.scenerypic.sceneryPic2'/>");
						$('#img3')
								.attr('src',
										"<s:property value='#request.scenerypic.sceneryPic3'/>");
						$('#img4')
								.attr('src',
										"<s:property value='#request.scenerypic.sceneryPic4'/>");
						$('#img5')
								.attr('src',
										"<s:property value='#request.scenerypic.sceneryPic5'/>");
					});

</script>