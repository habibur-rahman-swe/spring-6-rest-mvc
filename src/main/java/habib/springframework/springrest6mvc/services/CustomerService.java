package habib.springframework.springrest6mvc.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import habib.springframework.springrest6mvc.model.CustomerDTO;

public interface CustomerService {
	
	List<CustomerDTO> getAllCustomers();
	Optional<CustomerDTO> getCustomerById(UUID uuid);
	CustomerDTO saveNewCustomer(CustomerDTO customerDTO);
	Optional<CustomerDTO> updateCustomerByID(UUID customerId, CustomerDTO customerDTO);
	Boolean deleteCustomerById(UUID customerId);
	void patchCustomerById(UUID customerId, CustomerDTO customerDTO);
}
