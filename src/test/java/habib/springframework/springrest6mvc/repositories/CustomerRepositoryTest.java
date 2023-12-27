package habib.springframework.springrest6mvc.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import habib.springframework.springrest6mvc.entities.Customer;

@DataJpaTest
class CustomerRepositoryTest {

	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	void testSavedCustomer() {
		Customer customer = customerRepository.save(Customer.builder()
							.name("Customer")
							.build());
		
		assertThat(customer).isNotNull();
		assertThat(customer.getId()).isNotNull();
	}

}
