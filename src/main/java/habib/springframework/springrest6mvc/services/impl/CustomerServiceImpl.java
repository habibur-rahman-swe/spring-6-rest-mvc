package habib.springframework.springrest6mvc.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import habib.springframework.springrest6mvc.model.CustomerDTO;
import habib.springframework.springrest6mvc.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	
	private Map<UUID, CustomerDTO> customerMap;
	
	public CustomerServiceImpl() {
		CustomerDTO customer1 = CustomerDTO.builder()
							.id(UUID.randomUUID())
							.name("Customer 1")
							.version(1)
							.createdDate(LocalDateTime.now())
							.updateDate(LocalDateTime.now())
							.build();
		
		CustomerDTO customer2 = CustomerDTO.builder()
							.id(UUID.randomUUID())
							.name("Customer 2")
							.version(1)
							.createdDate(LocalDateTime.now())
							.updateDate(LocalDateTime.now())
							.build();
		
		CustomerDTO customer3 = CustomerDTO.builder()
							.id(UUID.randomUUID())
							.name("Customer 3")
							.version(1)
							.createdDate(LocalDateTime.now())
							.updateDate(LocalDateTime.now())
							.build();
		
		customerMap = new HashMap<>();
		
		customerMap.put(customer1.getId(), customer1);
		customerMap.put(customer2.getId(), customer2);
		customerMap.put(customer3.getId(), customer3);
	}
	
	@Override
	public Optional<CustomerDTO> getCustomerById(UUID uuid) {
		return Optional.of(customerMap.get(uuid));
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		return new ArrayList<>(customerMap.values());
	}

	@Override
	public void patchCustomerById(UUID customerId, CustomerDTO customerDTO) {
		CustomerDTO existing = customerMap.get(customerId);
		
		if (StringUtils.hasText(customerDTO.getName())) {
			existing.setName(customerDTO.getName());
		}
	}

	@Override
	public Boolean deleteCustomerById(UUID customerId) {
		customerMap.remove(customerId);
		
		return true;
	}

	@Override
	public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) {
		CustomerDTO savedCustomer = CustomerDTO.builder()
								.id(UUID.randomUUID())
								.version(1)
								.updateDate(LocalDateTime.now())
								.createdDate(LocalDateTime.now())
								.name(customerDTO.getName())
								.build();
		
		customerMap.put(savedCustomer.getId(), savedCustomer);
		
		return savedCustomer;
	}

	@Override
	public Optional<CustomerDTO> updateCustomerByID(UUID customerId, CustomerDTO customerDTO) {
		CustomerDTO existing = customerMap.get(customerId);
		
		existing.setName(customerDTO.getName());
		
		return Optional.of(existing);
	}

	
	
}
