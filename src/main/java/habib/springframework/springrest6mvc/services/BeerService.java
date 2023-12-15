package habib.springframework.springrest6mvc.services;

import java.util.List;
import java.util.UUID;

import habib.springframework.springrest6mvc.model.Beer;

public interface BeerService {
	List<Beer> listBeers();
	Beer getBeerById(UUID id);
	Beer saveNewBeers(Beer beer);
	void updateBeerById(UUID beerId, Beer beer);
}
