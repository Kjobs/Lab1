<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Add Book</title>
<style type="text/css">
	h1 {font-family:Georgia;}
	div.main{
		width:500px;
		margin:120px auto;
	}
</style>
</head>
<body>
	<div class="main">
		<h1>Add a Book</h1>
		<br/>
		<form action="AddBook" method="post">
			<table>
				<tr>
					<td>ISBN</td><td><input type="text" name="ISBN" /></td>
				</tr>
				<tr>
					<td>Title</td><td><input type="text" name="Title" /></td>
				</tr>
				<tr>
					<td>Name</td><td><input type="text" name="Name" /></td>
				</tr>
				<tr>
					<td>Publisher</td><td><input type="text" name="Publisher" /></td>
				</tr>
				<tr>
					<td>PublishDate</td><td><input type="text" name="PublishDate" /></td>
				</tr>
				<tr>
					<td>Price</td><td><input type="text" name="Price" /></td>
				</tr>	
			</table>
			<br/><br/>	
			<input type="submit" value="AddBook">
		</form>
		<p><a href="<s:url action='ReturnMain' />">Return to page of Index</a></p>
	</div>
</body>
</html>
