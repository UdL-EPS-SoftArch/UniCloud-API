Feature: Create Rating
  As student
  I want to register a new rating

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@local.com"
    Given There is a registered admin with username "admin" and password "password" and email "admin@local.com"
    #And There is a registered resource with name "Exàmen Parcial 1" by the user "student", with description "Primer parcial", type "TEST" and file "Activitat 1 ..." for the subject "Estructura de dades"

  Scenario: Add rating as student when already authenticated
    Given I login as "student" with password "password"
    When I register a new rating with rating 7 and comment "Aproved" referenced to resource with name "Exàmen Parcial 1"
    Then The response code is 201
    And A new rating has been created as "student"

  Scenario: Add rating as student when already authenticated referenced to non-existing resource
    Given I login as "student" with password "password"
    When I register a new rating with rating 7 and comment "Aproved" referenced to resource with name "xxx"
    Then The response code is 400

  Scenario: Add rating when not authenticated
    Given I'm not logged in
    When I register a new rating with rating 7 and comment "Aproved"
    Then The response code is 401
    And A new rating has not been created

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



