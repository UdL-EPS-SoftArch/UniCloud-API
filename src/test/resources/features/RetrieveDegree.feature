Feature: Retrieve Degree
  In order to see what degrees are available
  As a user or an admin
  I want to list all the degrees that match with the search.

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    Given There is a registered user with username "admin" and password "password" and email "admin@gmail.com"
    And There is a degree created with name "Enginyeria Informatica" and faculty "EPS"
    And There is a degree created with name "Disseny Grafic" and faculty "EPS"
    And There is a degree created with name "Medicina" and faculty "Medicina"
    And There is a degree created with name "Ciencies de l'Activitat Fisica i del Esport" and faculty "Ciencies de l'Activitat Fisica i del Esport"

    Scenario: List all degrees when not authenticated
      Given I'm not logged in


    Scenario: List all degrees as a user
      Given I login as "user" with password "password"


    Scenario: List all degrees as an admin
      Given I login as "admin" with password "password"


  #<--------------ID-------------->

    Scenario: Obtain an existing degree by id when not authenticated
      Given I'm not logged in


    Scenario: Obtain a non existing degree by id when not authenticated
      Given I'm not logged in


    Scenario: Obtain a existing degree by id as a student
      Given I login as "user" with password "password"


    Scenario: Obtain a non existing degree by id as a student
      Given I login as "user" with password "password"


    Scenario: Obtain a existing degree by id as an admin
      Given I login as "admin" with password "password"


    Scenario: Obtain a non existing degree by id as an admin
      Given I login as "admin" with password "password"
