<%@ page language="java" import="java.util.*,com.data.vo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'LabelJsp.jsp' starting page</title>
    
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
   	<script type="text/javascript" src="js/jquery.js"></script>
   	<script type="text/javascript" src="js/play.js"></script>
   	<script type="text/javascript" src="js/mediaplayer.js"></script>

  </head>
  
  <script type="text/javascript">
  	
  	function addtag(obj)
  	{
  		window.parent.randomPlay();
  		var labelchoosed = $(obj).text();
  		//alert(labelchoosed);
  		var searchcontent = window.parent.document.getElementById("tagSearchInput").value;		
  		//alert(searchcontent); 		
  		var pos = searchcontent.lastIndexOf('/');
  		
  		window.parent.document.getElementById("tagSearchInput").value = searchcontent.substr(0,pos+1)+labelchoosed;
		window.parent.document.getElementById("tagSearchInput").focus();
		window.parent.document.getElementById("remindInfo").text = labelchoosed;
  	}
  	
  	
  	//
  	var change_XmlHttpRequest = "";
  	
  	function getXmlHttpObject(){
		
		var xmlHttpRequest;
		//不同的浏览器获取对象xmlhttprequest 对象方法不一样
		if(window.ActiveXObject){
			
			xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
			
		}else{

			xmlHttpRequest=new XMLHttpRequest();
		}

		return xmlHttpRequest;

	}
  	
  	function change()
	{
		//alert("change1");
		change_XmlHttpRequest=getXmlHttpObject();
		if(change_XmlHttpRequest){
			var data =  "firstmatch=song";
			var url="/Jing/jing_servlet";
			change_XmlHttpRequest.open("post",url,true);
			change_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			//alert("change2");
			change_XmlHttpRequest.onreadystatechange=change_callback;
			//alert("change3");
			change_XmlHttpRequest.send(data); 
			//alert("change4");
		}
	}

	function change_callback(){		
		if(change_XmlHttpRequest.readyState==4){
			
			var res = "";
			if(change_XmlHttpRequest.status == 200)
			{
				res = change_XmlHttpRequest.responseText;
    			if(res == "yes")
    			{
    				//alert("change yes");

    				location.reload();
    			}
			}
		}
		
		
	}
  </script>
  
  <body>

        	
	<div id="labelBack">
   		<div id="labelGroups">
   		
   		
        	   			<!-- 同步匹配 -->
  	<% 
  		HttpSession hs = request.getSession(true);
		List labelstring = new ArrayList();
    	if(hs.getAttribute("labelstring") != null)
    	{
    		labelstring = (List)hs.getAttribute("labelstring");
    		for(int i=0; i<labelstring.size() && i<10; i++) {   	
    %>						
    			 <div class="labelEach">
        			<div id="" style="background:url(/songlist/<%=((Label)(labelstring.get(i))).getPicture()%>)" class="labelImg"></div>
                	<a id=" " class="searchTag" onclick="addtag(this)"><%=((Label)(labelstring.get(i))).getLabel()%></a>
        		</div>	
    <%		  						
    		}	    				
    		hs.removeAttribute("labelstring");	
    	}
	%>  
  	
  	<!-- 第一次匹配 -->
  	<% 
		List labellist = new ArrayList();
    	if(hs.getAttribute("labellist") != null)
    	{
    		labellist = (List)hs.getAttribute("labellist");
    		for(int i=0; i<labellist.size(); i++) {   	
    %>						
    			 <div class="labelEach">
        			<div id="labelImg1" class="labelImg" style="background:url(/songlist/<%=((Label)(labellist.get(i))).getPicture()%>)"></div><!-- id与图片有关 -->
                	<div class="searchTag" onclick="addtag(this)"><a class="tagFont"><%=((Label)(labellist.get(i))).getLabel()%></a></div>
        		</div>	
    <%		  						
    		}	    				
    		hs.removeAttribute("labellist");	
    	}
	%>  
	
	
        </div>
        
        <div id="changeTagDiv"><a id="changTagLab" onclick="change()">换一批试试</a></div>
   	</div>	
   </body>
</html>
