package habib.springframework.springrest6mvc.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import habib.springframework.springrest6mvc.model.CustomerDTO;
import habib.springframework.springrest6mvc.repositories.CustomerRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
class CustomerControllerIT {

	@Autowired
	CustomerController customerController;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	void testDeleteByIdNotFound() {
		assertThrows(NotFoundException.class, () -> {
			customerController.deleteCustomerById(UUID.randomUUID());
		});
	}
	
	@Test
	void testUpdateNotFound() {
		assertThrows(NotFoundException.class, () -> {
			customerController.updateCustomerByID(UUID.randomUUID(), CustomerDTO.builder().build());
		});
	}

	@Test
	void testListCustomers() {
		List<CustomerDTO> dtos = customerController.listAllCustomers();
		
		assertThat(dtos.size()).isEqualTo(3);
	}
	
	@Rollback
	@Transactional
	@Test
	void testEmptyList() {
		
		customerRepository.deleteAll();
		
		List<CustomerDTO> dtos = customerController.listAllCustomers();
		
		assertThat(dtos.size()).isEqualTo(0);
		
	}
	
}
