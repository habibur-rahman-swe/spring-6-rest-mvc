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

import habib.springframework.springrest6mvc.model.BeerDTO;
import habib.springframework.springrest6mvc.model.BeerStyle;
import habib.springframework.springrest6mvc.services.BeerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

	private Map<UUID, BeerDTO> beerMap;

	public BeerServiceImpl() {
		this.beerMap = new HashMap<>();

		BeerDTO beer1 = BeerDTO.builder().id(UUID.randomUUID()).version(1).beerName("Galaxy Cat")
				.beerStyle(BeerStyle.PALE_ALE).upc("12345").price(new BigDecimal("12.99")).quantityOnHand(122)
				.createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();

		BeerDTO beer2 = BeerDTO.builder().id(UUID.randomUUID()).version(1).beerName("Crank").beerStyle(BeerStyle.PALE_ALE)
				.upc("1234525").price(new BigDecimal("11.99")).quantityOnHand(192).createdDate(LocalDateTime.now())
				.updateDate(LocalDateTime.now()).build();
		BeerDTO beer3 = BeerDTO.builder().id(UUID.randomUUID()).version(1).beerName("Sunshine City").beerStyle(BeerStyle.IPA)
				.upc("123456").price(new BigDecimal("9.99")).quantityOnHand(1254).createdDate(LocalDateTime.now())
				.updateDate(LocalDateTime.now()).build();

		beerMap.put(beer1.getId(), beer1);
		beerMap.put(beer2.getId(), beer2);
		beerMap.put(beer3.getId(), beer3);
	}

	@Override
	public List<BeerDTO> listBeers() {
		return new ArrayList<>(beerMap.values());
	}

	@Override
	public Optional<BeerDTO> getBeerById(UUID id) {

		log.debug("Get Beer Id is service. ID: " + id.toString());

		return Optional.of(beerMap.get(id));
	}

	@Override
	public BeerDTO saveNewBeers(BeerDTO beerDTO) {
		BeerDTO savedBeer = BeerDTO.builder().id(UUID.randomUUID()).createdDate(LocalDateTime.now())
				.updateDate(LocalDateTime.now()).beerName(beerDTO.getBeerName()).beerStyle(beerDTO.getBeerStyle())
				.quantityOnHand(beerDTO.getQuantityOnHand()).upc(beerDTO.getUpc()).price(beerDTO.getPrice()).build();
		
		beerMap.put(savedBeer.getId(), savedBeer);
		
		return savedBeer;
	}

	@Override
	public void updateBeerById(UUID beerId, BeerDTO beerDTO) {
		
		BeerDTO existing = beerMap.get(beerId);
		existing.setBeerName(beerDTO.getBeerName());
		existing.setPrice(beerDTO.getPrice());
		existing.setUpc(beerDTO.getUpc());
		existing.setQuantityOnHand(beerDTO.getQuantityOnHand());
		
		beerMap.put(existing.getId(), existing);
	}

	@Override
	public void deleteById(UUID beerId) {
		beerMap.remove(beerId);
	}

	@Override
	public void patchBeerById(UUID beerId, BeerDTO beerDTO) {
		BeerDTO existing = beerMap.get(beerId);
		
		if (StringUtils.hasText(beerDTO.getBeerName())) {
			existing.setBeerName(beerDTO.getBeerName());
		}
		
		if (beerDTO.getBeerStyle() != null) {
			existing.setBeerStyle(beerDTO.getBeerStyle());
		}
		
		if (beerDTO.getPrice() != null) {
			existing.setPrice(beerDTO.getPrice());
		}
		
		if (beerDTO.getQuantityOnHand() != null) {
			existing.setQuantityOnHand(beerDTO.getQuantityOnHand());
		}
		
		if (StringUtils.hasText(beerDTO.getUpc())) {
			existing.setUpc(beerDTO.getUpc());
		}
		
		
	}

}
