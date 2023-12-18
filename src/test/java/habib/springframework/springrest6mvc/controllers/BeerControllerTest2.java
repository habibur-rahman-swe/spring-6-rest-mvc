package habib.springframework.springrest6mvc.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import habib.springframework.springrest6mvc.services.BeerService;

//@SpringBootTest
@WebMvcTest(BeerController.class)
class BeerControllerTest2 {

//	@Autowired
//	BeerController beerController;

	@Autowired
	MockMvc mocMvc;

	@MockBean
	BeerService beerService;

	@Test
	void testGetBeerById() throws Exception {
//		System.out.println(beerController.getBeerById(UUID.randomUUID()));
		mocMvc.perform(get("/api/v1/beer/" + UUID.randomUUID())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				;
	}

}
