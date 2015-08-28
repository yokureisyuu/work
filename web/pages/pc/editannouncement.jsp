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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/fckeditor/fckeditor.js"></script>
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
	font-weight: bold;
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
			Id:<s:property value='#request.announcementDDO.id' />
			<a id="btn" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" onClick="submitForm(this)">提交修改</a>
		</div>
	</div>
	<div style="overflow-y: scroll; height: 400px; width: 100%"
		align="center">
		<div align="left" style="width: 400px">
			<div
				style="font-weight: bold; margin-top: 5px; font-size: 13px; margin-bottom: 5px;">
				<label id="title"></label>
			</div>
			<s:hidden id="announcementId" name="#request.announcementDDO.id"></s:hidden>
			<div style="display: none;">
				<!--建一个同名称的文本域使默认值同步，再隐藏文本域-->
				<textarea name="content" id="content" cols="80" rows="20"
					style="width: 400px">
					<s:property value='#request.announcementDDO.announcementDesc' />
				</textarea>
			</div>
			<div style="border: 1px solid #000000; width: 400px">
				<script type="text/javascript">
					var oFCKeditor = new FCKeditor('content');
					oFCKeditor.BasePath = 'fckeditor/';

					//oFCKeditor.ToolbarSet = 'MyToolbar';
					oFCKeditor.Height = 800;
					oFCKeditor.Width = 400;

					oFCKeditor.Create();
				</script>

			</div>
		</div>

	</div>
</body>
</html>
<script>
	function InsertHTML() {
		var oEditor = FCKeditorAPI.GetInstance('FCKeditor1');
		if (oEditor.EditMode == FCK_EDITMODE_WYSIWYG) {
			oEditor
					.InsertHtml('- This is some <a href="/Test1.html">sample<\/a> HTML -');
		} else
			alert('You must be on WYSIWYG mode!');
	}

	function submitForm(target) {
		$(target).attr('disabled', true);
		var oEditor = FCKeditorAPI.GetInstance('content');
		var detail = oEditor.GetXHTML(true);
		if (detail > 10000) {
			$('#errorinfo').html("内容过长，请简短表述").slideDown().delay(2000)
					.slideUp();
			return false;
		}
		$('#errorinfo').html("提交中...").slideDown();
		$.ajax({
			type : 'POST',
			url : "announcementsaveOrUpdate",
			data : {
				id : $("#announcementId").val(),
				announcementDesc : detail,
			},
			success : function(data) {
				$(target).attr('disabled', false);
				if (data == "success") {
					$('#errorinfo').html("修改成功").slideDown().delay(2000)
							.slideUp(function() {
								window.location.href = window.location.href;
							});

				} else {
					$('#errorinfo').html(data).show().delay(2000).hide();
				}
			},
			dataType : "text"
		});
	}

	$(document)
			.ready(
					function() {

						$("#title")
								.html(
										"<s:property value='#request.announcementDDO.announcementName' />");

						var height = $(document).height();
						$('#content').css('height', height - 40);
						conHeight = $('.west').height();
						var scrollTop = $(document).scrollTop();
						var scrollLeft = $(document).scrollLeft();

						$("#errorinfo").css({
							position : 'absolute',
							'top' : 0,
							left : 400
						});
					});
</script>