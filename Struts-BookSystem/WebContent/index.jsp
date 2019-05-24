<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<style type="text/css">
	h1 {font-family:Georgia, serif;}
	div.main{
		width:500px;
		margin:100px auto;
	}
</style>
</head>
<body>
	<div class="main">
		<h1>Welcome To My Book System!</h1>
		<br/>
		<p><a style="text-decoration:none" href="<s:url action='hello' />">Hello World</a></p>
		<br/>
		<p><a style="text-decoration:none" href="<s:url action='Query' />">Query a Author</a></p>
		<br/>
		<p><a style="text-decoration:none" href="<s:url action='PageAdd' />">Add a Book</a></p>
		<br/>
	</div>
</body>
</html>
