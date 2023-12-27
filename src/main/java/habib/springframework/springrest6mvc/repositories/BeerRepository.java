package habib.springframework.springrest6mvc.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import habib.springframework.springrest6mvc.entities.Beer;

public interface BeerRepository extends JpaRepository<Beer, UUID>{

}
