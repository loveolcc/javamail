<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.0/css/bootstrap-combined.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
	header{
			position: relative;
			height: 90px;
			border:1px solid #ccc;
			background-color: rgba(139,129,76,0.125);
		}
		#logo{
			position: absolute;
			left: 130px;
			top: 10px;
		}
		#bnav{
			position: absolute;
			right: 15px;
			top: 9px;
		}
		#search{
			position: absolute;
			right: 15px;
			top: 40px;
		}
		.nav-collapse li{
			padding: 0px;
			width: 60px;
		}
		#divcontent{
			margin-left: 130px;
		}
		footer{
			position: relative;
			height: 100px;
		}
		#bottomlogo{
			position: absolute;
			left: 150px;
			top: 30px;
		}
		.container{
			padding:0px;
		}
</style>
</head>
<body>
	<header>
			<div id="logo">
				<img src="images/logo.png">
			</div>
			<div id="bnav">
				<ol class="breadcrumb">
				    <li><a href="shoppingcar.jsp"><span class="glyphicon glyphicon-shopping-cart"></span>购物车</a></li>
				    <li><a href="#">帮助中心</a></li>
				    <li><a href="login.jsp">登陆</a></li>
				    <li><a href="regis.jsp">新用户注册</a></li>
				</ol>
			</div>
			<nav id="search" class="navbar navbar-default" role="navigation">
		   <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default">提交</button>
        </form>
        </nav>
	</header>
	<div class="container">
	<div class='navbar navbar-inverse' style="margin-bottom: 5px;">
	  		<div class='nav-collapse' style="height: auto;">
			    <ul class="nav" style="font-size: 15px;">
			      <li><a href="#">文学</a></li>
			      <li><a href="#">生活</a></li>
			      <li style="width: 75px;"><a href="#">计算机</a></li>
			      <li><a href="#">外语</a></li>
			      <li><a href="#">经管</a></li>
			      <li><a href="#">励志</a></li>
			       <li><a href="#">社科</a></li>
			      <li><a href="#">学术</a></li>
			      <li><a href="#">少儿</a></li>
			      <li><a href="#">艺术</a></li>
			      <li><a href="#">原版</a></li>
			      <li><a href="#">科技</a></li>
			      <li><a href="#">考试</a></li>
			      <li style="width: 90px;"><a href="#">生活百科</a></li>
			      <li style="width: 120px;"><a href="books.jsp">全部商品目录</a></li>
			    </ul> 
			</div>
		</div>
	<form action="<%=request.getContextPath() %>/LoginServlet" method="post">
		<table border="1">
			<tr>
				<td>用户名:</td>
				<td><input type="text" name="username"/></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="text" name="password"/></td>
			</tr>
			<tr>
				<td><input type="text" name="dcode"/></td>
				<td><img alt="" src="<%=request.getContextPath() %>/ImageServlet"></td>
			</tr>
			<tr>
				<td><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
	</div>
	<footer>
		<div id="bottomlogo">
			<img src="images/logo.png">
		</div>
	</footer>
</body>
</html>