<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script class="jsbin" src="js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">

<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script class="jsbin" src="js/valide.js"></script>
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<title>无标题文档</title>
<style>

</style>
</head>

<body>

 <form  method ="post" enctype ="multipart/form-data"> 
       <input type="file" name ="image"/>     
	    <input type="text" name ="menuName"/> 
	   <input type="button" onclick="submitf()"/>
    </form> 
</body>

</html>
<script>

function submitf(){
alert(333);
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

