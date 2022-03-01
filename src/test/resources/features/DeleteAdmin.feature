Feature: Delete Administrator

  In order to unsubscribe from the app
  As a administrator
  I want to delete my account

  Scenario: Administrator deletes its own account
    Given There is a registered user with username "<" and password "password" and email "admin@gmail.com"
    And I login as "admin" with password "password"
    When I delete the administrator with username "admin"
    Then The response code is 204
    And I login as "demo" with password "password"
    And It does not exist a administrator with username "admin"


  Scenario: Administrator deletes a not owned account
    Given There is a registered user with username "admin" and password "password" and email "admin@gmail.com"
    And There is a registered user with username "admin2" and password "password" and email "admin2@gmail.com"
    And I login as "admin" with password "password"
    When I delete the administrator with username "admin2"
    Then The response code is 403
    And It has not been deleted a admin with username "admin2"

  Scenario: Delete a administrator that does not exist
    Given There is a registered user with username "admin" and password "password" and email "admin@gmail.com"
    And I login as "admin" with password "password"
    When I delete the administrator with username "unknown"
    Then The response code is 404

  Scenario: Delete a administrator when not authenticated
    Given There is a registered user with username "admin" and password "password" and email "admin@gmail.com"
    When I delete the administrator with username "admin"
    Then The response code is 401
    And I login as "admin" with password "password"
    And It has not been deleted a admin with username "admin"

  Scenario: Student deletes a administrator
    Given There is a registered user with username "admin" and password "password" and email "admin@gmail.com"
    And I login as "student" with password "password"
    When I delete the administrator with username "admin"
    Then The response code is 403
    And It has not been deleted a admin with username "admin"
