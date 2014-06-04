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
 	response.sendRedirect("/Jing/jing_servlet?mymusic=music");
 	return;
 }
 else
 	request.removeAttribute("isLegal");

 List<Song> lovesongs = (List<Song>)request.getAttribute("lovesongs");
 Iterator<Song> it = lovesongs.iterator();

 %>


<div id="wrap-board">
		<div class="content-board" id="timline">
			<div class="content-header">
				<h3>我的音乐</h3>
			</div>
			<div class="stream">
				<ol class="unstyled stream-items">
				
		<%
            for(int i=0;i<lovesongs.size();i++)
            { 
  				if(!it.hasNext())
					break;
					
				Song song=it.next();
  		%>
  		
					<li class="unstyled expanding-stream-item">
						<div class="post-item">
							<div class="album-item" style="margin-left:-20px">
								<a style="float:left;" href="#" class="CDcover100">
									<img src="social/img/collection_2.jpg">
								</a>
								<div style="float:left;" class ="album-info">
									<p><%=song.getName()%> -- <%=song.getSinger().getName()%></p>
									<div class ="album-description">
										<p>所在专辑: <%=song.getAlbum().getName()%></p>
										<p>综合评分: <%=song.getScore()%></p>
									</div>
									<button class="btn btn-primary">取消收藏</button>
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