Feature: Create Resource
  As a user
  I want to create a resource
  As an admin user
  I can't create a resource

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@sample.app"
    And There is a registered admin with username "admin" and password "password" and email "admin@sample.app"
    And There is a university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    And There is a degree created with name "Enginyeria informàtica" and faculty "EPS" and university "Universitat de Lleida"
    And There is a Subject with name "Programacio",course 2 and optional "false"

  Scenario: Create a new resource as an admin
    Given I login as "admin" with password "password"
    When I create a resource with name "name", description "description", filename "example.pdf", and resource type "NOTE" for the subject name "Programacio"
    Then The response code is 403

  Scenario: Create a new resource as a student with resource type Note
    Given I login as "student" with password "password"
    When I create a resource with name "name", description "description", filename "example.pdf", and resource type "NOTE" for the subject name "Programacio"
    Then The response code is 201
    And The resource count is 1
    And A new resource has been created

  Scenario: Create a new resource as a student with resource type Assignment
    Given I login as "student" with password "password"
    When I create a resource with name "name", description "description", filename "example.pdf", and resource type "ASSIGNMENT" for the subject name "Programacio"
    Then The response code is 201
    And The resource count is 1
    And A new resource has been created

  Scenario: Create a new resource as a student with resource type Test
    Given I login as "student" with password "password"
    When I create a resource with name "name", description "description", filename "example.pdf", and resource type "TEST" for the subject name "Programacio"
    Then The response code is 201
    And The resource count is 1
    And A new resource has been created

  Scenario: Create a new resource as a student with non-existing resource type
    Given I login as "student" with password "password"
    When I create a resource with name "name", description "description", filename "example.pdf", and resource type "ASDFGH" for the subject name "Programacio"
    Then The response code is 400

  Scenario: Create a new resource as a student with empty name
    Given I login as "student" with password "password"
    When I create a resource with name "", description "description", filename "example.pdf", and resource type "NOTE" for the subject name "Programacio"
    Then The response code is 400

  Scenario: Create a new resource as a student with empty file
    Given I login as "student" with password "password"
    When I create a resource with name "name", description "description", filename "", and resource type "NOTE" for the subject name "Programacio"
    Then The response code is 400

  Scenario: Create a new resource as a student with an already existing name
    Given I login as "student" with password "password"
    And There is a registered resource with name "name" by the user "student", with description "description", file "example.pdf", and resource type "NOTE" for the subject name "Programacio"
    When I create a resource with name "name", description "description", filename "example.pdf", and resource type "NOTE" for the subject name "Programacio"
    Then The response code is 409
    And The resource count is 1

  Scenario: Create a new resource as a student with a non-existent subject
    Given I login as "student" with password "password"
    When I create a resource with name "name", description "description", filename "example.pdf", and resource type "NOTE" for the subject name "n"
    Then The response code is 400