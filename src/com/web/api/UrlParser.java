package com.web.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import tool.Dom4j;

public class UrlParser {

	public static String parseUrl(String songname){
		StringBuffer buffer = new StringBuffer();
		
		try {
			URL url = new URL("http://box.zhangmen.baidu.com/x?op=12&count=1&title="+songname+"$$");
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
		
		return new String(buffer);
	}
	
	@SuppressWarnings("static-access")
	public String getSongUrl(String songname){
		String result;		
		String data;
		
		data = parseUrl(songname);
		
		Dom4j parser = new Dom4j();
		result = parser.ParseXMLData_FromBaiDu(data);
		return result;
	}
	
}
