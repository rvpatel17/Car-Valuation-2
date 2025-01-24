Feature: Car Valuation

  Scenario: Validate car valuation for multiple cars
    Given the car input file "car_input.txt"
    When I extract vehicle registration numbers
    And I search for each registration number on the car valuation website
    Then I should get the correct car details from "car_output.txt"