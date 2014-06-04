package com.web.servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.data.vo.Label;
import com.data.vo.Tagrecord;
import com.process.logic.CurrentList;
import com.process.logic.DJ;
import com.web.api.UrlParser;

public class SearchHandler {

	public void SearchName(HttpServletRequest request, PrintWriter out, DJ factory){
		String songname = request.getParameter("name");
		factory.SongnameSearchingProcess(songname);
	
		String currentsongname = factory.give_currentsongname();
		String currentpath = factory.give_currentpath();
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
		//out.print(currentpath);
		out.flush();
		out.close();	
	}
	
	public void SearchAlbum(HttpServletRequest request, PrintWriter out, DJ factory){
		String albumname = request.getParameter("album");
		factory.AlbumSearchingProcess(albumname);
		
		String currentsongname = factory.give_currentsongname();
		String currentpath = factory.give_currentpath();
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
		//out.print(currentpath);
		out.flush();
		out.close();	
	}
	
	public void SearchTag(HttpServletRequest request, PrintWriter out, DJ factory){
		String tagname = request.getParameter("tag");
		System.out.println("需要搜索的标签："+tagname);
		factory.KeywordSearchingProcess(tagname, 0);
		
		String currentsongname = factory.give_currentsongname();
		String currentpath = factory.give_currentpath();
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
	
	public void SearchInput(HttpServletRequest request, PrintWriter out, DJ factory){
		String input = request.getParameter("input");
		input = input.replace("/", "+");
		System.out.println("需要搜索的input："+input);
		factory.InputSearchingProcess(input);
		
		String currentsongname = factory.give_currentsongname();
		String currentpath = factory.give_currentpath();
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
	
	public void Match(HttpServletRequest request, PrintWriter out, DJ factory){
		List resulttag = new ArrayList();
		String tagname = request.getParameter("match");
		System.out.println("相关匹配："+tagname);
		resulttag = factory.find_MatchLabelProcess(tagname);
		
		//
		if(resulttag != null && resulttag.size() > 0)
		{			
			for(int k=0;k<resulttag.size();k++)
				System.out.println("label"+k+": "+((Label)resulttag.get(k)).getLabel());				
			
			
			HttpSession hs = request.getSession(true); 
			if(hs.getAttribute("labellist") != null)
				hs.removeAttribute("labellist");
			if(hs.getAttribute("labelstring") != null)
				hs.removeAttribute("labelstring");
    		hs.setAttribute("labelstring",resulttag);
			out.print("yes");
			out.flush();
			out.close();
		}
		else
		{
			out.print("no");
			out.flush();
			out.close();
		}
	}
	
	public void firstMatch(HttpServletRequest request, PrintWriter out, DJ factory){
		List resulttag = new ArrayList();
		System.out.println("随机匹配");
		resulttag = factory.create_randomlabelProcess();
		
		for(int k=0;k<resulttag.size();k++)
				System.out.println("label"+k+": "+((Label)resulttag.get(k)).getLabel());				
		
		//
		if(resulttag != null && resulttag.size()>0)
		{
			HttpSession hs = request.getSession(true);
			if(hs.getAttribute("labellist") != null)
				hs.removeAttribute("labellist");
			if(hs.getAttribute("labelstring") != null)
				hs.removeAttribute("labelstring");
    		hs.setAttribute("labellist",resulttag);
			out.print("yes");
			out.flush();
			out.close();
		}
		else
		{
			out.print("no");
			out.flush();
			out.close();
		}
	}
	
}
