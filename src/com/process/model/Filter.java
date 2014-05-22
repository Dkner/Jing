package com.process.model;

import java.util.ArrayList;
import java.util.List;

import com.data.vo.*;

public class Filter {
	private static int NOFILTER = 0;
	private static int SONGFILTER = 1;
	private static int SINGERFILTER = 2;
	private static int LABELFILTER = 3;

	private int filtertype = 0;
	private List filtercondition = new ArrayList();
	
	private SongDAO sd = new SongDAO();
	private SingerDAO sid = new SingerDAO();
	
	
	public Filter(){

	}
	
	public void setFilterType(int type){
		this.filtertype = type;
	}
	
	public void setFilterCondition(List filterlist){
		this.filtercondition = filterlist;
	}
	
	public List<Integer> filtering(List<Integer> idlist){
		
		if(filtertype == NOFILTER)
		{
			return idlist;
		}
		
		if(filtertype == SONGFILTER)
		{
			for(int i=0; i<filtercondition.size(); i++)
			{
				int filterId = (Integer) filtercondition.get(i);
				if(idlist.contains(filterId))
				{
					int index = idlist.indexOf(filterId);
					idlist.remove(index);
				}
			}
		}
		
		if(filtertype == SINGERFILTER)
		{
			for(int i=0; i<idlist.size(); i++)
			{
				Singer singer = sd.findById(idlist.get(i)).getSinger();
				if(filtercondition.contains(singer.getSingerId()))
				{
					idlist.set(i, 0);
				}
			}
			
			
		}
		
		return idlist;
	}
	
	
	
}
