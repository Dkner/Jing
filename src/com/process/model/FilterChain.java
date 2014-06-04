package com.process.model;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {

	List<Filter> filters = new ArrayList<Filter>();
	
	public void AddFilter(Filter filter){
		filters.add(filter);
	}
	
	public List<Integer> doFilter(List<Integer> idlist){
		System.out.println("FilterChain开始过滤。。。");
		System.out.println("目前歌曲数目"+idlist.size());
		
		for(int i=0; i<filters.size(); i++)
		{
			idlist = filters.get(i).doFilter(idlist);
			System.out.println("剩余歌曲数目"+idlist.size());
		}
		
		return idlist;
	}
}
