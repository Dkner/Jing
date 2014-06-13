package com.web.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.web.xmlbean.SingerXml;
import com.web.xmlbean.SongXml;


@XmlRootElement
public class Xml {
	
	private List<SongXml> songlist = new ArrayList<SongXml>();	
	private List<SingerXml> singerlist = new ArrayList<SingerXml>();
	
	

	public List<SingerXml> getSingerlist() {
		return singerlist;
	}

	public void setSingerlist(List<SingerXml> singerlist) {
		this.singerlist = singerlist;
	}

	public List<SongXml> getSonglist() {
		return songlist;
	}

	public void setSonglist(List<SongXml> songlist) {
		this.songlist = songlist;
	}
	
	
}
