<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="#request.store.storeName" /></title>
<script type="text/javascript">
	function bohao() {
		var phone = '${request.store.storeComm}';
		if (phone != null && phone != '' && phone != 'undefined') {
			if (/android/i.test(navigator.userAgent)) {
				// todo : android
				window.location = "tel:" + phone;
			} else if (/ipad|iphone|mac/i.test(navigator.userAgent)) {
				window.location = "tel:" + phone;
				document.writeln('<a href="tel:'+phone+'">联系我们 </a>');
				// todo : ios
			} else {
				document.writeln('<a href="tel:'+phone+'">联系我们 </a>');
			}
		}

	}
	window.onload = bohao();
</script>
</head>

<body>
	<div>
		<s:hidden name="#request.store.storeComm" id="phone" />
	</div>
</body>
</html>