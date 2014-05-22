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

  </head>
  
  <body>
  <p>匹配标签</p>
  <br/>
  	<div>
  	<!-- 同步匹配 -->
  	<% 
  		HttpSession hs = request.getSession(true);
		String[] labelstring;
    	if(hs.getAttribute("label") != null)
    	{
    		labelstring = (String[])hs.getAttribute("label");
    		for(int i=0; i<labelstring.length; i++) {   	
    %>						
    			 <p><%=labelstring[i]%></p>		
    <%		  						
    		}	    				
    		hs.removeAttribute("label");	
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
    			 <p><%=((Label)(labellist.get(i))).getLabel()%></p>		
    <%		  						
    		}	    				
    		hs.removeAttribute("labellist");	
    	}
	%>  
	</div>
    
  </body>
</html>
