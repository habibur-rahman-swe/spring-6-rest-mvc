package habib.springframework.springrest6mvc.services;

import java.util.UUID;

import habib.springframework.springrest6mvc.model.Beer;

public interface BeerService {
	Beer getBeerById(UUID id);
}
