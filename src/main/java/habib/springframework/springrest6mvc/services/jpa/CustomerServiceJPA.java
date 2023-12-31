package habib.springframework.springrest6mvc.services.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import habib.springframework.springrest6mvc.mappers.CustomerMapper;
import habib.springframework.springrest6mvc.model.CustomerDTO;
import habib.springframework.springrest6mvc.repositories.CustomerRepository;
import habib.springframework.springrest6mvc.services.CustomerService;
import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;

	@Override
	public List<CustomerDTO> getAllCustomers() {
		return customerRepository.findAll()
				.stream()
				.map(customerMapper::customerToCustomerDto)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<CustomerDTO> getCustomerById(UUID uuid) {
		return Optional.ofNullable(customerMapper.customerToCustomerDto(customerRepository.findById(uuid).orElse(null)));
	}
	
	@Override
	public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) {
		return customerMapper
				.customerToCustomerDto(
						customerRepository
						.save(customerMapper.customerDtoToCustomer(customerDTO)));
	}

	@Override
	public Optional<CustomerDTO> updateCustomerByID(UUID customerId, CustomerDTO customerDTO) {
		AtomicReference<Optional<CustomerDTO>> atomicReference = new AtomicReference<>();
		
		customerRepository.findById(customerId).ifPresentOrElse(
				foundCustomer -> {
					foundCustomer.setName(customerDTO.getName());
					atomicReference.set(Optional.of(
							customerMapper.customerToCustomerDto(
									customerRepository.save(foundCustomer))
							));
				}
				,
				() -> {
					atomicReference.set(Optional.empty());
				});
		
		return atomicReference.get();
	}
	
	@Override
	public Boolean deleteCustomerById(UUID customerId) {
		if (customerRepository.existsById(customerId)) {
			customerRepository.deleteById(customerId);
			return true;
		}
		return false;
	}
	
	@Override
	public void patchCustomerById(UUID customerId, CustomerDTO customerDTO) {
		// TODO Auto-generated method stub

	}

}
