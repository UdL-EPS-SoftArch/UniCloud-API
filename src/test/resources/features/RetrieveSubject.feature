Feature: Retrieve Subject
  In order to see which subjects are available
  As a user or an admin
  I want to list all the subjects that match with the search.


  Background:
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    Given There is a registered user with username "admin" and password "password" and email "admin@gmail.com"
    And There is a Subject with name "Algebra",course 1 and optional "True"
    And There is a Subject with name "Projecte web",course 3 and optional "False"
    #And There is a subject with name "Xarxes",course 2 and optional "True"
    #And There is a subject with name "Computació distribuïda i aplicacions",course 3 and optional "True"
    #And There is a subject with name "Arquitectura empresarial",course 1 and optional "False"

  Scenario: List all subjects when not authenticated
    Given I'm not logged in
    When I list all the subjects
    Then The response code is 200

  Scenario: List all subjects as a user
    Given I login as "user" with password "password"
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

  Scenario: Obtain a existing subject by id as a user
    Given I login as "user" with password "password"
    When I list the subject with id "1"
    Then The response code is 200
    And It returns the subject with name "Algebra"

  Scenario: List a non existing subject by id as a user
    Given I login as "admin" with password "password"
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
    # ------- currently failing the json path

  Scenario: Obtain an existing subject by name when not authenticated
    Given I'm not logged in
    When I list the subject with name "Fisica de les patates"
    And The response code is 200

  Scenario: Obtain a non existing subject by name when not authenticated
    Given I'm not logged in
    When I list the subject with name "Aerodinamica de les gallines"
    And The response code is 200

  Scenario: Obtain a existing subject by name as a user
    Given I login as "user" with password "password"
    When I list the subject with name "Algebra"
    And The response code is 200
    And The number of returned subjects is 1

    #Si esta mal canviar el resultat a code 200 i nª returnes subjects = 0
  Scenario: Obtain a non existing subject by name as a user
    Given I login as "user" with password "password"
    When I list the subject with name "Im batman"
    And The response code is 404


  Scenario: Obtain a existing subject by name as an admin
    Given I login as "admin" with password "password"
    When I list the subject with name "Arquitectura empresarial"
    And The response code is 200
    And The number of returned subjects is 1


  Scenario: Obtain a non existing subject by name as an admin
    Given I login as "admin" with password "password"
    When I list the subject with name "cacauet"
    And The response code is 404

