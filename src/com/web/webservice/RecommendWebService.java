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
import com.process.model.Page;
import com.process.service.RecommendImplement;

import com.sun.jersey.spi.resource.Singleton;

@Produces({"application/xml"})
@Path("recommend")
@Singleton
public class RecommendWebService {
	
	UserVerification verifier = new UserVerification();
	RecommendImplement rs = new RecommendImplement();
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
@Path("ranking")
public String RankingRecommend() {
	System.out.println("webservice排行榜");
	
	Page page = new Page();
	page.set_pagesize(20);
	page.set_pagenow(1);
	List result = rs.Recommend_ByRanking(page);
	
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

@GET
@Path("{userid}/{password}/lovesong")
public String LoveRecommend(@PathParam("userid") String id, @PathParam("password") String password) {
	System.out.println("webservice用户红心电台");
	
	if(verifier.verify(id, password))
	{
		List result = rs.hongxindiantai(id);
	
		return factory.ProductXmlString_FromSourceData(Song.class.getSimpleName(), result);
	}
	else
		return factory.ProductXmlString_NoUser();
}

@GET
@Path("{userid}/{password}/private")
public String AIRecommend(@PathParam("userid") String id, @PathParam("password") String password) {
	System.out.println("webservice用户智能推荐");
	
	if(verifier.verify(id, password))
	{
		List result = rs.zhinengtuijian(id);
	
		return factory.ProductXmlString_FromSourceData(Song.class.getSimpleName(), result);
	}
	else
		return factory.ProductXmlString_NoUser();
}

@GET
@Path("{userid}/{password}/collaboration")
public String U2URecommend(@PathParam("userid") String id, @PathParam("password") String password) {
	System.out.println("webservice用户智能推荐");
	
	if(verifier.verify(id, password))
	{
		List result = rs.GuessSong_ByPage(id, new Page());
	
		return factory.ProductXmlString_FromSourceData(Song.class.getSimpleName(), result);
	}
	else
		return factory.ProductXmlString_NoUser();
}

@POST
@Path("{add}")
@Produces("text/plain")
public String addCustomer(@PathParam("add") String ucontent) {
    //todo  
    return "add success";
} 
}
