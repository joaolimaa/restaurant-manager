Feature: Manage Restaurants

  Scenario: Create a new restaurant
    Given I have valid restaurant details:
      | name      | postalCode  | city        | state         | neighborhood        | street        | number  | kitchenType | cnpj                  | capacity | operatingHours      |
      | Test Cafe | 12345       | Test City   | Test State    | Test Neighborhood   | Main Street   | 123     | ITALIAN     | 12.345.678/0001-00    | 50       | MONDAY, 08:00-18:00 |
    When I send a POST request to "/api/restaurants" with the restaurant details
    Then I should receive a 201 status code
    And I should see the newly created restaurant details