package ru.nazarenko.se.project;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ServiceBusBootstrap {

	public static void main(String[] args) {
		loadHibernateConfig();

		SpringApplication.run(ServiceBusBootstrap.class, args);
	}

	private static void loadHibernateConfig() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();

		} catch (HibernateException ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

}
