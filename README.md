# Spring 6 Rest MVC

Learn about `project lombok` and `spring rest mvc`.

-  Project Lombock
    - @Data: set automatically `getters` and `setters`
    - @Builder: use to build objects
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
    - @AllArgsConstructor
    - @Slf4j: to see debug log. 
        `logging.level.habib=debug` is added to `application.properties` file to enable the log on console
    