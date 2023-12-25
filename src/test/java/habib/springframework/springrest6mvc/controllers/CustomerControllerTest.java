package habib.springframework.springrest6mvc.controllers;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import habib.springframework.springrest6mvc.model.Customer;
import habib.springframework.springrest6mvc.services.CustomerService;
import habib.springframework.springrest6mvc.services.impl.CustomerServiceImpl;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	CustomerService customerService;

	@Captor
	ArgumentCaptor<UUID> uuidArgumentCaptor;

	@Captor
	ArgumentCaptor<Customer> customerArgumentCaptor;

	CustomerServiceImpl customerServiceImpl;

	@BeforeEach
	void setUp() {
		customerServiceImpl = new CustomerServiceImpl();
	}

	@Test
	void getCustomerByIdNotFound() throws Exception {
		given(customerService.getCustomerById(any(UUID.class))).willReturn(Optional.empty());
		mockMvc.perform(get(CustomerController.CUSTOMER_PATH_ID, UUID.randomUUID())).andExpect(status().isNotFound());
	}
	
	void getCustomerByID() throws Exception {
		Customer testCustomer = customerServiceImpl.getAllCustomers().get(0);
		
		given(customerService.getCustomerById(testCustomer.getId())).willReturn(Optional.of(testCustomer));
		
		mockMvc.perform(get(CustomerController.CUSTOMER_PATH_ID, testCustomer.getId()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(testCustomer.getId().toString())))
				.andExpect(jsonPath("$.customerName", is(testCustomer.getName())));
		
	}

}
