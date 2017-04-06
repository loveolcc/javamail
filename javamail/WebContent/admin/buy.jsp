<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.0/css/bootstrap-combined.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<title>...</title>
<style type="text/css">
	table{
	font-size:30px;
	}
	tr{
	line-height:60px;
	}
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
<%
  Object email=session.getAttribute("email");
	if(email==null){
		email="登陆";
	}
%>
	<header>
			<div id="logo">
				<img src="images/logo.png">
			</div>
			<div id="bnav">
				<ol class="breadcrumb">
				    <li><a href="shoppingcar.jsp"><span class="glyphicon glyphicon-shopping-cart"></span>购物车</a></li>
				    <li><a href="#">帮助中心</a></li>
				    <li><a href="login.jsp"><%=email %></a></li>
				    <li><a href="regist.jsp">新用户注册</a></li>
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
		<div id="bid" style="display:none;"><%=request.getParameter("bid") %></div>
		<div class="row">
			<div class="col-md-4">
				<img alt="" src="" id="bookImg">
			</div>
			<div class="col-md-4">
				<table>
					<tr>
						<td class="text-danger">书名:&nbsp;&nbsp;</td>
						<td id="bookName"></td>
					</tr>
					<tr>
						<td class="text-danger">价格:</td>
						<td id="price"></td>
					</tr>
					<tr>
						<td class="text-danger" class="ttitle">类别:&nbsp;&nbsp;</td>
						<td id="category"></td>
					</tr>
					<tr>
						<td class="text-danger">库存:&nbsp;&nbsp;</td>
						<td id="pnum"></td>
					</tr>
					<tr>
						<td class="text-danger">简介:&nbsp;&nbsp;</td>
						<td id="description"></td>
					</tr>
					<tr><td><br></td></tr>
					<tr>
						<td colspan="2"><button class="btn btn-info btn-lg" onclick="addToShoppingCar()">购买</button></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
		<footer>
		<div id="bottomlogo">
			<img src="images/logo.png">
		</div>
	</footer>
	<script type="text/javascript">
		$.ajax({
			type:"GET",
			url:"<%=request.getContextPath() %>/BookSeachServlet",
			data:{
				bid:$("#bid").text(),
				method:"id"
			},
			success:function(data){
				var jsondata=JSON.parse(data);
				var name=jsondata.name;
				var price=jsondata.price;
				var description=jsondata.desciption;
				var pnum=jsondata.pnum;
				var imgurl=jsondata.imgurl;
				var category=jsondata.category;
				$("#bookImg").prop("src",imgurl);
				$("#bookName").text(name);
				$("#price").text("$"+price);
				$("#category").text(category);
				$("#pnum").text(pnum);
				$("#description").text(description);
			}
		})
		function addToShoppingCar(){
			var buynum=prompt("请输入购买数量", "");
			$.ajax({
				type:"GET",
				url:"<%=request.getContextPath() %>/ShoppingCarServlet",
				data:{
					bid:$("#bid").text(),
					buynum:buynum,
					action:"toshoppingcar"
				},
				success:function(data){
					alert(data);
				}
			})
		}
	</script>
</body>
</html>