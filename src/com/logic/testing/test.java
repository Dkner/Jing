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
import com.process.model.Filter;
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
				
		
		//LabelProcessor lp = new LabelProcessor();
		//lp.find_songlist_by_input("王力宏/舞曲", new Filter());
		
		String path = "http://zhangmenshiting.baidu.com/data2/music/2007566813/2007566813.mp3?xcode=68d49e1d7f80a534b08e2e0331640eeca9a15a6878ec7a91&mid=0.03356041476357";
		System.out.println(path.length());
		
	}

}

