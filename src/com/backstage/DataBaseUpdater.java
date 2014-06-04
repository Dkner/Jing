package com.backstage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.data.vo.Assess;
import com.data.vo.HibernateSessionFactory;
import com.data.vo.Song;
import com.data.vo.SongDAO;
import com.data.vo.User;
import com.data.vo.UserDAO;
import com.data.vo.Usertag;

public class DataBaseUpdater implements Runnable
{

	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			reviceScore();
			
			//五天更新一次
			try {
				Thread.sleep(1000*60*60*24*5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void reviceScore(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tst = session.beginTransaction();
		SongDAO ss = new SongDAO();
		List list = ss.findAll();
		for(int i=0; i<list.size(); i++)
		{
			System.out.println("歌曲:"+i);
			Song tempsong = (Song) list.get(i);
			
			Double score = 0.0;
			List assess = new ArrayList();
			if(tempsong.getAssesses().size() == 0)
				continue;
			assess.addAll(tempsong.getAssesses());
			int counter = 0;
			for(int j=0; j<assess.size(); j++)
			{
				String level = ((Assess) assess.get(j)).getLevel();
				if(level == null || level.equals(""))
					continue;
				
				counter++;
				score += Integer.parseInt(level);
			}
			score /= counter;
			tempsong.setScore(score);
			session.saveOrUpdate(tempsong);
		}
		
		tst.commit();
		session.close();
	}
	
	public void updateUserTag()
	{
		Session session = HibernateSessionFactory.getSession();
		Transaction tst = session.beginTransaction();
		
		UserDAO ud = new UserDAO();
		List<User> users = ud.findAll();
		for (Iterator<User> i = users.iterator(); i.hasNext();){  
			User user = i.next();
			Set usertagset = user.getUsertags();
			List<Usertag> usertags = new ArrayList<Usertag>();
			usertags.addAll(usertagset);
			for(int k=0; k<usertags.size(); k++)
			{
				Usertag u_tag = usertags.get(k);
				if(u_tag.getWeight()>0)
				{
					int weight = u_tag.getWeight();
					u_tag.setWeight(--weight);
				}
			}
		} 
		
		tst.commit();
		session.close();
	}
	
	
}
