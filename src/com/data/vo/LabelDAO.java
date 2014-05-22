package com.data.vo;

import com.connection.dao.BaseHibernateDAO;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Label
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.data.vo.Label
 * @author MyEclipse Persistence Tools
 */

public class LabelDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(LabelDAO.class);
	// property constants
	public static final String TYPE = "type";
	public static final String LABEL = "label";

	public void save(Label transientInstance) {
		log.debug("saving Label instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Label persistentInstance) {
		log.debug("deleting Label instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	//��������
	public List findByPropertyLikedLimited(String propertyName, Object value, int start, int limit) {
		log.debug("search label by property limit");

		try {
			String queryString = "from Label as model where model."
					+ propertyName + " like ?";
			Query query = getSession().createQuery(queryString);
			query.setParameter(0, "%"+value+"%");
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		} catch (RuntimeException re) {
			log.error("find label by property limit failed", re);
			throw re;
		}

	}
	public List findByLabelLikedLimited(Object label, int start, int limit){
		return findByPropertyLikedLimited(LABEL, label, start, limit);
	}
	
	
	public Label findById(java.lang.Integer id) {
		log.debug("getting Label instance with id: " + id);
		try {
			Label instance = (Label) getSession().get("com.data.vo.Label", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Label instance) {
		log.debug("finding Label instance by example");
		try {
			List results = getSession().createCriteria("com.data.vo.Label")
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
		log.debug("finding Label instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Label as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByLabel(Object label) {
		return findByProperty(LABEL, label);
	}

	public List findAll() {
		log.debug("finding all Label instances");
		try {
			String queryString = "from Label";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Label merge(Label detachedInstance) {
		log.debug("merging Label instance");
		try {
			Label result = (Label) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Label instance) {
		log.debug("attaching dirty Label instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Label instance) {
		log.debug("attaching clean Label instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}