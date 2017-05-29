package com.mmk.testexecuter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mmk.commonutils.DriverSetup;
import com.mmk.commonutils.ReadExcel;
import com.mmk.commonutils.TestDataComman;
import com.mmk.mainsite.ContactUsPage;
import com.mmk.mainsite.InviteFriends;
import com.mmk.mainsite.UserLogin;

public class MMKMainSuitExecuter extends DriverSetup
{
	
	//WebDriver driver = new DriverSetup().getDriver();

	@Test(priority=2)
	public  void submitFeedback() throws InterruptedException
	{
		
		ContactUsPage conpage = new ContactUsPage(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoader")));		
		conpage.contactUsLink.click();
		conpage.submitContactUsForm(TestDataComman.fName, TestDataComman.fEmail, TestDataComman.fMobile, TestDataComman.fType, TestDataComman.fMessage);

	}
	@Test(priority = 1)
	public void login() throws IOException, InterruptedException
	{
		UserLogin login = new UserLogin(driver);
		ReadExcel read = new ReadExcel();
		String uname = read.getCellData(1, 0);
		String passwd = read.getCellData(1, 1);
		login.doLogin(uname, passwd);
		
	}
	
	@Test(priority = 3)
	public void inviteFriend()
	{
		InviteFriends invitefriend = new InviteFriends(driver);
		invitefriend.inviteFriend(TestDataComman.friendName,TestDataComman.friendNumber);
		

		
		
	}
}