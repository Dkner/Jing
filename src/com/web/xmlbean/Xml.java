package com.web.xmlbean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class Xml {
	
	private List<SongXml> songlist = new ArrayList<SongXml>();	
	private List<SingerXml> singerlist = new ArrayList<SingerXml>();
	private List<LabelXml> labellist = new ArrayList<LabelXml>();
	
	
	
	public List<LabelXml> getLabellist() {
		return labellist;
	}

	public void setLabellist(List<LabelXml> labellist) {
		this.labellist = labellist;
	}

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
