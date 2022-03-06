Feature: MODIFY a Rating

  Scenario: Student authenticated modify his/her own rating without having rated anything before
    Given I login as "demo" with password "password"
    When I modify a Rating with id 26
    Then The response code is 404
    And The rating with id 26 does not exist

  Scenario: Modify rating as admin
    Given I login as "admin" with password "password"
    Given I add a new rating with rating 8 and comment "Good job"
    When I modify the last rating created changing the comment to "Sorry, was a bad job"
    Then The response code is 404

  Scenario: Modify rating as student
    Given I login as "demo" with password "password"
    Given I add a new rating with rating 5 and comment "Just approved"
    When I modify the last rating created changing the comment to "Just approved but you did an excelent work"
    Then The response code is 200




  Scenario: Modify rating when not authenticated
    Given I'm not logged in
    When I modify a Rating with id 26
    Then The response code is 401
    And The rating with id 26 was not modified

  Scenario: Student authenticated modify his/her own rating
    Given I login as "demo" with password "password"
    When I modify a Rating with id 26
    Then The response code is 404
    And The rating with id 26 was modified