package pageObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import managers.FileReaderManager;
import selenium.Wait;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "footer")
	WebElement footer;
	@FindBy(css = ".facebook [target='_blank']")
	WebElement btn_Facebook;
	@FindBy(css = ".twitter [target='_blank']")
	WebElement btn_Twitter;
	@FindBy(css = ".youtube [target='_blank']")
	WebElement btn_Youtube;
	@FindBy(css = ".google-plus [target='_blank']")
	WebElement btn_GooglePlus;
	@FindBy(css = "[href='http://automationpractice.com/index.php?id_category=3&controller=category'][title='Women']")
	WebElement btn_Women;
	// i think that these xpaths could be simpler ;)
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/ul[1]/li[2]/div[1]/div[1]/div[1]/a[1]/img[1]")
	WebElement pic_Blouse;
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/ul[1]/li[2]/div[1]/div[2]/div[2]/a[1]/span[1]")
	WebElement adToCart_Blouse;
	@FindBy(id = "layer_cart_product_price")
	WebElement cartProductPrice;
	@FindBy(xpath = "//span[@class='ajax_block_cart_total']")
	WebElement cartTotalPrice;
	@FindBy(css = "[title='Proceed to checkout']")
	WebElement btn_ProceedToCheckout;
	@FindBy(css = "[title='Contact us']")
	WebElement btn_ContactUs;
	@FindAll(@FindBy(css = "#blockbestsellers [itemprop='price']"))
	List<WebElement> prd_BestsellersListPrice;
	@FindAll(@FindBy(css = "#blockbestsellers .product-name"))
	List<WebElement> prd_BestsellersListName;
	@FindBy(css = "[href='#blockbestsellers']")
	WebElement btn_Bestsellers;
	@FindBy(css = ".login")
	WebElement btn_SignIn;
	@FindAll(@FindBy(xpath = "//*[@id]"))
	List<WebElement> allSiteIDs;
	@FindAll(@FindBy(css = ".fancybox [itemprop='image']"))
	List<WebElement> allThumbImages;
	// i think that this xpath could be simpler ;)
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/ul[1]/li[2]/div[1]/div[1]/div[1]/a[2]/span[1]")
	WebElement btn_BlouseQuickView;

	public void navigateTo_HomePage() {
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
	}

	public void scrollToFooter() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", footer);
	}

	public boolean isDisplayed_Facebook() {
		return btn_Facebook.isDisplayed();
	}

	public boolean isDisplayed_Twitter() {
		return btn_Twitter.isDisplayed();
	}

	public boolean isDisplayed_Youtube() {
		return btn_Youtube.isDisplayed();
	}

	public boolean isDisplayed_GooglePlus() {
		return btn_GooglePlus.isDisplayed();
	}

	public void click_Women() {
		btn_Women.click();
	}

	public void addToCartBlouse() {
		Actions action = new Actions(driver);
		action.moveToElement(pic_Blouse).moveToElement(adToCart_Blouse).click().build().perform();
	}

	public String getCartProductPrice() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(cartProductPrice));
		return cartProductPrice.getText();
	}

	public String getCartTotalPrice() {
		return cartTotalPrice.getText();
	}

	public void click_ProceedToCheckout() {
		btn_ProceedToCheckout.click();
	}

	public void click_ContactUs() {
		btn_ContactUs.click();
	}

	public void click_Bestsellers() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", btn_Bestsellers);
		btn_Bestsellers.click();
		Wait.untilJqueryIsDone(driver);
	}

	public List<String> getBestsellersProductNames() throws InterruptedException {
		List<String> productNames = new ArrayList<>();
		for (WebElement name : prd_BestsellersListName) {
			productNames.add(name.getText());
		}
		return productNames;
	}

	public List<String> getBestsellersProductPrices() throws InterruptedException {
		List<String> productPrices = new ArrayList<>();
		for (WebElement price : prd_BestsellersListPrice) {
			if (price.getText().equals("")) {
			} else {
				productPrices.add(price.getText());
			}
		}
		return productPrices;
	}

	public void saveToFile() throws FileNotFoundException, InterruptedException {
		try (PrintStream out = new PrintStream(new FileOutputStream("filename.txt"))) {
			out.println(getBestsellersProductNames());
			out.println(getBestsellersProductPrices());
			out.close();
		}
	}

	public void takeScreenshot() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("screenshot.png"));
	}

	public void click_SignIn() {
		btn_SignIn.click();
	}

	public List<String> getAllSiteIDs() throws InterruptedException {
		List<String> ids = new ArrayList<>();
		for (WebElement id : allSiteIDs) {
			ids.add(id.getAttribute("id"));
		}
		return ids;
	}

	public List<String> noDuplicatesArrayList() throws InterruptedException {
		Set<String> set = new HashSet<>(getAllSiteIDs());
		List<String> idNoDuplicate = new ArrayList<>();
		idNoDuplicate.addAll(set);
		return idNoDuplicate;
	}

	public void saveToFileIDs() throws FileNotFoundException, InterruptedException {
		try (PrintStream out = new PrintStream(new FileOutputStream("IDs.txt"))) {
			out.println(noDuplicatesArrayList());
			out.close();
		}
	}

	public void click_BlouseQuickView() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", pic_Blouse);
		Actions action = new Actions(driver);
		action.moveToElement(pic_Blouse).moveToElement(btn_BlouseQuickView).click().build().perform();
		Wait.until(driver, ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='fancybox-overlay fancybox-overlay-fixed']")));
		Wait.untilJqueryIsDone(driver);
	}

	public List<String> saveBlosueImg() throws InterruptedException, MalformedURLException, IOException {
		driver.switchTo().frame(1);
		List<String> imgs = new ArrayList<>();
		int i = 0;
		for (WebElement img : allThumbImages) {
			BufferedImage bufferedImage = ImageIO.read(new URL(img.getAttribute("src")));
			File outputfile = new File("saved" + i + ".png");
			ImageIO.write(bufferedImage, "png", outputfile);
			imgs.add(img.getAttribute("src"));
			i++;
		}
		driver.switchTo().defaultContent();
		return imgs;
	}

	public void getNumberOfDownloadImages() throws MalformedURLException, InterruptedException, IOException {
		System.out.println(saveBlosueImg().size());
	}

}
