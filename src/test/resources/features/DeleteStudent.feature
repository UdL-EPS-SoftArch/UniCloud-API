Feature: Delete Student
  In order to unsubscribe from the app
  As a student
  I want to delete my account

  Scenario: Student deletes its own account
    Given There is a registered user with username "student" and password "password" and email "student@gmail.com"
    And I login as "student" with password "password"
    When I delete the student with the username "student"
    Then The response code is 204
    And It does not exist a student with username "student"
    
    
  Scenario: Student deletes a not owned account
    Given There is a registered user with username "student" and password "password" and email "student@gmail.com"
    And There is a registered user with username "student2" and password "password" and email "student2@gmail.com"
    And I login as "student" with password "password"
    When I delete the student with the username "student2"
    Then The response code is 403
    And It has not been deleted a student with username "student2"
    
  Scenario: Delete a student when not authenticated
    Given There is a registered user with username "student" and password "password" and email "student@gmail.com"
    When I delete the student with the username "student"
    Then The response code is 401
    And It has not been deleted a student with username "student"

  Scenario: Delete a student that does not exist
    Given There is a registered user with username "student" and password "password" and email "student@gmail.com"
    And I login as "student" with password "password"
    When I delete the student with the username "unknown"
    Then The response code is 404