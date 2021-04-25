package ru.nazarenko.se.project;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		//// FIXME: 25/04/2021 поставить в загрузчик
		hiberConfigLoader();


		SpringApplication.run(Application.class, args);
	}

	private static void hiberConfigLoader() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();

			// SESSION_FACTORY = configuration.buildSessionFactory();
		} catch (HibernateException ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}


}
