package com.data.vo;

import com.connection.dao.BaseHibernateDAO;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Favor
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.data.vo.Favor
 * @author MyEclipse Persistence Tools
 */

public class FavorDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(FavorDAO.class);

	// property constants

	public void save(Favor transientInstance) {
		log.debug("saving Favor instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Favor persistentInstance) {
		log.debug("deleting Favor instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Favor findById(java.lang.Integer id) {
		log.debug("getting Favor instance with id: " + id);
		try {
			Favor instance = (Favor) getSession().get("com.data.vo.Favor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Favor instance) {
		log.debug("finding Favor instance by example");
		try {
			List results = getSession().createCriteria("com.data.vo.Favor")
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
		log.debug("finding Favor instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Favor as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByMultiProperty(Map<String,Object> conditions) {
		log.debug("finding Favor instance with multiproperty");
		try {
			String queryString = "from Favor where ";
			
			Set<String> key = conditions.keySet();
			int size = 0;
	        for (Map.Entry<String,Object> entry : conditions.entrySet()) {	           
	            size++;
	            if(size == key.size())
	            	queryString = queryString + entry.getKey()  + " = " + entry.getValue();
	            else
	            	queryString = queryString + entry.getKey()  + " = " + entry.getValue()+" and ";
	            
	        }
			Query queryObject = getSession().createQuery(queryString);
//			for (Map.Entry<String,Object> entry : conditions.entrySet()) { 
//	            queryObject.setParameter(size++, entry.getValue());
//	        } 
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Favor instances");
		try {
			String queryString = "from Favor";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Favor merge(Favor detachedInstance) {
		log.debug("merging Favor instance");
		try {
			Favor result = (Favor) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Favor instance) {
		log.debug("attaching dirty Favor instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Favor instance) {
		log.debug("attaching clean Favor instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}