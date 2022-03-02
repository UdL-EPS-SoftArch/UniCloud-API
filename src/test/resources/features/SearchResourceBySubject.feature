Feature: Search Resource by Subject
  As a user, normal or admin
  I want to find resources by subject

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given There is a registered subject "subject"
    Given There is a registered resource with name "name" by the user "user", with description "description", file "file" and for the subject "subject"