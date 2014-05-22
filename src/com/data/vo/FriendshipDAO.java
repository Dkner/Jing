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
 * Friendship entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.data.vo.Friendship
 * @author MyEclipse Persistence Tools
 */

public class FriendshipDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FriendshipDAO.class);
	// property constants
	public static final String RELATION = "relation";

	public void save(Friendship transientInstance) {
		log.debug("saving Friendship instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Friendship persistentInstance) {
		log.debug("deleting Friendship instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Friendship findById(com.data.vo.FriendshipId id) {
		log.debug("getting Friendship instance with id: " + id);
		try {
			Friendship instance = (Friendship) getSession().get(
					"com.data.vo.Friendship", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Friendship instance) {
		log.debug("finding Friendship instance by example");
		try {
			List results = getSession()
					.createCriteria("com.data.vo.Friendship")
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
		log.debug("finding Friendship instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Friendship as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRelation(Object relation) {
		return findByProperty(RELATION, relation);
	}

	public List findAll() {
		log.debug("finding all Friendship instances");
		try {
			String queryString = "from Friendship";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Friendship merge(Friendship detachedInstance) {
		log.debug("merging Friendship instance");
		try {
			Friendship result = (Friendship) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Friendship instance) {
		log.debug("attaching dirty Friendship instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Friendship instance) {
		log.debug("attaching clean Friendship instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}