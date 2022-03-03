Feature: DELETE a Rating

  Scenario: Student authenticated deletes his/her own rating without having rated anything before
    Given I login as "demo" with password "password"
    When I delete a Rating with id 26
    Then The response code is 404
    And The rating with id 26 does not exist

  Scenario: Delete rating as student
    Given I login as "demo" with password "password"
    Given I register rating with rating 8 and comment "Good job"
    When I delete the last created rating
    Then The response code is 204
    And The rating with id 28 was deleted


  Scenario: Delete rating when not authenticated
    Given I'm not logged in
    When I delete a Rating with id 26
    Then The response code is 401
    And The rating with id 26 was not deleted
