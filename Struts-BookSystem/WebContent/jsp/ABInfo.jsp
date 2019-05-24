<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Author and Book</title>
<style type="text/css">
	h1 {font-family:Georgia, serif;}
</style>
</head>
<body>
	<h1 align="center"><strong>Information about ${Title} and its Author</strong></h1>
	<div align="center">
		<br/>
		<h3>Book Information:</h3>
		<table border=1>
			<tr>
				<th width=150>ISBN</th>
				<th width=150>Title</th>
				<th width=150>AuthorID</th>
				<th width=150>Publisher</th>
				<th width=150>PublishDate</th>
				<th width=150>Price</th>
			</tr>
			<s:iterator value="book">
			<tr>
				<td><s:property value="ISBN" /></td>
				<td><s:property value="Title" /></td>
				<td><s:property value="AuthorID" /></td>
				<td><s:property value="Publisher" /></td>
				<td><s:property value="PublishDate" /></td>
				<td><s:property value="Price" /></td>
			</tr>
			</s:iterator>
		</table>
		<br/><br/><br/>
		<h3>Author Information:</h3>
		<table border=1>
			<tr>
				<th width=150>AuthorID</th>
				<th width=150>Name</th>
				<th width=150>Age</th>
				<th width=150>Country</th>
			</tr>
			<s:iterator value="author">
			<tr>
				<td><s:property value="AuthorID" /></td>
				<td><s:property value="Name" /></td>
				<td><s:property value="Age" /></td>
				<td><s:property value="Country" /></td>
			</tr>
			</s:iterator>
		</table>
		<p><a href="<s:url action='ReturnMain' />">Return to page of Index</a></p>
	</div>
</body>
</html>
