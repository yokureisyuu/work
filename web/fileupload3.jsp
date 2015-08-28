<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
	<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script class="jsbin" src="../js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../fckeditor/fckeditor.js"></script> <!--载入fckeditor类-->

<title>用JavaScript调用FCKeditor</title>
<style>
body{

	margin:0px;
	padding:0px;

	overflow-x:hidden;
	font-family: "Helvetica Neue",Helvetica,Arial;
font-size:12px;

}
.base tr{
height:40px;
font-size:12px;

}
.base tr td>input{
border:0px;
border-bottom:1px solid #000000;
width:500px;
padding-left:10px;

}
.base tr td>div{
border:0px;
border-bottom:1px solid #000000;
width:500px;
padding-left:10px;

}
</style>
</head>
<body>
<div style="padding:10px;">

微店首页图片:<div style='color:red;border:1px solid red;padding:5px;font-size:12px;positon:absolute;z-index:20;background-color:#FFFFFF;display:none' id='errorinfo'></div>
</br></br>

<table style="width:100%">
<tr>
<td>
<img src="<s:property value='#session.store.storeSwitchImage1'/>?" style="height:130px;width:250px;"/></br>
<form method ="POST" enctype ="multipart/form-data">
 <input type="file" name ="image"/>  
<input type="hidden" name="picindex" value="1">
<input type="button" value="上传" onclick="upload(this,1)"/>
</form>

</td>


</tr>
</table>
</br>
微店介绍(此内容会在微信的关于我们菜单中显示)
</br></br>


</div>
</body>
</html>
<script>
function upload(ele,index){

//$(ele).attr('disabled',true);
$("form").form("submit",{
				url: "storeuploadPic",	
				onSubmit: function(){   

						   return true;
                          },  
				 success:function(data){ 
				 
				   }
			});
}

</script>