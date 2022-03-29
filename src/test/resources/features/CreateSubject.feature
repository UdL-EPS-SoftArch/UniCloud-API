Feature: Create Subject
  As an Admin
  I want to create a Subject

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@local.com"
    Given There is a registered admin with username "admin" and password "password" and email "admin@local.com"

  Scenario: Create a new subject when not authenticated
    Given I'm not logged in
    When I create a new subject with name "Algebra" and course 1 and optional "Mandatory"
    Then The response code is 401
    And A new subject has not been created

  Scenario: Create a new subject as an admin
    Given I login as "admin" with password "password"
    When I create a new subject with name "Algebra" and course 1 and optional "Mandatory"
    Then The response code is 201
    And A new subject has been created

  Scenario: Create a new subject with same name as an admin
    Given I login as "admin" with password "password"
    And There is a Subject with name "Algebra",course 2 and optional "Optional"
    When I create a new subject with name "Algebra" and course 1 and optional "Mandatory"
    Then The response code is 409
    And A new subject has not been added

  Scenario: Create a new subject with associated degree
    Given I login as "admin" with password "password"
    And I create a degree with name "Medicine" and faculty "Facultat de medicina"
    When I create a new Subject with name "Algebra", course 1 and optional "Mandatory" associated with the name of the degree "Medicine"
    Then The response code is 201
    And A new subject has been created
    And It has been associated the degree named "Medicine" to the subject

  #Scenario: Create a new subject with associated resource
    #Given I login as "admin" with password "password"
    #When I create a new Subject with name "Algebra", course 1 and optional "Mandatory" associated with name of the resources "name"
    #Then The response code is 201
    #And A new subject has been created
    #And It has been associated the resource name "name" to the subject



