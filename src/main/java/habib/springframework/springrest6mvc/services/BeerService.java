package habib.springframework.springrest6mvc.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import habib.springframework.springrest6mvc.model.BeerDTO;

public interface BeerService {
	List<BeerDTO> listBeers();
	Optional<BeerDTO> getBeerById(UUID id);
	BeerDTO saveNewBeers(BeerDTO beerDTO);
	Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beerDTO);
	Boolean deleteById(UUID beerId);
	void patchBeerById(UUID beerId, BeerDTO beerDTO);
}
