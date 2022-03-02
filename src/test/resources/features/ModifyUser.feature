Feature: Modify User

  In order to update my user profile
  As a User
  I want to change/edit my profile attributes

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    Given There is a registered user with username "user2" and password "password" and email "user2@gmail.com"

  Scenario: User updates email
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    And I login as "user" with password "password"
    When I change the email of user "user" to "newuser@gmail.com"
    Then The response code is 200
    And It has been updated the email of user "user" to "newuser@gmail.com"

  Scenario: User updates email to an invalid one
    Given There is a registered user with username "user" and password "password" and email "userrny@gmail.com"
    And I login as "user" with password "password"
    When I change the email of user "user" to "newemailaaa"
    Then The response code is 400
    And The error message is "must be a well-formed email address"
    And Email of user "user" remains "user@gmail.com"

  Scenario: User updates email to a blank one
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    And I login as "user" with password "password"
    When I change the email of user "user" to ""
    Then The response code is 400
    And The error message is "must not be blank"
    And Email of user "user" remains "user@gmail.com"

  Scenario: Update the email of a user that does not exist
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    And I login as "user" with password "password"
    When I change the email of user "unknown" to "new@gmail.com"
    Then The response code is 404

  Scenario: User updates email of a not owned account
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    And There is a registered user with username "user2" and password "password" and email "user2@gmail.com"
    And I login as "user" with password "password"
    When I change the email of user "user2" to "new@gmail.com"
    Then The response code is 403
    And Email of user "user2" remains "user2@gmail.com"
