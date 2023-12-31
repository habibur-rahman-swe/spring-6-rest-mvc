package habib.springframework.springrest6mvc.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import habib.springframework.springrest6mvc.model.Beer;
import habib.springframework.springrest6mvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {

	public static final String BEER_PATH = "/api/v1/beer";
	public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

	private final BeerService beerService;

	@PatchMapping(BEER_PATH_ID)
	public ResponseEntity<Beer> updateBeerPathById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {

		beerService.patchBeerById(beerId, beer);

		return new ResponseEntity<Beer>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(BEER_PATH_ID)
	public ResponseEntity<Beer> deleteByID(@PathVariable("beerId") UUID beerId) {

		beerService.deleteById(beerId);

		return new ResponseEntity<Beer>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(BEER_PATH_ID)
	public ResponseEntity<Beer> updateById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {

		beerService.updateBeerById(beerId, beer);

		return new ResponseEntity<Beer>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(BEER_PATH)
	public ResponseEntity<Beer> handlePost(@RequestBody Beer beer) {

		Beer savedBeer = beerService.saveNewBeers(beer);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());

		return new ResponseEntity<Beer>(headers, HttpStatus.CREATED);

	}

	@GetMapping(BEER_PATH)
	public List<Beer> listBeers() {
		return beerService.listBeers();
	}

	@GetMapping(BEER_PATH_ID)
	public Beer getBeerById(@PathVariable("beerId") UUID beerId) {

		log.debug("Get Beer By ID - in controller. ID: ");

		return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
	}

}
