package habib.springframework.springrest6mvc.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import habib.springframework.springrest6mvc.model.CustomerDTO;

public interface CustomerService {
	
	Optional<CustomerDTO> getCustomerById(UUID uuid);
	
	List<CustomerDTO> getAllCustomers();

	void patchCustomerById(UUID customerId, CustomerDTO customerDTO);

	void deleteCustomerById(UUID customerId);

	CustomerDTO saveNewCustomer(CustomerDTO customerDTO);
	
}
