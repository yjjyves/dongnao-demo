<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<title>登录</title>

</head>
<body>
	<br>
    <div class="container">
        <div class="row">
            <div class="col-md-offset-3 col-md-6">
                <form class="form-horizontal" action="doLogin">
                    <h2 class="heading">用户登录</h2>
                    <div class="form-group">
                        <input type="text" class="form-control" id="username" placeholder="用户名或电子邮件" name="username">
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="pwd" placeholder="密　码" name="pwd">
                        <i class="fa fa-lock"></i>
                        <a href="#" class="fa fa-question-circle"></a>
                    </div>
    
                    <div class="form-group">
                        <label for="checkbox1"></label>
                        <button type="submit" class="btn btn-primary">登录</button>
                    </div>
                </form>
            </div>
        </div>
        
		<h2>
		所属会话sessionId= <%=session.getId() %>
		</h2>
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
    </div>

</body>
</html>