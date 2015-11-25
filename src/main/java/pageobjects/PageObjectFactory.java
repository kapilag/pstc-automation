package pageobjects;


import google.GmailHomePage;
import google.GooglePage;
import omelet.data.IProperty;
import omelet.data.driverconf.IBrowserConf;
import omelet.driver.Driver;

import org.openqa.selenium.WebDriver;

import amazon.AmazonPage;

public class PageObjectFactory {

	
	private WebDriver driver;
	private IProperty prop;
	private GooglePage googlePage;
	private GmailHomePage gmailHomePage;
	private AmazonPage amazonPage;
	
	public PageObjectFactory(IBrowserConf browserConf, IProperty prop){
		this.prop = prop;
		driver = Driver.getDriver(browserConf);
		driver.manage().window().maximize();
		
	}
	
	public GooglePage googlePage(){
		
		if(null==googlePage)
			googlePage = new GooglePage(driver,prop);
		
		return googlePage;
	}
	
	public GmailHomePage gmailHomePage(){
		if(null == gmailHomePage){
			gmailHomePage = new GmailHomePage(driver, prop);
		}
		
		return gmailHomePage;
	}
	
	public AmazonPage amazonPage(){
		
		if(null==amazonPage)
            amazonPage = new AmazonPage(driver,prop);
		
		return amazonPage;
	}

	
}
