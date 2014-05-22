package com.process.model;

import java.util.ArrayList;
import java.util.List;

import com.data.vo.*;


public class Page {
	
	public static int pagesize = 3;
	public int pagenow;
	public int pagecount;
	public int allcount;
	public String songname = "";
	public int level = 0;
	
	//DAO
	@SuppressWarnings("rawtypes")
	private List pagelist = null;
	private List taglist = null;
	public Song song;
	private AssessDAO ad = null;
	private SongDAO sd = null;
	
	
	public Page()
	{
		
	}
	
	public Page(String songname)
	{
		this.songname = songname;

		init();
	}
	
	@SuppressWarnings("unchecked")
	void init()
	{
		sd = new SongDAO();
		ad = new AssessDAO();
		pagelist = new ArrayList();
		taglist = new ArrayList();
		//
		@SuppressWarnings("rawtypes")
		List temp = sd.findByName(songname);
		this.song = (Song)(temp.get(0));
		taglist.addAll(this.song.getTags());
		
		List set = new ArrayList(); 
		set.addAll(this.song.getAssesses());
		allcount =  set.size();	
		for(int k=0; k<allcount; k++)
		{
			String templevel = ((Assess)(set.get(k))).getLevel();
			if(templevel != null)
			{
				this.level += Integer.parseInt(templevel);
			}
		}
		if(allcount != 0)
			this.level = this.level/allcount;
		
		
		if(allcount%pagesize != 0)
    		pagecount = allcount/pagesize + 1;
    	else
    		pagecount = allcount/pagesize;
		
		//默认第一页
		set_pagenow(1);
	}
	
	/**
	   * function 当前歌曲的评论翻到下一页
	   * @param 
	   * @return
	   */
	public final void next()
	{
		set_pagenow(this.pagenow+1);
	}
	
	/**
	   * function 当前歌曲的评论翻到上一页
	   * @param 
	   * @return
	   */
	public final void back()
	{
		set_pagenow(this.pagenow-1);
	}
	
	/**
	   * function 当前歌曲的评论翻到某一页
	   * @param 
	   * @return
	   */
	public final boolean set_pagenow(int n)
	{
		if(n>0 && n<=pagecount)
		{
			pagenow = n;
			set_pagelist();
			return true;
		}
		else
			return false;
	}
	
	/**
	   * function 设置当前歌曲的分页评论
	   * @param 
	   * @return
	   */
	@SuppressWarnings("unchecked")
	private final void set_pagelist()
	{	
		List temp = new ArrayList();
		temp.addAll(this.song.getAssesses());
		
		if(temp.size() <= (pagenow-1)*pagesize)
			return;
		else
		{
			//System.out.println("temp: "+temp.size());
			this.pagelist.clear();
			for(int j=(pagenow-1)*pagesize; j<(pagenow-1)*pagesize+pagesize && j<temp.size(); j++)
			{
				this.pagelist.add(temp.get(j));
			}
		}
		
		this.print();
	}
	
	/**
	   * function 得到当前歌曲的分页评论评分记录
	   * @param 
	   * @return List 分页的评论
	   */
	public final List get_pagelist()
	{
		return this.pagelist;
	}
	
	/**
	   * function 得到当前歌曲的标签列表
	   * @param 
	   * @return List label
	   */
	public final List get_taglist()
	{
		return this.taglist;
	}
	
	//后台评论情况输出
	public void print()
	{
		System.out.println("当前歌曲的评论数: "+this.allcount);
		System.out.println("当前歌曲: "+this.songname);
		System.out.println("当前歌曲的标签个数: "+this.taglist.size());
		for(int i=0; i<this.taglist.size(); i++)
			System.out.println("标签: "+((Tag)(this.taglist.get(i))).getLabel() );
		for(int i=0; i<this.pagelist.size(); i++)
			System.out.println("评论: "+((Assess)(this.pagelist.get(i))).getComment() );
	}
	
}
