<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Query Book</title>
<style type="text/css">
	h2 {font-family:Georgia, serif;}
	div.main{
		width:600px;
		margin:100px auto;
	}
</style>
</head>
<body>
	<div class="main">
		<h2>Query books of your input author's name</h2>
		<br/><br/>
		<s:form action="QueryAuthorBook" method="post">
			<div class="author">Input the name of a author(Empty will display all authors)</div><br/>
			<input type="text" name="AuthorName" />&nbsp;
			<input type="submit" value="Query" />
		</s:form>
		<p><a href="<s:url action='ReturnMain' />">Return to page of Index</a></p>
	</div>
</body>
</html>
