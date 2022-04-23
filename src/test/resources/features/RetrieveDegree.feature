Feature: Retrieve Degree
  In order to see what degrees are available
  As a user or an admin
  I want to list all the degrees that match with the search.

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    Given There is a registered admin with username "admin" and password "password" and email "admin@gmail.com"
    Given There is a university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    Given There is a university with name "Universitat de Girona", acronym "UDG", country "Spain", city "Girona"
    And There is a degree created with name "Enginyeria Informatica" and faculty "EPS" and university "Universitat de Lleida"
    And There is a degree created with name "Disseny Grafic" and faculty "EPS" and university "Universitat de Lleida"
    And There is a degree created with name "Medicina" and faculty "Medicina" and university "Universitat de Lleida"
    And There is a degree created with name "Ciencies de l'Activitat Fisica i del Esport" and faculty "Ciencies de l'Activitat Fisica i del Esport" and university "Universitat de Lleida"
    And There is a degree created with name "Enginyeria Mecanica" and faculty "EPS" and university "Universitat de Girona"

    Scenario: List all degrees when not authenticated
      Given I'm not logged in
      When I list all the degrees
      Then The response code is 200


    Scenario: List all degrees as a user
      Given I login as "user" with password "password"
      When I list all the degrees
      Then The response code is 200
      And The number of the returned degrees are 5


    Scenario: List all degrees as an admin
      Given I login as "admin" with password "password"
      When I list all the degrees
      Then The response code is 200
      And The number of the returned degrees are 5


  #<--------------ID-------------->

    Scenario: Obtain an existing degree by id when not authenticated
      Given I'm not logged in
      When I list the degree with id 1
      Then The response code is 200


    Scenario: Obtain a non existing degree by id when not authenticated
      Given I'm not logged in
      When I list the degree with id 999
      Then The response code is 404


    Scenario: Obtain a existing degree by id as a user
      Given I login as "user" with password "password"
      When I list the degree with id 1
      Then The response code is 200
      And It returns the degree with id 1

    Scenario: Obtain a non existing degree by id as a user
      Given I login as "user" with password "password"
      When I list the degree with id 999
      Then The response code is 404


    Scenario: Obtain a existing degree by id as an admin
      Given I login as "admin" with password "password"
      When I list the degree with id 4
      Then The response code is 200
      And It returns the degree with id 4


    Scenario: Obtain a non existing degree by id as an admin
      Given I login as "admin" with password "password"
      When I list the degree with id 999
      Then The response code is 404

  #<--------------NAME-------------->
  Scenario: Obtain an existing degree by name when not authenticated
    Given I'm not logged in
    When I list the degree with name "Enginyeria Informatica"
    Then The response code is 200

  Scenario: Obtain a non existing degree by name when not authenticated
    Given I'm not logged in
    When I list the degree with name "WRONG"
    Then The response code is 200
    And The number of returned degrees are 0

  Scenario: Obtain a existing degree by name as a user
    Given I login as "user" with password "password"
    When I list the degree with name "Enginyeria Informatica"
    Then The response code is 200
    And The number of returned degrees are 1

  Scenario: Obtain a non existing degree by name as a user
    Given I login as "user" with password "password"
    When I list the degree with name "WRONG"
    Then The response code is 200
    And The number of returned degrees are 0


  Scenario: Obtain a existing degree by name as an admin
    Given I login as "admin" with password "password"
    When I list the degree with name "Medicina"
    Then The response code is 200
    And The number of returned degrees are 1


  Scenario: Obtain a non existing degree by name as an admin
    Given I login as "admin" with password "password"
    When I list the degree with name "WRONG"
    Then The response code is 200
    And The number of returned degrees are 0

  #<--------------CONTAININGNAME-------------->
  Scenario: Obtain an existing degree by name when not authenticated
    Given I'm not logged in
    When I list the degree with name "Enginyeria"
    Then The response code is 200

  Scenario: Obtain a non existing degree by name when not authenticated
    Given I'm not logged in
    When I list the degree with name "WRONG"
    Then The response code is 200
    And The number of returned degrees are 0

  Scenario: Obtain a existing degree by name as a user
    Given I login as "user" with password "password"
    When I list the degree with name "Engi"
    Then The response code is 200
    And The number of returned degrees are 2

  Scenario: Obtain a non existing degree by name as a user
    Given I login as "user" with password "password"
    When I list the degree with name "WRONG"
    Then The response code is 200
    And The number of returned degrees are 0

  Scenario: Obtain a existing degree by name as an admin
    Given I login as "admin" with password "password"
    When I list the degree with name "Medi"
    Then The response code is 200
    And The number of returned degrees are 1

  Scenario: Obtain a non existing degree by name as an admin
    Given I login as "admin" with password "password"
    When I list the degree with name "WRONG"
    Then The response code is 200
    And The number of returned degrees are 0

  #<--------------FACULTY-------------->
  Scenario: Obtain an existing degree by faculty when not authenticated
    Given I'm not logged in
    When I list the degree with faculty name "Medicina"
    Then The response code is 200

  Scenario: Obtain a non existing degree by faculty when not authenticated
    Given I'm not logged in
    When I list the degree with faculty name "WRONG"
    Then The response code is 200
    And The number of returned degrees are 0

  Scenario: Obtain a existing degree by faculty as a user
    Given I login as "user" with password "password"
    When I list the degree with faculty name "EPS"
    Then The response code is 200
    And The number of returned degrees are 3

  Scenario: Obtain a non existing degree by faculty as a user
    Given I login as "user" with password "password"
    When I list the degree with faculty name "WRONG"
    Then The response code is 200
    And The number of returned degrees are 0

  Scenario: Obtain a existing degree by faculty as an admin
    Given I login as "admin" with password "password"
    When I list the degree with faculty name "Medicina"
    Then The response code is 200
    And The number of returned degrees are 1

  Scenario: Obtain a non existing degree by faculty as an admin
    Given I login as "admin" with password "password"
    When I list the degree with faculty name "WRONG"
    Then The response code is 200
    And The number of returned degrees are 0

  #<--------------CONTAININGFACULTY-------------->
  Scenario: Obtain an existing degree by faculty when not authenticated
    Given I'm not logged in
    When I list the degree with containing faculty name "Medi"
    Then The response code is 200

  Scenario: Obtain a non existing degree by faculty when not authenticated
    Given I'm not logged in
    When I list the degree with containing faculty name "WRONG"
    Then The response code is 200
    And The number of returned degrees are 0

  Scenario: Obtain a existing degree by faculty as a user
    Given I login as "user" with password "password"
    When I list the degree with containing faculty name "EP"
    Then The response code is 200
    And The number of returned degrees are 3

  Scenario: Obtain a non existing degree by faculty as a user
    Given I login as "user" with password "password"
    When I list the degree with containing faculty name "WRONG"
    Then The response code is 200
    And The number of returned degrees are 0

  Scenario: Obtain a existing degree by faculty as an admin
    Given I login as "admin" with password "password"
    When I list the degree with containing faculty name "Med"
    Then The response code is 200
    And The number of returned degrees are 1

  Scenario: Obtain a non existing degree by faculty as an admin
    Given I login as "admin" with password "password"
    When I list the degree with containing faculty name "WRONG"
    Then The response code is 200
    And The number of returned degrees are 0


  #<--------------UNIVERSITY NAME-------------->
  Scenario: Obtain an existing degree by university name when not authenticated
    Given I'm not logged in
    When I list the degree with university name "Lleida"
    Then The response code is 200

  Scenario: Obtain a non existing degree by university name when not authenticated
    Given I'm not logged in
    When I list the degree with university name "WRONG"
    Then The response code is 200
    And The number of returned degrees are 0

  Scenario: Obtain a existing degree by university name as a user
    Given I login as "user" with password "password"
    When I list the degree with university name "Lleida"
    Then The response code is 200
    And The number of returned degrees are 4

  Scenario: Obtain a non existing degree by university name as a user
    Given I login as "user" with password "password"
    When I list the degree with university name "WRONG"
    Then The response code is 200
    And The number of returned degrees are 0

  Scenario: Obtain a existing degree by university name as an admin
    Given I login as "admin" with password "password"
    When I list the degree with university name "Girona"
    Then The response code is 200
    And The number of returned degrees are 1

  Scenario: Obtain a non existing degree by university name as an admin
    Given I login as "admin" with password "password"
    When I list the degree with university name "WRONG"
    Then The response code is 200
    And The number of returned degrees are 0
