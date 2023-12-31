package habib.springframework.springrest6mvc.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import habib.springframework.springrest6mvc.model.Beer;
import habib.springframework.springrest6mvc.services.BeerService;
import habib.springframework.springrest6mvc.services.impl.BeerServiceImpl;

//@SpringBootTest

@WebMvcTest(BeerController.class)
class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	BeerService beerService;

	@Captor
	ArgumentCaptor<UUID> uuidArgumentCaptor;

	@Captor
	ArgumentCaptor<Beer> beerArgumentCaptor;

	BeerServiceImpl beerServiceImpl;

	@BeforeEach
	void setUp() {
		beerServiceImpl = new BeerServiceImpl();
	}

	@Test
	void testPatchBeer() throws JsonProcessingException, Exception {
		Beer beer = beerServiceImpl.listBeers().get(0);

		Map<String, Object> beerMap = new HashMap<>();

		beerMap.put("beerName", "New Name");

		mockMvc.perform(patch(BeerController.BEER_PATH_ID, beer.getId()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(beerMap)))
				.andExpect(status().isNoContent());

		verify(beerService).patchBeerById(uuidArgumentCaptor.capture(), beerArgumentCaptor.capture());

		assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
		assertThat(beerMap.get("beerName")).isEqualTo(beerArgumentCaptor.getValue().getBeerName());

	}

	@Test
	void testDeleteBeer() throws Exception {
		Beer beer = beerServiceImpl.listBeers().get(0);

		mockMvc.perform(delete(BeerController.BEER_PATH_ID, beer.getId()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

		ArgumentCaptor.forClass(UUID.class);
		verify(beerService).deleteById(uuidArgumentCaptor.capture());

		assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
	}

	@Test
	void testUpdateBeer() throws Exception {
		Beer beer = beerServiceImpl.listBeers().get(0);

		mockMvc.perform(put(BeerController.BEER_PATH_ID, beer.getId()).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(beer)))
				.andExpect(status().isNoContent());

		verify(beerService).updateBeerById(any(UUID.class), any(Beer.class));
	}

	@Test
	void testCreateNewBeer() throws Exception {

		Beer beer = beerServiceImpl.listBeers().get(0);

//		System.out.println(objectMapper.writeValueAsString(beer));

		beer.setVersion(null);
		beer.setId(null);

		given(beerService.saveNewBeers(any(Beer.class))).willReturn(beerServiceImpl.listBeers().get(1));

		mockMvc.perform(post(BeerController.BEER_PATH).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(beer)))
				.andExpect(status().isCreated()).andExpect(header().exists("Location"));

	}

	@Test
	void testListBeers() throws Exception {
		given(beerService.listBeers()).willReturn(beerServiceImpl.listBeers());

		mockMvc.perform(get(BeerController.BEER_PATH).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.length()", is(3)));
	}

	@Test
	void getBeerByIdNotFound() throws Exception {
		given(beerService.getBeerById(any(UUID.class))).willReturn(Optional.empty());
		mockMvc.perform(get(BeerController.BEER_PATH_ID, UUID.randomUUID())).andExpect(status().isNotFound());
	}

	@Test
	void getBeerById() throws Exception {

		Beer testBeer = beerServiceImpl.listBeers().get(0);

		given(beerService.getBeerById(testBeer.getId())).willReturn(Optional.of(testBeer));

		mockMvc.perform(get(BeerController.BEER_PATH_ID, testBeer.getId()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(testBeer.getId().toString())))
				.andExpect(jsonPath("$.beerName", is(testBeer.getBeerName())));

	}

}
