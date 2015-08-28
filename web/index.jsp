<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<!--
  Created using jsbin.com
  Source can be edited via http://jsbin.com/egaqub/7/edit
-->
<head>
<meta charset=utf-8 />
<title>千秋建筑装饰有限公司管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script class="jsbin" src="js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="easyui/themes/default/easyui.css">
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/plugins/jquery.parser.js"></script>

<!--[if IE]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<style>
body {
	margin: 0px;
	padding: 0px;
}

.west {
	width: 200px;
	padding: 10px;
}

.north {
	height: 100px;
}
</style>


</head>
<body class="easyui-layout">


	<div region="north" class="north" title="千秋建筑装饰有限公司管理系统"
		style="overflow: hidden; background: url(images/back.png)">
		<table style="width: 100%">
			<tr>
				<td >
				
				 <img src="images/top.png" />
				
				</td>
				<td valign="bottom" align="right"><a href="#" onClick="exit()">退出登录</a>&nbsp;&nbsp;<a
					href="#" id="chagnePwd">修改密码</a>&nbsp;&nbsp;</td>
			</tr>
		</table>


	</div>
	<div region="center" title="">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页" align="center">
				<div style="margin: 20px">欢迎使用千秋建筑装饰有限公司管理系统</div>
			</div>

		</div>

	</div>
	<div region="west" class="west" title="菜单">
		<ul id="tree"></ul>
	</div>


	<div id="tabsMenu" class="easyui-menu" style="width: 120px;">
		<div name="close">关闭</div>
		<div name="Other">关闭其他</div>
		<div name="All">关闭所有</div>
	</div>
	<script>
		function exit() {
			$.ajax({
				type : 'post',
				url : "adminexit",
				data : $("form").serialize(),
				success : function(msg) {
				}
			});
			window.top.location.href = "login.jsp";
		}

		$(function() {
			var treeData = [ {
				"id" : 1,
				"text" : "管理系统",

				"children" : [ {
					"id" : 11,
					"text" : "产品管理",
					"state" : "closed",
					"children" : [ {
						"id" : 111,
						"text" : "产品列表",
						"attributes" : {
							"url" : "pages/pc/scenery.jsp",  
						}
					} ]
				},
				{
					"id" : 12,
					"text" : "店铺管理",
					"state" : "closed",
					"children" : [ {
						"id" : 121,
						"text" : "店面设置",
						"attributes" : {
							"url" : "pages/storeset.jsp",
						}
					},{
						"id" : 122,
						"text" : "公司简介",
						"attributes" : {
							"url" : "introget.action?type=1",
						}
					},{
						"id" : 123,
						"text" : "企业文化",
						"attributes" : {
							"url" : "introget.action?type=2",
						}
					},{
						"id" : 124,
						"text" : "大事记",
						"attributes" : {
							"url" : "introget.action?type=3",
						}
					},{
						"id" : 125,
						"text" : "总经理致辞",
						"attributes" : {
							"url" : "introget.action?type=4",
						}
					},{
						"id" : 126,
						"text" : "交通指引",
						"attributes" : {
							"url" : "wayget",
						}
					} ]
				},{
					"id" : 13,
					"text" : "公告信息",
					"state" : "closed",
					"children" : [ {
						"id" : 131,
						"text" : "公告管理",
						"attributes" : {
							"url" : "pages/pc/announcement.jsp",
						}
					} , {
						"id" : 132,
						"text" : "合作意向名单",
						"attributes" : {
							"url" : "pages/partners.jsp",
						}
					} ]
				},
				]
			} ];
			var conHeight;
			var conWidth;
			//ʵβ˵
			$("#tree").tree({
				data : treeData,
				lines : true,
				onClick : function(node) {
					if (node.attributes) {
						Open(node.text, node.attributes.url);
					}
				}
			});
			//ұcenter򿪲˵tab
			function Open(text, url) {
				if ($("#tabs").tabs('exists', text)) {
					$('#tabs').tabs('select', text);
				} else {
					$('#tabs')
							.tabs(
									'add',
									{
										title : text,
										closable : true,
										content : "<iframe src="+url+" style='width:"+conWidth+"px;height:"+conHeight+"px;padding:0px;margin:0px;overflow:hidden;border:0px'/>",
									});
				}
			}
			;

			$('#chagnePwd').click(function() {
				Open("修改密码", "pages/pc/modifyPwd.jsp");
			});
			//tabsҼ˵
			$("#tabs").tabs({
				onContextMenu : function(e, title) {
					e.preventDefault();
					$('#tabsMenu').menu('show', {
						left : e.pageX,
						top : e.pageY
					}).data("tabTitle", title);
				}
			});

			//ʵmenuonClick¼
			$("#tabsMenu").menu({
				onClick : function(item) {
					CloseTab(this, item.name);
				}
			});

			//ر¼ʵ
			function CloseTab(menu, type) {
				var curTabTitle = $(menu).data("tabTitle");
				var tabs = $("#tabs");

				if (type === "close") {
					tabs.tabs("close", curTabTitle);
					return;
				}

				var allTabs = tabs.tabs("tabs");
				var closeTabsTitle = [];

				$.each(allTabs, function() {
					var opt = $(this).panel("options");
					if (opt.closable && opt.title != curTabTitle
							&& type === "Other") {
						closeTabsTitle.push(opt.title);
					} else if (opt.closable && type === "All") {
						closeTabsTitle.push(opt.title);
					}
				});

				for ( var i = 0; i < closeTabsTitle.length; i++) {
					tabs.tabs("close", closeTabsTitle[i]);
				}
			}
			conWidth = $('.tabs-panels-noborder').width();
			conWidth = conWidth - 4;
			conHeight = $('.west').height() + 13;

		});

		//下面内容为对话框脚步
		$.messager.defaults = {
			ok : "是",
			cancel : "否"
		};

		function toadd(ele, con) {
			var shade = $('.window-shadow');
			if (shade.length > 0) {
				shade.prev().remove();
				shade.remove();
			}
			var div = $("<div align='center'></div>");
			$(document.body).append(div);
			div.html(con);
			$.parser.parse(div);
			ele.toadd2(div);

		}

		function modify(ele, con) {
			var shade = $('.window-shadow');
			if (shade.length > 0) {
				shade.prev().remove();
				shade.remove();
			}
			var div = $("<div align='center'></div>");
			$(document.body).append(div);
			div.html(con);
			$.parser.parse(div);
			ele.modify2(div);

		}
		function del(ele, con) {

			if (con == null)
				con = "你确定要删除选中的行吗?";
			$.messager.confirm('删除确认', con, function(r) {
				if (r) {
					ele.del2();
				}
			});
		}
		function modify2(ele, con) {

			if (con == null)
				con = "你确定要修改选中的行吗?";
			$.messager.confirm('删除确认', con, function(r) {
				if (r) {
					ele.modify2();
				}
			});
		}
		function saveDialog() {

			$('#modifyDialog').dialog('close');
		}
		function cancleDialog() {
			$('#modifyDialog').dialog('close');
		}
		function createPanel(url) {
			var name = "编辑商品详情";
			conWidth = $('.tabs-panels-noborder').width();
			conWidth = conWidth - 4;
			conHeight = $('.west').height() + 13;
			if ($("#tabs").tabs('exists', name)) {
				var tabs = $("#tabs");
				tabs.tabs("close", name);
			}
			$('#tabs')
					.tabs(
							'add',
							{
								title : name,
								closable : true,
								content : "<iframe src="+url+" style='width:"+conWidth+"px;height:"+conHeight+"px;padding:0px;margin:0px;overflow:hidden;border:0px'/>",
							});
		}
	</script>

</body>
</html>