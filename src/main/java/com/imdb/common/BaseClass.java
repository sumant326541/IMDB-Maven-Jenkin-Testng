package com.imdb.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

	public String propertyReader(String key)
	{
		String path= System.getProperty("user.dir");
		return reader(key,path+"/src/main/java/com/imdb/config/imdb.properties");   
	}

	public String dataReader(String key)
	{
		String path= System.getProperty("user.dir");
		return reader(key,path+"/src/main/java/com/imdb/config/data.properties");   
	}
	
	public String reader(String key,String filepath)
	{
		Properties prop = new Properties();

		try {

			prop.load(new FileInputStream(filepath));  
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);

	}
	
	public WebElement waitForElementToBeClicable(WebDriver driver, String locator,int time)
	{
		WebDriverWait wait = new WebDriverWait(driver,time);
		WebElement element;
		element= wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		return element;
	}
	public WebElement waitForElementToBeVisible(WebDriver driver, String locator,int time)
	{
		WebDriverWait wait = new WebDriverWait(driver,time);
		WebElement element;
		element= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		return element;
	}
	public WebElement waitForElementToBeVisibleByName(WebDriver driver, String locator,int time)
	{
		WebDriverWait wait = new WebDriverWait(driver,time);
		WebElement element;
		element= wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));
		return element;
	}
	public void waitForPageToLoad(int time)
	{
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doLogin(WebDriver driver)
	{
		driver.manage().window().maximize();
		driver.get(propertyReader("imdbUrl"));
		WebElement signIN = waitForElementToBeClicable(driver,propertyReader("signIN"),10);
		signIN.click();
		WebElement signInByImdb = waitForElementToBeClicable(driver,propertyReader("signInByImdb"),10);
		signInByImdb.click();
		waitForPageToLoad(2000);
		
		driver.findElement(By.name(propertyReader("userIdTextBoxname"))).sendKeys(dataReader("userID"));
		driver.findElement(By.name(propertyReader("passwordTextBoxname"))).sendKeys(dataReader("password"));
		driver.findElement(By.id(propertyReader("SignInButtonid"))).click();
	}
}
