package habib.springframework.springrest6mvc.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import habib.springframework.springrest6mvc.model.Customer;

public interface CustomerService {
	
	Optional<Customer> getCustomerById(UUID uuid);
	
	List<Customer> getAllCustomers();

	void patchCustomerById(UUID customerId, Customer customer);

	void deleteCustomerById(UUID customerId);

	Customer saveNewCustomer(Customer customer);
	
}
