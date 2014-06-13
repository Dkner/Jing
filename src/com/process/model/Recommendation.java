package com.process.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Recommendation {

	protected static final int USERNO = 6040;
	protected static final int MOVIENO  = 3952;
	protected static final int GROUP  = 100;
	protected static final int TOP_K = 20;
	
	
	protected static final int STARTUSER = 1;
	protected static final int ENDUSER = 20;
	
	protected static final int STARTMOVIE = 2000;
	protected static final int ENDMOVIE = 2010;

	//存放相似度，1为被比较对象初始值
	protected List<Double> Similarity = new ArrayList<Double>();
	
	//存放所有用户的打分平均值
	protected List<Double> Average = new ArrayList<Double>();
	
	//相似用户的id列表
	protected List<Integer> GroupIdList = new ArrayList<Integer>();
	
	public void importRating() {  
		
   
    }  
	
	public void printMap(Map<Integer, Integer> map) { 
		if(map.size() == 0)
			System.out.println("空");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) { 

            System.out.println(); 
        } 
    } 
	
	/*
	 * function: 计算user之间或者是item之间的改良相似度，作为权值
	 */
	public void calculateSim(){
		
	}
	
	
	/*
	 * function: 根据公式加权平均值，估算用户对未知item的评分，并进行高分推荐
	 */
	public void calculateRating(int UserId){
		
	}
	
	
	public void output(String str, String path){
		
		
		 
	}
	
}
