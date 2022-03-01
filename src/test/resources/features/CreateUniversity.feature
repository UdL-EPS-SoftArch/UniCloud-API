Feature: Create university
  As an admin
  I want to create a university

  Background:
    Given There is a registered user with username "student" and password "password" and email "student@local.com"
    Given There is a registered user with username "admin" and password "password" and email "admin@local.com"

  Scenario: Create new university when not authenticated
    Given I'm not logged in
    When I create a new university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    Then The response code is 401
    And A new university has not been created

#FALTA FICAR TEST USUARI STUDENT I ROLS


  Scenario: Create new university as an admin
    Given I login as "admin" with password "password"
    When I create a new university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    Then The response code is 201
    And A new university has been created

  Scenario: Create new university with same name as an admin
    Given I login as "admin" with password "password"
    And There is a university with name "Universitat de Lleida", acronym "UPC", country "Spain", city "Barcelona"
    When I create a new university with name "Universitat de Lleida", acronym "UDL", country "Catlonia", city "Lleida"
    Then The response code is 409
    And A new university has not been added