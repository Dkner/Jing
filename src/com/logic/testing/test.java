package com.logic.testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tool.Dom4j;

import com.data.vo.*;
import com.process.logic.CurrentList;
import com.process.logic.DJ;
import com.process.model.AI_Recommender;
import com.process.model.AssessProcessor;
import com.process.model.Filter;
import com.process.model.FilterChain;
import com.process.model.LabelProcessor;
import com.process.model.Page;
import com.web.api.UrlParser;


public class test {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		//UrlParser parser = new UrlParser();
		//String path = parser.getSongUrl("怒放的生命");
				
//		FilterChain chain = new FilterChain();
//		chain.AddFilter(new Filter(4));
//		chain.AddFilter(new Filter(5));
//		LabelProcessor lp = new LabelProcessor();
//		lp.find_songlist_by_input("周杰伦", chain);
		
		//LabelProcessor lp = new LabelProcessor();
//		String name = "蔡依林";
//		SingerDAO sd = new SingerDAO();
//		Singer singer = (Singer)(sd.findByName(name).get(0));
//		int id = singer.getSingerId();
//		System.out.println("原来歌手的id:"+id);
//	    AI_Recommender ai = new AI_Recommender();
//	    ai.RecommendSinger_BySinger(name);
//	    List song = ai.hongxindiantai("100000000");
//	    printList(song);
	    
//	    FavorDAO fd = new FavorDAO();
//	    Map<String,Object> conditions = new HashMap<String,Object>(); 
//	    conditions.put("singer_id", 1);
//	    conditions.put("fans_id", "100000000");
//	    System.out.println(fd.findByMultiProperty(conditions).size());
	    
		//AssessProcessor ap = new AssessProcessor();
		//ap.collect_singer("100000000", "邓丽君");
		
		UsertagDAO utd = new UsertagDAO();
		List temp = utd.findTopFeature("100000001", 4);
		List<Label> labellist = new ArrayList<Label>();
		for(int i=0; i<temp.size(); i++)
		{
			Usertag tag = (Usertag) temp.get(i);
			labellist.add(tag.getLabel());
		}
		for(int i=0; i<labellist.size(); i++)
		{
			Label label = (Label) labellist.get(i);
			System.out.println(label.getLabel());
		}
	}
	
	public static void printMap(Map<String,Object> map) { 
		System.out.println("具体匹配情况:");
		if(map.size() == 0)
			System.out.println("空");
        for (Map.Entry<String,Object> entry : map.entrySet()) { 
            System.out.println(entry.getKey() + "  "
                    + entry.getValue()); 
        } 
    } 
	
	public static void printList(List list) { 
		for(int i=0; i<list.size(); i++)
		{
			Song song = ((Song)list.get(i));
			System.out.println(song.getName()+"/"+song.getPath()+"/"+song.getAlbum().getName()+"/"+song.getSinger().getName());
		}
    } 

	
}

