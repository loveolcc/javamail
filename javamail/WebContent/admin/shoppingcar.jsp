<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
	body,html{
	padding:0;
	margin:0;
	}
	td{
	 font-size:18px;
	}
	.header{
	  position:relative;
	  width:100%;
	  height:80px;
	  background:#000;  
	}
	#caption{
	   position:absolute;
		height:100%;
		width:200px;
		left:50%;
		transform: translate(-100px);
		color:green;
		font-size:50px;
		line-height:80px;
	}
	#mask{
			background-color: #000;
			opacity: 0.75;
			height: 1000px;
			width: 100%;
			position: absolute;
			left: 0;
			top: 0;
			z-index: 100;
		}
		#login{
		 position:fixed;
		 top:30%;
		 left:35%;
		 z-index: 99999;
		}
		#loginCon{
		position:relative;
		    height: 300px;
			width: 450px;
			background:#fff;
			border-radius:10px;
		}
		#title{
			height:50px;
			width:100%;
			font-size:20px;
			color:#fff;
			line-height: 50px;
			text-indent:10px;
			background-color:rgba(255,48,48,0.7);
			border-radius:10px 10px 0 0;
		}
		#close{
		cursor:pointer;
		position:absolute;
		top:10px;
		right:10px;
		height:32px;
		width:32px;
		background:url(images/close.png);
		}
</style>
</head>
<body>
<div class="container">
</div>
<div id="mask"></div>
	<div id="login">
		<div id="loginCon">
			<div id="title">请输入您的信息</div>
			<div class="form-horizontal" role="form" id="myform" style="padding:5px 5px 0 0">
	  			<div class="form-group">
		   			<label for="receiverName" class="col-sm-2 control-label">收货人姓名</label>
		    		<div class="col-sm-10">
		      			<input type="text" class="form-control" id="receiverName" placeholder="请输入姓名">
		    		</div>
    			</div>
    			<div class="form-group">
		   			<label for="receiverAddress" class="col-sm-2 control-label">收货人地址</label>
		    		<div class="col-sm-10">
		      			<input type="text" class="form-control" id="receiverAddress" placeholder="请输入地址">
		    		</div>
    			</div>
    			<div class="form-group">
		   			<label for="telephone" class="col-sm-2 control-label">电话</label>
		    		<div class="col-sm-10">
		      			<input type="text" class="form-control" id="telephone" placeholder="请输入电话">
		    		</div>
    			</div>	
    			<div class="form-group">
    				<div class="col-sm-offset-2 col-sm-10">
			      		<button type="button" class="btn btn-danger btn-lg" id="reset">清空</button>
			      		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			      		<button type="button" class="btn btn-info btn-lg" id="sub">确定</button>
    				</div>
 			 	</div>
  			</div>
			<div id="close"></div>
		</div>
	</div>
	<script type="text/javascript">
	$("#login").hide();
	$("#mask").hide();
	$("#close").click(function(){
		$("#login").hide();
		$("#mask").hide();
	})
		$.ajax({
			type:"POST",
			url:"<%=request.getContextPath() %>/ShoppingCarServlet",
			data:{
				action:"showall"
			},
			success:function(data){
				if(data.indexOf('')==-1){
					alert(data);
					return;
				}
				var allPrice=0;
				var jsondata=JSON.parse(data);
				var html="<h2 align='center'>购物车</h2><table class='table table-bordered table-hover'><tr class='info'><th>书名</th><th>购买数量</th><th>单价</th><th>总价</th></tr>";
				for(var i=0;i<jsondata.length;i++){
					var name=jsondata[i].name;
					var buynum=jsondata[i].buynum;
					var price=jsondata[i].price;
					var pprice=parseInt(buynum)*parseInt(price);
					allPrice+=pprice;
					html+="<tr><td>"+name+"</td><td>"+buynum+"</td><td>"+price+"</td><td>"+pprice+"</td></tr>"
				}
				html+="</table><button id='buy' class='btn btn-primary btn-lg'>结算</button>";
				$(".container").html(html);
				$("#buy").click(function(){
					$("#login").show();
					$("#mask").show();
				})
				$("#sub").click(function(){
					var is=confirm("一共是"+allPrice+"元确认要买吗");
					if(!is){
						return;
					}
					$.ajax({
					type:"POST",
					url:"<%=request.getContextPath() %>/ShoppingCarServlet",
					data:{
						action:"buyall",
						receiverName:$("#receiverName").val(),
						receiverAddress:$("#receiverAddress").val(),
						telephone:$("#telephone").val(),
						allPrice:allPrice
					},
					success:function(data){
						alert(data);
						location.reload();
					}
					})
				})
			}
		})
	</script>
</body>
</html>