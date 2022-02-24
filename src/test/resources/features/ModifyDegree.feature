Feature: Modify a Degree

  Background:
    Given I login as "admin" with password "password"
    And There is a degree created with id "1" and name "Grau en Enginyeria Informatica" and faculty "EPS"

  Scenario: Modify a Degree
    When I modify a degree with id "1" I set a name "Grau en Enginyeria Informatica" and faculty "Enginyeria"
    Then The response code is 200


  Scenario: Modify a Degree when not authenticated
    Given I'm not logged in
    When I modify a degree with id "1" I set a name "Grau en Enginyeria Informatica" and faculty "Enginyeria"
    Then The response code is 401


  Scenario: Modify a Degree that does not exist
    When I modify a degree with id "99" I set a name "Grau en Enginyeria Informatica" and faculty "Enginyeria"
    Then The response code is 403