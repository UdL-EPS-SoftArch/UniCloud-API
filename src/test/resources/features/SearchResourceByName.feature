Feature: Search Resource by name
  As a user, normal or admin
  I want to find resources by name

  Background:
    Given There is a registered user with username "student" and password "password" and email "student@sample.app"
    And There is a university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    And There is a degree created with name "GEI" and faculty "EPS"
    And There is a registered subject "subject" for the degree "degree" in the university "university"
    And There is a registered resource with name "Exàmen Parcial 1" by the user "student", with description "Primer parcial", type "TEST" and file "Activitat 1 ..." for the subject "Estructura de dades"
    And There is a registered resource with name "Exàmen Parcial 2" by the user "student", with description "Segon parcial", type "TEST" and file "Activitat 1 ..." for the subject "Estructura de dades"

  Scenario: Search a resource by the exact name
    Given I login as "student" with password "password"
    When I search a resource by its exact name "Exàmen Parcial 1"
    Then The response code is 200
    And "1" resources have been retrieved and his name is "Exàmen Parcial 1"

  Scenario: Search for resources containing a name
    Given I login as "student" with password "password"
    When I search for resources containing the name "Exàmen"
    Then The response code is 200
    And "2" resources have been retrieved and their names contain "Exàmen"

  Scenario: Search a resource by the exact name and not finding any result
    Given I login as "student" with password "password"
    When I search a resource by its exact name "name_nonexistent"
    Then The response code is 404

  Scenario: Search for resources containing a name and not finding any result
    Given I login as "student" with password "password"
    When I search for resources containing the name "name_nonexistent"
    Then The response code is 404
