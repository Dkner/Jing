package com.web.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 

import com.data.vo.Singer;
import com.data.vo.Song;
import com.process.model.AI_Recommender;

import com.sun.jersey.spi.resource.Singleton;

@Produces({"application/xml"})
@Path("recommend")
@Singleton
public class RecommendWebService {
	
	AI_Recommender rs = new AI_Recommender();
	XmlDataFactory factory = new XmlDataFactory();

@GET
public String testoutput() {
    return "<xml>RECOMMEND</xml>";
}

@GET
@Path("random")
public String RandomRecommend() {
	System.out.println("webservice随机推荐");
	
	List result = rs.suibiantingting();
	
	return factory.ProductXmlString_FromSourceData(Song.class.getSimpleName(), result);
}

@GET
@Path("similarsong/{input}")
public String SimilarSongRecommend(@PathParam("input") String input) {
	System.out.println("webservice类似歌曲推荐");
	
	List result = rs.RecommendSong_BySong(input);
	
	return factory.ProductXmlString_FromSourceData(Song.class.getSimpleName(), result);
}

@GET
@Path("similarsinger/{input}")
public String SimilarSingerRecommend(@PathParam("input") String input) {
	System.out.println("webservice类似歌手推荐");
	
	List result = rs.RecommendSimilarSinger(input);
	
	return factory.ProductXmlString_FromSourceData(Singer.class.getSimpleName(), result);
}

@POST
@Path("{add}")
@Produces("text/plain")
public String addCustomer(@PathParam("add") String ucontent) {
    //todo  
    return "add success";
} 
}
