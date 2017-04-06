<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.0/css/bootstrap-combined.min.css" rel="stylesheet">
	<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
	<link type="text/css" href="css/autoplay.css" rel="stylesheet" />
	<script type="text/javascript" src="js/autoplay.js"></script>
	<title>网上书店</title>
	<style type="text/css">
	  *{margin:0;padding:0;}
    ul,li,img{margin:0;padding:0;border:0;list-style-type:none;}
    #list{height:250px;list-style-type:none;overflow:hidden;}
    #luanpo{max-width:900px;height:335px;border:1px solid #CCC;margin:0 auto;position:relative;overflow:hidden;}

    #imgs li{float:left;height:335px;width:900px;}
    #imgs{height:335px;background:#ddd;position:absolute;}
    .a{background:red;}
    .b{background:yellow;}

    #num{overflow:auto;position:absolute;right:0;bottom:0;}
    #num li{float:left;height:30px;width:30px;text-align:center;line-height:30px;border:1px solid #CCC;margin-left:10px;cursor:pointer;z-index:2222;}
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
	<div id="content">
	 <div id="luanpo">
        <ul id="imgs">
           <li><img src="ad/index_ad1.jpg" width="900" height="335" /></li>
           <li><img src="ad/index_ad2.jpg" width="900" height="335" /></li>
           <li><img src="ad/index_ad3.jpg" width="900" height="335" /></li>
           <li><img src="ad/index_ad4.jpg" width="900" height="335" /></li>
           <li><img src="ad/index_ad5.jpg" width="900" height="335" /></li>
       </ul>
        <ul id="num">
            <li class="a">1</li>
            <li class="b">2</li>
            <li class="b">3</li>
            <li class="b">4</li>
        </ul>
    </div>
    </div>
    </div>
	<footer>
		<div id="bottomlogo">
			<img src="images/logo.png">
		</div>
	</footer>
<script type="text/javascript">
	   var x=0;
		$.ajax({
			type:"GET",
			url:"<%=request.getContextPath() %>/AllBooksServlet",
			success:function(data){
				var jsondata=JSON.parse(data);
				var html="<div class='row'>";
				for(var i=0;i<jsondata.length;i++){
					if(i%4==0&&i!=0){
						html+="</div><div class='row'><div class='book col-md-3' bid='"+jsondata[i].bid+"'><img src='"+jsondata[i].imgurl+"'><h3>$<b class='text-danger'>"+jsondata[i].price+"</b>&nbsp;&nbsp;"+jsondata[i].name+"<h3></div>";
						x++;
					}else{
					html+="<div class='book col-md-3' bid='"+jsondata[i].bid+"'><img src='"+jsondata[i].imgurl+"'><h3>$<b class='text-danger'>"+jsondata[i].price+"</b>&nbsp;&nbsp;"+jsondata[i].name+"<h3></div>" 
					x++;
					}
				}
				html+="</div>";
				$("#content").html(html);
				$(".book").click(function(){
				window.location("buy.jsp?bid="+$(this).attr("bid")+"");
				})
			}
		})
	</script>
</body>
</html>