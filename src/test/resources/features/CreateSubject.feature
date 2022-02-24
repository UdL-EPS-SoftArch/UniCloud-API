Feature: Create Subject
  As an Admin
  I want to create a Subject

  Scenario: Create a new subject when not authenticated
    Given I'm not logged in
    When I create a new Subject with name "Algebra", course 1.00 and optional true
    Then The response code is 401
    And It has not been created any subject yet
    
  Scenario: Create a new subject as a student
    Given I login as "student" with password "password"
    When I create a new Subject with name "Algebra", course 1.00 and optional isMandatory
    Then The response code is 401
    And It has not been created any subject yet

  Scenario: Create a new subject as an admin
    Given I login as "admin" with password "password"
    When I create a new Subject with name "Algebra", course 1.00 and optional isMandatory
    Then The response code is 201
    And It has been created a new subject

  Scenario: Create a new subject with same name as an admin
    Given I login as "admin" with password "password"
    When I create a new Subject with name "Algebra", course 2.00 and optional isMandatory
    Then The response code is 409
    And It has not been created any new subject
