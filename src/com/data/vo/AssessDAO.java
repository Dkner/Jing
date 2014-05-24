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
 * Assess entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.data.vo.Assess
 * @author MyEclipse Persistence Tools
 */

public class AssessDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(AssessDAO.class);
	// property constants
	public static final String LOVEORHATE = "loveorhate";
	public static final String COMMENT = "comment";
	public static final String LEVEL = "level";
	public static final String TIME = "time";

	public void save(Assess transientInstance) {
		log.debug("saving Assess instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Assess persistentInstance) {
		log.debug("deleting Assess instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Assess findById(java.lang.Integer id) {
		log.debug("getting Assess instance with id: " + id);
		try {
			Assess instance = (Assess) getSession().get("com.data.vo.Assess",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Assess instance) {
		log.debug("finding Assess instance by example");
		try {
			List results = getSession().createCriteria("com.data.vo.Assess")
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
		log.debug("finding Assess instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Assess as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLoveorhate(Object loveorhate) {
		return findByProperty(LOVEORHATE, loveorhate);
	}

	public List findByComment(Object comment) {
		return findByProperty(COMMENT, comment);
	}

	public List findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	public List findByTime(Object time) {
		return findByProperty(TIME, time);
	}

	public List findAll() {
		log.debug("finding all Assess instances");
		try {
			String queryString = "from Assess";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Assess merge(Assess detachedInstance) {
		log.debug("merging Assess instance");
		try {
			Assess result = (Assess) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Assess instance) {
		log.debug("attaching dirty Assess instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Assess instance) {
		log.debug("attaching clean Assess instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}