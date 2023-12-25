# Spring 6 Rest MVC

Learn about `project lombok` and `spring rest mvc`.

## Project Lombock
- `@Data`: set automatically `getters` and `setters`
- `@Builder`: use to build objects
    - Example 
    ```
    Beer.builder()
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
    ```
- `@AllArgsConstructor`: Set auto constructor for all  available arguments
- `@Slf4j`: to see debug log. 
    `logging.level.habib=debug` is added to `application.properties` file to enable the log on console

##  Restfull-Api's
-   `@RequestMapping`: before class add a common `HTTP` for all mapings of the class
-   `@RestController`: used for rest-api's controller before a class
-   `@PathVariable`: decleared the path variable
#### Example:


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



-  `@RequiredArgsConstructor`: Construct only required args constructor

-   `spring-boot-devtools`: restart spring-boot autometicaly every time after editing the spring-boot codes

-   `@PostMapping`: used for `post`
    
-   `@RequestBody`: bind the `json` to the `object`

    
            @PostMapping
            public ResponseEntity<Beer> handlePost(@RequestBody Beer beer) {
                
                Beer savedBeer = beerService.saveNewBeers(beer);
                
                HttpHeaders headers = new HttpHeaders();
                headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());
                
                return new ResponseEntity<Beer>(headers, HttpStatus.CREATED);
            }


-   `@PutMapping`: used to update an object or put an object


            @PutMapping("{beerId}")
            public ResponseEntity<Beer> updateById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {
                
                beerService.updateBeerById(beerId, beer);
                
                return new ResponseEntity<Beer>(HttpStatus.NO_CONTENT);
            }


-   `@DeleteMapping`:
    
        @DeleteMapping("{beerId}")
        public ResponseEntity<Beer> deleteByID(@PathVariable("beerId") UUID beerId) {

            beerService.deleteById(beerId);
            
            return new ResponseEntity<Beer>(HttpStatus.NO_CONTENT);
        }

-   `@PatchMapping`:
@PatchMapping("{beerId}")
 
        @PatchMapping("{beerId}")
        public ResponseEntity<Beer> updateBeerPathById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {
            
            beerService.patchBeerById(beerId, beer);
            
            return new ResponseEntity<Beer>(HttpStatus.NO_CONTENT);
        }

-   `@WebMvcTest` & `@MockBean`
    -   GetId
    ```
    @Test
	void getBeerById() throws Exception {

		Beer testBeer = beerServiceImpl.listBeers().get(0);

		 given(beerService.getBeerById(testBeer.getId())).willReturn(testBeer);
		
		mockMvc.perform(get("/api/v1/beer/" + testBeer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testBeer.getId().toString())))
                .andExpect(jsonPath("$.beerName", is(testBeer.getBeerName() + "go")));

	}
    ```
    -   GetList
        
        ```
        @Test
	    void testListBeers() throws Exception {
            given(beerService.listBeers()).willReturn(beerServiceImpl.listBeers());
            
            mockMvc.perform(get("/api/v1/beer").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.length()", is(4)));
        }
        ```

-   `ObjectMapper class`: ObjectMapper provides functionality for reading and writing JSON, either to and from basic POJOs (Plain Old Java Objects), or to and from a general-purpose JSON Tree Model ( JsonNode ), as well as related functionality for performing conversions.

```
@Autowired
ObjectMapper objectMapper;
```
```
@Test
	void testCreateNewBeer() throws JsonProcessingException {
		
		Beer beer = beerServiceImpl.listBeers().get(0);
		
		System.out.println(objectMapper.writeValueAsString(beer));
	}
```

-   `post test`:
```
@Test
	void testCreateNewBeer() throws Exception {
		
		Beer beer = beerServiceImpl.listBeers().get(0);
		
//		System.out.println(objectMapper.writeValueAsString(beer));
		
		beer.setVersion(null);
		beer.setId(null);
		
		given(beerService.saveNewBeers(any(Beer.class))).willReturn(beerServiceImpl.listBeers().get(1));
		
		mockMvc.perform(post("/api/v1/beer").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(beer)))
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"));
		
	}
```

-   `Put test`:
```
@Test
	void testUpdateBeer() throws Exception {
		Beer beer = beerServiceImpl.listBeers().get(0);
		
		mockMvc.perform(put("/api/v1/beer/" + beer.getId()).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(beer)))
				.andExpect(status().isNoContent());
		
		verify(beerService).updateBeerById(any(UUID.class), any(Beer.class));
	}
```

-   `Delete test`:
```
@Test
	void testDeleteBeer() throws Exception {
		Beer beer = beerServiceImpl.listBeers().get(0);
		
		mockMvc.perform(delete("/api/v1/beer/" + beer.getId())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		
		ArgumentCaptor<UUID> uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);
		verify(beerService).deleteById(uuidArgumentCaptor.capture());
		
		assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
	}
```

-   `Patch Test`:

```
@Test
	void testPatchBeer() throws JsonProcessingException, Exception {
		Beer beer = beerServiceImpl.listBeers().get(0);
		
		Map<String, Object> beerMap = new HashMap<>();
		
		beerMap.put("beerName", "New Name");
		
		mockMvc.perform(patch(BeerController.BEER_PATH_ID, beer.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(beerMap)))
				.andExpect(status().isNoContent());
		
		verify(beerService).patchBeerById(uuidArgumentCaptor.capture(), beerArgumentCaptor.capture());
		
		assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
		assertThat(beerMap.get("beerName")).isEqualTo(beerArgumentCaptor.getValue().getBeerName());
		
	}
```

-   `@ExceptionHandler`:
```
@ExceptionHandler(NotFoundException.class)
public ResponseEntity<Beer> handleNotFoundException() {
    System.out.println("In exception handler");
    
    return ResponseEntity.notFound().build();
}
```

-   `@ControllerAdvice`
```
@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Beer> handleNotFoundException() {
		System.out.println("In exception handler");
		
		return ResponseEntity.notFound().build();
	}
}

```

-   `@ResponseStatus`:
```
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "Value not found")
public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException() {

	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

	public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

```