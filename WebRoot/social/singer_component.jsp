<%@ page language="java" import="java.util.*,com.data.vo.*,com.process.model.Page" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
 HttpSession hs = request.getSession(true);
 if(hs.getAttribute("username") == null)
 {
	 response.sendRedirect("/Jing/Login.jsp");
	 return;
 }

 String flag = (String)request.getAttribute("isLegal");
 if(flag == null || !flag.equals("legal"))
 {
 	response.sendRedirect("/Jing/jing_servlet?mysinger=music");
 	return;
 }
 else
 	request.removeAttribute("isLegal");

 List<Singer> favorsingers = (List<Singer>)request.getAttribute("favorsingers");
 Page favorsingerpage = (Page)request.getAttribute("favorsingerpage");
 %>

<div id="wrap-board">
		<div class="content-board" id="timline">
			<div class="content-header">
				<h3>收藏艺人</h3>
			</div>
			<div class="stream">
				<ol class="unstyled stream-items">
				
		<% 
			Iterator<Singer> it= favorsingers.iterator();
            for(int i=0;i<favorsingerpage.get_pagesize();i++)
  			{ 
  				if(!it.hasNext())
					break;
					
				Singer singer=it.next();
  		%>
					<li class="unstyled expanding-stream-item">
						<div class="post-item">
							<div class="album-item" style="margin-left:-20px">
								<a style="float:left;" href="#" class="CDcover100">
									<img src="/songlist/<%=singer.getPicture()%>"/>
								</a>
								<div style="float:left;" class ="album-info">
									<p><%=singer.getName()%></p>
									<div class ="album-description">
										<pre><%=singer.getBriefing()%></pre>
									</div>
									<hr/>
									<button class="btn btn-primary">取消关注</button>
								</div>
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