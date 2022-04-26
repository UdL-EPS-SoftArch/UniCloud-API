Feature: Modify User

  In order to modify my user profile
  As a User
  I want to change/edit/modify my profile attributes

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    Given There is a registered user with username "user2" and password "password" and email "user2@gmail.com"

    # User modifies basic attributes
  Scenario: User modifies email
    Given I login as "user" with password "password"
    When I change the email of user "user" to "newuser@gmail.com"
    Then The response code is 200
    And It has been updated the email of user "user" to "newuser@gmail.com"

  Scenario: User modifies password
    Given I login as "user" with password "password"
    When I change the password of user "user" to "differentpassword"
    Then The response code is 204
    And I can login with username "user" and password "differentpassword"

    # Email update scenarios
  Scenario: User modifies email to an invalid one
    Given I login as "user" with password "password"
    When I change the email of user "user" to "newemailaaa"
    Then The response code is 400

  Scenario: User modifies email of a user that does not exist
    Given I login as "user" with password "password"
    When I change the email of user "unknown" to "new@gmail.com"
    Then The response code is 404

    # Password update scenarios
  Scenario: User modifies password without being logged in
    Given I'm not logged in
    When I change the password of user "user" to "password"
    Then The response code is 401
