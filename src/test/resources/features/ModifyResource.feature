Feature: Modify Resource
  As a user
  I can only modify my own resources
  As an admin
  I can't modify any resource

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given There is a registered administrator with username "admin" and password "password" and email "admin@sample.app"
    Given There is a registered subject "subject"
    Given There is a registered resource with name "name" by the user "user", with description "description", file "file" and for the subject "subject"

  Scenario: Modify a resource as an admin user
    Given I login as "admin" with password "password"
    When I, user "admin", modify the resource with name "name"
    Then The response code is 403
    And The error message is "Admin users can't modify resources"
    And There is a registered resource with name "name" by the user "user", with description "description", file "file" and for the subject "subject"

  Scenario: Modify an own resource as normal user changing its name
    Given I login as "user" with password "password"
    When I, user "user", modify the resource with name "name" and the new name "name2"
    Then The response code is 204
    And A resource named "name" does not exist, but a resource named "name2" does

  Scenario: Modify an own resource as normal user changing its description
    Given I login as "user" with password "password"
    When I, user "user", modify the resource with name "name" and the new description "description2"
    Then The response code is 204
    And The resource named "name" has a description "description2"


