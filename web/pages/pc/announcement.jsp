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

<title>无标题文档</title>
<style>
body {
	padding: 0px;
	margin: 0px;
}

.box { /*非IE的主流浏览器识别的垂直居中的方法*/
	display: table-cell;
	vertical-align: middle;
	/*设置水平居中*/
	text-align: center;
	/* 针对IE的Hack */
	*display: block;
	*font-size: 61px; /*约为高度的0.873，200*0.873 约为175*/
	*font-family: Arial; /*防止非utf-8引起的hack失效问题，如gbk编码*/
	width: 147px;
	height: 70px;
}

.box img { /*设置图片垂直居中*/
	vertical-align: middle;
}
</style>
</head>

<body>
	<table id="tt" data-options="fit:true">

	</table>

	<form id="searchForm">
		<div id="toolbar">
			<div style="margin: 5px">
				<a id="btn" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-add'" onclick="toadd()">增加</a> <span
					style="margin-left: 50px">主题:</span> <input type="text"
					name="sannouncementName" /> <a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" onclick="refreshPage()">查找</a>



			</div>
		</div>
	</form>


	<div style="display: none">
		<form id="dialog" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" />

			<table style="float: center; width: 100%; padding: 10px">

				<tr>
					<td align="right">主题：</td>
					<td><input class="easyui-validatebox" name="announcementName"
						data-options="required:true,validType:'productname'"></td>
				</tr>
			</table>
		</form>
	</div>
	<div
		style='color: red; border: 1px solid red; padding: 5px; font-size: 12px; positon: absolute; z-index: 20; background-color: #FFFFFF; display: none'
		id='errorinfo'></div>
</body>
</html>
<script>
	function editPics(target) {
		var rowindex = getRowIndex(target);
		$('#tt').datagrid('selectRow', rowindex);
		var row = $('#tt').datagrid('getSelected');
		top.createPanel("announcementgetPicDetail?id=" + row.id);
	}


	function toadd() {
		var dialog = $('#dialog').clone(true);
		top.toadd(this, dialog);
	}
	function toadd2(ele) {
		ele.dialog({
			title : '增加公告',
			closed : false,
			cache : false,
			modal : true,
			buttons : [
					{
						iconCls : 'icon-save',
						text : '确认',
						iconCls : 'icon-ok',
						handler : function() {

							$("#dialog", ele).form(
									"submit",
									{
										url : "announcementadd",
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
												$('#errorinfo').html("增加成功")
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

	function modify(target) {
		
		var rowindex = getRowIndex(target);
		$('#tt').datagrid('selectRow', rowindex);
		var row = $('#tt').datagrid('getSelected');
		var dialog = $('#dialog').clone(true);
		
		
		
		dialog.find('input[name=announcementName]').val(row.announcementName);
		//dialog.find('textarea[name=sceneryDesc]').val(row.sceneryDesc);
		//dialog.find('input[name=sceneryHead]').val(row.sceneryHead);
		dialog.find('input[name=id]').val(row.id);
		top.modify(this, dialog);

	}

	function modify2(ele) {
		
		//var row = $('#tt').datagrid('getSelected');
		ele.dialog({
			title : '修改主题',
			closed : false,
			cache : false,
			modal : true,
			buttons : [
					{
						iconCls : 'icon-save',
						text : '确认',
						iconCls : 'icon-ok',
						handler : function() {
							$("#dialog", ele).form(
									"submit",
									{
										url : "announcementmodify",
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

												$('#errorinfo').html("修改成功")
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
	function del() {
		top.del(this);
	}
	function del2() {

		var row = $('#tt').datagrid('getSelected');
		$.ajax({
			type : 'post',
			url : "announcementdel",
			data : "id=" + row.id,
			success : function(msg) {
				if (msg == 'success') {
					$('#errorinfo').html("删除成功").slideDown().delay(2000)
							.slideUp();
					refreshPage();
				} else
					$('#errorinfo').html(data).slideDown().delay(2000)
							.slideUp();
			}
		});
	}

	function loadDataGrid() {

		//var conHeight;
		var conWidth;
		conWidth = $('.panel-header').width();
		conWidth = $(window).width() - conWidth - 55;
		everywidth = conWidth / 15;
		$('#tt')
				.datagrid(
						{

							rownumbers : true,
							singleSelect : true,
							nowrap : false,
							striped : true,
							showPageList : false,

							toolbar : "#toolbar",
							//data: eval(msg),
							loadMsg : '数据加载中,请稍后...',
							url : 'announcementget',
							idField : 'id',

							columns : [ [

									{
										field : 'announcementName',
										title : '主题',
										width : everywidth * 3,
										align : 'center',
										sortable : true
									},
									{
										field : 'opt1',
										title : '编辑详情',
										width : everywidth,
										align : 'center',
										formatter : function(value, row, index) {
											return "<img style='width:18px;cursor:pointer;padding:2px;'  src='../../images/modify.png' onclick='editPics(this)'>";
										}
									},
									{
										field : 'opt3',
										title : '修改',
										width : everywidth,
										align : 'center',
										formatter : function(value, row, index) {
											return "<img style='width:18px;cursor:pointer;padding:2px;'  src='../../images/modify.png' onclick='modify(this)'>";
										}
									},
									{
										field : 'opt4',
										title : '删除',
										width : everywidth,
										align : 'center',
										formatter : function(value, row, index) {
											return "<img style='width:18px;cursor:pointer;padding:2px;' src='../../images/delete.png' onclick='del(this)'>";
										}
									} ] ],
							pagination : true,
							onLoadSuccess : function() {
							},
							rowStyler : function(index, row) {
								if (index % 2 == 1) {
									//  return 'background-color:#F2F2F2;';   
								}
							}
						});
		var p = $('#tt').datagrid('getPager');
		if (p) {
			$(p).pagination({ //设置分页功能栏

				beforePageText : '第',//页数文本框前显示的汉字  
				afterPageText : '页    共 {pages} 页',
				displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
				showPageList : false,
			});
		}
	}
	function getRowIndex(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	function refreshPage() {
		var query = {
				announcementName : $('input[name=sannouncementName]').val()
		};

		$("#tt").datagrid("load", query);

	}
	$(document).ready(function() {
		conHeight = $('.west').height();
		//var toph = ($(window).height() - $("#errorinfo").height()) / 5;
		var lefth = ($(window).width() - $("#errorinfo").width()) / 2;
		var scrollTop = $(document).scrollTop();
		var scrollLeft = $(document).scrollLeft();

		$("#errorinfo").css({
			position : 'absolute',
			'top' : 5 + scrollTop,
			left : lefth + scrollLeft
		});
		$('#tt').datagrid({
			loadFilter : pagerFilter
		}).datagrid('loadData', loadDataGrid());
	});
	
	function pagerFilter(data) {
		if (typeof data.length == 'number' && typeof data.splice == 'function') { // 判断数据是否是数组
			data = {
				total : data.length,
				rows : data
			};
		}
		var dg = $(this);
		var opts = dg.datagrid('options');
		var pager = dg.datagrid('getPager');
		pager.pagination({
			onSelectPage : function(pageNum, pageSize) {
				opts.pageNumber = pageNum;
				opts.pageSize = pageSize;
				pager.pagination('refresh', {
					pageNumber : pageNum,
					pageSize : pageSize
				});
				dg.datagrid('loadData', data);
			}
		});
		if (!data.originalRows) {
			data.originalRows = (data.rows);
		}
		var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
		var end = start + parseInt(opts.pageSize);
		data.rows = (data.originalRows.slice(start, end));
		return data;
	}

</script>