package com.process.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.data.vo.*;

public class Filter {
	private static int NOFILTER = 0;
	private static int SONGFILTER = 1;
	private static int SINGERFILTER = 2;
	private static int LABELFILTER = 3;
	private static int LIMITFILTER = 4;

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
		
		if(filtertype == LIMITFILTER)
		{
			int size = idlist.size();
			if(size>50)
			{
				List<Integer> tempidlist = new ArrayList<Integer>();
				List<Integer> number = this.create_randomnumber(size-1, 50);
				for(int i=0; i<50; i++)
				{
					tempidlist.add(idlist.get(number.get(i)));
				}
				idlist.clear();
				idlist = tempidlist;
			}
		}
		
		return idlist;
	}
	
	
	
	
	public final List<Integer> create_randomnumber(int uplimit, int amount)
	{
		int counter = 0;
		List<Integer> number = new ArrayList<Integer>();
		Random god = new Random();
		//
		while(counter<amount)
		{
			int label_id_choosed = god.nextInt(uplimit)+1;
			if(!number.contains(label_id_choosed))
			{
				counter++;
				number.add(label_id_choosed);
			}
		}
		
		
		return number;
	}
	
}
