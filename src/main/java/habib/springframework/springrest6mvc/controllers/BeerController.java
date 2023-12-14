package habib.springframework.springrest6mvc.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import habib.springframework.springrest6mvc.model.Beer;
import habib.springframework.springrest6mvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/beer")
public class BeerController {
	private final BeerService beerService;

	@GetMapping
	public List<Beer> listBeers() {
		return beerService.listBeers();
	}

	@GetMapping("{beerId}")
	public Beer getBeerById(@PathVariable("beerId") UUID beerId) {
		log.debug("Get Beer By ID - in controller. ID: ");

		return beerService.getBeerById(beerId);
	}

}
