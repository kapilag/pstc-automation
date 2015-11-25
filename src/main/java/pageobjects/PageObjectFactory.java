package pageobjects;


import omelet.data.IProperty;
import omelet.data.driverconf.IBrowserConf;
import omelet.driver.Driver;

import org.openqa.selenium.WebDriver;

public class PageObjectFactory {

	
	private WebDriver driver;
	private IProperty prop;
	
	private SearchPage searchPage;
	private GooglePage googlePage;
	
	
	public PageObjectFactory(IBrowserConf browserConf, IProperty prop){
		this.prop = prop;
		driver = Driver.getDriver(browserConf);
		driver.manage().window().maximize();
		
	}
	
	
public SearchPage searchPage(){
		
		if(null==searchPage)
			searchPage = new SearchPage(driver,prop);
		
		return searchPage;
	}
	public GooglePage googlePage(){
		
		if(null==googlePage)
			googlePage = new GooglePage(driver,prop);
		
		return googlePage;
	}
	
}
