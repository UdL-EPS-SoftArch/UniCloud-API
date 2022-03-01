Feature: Modify Resource
  As a user
  I can only modify my own resources
  As an admin
  I can't modify any resource

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given There is a registered administrator with username "admin" and password "password" and email "admin@sample.app"
    Given There is a registered resource with name "name" by the user "user"

  Scenario: Modify a resource as an admin user
    Given I login as "admin" with password "password"
    When I, user "admin", modify the resource with name "name"
    Then The response code is 403
    And The error message is "Admin users can't modify resources"


