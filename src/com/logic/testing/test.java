package com.logic.testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import tool.Dom4j;

import com.data.vo.*;
import com.process.logic.DJ;
import com.process.model.AI_Recommender;
import com.process.model.CurrentList;
import com.process.model.LabelProcessor;
import com.process.model.Page;
import com.web.api.UrlParser;


public class test {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		//LabelProcessor lp = new LabelProcessor();
		//lp.input_song();
		//AI_Recommender ai = new AI_Recommender();
		//ai.input_tag();
		
		//CurrentList list = new CurrentList();
		//list.set_userid("u6");
		
		//ProcessingFactory factory = new ProcessingFactory();
		//factory.KeywordSearchingProcess("午后", 0, list);
		
		//UrlParser parser = new UrlParser();
		//String path = parser.getSongUrl("怒放的生命");
		
//		StringBuffer buffer = new StringBuffer();
//		try {
//			URL url = new URL("http://box.zhangmen.baidu.com/x?op=12&count=1&title=我$$");
//			URLConnection conn = url.openConnection();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			String line = null;
//			while((line = reader.readLine()) != null){
//				buffer.append(line);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Dom4j test = new Dom4j();			
//		String path = test.ParseXMLData_FromBaiDu(new String(buffer));
		
		
		
		//LabelProcessor lp = new LabelProcessor();
		//lp.set_words("舞曲/说唱/恋爱");
		//lp.find_songlist_by_words(0);
		//lp.find_songlist_by_input("舞曲/说唱/恋爱");
		SongDAO sd = new SongDAO();
		SingerDAO sid = new SingerDAO();
		AI_Recommender ai = new AI_Recommender();
		ai.RecommendSinger_BySinger(sid.findById(1));
	}

}

