package com.roytuts.cucumber.selenium.driver.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, features = "classpath:cuke/features/cukeSelenium.feature", glue = "com.roytuts.cucumber.selenium.driver.step", monochrome = true, plugin = {
		"pretty", "html:target/cucumber", "json:target/Cucumber.json", "junit:target/Cucumber.xml" })
public class CukeRunner {

}
