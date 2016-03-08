package com.wikipediaTest.AutomationFramework;

import static org.junit.Assert.*;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.wikipediaTest.AutomationFramework.Util.TestUtil;


public class WikiMainPageTest {
	//Initialize driver object from WebDriver class
	public static WebDriver driver;
	private Properties prop = null;
	//path of the file which contains login credentials data
	private String wikiPropertyPath = "C://Users//test//Automation-Framework//AutomationFramework//wiki.properties";
	
	//constructor
	public WikiMainPageTest(){
		prop = TestUtil.loadProperties(wikiPropertyPath);
	}

	@BeforeClass
	public static void setUp() {
		
		//Initialize firefox driver
		driver = new FirefoxDriver();
		//If wait is set, driver will wait for specified amount of time for each findElement call
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
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
	public void signInTest() {

		// Sign in to wikipedia account

		driver.findElement(By.id("pt-login")).click();
		driver.findElement(By.xpath("//*[@id='wpName1']")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.xpath("//*[@id='wpPassword1']")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("//*[@id='wpLoginAttempt']")).click();
		
		// Assert username after signing in
		String userName = driver.findElement(By.xpath("//*[@id='pt-userpage']/a")).getText();
		assertEquals("Assertion Failure",prop.getProperty("username"), userName);

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
	
	@Test
	public void inValidUserTest(){
		//Sign in to wikipedia account with invalid password
		driver.findElement(By.id("pt-login")).click();
		driver.findElement(By.xpath("//*[@id='wpName1']")).clear();
		driver.findElement(By.xpath("//*[@id='wpName1']")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.xpath("//*[@id='wpPassword1']")).sendKeys(prop.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//*[@id='wpLoginAttempt']")).click();
		WebElement element = driver.findElement(By.xpath("//*[@id='userloginForm']/form/div[1]"));
		String text = element.getText();
		System.out.println(text);
		//Assert error message
		Assert.assertEquals("Login error"+"\n" +"Incorrect password entered. Please try again.", text);
		
	}

	@AfterClass
	public static void tearDown() {
		//close the driver object
		driver.close();
	}
	
	
}
