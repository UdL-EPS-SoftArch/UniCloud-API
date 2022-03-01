Feature: DELETE a Rating

  Scenario: Delete a Rating when not authenticated
    Given I'm not logged in
    When I remove a Rating with id 1
    Then The response code is 401
    And The Rating with  id 1 has not been removed
