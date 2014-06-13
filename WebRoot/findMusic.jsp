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
 	response.sendRedirect("/Jing/jing_servlet?findMusic=music");
 	return;
 }
 else
 	request.removeAttribute("isLegal");

 List<Singer> singers = (List<Singer>)request.getAttribute("singers");
 List<Song> rankingsongs = (List<Song>)request.getAttribute("rankingsongs");
 List<Song> guesssongs = (List<Song>)request.getAttribute("guesssongs");
 Page singerpage = (Page)request.getAttribute("singerpage");
 Page rankingpage = (Page)request.getAttribute("rankingpage");
 Page guesspage = (Page)request.getAttribute("guesspage");
 %>

<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
		<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
		<link href="css/bootstrap-responsive.css" rel="stylesheet" type="text/css">
		<link href="css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css">
		<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="css/findMusic.css" rel="stylesheet" type="text/css">
    
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/bootstrap.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/findMusic.js"></script>
    
     
		<script>
			$(document).ready(function(){
				});
		</script>
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
		<!-- ----------------------------------->
<div class="section">
	<div class="container">
	
		<div id="recommends">
		<h2>
			<strong class="bigtext">猜你喜欢</strong>
		</h2>
		<div class="view">
			<div class="content_block">
			
		<% 
			Iterator<Song> it2= guesssongs.iterator();
            for(int i=0;i<guesspage.get_pagesize();i++)
  			{ 
  				if(!it2.hasNext())
					break;
					
				Song song=it2.next();
  		%>
  		
				<div class="album">
					<a class="image">
						<img src="/songlist/<%=song.getSinger().getPicture()%>"></img>						
					</a>
					<div class="albumIntoplay" onclick="playMusicInner(this)"></div>
					<div class="info">
						<p class="name">
							<strong>
								<a><%=song.getName()%></a>
							</strong>
						</p>
						<p>
							<a><%=song.getSinger().getName()%></a>
						</p>
					</div>
				</div>
				
		<%
  			}
		%>		
									
			</div>
		</div>
	<a class="nav prev" onclick="preAlbums(this)" href=""></a>
	<a class="nav next" href="" onclick="nextAlbums(this)"></a>
	<div class="extra">
		<a class="toplay" onclick="playMusicOuter(1)">
		<i></i>
		一键播放
		</a>
	</div> 		
	<br/>  	
	</div>
	
	
	
	<div id="albums">
		<h2>
			<strong class="bigtext">歌手推荐</strong>
		</h2>
		<div class="view"> 		
			<div class="content_block">
			
		<% 
			Iterator<Singer> it= singers.iterator();
            for(int i=0;i<singerpage.get_pagesize();i++)
  			{ 
  				if(!it.hasNext())
					break;
					
				Singer singer=it.next();
  		%>
  		
				<div class="album">
					<input type="hidden" value="<%=singer.getSingerId()%>">
					<a class="image">
						<img src="/songlist/<%=singer.getPicture()%>"></img>
						
					</a>
					<div class="albumIntoplay" onclick="playMusicInner(this)">
					</div>
					<div class="info">
						<p class="name">
							<strong>
								<a><%=singer.getName()%></a>
							</strong>
						</p>						
					</div>
				</div>		
				
		<%
  			}
		%>
														
			</div>			
		</div>
	<a class="nav prev" onclick="preAlbums(this)" href=""></a>
	<a class="nav next" href="" onclick="nextAlbums(this)"></a>
	<div class="extra">
		<a class="toplay" onclick="playMusicOuter(2)">
		<i></i>
		一键播放
		</a>
	</div> 		
	<br/>  	
	</div>




	<div id="charts" data-spm="1478643733">
	<h2>
		<strong class="bigtext">排行榜</strong>
	</h2>
	<div class="content">
	<table>
		<tbody>
		
		<% 
			Iterator<Song> it1= rankingsongs.iterator();
            for(int i=0;i<rankingpage.get_pagesize();i++)
  			{ 
  				if(!it1.hasNext())
					break;
					
				Song song=it1.next();
  		%>
  		
			<tr class="songwrapper" onmouseover="rangListSongCoverVis(this)"  onmouseout="rangListSongCoverHid(this) ">
				<td>
					<div class="sortNumber"><%=(i+1)%></div>
					<input type="hidden" value="<%=song.getId()%>">
				</td>
				<td class="song_block">
					<div class="song">
						<div class="imageano" style="display:none">
							<img class="miniViewCover" src="./img/songCover2.jpg"></img>
							<div class="albumIntoplayano" onclick="playMusicRankingInner(this)"></div>
						</div>
						
						<div class="infor">
							<p>
								<strong>
									<a><%=song.getName()%></a>
								</strong>
							</p>
							
						</div>
					</div>
				</td>
				<td>
					<p class="singer song">
							<a target="_blank"><strong><%=song.getSinger().getName()%></strong></a>									
					</p>
				</td>
			</tr>
			
		<%
  			}
		%>
		
		</tbody>
	</table>
	</div>
	<div class="extra">
	<a class="toplay" onclick="playMusicOuter(3)">
	<i></i>
	一键播放
	</a>
	</div>
	<br/>
	</div>
	</div>
	</div>
	<div class="footer"><!--导航-->
			
   </div>
   </body> 
 </html>
