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
 	response.sendRedirect("/Jing/jing_servlet?mytag=music");
 	return;
 }
 else
 	request.removeAttribute("isLegal");

 User user = (User) request.getAttribute("user");

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
			List usertags = new ArrayList();
        	usertags.addAll(user.getUsertags());
			Iterator<Usertag> it= usertags.iterator();
            for(int i=0;i<usertags.size();i++)
  			{ 
  				if(!it.hasNext())
					break;
					
				 Label label = it.next().getLabel();
  		%>
        <span class="cloud2"><a href="#"><%=label.getLabel()%></a></span>&nbsp; &nbsp;
		<%
  			}
		%>
        </div>
        </li>
        </ol>
        </div>
</div>