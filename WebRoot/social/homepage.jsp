<%@ page language="java" import="java.util.*,com.data.vo.*,com.process.model.Page" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
try{
 HttpSession hs = request.getSession(true);
 if(hs.getAttribute("username") == null)
 {
	 response.sendRedirect("/Jing/Login.jsp");
	 return;
 }

 String flag = (String)request.getAttribute("isLegal");
 if(flag == null || !flag.equals("legal"))
 {
 	response.sendRedirect("/Jing/jing_servlet?homepage=music");
 	return;
 }
 else
 	request.removeAttribute("isLegal");

 User user = (User) request.getAttribute("user");
 List<Tagrecord> rankingsongs = (List<Tagrecord>)request.getAttribute("records");
 Page recordpage = (Page)request.getAttribute("recordpage");
 %>

<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="social/css/homepage.css">
	<link href="social/css/bootstrap.css" rel="stylesheet">
    <link href="social/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="social/css/main-css.css" rel="stylesheet">
    <link href="social/css/mymusic_component-css.css" rel="stylesheet">
    <link href="social/css/tab_component-css.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script src="social/js/js-dashboard.js"></script>
	<style type="text/css">

		#header{   text-align: left;  zoom:1;  z-index:10; }
		#header .wrapper,#secondary .wrapper,#footer .wrapper{
			_width: expression(documentElement.clientWidth < 980? "980px" : documentElement.clientWidth > 1100? "1100px" : "auto");
		}

		.ie6 #secondary a.current{ background: none;}
		#header .primary .search .submit{ padding:0; cursor: pointer;}
		#header a,#secondary a{ text-decoration:none;}
		#header .user{vertical-align:top;}
		#header .auto_complete strong{ font-weight:normal;}
		#header .primary .user_popup .content ul .logout{ padding:0;}
		#header .content,#secondary .content{ overflow: visible;}	
		#secondary{  text-align: left;}
		.no-shadow{ box-shadow: none !important; -moz-box-shadow: none !important; -webkit-box-shadow: none !important;}
		#footer{zoom: 1;}
	</style>
</head>

<body>
	
	<div id="bodyDiv">
		<div class="primary"><!--导航-->
			 <table>
				<tr>
					<td class="naviBtns">
						<a class="btn btn-large" href="/Jing/findMusic.jsp">发现音乐</a>
						<a class=" btn btn-large btn-success" href="/Jing/social/homepage.jsp">我的音乐</a>
					</td>
				</tr>
			</table>
		</div>
	</div>	

	<div id="p-nowrap"><div id="column695">
		<div class="c695_title clearfix" data-spm="1561534497" data-spm-max-idx="15">
			<div class="usr_navigation">
				<ul>
					<li><a class="active" href="#" onclick="$('#wrap-board').load('social/timeline_component.jsp');"><span>主页</span></a></li>
					<li><a href="#" onclick="$('#wrap-board').load('social/setting_component.jsp');"><span>个人信息</span></a></li>
					<li><a href="#" onclick="$('#wrap-board').load('social/tab_component.jsp');"><span>我的标签</span></a></li>
					<li><a href="#" onclick="$('#wrap-board').load('social/singer_component.jsp');"><span>收藏艺人</span></a></li>
					<li><a href="#" onclick="$('#wrap-board').load('social/mymusic_component.jsp');"><span>我的音乐</span></a></li>
					<li><a href="#" onClick="$('#wrap-board').load('social/myfriend_component.jsp');"><span>我的好友</span></a></li>
					<li><a href="#" onClick="$('#wrap-board').load('social/notice_component.jsp');"><span>我的通知</span></a></li>
					<li class="showmore">
						<a class="more" href="javascript:;">
							<span>更多</span>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div></div>

	<div id="wrap-board">
		<div class="content-board" id="timline">
			<div class="content-header">
				<h3>好友状态</h3>
			</div>
			<div class="stream">
				<ol class="unstyled stream-items">
		<% 
			List records = new ArrayList();
			records.addAll(user.getTagrecords());
			Iterator<Tagrecord> it= records.iterator();
            for(int i=0;i<recordpage.get_pagesize();i++)
  			{ 
  				if(!it.hasNext())
					break;
					
				Tagrecord record=it.next();
  		%>
					<li class="unstyled expanding-stream-item">
						<div class="post-item">
							<div class="post-header">
								<a>
									<img class="user-img" src="social/img/user-pic.jpeg"></img>
									<strong><%=user.getName()%></strong>
								</a>
								<small class="post-timestamp"><%=record.getTime()%></small>
							</div>
							<p class="post-text">
								收听关键词: <%=record.getRecord()%>
							</p>
							<img src="social/img/post-pic.jpg">
							<div class="post-item-footer">
								<ul class="unstyle post-actions">
									<li>
										<a href="">回复</a>
									</li>								
									<li>
										<a href="">赞</a>
									</li>
								</ul>
							</div>
						</div>
					</li>
					
		<%
  			}
		%>		
		
				</ol>
			</div>
		</div>
	</div>
</body>
</html>
<%}catch(NullPointerException e){
 
  return;
}catch(Exception e)
{
   
   return;
}
%>