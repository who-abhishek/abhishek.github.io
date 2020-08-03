package in.co.hospital.mgt.sys.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.hospital.mgt.sys.bean.AppointmentBean;
import in.co.hospital.mgt.sys.exception.ApplicationException;
import in.co.hospital.mgt.sys.exception.DuplicateRecordException;
import in.co.hospital.mgt.sys.util.HibDataSource;

public class AppointmentModel {
	
	private static Logger log = Logger.getLogger(AppointmentModel.class);

	/**
	 * Add a Role
	 * 
	 * @param bean
	 * @throws DuplicateRecordException
	 * @throws ApplicationException
	 * 
	 */
	public long add(AppointmentBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Role add method start");
		long pk = 0;
		Transaction tx = null;
		Session session = null;

		AppointmentBean duplicataRole = findByName(bean.getFirstName());
		// Check if updated Role already exist
		if (duplicataRole != null) {
			throw new DuplicateRecordException("Appointment already exists");
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
	public void delete(AppointmentBean bean) throws ApplicationException {

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
	public void update(AppointmentBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Role update method start");
		Session session = null;

		Transaction tx = null;

		AppointmentBean duplicataRole = findByName(bean.getFirstName());
		// Check if updated Role already exist
		if (duplicataRole != null && bean.getId()==duplicataRole.getId()) {
			throw new DuplicateRecordException("Appointment already exists");
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
	public AppointmentBean findByPK(long pk) throws ApplicationException {
		log.debug("Role findByPk method start");
		AppointmentBean bean = null;
		Session session = null;
		try {
			session = HibDataSource.getSession();
			bean = (AppointmentBean) session.get(AppointmentBean.class, pk);

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

	public AppointmentBean findByName(String name) throws ApplicationException {

		log.debug("Role findByName method start");
		Session session = null;
		AppointmentBean bean = null;

		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(AppointmentBean.class);

			criteria.add(Restrictions.eq("firstName", name));

			List list = criteria.list();

			if (list.size() == 1) {
				bean = (AppointmentBean) list.get(0);
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
			Criteria criteria = session.createCriteria(AppointmentBean.class);

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
	public List search(AppointmentBean bean) throws ApplicationException {

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

	public List search(AppointmentBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Role search method start");
		
		System.out.println("Role search method start");
		
		Session session = null;
		List list = null;

		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(AppointmentBean.class);

			if (bean.getId() > 0) {

				criteria.add(Restrictions.eq("id", bean.getId()));

			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				criteria.add(Restrictions.like("firstName", bean.getFirstName() + "%"));
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				criteria.add(Restrictions.like("lastName", bean.getLastName() + "%"));
			}
			if (bean.getDoctorName() != null && bean.getDoctorName().length() > 0) {
				criteria.add(Restrictions.like("doctorName", bean.getDoctorName() + "%"));
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
