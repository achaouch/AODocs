package selenium.listeners;

import io.qameta.allure.Attachment;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;
import selenium.Base.BasePage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


/**
 * 
 * @author Chaouch
 *
 */



public class TestAllureListener implements ITestListener {

		private static String getTestMethodName(ITestResult iTestResult) {
			return iTestResult.getMethod().getConstructorOrMethod().getName();
		}

		// Text attachments for Allure
		@Attachment(value = "Page screenshot", type = "image/png")
		public byte[] saveScreenshotPNG(WebDriver driver) {
			String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
			Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(1000)).takeScreenshot(driver);
			BufferedImage image = screenshot.getImage();
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
		    try {
		        ImageIO.write(image, "png", stream);
		    } catch(IOException e) {
		        // This *shouldn't* happen with a ByteArrayOutputStream, but if it
		        // somehow does happen, then we don't want to just ignore it
		        throw new RuntimeException(e);
		    }
		    return stream.toByteArray();
		}


		// Text attachments for Allure
		@Attachment(value = "{0}", type = "text/plain")
		public static String saveTextLog(String message) {
			return message;
		}

		// HTML attachments for Allure
		@Attachment(value = "{0}", type = "text/html")
		public static String attachHtml(String html) {
			return html;
		}

		@Override
		public void onStart(ITestContext iTestContext) {
			System.out.println("I am in onStart method " + iTestContext.getName());
			iTestContext.setAttribute("WebDriver", BasePage.getDriver());
		}

		@Override
		public void onFinish(ITestContext iTestContext) {
			System.out.println("I am in onFinish method " + iTestContext.getName());
		}

		@Override
		public void onTestStart(ITestResult iTestResult) {
			System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
		}

		@Override
		public void onTestSuccess(ITestResult iTestResult) {
			System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
		}

		@Override
		public void onTestFailure(ITestResult iTestResult) {
			System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
			Object testClass = iTestResult.getInstance();
			WebDriver driver = BasePage.getDriver();
			// Allure ScreenShotRobot and SaveTestLog
			if (driver instanceof WebDriver) {
				System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
				saveScreenshotPNG(driver);
			}
			// Save a log on allure.
			saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");		
		}

		@Override
		public void onTestSkipped(ITestResult iTestResult) {
			System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
		}

		@Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
			System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
		}

	}

