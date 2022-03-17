Feature: MODIFY a Rating

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@local.com"
    Given There is a registered admin with username "admin" and password "password" and email "admin@local.com"
    And There is a registered resource with name "Exàmen Parcial 1" by the user "student", with description "Primer parcial", type "TEST" and file "Activitat 1 ..." for the subject "Estructura de dades"

  Scenario: Student authenticated modify his/her own rating without having rated anything before
    Given I login as "student" with password "password"
    When I modify a Rating with id 26
    Then The response code is 404
    And The rating with id 26 does not exist

  Scenario: Modify rating as admin
    Given I login as "student" with password "password"
    And I add a new rating with rating 8 and comment "Good job"
    And I login as "admin" with password "password"
    When I modify the last rating created changing the comment to "Sorry, was a bad job"
    Then The response code is 403

  Scenario: Modify rating comment as student
    Given I login as "student" with password "password"
    And I add a new rating with rating 5 and comment "Just approved"
    And A new rating has been created as "student"
    When I modify the last rating created changing the comment to "Just approved but you did an excelent work"
    Then The response code is 200

  Scenario: Modify rating number as student
    Given I login as "student" with password "password"
    And I add a new rating with rating 4 and comment "You lose the exam"
    And A new rating has been created as "student"
    When I modify the last rating created changing the rating to 5
    Then The response code is 200

  Scenario: Modify rating when not authenticated
    Given I'm not logged in
    When I modify a Rating with id 26
    Then The response code is 401

  Scenario: Student authenticated modify his/her own rating
    Given I login as "student" with password "password"
    When I modify a Rating with id 26
    Then The response code is 404

    ###nou
  #Scenario: Student authenticated modify a rating from another author
   # Given I login as "demo" with password "password"
    #When I modify a Rating with id 26 created by author "paco"
    #Then The response code is 403