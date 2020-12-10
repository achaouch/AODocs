package selenium.Base;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

	public WebDriver initialize_driver(String browser) {
		if (browser.equals("CHROME")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		tdriver.set(driver);
		return getDriver();

	}

	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}

	public String getUrl() {
		String baseUrl = "";
		String url = baseUrl + "/login";
		return url;
	}

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}

}
