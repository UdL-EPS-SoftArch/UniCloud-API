Feature: Create a Degree
  In order to control who can create a degree
  Administrator is the only who can create a degree
  
  Background: 
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    Given There is a registered user with username "admin" and password "password" and email "admin@gmail.com"
    Given There is a university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    And There is a degree created with name "Disseny Grafic" and faculty "EPS" and university "Universitat de Lleida"


  Scenario: Allowed to create a degree as an administrator
    Given I login as "admin" with password "password"
    When I create a degree with name "Medicina" and faculty "Facultat de medicina" and university "Universitat de Lleida"
    Then The response code is 201
    And The degree with name "Medicina" and university "Universitat de Lleida" exists

  #ToDo Intent de crear un degree com a user


  Scenario: Create new degree when not authenticated
    Given I'm not logged in
    When I create a degree with name "Grau Enginyeria Informatica" and faculty "EPS" and university "Universitat de Lleida"
    Then The response code is 401
    And The degree with name "Grau Enginyeria Informatica" and university "Universitat de Lleida" doesn't exist


  Scenario: Create a degree that already exists
    Given I login as "admin" with password "password"
    When I create a degree with name "Disseny Grafic" and faculty "EPS" and university "Universitat de Lleida"
