Feature: Delete degree

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@gmail.com"
    Given There is a registered user with username "admin" and password "password" and email "admin@gmail.com"
    Given There is a university with name "Universitat de Lleida", acronym "UDL", country "Spain", city "Lleida"
    Given There is a university with name "Universitat de Girona", acronym "UDG", country "Spain", city "Girona"
    And There is a degree created with name "Disseny Grafic" and faculty "EPS" and university "Universitat de Lleida"
    And There is a degree created with name "Disseny Grafic" and faculty "EPS" and university "Universitat de Girona"


  Scenario: Administrator deletes a degree by name
    Given I login as "admin" with password "password"
    When I delete a degree with name "Disseny Grafic" and university "Universitat de Lleida"
    Then The response code is 204
    And The degree with name "Disseny Grafic" and university "Universitat de Lleida" doesn't exist
    And The degree with name "Disseny Grafic" and university "Universitat de Girona" exists

  Scenario: Delete a degree that does not exist
    And I login as "admin" with password "password"
    When I delete a non-existent degree
    Then The response code is 404

  Scenario: Delete a degree when not authenticated
    Given I'm not logged in
    When I delete a degree with name "Disseny Grafic" and university "Universitat de Lleida"
    Then The response code is 401
    And The degree with name "Disseny Grafic" and university "Universitat de Lleida" exists

    #ToDo Intent de eliminar un degree com a user