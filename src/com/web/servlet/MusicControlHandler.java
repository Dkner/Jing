package com.web.servlet;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.process.logic.DJ;
import com.process.model.CurrentList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MusicControlHandler {

	public void next(PrintWriter out, CurrentList list, DJ factory) throws UnsupportedEncodingException{
		list.play_nextsong();
		String currentpath = list.give_currentpath();
		String currentsongname = list.give_currentsongname();
		System.out.println("当前列表的歌数："+list.songamount);
		System.out.println(currentsongname);
		//
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		jObject.put("path", currentpath);
		jObject.put("songname", currentsongname);
		jArray.add(jObject);
		out.print(jArray.toString());
		//out.print(currentpath);
		out.flush();
		out.close();
	}
	
}
