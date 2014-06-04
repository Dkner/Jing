package com.process.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.data.vo.*;

public class Filter {
	private static int NOFILTER = 0;
	private static int SONGFILTER = 1;
	private static int SINGERFILTER = 2;
	private static int LABELFILTER = 3;
	private static int LIMITFILTER = 4;
	private static int PATHFILTER = 5;

	private int filtertype = 0;
	private List filtercondition = new ArrayList();
	
	private SongDAO sd = new SongDAO();
	private SingerDAO sid = new SingerDAO();
	
	public Filter(){
		
	}
	
	public Filter(int type){
		this.filtertype = type;
	}
	
	public void setFilterType(int type){
		this.filtertype = type;
	}
	
	public void setFilterCondition(List filterlist){
		this.filtercondition = filterlist;
	}
	
	public List<Integer> doFilter(List<Integer> idlist){
		
		if(filtertype == NOFILTER)
		{
			System.out.println("无过滤");
			return idlist;
		}
		
		if(filtertype == SONGFILTER)
		{
			System.out.println("歌名过滤");
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
			System.out.println("歌手过滤");
			for(int i=0; i<idlist.size(); i++)
			{
				Singer singer = sd.findById(idlist.get(i)).getSinger();
				if(filtercondition.contains(singer.getSingerId()))
				{
					idlist.set(i, 0);
				}
			}
			
			
		}
		
		if(filtertype == LIMITFILTER)
		{
			System.out.println("歌曲上限过滤");
			int size = idlist.size();
			if(size>50)
			{
				List<Integer> tempidlist = new ArrayList<Integer>();
				List<Integer> number = this.create_randomnumber(size, 50);
				for(int i=0; i<50; i++)
				{
					tempidlist.add(idlist.get(number.get(i)));
				}
				idlist.clear();
				idlist = tempidlist;
//				for(int i=0; i<50; i++)
//				{
//					idlist.add(tempidlist.get(i));
//				}
			}
		}
		
		if(filtertype == PATHFILTER)
		{
			System.out.println("路径过滤");
			for(int i=0; i<idlist.size(); i++)
			{
				Song song = sd.findById(idlist.get(i));
				if(song == null)
					idlist.set(i, 0);
				String path = song.getPath();
				if(path == null || path.equals("") || path.equals("null"))
				{
					idlist.set(i, 0);
				}
			}
		}
		
		return this.remove_InvalidElement(idlist);
	}
	
	
	
	
	public final List<Integer> create_randomnumber(int uplimit, int amount)
	{
		int counter = 0;
		List<Integer> number = new ArrayList<Integer>();
		Random god = new Random();
		//
		while(counter<amount)
		{
			int label_id_choosed = god.nextInt(uplimit);
			if(!number.contains(label_id_choosed))
			{
				counter++;
				number.add(label_id_choosed);
			}
		}
		
		
		return number;
	}
	
	
	public final List<Integer> remove_InvalidElement(List<Integer> idlist)
	{
	    Iterator<Integer> ListIterator = idlist.iterator();
	    while(ListIterator.hasNext()){
	        int e = ListIterator.next();
	        if(e==0){
	        	ListIterator.remove();
	        }
	    }
	    return idlist;
	}
	
}
