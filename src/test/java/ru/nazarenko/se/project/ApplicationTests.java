package ru.nazarenko.se.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.nazarenko.se.project.controller.DeliveryRequestController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private DeliveryRequestController deliveryRequestController;

	@Test
	void contextLoads() {
		assertThat(deliveryRequestController).isNotNull();
	}

}
