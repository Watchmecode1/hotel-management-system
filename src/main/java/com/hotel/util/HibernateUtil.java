package com.hotel.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.hotel.entity.*;

/**
 * Classe per i settings del framework Hibernate.
 *
 * @author Matthew Mazzotta
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null)
			try {
				Configuration configuration = new Configuration();
				
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/faggio_rosso?useSSL=false&amp");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.FORMAT_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "update");
				settings.put(Environment.CHECK_NULLABILITY, "true");
				
				configuration.setProperties(settings);
				
				configuration.addAnnotatedClass(Cliente.class);
				configuration.addAnnotatedClass(Documento.class);
				configuration.addAnnotatedClass(Prodotto.class);
				configuration.addAnnotatedClass(Camera.class);
				configuration.addAnnotatedClass(Prenotazione.class);
				configuration.addAnnotatedClass(Consumazione.class);
				configuration.addAnnotatedClass(Magazzino.class);
				configuration.addAnnotatedClass(Ordine.class);
				configuration.addAnnotatedClass(TipoCamera.class);
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch(Exception e) {
				e.printStackTrace();
			}
		return sessionFactory;
	}
}