package com.roytuts.cucumber.selenium.driver.step;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CukeStepDefinition {

	WebDriver driver = null;

	@Given("user navigates to Google")
	public void user_navigates_to_Google() {
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.google.com/");
	}

	@When("I enter spring in search box")
	public void i_enter_spring_in_search_box() {
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("spring");
		element.submit();
	}

	@Then("Google should return some results")
	public void google_should_return_some_results() {
		new WebDriverWait(driver, 10);
		try {
			List<WebElement> results = driver
					.findElements(By.xpath("//div[@class='bkWMgd']//div[@class='srg']//div[@class='g']/link"));
			System.out.println("Elements: " + results.size());

			assertTrue(!results.isEmpty());

			for (WebElement webElement : results) {
				System.out.println(webElement.getAttribute("href"));
			}
		} finally {
			driver.close();
			driver.quit();
		}
	}

}
