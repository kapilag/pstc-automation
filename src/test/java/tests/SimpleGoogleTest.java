package tests;

import omelet.data.IProperty;
import omelet.data.driverconf.IBrowserConf;
import omelet.driver.Driver;
import omelet.testng.support.SAssert;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pageobjects.GooglePage;
import pageobjects.PageObjectFactory;
import pageobjects.SeleniumPage;

public class SimpleGoogleTest {
	
	SAssert sassert = new SAssert();

	@Test(description = "verify if Selenium title is as expected", enabled = true,dataProvider="XmlData")
	public void verifySeleniumTitle_XML(IBrowserConf browserConf, IProperty prop) {
		
		System.out.println(browserConf.getCapabilities().getPlatform());
		PageObjectFactory pof = new PageObjectFactory(browserConf, prop);
		
		pof.googlePage().loadFromProperty().isLoaded().search("Selenium").clickOnLink(0);
		// Selenium
	
		sassert.assertEquals(pof.seleniumPage().isLoaded().getTitle(),
				prop.getValue("Selenium_Title"),
				"Check for the title of the page");
		sassert.assertAll();
	}
	

}
