package com.process.service;

import java.util.List;

import com.process.model.AI_Recommender;
import com.process.model.Page;

public class RecommendImplement implements RecommendService{

	AI_Recommender ai = new AI_Recommender();
	
	public List create_randomlabel() {
		// TODO Auto-generated method stub
		return ai.create_randomlabel();
	}

	public List hongxindiantai(String user_id) {
		// TODO Auto-generated method stub
		return ai.hongxindiantai(user_id);
	}

	public List suibiantingting() {
		// TODO Auto-generated method stub
		return ai.suibiantingting();
	}

	public List zhinengtuijian(String user_id) {
		// TODO Auto-generated method stub
		return ai.zhinengtuijian(user_id);
	}

	public List RecommendSinger_BySinger(String singername) {
		// TODO Auto-generated method stub
		return ai.RecommendSinger_BySinger(singername);
	}

	public List RecommendSong_BySong(String songname) {
		// TODO Auto-generated method stub
		return ai.RecommendSong_BySong(songname);
	}

	public List RecommendSinger_ByPage(String user_id, Page page) {
		// TODO Auto-generated method stub
		return ai.RecommendSinger_ByPage(user_id, page);
	}

	public List GuessSong_ByPage(String user_id, Page page) {
		// TODO Auto-generated method stub
		return ai.GuessSong_ByPage(user_id, page);
	}

	public List Recommend_ByRanking(Page page) {
		// TODO Auto-generated method stub
		return ai.Recommend_ByRanking(page);
	}

	public List RecommendSong_BySingers(String user_id, Page page) {
		// TODO Auto-generated method stub
		return ai.RecommendSong_BySingers(user_id, page);
	}

	public String get_currentlabel() {
		// TODO Auto-generated method stub
		return ai.get_currentlabel();
	}

	public List RecommendSimilarSinger(String input) {
		// TODO Auto-generated method stub
		return ai.RecommendSimilarSinger(input);
	}

}
