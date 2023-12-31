package habib.springframework.springrest6mvc.mappers;

import org.mapstruct.Mapper;

import habib.springframework.springrest6mvc.entities.Beer;
import habib.springframework.springrest6mvc.model.BeerDTO;

@Mapper
public interface BeerMapper {
	Beer beerDtoToBeer(BeerDTO dto);
	
	BeerDTO beerToBeerDto(Beer beer);
}
