Feature: Delete User

  In order to unsubscribe from the app
  As a user
  I want to delete my account

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    Given There is a registered user with username "user2" and password "password2" and email "user2@gmail.com"

  Scenario: User deletes its own account
    And I login as "user" with password "password"
    When I delete the user with username "user"
    Then The response code is 204
    And I cannot login with username "user" and password "<password>"

  Scenario: User deletes a not owned account
    And I login as "user2" with password "password2"
    When I delete the user with username "user"
    Then The response code is 403

  Scenario: Delete a user that does not exist
    And I login as "user" with password "password"
    When I delete the user with username "unknown"
    Then The response code is 404


  Scenario: Delete a user when not authenticated
    When I delete the user with username "user"
    Then The response code is 401
    And I can login with username "user" and password "password"
