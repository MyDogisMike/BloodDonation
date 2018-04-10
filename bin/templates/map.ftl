<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
		<title>献血地图</title>
		<#include "headInclude.ftl"/>
		<link rel="stylesheet" href="${request.contextPath}/site/css/map.css" />
		<script type="text/javascript" src="${request.contextPath}/site/js/map.js" ></script>
		<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.4&key=b0ddee3e124adec25fbf9b3505a49f46"></script>
		<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
	</head>
	<body>
		<#include "header.ftl"/>
		<div id="container"></div>
		<div id="panel"></div>
		<#include "footer.ftl"/>
	</body>
</html>
