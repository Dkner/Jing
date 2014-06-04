package com.web.servlet;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.process.logic.CurrentList;
import com.process.logic.DJ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MusicControlHandler {

	public void next(PrintWriter out, DJ factory) throws UnsupportedEncodingException{
		factory.play_nextsong();
		String currentpath = factory.give_currentpath();
		String currentsongname = factory.give_currentsongname();
		String currentsingername = factory.give_currentsingername();
		String currentlabel = factory.get_currentlabel();
		System.out.println("当前列表的歌数:"+factory.get_songamount()+"\n当前播放歌曲:"+currentsongname+"\n当前歌手名:"+currentsingername+"\n当前标签:"+currentlabel);
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
	
}
