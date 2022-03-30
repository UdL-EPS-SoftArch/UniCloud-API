Feature: Modify a Degree
  In order to control who can modify a degree
  Administrator is the only who can modify a degree

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    Given There is a registered admin with username "admin" and password "password" and email "admin@gmail.com"
    Given There is a university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    And There is a degree created with name "Enginyeria Informatica" and faculty "EPS" and university "Universitat de Lleida"

  Scenario: Admin modify the faculty of a Degree
    Given I login as "admin" with password "password"
    When I modify a degree with name "Enginyeria Informatica" and university "Universitat de Lleida" changing field faculty with "Enginyeria"
    Then The response code is 200
    And The Degree with name "Enginyeria Informatica" has the faculty "Enginyeria"

  Scenario: Admin modify the name of a Degree
    Given I login as "admin" with password "password"
    When I modify a degree with faculty "EPS" and university "Universitat de Lleida" changing field name with "Enginyeria Informatica"
    Then The response code is 200
    And The Degree with faculty "EPS" has the name "Enginyeria Informatica"

  Scenario: Modify a Degree when not authenticated
    Given I'm not logged in
    When I modify a degree with name "Enginyeria Informatica" and university "Universitat de Lleida" changing field faculty with "Enginyeria"
    Then The response code is 401
    And The Degree with name "Enginyeria Informatica" has the faculty "EPS"

  Scenario: User tries to modify the Degree
    Given I login as "user" with password "password"
    When I modify a degree with name "Enginyeria Informatica" and university "Universitat de Lleida" changing field faculty with "Enginyeria"
    Then The response code is 403
    And The Degree with name "Enginyeria Informatica" has the faculty "EPS"

  Scenario: Modify a Degree that does not exist
    Given I login as "admin" with password "password"
    When I modify a degree with name "Disseny" and university "Universitat de Lleida" changing field faculty with "Enginyeria"
    Then The response code is 404
