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
 	response.sendRedirect("/Jing/jing_servlet?usercenter=music");
 	return;
 }
 else
 	request.removeAttribute("isLegal");

 User user = (User) request.getAttribute("user");
 List<Tagrecord> rankingsongs = (List<Tagrecord>)request.getAttribute("records");
 Page recordpage = (Page)request.getAttribute("recordpage");
 %>

<div id="wrap-board">
		<div class="content-board" id="timline">
			<div class="content-header">
				<h3>音乐状态</h3>
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
								<a href="user_profile.html">
									<img class="user-img" src="social/img/user-pic.jpeg"></img>
									<strong><%=user.getName()%></strong>
								</a>
								<small class="post-timestamp"><%=record.getTime()%></small>
							</div>
							<p class="post-text">
								搜索了: <%=record.getRecord()%>
							</p>
							<!-- 
							<img src="img/post-pic.jpg">
							 -->
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