<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Author's Book</title>
</head>
<body>
	<h1 align="center">Books of ${AuthorName}</h1>
	<div align="center">
		<table border=2>
			<tr>
				<td width=170>Title</td>
				<td width=170>Author</td>
				<td width=170></td>
			</tr>
			<s:iterator value="AuthorBookList">
			<tr>
				<td><a href="QueryAB.action?Title=<s:property />"><s:property /></a></td>
				<td>${AuthorName}</td>
				<td><a href="DeleteBook.action?Title=<s:property />">Delete</a></td>
			</tr>	
			</s:iterator>
		</table>
		<p><a href="<s:url action='ReturnMain' />">Return to page of Index</a></p>
	</div>
</body>
</html>
