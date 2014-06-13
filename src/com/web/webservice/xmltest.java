package com.web.webservice;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.data.vo.Singer;
import com.data.vo.Song;
import com.process.model.AI_Recommender;
import com.process.model.Filter;
import com.process.model.FilterChain;
import com.process.model.LabelProcessor;
import com.process.model.Page;
import com.web.xmlbean.SingerXml;
import com.web.xmlbean.SongXml;

public class xmltest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		Song song = new Song(null, "JAY", "path");
//		List<Song> songlist = new ArrayList<Song>();
//		songlist.add(song);
		
		Page page = new Page();
		page.set_pagesize(5);
		page.set_pagenow(1);
		AI_Recommender ai = new AI_Recommender();
		
		FilterChain chain = new FilterChain();
		chain.AddFilter(new Filter(4));
		chain.AddFilter(new Filter(5));
		LabelProcessor lp = new LabelProcessor();
		List<Song> songlist = lp.find_songlist_by_input(0, "周杰伦", chain, "100000000");
		//List<Singer> singerlist = ai.RecommendSinger_ByPage("100000000", page);
		
		bean2xml ex = new bean2xml();
		List<SongXml> xmlsonglist = ex.Change_songlist(songlist);
		//List<SingerXml> xmlsingerlist = ex.Change_singerlist(singerlist);
		
		Xml data = new Xml();		
		data.setSonglist(xmlsonglist);
		//data.setSingerlist(xmlsingerlist);
		
		String xmldata = "";
		
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Xml.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);		
			//jaxbMarshaller.marshal(data, System.out);
			jaxbMarshaller.marshal(data, out);
	 
			xmldata = out.toString();
	      } catch (JAXBException e) {
	    	  e.printStackTrace();
	      }
		
		System.out.println(xmldata);
		
	}

	public static void printList(List list) { 
		for(int i=0; i<list.size(); i++)
		{
			Song song = ((Song)list.get(i));
			if(song == null)
				continue;
			System.out.println(song.getName()+"\\"+song.getPath()+"\\"+song.getSinger().getName());
		}
    } 
	
}
