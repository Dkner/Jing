package com.process.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class CurrentWebList {

	//當前歌曲號
	public int currentsongnumber = -1;
	//當前歌曲總數
	public int songamount = 0;
	//当前歌名
	public String songname = "";
	//上限
	public int listsize = 20;
	//当前歌曲路徑
	public String currentpath = null;
	//当前标签条件字符串
	public String currentlabel = null;
	//cover
	public String coverimg = null;
	//当前歌曲列表list
	public List songlist = null;
	
	
	public CurrentWebList()
	{
	
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
	public final void set_list(List list) throws UnsupportedEncodingException
	{
		this.songlist = list;
		this.currentsongnumber = songlist.size()-1;
		this.songamount = songlist.size();
		if(currentsongnumber >= 0)
		{
			this.currentpath = ((WebSong)songlist.get(currentsongnumber)).getUrl();
			this.songname = ((WebSong)songlist.get(currentsongnumber)).getTitle();
			this.coverimg = ((WebSong)songlist.get(currentsongnumber)).getCoverimg();
		}
		else
		{
			this.currentpath = "";
			this.songname = "";
			coverimg = "";
		}
	}
	
	/**
	   * function 播放当前歌曲list中的下一首歌曲
	   * @param 
	   * @return
	   */
	public final void play_nextsong() throws UnsupportedEncodingException
	{

		if(songamount == 0)
		{
			currentsongnumber = -1;
			currentpath = "";
			songname = "";
			coverimg = "";
			return;
		}
		songamount--;
		songlist.remove(currentsongnumber);
		if(songamount <= 0)
		{
			currentpath = "";
			songname = "";
			coverimg = "";
			return;
		}
		Random god = new Random();
		currentsongnumber = god.nextInt(songamount);
		if(currentsongnumber >= 0)
		{
			currentpath = ((WebSong)songlist.get(currentsongnumber)).getUrl();
			this.songname = ((WebSong)songlist.get(currentsongnumber)).getTitle();
			this.coverimg = ((WebSong)songlist.get(currentsongnumber)).getCoverimg();
		}
		else
		{
			currentpath = "";
			songname = "";
			coverimg = "";
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
	
	public final String give_currentcover()
	{
		return this.coverimg;
	}

	
}
