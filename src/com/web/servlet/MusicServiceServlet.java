package com.web.servlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.process.model.CurrentWebList;
import com.process.model.Page;

import tool.Dom4j;
import tool.JsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class MusicServiceServlet extends HttpServlet {

	private CurrentWebList list = null;
	
	private Page currentpage = null;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		list = new CurrentWebList();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/xml;charset=utf-8");  
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html");	
		response.setCharacterEncoding("utf-8"); 
				
		PrintWriter out = response.getWriter();
		
		
//		List<City> list = new ArrayList<City>();  
//		City city1 = new City(1,"AA"); 
//		City city2 = new City(2,"BB"); 
//		City city3 = new City(3,"CC"); 
//		list.add(city1);
//		list.add(city2);
//		list.add(city3);
//				
//		JSONArray jArray = new JSONArray();
//		for(int i=0; i<list.size(); i++)
//		{
//			String jsonstring = JsonUtil.bean2json(list.get(i));
//			jArray.add(jsonstring);
//		}
//	    System.out.println(jArray.toString());  	    
//	    out.print(jArray.toString());  
		
		
		StringBuffer buffer = new StringBuffer();
		
		if (request.getParameter("fct") != null) {
			
			String tag = request.getParameter("tag");
			System.out.println("musicservice正在处理。。。"+tag);
			
			try {
				//URL url = new URL("http://musicovery.com/api/track.php?fct="+fct+"&title="+title);
				URL url = new URL("http://musicovery.com/api/playlist.php?fct=tagseed&apikey=123_1&tag="+tag+"&tracksnumber=20");
				URLConnection conn = url.openConnection();
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = null;
				while((line = reader.readLine()) != null){
					buffer.append(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Dom4j test = new Dom4j();
			list.set_list(test.ParseXMLData_FromMusicCovery(new String(buffer)));
			
			String currentpath = list.give_currentpath();
			String currentsongname = list.give_currentsongname();
			String coverimg = list.give_currentcover();
			System.out.println("当前列表的歌数："+list.songamount);
			//
			JSONArray jArray = new JSONArray();
			JSONObject jObject = new JSONObject();
			jObject.put("path", currentpath);
			jObject.put("songname", currentsongname);
			jObject.put("coverimg", coverimg);
			jArray.add(jObject);
			out.print(jArray.toString());		
			
			out.flush();
			out.close();
		}
		
		
		
		
		if(request.getParameter("next") != null)
    	{
			list.play_nextsong();
			String currentpath = list.give_currentpath();
			String currentsongname = list.give_currentsongname();
			String coverimg = list.give_currentcover();
			System.out.println("当前列表的歌数："+list.songamount);
			System.out.println(currentsongname);
			//
			JSONArray jArray = new JSONArray();
			JSONObject jObject = new JSONObject();
			jObject.put("path", currentpath);
			jObject.put("songname", currentsongname);
			jObject.put("coverimg", coverimg);
			jArray.add(jObject);
			out.print(jArray.toString());
			out.flush();
			out.close();
    	}
		
		
		if (request.getParameter("comment") != null) {
			
		}
		
		
	}

}
