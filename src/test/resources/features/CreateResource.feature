Feature: Create Resource
  As a user
  I want to create a resource
  As an admin user
  I can't create a resource

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    And There is a registered administrator with username "admin" and password "password" and email "admin@sample.app"

  Scenario: Create a new resource as a normal user
    Given I login as "user" with password "password"
    And There is a registered subject "subject" for the degree "degree" in the university "university"
    When I, user "user", create a resource with name "name", description "description" and file "file" for the subject "subject"
    Then The response code is 201
    And It has been created with name "name", description "description" and file "file" by the user "user" for the subject "subject"

  Scenario: Create a new resource as an admin user
    Given I login as "admin" with password "password"
    And There is a registered subject "subject" for the degree "degree" in the university "university"
    When I, user "admin", create a resource with name "name", description "description" and file "file" for the subject "subject"
    Then The response code is 403
    And The error message is "Admin users can't create new resources"
    And It has not been created with name "name", description "description" and file "file" by the user "user" for the subject "subject"

  Scenario: Create a new resource as a normal user with empty name
    Given I login as "user" with password "password"
    And There is a registered subject "subject" for the degree "degree" in the university "university"
    When I, user "user", create a resource with name "", description "description" and file "file" for the subject "subject"
    Then The response code is 400
    And The error message is "The name of the resource must not be blank"
    And It has not been created with name "", description "description" and file "file" by the user "user" for the subject "subject"

  Scenario: Create a new resource as a normal user with empty file
    Given I login as "user" with password "password"
    And There is a registered subject "subject" for the degree "degree" in the university "university"
    When I, user "user", create a resource with name "name", description "description" and file "" for the subject "subject"
    Then The response code is 400
    And The error message is "The file of the resource must not be blank"
    And It has not been created with name "name", description "description" and file "" by the user "user" for the subject "subject"

  Scenario: Create a new resource as a normal user with an already existing name
    Given I login as "user" with password "password"
    And There is a registered subject "subject" for the degree "degree" in the university "university"
    And There is a registered resource with name "name" by the user "user", with description "description", file "file" and for the subject "subject"
    When I, user "user", create a resource with name "name", description "description" and file "file" for the subject "subject"
    Then The response code is 409
    And The error message is "Resource name already exists"
    And There is only one resource with name "name"

  Scenario: Create a new resource as a normal user for a nonexistent subject
    Given I login as "user" with password "password"
    And There is a registered subject "subject" for the degree "degree" in the university "university"
    When I, user "user", create a resource with name "name", description "description" and file "file" for the subject "subject2"
    Then The response code is 409
    And The error message is "The subject does not exist"
    And It has not been created with name "name", description "description" and file "file" by the user "user" for the subject "subject2"