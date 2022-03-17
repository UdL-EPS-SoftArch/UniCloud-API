Feature: DELETE a Rating

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@local.com"
    Given There is a registered admin with username "admin" and password "password" and email "admin@local.com"
    And There is a registered resource with name "Exàmen Parcial 1" by the user "student", with description "Primer parcial", type "TEST" and file "Activitat 1 ..." for the subject "Estructura de dades"

  Scenario: Student authenticated deletes a rating
    Given I login as "student" with password "password"
    When I delete a Rating with id 26
    Then The response code is 404
    And The rating with id 26 does not exist

  Scenario: Delete rating as student
    Given I login as "student" with password "password"
    And I register rating with rating 8 and comment "Good job"
    When I delete the last created rating
    Then The response code is 204
    And The rating was deleted

    # Modificar els permissos del WebSecurityConfig per al admin
  Scenario: Delete rating as admin
    Given I login as "student" with password "password"
    And I register rating with rating 8 and comment "Good job"
    And I login as "admin" with password "password"
    When I delete the last created rating
    Then The response code is 204


  Scenario: Delete rating when not authenticated
    Given I'm not logged in
    When I delete a Rating with id 26
    Then The response code is 401
    And The rating with id 26 was not deleted
