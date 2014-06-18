package com.process.service;

import java.util.List;
import com.process.model.AI_Recommender;
import com.process.model.Page;

@javax.jws.WebService(targetNamespace = "http://service.process.com/", serviceName = "RecommendImplementService", portName = "RecommendImplementPort", wsdlLocation = "WEB-INF/wsdl/RecommendImplementService.wsdl")
public class RecommendImplementDelegate {

	com.process.service.RecommendImplement recommendImplement = new com.process.service.RecommendImplement();

	public List create_randomlabel() {
		return recommendImplement.create_randomlabel();
	}

	public List hongxindiantai(String user_id) {
		return recommendImplement.hongxindiantai(user_id);
	}

	public List suibiantingting() {
		return recommendImplement.suibiantingting();
	}

	public List zhinengtuijian(String user_id) {
		return recommendImplement.zhinengtuijian(user_id);
	}

	public List RecommendSinger_BySinger(String singername) {
		return recommendImplement.RecommendSinger_BySinger(singername);
	}

	public List RecommendSong_BySong(String songname) {
		return recommendImplement.RecommendSong_BySong(songname);
	}

	public List RecommendSinger_ByPage(String user_id, Page page) {
		return recommendImplement.RecommendSinger_ByPage(user_id, page);
	}

	public List GuessSong_ByPage(String user_id, Page page) {
		return recommendImplement.GuessSong_ByPage(user_id, page);
	}

	public List Recommend_ByRanking(Page page) {
		return recommendImplement.Recommend_ByRanking(page);
	}

	public List RecommendSong_BySingers(String user_id, Page page) {
		return recommendImplement.RecommendSong_BySingers(user_id, page);
	}

	public String get_currentlabel() {
		return recommendImplement.get_currentlabel();
	}

	public List RecommendSimilarSinger(String input) {
		return recommendImplement.RecommendSimilarSinger(input);
	}

}