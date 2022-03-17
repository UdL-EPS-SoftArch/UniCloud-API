Feature: Delete Resource
  As a user
  I can only delete my resources
  As an admin
  I can delete any resource

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@sample.app"
    And There is a registered admin with username "admin" and password "password" and email "admin@sample.app"
    And There is a university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    And There is a degree created with name "GEI" and faculty "EPS"
    And There is a registered subject "subject" for the degree "degree" in the university "university"
    And There is a registered resource with name "Exàmen Parcial 1" by the user "student", with description "Primer parcial", type "TEST" and file "Activitat 1 ..." for the subject "Estructura de dades"

  Scenario: Delete a resource as an admin
    Given I login as "admin" with password "password"
    When I delete a resource with name "Exàmen Parcial 1"
    Then The response code is 200
    And There is not a registered resource with name "Exàmen Parcial 1"

  Scenario: Delete an own resource as a normal user
    Given I login as "student" with password "password"
    When I delete a resource with name "Exàmen Parcial 1"
    Then The response code is 200
    And There is not a registered resource with name "Exàmen Parcial 1"

  Scenario: Delete an external resource as a normal user
    Given I login as "student" with password "password"
    And There is a registered user with username "student2" and password "password" and email "user@sample.app"
    And There is a registered resource with name "Exàmen Parcial 2" by the user "student2", with description "Segon parcial", type "TEST" and file "Activitat 1 ..." for the subject "Estructura de dades"
    When I delete a resource with name "Exàmen Parcial 2"
    Then The response code is 403
    And The resource named "Exàmen Parcial 2" still exists
