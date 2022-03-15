Feature: Delete Subject
  As an Admin
  I want to delete a Subject

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@demo.com"
    Given There is a registered admin with username "admin" and password "password" and email "admin@demo.com"
    Given There is a Subject with name "Algebra",course 1 and optional "Mandatory"

    Scenario: Delete a Subject when not authenticated
      Given I'm not logged in
      When I remove a Subject with name "Algebra"
      Then The response code is 401
      And The subject with name "Algebra" has not been removed

    Scenario: Delete a Subject when authenticated as an admin
      Given I login as "admin" with password "password"
      When I remove a Subject with name "Algebra"
      Then The response code is 204
      And The subject with name "Algebra" has been removed

    Scenario: Delete a Subject when authenticated as a student
      Given I login as "student" with password "password"
      When I remove a Subject with name "Algebra"
      Then The response code is 403
      And The subject with name "Algebra" has not been removed

    Scenario: Delete a non-existing Subject when authenticated as an admin
      Given I login as "admin" with password "password"
      When I remove a non-existent Subject
      Then The response code is 404