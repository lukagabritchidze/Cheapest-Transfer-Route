# Cheapest Transfer Route Application

This project is a Spring Boot application designed to calculate the cheapest transfer route based on input parameters, such as maximum weight capacity and a list of available transfers. The application leverages a dynamic programming approach to determine the optimal set of transfers that minimizes the total cost while adhering to the weight limit. It provides a REST API endpoint for users to input their transfer data and receive the calculated results.

----------------------------------------

## Application Overview

### Key Features

- Dynamic Programming Solution: Efficiently calculates the cheapest transfer route using a dynamic programming approach to solve a variation of the knapsack problem.


- Validation: Ensures input data is valid, with checks for maximum weight, transfer weights, and costs.


- REST API: Provides a simple POST endpoint to accept transfer data and return the optimal result.


- Error Handling: Returns descriptive error messages for invalid inputs.


-----------------------------

## Technology Stack

- Backend Framework: Spring Boot

- Java Version: 17

- Build Tool: Maven

- Testing: JUnit 5


----------------------------------

## Building the Application

1. Clone the repository

```
https://github.com/lukagabritchidze/Cheapest-Transfer-Route.git
```

2. Build the Project 

```
mvn clean install
```

3. Run the Application:
```
mvn spring-boot:run
```


-------------------------

## API Endpoints
### Calculate Cheapest Route

**Endpoint**: `api/cheapest-route/cheapest-route`

**Method**: POST

**URL**: `http://localhost:8080/api/cheapest-route/cheapest-route`

--------------------------

## Running Tests
To execute all unit and integration tests run this command:
```
mvn test
```

-----------------------------------------------

## Example for CURL Commands
### Calculate Cheapest Route
Since no front end exists use this command to test on desirable input, run this command in Git Bash. There are also other cases in `api/examples`.
```curl
curl -X POST \
-H "Content-Type: application/json" \
-d '{"maxWeight":15,"availableTransfers":[{"weight":5,"cost":10},{"weight":10,"cost":20},{"weight":3,"cost":5},{"weight":8,"cost":15}]}' \
http://localhost:8080/api/cheapest-route/cheapest-route
```
