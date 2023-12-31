package habib.springframework.springrest6mvc.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import habib.springframework.springrest6mvc.model.Beer;
import habib.springframework.springrest6mvc.model.BeerStyle;
import habib.springframework.springrest6mvc.services.BeerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

	private Map<UUID, Beer> beerMap;

	public BeerServiceImpl() {
		this.beerMap = new HashMap<>();

		Beer beer1 = Beer.builder().id(UUID.randomUUID()).version(1).beerName("Galaxy Cat")
				.beerStyle(BeerStyle.PALE_ALE).upc("12345").price(new BigDecimal("12.99")).quantityOnHand(122)
				.createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();

		Beer beer2 = Beer.builder().id(UUID.randomUUID()).version(1).beerName("Crank").beerStyle(BeerStyle.PALE_ALE)
				.upc("1234525").price(new BigDecimal("11.99")).quantityOnHand(192).createdDate(LocalDateTime.now())
				.updateDate(LocalDateTime.now()).build();
		Beer beer3 = Beer.builder().id(UUID.randomUUID()).version(1).beerName("Sunshine City").beerStyle(BeerStyle.IPA)
				.upc("123456").price(new BigDecimal("9.99")).quantityOnHand(1254).createdDate(LocalDateTime.now())
				.updateDate(LocalDateTime.now()).build();

		beerMap.put(beer1.getId(), beer1);
		beerMap.put(beer2.getId(), beer2);
		beerMap.put(beer3.getId(), beer3);
	}

	@Override
	public List<Beer> listBeers() {
		return new ArrayList<>(beerMap.values());
	}

	@Override
	public Optional<Beer> getBeerById(UUID id) {

		log.debug("Get Beer Id is service. ID: " + id.toString());

		return Optional.of(beerMap.get(id));
	}

	@Override
	public Beer saveNewBeers(Beer beer) {
		Beer savedBeer = Beer.builder().id(UUID.randomUUID()).createdDate(LocalDateTime.now())
				.updateDate(LocalDateTime.now()).beerName(beer.getBeerName()).beerStyle(beer.getBeerStyle())
				.quantityOnHand(beer.getQuantityOnHand()).upc(beer.getUpc()).price(beer.getPrice()).build();
		
		beerMap.put(savedBeer.getId(), savedBeer);
		
		return savedBeer;
	}

	@Override
	public void updateBeerById(UUID beerId, Beer beer) {
		
		Beer existing = beerMap.get(beerId);
		existing.setBeerName(beer.getBeerName());
		existing.setPrice(beer.getPrice());
		existing.setUpc(beer.getUpc());
		existing.setQuantityOnHand(beer.getQuantityOnHand());
		
		beerMap.put(existing.getId(), existing);
	}

	@Override
	public void deleteById(UUID beerId) {
		beerMap.remove(beerId);
	}

	@Override
	public void patchBeerById(UUID beerId, Beer beer) {
		Beer existing = beerMap.get(beerId);
		
		if (StringUtils.hasText(beer.getBeerName())) {
			existing.setBeerName(beer.getBeerName());
		}
		
		if (beer.getBeerStyle() != null) {
			existing.setBeerStyle(beer.getBeerStyle());
		}
		
		if (beer.getPrice() != null) {
			existing.setPrice(beer.getPrice());
		}
		
		if (beer.getQuantityOnHand() != null) {
			existing.setQuantityOnHand(beer.getQuantityOnHand());
		}
		
		if (StringUtils.hasText(beer.getUpc())) {
			existing.setUpc(beer.getUpc());
		}
		
		
	}

}
