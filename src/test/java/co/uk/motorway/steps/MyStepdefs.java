package co.uk.motorway.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static co.uk.motorway.browserfactory.ManageBrowser.driver;
import static org.testng.AssertJUnit.assertEquals;

public class MyStepdefs {
    Map<String, String> expectedData = new HashMap<>();
    @Given("the car input file {string}")
    public void theCarInputFile(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                expectedData.put(parts[0], line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @When("I extract vehicle registration numbers")
    public void iExtractVehicleRegistrationNumbers() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("car_input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                // Extract registration numbers
                if (line.matches(".*\\b[A-Z0-9]{2}[0-9]{2}\\b.*")) {
                    String regNum = line.substring(line.indexOf(' ') + 1).trim();
                    searchCarDetails(regNum);
                }
            }
        }
    }

    public void searchCarDetails(String regNum) {
        driver.get("https://motorway.co.uk/");
        WebElement searchBox = driver.findElement(By.id("carRegSearchBox"));
        searchBox.sendKeys(regNum);
        WebElement searchButton = driver.findElement(By.id("searchButton"));
        searchButton.click();

        WebElement resultElement = driver.findElement(By.id("valuationResult"));
        String resultText = resultElement.getText();
        assertEquals(resultText, expectedData.get(regNum));
    }

    @And("I search for each registration number on the car valuation website")
    public void iSearchForEachRegistrationNumberOnTheCarValuationWebsite() {



    }

    @Then("I should get the correct car details from {string}")
    public void iShouldGetTheCorrectCarDetailsFrom(String arg0) {

    }
}
        