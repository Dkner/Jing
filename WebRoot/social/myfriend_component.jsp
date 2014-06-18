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
	response.sendRedirect("/Jing/jing_servlet?myfriend=music");
	return;
}
else
	request.removeAttribute("isLegal");

List<User> friends = (List<User>) request.getAttribute("friends");

 %>

<div id="wrap-board">
	<div class="content-board">
                <div class="expanding-user-item unstyled">
                
        <% 
			Iterator<User> it= friends.iterator();
            for(int i=0;i<friends.size();i++)
  			{ 
  				if(!it.hasNext())
					break;
					
				 User user = it.next();
  		%>
                    <div class="user-item-container">
                        
                        <div class="user-item">
                            <div class="user-header">
                                <img class="post-user-img" src="social/img/user-pic.jpeg"></img>
                                <span><%=user.getName()%>&nbsp&nbsp&nbsp<%=user.getSex()%></span>
                            </div>
                            
                            <div class="user-intro-text">
                               	 个人简介:<%=user.getSignature()%>
                            </div>
                            
                            <div class="follow-toggle">
                            <button class="btn btn-primary btn-followed">
                            	解除关系
                            </button>
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