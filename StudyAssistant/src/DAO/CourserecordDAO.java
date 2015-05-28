package DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Entity.Courserecord;

/**
 * A data access object (DAO) providing persistence and search support for
 * Courserecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see DAO.Courserecord
 * @author MyEclipse Persistence Tools
 */
public class CourserecordDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CourserecordDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String TIME = "time";
	public static final String WEEK = "week";
	public static final String LOCATION = "location";
	public static final String TEACHER = "teacher";
	public static final String REMIND = "remind";
	public static final String REMARK = "remark";

	public void save(Courserecord transientInstance) {
		log.debug("saving Courserecord instance");
		Transaction transaction = getSession().beginTransaction();

		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}

		transaction.commit();
		getSession().flush();
	}

	public void delete(Courserecord persistentInstance) {
		log.debug("deleting Courserecord instance");
		Transaction transaction = getSession().beginTransaction();

		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}

		transaction.commit();
		getSession().flush();
	}

	public Courserecord findById(java.lang.Integer id) {
		log.debug("getting Courserecord instance with id: " + id);
		try {
			Courserecord instance = (Courserecord) getSession().get(
					"Entity.Courserecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Courserecord instance) {
		log.debug("finding Courserecord instance by example");
		try {
			List results = getSession().createCriteria("DAO.Courserecord")
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
		log.debug("finding Courserecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Courserecord as model where model."
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

	public List findByTime(Object time) {
		return findByProperty(TIME, time);
	}

	public List findByWeek(Object week) {
		return findByProperty(WEEK, week);
	}

	public List findByLocation(Object location) {
		return findByProperty(LOCATION, location);
	}

	public List findByTeacher(Object teacher) {
		return findByProperty(TEACHER, teacher);
	}

	public List findByRemind(Object remind) {
		return findByProperty(REMIND, remind);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Courserecord instances");
		try {
			String queryString = "from Courserecord";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Courserecord merge(Courserecord detachedInstance) {
		log.debug("merging Courserecord instance");
		try {
			Courserecord result = (Courserecord) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Courserecord instance) {
		log.debug("attaching dirty Courserecord instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Courserecord instance) {
		log.debug("attaching clean Courserecord instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}