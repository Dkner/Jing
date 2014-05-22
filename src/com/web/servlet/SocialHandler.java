package com.web.servlet;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.process.logic.DJ;
import com.process.model.CurrentList;
import com.process.model.Page;

public class SocialHandler {
	public void CreateCommentWindow(HttpServletRequest request, PrintWriter out, CurrentList list, Page currentpage){
		System.out.println("commentwindow init");
		String comment = request.getParameter("commentwindow");
		//初始化page的session
		if(list.give_currentsongname() != "")
		{
			currentpage = new Page(list.give_currentsongname());
			currentpage.print();
			
			HttpSession hs = request.getSession(true);
			if(hs.getAttribute("page") != null)
				hs.removeAttribute("page");
			hs.setAttribute("page",currentpage);
			out.print("success");
			out.flush();
			out.close();
		}
		else
		{
			out.print("fail");
			out.flush();
			out.close();
		}
	}
	
	public void back(Page currentpage){
		System.out.println("backcommend");
		currentpage.back();
	}
	
	public void next(Page currentpage){
		System.out.println("nextcommend");
		currentpage.next();
	}
	
	public void Mark(HttpServletRequest request, PrintWriter out, CurrentList list, Page currentpage, DJ factory){
		System.out.println("level");
		String level = request.getParameter("level");
		//list.level_assess(level);
		int song_id = list.get_currentSongId();
		if(song_id != -1)
			factory.GradeMarkingProcess(level, list.get_userid(), song_id);
		//
		currentpage = new Page(list.give_currentsongname());
		currentpage.print();
		
		HttpSession hs = request.getSession(true);
		if(hs.getAttribute("page") != null)
			hs.removeAttribute("page");
		hs.setAttribute("page",currentpage);
		out.print("success");
		out.flush();
		out.close();
	}
	
	public void Comment(HttpServletRequest request, PrintWriter out, CurrentList list, Page currentpage, DJ factory){
		System.out.println("comment");
		String comment = request.getParameter("comment");
		//list.comment_assess(comment);
		int song_id = list.get_currentSongId();
		if(song_id != -1)
			factory.CommentProcess(comment, list.get_userid(), song_id);
		//
		currentpage = new Page(list.give_currentsongname());
		currentpage.print();
		
		HttpSession hs = request.getSession(true);
		if(hs.getAttribute("page") != null)
			hs.removeAttribute("page");
		hs.setAttribute("page",currentpage);
		out.print("success");
		out.flush();
		out.close();
	}
	
	public void Love(HttpServletRequest request, PrintWriter out, CurrentList list, DJ factory){
		System.out.println("服務器收到： love");
		//list.love_assess();
		int song_id = list.get_currentSongId();
		if(song_id != -1)
			factory.LoveProcess(list.get_userid(), song_id);
		//修改lovesonglist
		List lovesonglist = factory.GetLoveSongListProcess(list.get_userid());
		HttpSession hs = request.getSession(true);
		hs.setAttribute("lovesonglist",lovesonglist);
		out.print("love");
		out.flush();
		out.close();
	}
	
	public void Hate(PrintWriter out, CurrentList list, DJ factory){
		System.out.println("服務器收到：hate");
		//list.hate_assess();
		int song_id = list.get_currentSongId();
		if(song_id != -1)
			factory.HateProcess(list.get_userid(), song_id);
		out.print("hate");
		out.flush();
		out.close();
	}
	
	public void CreateUserTag(HttpServletRequest request, PrintWriter out, CurrentList list, DJ factory){
		String newusertag = request.getParameter("newusertag");		
		System.out.println(newusertag);
		boolean result = factory.CustomizeTagProcess(list.get_userid(), newusertag);
		if(result == true)
		{
			HttpSession hs = request.getSession(true);
			List usertaglist = factory.get_usertagProcess(list.get_userid());
			hs.setAttribute("usertag", usertaglist);
			out.print("标签订制成功");
			out.flush();
			out.close();
		}
		else
		{
			out.print("没有匹配的标签");
			out.flush();
			out.close();
		}
	}
	
	public void DeleteUserTag(HttpServletRequest request, PrintWriter out, CurrentList list, DJ factory){
		String oldusertag = request.getParameter("deleteusertag");		
		System.out.println(oldusertag);
		factory.UndoTagProcess(list.get_userid(), oldusertag);
		//
		HttpSession hs = request.getSession(true);
		List usertaglist = factory.get_usertagProcess(list.get_userid());
		hs.setAttribute("usertag", usertaglist);
		out.print("删除标签");
		out.flush();
		out.close();
	}
	
	public void ReviceUserInfo(HttpServletRequest request, PrintWriter out){
		String username = request.getParameter("username");			
		String sex = request.getParameter("sex");
		String signature = request.getParameter("signature");
		//
		//list.update_userinfo(username, sex, signature);	
		out.print("個人信息修改成功");
		out.flush();
		out.close();
	}
	
	public void ReviceKeyword(HttpServletRequest request, PrintWriter out){
		String oldkeyword = request.getParameter("oldkeyword");		
		String newkeyword = request.getParameter("newkeyword");		
		//boolean result = list.update_keyword(oldkeyword, newkeyword);
		boolean result = true;
		if(result == true)
		{
			out.print("密碼修改成功");
			out.flush();
			out.close();
		}
		else
		{
			out.print("密碼錯誤，修改失敗");
			out.flush();
			out.close();
		}
	}
	
}
