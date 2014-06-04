package com.process.service;

import java.util.List;

import com.data.vo.Singer;
import com.data.vo.Song;
import com.process.model.AI_Recommender;
import com.process.model.Page;

public interface RecommendService {
	
	/**
	   * function 获得用户的喜好标签
	   * @param String user_id
	   * @return List UserTag
	   */
	public List get_UserTag(String user_id);
	
	/**
	   * function 获得随机标签
	   * @param 
	   * @return List Label
	   */
	public List create_randomlabel();
	
	/**
	   * function 红心电台
	   * @param String user_id
	   * @return List Song
	   */
	public List hongxindiantai(String user_id);
	
	/**
	   * function 随便听听
	   * @param 
	   * @return List Song
	   */
	public List suibiantingting();
	
	/**
	   * function 智能推荐
	   * @param String user_id
	   * @return List Song
	   */
	public List zhinengtuijian(String user_id);
	
	/**
	   * function 根据一个歌手推荐类似的歌手
	   * @param String user_id
	   * @return List Song
	   */
	public List RecommendSinger_BySinger(String singername);
	
	/**
	   * function 根据一首歌曲推荐类似的歌曲
	   * @param Song Integer song,tracksnumber
	   * @return List Song
	   */
	@SuppressWarnings("unchecked")
	public List RecommendSong_BySong(int songId);
	
	public List RecommendSinger_ByPage(String user_id, Page page);
	
	public List Recommend_ByRanking(Page page);
	
	public List RecommendSong_BySingers(String user_id, Page page);
	
	public String get_currentlabel();
	
}
