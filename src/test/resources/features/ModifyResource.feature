Feature: Modify Resource
  As a user
  I can only modify my own resources
  As an admin
  I can't modify any resource

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@sample.app"
    And There is a registered admin with username "admin" and password "password" and email "admin@sample.app"
    And There is a university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    And There is a degree with name "Enginyeria informàtica", faculty "EPS", and university id "1"
    And There is a subject with name "Estructura de dades", course "2", optional "false", and degree id "1"
    And There is a registered resource with name "Exàmen Parcial 1" by the user "student", with description "Primer parcial", type "TEST" and file "Activitat 1 ..." for the subject "Estructura de dades"

  Scenario: Modify a resource as an admin user
    Given I login as "admin" with password "password"
    When I modify the resource with name "Exàmen Parcial 1" and the new name "Parcial 1"
    Then The response code is 403
    And There is a registered resource with name "Exàmen Parcial 1" by the user "student", with description "Primer parcial", type "TEST" and file "Activitat 1 ..." for the subject "Estructura de dades"

  Scenario: Modify an own resource as normal user changing its name
    Given I login as "student" with password "password"
    When I modify the resource with name "Exàmen Parcial 1" and the new name "Parcial 1"
    Then The response code is 204
    And A resource named "Exàmen Parcial 1" does not exist but a resource named "Parcial 1" does

  Scenario: Modify an own resource as normal user changing its description
    Given I login as "student" with password "password"
    When I modify the resource with name "Exàmen Parcial 1" and the new description "Primer parcial molt difícil"
    Then The response code is 204
    And The resource named "Exàmen Parcial 1" has a description "Primer parcial molt difícil"
  
  Scenario: Modify an own resource as normal user changing its file
    Given I login as "student" with password "password"
    When I modify the resource with name "Exàmen Parcial 1" and the new file "Activitat 2 ..."
    Then The response code is 204
    And The resource named "Exàmen Parcial 1" has a file "Activitat 2 ..."

  Scenario: Modify an own resource as normal user changing its resource type
    Given I login as "student" with password "password"
    When I modify the resource with name "Exàmen Parcial 1" and the new resource type "ASSIGNMENT"
    Then The response code is 204
    And The resource named "Exàmen Parcial 1" is type "ASSIGNMENT"

  Scenario: Modify an own resource as normal user changing its resource type to a non existent
    Given I login as "student" with password "password"
    When I modify the resource with name "Exàmen Parcial 1" and the new resource type "NON EXISTENT TYPE"
    Then The response code is 404
    And The resource named "Exàmen Parcial 1" is type "TEST"
  
  Scenario: Modify an external resource as normal user
    Given I login as "student" with password "password"
    And There is a registered user with username "student2" and password "password" and email "student@sample.app"
    And There is a registered resource with name "Exàmen Parcial 2" by the user "student2", with description "Segon parcial", type "TEST" and file "Activitat 1 ..." for the subject "Estructura de dades"
    When I modify the resource with name "Exàmen Parcial 2" and the new name "Parcial 2"
    Then The response code is 403
  
  Scenario: Modify an own resource as normal user changing its name by an already existing name
    Given I login as "student" with password "password"
    And There is a registered resource with name "Exàmen Parcial 2" by the user "student2", with description "Segon parcial", type "TEST" and file "Activitat 1 ..." for the subject "Estructura de dades"
    When I modify the resource with name "Exàmen Parcial 2" and the new name "Exàmen Parcial 1"
    Then The response code is 409
    And There is only one resource with name "Exàmen Parcial 1"

