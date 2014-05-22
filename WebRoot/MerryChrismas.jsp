<%@ page language="java" import="java.util.*,com.data.vo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>圣诞节快乐</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
   	<link href="css/play.css" rel="stylesheet" type="text/css">
   	<link href="css/merrychristmas.css" rel="stylesheet" type="text/css">
   	<script type="text/javascript" src="js/jquery.js"></script>
   	<script type="text/javascript" src="js/play.js"></script>
   	<script type="text/javascript" src="js/mediaplayer.js"></script>

  </head>
  
  <script type="text/javascript">
  		
  		window.onload=init;
  		
  		function getCookie(name)
		{
 			var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
 
 			if(arr=document.cookie.match(reg))
  				return unescape(arr[2]);
 			else
  				return null;
		} 
		
		//需要初始化的内容
		function init()
		{
			var searchcontent = window.parent.document.getElementById("tagSearchInput").value;	
			if(searchcontent == "刘青" || searchcontent == "liuqing")
			{
				//window.alert("lq");
				document.getElementById("video").src = "http://player.youku.com/player.php/sid/XNjUxNTY4ODY0/v.swf";
			}
			if(searchcontent == "吴珂" || searchcontent == "wuke")
			{
				//window.alert("wk");
				document.getElementById("video").src = "http://player.youku.com/player.php/sid/XNjUyMTYzNDQ0/v.swf";
			}
			if(searchcontent == "陈安然" || searchcontent == "chenanran")
			{
				//window.alert("car");
				document.getElementById("video").src = "";
			}
			//var player = new musicplayer("song/Brenda Lee - Jingle Bell.ogg");
			//var username=getCookie('username');
			//get('inputEmail').value = username;
		}
  		
  </script>
  
  <body id="merrychristmasbody">
  
    <br><p id="title1">Merry Christmas!</p>
    <p id="title2">亲爱的，下面是我为你录的一段视频，祝你圣诞快乐，你是我的唯一<p>
    
    <div id="videodiv">
    	
    	<!--
    	<iframe id="video" height=498 width=510 frameborder=0 allowfullscreen></iframe>
    	-->
    	
    	
    	<embed id="video" allowFullScreen="true" quality="high" width="480" height="400" align="middle" allowScriptAccess="always" type="application/x-shockwave-flash">
    	</embed>
    	
    
    </div>
    <br>
  </body>
</html>
