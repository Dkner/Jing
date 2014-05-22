<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>JING 今天你听了没</title>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap-responsive.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/login_register.css" rel="stylesheet" type="text/css">
     
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/login_register.js"></script>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  
  <!-- 嵌入js代码 -->
  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <script type="text/javascript">
  	$(document).ready(function()
		{
  			$("#loginBtn").click(showLogFrame);
  			$("#regisBtn").click(showRegFrame);
		});
			
  
  	var Regis_XmlHttpRequest="";
  	var login_XmlHttpRequest="";
  	
  	//创建ajax引擎
	function getXmlHttpObject(){
		
		var xmlHttpRequest;
		//不同的浏览器获取对象xmlhttprequest 对象方法不一样
		if(window.ActiveXObject){
			
			xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
			
		}else{

			//window.alert("firefox");
			xmlHttpRequest=new XMLHttpRequest();
		}

		//window.alert("create ajax");
		return xmlHttpRequest;

	}
	
	function register(){
		var data;
		//跳转到servlet验证	
  		//window.location.href='/Jing/jing_servlet?'+data;		
  		Regis_XmlHttpRequest=getXmlHttpObject();
		if(Regis_XmlHttpRequest){
			data =  "RegisEmail=" + get('RegisEmail').value + "&RegisPassword="+get('RegisPassword').value;
			var url="/Jing/jing_servlet";
			Regis_XmlHttpRequest.open("post",url,true);
			Regis_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			Regis_XmlHttpRequest.onreadystatechange=register_callback;
			Regis_XmlHttpRequest.send(data); 
		}
	}
	
	function register_callback(){		
		if(Regis_XmlHttpRequest.readyState==4){
			
			var res = "";
			if(Regis_XmlHttpRequest.status == 200)
			{
				res = Regis_XmlHttpRequest.responseText;
				if(res == "email occupied")
    				get('RegisEmail').value = res;
    			if(res == "success")
    			{
    				//
    				window.location.href='/Jing/MainView.jsp';	
    			}
			}
		}	
	}
	
  	function login(){	
  		var data;
		//跳转到servlet验证	
  		//window.location.href='/Jing/jing_servlet?'+data;		
  		login_XmlHttpRequest=getXmlHttpObject();
		if(login_XmlHttpRequest){
			data =  "username=" + get('LoginEmail').value + "&password="+get('LoginPassword').value;
			var url="/Jing/jing_servlet";
			login_XmlHttpRequest.open("post",url,true);
			login_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			login_XmlHttpRequest.onreadystatechange=login_callback;
			login_XmlHttpRequest.send(data); 
		}
	}
	
	function login_callback(){		
		if(login_XmlHttpRequest.readyState==4){
			
			var res = "";
			if(login_XmlHttpRequest.status == 200)
			{
				res = login_XmlHttpRequest.responseText;
				if(res == "wrong")
    				get('LoginEmail').value = res;
    			if(res == "correct")
    			{
    				//
    				window.location.href='/Jing/MainView.jsp';	
    			}
			}
		}	
	}
	
	function get(id){
		return document.getElementById(id);
	}
  </script>
  
  
  
  
  <body>
  <%
		String name = "";
		String password = "";
  		
  		HttpSession hs = request.getSession(true);
  		if(hs.getAttribute("username") != null)
  		{
  			response.sendRedirect("/Jing/MainView.jsp");
  			return;
  		}
  		
   %>
    <!--  <div class="header">Welcome to the jing</div>-->
    <div id="login_page">
 		<div id="mainFrame" class="myrow">
 			<div id="theisName">
 				<div class="span4 offset1">
 					 <img src="./img/jing3.png" /></img>
 				</div>
 			</div>
 			
 			
 			<div  id="forms" class="row">
 			<div id="loginDiv" class=" span4 offset3  layoutForm hide" >
 				<p><strong>欢迎登入</strong></p>
 			 	<form id="loginForm" class="form-horizontal" action="">
 			 		<div class="input-prepend">
 						 <span class="add-on">Email&nbsp;&nbsp;</span>
  						 <input class="span2" id="LoginEmail" type="text">
					</div>
					<div><br></div>
					<div class="input-prepend">
						 <span class="add-on">Password</span>
  						 <input class="span2" id="LoginPassword" type="password">
  			        </div>
  			        <div><br></div>
                    <div class="span2 offset1">
						<button id="" class="btn btn-info" type="button" onclick="login();">登入</button>
					</div>
 			 	</form>	
 			</div>
 			
 			<div id="regisDiv" class=" span4 offset8 layoutForm hide">
 				<p><strong>欢迎注册</strong></p>
 				<form id="regisForm" class="form-horizontal" action="#">
 					<div class="input-prepend">
 						 <span class="add-on">Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
  						 <input class="span2" id="RegisEmail" type="text"> 						
					</div>
					<div><br></div>
					<div class="input-prepend">
 						 <span class="add-on">Password</span>
  						 <input class="span2" id="RegisPassword" type="password"> 
					</div>
					<div><br></div>
					<div class="span2 offset1">
						<button class="btn btn-primary" type="button" onclick="register();">注册</button>
					</div>
 				</form>
 			</div>
 		</div>
 		</div>
 	</div>	
 	<div class="span12 footer">
 		<div class="row">
 			<div class="span2 offset6">
 				<button id="loginBtn" class="btn btn-large" data-toggle="tooltip" data-placement="top" title="登入" ><img class="icon-info-sign"></img></button>
 			</div>
 			<div class="span2">
 				<button id="regisBtn" class="btn btn-large" data-toggle="tooltip" data-placement="top"  title="注册"><img class="icon-user"></img></button>
 			</div>
 			<div id="forgetPsd" class="span2">
 				<br>
 				<!-- 
 				<a href="">忘记密码？</a>
 				 -->
 			</div>
 		</div>
 	</div>
 	
 	
  </body>
</html>
