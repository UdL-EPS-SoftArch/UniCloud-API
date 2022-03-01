Feature: Modify a Degree
  In order to control who can modify a degree
  Administrator is the only who can modify a degree

  Background:
    Given There is a registered user with username "admin" and password "password" and email "admin@gmail.com"
    And There is a degree created with name "Grau en Enginyeria Informatica" and faculty "EPS"

  Scenario: Modify a Degree
    Given I login as "admin" with password "password"
    When I modify a degree with id "1" I set a name "Grau en Enginyeria Informatica" and faculty "Enginyeria"
    Then The response code is 200


  Scenario: Modify a Degree when not authenticated
    Given I'm not logged in
    When I modify a degree with id "1" I set a name "Grau en Enginyeria Informatica" and faculty "Enginyeria"
    Then The response code is 401

  #ToDo Modify a Degree that does not exist