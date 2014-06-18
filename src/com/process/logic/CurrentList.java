package com.process.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.data.vo.Song;

public class CurrentList implements CurrentListInterface{

	//當前歌曲號
	public int currentsongnumber = -1;
	//當前歌曲總數
	public int songamount = 0;
	//当前歌名
	public String songname = "";
	public String singername = "";
	//上限
	public int listsize = 20;
	//当前歌曲路徑
	public String currentpath = "";
	//当前标签条件字符串
	public String currentlabel = "";
	//当前歌曲列表list
	public List<Song> songlist = null;
	//当前currentlist的用戶id
	private String user_id = "";
	
	
	public CurrentList()
	{
		this.songlist = new ArrayList<Song>();
	}
	
	/**
	   * function 设置用户id
	   * @param String 用户id
	   * @return
	   */
	public final void set_userid(String user_id)
	{
		this.user_id = user_id;
	}
	
	/**
	   * function 得到用户id
	   * @param 
	   * @return String user_id
	   */
	public final String get_userid(){
		return this.user_id;
	}
	
	/**
	   * function 设置当前标签条件字符串
	   * @param String label字符串
	   * @return
	   */
	public final void set_currentlabel(String label){
		this.currentlabel = label;
	}
	
	/**
	   * function 得到当前标签条件字符串
	   * @param 
	   * @return String label字符串
	   */
	public final String get_currentlabel(){
		return this.currentlabel;
	}
	
	/**
	   * function 设置当前歌曲列表
	   * @param List 歌曲列表
	   * @return
	   */
	public final void set_list(List list)
	{
		this.songlist.clear();
		this.songlist = list;
		this.currentsongnumber = songlist.size()-1;
		this.songamount = songlist.size();
		if(currentsongnumber >= 0)
		{
			this.currentpath = songlist.get(currentsongnumber).getPath();
			this.songname = songlist.get(currentsongnumber).getName();
			singername = songlist.get(currentsongnumber).getSinger().getName();
		}
		else
		{
			this.currentpath = "";
			this.songname = "";
			singername = "";
		}
	}
	
	/**
	   * function 得到当前歌曲id
	   * @param 
	   * @return Integer song_id
	   */
	public final int get_currentSongId(){
		if(currentsongnumber >= 0)
			return songlist.get(currentsongnumber).getId();
		else
			return -1;
	}
	
	/**
	   * function 播放当前歌曲list中的下一首歌曲
	   * @param 
	   * @return
	   */
	public final void play_nextsong()
	{
		if(songamount <= 0)
		{
			currentsongnumber = -1;
			currentpath = "";
			songname = "";
			singername = "";
			return;
		}
		songlist.remove(currentsongnumber);
		songamount--;
		if(songamount <= 0)
		{
			currentsongnumber = -1;
			currentpath = "";
			songname = "";
			singername = "";
			return;
		}
		
		Random god = new Random();
		currentsongnumber = god.nextInt(songamount);
		
		Song song = songlist.get(currentsongnumber);
		if(currentsongnumber >= 0 && song!=null)
		{
			currentpath = song.getPath();
			songname = song.getName();
			if(song.getSinger() != null)
				singername = song.getSinger().getName();
			else
				singername = "";
		}
		else
		{
			currentpath = "";
			songname = "";
			singername = "";
		}
	}
	
	/**
	   * function 给出当前歌曲的路径
	   * @param 
	   * @return String url
	   */
	public final String give_currentpath()
	{
		return this.currentpath;
	}
	
	/**
	   * function 给出当前歌曲名
	   * @param 
	   * @return String song。name
	   */
	public final String give_currentsongname()
	{
		return this.songname;
	}

	
	public final String give_currentsingername()
	{
		return this.singername;
	}
	
	public int get_songamount() {
		return this.songamount;
	}
	
}
