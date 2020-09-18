package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage {

	WebDriver driver;

	public ContactUsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "id_contact")
	WebElement drpdwn_SubjectHeading;
	@FindBy(id = "email")
	WebElement txtbx_Email;
	@FindBy(id = "id_order")
	WebElement txtbx_OrderReference;
	@FindBy(id = "fileUpload")
	WebElement txtbx_AttachFile;
	@FindBy(id = "message")
	WebElement txtbx_Message;
	@FindBy(css = "[name='submitMessage']")
	WebElement btn_Send;
	@FindBy(css = ".alert-success")
	WebElement messageSuccessfullySent;

	public void select_SubjectHeading(String select) {
		Select subjectHeading = new Select(drpdwn_SubjectHeading);
		subjectHeading.selectByVisibleText(select);
	}

	public void enter_Emial(String emial) {
		txtbx_Email.sendKeys(emial);
	}

	public void enter_OrderReference(String orderReference) {
		txtbx_OrderReference.sendKeys(orderReference);
	}

	public void enter_AttachFile() {
		// use relative path
		// what if we want to attach different file?
		txtbx_AttachFile.sendKeys(
				"C:\\Users\\p_smandzich\\Desktop\\Cucumber\\TA_Training\\src\\test\\resources\\files\\contactUs.txt");
	}

	public void enter_Message(String message) {
		txtbx_Message.sendKeys(message);
	}

	public void click_Send() {
		btn_Send.click();
	}

	public String getMessageSuccessfullySent() {
		return messageSuccessfullySent.getText();
	}

}
