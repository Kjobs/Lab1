<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<title>Welcome to NewsFocus</title>
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
</head>
<body>
<!-- Header -->
<div id="header">
	<div class="shell">
		<!-- Logo + Top Nav -->
		<div id="top">
			<h1><a href="#">News Focus on Today</a></h1>
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
		
		<!-- Main Nav -->
		<div id="navigation">
			<ul>
			    <li><a href="<s:url action='main' />"><span>今日要闻</span></a></li>
			    <li><a href="<s:url action='technology' />"><span>科技</span></a></li>
			    <li><a href="<s:url action='education' />"><span>教育</span></a></li>
			    <li><a href="<s:url action='sports' />"><span>体育</span></a></li>
			    <li><a href="<s:url action='finance' />"><span>财经</span></a></li>
			    <li><a href="<s:url action='entertainment' />"><span>娱乐</span></a></li>
			    <li><a href="<s:url action='video' />"><span>视频</span></a></li>
			    <li><a href="<s:url action='car' />"><span>汽车</span></a></li>
			    <li><a href="<s:url action='game' />" class="active"><span>游戏</span></a></li>
			    <li><a href="<s:url action='health' />"><span>健康</span></a></li>
			    <li><a href="<s:url action='war' />"><span>军事</span></a></li>
			    <li><a href="<s:url action='movie' />"><span>影视</span></a></li>
			</ul>
		</div>
		<!-- End Main Nav -->
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
			Games
		</div>
		<!-- End Small Nav -->
		<!-- Main -->
		<div id="main">
			<div class="cl">&nbsp;</div>
			
			<!-- Content -->
			<div id="content">
				
				<!-- Box -->
				<div class="box">
					<!-- Box Head -->
					<div class="box-head">
						<h2 class="left">游戏要闻</h2>
					</div>
					<!-- End Box Head -->	

					<!-- Table -->
					<div class="table">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th>Title</th>
								<th>Date</th>
								<th>Source From</th>
							</tr>
							<s:iterator value="GameYaowenList">
							<tr>
								<td><h3><a href="<s:property value='Link' />"><s:property value='Title' /></a></h3></td>
								<td><s:property value="Date" /></td>
								<td><s:property value="Source" /></td>
							</tr>
							</s:iterator>
						</table>
						
					</div>
					<!-- Table -->
					
				</div>
				<!-- End Box -->
				<!-- Box -->
				<div class="box">
					<!-- Box Head -->
					<div class="box-head">
						<h2 class="left">游戏热点</h2>
					</div>
					<!-- End Box Head -->	

					<!-- Table -->
					<div class="table">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th>Title</th>
								<th>Date</th>
								<th>Source From</th>
							</tr>
							<s:iterator value="HotGamesList">
							<tr>
								<td><h3><a href="<s:property value='Link' />"><s:property value='Title' /></a></h3></td>
								<td><s:property value="Date" /></td>
								<td><s:property value="Source" /></td>
							</tr>
							</s:iterator>
						</table>
						
					</div>
					<!-- Table -->
					
				</div>
				<!-- End Box -->
			</div>
			<!-- End Content -->
			
			<!-- Sidebar -->
			<div id="sidebar">
				
				<!-- Box -->
				<div class="box">
					
					<!-- Box Head -->
					<div class="box-head">
						<h2>Management</h2>
					</div>
					<!-- End Box Head-->
					
					<div class="box-content">
						
						<!-- Sort -->
						<div class="sort">
							<label>Sort by</label>
							<select class="field">
								<option value="">Title</option>
							</select>
							<select class="field">
								<option value="">Date</option>
							</select>
							<select class="field">
								<option value="">Source</option>
							</select>
						</div>
						<!-- End Sort -->
						
					</div>
				</div>
				<!-- End Box -->
			</div>
			<!-- End Sidebar -->
			
			<div class="cl">&nbsp;</div>			
		</div>
		<!-- Main -->
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