Feature: Delete degree
  As a admin
  I want to delete a degree

  Background:
    Given I login as "admin" with password "password"
    And There is a degree created with id "1"


  Scenario: Admin deletes a degree
    Given I login as "admin" with password "password"
    When I delete a degree with id "1"
    Then The response code is 204

  Scenario: Delete a degree that does not exist
    And I login as "admin" with password "password"
    When I delete a degree with id "999"
    Then The response code is 404

  Scenario: Delete a degree when not authenticated
    Given I'm not logged in
    When I delete a degree with id "1"
    Then The response code is 401