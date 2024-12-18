Feature: Manage Restaurants

  Scenario: Create a new restaurant
    Given I have valid restaurant details:
      | name      | postalCode  | street        | number  | kitchenType | cnpj                  | capacity | initialTime | finalTime |
      | Test Cafe | 12345       | Rua SÃO PAULO | 123     | ITALIAN     | 12.345.678/0001-00    | 50       | 08:00:00    | 18:00:00  |
    When I send a POST request to "/api/restaurants" with the restaurant details
    Then I should receive a 201 status code
    And I should see the newly created restaurant details

  Scenario: Get a restaurant by ID
    Given the restaurant with ID 1 exists in the system
    When I send a GET request to "/api/restaurants/1"
    Then I should receive a 200 status code
    And I should see the details of the restaurant with ID 1
      | name      | postalCode  | street        | number  | kitchenType | cnpj                  | capacity | initialTime | finalTime |
      | Test Cafe | 12345       | Rua SÃO PAULO | 123     | ITALIAN     | 12.345.678/0001-00    | 50       | 08:00:00    | 18:00:00  |

  # It is necessary to fix the error described at RestaurantUseCase.java first for update flow.
  #Scenario: Update an existing restaurant
  #  Given the restaurant with ID 1 exists in the system
  #  And I have updated restaurant details:
  #    | name              | street      |
  #    | Updated Test Cafe | Rua Goiânia |
  #  When I send a PUT request to "/api/restaurants/1" with the updated details
  #  Then I should receive a 200 status code
  #  And I should see the updated restaurant details

  Scenario: Delete a restaurant
    Given I have valid restaurant details:
      | name      | postalCode  | street        | number  | kitchenType | cnpj                  | capacity | initialTime | finalTime |
      | Test Cafe | 12345       | Rua SÃO PAULO | 123     | ITALIAN     | 12.345.678/0001-00    | 50       | 08:00:00    | 18:00:00  |
    When I send a POST request to "/api/restaurants" with the restaurant details
    Then I should receive a 201 status code
    Given the restaurant with ID 1 exists in the system
    When Regarding to restaurants, I send a DELETE request to "/api/restaurants/1"
    Then I should receive a 204 status code
    And the restaurant with ID 1 should no longer exist in the system, and should retrieve 404
