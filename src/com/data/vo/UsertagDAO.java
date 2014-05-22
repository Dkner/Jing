package com.data.vo;

import com.connection.dao.BaseHibernateDAO;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Usertag entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.data.vo.Usertag
 * @author MyEclipse Persistence Tools
 */

public class UsertagDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(UsertagDAO.class);
	// property constants
	public static final String WEIGHT = "weight";
	public static final String SHIT = "shit";

	public void save(Usertag transientInstance) {
		log.debug("saving Usertag instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Usertag persistentInstance) {
		log.debug("deleting Usertag instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Usertag findById(java.lang.Integer id) {
		log.debug("getting Usertag instance with id: " + id);
		try {
			Usertag instance = (Usertag) getSession().get(
					"com.data.vo.Usertag", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Usertag instance) {
		log.debug("finding Usertag instance by example");
		try {
			List results = getSession().createCriteria("com.data.vo.Usertag")
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
		log.debug("finding Usertag instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Usertag as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByWeight(Object weight) {
		return findByProperty(WEIGHT, weight);
	}

	public List findByShit(Object shit) {
		return findByProperty(SHIT, shit);
	}

	public List findAll() {
		log.debug("finding all Usertag instances");
		try {
			String queryString = "from Usertag";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Usertag merge(Usertag detachedInstance) {
		log.debug("merging Usertag instance");
		try {
			Usertag result = (Usertag) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Usertag instance) {
		log.debug("attaching dirty Usertag instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Usertag instance) {
		log.debug("attaching clean Usertag instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}