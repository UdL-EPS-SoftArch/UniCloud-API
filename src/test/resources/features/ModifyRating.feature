Feature: MODIFY a Rating

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@local.com"
    Given There is a registered admin with username "admin" and password "password" and email "admin@local.com"

  Scenario: Student authenticated modify his/her own rating without having rated anything before
    Given I login as "student" with password "password"
    When I modify a Rating with id 26
    Then The response code is 404
    And The rating with id 26 does not exist

    # Modificar els permissos del WebSecurityConfig per al admin
  Scenario: Modify rating as admin
    Given I login as "student" with password "password"
    Given I add a new rating with rating 8 and comment "Good job"
    Given I login as "admin" with password "password"
    When I modify the last rating created changing the comment to "Sorry, was a bad job"
    Then The response code is 403

  Scenario: Modify rating as student
    Given I login as "student" with password "password"
    Given I add a new rating with rating 5 and comment "Just approved"
    When I modify the last rating created changing the comment to "Just approved but you did an excelent work"
    Then The response code is 200

  Scenario: Modify rating when not authenticated
    Given I'm not logged in
    When I modify a Rating with id 26
    Then The response code is 401

  Scenario: Student authenticated modify his/her own rating
    Given I login as "demo" with password "password"
    When I modify a Rating with id 26
    Then The response code is 404