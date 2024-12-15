
# src/test/resources/features/User.feature
Feature: Manage Users

  Scenario: Create a new user
    Given I have valid cpf: "893.996.750-09"
    When I send a POST request to "/api/users" with the following data : name: "Felipe", email: "fefe@email.com", cpf: "893.996.750-09"
    Then I should receive a 201 Created response for users

  Scenario: Successfully retrieving an existing user
    Given The user with id "1", should be retrieve
    When I send a GET request to "/api/users/1" no collection
    Then I should receive a 200 OK response
    And the response body should contain the details of the user with ID "1"

  Scenario: Retrieve all users
    Given I have a valid API endpoint for users
    When I send a GET request to "/api/users" expecting a collection
    Then I should receive a 200 OK response

  Scenario: Successfully updating an existing user
    Given a user with ID "1" exists in the system with the following data : name: "Felipe", email: "fefe@email.com", cpf: "893.996.750-09"
    When I send a PUT request to "/api/users/1" with the following data:
      | name    | Felipe            |
      | email   | felipe@email.com  |
      | cpf     | 893.996.750-09    |
    Then the response status should be 200
    And the response body should contain the updated user details as follows id: "1", name: "Felipe", email: "felipe@email.com", cpf: "893.996.750-09"

  Scenario: Successfully deleting an existing user
    Given The user with id "1", should exists
    When I send a DELETE request to "/api/users/1"
    Then the response status should be 204