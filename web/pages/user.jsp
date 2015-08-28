<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script class="jsbin" src="../js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">

<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<script class="jsbin" src="../js/valide.js"></script>
	<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">

<title>无标题文档</title>
<style>
body{
padding:0px;
margin:0px;
}
</style>
</head>

<body>
	<table id="tt" fit="true">
	
	</table>

<form id="searchForm">
<div id="toolbar">
    <div style="margin:5px">
		
     
       &nbsp;用户名: 
        <input style="width:100px;display: inline-block;vertical-align: middle;" name="susername">	
		&nbsp;手机: 
        <input style="width:100px;display: inline-block;vertical-align: middle;" name="sphone">		
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="refreshPage()">查找</a>
    </div>
 </div>
</form>

<div style='color:red;border:1px solid red;padding:5px;font-size:12px;positon:absolute;z-index:20;background-color:#FFFFFF;display:none' id='errorinfo'></div>
</body>
</html>
<script>


function loadDataGrid(){

var conHeight;
var conWidth;
	conWidth =$('.panel-header').width();
	conWidth=$(window).width()-conWidth-50;
    everywidth =conWidth/6;	
$('#tt').datagrid({

	 rownumbers: true,
	 singleSelect: true,
     nowrap: false,
	 striped:true,
	 showPageList:false,
	
     toolbar:"#toolbar",
	//data: eval(msg),
	loadMsg:'数据加载中,请稍后...', 
	queryParams: {menuTypeId: $('input[name=menuType]').val(), menuName:$('input[name=menuName]').val() },
	url:'usergetAllUser',
	idField: 'id', 
	
	columns:[[
	
	{field:'name',title:'用户名',width:everywidth*2,align:'center',sortable:true},

	{field:'phone',title:'手机',width:everywidth*2,align:'center',sortable:true},
    {field:'createDate',title:'创建时间',width:everywidth*2,align:'center',sortable:true}
]],
  pagination: true,
  onLoadSuccess:function(){},
   rowStyler:function(index,row){   	
        if (index%2==1){   
          //  return 'background-color:#F2F2F2;';   
        }   
    }   
});
 var p = $('#tt').datagrid('getPager');
  if (p){
           $(p).pagination({ //设置分页功能栏
	
		       beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		   showPageList:false,
           });
       }
}
function getRowIndex(target){
    var tr = $(target).closest('tr.datagrid-row');
    return parseInt(tr.attr('datagrid-row-index'));
}
function refreshPage(){
 var query = { username: $('input[name=susername]').val(), phone:$('input[name=sphone]').val() };
 $("#tt").datagrid("load",query);
		
}
$(document).ready(function(){	

	conHeight = $('.west').height();
	var toph = ($(window).height() - $("#errorinfo").height())/5;   
   var  lefth = ($(window).width() - $("#errorinfo").width())/2;   
	var scrollTop = $(document).scrollTop();   
    var scrollLeft = $(document).scrollLeft(); 

	$("#errorinfo").css( { position : 'absolute', 'top' : 5+ scrollTop, left : lefth + scrollLeft} );  
	//$('#tt').css('width',conWidth);
	//refreshPage();
	  loadDataGrid();   

	  loadDataGrid();   
	//  var tt="102";
	//var t=/^(\d{1,3})$/.test(tt);
	//alert(t);


});

</script>