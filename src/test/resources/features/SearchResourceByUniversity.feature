#Feature: Search Resource by University
#  As a user, normal or admin
#  I want to find resources by university
#
#  Background:
#    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
#    And There is a registered subject "subject" for the degree "degree" in the university "university"
#    And There is a registered subject "subject2" for the degree "degree2" in the university "university2"
#    And There is a registered resource with name "name" by the user "user", with description "description", file "file" and for the subject "subject"
#    And There is a registered resource with name "name2" by the user "user", with description "description", file "file" and for the subject "subject2"
#
#  Scenario: Serch a resource by the exact university
#    Given I login as "user" with password "password"
#    When I search a resource by its exact university "university"
#    Then The response code is 200
#    And 1 resources have been retrieved
#
#  Scenario: Search for resources containing a university
#    Given I login as "user" with password "password"
#    When I search for resources containing the university "university"
#    Then The response code is 200
#    And 2 resources have been retrieved
#
#  Scenario: Search a resource by the exact university and not finding any result
#    Given I login as "user" with password "password"
#    When I search a resource by its exact university "university_nonexistent"
#    Then The response code is 404
#    And The error message is "No resource found with this exact university"
#
#  Scenario: Search for resources containing a university and not finding any result
#    Given I login as "user" with password "password"
#    When I search for resources containing the university "university_nonexistent"
#    Then The response code is 404
#    And The error message is "No resources found containing this university"