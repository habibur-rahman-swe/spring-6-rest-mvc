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
