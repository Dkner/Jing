package com.process.logic;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.process.model.AI_Recommender;
import com.process.model.AssessProcessor;
import com.process.model.CurrentList;
import com.process.model.Filter;
import com.process.model.LabelProcessor;
import com.process.service.AssessService;
import com.process.service.RecommendService;
import com.process.service.SearchService;

public class DJ implements UserInterface{

	private AssessService assessservice;
	private SearchService searchservice;
	private RecommendService recommendservice;
	
	
	public DJ(){
		searchservice = new LabelProcessor();
		recommendservice = new AI_Recommender();
		assessservice = new AssessProcessor();
	}
	
	/**
	   * function 根据歌曲名搜索到歌曲列表并填充到用户的当前歌曲列表
	   * @param String CurrentList song.name,list
	   * @return
	   */
	public final void SongnameSearchingProcess(String songname, CurrentList list){
		List result = searchservice.find_songlist_by_songname(songname);
		if(result.size() == 0)
			return;
		
		try {
				list.set_currentlabel(songname);
				list.set_list(result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	   * function 根据专辑名搜索到歌曲列表并填充到用户的当前歌曲列表
	   * @param String CurrentList album.name,list
	   * @return
	   */
	public final void AlbumSearchingProcess(String albumname, CurrentList list){
		List result = searchservice.find_songlist_by_albumname(albumname);
		if(result.size() == 0)
			return;
		
		try {
				list.set_currentlabel(albumname);
				list.set_list(result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	   * function 根据标签条件字符串搜索到歌曲列表并填充到用户的当前歌曲列表
	   * @param String Integer CurrentList words,minus,list
	   * @return
	   */
	public final void KeywordSearchingProcess(String words, int minus, CurrentList list){
		List result = searchservice.find_songlist_by_words(words, minus);
		if(result.size() == 0)
			return;
		
		try {
				list.set_currentlabel(words);
				list.set_list(result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	   * function 根据用户输入的字符串搜索到歌曲列表并填充到用户的当前歌曲列表
	   * @param String Integer CurrentList words,minus,list
	   * @return boolean 找到与否
	   */
	public final boolean InputSearchingProcess(String input, CurrentList list){
		List result = searchservice.find_songlist_by_input(input, new Filter());
		if(result.size() == 0)
			return false;
		
		try {
				list.set_currentlabel(input);
				list.set_list(result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	/**
	   * function 红心电台填充当前歌曲列表
	   * @param String CurrentList user_id,list
	   * @return
	   */
	public final void LoveRecommendProcess(String user_id, CurrentList list){
		List result = recommendservice.hongxindiantai(user_id);
		if(result.size() == 0)
			return;
		
		try {
				list.set_list(result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	   * function 得到用户喜欢的歌曲list
	   * @param String CurrentList user_id,list
	   * @return
	   */
	public final List GetLoveSongListProcess(String user_id){
		List result = recommendservice.hongxindiantai(user_id);
		return result;
	}
	
	/**
	   * function 随便听听填充当前歌曲列表
	   * @param CurrentList list
	   * @return
	   */
	public final void RandomRecommendProcess(CurrentList list){
		List result = recommendservice.suibiantingting();
		if(result.size() == 0)
			return;
		
		try {
				list.set_list(result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	   * function 智能推荐填充当前歌曲列表
	   * @param String CurrentList user_id,list
	   * @return
	   */
	public final void SmartRecommendProcess(String user_id, CurrentList list){
		List result = recommendservice.zhinengtuijian(user_id);
		if(result.size() == 0)
			return;
		
		try {
				list.set_list(result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	   * function 给予歌曲评分
	   * @param String level,user_id,song_id
	   * @return
	   */
	public final void GradeMarkingProcess(String level, String user_id, int song_id){
		assessservice.give_levelassess(level, user_id, song_id);
	}
	
	/**
	   * function 给予歌曲评论
	   * @param String comment,user_id,song_id
	   * @return
	   */
	public final void CommentProcess(String comment, String user_id, int song_id){
		assessservice.give_commentassess(comment, user_id, song_id);
	}
	
	/**
	   * function 标记歌曲为讨厌的歌曲
	   * @param String user_id,song_id
	   * @return
	   */
	public final void HateProcess(String user_id, int song_id){
		assessservice.give_hateveassess(user_id, song_id);
	}
	
	/**
	   * function 标记歌曲为喜欢的歌曲
	   * @param String user_id,song_id
	   * @return
	   */
	public final void LoveProcess(String user_id, int song_id){
		assessservice.give_loveassess(user_id, song_id);
	}
	
	/**
	   * function 添加用户喜好标签
	   * @param String user_id,usertag
	   * @return boolean 操作是否成功
	   */
	public final boolean CustomizeTagProcess(String user_id, String usertag){
		return assessservice.customize_usertag(user_id, usertag);
	}
	
	/**
	   * function 删除用户标签
	   * @param String user_id,usertag
	   * @return
	   */
	public final void UndoTagProcess(String user_id, String usertag){
		assessservice.undo_usertag(user_id, usertag);
	}
	
	/**
	   * function 匹配标签
	   * @param String word
	   * @return List Label
	   */
	public final List find_MatchLabelProcess(String word){
		return searchservice.find_matchlabel(word);
	}
	
	/**
	   * function 获得用户的喜好标签
	   * @param String user_id
	   * @return List UserTag
	   */
	public final List get_usertagProcess(String user_id)
	{
		return recommendservice.get_UserTag(user_id);
	}
	
	/**
	   * function 获得随机标签
	   * @param 
	   * @return List Label
	   */
	public final List create_randomlabelProcess()
	{
		return recommendservice.create_randomlabel();
	}
	

}
