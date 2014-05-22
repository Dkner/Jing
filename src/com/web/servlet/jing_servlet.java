package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.process.logic.DJ;
import com.process.model.CurrentList;
import com.process.model.Page;
import com.data.vo.User;
import com.data.vo.UserDAO;

public class jing_servlet extends HttpServlet {
	
	private DJ factory;
	private CurrentList list = null;
	private Page currentpage = null;
	
	private LoginHandler loginhandler = null;
	private SocialHandler socialhandler = null;
	private RecommendHandler recommendhandler = null;
	private MusicControlHandler control = null;
	private SearchHandler searchhandler = null;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		list = new CurrentList();
		factory = new DJ();
		loginhandler = new LoginHandler();
		socialhandler = new SocialHandler();
		recommendhandler = new RecommendHandler();
		control = new MusicControlHandler();
		searchhandler = new SearchHandler();
	}
	
	
	//abandoned
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//
		//login
				if (request.getParameter("username") != null
						&& request.getParameter("password") != null) {
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					System.out.println(username + " " + password);
					UserDAO identify = new UserDAO();
					// 用邮箱登录，验证
					@SuppressWarnings("rawtypes")
					List temp = identify.findByMail(username);

					if (temp.size() > 0
							&& ((User) temp.get(0)).getKeyword().equals(password)) {
						System.out.println("验证成功");
						list.set_userid(((User) temp.get(0)).getUserId());

						// session
						System.out.println("写入session");
						HttpSession hs = request.getSession(true);
						hs.setAttribute("username", username);
						response.sendRedirect("/Jing/test_jsp/TestJsp.jsp");
					} else {
						System.out.println("验证错误");
						response.sendRedirect("/Jing/test_jsp/Login.jsp");
					}

				}
		
		//exit
		if(request.getParameter("exit") != null)
		{
			
			HttpSession hs = request.getSession(true);
			hs.removeAttribute("username");		

//			Cookie[] allcookies = request.getCookies();
//			if(allcookies != null)
//	  		{
//	  			for(int i=0;i<allcookies.length;i++)
//	  			{
//	  				Cookie temp = allcookies[i];
//	  				if(temp.getName().equals("username") || temp.getName().equals("password"))
//	  				{
//	  					System.out.println("ɾ���û���cookie");
//	  					temp.setMaxAge(0);
//	  				}	  				
//	  			}
//	  		}
			
			response.sendRedirect("/Jing/Login.jsp");
		}
		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		
		PrintWriter out = response.getWriter();
		
		//login
		if (request.getParameter("username") != null
				&& request.getParameter("password") != null) {
			
			loginhandler.login(request, response, out, factory, list);
		}

		//注册 
		if(request.getParameter("RegisEmail") != null && request.getParameter("RegisPassword") != null)
		{
			loginhandler.register(request, response, out, list);
		}
			
		
		
		
		
		
		
		//初始化评价窗口
		if (request.getParameter("commentwindow") != null) {
			socialhandler.CreateCommentWindow(request, out, list, currentpage);
		}
			
		//社交评价相关
		if (request.getParameter("backcommend") != null) {
			socialhandler.back(currentpage);
		}
		if (request.getParameter("nextcommend") != null) {
			socialhandler.next(currentpage);
		}
		
		if (request.getParameter("level") != null) {
			socialhandler.Mark(request, out, list, currentpage, factory);
		}

		if (request.getParameter("comment") != null) {
			socialhandler.Comment(request, out, list, currentpage, factory);
		}

		if (request.getParameter("love") != null) {
			socialhandler.Love(request, out, list, factory);
		}

		if (request.getParameter("hate") != null) {
			socialhandler.Hate(out, list, factory);
		}
		
		//修改個人信息
		if(request.getParameter("userinfo") != null) 
		{
			socialhandler.ReviceUserInfo(request, out);
		}
		//修改密碼
		if(request.getParameter("oldkeyword") != null && request.getParameter("newkeyword") != null)
		{
			socialhandler.ReviceKeyword(request, out);
		}
		//订制个人标签
		if(request.getParameter("newusertag") != null)
		{
			socialhandler.CreateUserTag(request, out, list, factory);
		}
		//删除个人标签
		if(request.getParameter("deleteusertag") != null)
		{
			socialhandler.DeleteUserTag(request, out, list, factory);
		}

		
		
		
		
		
		
		
		
		
		
		//
		if (request.getParameter("lookandlisten") != null) {
			recommendhandler.LookandListen(out, list, factory);
		}
		
		if (request.getParameter("hongxindiantai") != null) {
			recommendhandler.LoveSong(out, list, factory);
		}
		
		if (request.getParameter("zhinengtuijian") != null) {
			recommendhandler.AI(out, list, factory);
		}
		
		
		
		
		
		
		
		
		
		//
		if(request.getParameter("next") != null)
    	{
			control.next(out, list, factory);
    	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//
		if(request.getParameter("input") != null)
    	{   
			searchhandler.SearchInput(request, out, list, factory);
    	}
		
		if(request.getParameter("name") != null)
    	{   
			searchhandler.SearchName(request, out, list, factory);
    	}
	
		if(request.getParameter("album") != null)
		{
			searchhandler.SearchAlbum(request, out, list, factory);
		}
	
		if(request.getParameter("tag") != null)
		{
			searchhandler.SearchTag(request, out, list, factory);
		}
	
		//
		if(request.getParameter("match") != null)
		{
			searchhandler.Match(request, out, list, factory);
		}
		//
		if(request.getParameter("firstmatch") != null)
		{
			searchhandler.firstMatch(request, out, list, factory);
		}		
		
		
		
		
		
		
		
		
	}
	
	
}
