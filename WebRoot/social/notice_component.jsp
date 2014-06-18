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
	response.sendRedirect("/Jing/jing_servlet?mynotice=music");
	return;
}
else
	request.removeAttribute("isLegal");

List<Notice> notices = (List<Notice>) request.getAttribute("notices");

 %>

<script type="text/javascript">
var notice_XmlHttpRequest="";

function get(id){
	return document.getElementById(id);
}

function getXmlHttpObject(){
	
	var xmlHttpRequest;
	//不同的浏览器获取对象xmlhttprequest 对象方法不一样
	if(window.ActiveXObject){
		
		xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
		
	}else{

		xmlHttpRequest=new XMLHttpRequest();
	}

	return xmlHttpRequest;

}

function notice(content){
	notice_XmlHttpRequest=getXmlHttpObject();

		//怎么判断创建ok
		if(notice_XmlHttpRequest){
			
			//var data =  "input="+get('NoticeContent').innerText;			
			var data =  "input="+content;
			var url="/Jing/jing_servlet";
			
			notice_XmlHttpRequest.open("post",url,true);
			notice_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			notice_XmlHttpRequest.onreadystatechange=notice_callback;
			notice_XmlHttpRequest.send(data); 
			//alert("浏览器发送:"+data);
		}
	}

	//回调函数
	function notice_callback(){		

		var par = window.opener;
		
		if(notice_XmlHttpRequest.readyState==4){
			
			if(notice_XmlHttpRequest.status == 200)
			{
				var mes=notice_XmlHttpRequest.responseText;
				//window.alert(mes);
				var mes_obj=eval("("+mes+")");
				par.document.getElementById('player').src = "/songlist/"+mes_obj[0].path;
				par.document.getElementById('songName').innerText = mes_obj[0].songname;
				par.document.getElementById('singerName').innerText = mes_obj[0].singername;

				if(mes_obj[0].path != "")
					par.document.getElementById("remindInfo").innerText = "正在播放好友的推荐歌曲";
				if($.trim(mes_obj[0].path) == "")
					par.document.getElementById("remindInfo").innerText = "不好意思,歌曲放完了。。。";
			}
		}
	}
</script>
	
<div id="wrap-board">
	<div class="content-board">
                <div class="expanding-user-item unstyled">
                
        <% 
			Iterator<Notice> it= notices.iterator();
            for(int i=0;i<notices.size();i++)
  			{ 
  				if(!it.hasNext())
					break;
					
				 Notice notice = it.next();
  		%>
                    <div class="user-item-container">
                        
                        <div class="user-item">
                            <div class="user-header">
                                <img class="post-user-img" src="social/img/user-pic.jpeg"></img>
                                <span><%=notice.getUser().getName()%>向你推荐歌曲:<a onclick="notice('<%=notice.getContent()%>')"><%=notice.getContent()%></a></span>
                            </div>
                        </div>
                        
                    </div>
		<%
  			}
		%>
                   
                </div>
    </div>
</div>
<%}catch(NullPointerException e){
 
  return;
}catch(Exception e)
{
   
   return;
}
%>