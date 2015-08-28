<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script class="jsbin" src="js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/fckeditor/fckeditor.js"></script>
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

img {
	width: 100%;
	margin-bottom: 6px
	height: auto !important
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
	<div style="overflow-y: scroll; height: 400px">
		<table style="width: 100%">
			<tr>
				<td align="center" style="width: 460px" valign="top">
					<form>
						<input type="hidden" value="<s:property value='#request.way.id'/>"
							name="id">
						<table class="base" align="center">
							<tr>
								<td>地址:</td>
								<td><input type="text" maxlength="30"
									value="<s:property value='#request.way.address'/>"
									name="address">
									<a href="#" 
									class="easyui-linkbutton" data-options="iconCls:'icon-search'" 
 									onclick="parseAddress(this);">地址解析</a> 
									</td>
							</tr>
							<tr>
								<td>经度:</td>
								<td><input type="text"
									value="<s:property value='#request.way.longitude'/>"
									name="longitude"></td>
							</tr>
							<tr>
								<td>纬度:</td>
								<td><input type="text"
									value="<s:property value='#request.way.latitude'/>"
									name="latitude"></td>
							</tr>
							<tr class="remark">
								<td colspan="2" style="font-weight: 100; color: #FF0000">(解析失败，请在http://api.map.baidu.com/lbsapi/creatmap/index.html查询得到)</td>
							</tr>
						</table>

					</form>
				</td>
				<td align="left">
					<div
						style="font-weight: bold; margin-top: 5px; font-size: 13px; margin-bottom: 5px;">交通地址路线规划</div>
					<div style="display: none">
						<!--建一个同名称的文本域使默认值同步，再隐藏文本域-->
						<textarea name="content" id="content" cols="80" rows="20"
							style="width: 400px">
							<s:property value='#request.way.detail' />
						</textarea>
					</div>
					<div style="border: 1px solid #000000; width: 350px">
						<script type="text/javascript">

var oFCKeditor = new FCKeditor( 'content' ) ;
oFCKeditor.BasePath='fckeditor/';

//oFCKeditor.ToolbarSet='MyToolbar' ;
    oFCKeditor.Height = 800 ; 
oFCKeditor.Width =350 ;
  
   oFCKeditor.Create();
</script>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
<script>
function InsertHTML()
{
	var oEditor = FCKeditorAPI.GetInstance('FCKeditor1') ;
	if ( oEditor.EditMode == FCK_EDITMODE_WYSIWYG )
	{
		oEditor.InsertHtml( '- This is some <a href="/Test1.html">sample<\/a> HTML -' ) ;
	}
	else
		alert( 'You must be on WYSIWYG mode!' ) ;
}

function parseAddress(target) {
	$(target).attr('disabled',true);
	var address = $('input[name=address]').val()
	if (address != "" && address != null && address != 'undefined') {
		$.ajax({
		  	type: 'post',
		  	dataType :'json',
		 	url: "wayparseAddress",
			data: "address="+address,	
		 	success: function(msg){
		 		$(target).attr('disabled',false);
				var obj = msg;
				// 百度解析
				if (obj.status == 0) {
					$('input[name=longitude]').val(obj.result.location.lng);
					$('input[name=latitude]').val(obj.result.location.lat);
				}else{
					$(".remark").show();
				} 
		 	},	
		});		
	}else{
		alert("地址不能为空");
		return false;
	}
}

function submitForm(target){

	$(target).attr('disabled',true);
	var oEditor = FCKeditorAPI.GetInstance('content') ;
	var detail = oEditor.GetXHTML(true);
    if(detail>5000){
    	$('#errorinfo').html("内容过长，请简短表述").slideDown().delay(2000).slideUp();
    	return false;
    }
	$('#errorinfo').html("提交中...").slideDown();
	$.ajax({
		type: 'POST',
		url : "waysaveOrUpdate",
		data: {
			  id:$('input[name=id]').val(),
			  address : $('input[name=address]').val(),
			  latitude:$('input[name=latitude]').val(),
			  longitude:$('input[name=longitude]').val(),
			  detail:detail
			  },
		success: function(data){
			 $(target).attr('disabled',false);
				if(data=="success"){
					$('#errorinfo').html("修改成功").slideDown().delay(2000).slideUp(function(){
						window.location.href = window.location.href;	
					});
				}else{
					$('#errorinfo').html(data).show().delay(2000).hide();	
				}
	       },
		 dataType: "text"
});	

}

$(document).ready(function(){	
	var height = $(document).height();
	$(".remark").hide();
	$('#content').css('height',height-40);
		conHeight = $('.west').height();
	var toph = ($(window).height() - $("#errorinfo").height())/5;   
   var  lefth = ($(window).width() - $("#errorinfo").width())/2;   
	var scrollTop = $(document).scrollTop();   
    var scrollLeft = $(document).scrollLeft(); 
    $("#errorinfo").css( { position : 'absolute', 'top' : 5+ scrollTop, left : lefth + scrollLeft} );
	
});
</script>