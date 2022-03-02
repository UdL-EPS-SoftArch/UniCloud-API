Feature: DELETE a Rating

  Scenario: Student authenticated deletes his/her own rating without having rated anything before
    Given I login as "demo" with password "password"
    When I delete a Rating with id 26
    Then The response code is 404
    And The rating with id 26 does not exist
