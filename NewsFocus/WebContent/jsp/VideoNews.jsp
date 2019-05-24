<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<title>Welcome to NewsFocus</title>
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
	<style type="text/css">
.XYTipsWindow .boxLoading { position: absolute; display: block; width: 90px; height: 30px; line-height: 30px; margin-left: -45px; margin-top: -15px; left: 50%; top: 50%; color: #f00; }
.XYTipsWindow .boxLoading { width:28px; margin-left: -14px; background:url("images/loading.gif") no-repeat; text-indent: -999em; text-align: center; }
.XYTipsWindow .boxTitle, .XYTipsWindow .boxTitle span, .XYTipsWindow .boxTitle span.hover, .XYTipsWindow .loadinglayer, .XYTipsWindow .tipslayer, .XYTipsWindow .arrowLeft, .XYTipsWindow .colseBtn, .XYTipsWindow .boxError em, .XYTipsWindow .dialogBtn, .XYTipsWindow .dialogBtn.hover { background-image: url("images/ico.png"); background-repeat: no-repeat; }
.XYTipsWindow .boxTitle { position: relative; border: 1px solid #A6C9E1; border-bottom: none; background-position: 0 0; background-repeat: repeat-x; height: 30px; line-height: 30px; }
.XYTipsWindow .boxTitle h3 { float: left; font-weight: normal; color: #666; font-size: 14px; margin: 0; text-indent: 10px; }
.XYTipsWindow .boxTitle span { position: absolute; width: 10px; background-position: -80px -40px; text-indent: -10em; right: 10px; top: 10px; height: 16px; overflow: hidden; cursor: pointer; }
.XYTipsWindow .boxTitle span.hover { background-position: -90px -40px; }
.XYTipsWindow .loadinglayer { line-height: 40px; background-position: 0 -100px!important; }
.XYTipsWindow .tipslayer { line-height: 20px; text-align: left; }
.XYTipsWindow .arrowLeft { position: absolute; width: 8px; height: 16px; background-position: -20px -170px; text-indent: -9999em; z-index: 20591; overflow: hidden; }
.XYTipsWindow .colseBtn { position: absolute; top: 5px; right: 5px; width: 8px; height: 8px; background-position: -55px -170px; text-indent: -9999em; cursor: pointer; z-index: 20591; overflow: hidden; }
.XYTipsWindow .boxError { position: absolute; left: 50%; top: 50%; margin-left: -60px; margin-top: -15px; width: 120px; height: 30px; line-height: 30px; color: #f00; }
.XYTipsWindow .boxError em { float: left; width:30px; height: 30px; background-position: -35px -140px; }
.XYTipsWindow .dialogBtn { margin: 5px 5px 0 0; width:80px; height:35px; background-position: 0 -30px; border:none; color:#333; }
.XYTipsWindow .dialogBtn.hover { background-position: 0 -65px; }
.XYTipsWindow.shadow { box-shadow:2px 2px 5px #C0BBB5; -moz-box-shadow: 2px 2px 5px #C0BBB5; -webkit-box-shadow:2px 2px 5px #C0BBB5; }
</style>
 <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
 <script type="text/javascript" src="js/jquery.XYTipsWindow.min.2.8.js"></script>

 <script type="text/javascript">
 $(document).ready(function() {	
	$("#reg").click(function(){
		$.XYTipsWindow({
			___title:"注册",
			___content:"iframe:reg.html",
			___width:"460",
			___height:"250",
			___showbg:true,
			___drag:"___boxTitle"
		});
	});
 })
 </script>
</head>
<body>
<!-- Header -->
<div id="header">
	<div class="shell">
		<!-- Logo + Top Nav -->
		<div id="top">
			<h1><a href="#">News Focus on Today</a></h1>
			<div id="top-navigation">
				Welcome <a href="#"><strong>${Guser}</strong></a>
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
			    <li><a href="<s:url action='video' />" class="active"><span>视频</span></a></li>
			    <li><a href="<s:url action='car' />"><span>汽车</span></a></li>
			    <li><a href="<s:url action='game' />"><span>游戏</span></a></li>
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
			Video
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
						<h2 class="left">热点视频</h2>
					</div>
					<!-- End Box Head -->	

					<!-- Table -->
					<div class="table">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th>Title</th>
								<th>Date</th>
								<th>Source From</th>
								<th width="90" class="ac">Collect News</th>
							</tr>
							<s:iterator value="EntList">
							<tr>
								<td><h3><a href="<s:property value='Link' />"><s:property value='Title' /></a></h3></td>
								<td><s:property value="Date" /></td>
								<td><s:property value="Source" /></td>
								<td>关注&nbsp;&nbsp;&nbsp;<a href="#" class="ico del" onclick="this.innerHTML=parseInt(this.innerHTML)+1;">
									<s:property value='LikeNum' /></a></td>
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
						<h2 class="left">视频排行榜</h2>
					</div>
					<!-- End Box Head -->	

					<!-- Table -->
					<div class="table">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th>Title</th>
								<th>Date</th>
								<th>Source From</th>
								<th width="90" class="ac">Collect News</th>
							</tr>
							<s:iterator value="VideoList">
							<tr>
								<td><h3><a href="<s:property value='Link' />"><s:property value='Title' /></a></h3></td>
								<td><s:property value="Date" /></td>
								<td><s:property value="Source" /></td>
								<td>关注&nbsp;&nbsp;&nbsp;<a href="#" class="ico del" onclick="this.innerHTML=parseInt(this.innerHTML)+1;">
									<s:property value='LikeNum' /></a></td>
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
							<label>SORT BY</label>
							<form action="video.action" method="post">   
        						<input type="submit" name="OrderType" value="标题"><br><br>
        						<input type="submit" name="OrderType" value="来源"><br><br>   
        						<input type="submit" name="OrderType" value="日期"><br><br>
        						<input type="submit" name="OrderType" value="关注度"><br><br>   
    						</form> 
						</div>
						<!-- End Sort -->
						
					</div>
				</div>
				<!-- End Box -->
				<!-- Box -->
				<div class="box">
					
					<!-- Box Head -->
					<div class="box-head">
						<h2>Management</h2>
					</div>
					<!-- End Box Head-->
					
					<div class="box-content">
						<form action="login.action" method="post" class="loginform">  
							   用户:<input type="text" name="username"><br><br>  
							   密码:<input type="password" name="password"><br><br>  
							 <input type="submit" value="登录" class="login"><br><br> 
						</form>
						<button id="reg">注册</button>
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