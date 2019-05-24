<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>Welcome to NewsFocus</title>
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>NewsFocus</title>
<style type="text/css">
	#header h1{ padding-top:30px !important; font-size: 30px;}
	#top-navigation { padding-top:30px !important; font-size: 15px;}
	.box-content {
		border-radius: 12px;
		background: #fff;
		text-align: center;
		height: 400px; 
	}
	.main-title {
		color: #000;
		margin-top: 100px; 
		font-size: 50px;
		font-family:"Times New Roman",Georgia,Serif
	}
	.subtitle {
		margin-top: 50px;
		font-size: 30px;
		color: #000;
		font-family:"Times New Roman",Georgia,Serif
	}
	.enter-button {
		color: #000;
		width: 260px;
		margin-left: 620px;
		margin-top: 90px;
		font-size: 24px;
	}
</style>
</head>
<body>
<!-- Header -->
<div id="header">
	<div class="shell">
		<!-- Logo + Top Nav -->
		<div id="top">
			<h1><a href="#">NewsFocus</a></h1>
			<div id="top-navigation">
				Welcome <a href="#"><strong>Administrator</strong></a>
				<span>|</span>
				<a href="#">Help</a>
				<span>|</span>
				<a href="#">Profile Settings</a>
				<span>|</span>
				<a href="#">Log out</a>
			</div>
		</div>
		<!-- End Logo + Top Nav -->
	</div>
</div>
<!-- End Header -->

<!-- Container -->
<div id="container">
	<div class="shell">
		
		<!-- Small Nav -->
		<div class="small-nav">
			<a href="#">Dashboard</a>
			<span>&gt;</span>
			Welcome Page
		</div>
		<!-- End Small Nav -->	
		<br />
		<!-- Box -->
				<div class="box-content">
					<h1 class="main-title">Welcome To NewsFocus</h1>
					<h2 class="subtitle">To know more news in the world, to be better in your life</h2>
					<div class="enter-button">
						<span><a href="<s:url action='main' />">Watch News</a></span>
					</div>
				</div>
				<!-- End Box -->
	</div>
</div>
<!-- End Container -->

<!-- Footer -->
<div id="footer">
	<div class="shell">
		<span class="left">&copy; 2016 - Kjobs All Rights Reserved</span>
		<span class="right">
			Design by <a href="#" target="_blank" title="The Sweetest CSS Templates WorldWide">Kjobs</a>
		</span>
	</div>
</div>
<!-- End Footer -->
	
</body>
</html>