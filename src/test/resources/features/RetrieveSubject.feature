Feature: Retrieve Subject
  In order to see which subjects are available
  As a user or an admin
  I want to list all the subjects that match with the search.


  Background:
    Given There is a registered student with username "student" and password "password" and email "user@gmail.com"
    Given There is a registered admin with username "admin" and password "password" and email "admin@gmail.com"
    And There is a Subject with name "Algebra",course 1 and optional "True"
    And There is a Subject with name "Projecte web",course 3 and optional "False"
    #And There is a subject with name "Xarxes",course 2 and optional "True"
    #And There is a subject with name "Computació distribuïda i aplicacions",course 3 and optional "True"
    #And There is a subject with name "Arquitectura empresarial",course 1 and optional "False"

  Scenario: List all subjects when not authenticated
    Given I'm not logged in
    When I list all the subjects
    Then The response code is 200

  Scenario: List all subjects as a student
    Given I login as "student" with password "password"
    When I list all the subjects
    Then The response code is 200
    And The number of returned subjects is 2
    
  Scenario:
    Given I login as "admin" with password "password"
    When I list all the subjects
    Then The response code is 200
    And The number of returned subjects is 2

        # ------- tests with id -----------
  Scenario: Obtain an existing subject by id when not authenticated
    Given I'm not logged in
    When I list the subject with id "1"
    And The response code is 200

  Scenario: Obtain a non existing subject by id when not authenticated
    Given I'm not logged in
    When I list the subject with id "999"
    And The response code is 404

  Scenario: Obtain a existing subject by id as a student
    Given I login as "student" with password "password"
    When I list the subject with id "1"
    Then The response code is 200
    And It returns the subject with name "Algebra"

  Scenario: List a non existing subject by id as a student
    Given I login as "student" with password "password"
    When I list the subject with id "999"
    Then The response code is 404

  Scenario: Obtain a non existing subject by id as an admin
    Given I login as "admin" with password "password"
    When I list the subject with id "999"
    And The response code is 404

  Scenario: Obtain a existing subject by id as an admin
    Given I login as "admin" with password "password"
    When I list the subject with id "2"
    And The response code is 200
    And It returns the subject with name "Projecte web"

    # ------- tests with name -----------

  Scenario: Obtain an existing subject by name when not authenticated
    Given I'm not logged in
    When I list the subject with name "Algebra"
    And The response code is 200

  Scenario: Obtain a non existing subject by name when not authenticated
    Given I'm not logged in
    When I list the subject with name "Aerodinamica de les gallines"
    Then The response code is 200
    And The number of returned subjects is 0

  Scenario: Obtain a existing subject by name as a student
    Given I login as "student" with password "password"
    When I list the subject with name "Algebra"
    Then The response code is 200
    And The number of returned subjects is 1
    
  Scenario: Obtain a non existing subject by name as a student
    Given I login as "student" with password "password"
    When I list the subject with name "Im batman"
    Then The response code is 200
    And The number of returned subjects is 0
    
  Scenario: Obtain a existing subject by name as an admin
    Given I login as "admin" with password "password"
    When I list the subject with name "Projecte web"
    Then The response code is 200
    And The number of returned subjects is 1
    
  Scenario: Obtain a non existing subject by name as an admin
    Given I login as "admin" with password "password"
    When I list the subject with name "cacauet"
    Then The response code is 200
    And The number of returned subjects is 0
    
    # ------- tests with course -----------

  Scenario: Obtain an existing subject by course when not authenticated
    Given I'm not logged in
    When I list the subject with course 1 
    And The response code is 200

  Scenario: Obtain a non existing subject by course when not authenticated
    Given I'm not logged in
    When I list the subject with course 8
    Then The response code is 200
    And The number of returned subjects is 0

  Scenario: Obtain a existing subject by course as a student
    Given I login as "student" with password "password"
    When I list the subject with course 3
    Then The response code is 200
    And The number of returned subjects is 1

  Scenario: Obtain a non existing subject by course as a student
    Given I login as "student" with password "password"
    When I list the subject with course 45 
    Then The response code is 200
    And The number of returned subjects is 0

  Scenario: Obtain a existing subject by course as an admin
    Given I login as "admin" with password "password"
    When I list the subject with course 1
    Then The response code is 200
    And The number of returned subjects is 1

  Scenario: Obtain a non existing subject by course as an admin
    Given I login as "admin" with password "password"
    When I list the subject with course 34
    Then The response code is 200
    And The number of returned subjects is 0
    
  
  

