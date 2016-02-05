package com.wikipediaTest.AutomationFramework;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.wikipediaTest.AutomationFramework.Util.TestUtil;

public class WikiMainPageTest {
	//Initialize driver object from WebDriver class
	public static WebDriver driver;
	private Properties prop = null;
	//path of the file which contains login credentials data
	private String wikiPropertyPath = "C:\\workspace\\flexton\\AutomationFramework\\wiki.properties";
	
	//constructor
	public WikiMainPageTest(){
		prop = TestUtil.loadProperties(wikiPropertyPath);
	}

	@BeforeClass
	public static void setUp() {
		
		//Initialize firefox driver
		driver = new FirefoxDriver();
		//opens wikipedia web page
		driver.get("http://www.wikipedia.com");
		//maximizes the window
		driver.manage().window().maximize();
	}

	@Test
	public void wikiOpenMainPage() {
        //Click on English link
		driver.findElement(By.xpath("//*[@id='www-wikipedia-org']/div[1]/div[2]/a/strong")).click();
		//Asserts title of the page
		String pageTitle = driver.getTitle();
		assertEquals("Assertion Failure", "Wikipedia, the free encyclopedia",pageTitle);

	}

	@Test
	public void SignInTest() {

		// Sign in to wikipedia account
		driver.findElement(By.id("pt-login")).click();
		driver.findElement(By.xpath("//*[@id='wpName1']")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.xpath("//*[@id='wpPassword1']")).sendKeys("2q3w4e5r");
		driver.findElement(By.xpath("//*[@id='wpLoginAttempt']")).click();

		// Assert username after signing in
		String userName = driver.findElement(By.xpath("//*[@id='pt-userpage']/a")).getText();
		assertEquals("Assertion Failure", "ArthiBabykannan", userName);

		// Search java
		driver.findElement(By.id("searchInput")).sendKeys("Java programming",Keys.ENTER);
		String searchPageTitle = driver.getTitle();
		assertEquals("Assertion Failure", "Java (programming language) - Wikipedia, the free encyclopedia",searchPageTitle);

		// Sign out
		driver.findElement(By.id("pt-logout")).click();

		// return to main page
		driver.findElement(By.xpath("//*[@id='n-mainpage-description']/a")).click();

		// assert main page title again
		String pageTitle = driver.getTitle();
		assertEquals("Assertion Failure", "Wikipedia, the free encyclopedia",pageTitle);

	}

	@AfterClass
	public static void tearDown() {
		//close the driver object
		driver.close();
	}

}
