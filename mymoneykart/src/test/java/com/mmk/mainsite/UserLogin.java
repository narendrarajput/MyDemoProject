package com.mmk.mainsite;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;

import com.mmk.commonutils.Comman;
import com.mmk.commonutils.TestDataComman;
import com.mmk.reader.LogWriter;


public class UserLogin 
{
		@FindBy(className = "Popupiframe")
		WebElement iframe;
		
		@FindBy(id = "MobileNo")
		WebElement mobileNumber;
		
		@FindBy(id = "btnLogin")
		WebElement loginButton;
		
		@FindBy(id = "Password")
		WebElement password;
		
		@FindBy(id = "btnSubmit")
		WebElement submitButton;
		
		@FindBy(id = "divLoader")
		WebElement loader;
		
		@FindBy(xpath = ".//*[@id='header']/div/div/div[5]/ul/li[1]/label")
		List<WebElement> usersname;
		
		@FindBy(id = "linkshowwallet")
		WebElement walletButton;		
		
		@FindBy(id = "WalletPartialView")
		WebElement walletPopup;
		
		@FindBy(xpath = "//div[@id='WalletPartialView']//li")
		List<WebElement> walletData;
		
		
		
		WebDriver driver;
		
		public UserLogin(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
			this.driver=driver;
		}
		
		public void doLogin(String username, String pasword) throws InterruptedException
		{
			try
			{
				//	WebDriverWait wait = new WebDriverWait(driver, 60);
				// Switch into popup opens in iframe and do login
				Comman.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
				LogWriter.logger.info("On User Login Popup");
				//Thread.sleep(3000);
				//driver.switchTo().frame(iframe);
				mobileNumber.sendKeys(username);
				Comman.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoader")));
				loginButton.click();
				LogWriter.logger.info("Username Entered");
				Comman.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoader")));
				password.sendKeys(pasword);
				LogWriter.logger.info("Password Entered");
				submitButton.click();	
				LogWriter.logger.info("Login processing...");
				driver.switchTo().defaultContent();
				Comman.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoader")));
				//AssertJUnit.assertEquals(TestDataComman.usersname, usersname.getText());
				//TakeScreenshot.takeScreen("hello", true);
				//System.out.println("User Log in success");			
				LogWriter.logger.info("User login success");
			}
			catch(Exception e)
			{
				LogWriter.logger.info(e.toString());
			}
		}
		
		public void checkWallet()
		{
			if(usersname.size()>0)
			{
				Comman.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoader")));
				walletButton.click();
				Comman.wait.until(ExpectedConditions.visibilityOf(walletPopup));
				for(WebElement e: walletData)
				{
					LogWriter.logger.info(e.getText());
				}
			}
		}
}
