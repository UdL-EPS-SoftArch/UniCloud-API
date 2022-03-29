Feature: Create Rating
  As student
  I want to register a new rating

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@local.com"
    Given There is a registered admin with username "admin" and password "password" and email "admin@local.com"

  Scenario: Add rating as student when already authenticated
    Given I login as "student" with password "password"
    When I register a new rating with rating 7 and comment "Aproved"
    Then The response code is 201
    And A new rating has been created

  Scenario: Add rating when not authenticated
    Given I'm not logged in
    When I register a new rating with rating 7 and comment "Aproved"
    Then The response code is 401
    And A new rating has not been created

    # Modificar els permissos del WebSecurityConfig per al admin
  Scenario: Add rating as admin
    Given I login as "admin" with password "password"
    When I register a new rating with rating 7 and comment "Aproved"
    Then The response code is 403
    And A new rating has not been created

  Scenario: Add a negative rating as student when already authenticated
    Given I login as "student" with password "password"
    When I register a new rating with rating -3 and comment "Aproved"
    Then The response code is 400
    And A new rating has not been created
