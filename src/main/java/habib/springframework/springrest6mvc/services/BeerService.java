package habib.springframework.springrest6mvc.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import habib.springframework.springrest6mvc.model.BeerDTO;

public interface BeerService {
	List<BeerDTO> listBeers();
	Optional<BeerDTO> getBeerById(UUID id);
	BeerDTO saveNewBeers(BeerDTO beerDTO);
	void updateBeerById(UUID beerId, BeerDTO beerDTO);
	void deleteById(UUID beerId);
	void patchBeerById(UUID beerId, BeerDTO beerDTO);
}
