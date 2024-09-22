# Restaurant Ordering App

## Overview

The Restaurant Ordering app is a back-end project developed in hopes to aid company workers pick a restaurant and order 
from it.

As many people have different tastes and wishes, the only right way to handle this is democracy. Employees get to vote 
between different restaurants in the area and after all the votes have been put, the winning restaurant is decided.

## Getting Started

### Prerequisites

- [Java 22 or higher](https://www.oracle.com/java/technologies/javase/jdk22-archive-downloads.html)
- [Docker](https://www.docker.com/)
- [Gradle 8.9](https://gradle.org/releases/)

### Installation

1. **Clone the repository:**
    ```bash
    git clone ssh://git@gitlab.og-cs.hr:2244/education/niko-spring-crud.git
    # OR
    git clone https://gitlab.og-cs.hr/education/niko-spring-crud.git
    ```
2. **Build the application using gradle:**
    ```commandline
    cd RestaurantOrdering
    ./gradlew clean build   # Use for building everything, including tests and docs
    ./gradlew bootJar       # Use for only building the executable jar file
    ```

3. **Run docker compose in app root folder**

    ```commandline
    docker-compose up --build
    ```

4. **Access the app**
    > API Docs: http://localhost:8080/swagger-ui.html

    > Database: http://localhost:5432

## API Endpoints

### Poll API Endpoints
- `GET /polls`
- `GET /polls/{id}`
- `POST /polls`
- `PUT /polls/{id}`
- `DELETE /polls/{id}`

### Restaurant API Endpoints
- `GET /restaurants`
- `GET /restaurants/{id}`
- `POST /restaurants`
- `PUT /restaurants/{id}`
- `DELETE /restaurants/{id}`

### MenuItem API Endpoints
- `GET /menu-items`
- `GET /menu-items/{id}`
- `POST /menu-items`
- `PUT /menu-items/{id}`
- `DELETE /menu-items/{id}`

### Order API Endpoints
- `GET /orders`
- `GET /orders/{id}`
- `POST /orders`
- `PUT /orders/{id}`
- `DELETE /orders/{id}`
- `POST /orders/{orderId}/items`
- `DELETE /orders/{orderId}/items/{orderItemId}`

### OrderItem API Endpoints
- `GET /order-items`
- `GET /order-items/{id}`
- `POST /order-items`
- `PUT /order-items/{id}`
- `DELETE /order-items/{id}`

### Vote API Endpoints
- `GET /votes`
- `GET /votes/{id}`
- `POST /votes`
- `PUT /votes/{id}`
- `DELETE /votes/{id}`

### User API Endpoints
- `GET /users`
- `GET /users/{id}`
- `POST /users`
- `PUT /users/{id}`
- `DELETE /users/{id}`

