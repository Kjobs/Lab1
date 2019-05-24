<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>AuthorList</title>
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
		<h1>List of Author</h1>
		<table border=1>
			<tr>
				<th>AuthorID</th>
				<th>Name</th>
			</tr>
			<s:iterator value="AuthorList">
				<tr>
					<td width=100><s:property value="AuthorID"/></td>
					<td width=150><s:property value="Name"/></td>
				</tr>
			</s:iterator>
		</table>
		<p><a href="<s:url action='ReturnMain' />">Return to page of Index</a></p>
	</div>
</body>
</html>
