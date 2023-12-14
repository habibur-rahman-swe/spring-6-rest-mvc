package habib.springframework.springrest6mvc.services;

import java.util.List;
import java.util.UUID;

import habib.springframework.springrest6mvc.model.Customer;

public interface CustomerService {
	
	Customer getCustomerById(UUID uuid);
	
	List<Customer> getAllCustomers();
	
}
