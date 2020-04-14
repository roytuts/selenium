Feature: Search Automation

Scenario: Search in Google

	Given user navigates to Google
	When I enter spring in search box
	Then Google should return some results