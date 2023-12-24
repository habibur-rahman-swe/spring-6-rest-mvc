package habib.springframework.springrest6mvc.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import habib.springframework.springrest6mvc.model.Customer;
import habib.springframework.springrest6mvc.services.CustomerService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CustomerController {
	public static final String CUSTOMER_PATH = "/api/v1/customer";
	public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

	private final CustomerService customerService;

	@PatchMapping(value = CUSTOMER_PATH_ID)
	public ResponseEntity<Customer> patchCustomerById(@PathVariable("customerId") UUID customerId,
			@RequestBody Customer customer) {
		customerService.patchCustomerById(customerId, customer);

		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = CUSTOMER_PATH_ID)
	public ResponseEntity<Customer> deleteCustomerById(@PathVariable("customerId") UUID customerId) {

		customerService.deleteCustomerById(customerId);

		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = CUSTOMER_PATH_ID)
	public ResponseEntity<Customer> updateCustomerByID(@PathVariable("customerId") UUID customerId,
			@RequestBody Customer customer) {
		Customer savedCustomer = customerService.saveNewCustomer(customer);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", CUSTOMER_PATH + "/" + savedCustomer.getId().toString());

		return new ResponseEntity<Customer>(headers, HttpStatus.CREATED);
	}

	@PostMapping(value=CUSTOMER_PATH)
	public ResponseEntity<Customer> handlePost(@RequestBody Customer customer) {
		Customer savedCustomer = customerService.saveNewCustomer(customer);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", CUSTOMER_PATH + "/" + savedCustomer.getId().toString());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Customer> handleNotFoundException() {
		System.out.println("In exception handler");
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(value = CUSTOMER_PATH)
	public List<Customer> listAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping(value = CUSTOMER_PATH_ID)
	public Customer getCustomerById(@PathVariable("customerId") UUID id) {
		return customerService.getCustomerById(id);
	}
	
	

}
