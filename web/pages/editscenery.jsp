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
	src="${pageContext.request.contextPath}/ckplayer/ckplayer.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/fckeditor/fckeditor.js"></script>
	

	
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
<body onload="init();">



	<!-- 视频 -->
	<div style="display: none">
		<form id="videodialog" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" />
		<input type="hidden" name="classNo" />
		<input type="hidden" name="videoNo" />

			<table style="width: 100%; padding: 10px" align="center">


				
				
				<tr>
					<td align="right">视频：</td>
					<td colspan="2"><input type="file" name="image" id="shipin"
						class="easyui-validatebox" data-options="required:true"></td>
				</tr>
				<tr>
					<td align="right">视频名称：</td>
					<td><input class="easyui-validatebox" name="sceneryName"
						data-options="required:true,validType:'length[0,200]'"></td>
				</tr>
				<tr>
					<td align="right">视频说明：</td>
					<td><textarea style="width: 100%; height: 50px;"
							name="sceneryDesc" class="easyui-validatebox"	
							data-options="validType:'length[0,200]'"></textarea></td>
				</tr>



			</table>
		</form>
	</div>


	<!-- 声音 -->
	<div style="display: none">
		<form id="voicedialog" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" />
		<input type="hidden" name="classNo" />
		<input type="hidden" name="videoNo" />
			<table style="width: 100%; padding: 10px" align="center">
			
			
			
				
				<tr>
					<td align="right">声音：</td>
					<td colspan="2"><input type="file" name="image" id="yuyin"
						class="easyui-validatebox" data-options="required:true"></td>
				</tr>
				<tr>
					<td align="right">声音名称：</td>
					<td><input class="easyui-validatebox" name="sceneryName"
						data-options="required:true,validType:'length[0,200]'"></td>
				</tr>
				<tr>
					<td align="right">声音说明：</td>
					<td><textarea style="width: 100%; height: 50px;"
							name="sceneryDesc" class="easyui-validatebox"	
							data-options="validType:'length[0,200]'"></textarea></td>
				</tr>

			</table>
		</form>
	</div>

	<div id="toolbar" class="datagrid-toolbar"
		style="height: 30px; padding-top: 6px;">
		<div
			style='color: red; border: 1px solid red; padding: 5px; font-size: 12px; positon: absolute; z-index: 20; background-color: #FFFFFF; display: none'
			id='errorinfo'></div>


		<div style="float: right">
	<input type="hidden" name="id"
				value="<s:property value='#request.scenery.id'/>">
				
				<s:select name="classNo"  id="classNo" list="#{'1':'产地特点','2':'产品特点','3':'营养价值','4':'食用方法','5':'产品介绍'}" 
				label="分类" headerKey="" headerValue="请选择分类"></s:select> 
				
                <a id="btn2" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" onclick="generateQrCode(this,'1')">编辑图片</a>
					
					视频编号：<select name="videoNo"   id="videoNo"
						style="width: 80px; height: auto">
							<option value="1">视频1</option>
							<option value="2">视频2</option>
							<option value="3">视频3</option>
					</select>
				<a id="btn1" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" onclick="modifyVideo(this)">增加视频</a>
					声音 编号：
					<select name="videoNo" id="videoNo"
						style="width: 80px; height: auto">
							<option value="1">声音 1</option>
							<option value="2">声音 2</option>
							<option value="3">声音 3</option>
					</select>
				
					
			
			<a id="btn1" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" onclick="modifyVoice(this)">增加音频</a>
			<a id="btn1" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" onclick="submitForm(this)">提交修改</a>
			
			<a id="btn2" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" onclick="generateQrCode(this,'2')">生成视频二维码</a>
		</div>
	</div>
	<div style="overflow-y: scroll; height: 300px" id="content">
		<table style="width: 100%">
			<tr>
				<td align="center" style="width: 650px" valign="top"></br> </br>
					<div>
						<input type="hidden" id="voice"
							value="<s:property value='#request.scenery.voice'/>"> <input
							type="hidden" id="video"
							value="<s:property value='#request.scenery.video'/>"><input
							type="hidden" id="qrCodeVideoPic"
							value="<s:property value='#request.scenery.qrCodeVideoPic'/>">
						<input type="hidden" id="qrCodeVoicePic"
							value="<s:property value='#request.scenery.qrCodeVoicePic'/>">
						<input type="hidden" id="sceneryId"
							value="<s:property value='#request.scenery.id'/>">
					</div>
					<div style="margin-left: 10px; margin-right: 10px;">


						<div id="showVoice">
							<hr>
							语音(<a href="#" onclick="reupload('1')">重传语音</a>)</br>
							<div class='align_c'>
								<div id="ivs_player" class="clear align_c margin_c"
									style="margin-top: 10px; margin-bottom: 10px;">voiceplayer...</div>
							</div>
						</div>
						<div id="selectVoice">
							<hr>
							语音</br>
							<form method="POST" enctype="multipart/form-data">
								<input type="file" id="yuyin" name="image" /><input
									type="button" value="上传" onclick="uploadRes(this,1)" /> 
							</form>
						</div>




						<div id="showVideo">
							<hr>
							视频1(<a href="#" onclick="reupload('2')">重传视频</a>)</br>
							<div class='margin_c bg_0'
								style='width: 640px; margin-top: 10px;'>
								<div id="div_video" style="width: 640px;">
									<div id="box_video" class="clear align_c">videoplayer...</div>
								</div>
							</div>
						</div>

						<div id="selectVideo">
							<hr>
							视频1</br>
							<form method="POST" enctype="multipart/form-data">
								<input type="file" id="shipin" name="image" /><input
									type="button" value="上传" onclick="uploadRes(this,2)" />
							</form>
						</div>

						<div>
							<div id="qrCodeVoice">
								<hr>
								音频二维码</br> <img src="" style="height: 200px; width: 200px;" id="img2" />
							</div>
							<div id="qrCodeVedio">
								<hr>
								视频二维码</br> <img src="" style="height: 200px; width: 200px;" id="img3" />
							</div>

						</div>
					</div></td>
				<td align="left" style="padding-left: 50px">
					<div
						style="font-weight: bold; margin-top: 5px; font-size: 13px; margin-bottom: 5px;">文字介绍</div>


					<div style="display: none">
						<!--建一个同名称的文本域使默认值同步，再隐藏文本域-->
						<textarea name="detail" id="detail" cols="80" rows="20"
							style="width: 400px">
						<s:property value='#request.product.productDesc' />
						</textarea>
					</div>

					<div style="border: 1px solid #000000; width: 350px">
						<script type="text/javascript">
							var oFCKeditor = new FCKeditor('detail');
							oFCKeditor.BasePath = '${pageContext.request.contextPath}/fckeditor/';

							//oFCKeditor.ToolbarSet = 'MyToolbar';
							oFCKeditor.Height = 800;
							oFCKeditor.Width = 350;

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
	function InsertHTML() {
		var oEditor = FCKeditorAPI.GetInstance('FCKeditor1');
		if (oEditor.EditMode == FCK_EDITMODE_WYSIWYG) {
			oEditor
					.InsertHtml('- This is some <a href="/Test1.html">sample<\/a> HTML -');
		} else
			alert('You must be on WYSIWYG mode!');
	}

	
	
	
	function uploadRes(ele, index) {
		var ff;
		var url;
            if (index == 1) {
			var yuyin = $("#yuyin").val();
			if (yuyin == "") {
				alert("请选择音频");
				return false;
			}
			var fileType = yuyin.substring(yuyin.lastIndexOf(".") + 1);
			if (fileType != "mp3" && fileType != "MP3") {
				alert("音频文件只支持MP3格式");
				return false;
			}			
			ff = $($("form")[0]);
			url = "sceneryupdateVoice";
		} else {
			var shipin = $("#shipin").val();
			if (shipin == "") {
				alert("请选择视频");
				return false;
			}
			var fileType = shipin.substring(shipin.lastIndexOf(".") + 1);
			if (fileType != "mp4" && fileType != "MP4" && fileType != "flv" && fileType != "FLV" &&
				fileType != "avi" && fileType != "AVI" && fileType != "asf" && fileType != "ASF") {
				alert("请选择常用视频格式(mp4,flv,avi,asf),推荐mp4格式");
				return false;
			}			
			ff = $($("form")[1]);
			url = "sceneryupdateVideo";
		}

		$(ele).attr('disabled', true);
		$('#errorinfo').html("上传中...").slideDown();
		ff.form("submit", {
			url : url,
			onSubmit : function() {
				return true;
			},
			success : function(data) {
				$(ele).attr('disabled', false);
				var img = ff.prev().prev();
				var src = img.attr('src');

				if (data == "success") {
					$('#errorinfo').html("上传成功").slideDown().delay(2000).slideUp(function(){
						window.location.reload(); 
					});	
				} else {
					$('#errorinfo').html(data).show().delay(2000).hide();
				}
			}
		});
	}
	
	function reupload(val){
		if(val == '1'){//重传语音
			$("#showVoice").hide();
			$("#selectVoice").show();
		}else{//重传视频
			$("#showVideo").hide();
			$("#selectVideo").show();
		}
	}

	function init() {
		
		var video = $("#video").val();
		var voice = $("#voice").val();
		var qrCodeVideoPic = $("#qrCodeVideoPic").val();
		var qrCodeVoicePic = $("#qrCodeVoicePic").val();
		
		var height = $(document).height();
		$('#content').css('height', height - 40);
		conHeight = $('.west').height();
		var toph = ($(window).height() - $("#errorinfo").height()) / 5;
		var lefth = ($(window).width() - $("#errorinfo").width()) / 2;
		var scrollTop = $(document).scrollTop();
		var scrollLeft = $(document).scrollLeft();

		$("#errorinfo").css( { position : 'absolute', 'top' : 5+ scrollTop, left : lefth + scrollLeft} );
		
		if (video != null && video != '' && video != 'undefined') {
			$("#showVideo").show();
			$("#selectVideo").hide();
 			videoBuild("<s:property value='#request.scenery.video'/>",
				'div_video', 'box_video', 452, 360);
		} else {
			$("#showVideo").hide();
			$("#selectVideo").show();
		}
		
		if (voice != null && voice != '' && voice != 'undefined') {
			$("#showVoice").show();
			$("#selectVoice").hide();
			voiceBuild("<s:property value='#request.scenery.voice'/>");
		} else {
			$("#showVoice").hide();
			$("#selectVoice").show();
		}
		
		if (qrCodeVideoPic != null && qrCodeVideoPic != '' && qrCodeVideoPic != 'undefined') {
			$("#qrCodeVedio").show();
			$('#img3').attr('src',
			"<s:property value='#request.scenery.qrCodeVideoPic'/>");
		} else {
			$("#qrCodeVedio").hide();
		}		
		
		if (qrCodeVoicePic != null && qrCodeVoicePic != '' && qrCodeVoicePic != 'undefined') {
			$("#qrCodeVoice").show();
			$('#img2').attr('src',
			"<s:property value='#request.scenery.qrCodeVoicePic'/>");
		} else {
			$("#qrCodeVoice").hide();
		}
		
	}
	function fileTypeCheck(filepath,suppotFile){
		//var suppotFile=".mp3.amr";
		if(filepath!=""){
			if(filepath.lastIndexOf(".")==-1){
				return false;
			}else{
				var fileType = (filepath.substring(filepath.lastIndexOf("."),filepath.length)).toLowerCase();
				if(suppotFile.indexOf(fileType)==-1){return false;}
				return true;
			}
		}else{
			return false;
		}
	}
	function videoBuild(ivideo,idiv,ibox,iwidth,iheight){
		if(fileTypeCheck(ivideo,".flv.f4v.mp4")){
			if(CKobject.getObjectById('ckplayer_a1')){
				var iplayer=CKobject.getObjectById('ckplayer_a1');
				iplayer.ckplayer_newaddress('{f->'+ivideo+'}');return;
			}
			var flashvars={f:ivideo,c:0};//b:1去掉后可js
			var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always'};
			CKobject.embedSWF('${pageContext.request.contextPath}/ckplayer/ckplayer.swf',ibox,'ckplayer_a1',iwidth,iheight,flashvars,params);
			if(fileTypeCheck(flashvars.f,".mp4")){
				var video=[flashvars.f+'->video/mp4'];
				var support=['iPad','iPhone','ios','android+false','msie10+false'];
				CKobject.embedHTML5(idiv,'ckplayer_a1',iwidth,iheight,video,flashvars,support);
			}
			
		}else{
			alert('file is not:.flv.f4v.mp4');
		}
	}
	function voiceBuild(ivoice) {
		if (fileTypeCheck(ivoice, ".mp3")) {
				var htmlstr = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="400" height="30"  codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">';
			    htmlstr += '<param name="movie" value="${pageContext.request.contextPath}/jmp3/singlemp3player.swf?file='+ ivoice +'&showDownload=false&autoStart=false&repeatPlay=true&songVolume=100"/>' ;
			    htmlstr += '<param name="wmode" value="transparent" />';
			    htmlstr += '<embed wmode="transparent" width="400" height="30" src="${pageContext.request.contextPath}/jmp3/singlemp3player.swf?file='+ ivoice +'&showDownload=false&autoStart=false&repeatPlay=true&songVolume=100"';
			    htmlstr += 'type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />';
			    htmlstr += '</object>';
				$("#ivs_player").html(htmlstr);
		} else {
			alert("抱歉，暂无语音播放");
		}
	}
	function checkHtml5() {
		var a = document.createElement('audio');
		return !!(a.canPlayType && a.canPlayType('audio/mpeg;').replace(/no/, ''));
	}	
	
	
	
	
	
	// 	视频
	function modifyVideo(target) {
		var dialog = $('#videodialog').clone(true);
		dialog.find('input[name=id]').val(<s:property value='#request.scenery.id'/>);
		dialog.find('input[name=classNo]').val($('#classNo').val());
		dialog.find('input[name=videoNo]').val($('#videoNo').val());
		top.toadd(this, dialog);
	}
	function toadd2(ele) {
		ele.dialog({
			title : '增加视频(基本信息)',
			closed : false,
			cache : false,
			modal : true,
			buttons : [
					{
						iconCls : 'icon-save',
						text : '确认',
						iconCls : 'icon-ok',
						handler : function() {

							$("#videodialog", ele).form(
									"submit",
									{
										url : "sceneryupdateVideo",
										idField : 'id',
										onSubmit : function() {
											var valide = ele.form('validate');
											
											if (valide) {
												
												
												ele.dialog('close');
												return true;
											} else
												return false;
										},
										success : function(data) {
											if (data == "success") {
												$('#errorinfo').html("增加视频成功")
														.slideDown()
														.delay(2000).slideUp();
												refreshPage();
											} else {
												$('#errorinfo').html(data)
														.slideDown()
														.delay(2000).slideUp();
											}
										}
									});

						}
					}, {
						text : '取消',
						handler : function() {
							ele.dialog('close');
						}
					} ]
		});
	}	
	
    // 语音
	function modifyVoice(target) {
		var dialog = $('#voicedialog').clone(true);
		dialog.find('input[name=id]').val(<s:property value='#request.scenery.id'/>);
		dialog.find('input[name=classNo]').val($('#classNo').val());
		dialog.find('input[name=videoNo]').val($('#videoNo').val());
		
		top.modify(this, dialog);
	}
    
	function modify2(ele) {
		ele.dialog({
			title : '增加语音(基本信息)',
			closed : false,
			cache : false,
			modal : true,
			buttons : [
					{
						iconCls : 'icon-save',
						text : '确认',
						iconCls : 'icon-ok',
						handler : function() {
							$("#voicedialog", ele).form(
									"submit",
									{
										url : "sceneryupdateVoice",
										idField : 'id',
										idField : 'id',
										onSubmit : function() {

											var valide = ele.form('validate');
											if (valide) {
												
												ele.dialog('close');
												return true;
											} else
												return false;
										},
										success : function(data) {

											if (data == "success") {

												$('#errorinfo').html("修改语音成功")
														.slideDown()
														.delay(2000).slideUp();
												refreshPage();
											} else {
												$('#errorinfo').html(data)
														.slideDown()
														.delay(2000).slideUp();
											}

										}
									});

						}
					}, {
						text : '取消',
						handler : function() {
							ele.dialog('close');
						}
					} ]
		});

	}
	
	// 图文
	function submitForm(target) {
	
		$(target).attr('disabled', true);
		var oEditor = FCKeditorAPI.GetInstance('detail');
		var detail = oEditor.GetXHTML(true);
        if(detail>5000){
        	$('#errorinfo').html("内容过长，请简短表述").slideDown().delay(2000).slideUp();
        	return false;
        }
		$('#errorinfo').html("提交中...").slideDown();
		$.post("scenerysaveOrUpdate", {
			detail : detail,
			id : $("#sceneryId").val(),
			classNo : $("#classNo").val(),
		}, function(data) {
			$(target).attr('disabled', false);
			if (data == "success") {
				$('#errorinfo').html("修改成功").slideDown().delay(2000).slideUp();
			} else {
				$('#errorinfo').html(data).show().delay(2000).hide();
			}
		});

	}
	
	// 装载图文
	function generateQrCode(target,val) {

		$(target).attr('disabled', true);
		$('#errorinfo').html("生成中...").slideDown();
		$.ajax({
			type: 'POST',
			url : "scenerygenerateQrCode",
			data: {
				  id:$("#sceneryId").val(),
				  index:val,
				  classNo : $("#classNo").val(),
				  },
			success: function(data){
				 $(target).attr('disabled',false);
					if(data=="success"){
						$('#errorinfo').html("生成成功").slideDown().delay(2000).slideUp(function(){
							window.location.href = "scenerygetDetail?id="+$("#sceneryId").val()+"&classNo="+$("#classNo").val();
						});
					}else{
						$('#errorinfo').html(data).show().delay(2000).hide();	
					}
		       },
			 dataType: "text"
	});		
	}	

	
	
	function uploadVideo(ele, index) {
		var ff;
		var url;
            if (index == 1) {
			var yuyin = $("#yuyin").val();
			if (yuyin == "") {
				alert("请选择音频");
				return false;
			}
			var fileType = yuyin.substring(yuyin.lastIndexOf(".") + 1);
			if (fileType != "mp3" && fileType != "MP3") {
				alert("音频文件只支持MP3格式");
				return false;
			}			
			ff = $($("form")[0]);
			url = "sceneryupdateVoice";
		} else {
			//var shipin = $("#shipin").val();
			var shipin =ele.dialog.find('input[name=sceneryName]').val();
			if (shipin == "") {
				alert("请选择视频");
				return false;
			}
			var fileType = shipin.substring(shipin.lastIndexOf(".") + 1);
			if (fileType != "mp4" && fileType != "MP4" && fileType != "flv" && fileType != "FLV" &&
				fileType != "avi" && fileType != "AVI" && fileType != "asf" && fileType != "ASF") {
				alert("请选择常用视频格式(mp4,flv,avi,asf),推荐mp4格式");
				return false;
			}			
			ff = $($("form")[1]);
			url = "sceneryupdateVideo";
		}

		$(ele).attr('disabled', true);
		$('#errorinfo').html("上传中...").slideDown();
		ff.form("submit", {
			url : url,
			onSubmit : function() {
				return true;
			},
			success : function(data) {
				$(ele).attr('disabled', false);
				var img = ff.prev().prev();
				var src = img.attr('src');

				if (data == "success") {
					$('#errorinfo').html("上传成功").slideDown().delay(2000).slideUp(function(){
						window.location.reload(); 
					});	
				} else {
					$('#errorinfo').html(data).show().delay(2000).hide();
				}
			}
		});
	}

	
</script>