package habib.springframework.springrest6mvc.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import habib.springframework.springrest6mvc.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID>{
	
}
