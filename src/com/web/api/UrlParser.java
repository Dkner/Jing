package com.web.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.data.vo.HibernateSessionFactory;
import com.data.vo.Song;
import com.data.vo.SongDAO;

import tool.Dom4j;

public class UrlParser {

	public static String parseUrl(String songname){
		StringBuffer buffer = new StringBuffer();
		
		try {
			URL url = new URL("http://box.zhangmen.baidu.com/x?op=12&count=1&title="+songname.trim()+"$$");
			URLConnection conn = url.openConnection();
			conn.setDoInput(true);
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
	
	//使用API导入歌曲的url
		public void importUrl(List result){
			
			System.out.println("开始导入歌曲url");
			
			SongDAO sd = new SongDAO();
			Session session = HibernateSessionFactory.getSession();
			Transaction tst = session.beginTransaction();	
			
			for(int i=0; i<result.size(); i++)
			{		
				Song tempsong = sd.findById(((Song)result.get(i)).getId());
				//if(tempsong != null && tempsong.getPath() != null && tempsong.getPath().trim().length()>0)
				//	continue;
				
				String currentpath = this.getSongUrl(((Song)result.get(i)).getName());
				if(currentpath == null || currentpath.trim().length() == 0)
				{
					result.remove(i);
					continue;
				}
				
				System.out.println(tempsong.getName()+" Url import:"+currentpath);
				tempsong.setPath(currentpath);
				sd.save(tempsong);
			}
			
			tst.commit();
			session.close();
		}
}
