<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib prefix= "t" tagdir="/WEB-INF/tags" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Highchart</title>
</head>
<body>
<t:nav></t:nav>
<t:header></t:header>
<div class= "container">
	<h2>Welcome to Finteck charts</h2>
	<div id="container" style="height: 500px;"></div>
	<script src="js/highcharts.js"></script>
	<script src="js/jquery.js"></script>
	<script src="js/index.js"></script>
</div>
</body>
</html>