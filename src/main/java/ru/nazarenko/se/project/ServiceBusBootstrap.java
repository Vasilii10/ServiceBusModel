package ru.nazarenko.se.project;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ServiceBusBootstrap {

	public static void main(String[] args) {

		//// FIXME: 25/04/2021 поставить в загрузчик
		
		//todo записывать его когда СУТ скажет
		
		// // TODO: 27/04/2021 обработка ошибок транзакций 
		
		// // FIXME: 27/04/2021 что-то с айдишкой придумать !
		
		// // TODO: 27/04/2021 вынос конфигурации 
		
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
