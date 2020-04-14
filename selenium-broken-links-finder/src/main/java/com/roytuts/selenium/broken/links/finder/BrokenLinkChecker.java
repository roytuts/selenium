package com.roytuts.selenium.broken.links.finder;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinkChecker {

	private static final Logger LOG = Logger.getLogger(BrokenLinkChecker.class.getName());

	private static WebDriver driver = null;

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");

		String homePage = "https://www.roytuts.com";
		String url = "";
		HttpsURLConnection https = null;

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get(homePage);

		List<WebElement> links = driver.findElements(By.tagName("a"));

		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) {
			url = it.next().getAttribute("href");

			if (url == null || url.isEmpty()) {
				LOG.log(Level.SEVERE, "URL not found");
				continue;
			}

			if (!url.startsWith(homePage)) {
				LOG.log(Level.SEVERE, "URL belongs to another domain, skipping it.");
				continue;
			}

			try {
				https = (HttpsURLConnection) (new URL(url).openConnection());
				https.setRequestMethod("HEAD");
				https.connect();

				int respCode = https.getResponseCode();

				if (respCode >= 400) {
					LOG.log(Level.SEVERE, url + " is a broken link");
				} else {
					LOG.info(url + " is a valid link");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		driver.quit();
	}

}
