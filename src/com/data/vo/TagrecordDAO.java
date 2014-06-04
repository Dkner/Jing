package com.data.vo;

import com.connection.dao.BaseHibernateDAO;
import com.process.model.Page;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tagrecord entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.data.vo.Tagrecord
 * @author MyEclipse Persistence Tools
 */

public class TagrecordDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TagrecordDAO.class);
	// property constants
	public static final String RECORD = "record";
	public static final String TIME = "time";

	public void save(Tagrecord transientInstance) {
		log.debug("saving Tagrecord instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tagrecord persistentInstance) {
		log.debug("deleting Tagrecord instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tagrecord findById(java.lang.Integer id) {
		log.debug("getting Tagrecord instance with id: " + id);
		try {
			Tagrecord instance = (Tagrecord) getSession().get(
					"com.data.vo.Tagrecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tagrecord instance) {
		log.debug("finding Tagrecord instance by example");
		try {
			List results = getSession().createCriteria("com.data.vo.Tagrecord")
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
		log.debug("finding Tagrecord instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tagrecord as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRecord(Object record) {
		return findByProperty(RECORD, record);
	}

	public List findByTime(Object time) {
		return findByProperty(TIME, time);
	}

	public List findAll() {
		log.debug("finding all Tagrecord instances");
		try {
			String queryString = "from Tagrecord";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tagrecord merge(Tagrecord detachedInstance) {
		log.debug("merging Tagrecord instance");
		try {
			Tagrecord result = (Tagrecord) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tagrecord instance) {
		log.debug("attaching dirty Tagrecord instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tagrecord instance) {
		log.debug("attaching clean Tagrecord instance");
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
			public List findPropertyByPage(String user_id, Page page)
			{
				try {
					List<Tagrecord> gifts = new ArrayList<Tagrecord>();
					String queryString = "from Tagrecord where user_id = "+user_id+" order by time desc";

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
			public int getUserTotalRows(String user_id)
			{

				Number c= (Number) getSession().createQuery("select count(*) from Tagrecord where user_id = "+user_id)
						.uniqueResult();


				return c.intValue();

			}
			
	
}