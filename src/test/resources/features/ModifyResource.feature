Feature: Modify Resource
  As a user
  I can only modify my own resources
  As an admin
  I can't modify any resource

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@sample.app"
    And There is a registered admin with username "admin" and password "password" and email "admin@sample.app"
    And There is a university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    And There is a degree created with name "GEI" and faculty "EPS"
    #And There is a subject with name "Estructura de dades", course "2", optional "false", and degree id "1"
    And There is a registered resource with name "name" by the user "student", with description "description", file "example.pdf", and resource type "NOTE" for the subject id 1

  Scenario: Modify a resource as an admin user
    Given I login as "admin" with password "password"
    When I modify the resource with name "name" and the new name "new_name"
    Then The response code is 403

  Scenario: Modify an own resource as normal user changing its name
    Given I login as "student" with password "password"
    When I modify the resource with name "name" and the new name "new_name"
    Then The response code is 200
    And A resource named "name" does not exist but a resource named "new_name" does

  Scenario: Modify an own resource as normal user changing its description
    Given I login as "student" with password "password"
    When I modify the resource with name "name" and the new description "new_description"
    Then The response code is 200
    And The resource named "name" has a description "new_description"

  Scenario: Modify an own resource as normal user changing its file
    Given I login as "student" with password "password"
    When I modify the resource with name "name" and the new file "Activitat 2 ..."
    Then The response code is 200
    And The resource named "name" has a file "Activitat 2 ..."

  Scenario: Modify an own resource as normal user changing its resource type
    Given I login as "student" with password "password"
    When I modify the resource with name "name" and the new resource type "assignment"
    Then The response code is 200
    And The resource named "name" is type "assignment"

  Scenario: Modify an own resource as normal user changing its resource type to a non existent
    Given I login as "student" with password "password"
    When I modify the resource with name "name" and the new resource type "NON EXISTENT TYPE"
    Then The response code is 400
    And The resource named "name" is type "note"

  Scenario: Modify an external resource as normal user
    Given I login as "student" with password "password"
    And There is a registered student with username "student2" and password "password" and email "student2@sample.app"
    And There is a registered resource with name "name2" by the user "student2", with description "description", file "example.pdf", and resource type "NOTE" for the subject id 1
    When I modify the resource with name "name2" and the new name "new_name2"
    Then The response code is 403

  Scenario: Modify an own resource as normal user changing its name by an already existing name
    Given I login as "student" with password "password"
    And There is a registered resource with name "name2" by the user "student", with description "description", file "example.pdf", and resource type "NOTE" for the subject id 1
    When I modify the resource with name "name2" and the new name "name"
    Then The response code is 409
    And There is only one resource with name "name"

