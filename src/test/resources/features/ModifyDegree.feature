Feature: Modify a Degree
  In order to control who can modify a degree
  Administrator is the only who can modify a degree

  Background:
    Given There is a registered user with username "admin" and password "password" and email "admin@gmail.com"
    And There is a degree created with name "Grau en Enginyeria Informatica" and faculty "EPS"

  Scenario: Modify the faculty of a Degree
    Given I login as "admin" with password "password"
    When I modify a degree with name "Grau en Enginyeria Informatica" changing field faculty with "Enginyeria"
    Then The response code is 200
    And The Degree with name "Grau en Enginyeria Informatica" has the faculty "Enginyeria"

  Scenario: Modify the name of a Degree
    Given I login as "admin" with password "password"
    When I modify a degree with faculty "EPS" changing field name with "Enginyeria Informatica"
    Then The response code is 200
    And The Degree with faculty "EPS" has the name "Enginyeria Informatica"

  Scenario: Modify a Degree when not authenticated
    Given I'm not logged in
    When I modify a degree with name "Grau en Enginyeria Informatica" changing field faculty with "Enginyeria"
    Then The response code is 401
    And The Degree with name "Grau en Enginyeria Informatica" has the faculty "EPS"


  Scenario: Modify a Degree that does not exist
    Given I login as "admin" with password "password"
    When I modify a degree with name "Disseny" changing field faculty with "Enginyeria"
    Then The response code is 404
