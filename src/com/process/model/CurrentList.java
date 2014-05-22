package com.process.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.data.vo.Song;
import com.data.vo.Usertag;
import com.data.vo.HibernateSessionFactory;

public class CurrentList {

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
	//当前歌曲列表list
	public List songlist = null;
	//当前currentlist的用戶id
	private String user_id = null;
	
	
	public CurrentList()
	{
	
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
	public final void set_list(List list) throws UnsupportedEncodingException
	{
		this.songlist = list;
		this.currentsongnumber = songlist.size()-1;
		this.songamount = songlist.size();
		if(currentsongnumber >= 0)
		{
			this.currentpath = ((Song)songlist.get(currentsongnumber)).getPath();
			this.songname = ((Song)songlist.get(currentsongnumber)).getName();
		}
		else
		{
			this.currentpath = "";
			this.songname = "";
		}
	}
	
	/**
	   * function 得到当前歌曲id
	   * @param 
	   * @return Integer song_id
	   */
	public final int get_currentSongId(){
		if(currentsongnumber >= 0)
			return ((Song)songlist.get(currentsongnumber)).getId();
		else
			return -1;
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
			return;
		}
		songamount--;
		songlist.remove(currentsongnumber);
		if(songamount <= 0)
		{
			currentpath = "";
			songname = "";
			return;
		}
		Random god = new Random();
		currentsongnumber = god.nextInt(songamount);
		if(currentsongnumber >= 0)
		{
			currentpath = ((Song)songlist.get(currentsongnumber)).getPath();
			this.songname = ((Song)songlist.get(currentsongnumber)).getName();
		}
		else
		{
			currentpath = "";
			songname = "";
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

	
}
