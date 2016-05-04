package br.com.sistema.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private static final SessionFactory SESSION_FACTORY;
	public static final String HIBERNATE_SESSION = "hibernate_session";
	
	static {
		try {
			System.out.println("tentando configurar a session factory");
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			SESSION_FACTORY = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("session factory criada com sucesso");
		} catch (Exception e) {
			System.out.println("ocorreu um erro ao iniciar a session factory" + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}
	
}


