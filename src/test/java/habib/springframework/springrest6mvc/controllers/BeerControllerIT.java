package habib.springframework.springrest6mvc.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import habib.springframework.springrest6mvc.model.BeerDTO;
import habib.springframework.springrest6mvc.repositories.BeerRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
class BeerControllerIT {

	@Autowired
	BeerController beerController;
	
	@Autowired
	BeerRepository beerRepository;

	@Test
	void testDeleteByIdNotFound() {
		assertThrows(NotFoundException.class, () -> {
            beerController.deleteByID(UUID.randomUUID());
        });
	}
	
	@Test
	void testUpdateNotFound() {
		assertThrows(NotFoundException.class, () -> {
            beerController.updateById(UUID.randomUUID(), BeerDTO.builder().build());
        });
	}
	
	@Test
	void testListBeers() {
		List<BeerDTO> dtos = beerController.listBeers();
		
		assertThat(dtos.size()).isEqualTo(3);
	}
	
	@Rollback
	@Transactional
	@Test
	void testEmptyList() {
		
		beerRepository.deleteAll();
		
		List<BeerDTO>  dtos = beerController.listBeers();
		
		assertThat(dtos.size()).isEqualTo(0);
	}
	
	
}
