<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
</head>
<body>
	
	<h2> 欢迎， ${currUser.username}</h2>
	<p><a href="logout">注销</a></p>
	<H3>sessionId= ${pageContext.request.session.id}</H3>
	<h2>
	请求中的cookie:<br>
		<%
			Cookie[] coos= request.getCookies();
			if(coos != null){
				for(Cookie c: coos){
					out.println(c.getName()  + "=" + c.getValue() + "<br>");
				}
			}
		%>
	</h2>
</body>
</html>