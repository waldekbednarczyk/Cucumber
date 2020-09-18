package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WomenPage {

	WebDriver driver;

	public WomenPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// i'm not sure whether using whole url as locator is a great idea
	@FindBy(css = "h5 [href='http://automationpractice.com/index.php?id_category=4&controller=category']")
	WebElement btn_Tops;
	@FindBy(css = "h5 [href='http://automationpractice.com/index.php?id_category=8&controller=category']")
	WebElement btn_Dresses;

	public boolean isDisplayed_Tops() {
		return btn_Tops.isDisplayed();
	}

	public boolean isDisplayed_Dresses() {
		return btn_Dresses.isDisplayed();
	}

}
