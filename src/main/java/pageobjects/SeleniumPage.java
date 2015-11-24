package pageobjects;

import omelet.common.ExpectedConditionExtended;
import omelet.data.IProperty;
import omelet.driver.DriverUtility;
import omelet.exception.FrameworkException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumPage {
	
	
	@FindBy(css = "#header h1 a")
	private WebElement headerText;
	@FindBy(id = "editPage")
	private WebElement editThisPage;
	private WebDriver driver;
	private IProperty prop;

	public SeleniumPage(WebDriver driver, IProperty prop){
		this.driver = driver;
		this.prop = prop;
		PageFactory.initElements(driver, this);
	}
	
	

	public SeleniumPage isLoaded(){
		if(null == DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(editThisPage), driver, 20)){
			throw new FrameworkException("Selenium Home Page is not loaded in 10 seconds");
		}
		return this;
	}
	
	public String getTitle(){
		return driver.getTitle();
	}
	
	public String getHeaderTest(){
		return headerText.getText();
	}

}
