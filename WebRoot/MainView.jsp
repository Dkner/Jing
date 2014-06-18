<%@ page language="java" import="java.util.*,com.data.vo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%try{ %>
<!doctype html>
<html>
  <head>
  	<base href="<%=basePath%>">
  	
  	<title>JING 你的音乐网站</title>
    
    <title>我的音乐</title>
    
	<meta http-equiv="pragma" content="no-cache">

  </head>
  
  	<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  	<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap-responsive.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/play.css" rel="stylesheet" type="text/css">
    <link href="css/star.css" rel="stylesheet" type="text/css">
     
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/musiccontrol.js"></script> 
	<script type="text/javascript" src="js/star.js"></script>  
	<script type="text/javascript" src="js/play.js"></script>
  	<script type="text/javascript" src="js/mediaplayer.js"></script> 
  	<script type="text/javascript" src="js/match.js"></script>  
  	<script type="text/javascript" src="js/recommender.js"></script> 
  	<script type="text/javascript" src="js/search_ajax.js"></script> 
  	<script type="text/javascript" src="js/Info.js"></script> 
  
  
  <script type="text/javascript">
  
  	window.onload=init;
  	
  	$(document).ready(function()
		{
  			$("#moreLabel").dropdown();
			$("#dLabel").dropdown();
			$("#adLabel").dropdown();
			$("#settiBtn").click(function(){
				window.open("/Jing/findMusic.jsp");
				});
			$("p.showMenu").click(showMenu);

            $(".ImageOperation").click(function(){
				$(this).parent().remove();
				});
			$("p.labelClick").click(function(){
				$(this).toggleClass("btn-success");
				
				});
			$("#musicConBtn").click(playOrStop);
			$("#commentBtn").click(function(){
				//retify comment.html
				loop();
				window.open("/Jing/Comment.jsp");
			});
			//$("#closeCommentBtn").click(hideComment);
			$(".searchLa").click(function(){
				$("#dLabel").text(this.text);
				});
			$("#tagSearchInput").focus(firstmatch);
			$("#tagSearchInput").keyup(function(){
				switch(window.event.keyCode){
				//ENTER
					case 13:
					{
						if($.trim($("#tagSearchInput").val()).length>0)
						{
							$("#tagSearchInput").blur();
							search();
						}
						else
							$("#remindInfo").text("搜索内容不能为空");
						
						break;
					}
				
					default:
					{
						match();
						break;
					}
				}
			});
		});
	
		
		//读取cookies
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
			var player = new musicplayer("");
			
			//alert("cookie中的登陸名："+getCookie('username'));
			var username=getCookie('username');
			$('#inputEmail').value = username;
			$('#remindInfo').text("欢迎您: "+username);
		}

		function exit(){
			//window.alert("exit");
			window.location.href = "/Jing/jing_servlet?exit=true";
			
		}
		
</script>
  
	<% 
		request.setCharacterEncoding("UTF-8");
  	
  		HttpSession hs = request.getSession(true);
  		if(hs.getAttribute("username") == null)
    	{   		
    		response.sendRedirect("/Jing/Login.jsp");
    		return;
    	}    	
    %>  
  

<body>

 	<div id="maxDiv">
 	<!-- 标签提示浮窗 -->
    	<iframe id="searchTagFrame" class="hide" src="LabelJsp.jsp"></iframe>
    	
 		<div id="leftFrame">
  			<div id="topLeftFrame">
            	<div id="scheLabel" class="">
            		<div class="leftExtendBtn" data-placement="left" data-toggle="tooltip" title="智能匹配与屏蔽" >
                    	<div class="btn-group dropdown">
                        	<button class="dropdown-toggle btn-small btn" id="moreLabel" role="button" data-toggle="dropdown"><img class="icon-th-list"></img></button>
  						<ul class="dropdown-menu"  aria-labelledby="moreLabel">
                            <li class="divider"></li>
   							<li><a id="similarsingerBtn" onclick="searchsimilarsinger()" tabindex="-1">相似艺人</a></li>
                            <li class="divider"></li>
       						<li><a id="similarsongBtn" onclick="searchsimilarsong()" tabindex="-1">相似歌曲</a></li>
                            <li class="divider"></li>
      				    	<li><a id="collectsingerBtn" onclick="collectsinger()" tabindex="-1">收藏艺人</a></li>
      				    	<li class="divider"></li>
      				    	<li><a id="collectsingerBtn" onclick="" tabindex="-1">分享好友</a></li>
                   	    </ul>
                        </div>
					</div>
            	
                 <div id="logOut" class="rightExtendBtn" data-placement="right" data-toggle="tooltip" title="退出" >
               		<button class="btn img-circle" onclick="exit()"><i class="icon-off"></i></button>
               	</div>
            </div>
         
        	<div id="musicShow" class="row-fluid">
            	<div id="musicLove" onclick="loveMusic()" class="musicShowSize" data-toggle="tooltip" data-placement="top" title="喜欢"></div>
               
                <div id="musicHate" onclick="hateMusic()" class="musicShowSize" data-toggle="tooltip" data-placement="top" title="讨厌"></div>
                 <div>
               		 <div id="MusicPlayer" class="" data-toggle="tooltip" data-placement="top" title="play">
                		<div id="musicConBtn"></div>
                       
                	</div>
                <div class="informationOfSong">
                    <a id="songName"></a>
                    <a id="singerName"></a>
                    <div id="commentBtn" class="icon-comment large"></div>
                </div>
                </div>
                <div id="musicCirPlay" onclick="cirPlayMusic()" class="musicShowSize" data-toggle="tooltip" data-placement="top" title="循环"></div>
                <div id="musicChange"  onclick="changeMusic()"class="musicShowSize" data-toggle="tooltip" data-placement="top" title="换歌"></div>
            </div>
            
            
            <div id="extendAndControl">
					<div class="leftExtendBtn" data-placement="left" data-toggle="tooltip" title="更多功能~" >
                    	<div class="btn-group dropdown dropup">
                        	<button class="dropdown-toggle btn-small btn" id="adLabel" role="button" data-toggle="dropdown"><img class="icon-plus-sign"></img></button>
  						<ul class="dropdown-menu"  aria-labelledby="adLabel">
                            <li class="divider"></li>
   							<li><a tabindex="-1" onclick="hongxindiantai()">红心电台</a></li>
                            <li class="divider"></li>
       						<li><a tabindex="-1" onclick="zhinengtuijian()">智能推荐</a></li>
                            <li class="divider"></li>
      				    	<li><a id="randomBtn" onclick="lookandlisten()" tabindex="-1">随便听听</a></li>
                   	    </ul>
                        </div>
					</div>
      
      <!-- 音乐播放器嵌入 -->
               <div id="control">
				
				
               </div>
               
            </div>
            <!--  -->
         
<%--            <div id="actionLabelForm" onclick="backToListen()"><!-- fanhui -->--%>
<%--            	<img id="flyImg" class="actionImg hide " src="./img/actionLa.png"></img>--%>
<%--            	<!--<img class="actionImg" src="./img/actionLa.jpg"></img>-->--%>
<%--                  <a id="remindInfo"></a>--%>
<%--            </div>--%>
         <!--  -->
          </div> 
          	<div id="actionLabelForm" onclick="backToListen()"><!-- fanhui -->
            	<img id="flyImg" class="actionImg hide " src="./img/actionLa.png"></img>
            	<!--<img class="actionImg" src="./img/actionLa.jpg"></img>-->
                  <a id="remindInfo"></a>
            </div>
            <div id="footer">
                <div id="search">
                  <div class="input-append dropup">
  					<div class="btn-group dropdown">
 	 					<button class="dropdown-toggle btn" id="dLabel" role="button" data-toggle="dropdown tooltip" data-placement="right" title="选择搜索方式" >搜歌曲
    				
  						</button>
  						<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
   							<li><a class="searchLa" tabindex="-1" >搜歌曲</a></li>      						
                   	    </ul>
					</div>
  				  	<input class="span4" id="tagSearchInput" type="text" placeholder="歌名,专辑,情绪,状态,风格...(用/隔开)"></input>
                    <!-- <button id="search_btn" class="btn" data-placement="right" data-toggle="tooltip" title="搜索"><i class="icon-search"></i></button> -->
				  </div>
                </div>
                
             	<div id="setting" class="rightExtendBtn">
                	<button id ="settiBtn" class="btn img-circle" data-placement="right" data-toggle="tooltip" title="用户设置" > <i class="icon-user"></i></button>
                </div>
             
            	
            </div>
        </div>
   
   	<!--
     <div id="commentFrameDiv" class="hide">
	    <i id="closeCommentBtn" class=" icon-chevron-down "></i>
      	<iframe id="commentFrame" class="img-polaroid" src="Comment.jsp"></iframe>
     </div>
     -->
     
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