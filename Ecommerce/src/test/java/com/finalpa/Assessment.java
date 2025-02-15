package com.finalpa;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseclass.Base_class;
import com.pom.Home_Page;
import com.pom.Parfum_Page;

public class Assessment extends Base_class {

	public static Home_Page home;
	public static Parfum_Page parfum;

	@Parameters("browser")
	@BeforeClass
	public void browser_Launching(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			browser_Launch("chrome");
			maximizeWindow();
		}else if(browser.equalsIgnoreCase("edge")) {
			browser_Launch("edge");
			maximizeWindow();
		}
		
	}

	@Test(priority = 1)
	public void user_Click_The_Parfum_Category() throws Throwable {
//		browser_Launch("chrome");
//		maximizeWindow();
		// driver.get("https://www.douglas.de/de");
		launch_Url("https://www.douglas.de/de");
		home = new Home_Page(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Thread.sleep(3000);
		WebElement shadowHost = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("usercentrics-root")));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		Object shadowRoot = js.executeScript("return arguments[0].shadowRoot", shadowHost);
		if (shadowRoot == null) {
			throw new NullPointerException("Shadow root not found! The page might not be fully loaded.");
		}

		WebElement accInfo = (WebElement) js.executeScript(
				"return arguments[0].querySelector('button[data-testid=\"uc-accept-all-button\"]')", shadowRoot);
		if (accInfo == null) {
			throw new NullPointerException("'ALL ERLAUBEN' button not found. check the selector.");
		}

		accInfo.click();
		System.out.println("Successfully clicked");

		clickOnElement(home.getParfumOption());
		System.out.println("Successfull");
	}

	@Test(priority = 2)
	public void user_Clicks_The_All_Filter_Options() throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		parfum = new Parfum_Page(driver);
		Assert.assertTrue(parfum.getMehrFilter().isDisplayed() || parfum.getMehrFilter().isEnabled()
				, "Filter element is not ready to interact.");
		clickOnElement(parfum.getMehrFilter());
		Thread.sleep(1000);
		Assert.assertTrue(parfum.getGeschenkfür().isDisplayed() || parfum.getGeschenkfür().isEnabled()
				, "Gift dpdo element is not ready to interact.");
		clickOnElement(parfum.getGeschenkfür());
		Thread.sleep(1000);
		Assert.assertTrue(parfum.getGeschenkfürElement().isDisplayed() || parfum.getGeschenkfürElement().isEnabled()
				, "Gift element is not ready to interact.");
		clickOnElement(parfum.getGeschenkfürElement());
		Thread.sleep(1000);
		Assert.assertTrue(parfum.getProduktart().isDisplayed() || parfum.getProduktart().isEnabled()
				, "Product dpdo element is not ready to interact.");
		clickOnElement(parfum.getProduktart());
		Thread.sleep(1000);
		Assert.assertTrue(parfum.getProduktartElement().isDisplayed() || parfum.getProduktartElement().isEnabled()
				, "Product element is not ready to interact.");
		clickOnElement(parfum.getProduktartElement());
		Thread.sleep(1000);
		Assert.assertTrue(parfum.getMarke().isDisplayed() || parfum.getMarke().isEnabled()
				, "Brand dpdo element is not ready to interact.");
		clickOnElement(parfum.getMarke());
		Thread.sleep(1000);
		Assert.assertTrue(parfum.getMarkeEelment().isDisplayed() || parfum.getMarkeEelment().isEnabled()
				, "Brand element is not ready to interact.");
		clickOnElement(parfum.getMarkeEelment());
		Thread.sleep(1000);
		Assert.assertTrue(parfum.getFürWen().isDisplayed() || parfum.getFürWen().isEnabled()
				, "Gender dpdo element is not ready to interact.");
		clickOnElement(parfum.getFürWen());
		Thread.sleep(1000);
		Assert.assertTrue(parfum.getFürWenElement().isDisplayed() || parfum.getFürWenElement().isEnabled()
				, "Gender element is not ready to interact.");
		clickOnElement(parfum.getFürWenElement());
	}
	
	@Test(priority = 3)
	public void validate_The_Listed_Products() throws Throwable {
		
		List<WebElement> products = driver.findElements(By.xpath("//*[@class='cms-container']//descendant::div[@class='text top-brand']"));
		
		Assert.assertFalse(products.isEmpty(),"No products are displayed after applying filter!");
		System.out.println("Successfully Valiadte the listed products");
	}
}
