package com.process.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.data.vo.Assess;
import com.data.vo.AssessDAO;
import com.data.vo.Song;
import com.data.vo.SongDAO;
import com.data.vo.User;
import com.data.vo.UserDAO;
import com.data.vo.Usertag;
import com.data.vo.UsertagDAO;

public class User2UserRecommendation{
	
	//存放相似度，1为被比较对象初始值
	private List<Double> Similarity = new ArrayList<Double>();
	private List<Integer> GroupIdList = new ArrayList<Integer>();
	
	private String user_id;
	private int TOP_N = 5;
	
	public User2UserRecommendation(){
		
	}
	
	public User2UserRecommendation(String user_id){
		this.set_userId(user_id);
	}
	
	public void set_userId(String user_id){
		this.user_id = user_id;
	}
	
	/*
	 * function: 计算一个user与其他用户的相似度
	 * 效率太差，废弃
	 */
//	public void calculateSim(){
//		
//		System.out.println("开始计算用户相似度。。。");
//		
//		AssessDAO ad = new AssessDAO();
//		UserDAO ud = new UserDAO();
//		List<User> users = ud.findAll();
//		int USERNO = users.size();
//		User user = ud.findById(user_id);
//		List<Assess> myassess = new ArrayList<Assess>(user.getAssesses()); 
//		
//		//遍历所有用户，算出与user的相似度
//		for(int i=0; i<USERNO; i++){
//			System.out.println("扫描第"+(i+1)+"个用户。。。");
//			User other = users.get(i);
//			Double sim = 0.0;
//			if(other.getUserId().equals(user_id))
//			{
//				Similarity.add(sim);
//				System.out.println("与第"+(i+1)+"个用户的相似度:"+sim);
//				continue;
//			}
//			List<Assess> otherassess = new ArrayList<Assess>(other.getAssesses());
//			if(otherassess.size()<10)
//			{
//				Similarity.add(sim);
//				System.out.println("与第"+(i+1)+"个用户的相似度:"+sim);
//				continue;
//			}
//			
//			//
//			Double arg1 = 0.0;
//			Double arg2 = 0.0;
//			Double arg3 = 0.0;
//			for(int j=0; j<myassess.size(); j++)
//			{
//				Assess dimension = myassess.get(j);
//				if(dimension.getLevel() == null)
//					continue;
//				//System.out.println("分数:"+dimension.getLevel());
//				
//				List<Assess> temp = ad.findByProperty2(dimension.getSong().getId(), other.getUserId());
//				//List<Assess> temp = new ArrayList<Assess>();
//				if(temp == null || temp.size()==0)
//				{
//					continue;
//				}
//				else
//				{
//					if(temp.get(0).getLevel() == null)
//						continue;
//					arg1 += (Integer.parseInt(temp.get(0).getLevel())-5)*(Integer.parseInt(dimension.getLevel())-5);
//					arg2 += Math.pow(Integer.parseInt(dimension.getLevel())-5,2);
//					arg3 += Math.pow(Integer.parseInt(temp.get(0).getLevel())-5,2);	
//				}
//				
//			}
//			
//			if(arg1*arg2*arg3 != 0)
//			{
//				sim = arg1/(Math.sqrt(arg2)*Math.sqrt(arg3));
//				Similarity.add(sim);
//			}
//			else
//				Similarity.add(sim);
//			
//			System.out.println("与第"+(i+1)+"个用户的相似度:"+sim);
//		}
//					
//		//排序取前n个相似度的用户作为参考群体
//		List<Double> sortedSimilarity = new ArrayList<Double>(Similarity);
//		Collections.sort(sortedSimilarity,Collections.reverseOrder()); 
//		Double boundary = sortedSimilarity.get(TOP_N);
//		for(int i=0; i<Similarity.size(); i++)
//		{
//			if(Similarity.get(i) > boundary)
//				GroupIdList.add(i);
//		}
//		
//	}
	
	
	
	
	public void locateCluster(){
		UserDAO ud = new UserDAO();
		UsertagDAO utd = new UsertagDAO();
		List<User> users = ud.findAll();
		int USERNO = users.size();
		List<Integer> sampleid = this.create_randomnumber(USERNO, 150);
		User user = ud.findById(user_id);
		List<Usertag> mytag = utd.findTopFeature(user_id, 4); 
		
		//遍历所有用户，算出与user的相似度
		for(int i=0; i<150; i++){
			//System.out.println("扫描第"+(i+1)+"个用户。。。");
			User otherone = users.get(sampleid.get(i));
			List<Usertag> othertag = utd.findTopFeature(otherone.getUserId(), 4);
			List<Integer> mytagid = new ArrayList<Integer>();
			List<Integer> othertagid = new ArrayList<Integer>();
			
			int othertagsize = othertag.size();
			
			Double sim = 0.0;				
			Double arg1 = 0.0;
			Double arg2 = 0.0;
			Double arg3 = 0.0;
			if(othertagsize == 0)
			{
				Similarity.add(sim);
				continue;
			}
			
			for(int j=0; j<mytag.size(); j++)
			{
				mytagid.add(mytag.get(j).getLabel().getLabelId());
			}
			for(int j=0; j<othertagsize; j++)
			{
				othertagid.add(othertag.get(j).getLabel().getLabelId());
			}
			
			for(int j=0;j<mytagid.size();j++)
			{				
				int index = othertagid.indexOf(mytagid.get(j));
				if(index == -1)
					continue;
				else
				{
					//arg1 += Math.pow((othertag.get(index).getWeight()-mytag.get(j).getWeight()), 2);
					//arg2 += Math.pow(othertag.get(index).getWeight(), 2) + Math.pow(mytag.get(j).getWeight(), 2);
					arg1 += othertag.get(index).getWeight()*mytag.get(j).getWeight();
					arg2 += Math.pow(mytag.get(j).getWeight(), 2);
					arg3 += Math.pow(othertag.get(index).getWeight(), 2);
				}
			}

			if(arg1*arg2 != 0)
			{
				//sim = 1.0 - arg1/arg2;
				sim = arg1/(Math.sqrt(arg2)*Math.sqrt(arg3));
				Similarity.add(sim);
			}
			else
				Similarity.add(sim);

			//System.out.println("与第"+sampleid.get(i)+"个用户的相似度:"+sim);
		
		}
		
		
		//排序取前n个相似度的用户作为参考群体
		List<Double> sortedSimilarity = new ArrayList<Double>(Similarity);
		Collections.sort(sortedSimilarity,Collections.reverseOrder()); 
		Double boundary = sortedSimilarity.get(TOP_N);
		for(int i=0; i<Similarity.size(); i++)
		{
			if(Similarity.get(i) >= boundary)
				GroupIdList.add(i);
		}
		
		//System.out.println("Group的个数:"+GroupIdList.size());
	}
	
	public List<Song> recommendSongBySimilarity(){
		List<Song> lovesonglist = new ArrayList<Song>();
		int number = 0;
		UserDAO ud = new UserDAO();
		if(GroupIdList.size()>0)
			number = create_randomnumber(this.GroupIdList.size(), 1).get(0);
		else
			return lovesonglist;
		int id = GroupIdList.get(number);
		User similaruser = (User) ud.findAll().get(id);
		
		SongDAO sd = new SongDAO();
		List<Integer> idlist = new ArrayList<Integer>();
		List templist = new ArrayList();
		templist.addAll(similaruser.getAssesses());
		
		if(templist.size() == 0)
		{
			return recommendSongBySimilarity();
		}
	
		for (Iterator<Assess> i = templist.iterator(); i.hasNext();){  
			Assess assess = i.next();
			if(assess.getLevel()==null)
				continue;
			if(Integer.parseInt(assess.getLevel())>=7)
			{
				lovesonglist.add(assess.getSong());
			}
		} 
		
		for(int k=0; k<lovesonglist.size(); k++)
			idlist.add(lovesonglist.get(k).getId());
	
		FilterChain chain = new FilterChain();
		chain.AddFilter(new Filter(4));
		chain.AddFilter(new Filter(5));
		idlist = chain.doFilter(idlist);
		lovesonglist.clear();
		
		for (int i=0; i<idlist.size(); i++) 
	      {
	    	  Song temp = sd.findById(idlist.get(i));
	    	  if(temp != null)
	    		  lovesonglist.add(temp);
	      }   
		return lovesonglist;
	}
	
	/**
	   * function 生成随机数字，用来随机标签
	   * @param int 上限（不包含边界），随机数字个数
	   * @return List integers
	   */
	public final List<Integer> create_randomnumber(int uplimit, int amount)
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
	
}
