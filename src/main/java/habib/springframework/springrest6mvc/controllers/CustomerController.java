package habib.springframework.springrest6mvc.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import habib.springframework.springrest6mvc.model.Customer;
import habib.springframework.springrest6mvc.services.CustomerService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@RestController
public class CustomerController {

	private final CustomerService customerService;

	@GetMapping
	public List<Customer> listAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping(value = "{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") UUID id) {
		return customerService.getCustomerById(id);
	}

}
