package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.process.logic.DJ;
import com.process.model.Page;

@SuppressWarnings("serial")
public class jing_servlet extends HttpServlet {
	
	private DJ factory;
	private Page currentpage = new Page();
	
	private LoginHandler loginhandler = null;
	private SocialHandler socialhandler = null;
	private RecommendHandler recommendhandler = null;
	private MusicControlHandler control = null;
	private SearchHandler searchhandler = null;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
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
		
		
		
		//设计相关
				if(request.getParameter("findMusic") != null)
				{
					System.out.println("find...");
					recommendhandler.Recommend_ByRanking(request, factory);
					recommendhandler.RecommendSinger_ByPage(request, factory);
					recommendhandler.GuessSong_ByPage(request, factory);
					
					//使用response挑战会丢失request\session的数据
					//response.sendRedirect("/Jing/findMusic.jsp");
					request.getRequestDispatcher("findMusic.jsp").forward(request, response);
				}
				if(request.getParameter("homepage") != null)
				{
					System.out.println("homepage");
					
					socialhandler.gotoUserCenter(1, request, factory);
					request.getRequestDispatcher("social/homepage.jsp").forward(request, response);
				}
				if(request.getParameter("usercenter") != null)
				{
					System.out.println("usercenter");
					
					socialhandler.gotoUserCenter(1, request, factory);
					request.getRequestDispatcher("social/timeline_component.jsp").forward(request, response);
				}
				if(request.getParameter("mytag") != null)
				{
					System.out.println("mytag");
					
					socialhandler.getUser(request, factory);
					request.getRequestDispatcher("social/tab_component.jsp").forward(request, response);
				}
				if(request.getParameter("mysinger") != null)
				{
					System.out.println("mysinger");
					
					socialhandler.getFavorSingerByPage(1, request, factory);
					request.getRequestDispatcher("social/singer_component.jsp").forward(request, response);
				}
				if(request.getParameter("mymusic") != null)
				{
					System.out.println("mymusic");
					
					socialhandler.getFavorSong(request, factory);
					request.getRequestDispatcher("social/mymusic_component.jsp").forward(request, response);
				}
		
				if (request.getParameter("assesswindow") != null) {			
					socialhandler.CreateAssessWindow(request, currentpage, factory);
					request.getRequestDispatcher("Comment.jsp").forward(request, response);
				}	
				if (request.getParameter("backcommend") != null) {
					socialhandler.back(currentpage, request, factory);
					request.getRequestDispatcher("Comment.jsp").forward(request, response);
				}
				if (request.getParameter("nextcommend") != null) {
					socialhandler.next(currentpage, request, factory);
					request.getRequestDispatcher("Comment.jsp").forward(request, response);
				}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		
		PrintWriter out = response.getWriter();
		
		//登陆相关
		//login
		if (request.getParameter("username") != null
				&& request.getParameter("password") != null) {
			
			loginhandler.login(request, response, out, factory);
		}
		//注册 
		if(request.getParameter("RegisEmail") != null && request.getParameter("RegisPassword") != null)
		{
			loginhandler.register(request, response, out, factory);
		}
			
		if(!loginhandler.verify(response, request, factory))
			return;
		
		
		
		
	
		
		
		
		
		
		
		
		
		//初始化评价窗口
//		if (request.getParameter("assesswindow") != null) {			
//			socialhandler.CreateAssessWindow(request, out, currentpage, factory);
//		}	
		//社交评价相关
//		if (request.getParameter("backcommend") != null) {
//			socialhandler.back(currentpage, request, factory);
//		}
//		if (request.getParameter("nextcommend") != null) {
//			socialhandler.next(currentpage, request, factory);
//		}
		if(request.getParameter("collectsinger") != null)
    	{  
			System.out.println("收藏艺人");
			socialhandler.CollectSinger(out, factory);
    	}
		if (request.getParameter("level") != null) {
			socialhandler.Mark(request, currentpage, factory);
			request.getRequestDispatcher("Comment.jsp").forward(request, response);
		}

		if (request.getParameter("comment") != null) {
			socialhandler.Comment(request, currentpage, factory);
			request.getRequestDispatcher("Comment.jsp").forward(request, response);
		}

		if (request.getParameter("love") != null) {
			socialhandler.Love(request, out, factory);
		}

		if (request.getParameter("hate") != null) {
			socialhandler.Hate(out, factory);
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
			socialhandler.CreateUserTag(request, factory);
			request.getRequestDispatcher("social/tab_component.jsp").forward(request, response);
		}
		//删除个人标签
		if(request.getParameter("deleteusertag") != null)
		{
			socialhandler.DeleteUserTag(request, factory);
			request.getRequestDispatcher("social/tab_component.jsp").forward(request, response);
		}

		
		
		
		
		
		
		
		
		
		
		//推荐相关
		if (request.getParameter("lookandlisten") != null) {
			System.out.println("随机搜索");
			recommendhandler.LookandListen(out, factory);
		}
		
		if (request.getParameter("hongxindiantai") != null) {
			System.out.println("红心电台");
			recommendhandler.LoveSong(out, factory);
		}
		
		if (request.getParameter("zhinengtuijian") != null) {
			System.out.println("智能搜索");
			recommendhandler.AI(out, factory);
		}
		
		if(request.getParameter("searchsimilarsinger") != null)
    	{  
			System.out.println("搜索相似歌手");
			recommendhandler.SearchSimilarSinger(out, factory);
    	}
		
		if(request.getParameter("searchsimilarsong") != null)
    	{  
			System.out.println("搜索相似歌曲");
			recommendhandler.SearchSimilarSong(out, factory);
    	}
		
		if(request.getParameter("recommendsinger") != null)
    	{  
			System.out.println("推荐歌手");
			recommendhandler.RecommendSinger_ByPage_AJAX(out, request, factory);
    	}
		
		if(request.getParameter("guesssong") != null)
    	{  
			System.out.println("猜你喜欢");
			recommendhandler.GuessSong_ByPage_AJAX(out, request, factory);
    	}
		
		if(request.getParameter("ranking") != null)
    	{  
			System.out.println("排行榜");
			recommendhandler.Recommend_ByRanking_AJAX(out, request, factory);
    	}
		if(request.getParameter("nextsinger") != null)
    	{
			recommendhandler.RecommendSinger_ByPage(request, factory);
    	}
		if(request.getParameter("nextranking") != null)
    	{
			recommendhandler.Recommend_ByRanking(request, factory);
    	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//音乐播放相关
		if(request.getParameter("next") != null)
    	{
			control.next(out, factory);
    	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//搜索相关
		if(request.getParameter("input") != null)
    	{  
			String input = request.getParameter("input");
			System.out.println("服务器收到："+input);
			searchhandler.SearchInput(request, out, factory);
    	}
				
		
		
		
		
		
		
		
		
		//
		if(request.getParameter("match") != null)
		{
			searchhandler.Match(request, out, factory);
		}
		if(request.getParameter("firstmatch") != null)
		{
			searchhandler.firstMatch(request, out, factory);
		}		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		
	}
	
	
}
