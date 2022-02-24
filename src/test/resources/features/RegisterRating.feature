Feature: Add Rating
  As student
  I want to register a new rating

  Scenario: Add rating as student when already authenticated
    Given I login as "demo" with password "password"
    When I register a new rating with rating 7 and comment "Aproved"
    Then The response code is 201

  Scenario: Add rating as student when already unauthenticated
    Given I'm not logged in
    When I register a new rating with rating 4 and comment "Suspense"
    Then The response code is 401
    And A new rating has not been created
