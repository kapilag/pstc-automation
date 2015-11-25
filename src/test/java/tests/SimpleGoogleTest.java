package tests;

import omelet.data.IProperty;
import omelet.data.driverconf.IBrowserConf;
import omelet.testng.support.SAssert;
import org.testng.annotations.Test;
import pageobjects.PageObjectFactory;

public class SimpleGoogleTest {
	
	SAssert sassert = new SAssert();

	@Test(description = "verify if Selenium title is as expected", enabled = true,dataProvider="XmlData")
	public void verifySeleniumTitle_XML(IBrowserConf browserConf, IProperty prop) {
		
		PageObjectFactory pof = new PageObjectFactory(browserConf, prop);
		pof.googlePage().loadFromProperty().isLoaded().login();
		
		sassert.assertAll();
	}
	

}
