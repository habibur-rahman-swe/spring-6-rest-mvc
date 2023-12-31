package habib.springframework.springrest6mvc.mappers;

import org.mapstruct.Mapper;

import habib.springframework.springrest6mvc.entities.Customer;
import habib.springframework.springrest6mvc.model.CustomerDTO;

@Mapper
public interface CustomerMapper {

	Customer customebrDtoToCustomer(CustomerDTO dto);
	
	CustomerDTO customerToCustomerDto(Customer customer);
	
}
