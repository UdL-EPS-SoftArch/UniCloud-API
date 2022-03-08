Feature: Retrieve University
  In order to see what universities are available in the app
  As a user or an admin
  I want to list all the universities that match with a search.

  Background:
    Given There is a registered user with username "student" and password "password" and email "student@local.com"
    Given There is a registered user with username "admin" and password "password" and email "admin@local.com"
    And There is a university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    And There is a university with name "Universitat de Girona", acronym "UDG", country "Spain", city "Girona"
    And There is a university with name "Universitat de Barcelona", acronym "UB", country "Spain", city "Barcelona"
    And There is a university with name "Universitat Politècnica de Catalunya", acronym "UPC", country "Spain", city "Barcelona"


  Scenario: List all universities when not authenticated
    Given I'm not logged in
    When I list all the existing universities in the app
    Then The response code is 200

  Scenario: List all universities as a student
    Given I login as "student" with password "password"
    When I list all the existing universities in the app
    Then The response code is 200
    And The number of returned universities are 4

  Scenario: List all universities as an admin
    Given I login as "admin" with password "password"
    When I list all the existing universities in the app
    Then The response code is 200
    And The number of returned universities are 4

    #--------------id--------------------

  Scenario: List an existing university by id when not authenticated
    Given I'm not logged in
    When I list the university with id "1"
    Then The response code is 200

  Scenario: List a non existing university by id when not authenticated
    Given I'm not logged in
    When I list the university with id "99"
    Then The response code is 404

  Scenario: List a existing university by id as a student
    Given I login as "student" with password "password"
    When I list the university with id "1"
    Then The response code is 200
    And It returns the university with name "Universitat de Lleida"

  Scenario: List a non existing university by id as a student
    Given I login as "student" with password "password"
    When I list the university with id "99"
    Then The response code is 404

  Scenario: List a existing university by id as a admin
    Given I login as "admin" with password "password"
    When I list the university with id "2"
    Then The response code is 200
    And It returns the university with name "Universitat de Girona"

  Scenario: List a non existing university by id as an admin
    Given I login as "admin" with password "password"
    When I list the university with id "99"
    Then The response code is 404

    #-----------------------NAME-------------------------

  Scenario: List an existing university by name when not authenticated
    Given I'm not logged in
    When I list the university with name "Universitat de Lleida"
    Then The response code is 200

  Scenario: List a non existing university by name when not authenticated
    Given I'm not logged in
    When I list the university with name "Universitat de Pamplona"
    Then The response code is 200

  Scenario: List a existing university by name as a student
    Given I login as "student" with password "password"
    When I list the university with name "Universitat de Lleida"
    Then The response code is 200
    And The number of returned universities are 1

  Scenario: List a non existing university by name as a student
    Given I login as "student" with password "password"
    When I list the university with name "Universitat de Pamplona"
    Then The response code is 200
    And The number of returned universities are 0

  Scenario: List a existing university by name as a admin
    Given I login as "admin" with password "password"
    When I list the university with name "Universitat de Lleida"
    Then The response code is 200
    And The number of returned universities are 1

  Scenario: List a non existing university by name as an admin
    Given I login as "admin" with password "password"
    When I list the university with name "Universitat de Pamplona"
    Then The response code is 200
    And The number of returned universities are 0

  #------------------------NAME CONTAINGING----------

  Scenario: List an existing university containing name when not authenticated
    Given I'm not logged in
    When I list the university containing name "Universitat"
    Then The response code is 200

  Scenario: List a non existing university containing name when not authenticated
    Given I'm not logged in
    When I list the university containing name "Escola"
    Then The response code is 200
    And The number of returned universities are 0

  Scenario: List a existing university containing name as a student
    Given I login as "student" with password "password"
    When I list the university containing name "Universitat"
    Then The response code is 200
    And The number of returned universities are 4

  Scenario: List a non existing university containing name as a student
    Given I login as "student" with password "password"
    When I list the university containing name "Escola"
    Then The response code is 200
    And The number of returned universities are 0

  Scenario: List a existing university containing name as a admin
    Given I login as "admin" with password "password"
    When I list the university containing name "de"
    Then The response code is 200
    And The number of returned universities are 4

  Scenario: List a non existing university containing name as an admin
    Given I login as "admin" with password "password"
    When I list the university containing name "Escola"
    Then The response code is 200
    And The number of returned universities are 0

  #-------------------------ACRONYM------------------------------

  Scenario: List an existing university by acronym when not authenticated
    Given I'm not logged in
    When I list the university with acronym "UDL"
    Then The response code is 200

  Scenario: List a non existing university by acronym when not authenticated
    Given I'm not logged in
    When I list the university with acronym "UHU"
    Then The response code is 200

  Scenario: List a existing university by acronym as a student
    Given I login as "student" with password "password"
    When I list the university with acronym "UDL"
    Then The response code is 200
    And The number of returned universities are 1

  Scenario: List a non existing university by acronym as a student
    Given I login as "student" with password "password"
    When I list the university with acronym "UMA"
    Then The response code is 200
    And The number of returned universities are 0

  Scenario: List a existing university by acronym as a admin
    Given I login as "admin" with password "password"
    When I list the university with acronym "UPC"
    Then The response code is 200
    And The number of returned universities are 1

  Scenario: List a non existing university by name as an admin
    Given I login as "admin" with password "password"
    When I list the university with acronym "UAB"
    Then The response code is 200
    And The number of returned universities are 0

  #---------------------------CITY----------------------------

  Scenario: List an existing university by city when not authenticated
    Given I'm not logged in
    When I list the university with city "Lleida"
    Then The response code is 200

  Scenario: List a non existing university by city when not authenticated
    Given I'm not logged in
    When I list the university with city "Huelva"
    Then The response code is 200

  Scenario: List a existing university by city as a student
    Given I login as "student" with password "password"
    When I list the university with city "Barcelona"
    Then The response code is 200
    And The number of returned universities are 2

  Scenario: List a non existing university by city as a student
    Given I login as "student" with password "password"
    When I list the university with city "Sevilla"
    Then The response code is 200
    And The number of returned universities are 0

  Scenario: List a existing university by city as a admin
    Given I login as "admin" with password "password"
    When I list the university with city "Lleida"
    Then The response code is 200
    And The number of returned universities are 1

  Scenario: List a non existing university by city as an admin
    Given I login as "admin" with password "password"
    When I list the university with city "Pamplona"
    Then The response code is 200
    And The number of returned universities are 0

  #---------------------------COUNTRY----------------------------

  Scenario: List an existing university by country when not authenticated
    Given I'm not logged in
    When I list the university with country "Spain"
    Then The response code is 200

  Scenario: List a non existing university by country when not authenticated
    Given I'm not logged in
    When I list the university with country "United Kingdom"
    Then The response code is 200

  Scenario: List a existing university by country as a student
    Given I login as "student" with password "password"
    When I list the university with country "Spain"
    Then The response code is 200
    And The number of returned universities are 4

  Scenario: List a non existing university by country as a student
    Given I login as "student" with password "password"
    When I list the university with country "Holland"
    Then The response code is 200
    And The number of returned universities are 0

  Scenario: List a existing university by country as a admin
    Given I login as "admin" with password "password"
    When I list the university with country "Spain"
    Then The response code is 200
    And The number of returned universities are 4

  Scenario: List a non existing university by country as an admin
    Given I login as "admin" with password "password"
    When I list the university with country "United States of America"
    Then The response code is 200
    And The number of returned universities are 0