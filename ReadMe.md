### Known limitations of solution

* using default spring-boot configuration so request processing is in sync-mode in the thread from dedicated the pool.

### Key design decisions

* separating game logic from other abstraction layers (e.g. persistence)
* working on interface dependencies (easy way to replace implementation without business logic changes)
* entry points for each domain parts by User & Game services (facades to use those business features)
* open to future strategy development (adding new one without any impact to existing code, but current strategies depends only on different balance calculation way)

### How to start

To start application run following command 
* `mvn spring-boot:run`

Application uses H2 in memory database so there isn't any additional setup necessary.

### API description

First step of testing application should be user creation call by sending POST request with empty body to `/users` path. Each user is initialized with 5000$ during creation (that's why endpoint does not require any request data).

Then the following endpoints will be available to have fun with game API (models are described below):

* POST `/games/free` - start free-mode game (require PlayGameRequest, return PlayGameResponse)
* POST `/games/cash` - start cash-mode game (require PlayGameRequest, return PlayGameResponse)
* GET `/games/{gameId}` - fetching specific game round by game-id (return GameModel)
* GET `/games/users/{userId}` - fetching all user game rounds by user-id (return list of GameModel)

PlayGameRequest consists of - { Long userId, BigDecimal betAmount }

PlayGameResponse consists of - { BigDecimal balance }

GameModel consists of - { Long id, String type, BigDecimal balance, BigDecimal winAmount, BetModel bet }
