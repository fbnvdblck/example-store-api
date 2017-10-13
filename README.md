# Store API (example)

This is an example of RESTful API using Spring Boot, oAuth2 and JWT.

Note: the database structure and data are automatically loaded at startup from a migration tool (Liquibase).

### Example #1: Obtain an access token and a refresh token
``
curl -i -X POST --user webapp:mei6bai0Aimeiwe2aegheebohnoh1U -d { grant_type=password&username=jdoe&password=secret } http://localhost:8080/api/v0.1/oauth/token
``

### Example #2: Obtain a new access token with refresh token
``
curl -i -X POST --user webapp:mei6bai0Aimeiwe2aegheebohnoh1U -d { grant_type=refresh_token&refresh_token=<refresh token> } http://localhost:8080/api/v0.1/oauth/token
``

### Example #3: Retrieve all products (need an authentication)
``
curl -i -X GET -H "Authorization: Bearer <access token>" -H "Accept: application/json" http://localhost:8080/api/v0.1/products
``




