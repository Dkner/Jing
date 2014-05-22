package com.web.servlet;

import java.io.PrintWriter;

import com.process.logic.DJ;
import com.process.model.CurrentList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RecommendHandler {

	public void LookandListen(PrintWriter out, CurrentList list, DJ factory){
		factory.RandomRecommendProcess(list);
		String currentpath = list.give_currentpath();
		String currentsongname = list.give_currentsongname();
		String currentlabel = list.get_currentlabel();
		System.out.println("当前列表的歌数："+list.songamount);
		//
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		jObject.put("path", currentpath);
		jObject.put("songname", currentsongname);
		jObject.put("label", currentlabel);
		jArray.add(jObject);
		out.print(jArray.toString());
		out.flush();
		out.close();
	}
	
	public void LoveSong(PrintWriter out, CurrentList list, DJ factory){
		factory.LoveRecommendProcess(list.get_userid(), list);
		String currentpath = list.give_currentpath();
		String currentsongname = list.give_currentsongname();
		System.out.println("当前列表的歌数："+list.songamount);
		//
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		jObject.put("path", currentpath);
		jObject.put("songname", currentsongname);
		jArray.add(jObject);
		out.print(jArray.toString());
		out.flush();
		out.close();
	}
	
	public void AI(PrintWriter out, CurrentList list, DJ factory){
		factory.SmartRecommendProcess(list.get_userid(), list);
		String currentpath = list.give_currentpath();
		String currentsongname = list.give_currentsongname();
		String currentlabel = list.get_currentlabel();
		System.out.println("当前列表的歌数："+list.songamount);
		//
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		jObject.put("path", currentpath);
		jObject.put("songname", currentsongname);
		jObject.put("label", currentlabel);
		jArray.add(jObject);
		out.print(jArray.toString());
		out.flush();
		out.close();
	}
	
	
	
	
	
	
	
	
}
