package com.skumarv.o2o;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	Configuration configuration = new Configuration();
        	configuration.configure("com/skumarv/o2o/hibernate.cfg.xml");
        	System.out.println("Hibernate Configuration loaded");
        	
        	//ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        	//System.out.println("Hibernate serviceRegistry created");
        	
        	SessionFactory sessionFactory = configuration.buildSessionFactory();
        	
            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}