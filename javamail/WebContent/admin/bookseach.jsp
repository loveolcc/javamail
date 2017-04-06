<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
	<div id="book"></div>
	<input type="text" id="bookName">
	<button onclick="submit()">11</button>
	<script type="text/javascript">
		function submit(){
			$.ajax({
				type:"POST",
				url:"<%=request.getContextPath() %>/BookSeachServlet",
				data:{
					bookName:$("#bookName").val(),
					method:"name"
				},
				success:function(data){
					var jsondata=JSON.parse(data);
					if(jsondata[0]==null){
						alert("不好意思，没有此书的相关信息");
						return;
					}
					alert(jsondata[0].bid);
				}
			})
		}
	</script>
</body> 
</html>