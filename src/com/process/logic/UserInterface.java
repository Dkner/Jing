package com.process.logic;

import java.util.List;

import com.process.model.CurrentList;

public interface UserInterface {
	
	/**
	   * function 根据歌曲名搜索到歌曲列表并填充到用户的当前歌曲列表
	   * @param String CurrentList song.name,list
	   * @return
	   */
	public void SongnameSearchingProcess(String songname, CurrentList list);

	/**
	   * function 根据专辑名搜索到歌曲列表并填充到用户的当前歌曲列表
	   * @param String CurrentList album.name,list
	   * @return
	   */
	public void AlbumSearchingProcess(String albumname, CurrentList list);
	
	/**
	   * function 根据标签条件字符串搜索到歌曲列表并填充到用户的当前歌曲列表
	   * @param String Integer CurrentList words,minus,list
	   * @return
	   */
	public void KeywordSearchingProcess(String words, int minus, CurrentList list);
	
	/**
	   * function 红心电台填充当前歌曲列表
	   * @param String CurrentList user_id,list
	   * @return
	   */
	public void LoveRecommendProcess(String user_id, CurrentList list);
	
	/**
	   * function 红心电台填充当前歌曲列表
	   * @param String CurrentList user_id,list
	   * @return
	   */
	public List GetLoveSongListProcess(String user_id);
	
	/**
	   * function 随便听听填充当前歌曲列表
	   * @param CurrentList list
	   * @return
	   */
	public void RandomRecommendProcess(CurrentList list);
	
	/**
	   * function 智能推荐填充当前歌曲列表
	   * @param String CurrentList user_id,list
	   * @return
	   */
	public void SmartRecommendProcess(String user_id, CurrentList list);
	
	/**
	   * function 给予歌曲评分
	   * @param String level,user_id,song_id
	   * @return
	   */
	public void GradeMarkingProcess(String level, String user_id, int song_id);
	
	/**
	   * function 给予歌曲评论
	   * @param String comment,user_id,song_id
	   * @return
	   */
	public void CommentProcess(String comment, String user_id, int song_id);
	
	/**
	   * function 标记歌曲为讨厌的歌曲
	   * @param String user_id,song_id
	   * @return
	   */
	public void HateProcess(String user_id, int song_id);
	
	/**
	   * function 标记歌曲为喜欢的歌曲
	   * @param String user_id,song_id
	   * @return
	   */
	public void LoveProcess(String user_id, int song_id);
	
	/**
	   * function 添加用户喜好标签
	   * @param String user_id,usertag
	   * @return boolean 操作是否成功
	   */
	public boolean CustomizeTagProcess(String user_id, String usertag);
	
	/**
	   * function 删除用户标签
	   * @param String user_id,usertag
	   * @return
	   */
	public void UndoTagProcess(String user_id, String usertag);
	
	/**
	   * function 匹配标签
	   * @param String word
	   * @return List Label
	   */
	public List find_MatchLabelProcess(String word);
	
	/**
	   * function 获得用户的喜好标签
	   * @param String user_id
	   * @return List UserTag
	   */
	public List get_usertagProcess(String user_id);
	
	/**
	   * function 获得随机标签
	   * @param 
	   * @return List Label
	   */
	public List create_randomlabelProcess();
	
}
