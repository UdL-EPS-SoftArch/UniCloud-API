Feature: Create university
  As an admin
  I want to create a university

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@local.com"
    Given There is a registered admin with username "admin" and password "password" and email "admin@local.com"

  Scenario: Create new university when not authenticated
    Given I'm not logged in
    When I create a new university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    Then The response code is 401
    And The university count is 0

  Scenario: Create new university as a student
    Given I login as "student" with password "password"
    When I create a new university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    Then The response code is 403
    And The university count is 0


  Scenario: Create new university as an admin
    Given I login as "admin" with password "password"
    When I create a new university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    Then The response code is 201
    And The university count is 1
    And A new university has been created

  Scenario: Create new university with same name as an admin
    Given I login as "admin" with password "password"
    And There is a university with name "Universitat de Lleida", acronym "UPC", country "Spain", city "Barcelona"
    When I create a new university with name "Universitat de Lleida", acronym "UDL", country "Catalonia", city "Lleida"
    Then The response code is 409
    And The university count is 1

  Scenario: Create new university with blank name as an admin
    Given I login as "admin" with password "password"
    When I create a new university with name "", acronym "UDL", country "Catalonia", city "Lleida"
    Then The response code is 400

  Scenario: Create new university with blank acronym as an admin
    Given I login as "admin" with password "password"
    When I create a new university with name "Universitat de Lleida", acronym "", country "Catalonia", city "Lleida"
    Then The response code is 400

  Scenario: Create new university with blank country as an admin
    Given I login as "admin" with password "password"
    When I create a new university with name "Universitat de Lleida", acronym "UDL", country "", city "Lleida"
    Then The response code is 400

  Scenario: Create new university with blank city as an admin
    Given I login as "admin" with password "password"
    When I create a new university with name "Universitat de Lleida", acronym "UDL", country "Catalonia", city ""
    Then The response code is 400