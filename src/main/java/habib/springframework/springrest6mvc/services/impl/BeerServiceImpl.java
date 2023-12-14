package habib.springframework.springrest6mvc.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import habib.springframework.springrest6mvc.model.Beer;
import habib.springframework.springrest6mvc.model.BeerStyle;
import habib.springframework.springrest6mvc.services.BeerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
	
	@Override
	public Beer getBeerById(UUID id) {
		
		log.debug("Get Beer Id is service was called");
		
		return Beer.builder()
				.id(id)
				.version(1)
				.beerName("Galaxy Cat")
				.beerStyle(BeerStyle.PALE_ALE)
				.upc("12345")
				.price(new BigDecimal("12.99"))
				.quantityOnHand(1233)
				.createdDate(LocalDateTime.now())
				.updateDate(LocalDateTime.now())
				.build();
		
	}

}
