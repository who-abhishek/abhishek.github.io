package in.co.hospital.mgt.sys.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.hospital.mgt.sys.bean.RoleBean;
import in.co.hospital.mgt.sys.exception.ApplicationException;
import in.co.hospital.mgt.sys.exception.DuplicateRecordException;
import in.co.hospital.mgt.sys.util.HibDataSource;

public class RoleModel {
	
	private static Logger log = Logger.getLogger(RoleModel.class);

	/**
	 * Add a Role
	 * 
	 * @param bean
	 * @throws DuplicateRecordException
	 * @throws ApplicationException
	 * 
	 */
	public long add(RoleBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Role add method start");
		long pk = 0;
		Transaction tx = null;
		Session session = null;

		RoleBean duplicataRole = findByName(bean.getName());
		// Check if updated Role already exist
		if (duplicataRole != null) {
			throw new DuplicateRecordException("Role already exists");
		}

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.save(bean);
			pk = bean.getId();

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Role Add " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		log.debug("Role add method End");
		return pk;
	}

	/**
	 * Delete a Role
	 * 
	 * @param bean
	 * @throws ApplicationException
	 */
	public void delete(RoleBean bean) throws ApplicationException {

		log.debug("Role delete method start");
		Session session = null;

		Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(bean);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Role Delete " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		log.debug("Role delete method end");
	}

	/**
	 * Update a Role
	 * 
	 * @param bean
	 * @throws DuplicateRecordException
	 * @throws ApplicationException
	 */
	public void update(RoleBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Role update method start");
		Session session = null;

		Transaction tx = null;

		RoleBean duplicataRole = findByName(bean.getName());
		// Check if updated Role already exist
		if (duplicataRole != null) {
			throw new DuplicateRecordException("Role already exists");
		}

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.update(bean);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Role Update " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		log.debug("Role update method end");
	}

	/**
	 * Find Role by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 */
	public RoleBean findByPK(long pk) throws ApplicationException {
		log.debug("Role findByPk method start");
		RoleBean bean = null;
		Session session = null;
		try {
			session = HibDataSource.getSession();
			bean = (RoleBean) session.get(RoleBean.class, pk);

		} catch (Exception e) {

			throw new ApplicationException("Exception in getting Role by pk " + e.getMessage());

		} finally {
			session.close();
		}
		log.debug("Role findByPk method end");
		return bean;
	}

	/**
	 * Find Role by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 */

	public RoleBean findByName(String name) throws ApplicationException {

		log.debug("Role findByName method start");
		Session session = null;
		RoleBean bean = null;

		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(RoleBean.class);

			criteria.add(Restrictions.eq("name", name));

			List list = criteria.list();

			if (list.size() == 1) {
				bean = (RoleBean) list.get(0);
			}

		} catch (HibernateException e) {

			throw new ApplicationException("Exception in getting Role by Name " + e.getMessage());

		} finally {
			session.close();
		}

		log.debug("Role findByName method end");
		return bean;
	}

	/**
	 * Gets List of Role
	 * 
	 * @return list : List of Roles
	 * @throws ApplicationException
	 */

	public List list() throws ApplicationException {

		return list(0, 0);
	}

	/**
	 * get List of Role with pagination
	 * 
	 * @return list : List of Roles
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {

		log.debug("Role list method start");
		Session session = null;

		List list = null;

		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(RoleBean.class);

			if (pageSize > 0) {

				pageNo = (pageNo - 1) * pageSize;

				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();

		} catch (HibernateException e) {

			throw new ApplicationException("Exception in getting list ");

		} finally {
			session.close();
		}
		log.debug("Role list method end");
		return list;
	}

	/**
	 * Searches Role
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(RoleBean bean) throws ApplicationException {

		return search(bean, 0, 0);
	}

	/**
	 * Searches Roles with pagination
	 * 
	 * @return list : List of Roles
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws ApplicationException
	 */

	public List search(RoleBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Role search method start");
		
		System.out.println("Role search method start");
		
		Session session = null;
		List list = null;

		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(RoleBean.class);

			if (bean.getId() > 0) {

				criteria.add(Restrictions.eq("id", bean.getId()));

			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				criteria.add(Restrictions.like("name", bean.getName() + "%"));
			}
		
			if (pageSize > 0) {

				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();

			
		} catch (HibernateException e) {

			throw new ApplicationException("exception in search");
		} finally {
			session.close();
		}

		log.debug("Role search method end");
		return list;
	}


}
