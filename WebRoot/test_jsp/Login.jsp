<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  
  <!-- 嵌入js代码 -->
  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <script type="text/javascript">
  
  	var myXmlHttpRequest="";
  	//var myXmlHttpRequest1="";
  	
  	//创建ajax引擎
	function getXmlHttpObject(){
		
		var xmlHttpRequest;
		//不同的浏览器获取对象xmlhttprequest 对象方法不一样
		if(window.ActiveXObject){
			
			xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
			
		}else{

			window.alert("firefox");
			xmlHttpRequest=new XMLHttpRequest();
		}

		//window.alert("create ajax");
		return xmlHttpRequest;

	}
	
  	function login(){
  		var data;
  		//myXmlHttpRequest = getXmlHttpObject();
  		if($('cookie').checked == true)
			data =  "username=" + $('username').value + "&password="+$('password').value+"&cookie=true";
		else
			data =  "username=" + $('username').value + "&password="+$('password').value;
		//跳转到servlet验证	
  		//window.location.href="/Jing/jing_servlet?"+data;
  		window.alert("firefox");
  		//window.location = "/Jing/jing_servlet?"+data;
  		window.location.href='/Jing/jing_servlet?'+data;
	}
	
	function $(id){
		return document.getElementById(id);
	}
  </script>
  
  
  
  
  <body>
  <%
		String name = "";
		String password = "";
  		/*
  		Cookie[] allcookies = request.getCookies();
  		if(allcookies != null)
  		{
  			for(int i=0;i<allcookies.length;i++)
  			{
  				Cookie temp = allcookies[i];
  				if(temp.getName().equals("username"))
  				{
  					name = temp.getValue();
  				}
  				if(temp.getName().equals("password"))
  				{
  					password = temp.getValue();
  				}
  			}
  			
  			//得到cookie中的name和password再交给servlet验证
  			if(!name.equals("") && !password.equals(""))
  			{
  				response.sendRedirect("/Jing/jing_servlet?username="+name+"&password="+password);
  				return;
  			}
  		}
  		*/
  		
  		HttpSession hs = request.getSession(true);
  		if(hs.getAttribute("username") != null)
  		{
  			response.sendRedirect("/Jing/test_jsp/TestJsp.jsp");
  			return;
  		}
  		
   %>
    This is Login page. <br>
    
    <form id="loginform">
	用户名称：<input type="text" name = "name" id ="username">
	<br/>
	用户密码：<input type="text" name = "pw" id = "password">
	<br/>
    <input type="button" onclick="login();" value="登录"><input type="checkbox" id="cookie">保留cookie
	</form>
  </body>
</html>
