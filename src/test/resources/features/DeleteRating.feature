Feature: DELETE a Rating

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@local.com"
    Given There is a registered student with username "student2" and password "password" and email "student2@local.com"
    Given There is a registered admin with username "admin" and password "password" and email "admin@local.com"
    And There is a registered resource with name "name" by the user "student", with description "description", file "example.pdf", and resource type "NOTE" for the subject id 1

  Scenario: Student authenticated deletes an uncreated rating
    Given I login as "student" with password "password"
    And I register rating with rating 8 and comment "Good job" and resource with name "name"
    Given I login as "student2" with password "password2"
    When I delete the last created rating
    Then The response code is 401
    #And This user doesn't have any rating in the resource with name "name"

  Scenario: Delete rating as student
    Given I login as "student" with password "password"
    And I register rating with rating 8 and comment "Good job" and resource with name "name"
    When I delete the last created rating
    Then The response code is 204
    And The rating was deleted

    # Modificar els permissos del WebSecurityConfig per al admin
  Scenario: Delete rating as admin
    Given I login as "student" with password "password"
    And I register rating with rating 8 and comment "Good job" and resource with name "name"
    And I login as "admin" with password "password"
    When I delete the last created rating
    Then The response code is 204


  Scenario: Delete rating when not authenticated
    Given I'm not logged in
    When I delete a Rating with id 26
    Then The response code is 401
    And The rating with id 26 was not deleted
