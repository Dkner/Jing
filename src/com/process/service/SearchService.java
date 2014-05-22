package com.process.service;

import java.util.List;

import com.process.model.Filter;
import com.process.model.LabelProcessor;

public interface SearchService {
	
	/**
	   * function 匹配标签
	   * @param String word
	   * @return List Label
	   */
	public List find_matchlabel(String word);
	
	/**
	   * function 歌名搜索
	   * @param String song。name
	   * @return List Song
	   */
	public List find_songlist_by_songname(String songname);
	
	/**
	   * function 专辑搜索
	   * @param String album。name
	   * @return List Song
	   */
	public List find_songlist_by_albumname(String albumname);
	
	/**
	   * function 歌手搜索
	   * @param String album。name
	   * @return List Song
	   */
	public List find_songlist_by_singer(String singer);
	
	/**
	   * function 标签搜索
	   * @param String Integer words,minus
	   * @return List Song
	   */
	public List find_songlist_by_words(String words, int minus);
	
	/**
	   * function 全智能搜索
	   * @param String Input
	   * @return List Song
	   */
	public List find_songlist_by_input(String input, Filter filter);
	
}
