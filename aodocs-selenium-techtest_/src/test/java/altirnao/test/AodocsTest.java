package altirnao.test;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import selenium.Base.BasePage;
import org.openqa.selenium.WebDriver;


import selenium.Base.BasePage;
import selenium.forme.AodocsPage;
import selenium.forme.GooglePage;
import selenium.listeners.TestAllureListener;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Listeners({ TestAllureListener.class })
public class AodocsTest {

	public BasePage basePage;
	public WebDriver driver;

	public GooglePage googlePage;
	public AodocsPage aodocsPage;

	@BeforeMethod // this method will be executed before every @test method
	public void setUp() {
		basePage = new BasePage();
		driver = basePage.initialize_driver("CHROME");
		driver.get("https://www.google.fr/");
	}

	
	
	@Test(retryAnalyzer = MyRetry.class)
    @Description("The marketing team ask you to write an automation test to validate how prospect access to our website, and the form to request a demo of the product")
	public void AodocsTest() {
		

		googlePage = new GooglePage(driver);
		googlePage.Search("AODocs");
		googlePage.openWebsite();
		aodocsPage = new AodocsPage(driver);
		aodocsPage.openWebsite();
		aodocsPage.FillTheForm("Chaouch","","xxxxxxxtest@gmail.com","0-4 employees");
		aodocsPage.checkErrorMessages();
	}

	@AfterMethod // --this method will be executed after every test method
	public void tearDown() {
		driver.quit();
	}

}
