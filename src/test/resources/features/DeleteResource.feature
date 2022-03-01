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

  Scenario: Delete an own resource as a normal user
    Given I login as "user" with password "password"
    When I, user "user", delete a resource with name "name"
    Then The response code is 200
    And There is not a registered resource with name "name"

  Scenario: Delete an external resource as a normal user
    Given I login as "user" with password "password"
    And There is a registered user with username "user2" and password "password" and email "user@sample.app"
    And There is a registered resource with name "name" by the user "user2"
    When I, user "user", delete a resource with name "name"
    Then The response code is 403
    And The error message is "Users can't delete other user's resources"
    And There is a registered resource with name "name" by the user "user2"