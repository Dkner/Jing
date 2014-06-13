package com.process.model;

import java.util.ArrayList;
import java.util.List;

import com.data.vo.*;


public class Page {
	
	private int pagesize;
	private int pagenow;
	private int pagecount;
	private int allcount;
	
	
	public Page()
	{
		
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
	public final void set_pagenow(int n)
	{
		//if(n>0 && n<=this.pagecount)
			pagenow = n;
	}
	
	public final void set_pagesize(int n)
	{
		this.pagesize = n;
	}
	
	public final void set_allcount(int n)
	{
		this.allcount = n;
	}
	
	public final void set_pagecount()
	{
		if(pagesize == 0)
			return;
		if(allcount%pagesize != 0)
    		pagecount = allcount/pagesize + 1;
    	else
    		pagecount = allcount/pagesize;
	}
	
	public final int get_pagenow()
	{
		return this.pagenow;
	}
	
	public final int get_pagesize()
	{
		return this.pagesize;
	}
	
	public final int get_pagecount()
	{
		return this.pagecount;
	}
	
	public void print_page(){
		System.out.println("pagenow:"+this.pagenow+"\npagecount:"+this.pagecount+"\npagesize:"+this.pagesize);
	}
	
}
