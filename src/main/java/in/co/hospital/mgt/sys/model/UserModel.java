package in.co.hospital.mgt.sys.model;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.hospital.mgt.sys.bean.UserBean;
import in.co.hospital.mgt.sys.exception.ApplicationException;
import in.co.hospital.mgt.sys.exception.DatabaseException;
import in.co.hospital.mgt.sys.exception.DuplicateRecordException;
import in.co.hospital.mgt.sys.exception.RecordNotFoundException;
import in.co.hospital.mgt.sys.util.EmailBuilder;
import in.co.hospital.mgt.sys.util.HibDataSource;

public class UserModel {
	
	private static Logger log = Logger.getLogger(UserModel.class);

	/**
	 * Add a user
	 * 
	 * @param bean
	 * @throws DuplicateRecordException
	 * @throws ApplicationException
	 * 
	 */

	public long add(UserBean bean) throws ApplicationException, DuplicateRecordException, DatabaseException {

		log.debug("User Model add method start");

		System.out.println("HIB add user model");

		long pk = 0;

		
		UserBean beanExist = findByLogin(bean.getLogin());

		
		
		if (beanExist != null ) {
			System.out.println("beanExist.getLogin :"+beanExist.getLogin());
			throw new DuplicateRecordException("LoginId is already exist");
		}

		

		Transaction tx = null;
		Session session = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(bean);
			pk = bean.getId();

			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in User Add " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}

		}

		log.debug("User Model add method end");

		return pk;
	}

	/**
	 * Delete a User
	 * 
	 * @param bean
	 * @throws ApplicationException
	 */

	public void delete(UserBean bean) throws ApplicationException {
		log.debug("User Model delete  method start");

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
			throw new ApplicationException("Exception in User Delete " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		log.debug("User Model delete  method end");
	}

	/**
	 * Update a User
	 * 
	 * @param bean
	 * @throws DuplicateRecordException
	 * @throws ApplicationException
	 */

	public void update(UserBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("User Model Update method start");

		Session session = null;
		Transaction tx = null;

		UserBean beanExist = findByLogin(bean.getLogin());

		System.out.println("bean.getLogin()"+bean.getLogin());
		// Check if updated LoginId already exist
		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("LoginId is already exist");
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

			throw new ApplicationException("Exception in update user ");
		} finally {

			if (session != null) {
				session.close();
			}
		}
		log.debug("User Model Update method end");
	}

	/**
	 * Find User by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 */
	public UserBean findByPK(long pk) throws ApplicationException {

		log.debug("User Model findByPK method start");
		Session session = null;
		UserBean bean = null;

		try {
			session = HibDataSource.getSession();
			bean = (UserBean) session.get(UserBean.class, pk);
		} catch (HibernateException e) {

			throw new ApplicationException("Exception in getting user by PK");
		} finally {
			session.close();
		}

		log.debug("User Model findByPK method End");
		return bean;
	}

	/**
	 * Gets List of User
	 * 
	 * @return list : List of User
	 * @throws ApplicationException
	 *
	 */

	public List list() throws ApplicationException {

		return list(0, 0);
	}

	/**
	 * get List of Users with pagination
	 * 
	 * @return list : List of User
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {

		log.debug("User Model list  method start");
		Session session = null;

		List list = null;

		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(UserBean.class);

			if (pageSize > 0) {

				pageNo = (pageNo - 1) * pageSize;// (pageNo - 1) * pageSize+1

				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("exception in getting list of User");
		} finally {
			session.close();
		}

		log.debug("User Model list  method end");
		return list;
	}

	/**
	 * Searches User
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws ApplicationException
	 */

	public List search(UserBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Searches User with pagination
	 * 
	 * @return list : List of Users
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws ApplicationException
	 */

	public List search(UserBean bean, int pageNo, int pageSize) throws ApplicationException {

		log.debug("User Model search method start");

		System.out.println("User Model search method start");

		Session session = null;

		List list = null;

		try {

			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(UserBean.class);

			if (bean.getId() > 0) {

				criteria.add(Restrictions.eq("id", bean.getId()));
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				criteria.add(Restrictions.like("firstName", bean.getFirstName() + "%"));
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				criteria.add(Restrictions.like("lastName", bean.getFirstName() + "%"));
			}
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {

				criteria.add(Restrictions.like("login", bean.getLogin() + "%"));

			}
			if (bean.getRoleId() > 0) {
				criteria.add(Restrictions.eq("roleId", bean.getRoleId()));
			}

			if (pageSize > 0) {

				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();

		} catch (HibernateException e) {

			throw new ApplicationException("Exception in search user");

		} finally {
			session.close();
		}

		log.debug("User Model search method end");
		return list;
	}

	/**
	 * Find User by Login
	 * 
	 * @param name
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 */
	public UserBean findByLogin(String login) throws ApplicationException {

		log.debug("User Model findbylogin method start");
		Session session = null;
		UserBean bean = null;
		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(UserBean.class);

			criteria.add(Restrictions.like("login", login));

			List list = criteria.list();

			if (list.size() >0) {
				bean = (UserBean) list.get(0);
			} else {
				bean = null;
			}
		} catch (HibernateException e) {

			throw new ApplicationException("exception in getting user by login");
		} finally {
			session.close();
		}

		log.debug("User Model findbylogin method end");
		return bean;
	}

	/**
	 * Change Password By pk
	 * 
	 * @param pk
	 *            ,oldPassword,newPassword : get parameter
	 * @return bean
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 */

	public boolean changePassword(Long id, String oldPassword, String newPassword)
			throws RecordNotFoundException, ApplicationException {

		log.debug("User Model changepassword method start");
		boolean flag = false;

		UserBean beanExist = null;

		beanExist = findByPK(id);

		if (beanExist != null && beanExist.getPassword().equals(oldPassword)) {
			beanExist.setPassword(newPassword);

			try {
				update(beanExist);
			} catch (DuplicateRecordException e) {

				throw new ApplicationException("exception in  change password ");
			}
			flag = true;
		} else {
			throw new RecordNotFoundException("exception in change password");
		}
		

		log.debug("Model changePassword End");
		return flag;
	}

	/**
	 * @param login
	 *            : String login
	 * @param password
	 *            : password
	 * @throws DatabaseException
	 */
	public UserBean authenticate(String login, String password) throws ApplicationException {

		log.debug("User Model authenticate method start");
		Session session = null;
		UserBean bean = null;

		session = HibDataSource.getSession();

		Query q = session.createQuery("from UserBean where login=? and password =?");

		q.setString(0, login);
		q.setString(1, password);

		List list = q.list();

		if (list.size() > 0) {

			bean = (UserBean) list.get(0);

		} else {

			bean = null;
		}
		log.debug("Model authenticate End");
		return bean;

	}

	/**
	 * Register a user
	 * 
	 * @param bean
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when user already exists
	 */
	public long registerUser(UserBean bean) throws ApplicationException, DuplicateRecordException, DatabaseException {

		log.debug("Model registerUser Started");

		long pk = add(bean);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		

		log.debug("Model registerUser End");

		return pk;

	}

	/**
	 * Send the password of User to his Email
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 *             : if user not found
	 */

	public boolean forgetPassword(String login) throws ApplicationException, RecordNotFoundException {

		log.debug("Model forgetPassword Started");

		UserBean userData = findByLogin(login);

		boolean flag = false;

		if (userData == null) {

			throw new RecordNotFoundException("Email Id Does not exist.");

		}

		

		log.debug("Model forgetPassword End");
		return flag;
	}

}
