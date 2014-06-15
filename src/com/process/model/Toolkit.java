package com.process.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.data.vo.Song;

public class Toolkit {
	
	/**
	   * function 生成随机数字，用来随机标签
	   * @param int 上限（不包含边界），随机数字个数
	   * @return List integers
	   */
	public static List<Integer> create_randomnumber(int uplimit, int amount)
	{
		int counter = 0;
		List<Integer> number = new ArrayList<Integer>();
		if(uplimit<=0)
			return number;
		
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
	
	
	/**
	   * function 后台输出搜索到的歌曲列表
	   * @param List Song
	   * @return
	   */
	public static void printsong_byname(List songlist){
		if(songlist.size() == 0)
		{
			System.out.println("未找到歌曲");
			return;
		}
		System.out.println("歌曲数目:"+songlist.size());
		for (int i = 0; i < songlist.size(); i++) {
			 System.out.println("id: "+((Song)songlist.get(i)).getId()+"    name："
					 +((Song)songlist.get(i)).getName());	
		}
	}
	
}
