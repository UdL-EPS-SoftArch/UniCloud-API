Feature: Create university
  As an admin
  I want to create a university

  Scenario: Create new university when not authenticated
    Given I'm not logged in
    When I create a new university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    Then The response code is 401
    And It has not been created any university yet

  Scenario: Create new university as a student
    Given I login as "student" with password "password"
    When I create a new university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    Then The response code is 401
    And It has not been created any university yet

  Scenario: Create new university as an admin
    Given I login as "admin" with password "password"
    When I create a new university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    Then The response code is 201
    And It has been created a new university

  Scenario: Create new university with same name as an admin
    Given I login as "admin" with password "password"
    When I create a new university with name "Universitat de Lleida", acronym "UPC", country "Spain", city "Barcelona"
    Then The response code is 409
    And It has not been created any new university