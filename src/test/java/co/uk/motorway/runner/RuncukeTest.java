package co.uk.motorway.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features", //path of feature
        glue = "au/com/carsguide/steps",// steps source root path
        plugin = {"html:target/cucumber-reports/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "json:target/cucumber-reports/cucumber.json"},
        tags = "@a",
        dryRun = false
)




public class RuncukeTest extends AbstractTestNGCucumberTests {



}
