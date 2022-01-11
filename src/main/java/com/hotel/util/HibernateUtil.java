package com.hotel.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.hotel.entity.*;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null)
			try {
				Configuration configuration = new Configuration();
				
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
				settings.put(Environment.URL, "");
				settings.put(Environment.USER, "");
				settings.put(Environment.PASS, "");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.FORMAT_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "update");
				settings.put(Environment.CHECK_NULLABILITY, "true");
				
				configuration.setProperties(settings);
				
				configuration.addAnnotatedClass(Customer.class);
				configuration.addAnnotatedClass(Document.class);
				configuration.addAnnotatedClass(Product.class);
				configuration.addAnnotatedClass(Room.class);
				configuration.addAnnotatedClass(Reservation.class);
				configuration.addAnnotatedClass(Consumption.class);
				configuration.addAnnotatedClass(Warehouse.class);
				configuration.addAnnotatedClass(Order.class);
				configuration.addAnnotatedClass(RoomType.class);
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch(Exception e) {
				e.printStackTrace();
			}
		return sessionFactory;
	}
}