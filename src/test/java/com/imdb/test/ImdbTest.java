package com.imdb.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import com.imdb.common.BaseClass;


public class ImdbTest extends BaseClass{
	WebDriver driver;

	@BeforeTest
	public void setUp() {
		String path= System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",path+"/driver/chromedriver"); // for mac	
		driver = new ChromeDriver();
	}
	@Test
	public void loginToImdb() {
		doLogin(driver);
		waitForPageToLoad(2000);
		driver.get(propertyReader("LogOut"));
		
	}

	@Test
	public void shortMovieBydateAndgetReleaseDate() {
		
		doLogin(driver);
		WebElement MenuBar=waitForElementToBeClicable(driver,propertyReader("MenuBar"),10);
		MenuBar.click();	
		waitForPageToLoad(2000);
		driver.findElement(By.xpath(propertyReader("TopRatedMovie"))).click();
		waitForElementToBeVisibleByName(driver,propertyReader("shortDrpDownname"),10);	
		Select select = new Select(driver.findElement(By.name(propertyReader("shortDrpDownname"))));
		select.selectByIndex(2);
		waitForPageToLoad(2000);			
		List<WebElement> movielist = driver.findElements(By.xpath(propertyReader("movieList")));
		movielist.get(movielist.size()-1).click();
		waitForPageToLoad(2000);		
		String releasedate=driver.findElement(By.xpath(propertyReader("releaseDate"))).getText();
		System.out.println("Release month and year of last movie is:  "+releasedate);
		
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
