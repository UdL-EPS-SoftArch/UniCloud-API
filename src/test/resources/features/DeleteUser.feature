Feature: Delete User

  In order to unsubscribe from the app
  As a user
  I want to delete my account

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    Given There is a registered user with username "user2" and password "password" and email "user2@gmail.com"

  Scenario: User deletes its own account
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    And I login as "user" with password "password"
    When I delete the user with username "user"
    Then The response code is 204
    And I login as "user" with password "password"
    And It does not exist a user with username "user"


  Scenario: User deletes a not owned account
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    And There is a registered user with username "user2" and password "password" and email "user2@gmail.com"
    And I login as "user" with password "password"
    When I delete the user with username "user2"
    Then The response code is 403
    And It has not been deleted a user with username "user2"

  Scenario: Delete a user that does not exist
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    And I login as "user" with password "password"
    When I delete the user with username "unknown"
    Then The response code is 404


  Scenario: Delete a user when not authenticated
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    When I delete the user with username "user"
    Then The response code is 401
    And I login as "user" with password "password"
    And It has not been deleted a user with username "user"