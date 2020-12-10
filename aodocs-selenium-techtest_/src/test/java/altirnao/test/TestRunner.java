package altirnao.test;

import org.testng.TestNG;

import selenium.listeners.ExtentReportListener;

public class TestRunner {
	
	static TestNG testNg;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		ExtentReportListener ext = new ExtentReportListener();

		testNg = new TestNG();
		
		testNg.setTestClasses(new Class[] {AodocsTest.class});
		testNg.addListener(ext);
		testNg.run();
		
		
	}

}
