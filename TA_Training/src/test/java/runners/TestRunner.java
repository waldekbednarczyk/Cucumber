package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src\\test\\resources\\funcionalTests"},
		glue = {"stepDefinitions"},
		monochrome = true,
		stepNotifications = true
		)

public class TestRunner {
	

}
