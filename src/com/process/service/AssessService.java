package com.process.service;

import java.util.List;

import com.data.vo.User;
import com.process.model.AssessProcessor;
import com.process.model.Page;

public interface AssessService {
	
	/**
	   * function 标记歌曲为喜欢的歌曲
	   * @param String user_id,song_id
	   * @return
	   */
	public void give_loveassess(String user_id, int song_id);
	
	/**
	   * function 标记歌曲为讨厌的歌曲
	   * @param String user_id,song_id
	   * @return
	   */
	public void give_hateveassess(String user_id, int song_id);

	/**
	   * function 给予歌曲评论
	   * @param String comment,user_id,song_id
	   * @return
	   */
	public void give_commentassess(String comment, String user_id, int song_id);

	/**
	   * function 给予歌曲评分
	   * @param String level,user_id,song_id
	   * @return
	   */
	public void give_levelassess(String level, String user_id, int song_id);
	
	/**
	   * function 添加用户喜好标签
	   * @param String user_id,usertag
	   * @return boolean 操作是否成功
	   */
	public boolean customize_usertag(String user_id, String usertag);
	
	/**
	   * function 删除用户标签
	   * @param String user_id,usertag
	   * @return
	   */
	public void undo_usertag(String user_id, String usertag);
	
	/**
	   * function 收藏艺人
	   * @param String user_id,usertag
	   * @return
	   */
	public boolean collect_singer(String user_id, String singername);
	
	
	public List get_RecordsByPage(String user_id, Page page);
	
	public User get_User(String user_id);
	
	public List get_FavorSingerByPage(String usr_id, Page page);
}
