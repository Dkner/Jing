package com.process.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.data.vo.HibernateSessionFactory;
import com.data.vo.Label;
import com.data.vo.LabelDAO;
import com.data.vo.Song;
import com.data.vo.SongDAO;
import com.data.vo.Tag;
import com.data.vo.Tagrecord;
import com.data.vo.User;
import com.data.vo.UserDAO;
import com.data.vo.Usertag;
import com.data.vo.UsertagDAO;

public class UserProfileProcessor {
	
	public void TagRecordProcessing(String user_id, String input){
		//用户记录
		Date date = new Date();
		String time = "";
		time += (date.getYear()+1900)+"-";
		time += (date.getMonth()+1)+"-";
		time += date.getDate();

		UserDAO ud = new UserDAO();
		Session session = HibernateSessionFactory.getSession();
		Transaction tst = session.beginTransaction();
		Tagrecord record = new Tagrecord(ud.findById(user_id), input, time);
		session.save(record);
		tst.commit();
		session.close();
	}
	
	public void UserTagProcesing_FOR_Search(String user_id, String labelname)
	{
		LabelDAO ld = new LabelDAO();
		UserDAO ud = new UserDAO();
		UsertagDAO utd = new UsertagDAO();
		List<Label> temp = new ArrayList();
		temp = ld.findByLabel(labelname);
		User tempuser = ud.findById(user_id);
		if(tempuser == null || temp.size() == 0)
		{
			System.out.println("UserTag process failed.");
			return;
		}
		else
		{
			Session session = HibernateSessionFactory.getSession();
			Transaction tst = session.beginTransaction();
			
		    Map<String,Object> conditions = new HashMap<String,Object>(); 
		    conditions.put("user_id", user_id);
		    conditions.put("label_id", temp.get(0).getLabelId());
			List<Usertag> usertag = utd.findByMultiProperty(conditions);
			if(usertag != null && usertag.size()>0)
			{
				Usertag exisitedtag = usertag.get(0);
				int weight = exisitedtag.getWeight();
				exisitedtag.setWeight(++weight);
				
				System.out.println("已存在usertag");
				session.saveOrUpdate(exisitedtag);
			}
			else
			{
				System.out.println("新建usertag");
				Usertag newusertag = new Usertag((Label)(temp.get(0)), ud.findById(user_id), 1, 0);
				session.saveOrUpdate(newusertag);
			}
			tst.commit();
			session.close();
		}
		
	}
	
	public void UserTagProcesing_FOR_CollectSong(String user_id, int song_id)
	{
		LabelDAO ld = new LabelDAO();
		UserDAO ud = new UserDAO();
		SongDAO sd = new SongDAO();
		UsertagDAO utd = new UsertagDAO();
		Song song = sd.findById(song_id);
		User tempuser = ud.findById(user_id);
		if(song == null || tempuser == null)
		{
			System.out.println("UserTag process failed.");
			return;
		}
		else
		{
			Session session = HibernateSessionFactory.getSession();
			Transaction tst = session.beginTransaction();
			
			Set songtagset = song.getTags();
			List<Tag> songtaglist = new ArrayList<Tag>();
			songtaglist.addAll(songtagset);
			for(int i=0; i<songtaglist.size(); i++)
			{
				Label label = songtaglist.get(i).getLabel();
				
				Map<String,Object> conditions = new HashMap<String,Object>(); 
			    conditions.put("user_id", user_id);
			    conditions.put("label_id", label.getLabelId());
				List<Usertag> usertag = utd.findByMultiProperty(conditions);
				if(usertag != null && usertag.size()>0)
				{
					Usertag exisitedtag = usertag.get(0);
					int weight = exisitedtag.getWeight()+5;
					exisitedtag.setWeight(weight);
					
					System.out.println("已存在usertag");
					session.saveOrUpdate(exisitedtag);
				}
				else
				{
					System.out.println("新建usertag");
					Usertag newusertag = new Usertag(label, ud.findById(user_id), 5, 0);
					session.saveOrUpdate(newusertag);
				}
			}
			
			tst.commit();
			session.close();
		}
		
	}
	
	public void UserTagProcesing_FOR_HateSong(String user_id, int song_id)
	{
		LabelDAO ld = new LabelDAO();
		UserDAO ud = new UserDAO();
		SongDAO sd = new SongDAO();
		UsertagDAO utd = new UsertagDAO();
		Song song = sd.findById(song_id);
		User tempuser = ud.findById(user_id);
		if(song == null || tempuser == null)
		{
			System.out.println("UserTag process failed.");
			return;
		}
		else
		{
			Session session = HibernateSessionFactory.getSession();
			Transaction tst = session.beginTransaction();
			
			Set songtagset = song.getTags();
			List<Tag> songtaglist = new ArrayList<Tag>();
			songtaglist.addAll(songtagset);
			for(int i=0; i<songtaglist.size(); i++)
			{
				Label label = songtaglist.get(i).getLabel();
				
				Map<String,Object> conditions = new HashMap<String,Object>(); 
			    conditions.put("user_id", user_id);
			    conditions.put("label_id", label.getLabelId());
				List<Usertag> usertag = utd.findByMultiProperty(conditions);
				if(usertag != null && usertag.size()>0)
				{
					Usertag exisitedtag = usertag.get(0);
					int weight = exisitedtag.getWeight()-5;
					exisitedtag.setWeight(weight);
					
					System.out.println("已存在usertag");
					session.saveOrUpdate(exisitedtag);
				}
				else
				{
					System.out.println("新建usertag");
					Usertag newusertag = new Usertag(label, ud.findById(user_id), -5, 0);
					session.saveOrUpdate(newusertag);
				}
			}
			
			tst.commit();
			session.close();
		}
		
	}
	
}
