package habib.springframework.springrest6mvc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import habib.springframework.springrest6mvc.model.Beer;

//@ControllerAdvice
public class ExceptionController {
//	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Beer> handleNotFoundException() {
//		System.out.println("In exception handler");
		
		return ResponseEntity.notFound().build();
	}
}
