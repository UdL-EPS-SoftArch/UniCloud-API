Feature: Modify Subject
  As an Admin
  I want to modify a Subject

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@local.com"
    Given There is a registered admin with username "admin" and password "password" and email "admin@local.com"
    Given There is a Subject with name "Algebra",course 1 and optional "True"

  Scenario: Modify a Subject when not authenticated
    Given I'm not logged in
    When I modify a subject with name "Algebra" changing field course to 2
    Then The response code is 401
    And The field course of the subject with name "Algebra" has the value 1

  Scenario: Modify a non-existing subject when authenticated as an admin
    Given I login as "admin" with password "password"
    When I modify a subject with name "IA" changing field course to 2
    Then The response code is 404

  Scenario: Modify a Subject as an admin
    Given I login as "admin" with password "password"
    When I modify a subject with name "Algebra" changing field course to 2
    Then The response code is 200
    And The field course of the subject with name "Algebra" has the value 2

  Scenario: Modify a Subject as an admin
    Given I login as "admin" with password "password"
    When I modify a subject with name "Algebra" changing field optional to "False"
    Then The response code is 200
    And The field optional of the subject with name "Algebra" has the value "False"
