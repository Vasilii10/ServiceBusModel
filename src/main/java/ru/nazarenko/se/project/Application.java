package ru.nazarenko.se.project;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.nazarenko.se.project.entities.*;
import ru.nazarenko.se.project.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.nazarenko.se.project.util.HibernateUtil;

import static ru.nazarenko.se.project.model.ServiceProposalStatus.IN_SEQUECE;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		try {
			Configuration configuration = new Configuration();
			configuration.configure();

			// SESSION_FACTORY = configuration.buildSessionFactory();
		} catch (HibernateException ex) {
			throw new ExceptionInInitializerError(ex);
		}

		SpringApplication.run(Application.class, args);
	}


}
