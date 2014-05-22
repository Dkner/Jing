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
 * Singerlabel entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.data.vo.Singerlabel
 * @author MyEclipse Persistence Tools
 */

public class SingerlabelDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SingerlabelDAO.class);

	// property constants

	public void save(Singerlabel transientInstance) {
		log.debug("saving Singerlabel instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Singerlabel persistentInstance) {
		log.debug("deleting Singerlabel instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Singerlabel findById(java.lang.Integer id) {
		log.debug("getting Singerlabel instance with id: " + id);
		try {
			Singerlabel instance = (Singerlabel) getSession().get(
					"com.data.vo.Singerlabel", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Singerlabel instance) {
		log.debug("finding Singerlabel instance by example");
		try {
			List results = getSession()
					.createCriteria("com.data.vo.Singerlabel")
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
		log.debug("finding Singerlabel instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Singerlabel as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Singerlabel instances");
		try {
			String queryString = "from Singerlabel";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Singerlabel merge(Singerlabel detachedInstance) {
		log.debug("merging Singerlabel instance");
		try {
			Singerlabel result = (Singerlabel) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Singerlabel instance) {
		log.debug("attaching dirty Singerlabel instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Singerlabel instance) {
		log.debug("attaching clean Singerlabel instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}