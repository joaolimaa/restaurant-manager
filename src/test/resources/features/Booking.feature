Feature: Manage Booking Operations

Scenario: Create a new booking
 Given the following user name: "Felipe", email: "fefe@email.com", cpf: "893.996.750-09" and the restaurant
    | name      | postalCode  | street        | number  | kitchenType | cnpj                  | capacity | initialTime | finalTime |
    | Test Cafe | 12345       | Rua SÃO PAULO | 123     | ITALIAN     | 12.345.678/0001-00    | 50       | 08:00:00    | 18:00:00  |
  When I send a POST request to "/v1/api/reserva" with the payload
    | restaurantId   | 1                    |
    | userId         | 1                    |
    | bookingDate    | 2025-12-31T18:00:00  |
    | status         | PENDING              |
    | peopleQuantity | 4                    |
  Then regarding to booking the response status should be 201
  And the response body should contain the created booking details

  Scenario: Retrieve a booking by ID
  Given the following user name: "Felipe", email: "fefe@email.com", cpf: "893.996.750-09" and the restaurant
    | name      | postalCode  | street        | number  | kitchenType | cnpj                  | capacity | initialTime | finalTime |
    | Test Cafe | 12345       | Rua SÃO PAULO | 123     | ITALIAN     | 12.345.678/0001-00    | 50       | 08:00:00    | 18:00:00  |
  When I send a POST request to "/v1/api/reserva" with the payload
    | restaurantId   | 1                    |
    | userId         | 1                    |
    | bookingDate    | 2025-12-31T18:00:00  |
    | status         | PENDING              |
    | peopleQuantity | 4                    |
  Then regarding to booking the response status should be 201
  Given a booking with ID 1 exists
  When regarding to booking I send a GET request to "/v1/api/reserva/1"
  Then regarding to booking the response status should be 200
  And the response body should contain the booking details

  Scenario: Delete a booking
  Given the following user name: "Felipe", email: "fefe@email.com", cpf: "893.996.750-09" and the restaurant
      | name      | postalCode  | street        | number  | kitchenType | cnpj                  | capacity | initialTime | finalTime |
      | Test Cafe | 12345       | Rua SÃO PAULO | 123     | ITALIAN     | 12.345.678/0001-00    | 50       | 08:00:00    | 18:00:00  |
    When I send a POST request to "/v1/api/reserva" with the payload
      | restaurantId   | 1                    |
      | userId         | 1                    |
      | bookingDate    | 2025-12-31T18:00:00  |
      | status         | PENDING              |
      | peopleQuantity | 4                    |
    Then regarding to booking the response status should be 201
    Given a booking with ID 1 exists
    When regarding to booking I send a DELETE request to "/v1/api/reserva/1"
    Then regarding to booking the response status should be 204
    And the booking with ID 1 should no longer exist