<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>验证页面</title>
<#include "headInclude.ftl"/>
</head>
<body>
	<input type="hidden" value="${msg}" id="msg" />
</body>
<script>
	$(function(){
		alertMessage("提示", $("#msg").val(), function(){location.href = '/';});
	});
</script>
</html>