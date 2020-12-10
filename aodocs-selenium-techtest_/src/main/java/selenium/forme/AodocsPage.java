package selenium.forme;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.Base.BasePage;


import io.qameta.allure.Step;

public class AodocsPage extends BasePage {

	@FindBy(xpath = "//*[@id=\"DND_banner-module-1\"]/div/div/div[1]/div/div/a")
	WebElement RequestDemo;
	
	@FindBy(id = "firstname-384ed391-59a1-4016-bc91-62bb1307edb2_907")
	WebElement firstName;
	@FindBy(id = "firstname-384ed391-59a1-4016-bc91-62bb1307edb2_907")
	WebElement lastName;
	@FindBy(id = "email-384ed391-59a1-4016-bc91-62bb1307edb2_907")
	WebElement mail;
	@FindBy(id = "company-384ed391-59a1-4016-bc91-62bb1307edb2_907")
	WebElement company;
	
	@FindBy(id = "company_size__c-384ed391-59a1-4016-bc91-62bb1307edb2_907")
	Select companySize;
	//*[@id="hsForm_384ed391-59a1-4016-bc91-62bb1307edb2_907"]/div/div[2]/input
	@FindBy(xpath = "/*[@id=\"hsForm_384ed391-59a1-4016-bc91-62bb1307edb2_907\"]/div/div[2]/input")
	WebElement Submit;
	
	@FindBy(xpath = "//*[@id='hsForm_384ed391-59a1-4016-bc91-62bb1307edb2_907']/div[1]/ul/li/label[contains(text(),'Please complete all required fields.')]")
	WebElement msgError;
	
	
	
	
	
	private WebDriver driver;

	public AodocsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@Step("on ouvre le site web 'AODocsSite'")
	public void openWebsite() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		RequestDemo.click();
		wait.until(ExpectedConditions.visibilityOf(firstName));
		
	}

	@Step("on saisie les informations")
	public void FillTheForm(String FirstName,String LastName,String Mail,String CompanySize) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//driver.findElement(By.id("ccc-notify-accept")).click();
		firstName.sendKeys(FirstName);
		lastName.sendKeys(LastName);
		mail.sendKeys(Mail);
		company.sendKeys("company");
		Select companySizeS = new Select(driver.findElement(By.id("company_size__c-384ed391-59a1-4016-bc91-62bb1307edb2_907")));
		companySizeS.selectByVisibleText(CompanySize);
			
		WebElement SubmitForm = driver.findElement(By.xpath("//*[@id=\"hsForm_384ed391-59a1-4016-bc91-62bb1307edb2_907\"]/div/div[2]/input"));
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", SubmitForm);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", SubmitForm);
	}
	@Step("on vérifie le message d'erreur")
	public void checkErrorMessages() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(msgError));
		
	}
	public void threadwait(long millisecond) {
	try {
		Thread.sleep(millisecond);
	}catch(InterruptedException e) {
		throw new RuntimeException(e.getMessage(), e);
	}
	}
}
