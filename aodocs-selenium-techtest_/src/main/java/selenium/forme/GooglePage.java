package selenium.forme;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.Base.BasePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class GooglePage extends BasePage {

	
	
	@FindBy(xpath = "//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input")
	WebElement inputGoogle;
	
	@FindBy(xpath = "//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]")
	WebElement searchGoogle;
	
	@FindBy(id = "result-stats")
	WebElement resultOfSearch;
	
	@FindBy(xpath = "//*[@id=\"gbwa\"]/div/a")
	WebElement confirmInput;
	
	@FindBy(xpath = "//*[@id=\"introAgreeButton\"]/span/span")
	WebElement AgreeButton;
	
	
	//@FindBy(xpath = "//*[@id='rso']/div[1]/div/div[1]/a/h3/span[contains(text(),'AODocs: Cloud Document Services Platform')]")
	@FindBy(xpath = "//a[@href='https://www.aodocs.com/']/h3/span")
	WebElement AODocsSite;
	
	@FindBy(xpath = "//*[@id=\"DND_banner-module-1\"]/div/div/div[1]/div/div/a")
	WebElement RequestDemo;
	
	
	private WebDriver driver;

	public GooglePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Step("On recherche:  {0} sur Google'")
	public void Search(String seachValue) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(inputGoogle));
		driver.switchTo().frame(0);
		wait.until(ExpectedConditions.visibilityOf(AgreeButton));
		AgreeButton.click();
		inputGoogle.sendKeys(seachValue);
		confirmInput.click();
		searchGoogle.click();
		wait.until(ExpectedConditions.visibilityOf(resultOfSearch));
		
	}
	
	@Step("on accède au site 'AODocsSite'")
	public void openWebsite() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(AODocsSite));
		AODocsSite.click();
		wait.until(ExpectedConditions.visibilityOf(RequestDemo));
		
	}

	
}
