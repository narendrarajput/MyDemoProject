package com.executer;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pages.ContactUsPage;
import com.pages.InviteFriends;
import com.pages.UserLogin;

import pom.utils.TakeScreenshot;
import pom.utils.TestDataComman;

public class ExecuteTest 
{
	
	public static WebDriver driver = null;
	
	
	@BeforeClass
	public void setup()
	{
		// Intitilize driver
		System.setProperty("webdriver.chrome.driver", "D:\\Application\\chromedriver.exe");	
		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("disable-infobars"); 
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("http://www.mymoneykart.com/mmkweb/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test(enabled=false)
	public  void submitFeedback()
	{
		
		ContactUsPage conpage = new ContactUsPage(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoader")));		
		conpage.contactUsLink.click();
		conpage.submitContactUsForm(TestDataComman.fName, TestDataComman.fEmail, TestDataComman.fMobile, TestDataComman.fType, TestDataComman.fMessage);
		
		

	}
	@Test(priority = 1)
	public void login()
	{
		UserLogin login = new UserLogin(driver);
		login.doLogin(TestDataComman.username, TestDataComman.password);
		
	}
	
	@Test(priority = 2)
	public void inviteFriend()
	{
		InviteFriends invitefriend = new InviteFriends(driver);
		invitefriend.inviteFriend();
		invitefriend.myTest();
		
	}

}
