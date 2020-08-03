package in.co.hospital.mgt.sys.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



/**
 * Hibernate DataSource for Data Connection Pool
 * 
 * @author NavigableSet
 * @version 1.0
 * @Copyright (c) NavigableSet
 * 
 */
public class HibDataSource {

	private static SessionFactory factory;

	private HibDataSource() {

	}
	/**
     * Get the instance of Session Factory
     * 
     * @return sessionFactory
     */
	public static SessionFactory getSessionfactory() {
		if (factory == null) {
			factory = new Configuration().configure().buildSessionFactory();
		}

		return factory;

	}

	 /**
     * Get Session by SessionFactory
     * 
     * @return session
     */
    public static Session getSession() {
        Session session = getSessionfactory().openSession();
        return session;
    }

    /**
     * Close Session
     */
    public static void closeSession(Session session) {
        if (session != null) {
            session.close();
        }
    }

}
