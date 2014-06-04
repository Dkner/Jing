package com.data.vo;

import com.connection.dao.BaseHibernateDAO;
import com.process.model.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Song
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.data.vo.Song
 * @author MyEclipse Persistence Tools
 */

public class SongDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SongDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String PATH = "path";
	public static final String SCORE = "score";

	public void save(Song transientInstance) {
		log.debug("saving Song instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Song persistentInstance) {
		log.debug("deleting Song instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Song findById(java.lang.Integer id) {
		log.debug("getting Song instance with id: " + id);
		try {
			Song instance = (Song) getSession().get("com.data.vo.Song", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Song instance) {
		log.debug("finding Song instance by example");
		try {
			List results = getSession().createCriteria("com.data.vo.Song")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Song instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Song as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	public List findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List findAll() {
		log.debug("finding all Song instances");
		try {
			String queryString = "from Song";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Song merge(Song detachedInstance) {
		log.debug("merging Song instance");
		try {
			Song result = (Song) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Song instance) {
		log.debug("attaching dirty Song instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Song instance) {
		log.debug("attaching clean Song instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	
	
	
	
	
	
	
	//按页查找

		@SuppressWarnings("unchecked")
		public List findByPage(Page page)
		{
			try {
				List<Song> gifts = new ArrayList<Song>();
				String queryString = "from Song order by score desc";

				Query queryObject = getSession().createQuery(queryString);
				queryObject.setFirstResult((page.get_pagenow()-1)*page.get_pagesize());
				queryObject.setMaxResults(page.get_pagesize());

				gifts = queryObject.list();						
				return gifts;
			}
			catch(RuntimeException re) {
				log.error("find by page failed", re);
				throw re;
			}


		}



		//得到总的礼品数
		public int getTotalRows()
		{

			Number c= (Number) getSession().createQuery("select count(*) from Song")
					.uniqueResult();


			return c.intValue();

		}
	
	
	
	
}