package habib.springframework.springrest6mvc.controllers;

import java.util.UUID;

import org.springframework.stereotype.Controller;

import habib.springframework.springrest6mvc.model.Beer;
import habib.springframework.springrest6mvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Controller
public class BeerController {
	private final BeerService beerService;
	
	public Beer getBeerById(UUID id) {
		log.debug("Get Beer By ID - in controller. ID: " + beerService.getBeerById(id).toString());
		
		return beerService.getBeerById(id);
	}
	
}
