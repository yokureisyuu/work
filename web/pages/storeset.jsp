<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script class="jsbin" src="../js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="../easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../fckeditor/fckeditor.js"></script>
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
		<div style="float: right">
			<a id="btn" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" onclick="submitForm(this)">提交修改</a>
		</div>
	</div>
	<div style="overflow-y: scroll; height: 300px" id="content">
		<table style="width: 100%">
			<tr>
				<td align="center" valign="top">
					<form>
						<table class="base" align="center">

							<tr>
								<td>名称:</td>
								<td><input type="text" maxlength="20"
									value="<s:property value='#session.store.storeName'/>"
									name="name"></td>
							</tr>
							<tr>
								<td>欢迎语:</td>
								<td><input type="text" maxlength="250"
									value="<s:property value='#session.store.storeDiscountInfo'/>"
									name="welcome"></td>
							</tr>
						</table>
					</form>
					<hr>首页图片:<span
					style='color: red; border: 1px solid red; padding-left: 10px; padding-right: 10px; font-size: 12px; background-color: #FFFFFF; margin-left: 20px; padding-top: 5px; padding-bottom: 5px; position: absolute; display: none'
					id='errorinfo'></span> </br> </br>

					<div style="margin-left: 10px; margin-right: 10px;">
						<img src="#" style="height: 130px; width: 250px;" id="img1" /></br>
						<form method="POST" enctype="multipart/form-data">
							<input type="file" name="image" id="photo1" /> <input
								type="hidden" name="picindex" value="1"> <input
								type="button" value="上传" onClick="upload(this,1)" />
						</form>


						<img src="#" style="height: 130px; width: 250px;" id="img2" /></br>
						<form method="POST" enctype="multipart/form-data">
							<input type="file" name="image" id="photo2" /> <input
								type="hidden" name="picindex" value="2"> <input
								type="button" value="上传" onClick="upload(this,2)" />
						</form>

						<img src="#" style="height: 130px; width: 250px;" id="img3" /></br>
						<form method="POST" enctype="multipart/form-data">
							<input type="file" name="image" id="photo3" /> <input
								type="hidden" name="picindex" value="3"> <input
								type="button" value="上传" onClick="upload(this,3)" />
						</form>

						<img src="#" style="height: 130px; width: 250px;" id="img4" /></br>
						<form method="POST" enctype="multipart/form-data">
							<input type="file" name="image" id="photo4" /> <input
								type="hidden" name="picindex" value="4"> <input
								type="button" value="上传" onClick="upload(this,4)" />
						</form>

						<img src="#" style="height: 130px; width: 250px;" id="img5" /></br>
						<form method="POST" enctype="multipart/form-data">
							<input type="file" name="image" id="photo5" /> <input
								type="hidden" name="picindex" value="5"> <input
								type="button" value="上传" onClick="upload(this,5)" />
						</form>

					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
<script>
	function submitForm(target) {

		$(target).attr('disabled', true);
		$('#errorinfo').html("提交中...").slideDown();
		$.post("storesubmit", {
			name : $('input[name=name]').val(),
			welcome : $('input[name=welcome]').val()
		}, function(data) {
			$(target).attr('disabled', false);
			if (data == "success") {
				$('#errorinfo').html("修改成功").slideDown().delay(2000).slideUp(
						function() {
							window.location.href = window.location.href;
						});
			} else {
				$('#errorinfo').html(data).show().delay(2000).hide();
			}
		});

	}
	function upload(ele, index) {
		var ff;
		if (index == 1) {
			if (check(1))
				ff = $($("form")[1]);
			else
				return false;
		} else if (index == 2) {
			if (check(2))
				ff = $($("form")[2]);
			else
				return false;
		} else if (index == 3) {
			if (check(3))
				ff = $($("form")[3]);
			else
				return false;
		} else if (index == 4) {
			if (check(4))
				ff = $($("form")[4]);
			else
				return false;
		} else {
			if (check(5))
				ff = $($("form")[5]);
			else
				return false;
		}
		$(ele).attr('disabled', true);

		ff.form("submit", {
			url : "storeuploadPic",
			onSubmit : function() {
				return true;
			},
			success : function(data) {
				$(ele).attr('disabled', false);
				if (data == "success") {
					$('#errorinfo').html("图片上传成功").slideDown().delay(2000)
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
										"<s:property value='#session.store.storeSwitchImage1'/>");
						$('#img2')
								.attr('src',
										"<s:property value='#session.store.storeSwitchImage2'/>");
						$('#img3')
								.attr('src',
										"<s:property value='#session.store.storeSwitchImage3'/>");
						$('#img4')
								.attr('src',
										"<s:property value='#session.store.storeSwitchImage4'/>");
						$('#img5')
								.attr('src',
										"<s:property value='#session.store.storeSwitchImage5'/>");
					});

	
</script>