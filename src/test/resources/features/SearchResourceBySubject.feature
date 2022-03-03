Feature: Search Resource by Subject
  As a user, normal or admin
  I want to find resources by subject

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    And There is a registered subject "subject" for the degree "degree" in the university "university"
    And There is a registered subject "subject2" for the degree "degree" in the university "university"
    And There is a registered resource with name "name" by the user "user", with description "description", file "file" and for the subject "subject"
    And There is a registered resource with name "name" by the user "user", with description "description", file "file" and for the subject "subject2"

  Scenario: Search a resource by the exact subject
    Given I login as "user" with password "password"
    When I search a resource by its exact subject "subject"
    Then The response code is 200
    And 1 resources has been retrieved

  Scenario: Search for resources containing a subject
    Given I login as "user" with password "password"
    When I search for resources containing the subject "subject"
    Then The response code is 200
    And 2 resources has been retrieved

  Scenario: Search a resource by the exact subject and not finding any result
    Given I login as "user" with password "password"
    When I search a resource by its exact subject "subject_nonexistent"
    Then The response code is 404
    And The error message is "No resource found with this exact subject"

  Scenario: Search for resources containing a subject and not finding any result
    Given I login as "user" with password "password"
    When I search for resources containing the subject "subject_nonexistent"
    Then The response code is 404
    And The error message is "No resources found containing this subject"
