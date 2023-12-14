package habib.springframework.springrest6mvc.controllers;


import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeerControllerTest {

	@Autowired
	BeerController beerController;
	
	@Test
	void test() {
		System.out.println(beerController.getBeerById(UUID.randomUUID()));
	}

}
