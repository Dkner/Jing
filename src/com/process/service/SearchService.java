package com.process.service;

import java.util.List;

import com.data.vo.Song;
import com.process.model.FilterChain;


public interface SearchService {
	
	/**
	   * function 匹配标签
	   * @param String word
	   * @return List Label
	   */
	public List find_matchlabel(String word);

	
	/**
	   * function 全智能搜索
	   * @param String Input
	   * @return List Song
	   */
	public List find_songlist_by_input(int match_degree_minus, String input, FilterChain chain, String user_id);
	
	/**
	   * function 全智能搜索,基础形式,webservice专用
	   * @param String Input
	   * @return List Song
	   */
	public List find_songlist_by_input_Basic(String input);
	
	
	public Song find_song_by_id(int song_id);
	
}
