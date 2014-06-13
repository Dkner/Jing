package com.web.webservice;

import java.util.ArrayList;
import java.util.List;

import com.data.vo.Singer;
import com.data.vo.Song;
import com.web.xmlbean.SingerXml;
import com.web.xmlbean.SongXml;

public class bean2xml {
	
	
	public List<SongXml> Change_songlist(List<Song> songlist){
		
		List<SongXml> result = new ArrayList<SongXml>();
		
		if(songlist == null ||songlist.size() == 0)
			return result;
			
			for(int i=0; i<songlist.size(); i++)
			{
				Song song = songlist.get(i);
				SongXml xmlsong = new SongXml();
				xmlsong.setSongname(song.getName());
				xmlsong.setUrl(song.getPath());
				if(song.getScore() != null)
					xmlsong.setScore(song.getScore().toString());
				else
					xmlsong.setScore("0");
				if(song.getSinger() != null)
					xmlsong.setSinger(song.getSinger().getName());
				else
					xmlsong.setSinger("unknown");
				if(song.getAlbum() != null)
					xmlsong.setAlbum(song.getAlbum().getName());
				else
					xmlsong.setAlbum("unknown");
				
				result.add(xmlsong);
			}
			
		return result;
	     
	}
	
	
	
	public List<SingerXml> Change_singerlist(List<Singer> singerlist){
		
		List<SingerXml> result = new ArrayList<SingerXml>();
		
			for(int i=0; i<singerlist.size(); i++)
			{
				Singer singer = singerlist.get(i);
				SingerXml xmlsinger = new SingerXml();
				xmlsinger.setName(singer.getName());
				xmlsinger.setNationality(singer.getNationality());
				xmlsinger.setBriefing(singer.getBriefing());
				
				result.add(xmlsinger);
			}
			
		return result;
	     
	}
	
	
	
	
	
	
	
	
}
