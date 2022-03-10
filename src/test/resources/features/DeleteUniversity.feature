Feature: DELETE a University

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@demo.com"
    Given There is a registered admin with username "admin" and password "password" and email "admin@demo.com"
    Given There is a university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"

  Scenario: Delete a University when not authenticated
    Given I'm not logged in
    When I remove a University with name "Universitat de Lleida"
    Then The response code is 401
    And The University with name "Universitat de Lleida" has not been removed

  Scenario: Delete a University when authenticated as a student
    Given I login as "student" with password "password"
    When I remove a University with name "Universitat de Lleida"
    Then The response code is 403
    And The University with name "Universitat de Lleida" has not been removed

  Scenario: Delete a University when authenticated as an admin
    Given I login as "admin" with password "password"
    When I remove a University with name "Universitat de Lleida"
    Then The response code is 204
    And The University with name "Universistat de Lleida" has been removed

    Scenario: Delete a non-existing University when authenticated as an admin
      Given I login as "admin" with password "password"
      When I remove a University with name "Universitat de Barcelona"
      Then The response code is 404

