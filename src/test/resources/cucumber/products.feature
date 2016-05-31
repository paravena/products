Feature: Product Web Service
  As a user I want to use Product Web service, and see if I'm able to store and retrieve new and existing products

  Scenario: New Products
    Given Product Web Service is running
    And I post following products:
      |Product 1|First Product|100|
      |Product 2|Second Product|101|
    When I send a request for all products
    Then I retrieve following products:
      |Product 1|First Product|100|
      |Product 2|Second Product|101|
