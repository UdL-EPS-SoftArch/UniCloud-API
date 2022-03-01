Feature: Delete Resource
  As a user
  I can only delete my resources
  As an admin
  I can delete any resource
  
  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given There is a registered administrator with username "admin" and password "password" and email "admin@sample.app"
    Given There is a registered resource with name "name" by the user "user"
  
  Scenario: Delete a resource as an admin 
    Given I login as "admin" with password "password"
    When I, user "admin", delete a resource with name "name"
    Then The response code is 200
    And There is not a registered resource with name "name"

  Scenario: Delete an own resource as a user
    Given I login as "user" with password "password"
    When I, user "user", delete a resource with name "name"
    Then The response code is 200
    And There is not a registered resource with name "name"