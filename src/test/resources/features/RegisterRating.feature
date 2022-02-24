Feature: Register Rating
  In order to use the app
  As a user
  I want to register myself and get an account

  Scenario: Add rating to a user when already authenticatede
    Given I login as "demo" with password "password"
    When I register a new rating with rating 7 and comment "Aprobat"
    Then The response code is 201

