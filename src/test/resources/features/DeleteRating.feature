Feature: DELETE a Rating

  Scenario: Student authenticated deletes his/her own rating
    Given Student is logged in as "demo" with password "password"
    And student has never made a rating before
    When I delete a Rating with id 26
    Then The response code is 401
    And A rating made by student "demo" does not exist
