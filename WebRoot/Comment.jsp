<%@ page language="java" import="java.util.*,com.data.vo.*,com.process.model.Page" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%try{ %>
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
	
  </head>
  
  <script type="text/javascript">
  
function giveComment()
{
	$("#commentForm").attr("action","/Jing/jing_servlet");	
	$("#commentForm").submit();
}

  </script>
  
  <body>

  	<%
  	 String flag = (String)request.getAttribute("isLegal");
  	 if(flag == null || !flag.equals("legal"))
  	 {
  	 	response.sendRedirect("/Jing/jing_servlet?assesswindow=music");
  	 	return;
  	 }
  	 else
  	 	request.removeAttribute("isLegal");
  	 
  	 List<Assess> assesses = (List<Assess>)request.getAttribute("assesses");
  	 Song song = (Song) request.getAttribute("song");
  	 List<Tag> songtags = new ArrayList<Tag>(song.getTags());
  	 Page commentpage = (Page)request.getAttribute("commentpage");
  	 Iterator<Assess> it = assesses.iterator();
	%>
        	
	 <div id="commentDiv">
        	<div id="headComment">
            	<div id="nameAndImg">
        			<div id="albumImg"></div>
        			<a><%=song.getName()%>--<%=song.getSinger().getName()%></a>
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
                <%
                    for(int i=0; i<songtags.size()&&i<5; i++) {   	
   				%>		
   					
                   	<div class="tagList"><%=songtags.get(i).getLabel().getLabel()%></div>
                   
                <%
                  	}
                %>      
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
           
           		<form id="commentForm" method="post">
                  		<textarea id="commentTextArea" name="comment" type="text" class="large" placeholder="评论"></textarea>
                  		<div class="saveOrcCanBtn">
                     		<button class="btn btn-info" onclick="giveComment()">发送</button>
                  		</div>        
           		</form>
           		     

   				<div id="othersComment">
   			<%                	
                for(int i=0; i<commentpage.get_pagesize(); i++) {  
                	if(!it.hasNext())
    					break;
    					
    				Assess assess = it.next();
                	String user = assess.getUser().getName();
               	 	String comment = assess.getComment();
               	 	String time = assess.getTime();
  			%>	
					<div class="commentDivBox">
						<ul>
						<li><%=user%>&nbsp&nbsp 评论于<%=time%></li>
						<li><%=comment%></li>
						</ul>
					</div>	
			<%
                }
			%>
				</div>

            </div>
            
 		
     	<div id=navi>
     		<a href="/Jing/jing_servlet?backcommend=control"> back </a>&nbsp&nbsp&nbsp&nbsp
     		<a href="/Jing/jing_servlet?nextcommend=control"> next </a>
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