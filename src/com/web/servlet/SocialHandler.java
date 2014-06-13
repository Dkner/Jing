package com.web.servlet;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.data.vo.Song;
import com.data.vo.User;
import com.process.logic.DJ;
import com.process.model.Page;

public class SocialHandler {
	
	public void back(Page currentpage, HttpServletRequest request, DJ factory){
		System.out.println("backcommend");
		currentpage.back();
		List assesses = factory.get_Assess(currentpage);
		Song song = factory.get_CurrentSong();
		
		request.setAttribute("song",song);
		request.setAttribute("commentpage",currentpage);
		request.setAttribute("assesses",assesses);
		request.setAttribute("isLegal","legal");
	}
	
	public void next(Page currentpage, HttpServletRequest request, DJ factory){
		System.out.println("nextcommend");
		currentpage.next();
		List assesses = factory.get_Assess(currentpage);
		Song song = factory.get_CurrentSong();
		
		request.setAttribute("song",song);
		request.setAttribute("commentpage",currentpage);
		request.setAttribute("assesses",assesses);
		request.setAttribute("isLegal","legal");
	}
	
	public void CreateAssessWindow(HttpServletRequest request, Page currentpage, DJ factory){
		System.out.println("assess window init");
		//初始化commentpage的session
		if(factory.get_songamount() != 0 && !factory.give_currentsongname().equals(""))
		{
			//Page			
			currentpage.set_pagenow(1);
			currentpage.set_pagesize(8);
			
			List assesses = factory.get_Assess(currentpage);
			Song song = factory.get_CurrentSong();
			System.out.println("歌曲:"+song.getName()+"\n评论条数"+assesses.size());
			
			request.setAttribute("song",song);
			request.setAttribute("commentpage",currentpage);
			request.setAttribute("assesses",assesses);
			request.setAttribute("isLegal","legal");
		}
		
	}
	
//	public void CreateAssessWindow(HttpServletRequest request, PrintWriter out, Page currentpage, DJ factory){
//		System.out.println("assess window init");
//		//初始化commentpage的session
//		if(!factory.give_currentsongname().equals(""))
//		{
//			//Page			
//			currentpage.set_pagenow(1);
//			currentpage.set_pagesize(8);
//			
//			List assesses = factory.get_Assess(currentpage);
//			Song song = factory.get_CurrentSong();
//			System.out.println("歌曲:"+song.getName()+"\n评论条数"+assesses.size());
//			
//			request.setAttribute("song",song);
//			request.setAttribute("commentpage",currentpage);
//			request.setAttribute("assesses",assesses);
//			request.setAttribute("isLegal", "legal");
//			
//			out.print("success");
//			out.flush();
//			out.close();
//		}
//		else
//		{
//			out.print("fail");
//			out.flush();
//			out.close();
//		}
//	}
	
	public void gotoUserCenter(int pagenow, HttpServletRequest request, DJ factory){
		Page page = new Page();
		page.set_pagesize(20);
		page.set_pagenow(pagenow);
		
		List records = factory.get_Tagrecord(page);
		User user = factory.get_User();
		if(records != null && records.size()>0)
		{
			request.setAttribute("user", user);
			request.setAttribute("records", records);
			request.setAttribute("recordpage", page);
			request.setAttribute("isLegal", "legal");
		}
	}
	
	public void getUser(HttpServletRequest request, DJ factory){
		
		List userlabel = factory.get_usertagProcess();
		if(userlabel != null && userlabel.size()>0)
		{
			request.setAttribute("userlabel", userlabel);
			request.setAttribute("isLegal", "legal");
		}
	}
	
	public void getFavorSingerByPage(int pagenow, HttpServletRequest request, DJ factory){
		Page page = new Page();
		page.set_pagesize(20);
		page.set_pagenow(pagenow);
		
		List singers = factory.GetFavorSingerListProcess(page);
		if(singers != null)
		{
			request.setAttribute("favorsingers", singers);
			request.setAttribute("favorsingerpage", page);
			request.setAttribute("isLegal", "legal");
		}
		
	}
	
	public void getFavorSong(HttpServletRequest request, DJ factory){
		
		List lovesongs = factory.GetLoveSongListProcess();
		if(lovesongs != null)
		{
			request.setAttribute("lovesongs", lovesongs);
			request.setAttribute("isLegal", "legal");
		}
		
	}
	
	public void CollectSinger(PrintWriter out, DJ factory){
		if(factory.CollectSingerProcess())
		{
			out.print("success");
		}
		else
			out.print("fail");
		out.flush();
		out.close();
	}
	
	public void Mark(HttpServletRequest request, Page currentpage, DJ factory){
		System.out.println("level");
		String level = request.getParameter("level");
		//list.level_assess(level);
		int song_id = factory.get_currentSongId();
		if(song_id != -1)
			factory.GradeMarkingProcess(level);
		//
		currentpage.set_pagenow(1);
		currentpage.set_pagesize(8);
		
		List assesses = factory.get_Assess(currentpage);
		Song song = factory.get_CurrentSong();
		
		request.setAttribute("song",song);
		request.setAttribute("commentpage",currentpage);
		request.setAttribute("assesses",assesses);
		request.setAttribute("isLegal", "legal");
				
	}
	
	public void Comment(HttpServletRequest request, Page currentpage, DJ factory){
		System.out.println("comment");
		String comment = request.getParameter("comment");
		//list.comment_assess(comment);
		int song_id = factory.get_currentSongId();
		if(song_id != -1)
			factory.CommentProcess(comment);
		//
		List assesses = factory.get_Assess(currentpage);
		Song song = factory.get_CurrentSong();
		
		request.setAttribute("song",song);
		request.setAttribute("commentpage",currentpage);
		request.setAttribute("assesses",assesses);
		request.setAttribute("isLegal", "legal");
		
	}
	
	public void Love(HttpServletRequest request, PrintWriter out, DJ factory){
		System.out.println("服務器收到： love");
		//list.love_assess();
		int song_id = factory.get_currentSongId();
		if(song_id != -1)
			factory.LoveProcess();
		
		out.print("love");
		out.flush();
		out.close();
	}
	
	public void Hate(PrintWriter out, DJ factory){
		System.out.println("服務器收到：hate");
		//list.hate_assess();
		int song_id = factory.get_currentSongId();
		if(song_id != -1)
			factory.HateProcess();
		out.print("hate");
		out.flush();
		out.close();
	}
	
	public void CreateUserTag(HttpServletRequest request, PrintWriter out, DJ factory){
		String newusertag = request.getParameter("newusertag");		
		System.out.println("添加标签："+newusertag);
		boolean result = factory.CustomizeTagProcess(newusertag);
		if(result == true)
		{
			List usertaglist = factory.get_usertagProcess();
			request.setAttribute("userlabel", usertaglist);
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
	
	public void DeleteUserTag(HttpServletRequest request, PrintWriter out, DJ factory){
		String oldusertag = request.getParameter("deleteusertag");		
		System.out.println(oldusertag);
		factory.UndoTagProcess(oldusertag);
		//
		List usertaglist = factory.get_usertagProcess();
		request.setAttribute("userlabel", usertaglist);
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
