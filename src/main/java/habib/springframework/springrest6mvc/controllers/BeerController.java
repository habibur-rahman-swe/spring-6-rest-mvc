package habib.springframework.springrest6mvc.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping
	public ResponseEntity<Beer> handlePost(@RequestBody Beer beer) {
		
		Beer savedBeer = beerService.saveNewBeers(beer);
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());
		
		return new ResponseEntity<Beer>(headers, HttpStatus.CREATED);
		
	}
	
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
