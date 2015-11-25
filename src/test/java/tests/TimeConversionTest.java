package tests;

import omelet.data.IProperty;
import omelet.data.driverconf.IBrowserConf;
import omelet.testng.support.SAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.testng.annotations.Test;
import pageobjects.PageObjectFactory;

public class TimeConversionTest {
	
	SAssert sassert = new SAssert();

	@Test(description = "verify if Google Page is loaded and search is working", enabled = true,dataProvider="XmlData")
	public void verifyGooglePageLoadAndSearch(IBrowserConf browserConf, IProperty prop) throws IOException {
		
		System.out.println(browserConf.getCapabilities().getPlatform());
		PageObjectFactory pof = new PageObjectFactory(browserConf, prop);
		
		pof.googlePage().loadFromProperty().isLoaded().searchTime();
		
	
		String time=pof.searchPage().isLoaded().getConvertedTime();
	
		pof.searchPage().fileWrite(time);
		sassert.assertAll();
	}
	

}
