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
 	response.sendRedirect("/Jing/jing_servlet?mytag=music");
 	return;
 }
 else
 	request.removeAttribute("isLegal");

 List<Label> userlabel = (List<Label>) request.getAttribute("userlabel");

 %>

<div class="content-board" id="timline">
        <div class="content-header">
            <h3>我的标签</h3>
        </div>
        <div class="stream">
        <ol class="unstyled stream-items">
        <li class="unstyled expanding-stream-item">
        <div class="indent">
        
        <% 
			Iterator<Label> it= userlabel.iterator();
            for(int i=0;i<userlabel.size();i++)
  			{ 
  				if(!it.hasNext())
					break;
					
				 Label label = it.next();
  		%>
        <span class="cloud4"><button><%=label.getLabel()%></button></span>&nbsp; &nbsp;
        <span class="cloud4"></span>
		<%
  			}
		%>
		
		<br/><br/>
		<form action="/Jing/jing_servlet" method="post">
			<input type="text" name="newusertag"></input>
			<button type="submit">添加标签</button>
		</form>
		<form action="/Jing/jing_servlet" method="post">
			<input type="text" name="deleteusertag"></input>
			<button type="submit">删除标签</button>
		</form>
		
        </div>
        </li>
        </ol>
        </div>
</div>
<%}catch(NullPointerException e){
 
  return;
}catch(Exception e)
{
   
   return;
}
%>