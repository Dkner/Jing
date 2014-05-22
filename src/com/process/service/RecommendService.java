package com.process.service;

import java.util.List;

import com.process.model.AI_Recommender;

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
	
}
