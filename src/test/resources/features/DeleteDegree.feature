Feature: Delete degree

  Background:
    Given There is a registered user with username "admin" and password "password" and email "admin@gmail.com"
    And There is a degree created with name "Disseny Grafic" and faculty "EPS"


  Scenario: Administrator deletes a degree by name
    Given I login as "admin" with password "password"
    When I delete a degree with name "Disseny Grafic"
    Then The response code is 204
    And The degree with name "Disseny Grafic" doesn't exist

  Scenario: Delete a degree that does not exist
    And I login as "admin" with password "password"
    When I delete a non-existent degree
    Then The response code is 404

  Scenario: Delete a degree when not authenticated
    Given I'm not logged in
    When I delete a degree with name "Disseny Grafic"
    Then The response code is 401
    And The degree with name "Disseny Grafic" exist

    #ToDo Intent de eliminar un degree com a user