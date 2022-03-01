Feature: Create a Degree
  In order to control who can create a degree
  Administrator is the only who can create a degree
  
  Background: 
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    Given There is a registered user with username "admin" and password "password" and email "admin@gmail.com"


  Scenario: Allowed to create a degree as an administrator
    Given I login as "admin" with password "password"
    When I create a degree with name "Medicina" and faculty "Facultat de medicina"
    Then The response code is 201

  #ToDo Intent de crear un degree com a user


  Scenario: Create new degree when not authenticated
    Given I'm not logged in
    When I create a degree with name "Grau Enginyeria Informatica" and faculty "EPS"
    Then The response code is 401
    And A new university has not been created