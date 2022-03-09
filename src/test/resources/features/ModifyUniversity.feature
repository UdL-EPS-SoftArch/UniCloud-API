Feature: Modify university
  As an admin
  I want to modify a university

  Background:
    Given There is a registered user with username "student" and password "password" and email "student@local.com"
    Given There is a registered user with username "admin" and password "password" and email "admin@local.com"
    And There is a university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Barcelona"

  Scenario: Modify a university when not authenticated
    Given I'm not logged in
    When I modify an university with name "Universitat de Lleida" changing field "acronym" to "UPC"
    Then The response code is 401
    And The university with name "Universitat de Lleida" has the acronym "UDL"

  Scenario: Modify a non-existing university when authenticated as an admin
    Given I login as "admin" with password "password"
    When I modify an university with name "Universitat de Barcelona" changing field "acronym" to "UPC"
    Then The response code is 404

#FALTA FICAR TEST USUARI STUDENT I ROLS
  Scenario: Modify a university as a student
    Given I login as "student" with password "password"
    When I modify an university with name "Universitat de Lleida" changing field "acronym" to "UPC"
    Then The response code is 401
    And The university with name "Universitat de Lleida" has the acronym "UDL"

  Scenario: Modify acronym field of university as an admin
    Given I login as "admin" with password "password"
    When I modify an university with name "Universitat de Lleida" changing field "acronym" to "UPC"
    Then The response code is 200
    And The university with name "Universitat de Lleida" has the acronym "UPC"

  Scenario: Modify country field of university as an admin
    Given I login as "admin" with password "password"
    When I modify an university with name "Universitat de Lleida" changing field "country" to "United Kingdom"
    Then The response code is 200
    And The university with name "Universitat de Lleida" has the country "United Kingdom"

  Scenario: Modify city field of university as an admin
    Given I login as "admin" with password "password"
    When I modify an university with name "Universitat de Lleida" changing field "city" to "Barcelona"
    Then The response code is 200
    And The university with name "Universitat de Lleida" has the city "Barcelona"

  Scenario: Modify name field of university as an admin
    Given I login as "admin" with password "password"
    When I modify an university with name "Universitat de Lleida" changing field "name" to "Universitat de Pamplona"
    Then The response code is 200
    And A university with name "Universitat de Pamplona" exists