<%@ page language="java" import="java.util.*,com.data.vo.*,com.process.model.Page" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>音乐评论</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap-responsive.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/play.css" rel="stylesheet" type="text/css">
	<link href="css/star.css" rel="stylesheet" type="text/css">
    
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/star.js"></script>	
	<script type="text/javascript" src="js/play.js"></script>
	<script type="text/javascript" src="js/musiccontrol.js"></script>
	
  </head>
  
  <script type="text/javascript">
  
  	//window.onload=init;
  	
  	function init()
  	{
  		this.location.reload();
  		//alert("refresh");
  	}
  	
var infoXmlHttpRequest = "";
var keyXmlHttpRequest = "";
  	
  	function givecomment()
{
	//alert("1");
	givcomXmlHttpRequest=getXmlHttpObject();
	if(givcomXmlHttpRequest){
		//alert("2");
		var data =  "comment="+get('comment').value;
		var url="/Jing/jing_servlet";
		givcomXmlHttpRequest.open("post",url,true);
		givcomXmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		givcomXmlHttpRequest.onreadystatechange=givecomment_callback;
		givcomXmlHttpRequest.send(data);
		//alert("3");
	}
}

function givecomment_callback()
{
	if(givcomXmlHttpRequest.readyState==4){
		if(givcomXmlHttpRequest.status == 200)
		{
			//alert("4");
			var result = givcomXmlHttpRequest.responseText;	
			init();
		}
	}
}

function givelevel(n)
{
	on_click(n);
	//alert("1");
	givlevXmlHttpRequest=getXmlHttpObject();
	if(givlevXmlHttpRequest){
		//alert(score);
		var data =  "level="+score;
		var url="/Jing/jing_servlet";
		givlevXmlHttpRequest.open("post",url,true);
		givlevXmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		givlevXmlHttpRequest.onreadystatechange=givelevel_callback;
		givlevXmlHttpRequest.send(data);
		//alert("3");
	}
}

function givelevel_callback()
{
	if(givlevXmlHttpRequest.readyState==4){
		if(givlevXmlHttpRequest.status == 200)
		{
			//alert("4");
			var result = givlevXmlHttpRequest.responseText;	
			init();
		}
	}
}


	var naviXmlHttpRequest = "";
function nextorback(type)
{
	//alert("1");
	naviXmlHttpRequest=getXmlHttpObject();
	if(naviXmlHttpRequest){
		//alert("2");
		var data =  type+"=song";
		var url="/Jing/jing_servlet";
		naviXmlHttpRequest.open("post",url,true);
		naviXmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		naviXmlHttpRequest.onreadystatechange=nextorback_callback;
		naviXmlHttpRequest.send(data);
		//alert("3");
	}
}

function nextorback_callback()
{
	if(naviXmlHttpRequest.readyState==4){
		if(naviXmlHttpRequest.status == 200)
		{
			//alert("4");
			init();
		}
	}
}

  	
  	
  	function test()
  	{
  		alert("test");
  	}
  </script>
  
  <body>

<%--  	<% --%>
<%--  		HttpSession hs = request.getSession(true);--%>
<%--  		Page currentpage = new Page();--%>
<%--  		if(hs.getAttribute("page") != null)--%>
<%--			currentpage = (Page)hs.getAttribute("page");   --%>
<%--		else--%>
<%--			return;	--%>
<%--	%>  --%>
        	
	 <div id="commentDiv">
        	<div id="headComment">
            	<div id="nameAndImg">
        			<div id="albumImg"></div>
        					<a>123</a>
<%--        			<a><%=currentpage.songname%></a>--%>
        		</div>
                <div id="scoresAndTagsDiv">
					<!--*******************************************************************************************************************-->
                	<span id="stars0">
							<img id="star_1" value="1" src="img/nst.gif"/>
							<img id="star_2" value="2" src="img/nst.gif"/>
							<img id="star_3" value="3" src="img/nst.gif"/>
							<img id="star_4" value="4" src="img/nst.gif"/>
							<img id="star_5" value="5" src="img/nst.gif"/>
					</span>
					<!--*******************************************************************************************************************-->
                    <div id="tagGroups">
<%--                 <% --%>
<%--                 	--%>
<%--                    for(int i=0; i<currentpage.get_taglist().size()&&i<6; i++) {   	--%>
<%--   				 %>			--%>
<%--                    	<div class="tagList"><%=((Tag)(currentpage.get_taglist().get(i))).getLabel().getLabel()%></div>--%>
<%--                 <%--%>
<%--                  	}--%>
<%--                 %>      --%>
                    </div>
                </div>
            </div>
            <br>
			<!--********************************************************************************************************************************-->
			<div id="rating" onmouseout="mOut()">
            评价:
                <span id="stars">
                <a>
                    <img id="star1" value="1" onmouseover="mOver(1)" onclick="givelevel(1)" src="img/nst.gif"/>
                </a>
                <a>
                    <img id="star2" value="2" onmouseover="mOver(2)" onclick="givelevel(2)" src="img/nst.gif"/>
                </a>
                <a>
                    <img id="star3" value="3" onmouseover="mOver(3)" onclick="givelevel(3)" src="img/nst.gif"/>
                </a>
                <a>
                    <img id="star4" value="4" onmouseover="mOver(4)" onclick="givelevel(4)" src="img/nst.gif"/>
                </a>
                <a>
                    <img id="star5" value="5" onmouseover="mOver(5)" onclick="givelevel(5)" src="img/nst.gif"/>
                </a>
                </span>
            <input id="n_rating" type="hidden" value="">
            </div>
            <!--********************************************************************************************************************************-->
            <div id="commendGroups"><!-- 修改地方 -->
            	<!--<div id="" class="">
                  		<textarea id="comment" type="text" class="large" placeholder="评论"></textarea>
                  		<div class="saveOrcCanBtn">
                     		<button id="###" class="btn" onclick="givecomment()">发送</button>          
                  		</div>        
           		</div>-->
           		<form id="commentForm" class="" action="#">
                  		<textarea id="commentTextArea"type="text" class="large" placeholder="评论"></textarea>
                  		<div class="saveOrcCanBtn">
                     		<button id="###" class="btn btn-info">发送</button>
                      	 	<button id="###" class="btn">取消</button>
                  		</div>        
           		</form>
           		
<%--           	<%                	--%>
<%--                for(int i=0; i<currentpage.get_pagelist().size(); i++) {   	--%>
<%--                	String level = ((Assess)(currentpage.get_pagelist().get(i))).getLevel();--%>
<%--                	String comment = ((Assess)(currentpage.get_pagelist().get(i))).getComment();--%>
<%--   			%>	--%>
   			<!-- 修改过 -->
   				<div id="othersComment">
					<div class="commentDivBox">
						<a class="">Amy</a>:<a>真好听</a>
					</div>	
					<div class="commentDivBox">
						<a class="">Jack</a>:<p>真难听</p>
					</div>	
					<div class="commentDivBox">
						<a class="">Jackie_9692</a>:<a>歌曲旋律真不错，大爱！！！</a>
					</div>	
				</div>
<%--            	<!-- <div class="commentDiv">--%>
<%--                	<a class=""><%=((Assess)(currentpage.get_pagelist().get(i))).getUser().getName()%></a>--%>
<%--                	<a class=""><%=((Assess)(currentpage.get_pagelist().get(i))).getTime()%></a>--%>
<%--                	--%>
<%--                <%--%>
<%--                	if(level != null)--%>
<%--                	{--%>
<%--                %>               	               	--%>
<%--                		<br/>--%>
<%--                		<a>评分： <%=level%></a>--%>
<%--                <%	--%>
<%--                	} --%>
<%--                	if(comment != null)--%>
<%--                	{--%>
<%--                %>	--%>
<%--                		<br/>--%>
<%--                		<a>评论： <%=comment%></a>--%>
<%--                <%  } %>	--%>
<%--                --%>
<%--                </div>	--%>
<%--                --%>
<%--            <%--%>
<%--                 }--%>
<%--            %>        --%>
<%--                 -->--%>

            </div>
            
 
     	<div id=navi>
     		<a onclick="nextorback('backcommend')"> back </a>&nbsp&nbsp&nbsp&nbsp
     		<a onclick="nextorback('nextcommend')"> next </a>
  		</div>
    
    
    
     </div> 
   </body>
</html>
