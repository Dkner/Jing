<%@ page language="java" import="java.util.*,com.data.vo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>JING 你的音乐网站</title>
    <base href="<%=basePath%>">
    
    <title>My JSP 'TestJsp.jsp' starting page</title>
    
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
			$("#test").click(test);
			$("#dLabel").dropdown();
			$("#adLabel").dropdown();
			$("#settiBtn").click(function(){
				$("#rightFrame").toggle(500);
				var username=getCookie('username');
				$("#userNickName").text = username;
				});
			$("p.showMenu").click(showMenu);

            $(".ImageOperation").click(function(){
				$(this).parent().remove();
				});
			$("p.labelClick").click(function(){
				$(this).toggleClass("btn-success");
				
				});
			$("#musicConBtn").click(playOrStop);
			$("#commentBtn").click(comment);
			$("#closeCommentBtn").click(hideComment);
			$(".searchLa").click(function(){
				$("#dLabel").text(this.text);
				});
			$("#randomBtn").click(look);
			//$(".labelRemove").click(delete_tag(this));
			
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
			//
			//alert("cookie中的登陸名："+getCookie('username'));
			var username=getCookie('username');
			get('inputEmail').value = username;
			//get('userNickName').text = username;
			get('remindInfo').text = "欢迎您: "+username+"，           圣诞节快乐！！！";
		}
		
		function presearch(){
			var searchtype = $.trim($("#dLabel").text());
			//var searchcontent = $("#tagSearchInput").text();
			//$("#remindInfo").text = "正在为您搜索: "+searchcontent;
			if(searchtype == "搜歌曲")
			{
				//alert(1);
				search('name');
			}
			if(searchtype == "搜专辑")
			{
				search('album');
			}
			if(searchtype == "搜标签")
			{
				search('tag');
			}
		}
		
		
		function test(){
			window.alert("test");
		}
	
	
	
		function exit(){
			//window.alert("exit");
			window.location.href = "/Jing/jing_servlet?exit=true";
			
		}

		
</script>
  
  
  

<body>
	<% 
	request.setCharacterEncoding("UTF-8");
  	%>
  
 	<%
  		HttpSession hs = request.getSession(true);
  		if(hs.getAttribute("username") == null)
    	{   		
    		response.sendRedirect("/Jing/Login.jsp");
    		return;
    	}    	
    %>

 	<div>
 	<!-- 标签提示浮窗 -->
    	<iframe id="searchTagFrame" class="hide" src="LabelJsp.jsp"></iframe>
    	<iframe id="MerryChristmasFrame" class="hide" src="MerryChrismas.jsp"></iframe>
    	
 		<div id="leftFrame">
  			<div id="topLeftFrame">
            	<div id="scheLabel" class="">
            	<button id="test" class="btn hide">Test</button>
                 <div id="logOut" data-placement="right" data-toggle="tooltip" title="退出" >
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
                    <a id="songName"></a>
                    <div id="commentBtn" class="icon-comment large"></div>
                </div>
                <div id="musicCirPlay" onclick="cirPlayMusic()" class="musicShowSize" data-toggle="tooltip" data-placement="top" title="循环"></div>
                <div id="musicChange"  onclick="changeMusic()"class="musicShowSize" data-toggle="tooltip" data-placement="top" title="换歌"></div>
            </div>
            
            
            <div id="extendAndControl">
					<div id="extenfFunc" data-placement="left" data-toggle="tooltip" title="更多功能~" >
                    	<div class="btn-group dropdown dropup">
                        	<button class="dropdown-toggle btn-small btn" id="adLabel" role="button" data-toggle="dropdown"><img class="icon-plus-sign"></img></button>
  						<ul class="dropdown-menu"  aria-labelledby="adLabel">
                            <li class="divider"></li>
   							<li><a tabindex="-1" onclick="hongxindiantai()">红心电台</a></li>
                            <li class="divider"></li>
       						<li><a tabindex="-1" onclick="zhinengtuijian()">智能推荐</a></li>
                            <li class="divider"></li>
      				    	<li><a id="randomBtn" tabindex="-1">随便听听</a></li>
                   	    </ul>
                        </div>
					</div>
      
      <!-- 音乐播放器嵌入 -->
               <div id="control">
				<!-- 
  					<audio id="player" src="song/Brenda Lee - Jingle Bell.ogg" controls="controls" autoplay="autoplay"></audio>
  				 -->	 
               </div>
            </div>
            <!--  -->
          </div>  
            <div id="actionLabelForm" onclick="backToListen()"><!-- fanhui -->
            	<img id="flyImg" class="actionImg hide " src="./img/actionLa.jpg"></img>
            	<!--<img class="actionImg" src="./img/actionLa.jpg"></img>-->
                  <a id="remindInfo"></a>
            </div>
         <!--  -->
        	
            <div id="footer">
                <div id="search">
                  <div class="input-append dropup">
  					<div class="btn-group dropdown">
 	 					<button class="dropdown-toggle btn" id="dLabel" role="button" data-toggle="dropdown tooltip" data-placement="right" title="选择搜索方式" >搜歌曲
    				
  						</button>
  						<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
   							<li><a class="searchLa" tabindex="-1" >搜歌曲</a></li>
       						<li><a class="searchLa" tabindex="-1" >搜专辑</a></li>
      				    	<li><a class="searchLa" tabindex="-1" >搜标签</a></li>
                   	    </ul>
					</div>
  				  	<input class="span4" id="tagSearchInput" type="text" placeholder="歌名,专辑,情绪,状态,风格...(用/隔开)" onfocus="firstmatch()" onkeyup="match()"></input>
                    <button class="btn" data-placement="right" data-toggle="tooltip" title="搜索" onclick="presearch()"><i class="icon-search"></i></button>
				  </div>
                </div>
             
                <div id="setting" class="">
                	<button id ="settiBtn" class="btn img-circle" data-placement="right" data-toggle="tooltip" title="用户设置" > <i class="icon-user"></i></button>
                </div>
            	
            </div>
        </div>
		
		<div id="rightFrame" class="hide">
        	<div id="headBackgroundImage">
        		<div id="userNickName"></div>
        	</div>
			<div class="personalInfo">
        			<p id="setMenu" class="showMenu btn">个人信息&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</p>
           			<div id="personInfor" class="menuForm hide">
           				<input type="text" id="inputNickName" placeholder="昵  称">
                 	    <label class="radio">
                        	<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>男
				        </label
                        ><label class="radio">
                            <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">女
				  		</label>   
                  		<input type="text" id="inputEmail" placeholder="Email" readonly="readonly">
                  		<textarea rows="3" id="inputSentence" placeholder="个性签名"></textarea>
                  		<div class="saveOrcCanBtn">
                     		<button id="saveInfo" class="btn-primary" onclick="update_info()">保存</button>
                  		</div>        
           			</div>
       	    </div>
            
            <div class="personalInfo">
        			<p id="setPwd" class="showMenu btn">修改密码&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</p>
           			<div id="personPwd" class="menuForm hide">
           				<input type="password" id="oldPwd"  class="large" placeholder="旧密码">
                 	    <input type="password" id="newPwd" placeholder="新密码">
                        <button id="changeKey" class="btn-primary" onclick="update_key()">保存</button>	    
           			</div>
       	    </div>
            
            <div class="personalInfo">
        			<p id="selectMenu" class="showMenu btn">选择器&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</p>
                    <div class="hide">
                    	<div id="loveLabels">
                    	
                <% 
  					HttpSession usertagsession = request.getSession(true);
					List usertag = new ArrayList();
    				if(usertagsession.getAttribute("usertag") != null)
    				{
    					usertag = (List)usertagsession.getAttribute("usertag");
    					for(int i=0; i<usertag.size(); i++) {   	
    			%>	
                        	<div id="usertag" class="loveLabelEach">
                            	<div class="row-fluid  ">
                            		<div class="labelText btn-small"><%=((Label)(usertag.get(i))).getLabel()%></div>
                                	<div class="icon-remove labelRemove" onclick="delete_tag(this)"></div>
                           	    </div> 
                             </div>   
				 <%		  						
			    		}	    				
			    	}
				 %>  
                     	</div>
                     	
           				<div id="" class="menuForm" >
                    		<div class="input-append">
                        		<input type="text" id="inputTag" placeholder="Tag"> </input>
                     			<button id="save_Tag" class="btn" onclick="update_tag()">添加</button>
                   			</div>	
           				</div>
                    </div>
                    	
       	    </div>
            
            
             <div class="personalInfo">
        			<p id="setLoveOrHate" class="showMenu btn">音乐&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</p>
           			<div class="hide">
           		
                    	<div>
                        	<p id="checkLove" class="showSecondMenu showMenu btn btn-info">喜欢的</p>
                            <div class="hide historyGroup" id="loveGroup">
                        <% 
  							HttpSession lovesession = request.getSession(true);
							List lovesonglist = new ArrayList();
    						if(usertagsession.getAttribute("lovesonglist") != null)
    						{
    							lovesonglist = (List)lovesession.getAttribute("lovesonglist");
    							for(int i=0; i<lovesonglist.size(); i++) {   	
    					%>	
                            	<div class="historyEach">
                                	<div class="songImag"></div>
                                    <div class="singerAndName">
                                    	<div class="songname"><label><%=((Song)(lovesonglist.get(i))).getName()%></label></div>
                                    
                                    </div>
                                    <div class="ImageOperation ImageLoveOperation"></div>
                                </div>
                        <%		  						
			    				}	    				
			    			}
						%>         
                        
                            </div>
                        </div>
                     
				
                    <!-- 
                        <div>
                        	<p id="checkHate" class="showSecondMenu showMenu btn btn-info">讨厌的</p>
                            <div class="hide historyGroup" id="hateGroup">
                            	<div class="historyEach">
                                	<div class="songImag"></div>
                                    <div class="singerAndName">
                                    	<div class="songname"><label>songName</label></div>
                                        <div class="singer"><label>singer</label></div>
                                    </div>
                                    <div class="ImageOperation ImageHateOperation"></div>
                                </div>
                                <div class="historyEach">
                                	<div class="songImag"></div>
                                    <div class="singerAndName">
                                    	<div class="songname"><label>songName</label></div>
                                        <div class="singer"><label>singer</label></div>
                                    </div>
                                    <div class="ImageOperation ImageHateOperation"></div>
                                </div>
                            </div>

                        </div>
                      -->
                      
           			</div>
       	    </div>
      
		</div> 
    	
        
      
        
        <!-- 评论模块 -->
        
 	</div>
      
   <!--  
        <div id="commentFrame" class="hide">
        	<i id="closeCommentBtn" class="icon-remove"></i>
        	<div id="headComment">
        		<div id="nameAndImg">
        			<div id="albumImg"></div>
        			<a>songName</a>
        		</div>
            	
                <div id="scoresAndTagsDiv">
                	<span id="stars0">
							<img id="star_1" value="1" src="img/nst.gif"/>
							<img id="star_2" value="2" src="img/nst.gif"/>
							<img id="star_3" value="3" src="img/nst.gif"/>
							<img id="star_4" value="4" src="img/nst.gif"/>
							<img id="star_5" value="5" src="img/nst.gif"/>
					</span>
                    <div id="tagGroups">
                    	<div class="tagList">古典</div>
                        <div class="tagList">爵士</div>
                        <div class="tagList">民谣</div>
                        <div class="tagList">戏曲</div>
                        <div class="tagList">流行</div>
                        <div class="tagList">嘻哈</div>
                    </div>
                </div>
            </div>
            <br>
            <div id="rating" onmouseout="mOut()">
            评价:
                <span id="stars">
                <a>
                    <img id="star1" value="1" onmouseover="mOver(1)" onclick="on_click(1)" src="img/nst.gif"/>
                </a>
                <a>
                    <img id="star2" value="2" onmouseover="mOver(2)" onclick="on_click(2)" src="img/nst.gif"/>
                </a>
                <a>
                    <img id="star3" value="3" onmouseover="mOver(3)" onclick="on_click(3)" src="img/nst.gif"/>
                </a>
                <a>
                    <img id="star4" value="4" onmouseover="mOver(4)" onclick="on_click(4)" src="img/nst.gif"/>
                </a>
                <a>
                    <img id="star5" value="5" onmouseover="mOver(5)" onclick="on_click(5)" src="img/nst.gif"/>
                </a>
                </span>
            <input id="n_rating" type="hidden" value="">
            </div>
            
            <div id="commendGroups">
            	<form id="" class="" action="#">
                  		<textarea id="commentTextArea"type="text" class="large" placeholder="评论"></textarea>
                  		<div class="saveOrcCanBtn">
                     		<button id="###" class="btn-primary">发送</button>
                      	 	<button id="###" class="btn-primary">取消</button>
                  		</div>        
           		</form>
            	<div class="commentDiv">
                	<a class="">userName</a>:<a>commend</a>
                </div>	
                <div class="commentDiv">
                	<a class="">userName</a>:<a>commend</a>
                </div>	
            </div>
        </div>
    
	 -->
	    <i id="closeCommentBtn" class="icon-remove hide"></i>
      	<iframe id="commentFrame" class="hide" src="Comment.jsp"></iframe>
</body>
</html>
