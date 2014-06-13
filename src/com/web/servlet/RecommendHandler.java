package com.web.servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.process.logic.DJ;
import com.process.model.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RecommendHandler {
	
	private int singerpagenow = 1;
	private int rankingpagenow = 1;
	private int guesspagenow = 1;
	
	public void Recommend_ByRanking(HttpServletRequest request, DJ factory){
		Page page = new Page();
		page.set_pagesize(20);
		page.set_pagenow(rankingpagenow);
		
		List songs = factory.Recommend_ByRanking(page);
		if(songs != null && songs.size()>0)
		{
			request.setAttribute("rankingsongs", songs);
			request.setAttribute("rankingpage", page);
			request.setAttribute("isLegal", "legal");
			
			System.out.println("排行榜");
		}
	
	}
	
	public void Recommend_ByRanking_AJAX(PrintWriter out, HttpServletRequest request, DJ factory){
		Page page = new Page();
		page.set_pagesize(20);
		page.set_pagenow(rankingpagenow);
		
		List songs = factory.Recommend_ByRanking(page);
		factory.set_list(songs);
		String currentpath = factory.give_currentpath();
		String currentsongname = factory.give_currentsongname();
		String currentlabel = factory.get_currentlabel();
		String currentsingername = factory.give_currentsingername();
		System.out.println("当前列表的歌数："+ factory.get_songamount());
		//
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		jObject.put("path", currentpath);
		jObject.put("songname", currentsongname);
		jObject.put("singername", currentsingername);
		jObject.put("label", currentlabel);
		jArray.add(jObject);
		out.print(jArray.toString());
		out.flush();
		out.close();
	
	}
	
	public void GuessSong_ByPage(HttpServletRequest request, DJ factory){
		Page page = new Page();
		page.set_pagesize(5);
		page.set_pagenow(guesspagenow);
		
		List songs = factory.GuessSong_ByPage(page);
		if(songs != null && songs.size()>0)
		{
			request.setAttribute("guesssongs", songs);
			request.setAttribute("guesspage", page);
			request.setAttribute("isLegal", "legal");
			
			System.out.println("猜你喜欢");
		}
		
	}
	
	public void GuessSong_ByPage_AJAX(PrintWriter out, HttpServletRequest request, DJ factory){
		Page page = new Page();
		page.set_pagesize(5);
		page.set_pagenow(guesspagenow);
		
		List songs = factory.GuessSong_ByPage(page);
		
		factory.set_list(songs);
		String currentpath = factory.give_currentpath();
		String currentsongname = factory.give_currentsongname();
		String currentlabel = factory.get_currentlabel();
		String currentsingername = factory.give_currentsingername();
		System.out.println("当前列表的歌数："+ factory.get_songamount());
		//
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		jObject.put("path", currentpath);
		jObject.put("songname", currentsongname);
		jObject.put("singername", currentsingername);
		jObject.put("label", currentlabel);
		jArray.add(jObject);
		out.print(jArray.toString());
		out.flush();
		out.close();
	}
	
	public void RecommendSinger_ByPage(HttpServletRequest request, DJ factory){
		Page page = new Page();
		page.set_pagesize(5);
		page.set_pagenow(singerpagenow);
		
		List singers = factory.RecommendSinger_ByPage(page);
		if(singers != null && singers.size()>0)
		{
			request.setAttribute("singers", singers);
			request.setAttribute("singerpage", page);
			request.setAttribute("isLegal", "legal");
			
			System.out.println("歌手推荐");
		}
		
	}
	
	public void RecommendSinger_ByPage_AJAX(PrintWriter out, HttpServletRequest request, DJ factory){
		Page page = new Page();
		page.set_pagesize(5);
		page.set_pagenow(singerpagenow);

		List songs = factory.RecommendSong_BySingers(page);
		
		factory.set_list(songs);
		String currentpath = factory.give_currentpath();
		String currentsongname = factory.give_currentsongname();
		String currentlabel = factory.get_currentlabel();
		String currentsingername = factory.give_currentsingername();
		System.out.println("当前列表的歌数："+ factory.get_songamount());
		//
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		jObject.put("path", currentpath);
		jObject.put("songname", currentsongname);
		jObject.put("singername", currentsingername);
		jObject.put("label", currentlabel);
		jArray.add(jObject);
		out.print(jArray.toString());
		out.flush();
		out.close();
	}
	
	public void SearchSimilarSong(PrintWriter out, DJ factory){
		factory.SearchSimilarSongProcess();
		String currentpath = factory.give_currentpath();
		String currentsongname = factory.give_currentsongname();
		String currentlabel = factory.get_currentlabel();
		String currentsingername = factory.give_currentsingername();
		System.out.println("当前列表的歌数："+ factory.get_songamount());
		//
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		jObject.put("path", currentpath);
		jObject.put("songname", currentsongname);
		jObject.put("singername", currentsingername);
		jObject.put("label", currentlabel);
		jArray.add(jObject);
		out.print(jArray.toString());
		out.flush();
		out.close();
	}
	
	public void SearchSimilarSinger(PrintWriter out, DJ factory){
		factory.SearchSimilarSingerProcess();
		String currentpath = factory.give_currentpath();
		String currentsongname = factory.give_currentsongname();
		String currentlabel = factory.get_currentlabel();
		String currentsingername = factory.give_currentsingername();
		System.out.println("当前列表的歌数："+factory.get_songamount());
		//
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		jObject.put("path", currentpath);
		jObject.put("songname", currentsongname);
		jObject.put("singername", currentsingername);
		jObject.put("label", currentlabel);
		jArray.add(jObject);
		out.print(jArray.toString());
		out.flush();
		out.close();
	}
	
	public void LookandListen(PrintWriter out, DJ factory){
		factory.RandomRecommendProcess();
		String currentpath = factory.give_currentpath();
		String currentsongname = factory.give_currentsongname();
		String currentlabel = factory.get_currentlabel();
		String currentsingername = factory.give_currentsingername();
		System.out.println("当前列表的歌数："+factory.get_songamount());
		//
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		jObject.put("path", currentpath);
		jObject.put("songname", currentsongname);
		jObject.put("singername", currentsingername);
		jObject.put("label", currentlabel);
		jArray.add(jObject);
		out.print(jArray.toString());
		out.flush();
		out.close();
	}
	
	public void LoveSong(PrintWriter out, DJ factory){
		factory.LoveRecommendProcess();
		String currentpath = factory.give_currentpath();
		String currentsongname = factory.give_currentsongname();
		String currentlabel = factory.get_currentlabel();
		String currentsingername = factory.give_currentsingername();
		System.out.println("当前列表的歌数："+factory.get_songamount());
		//
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		jObject.put("path", currentpath);
		jObject.put("songname", currentsongname);
		jObject.put("singername", currentsingername);
		jObject.put("label", currentlabel);
		jArray.add(jObject);
		out.print(jArray.toString());
		out.flush();
		out.close();
	}
	
	public void AI(PrintWriter out, DJ factory){
		factory.SmartRecommendProcess();
		String currentpath = factory.give_currentpath();
		String currentsongname = factory.give_currentsongname();
		String currentlabel = factory.get_currentlabel();
		String currentsingername = factory.give_currentsingername();
		System.out.println("当前列表的歌数："+factory.get_songamount());
		//
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		jObject.put("path", currentpath);
		jObject.put("songname", currentsongname);
		jObject.put("singername", currentsingername);
		jObject.put("label", currentlabel);
		jArray.add(jObject);
		out.print(jArray.toString());
		out.flush();
		out.close();
	}
	
	
	
	
	
	
	
	
}
