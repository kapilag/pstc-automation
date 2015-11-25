package google;

import java.util.List;

import omelet.data.IProperty;
import omelet.driver.DriverUtility;
import omelet.exception.FrameworkException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pageobjects.DataEnum;

public class GmailHomePage {
	
	private WebDriver driver;
	private IProperty prop;
	
	@FindBy(css = ".gbqfif")
	private WebElement searchBar;
	
	@FindBys(@FindBy(css = ".xY.a4W"))
	private List<WebElement> listSubjectEmails;
	@FindBy(css = ".gbqfb")
	private WebElement searchButton;
	
	private String expectedTitle = "search";
	
	
	public GmailHomePage(WebDriver driver, IProperty prop){
		this.driver = driver;
		this.prop = prop;
		PageFactory.initElements(driver, this);
	}
	
	public GmailHomePage load(){
		driver.get(prop.getValue(DataEnum.GmailHomePage_url));
		return this;
	}
	
	public GmailHomePage isLoaded(){
		if(null == DriverUtility.waitFor(ExpectedConditions.elementToBeClickable(searchBar), driver, 15)){
			throw new FrameworkException("Gmail Home page is not loaded in +15 seconds");
		}
		return this;
	}
	
	public GmailHomePage search(String searchText){
		String initialTitle = driver.getTitle();
		searchBar.sendKeys(searchText);
		searchButton.click();
		sleep(4);
		return this;
		
	}
	
	public int getEmailsCount(){
		return listSubjectEmails.size();
	}
	
	private void sleep(int timeoutInSeconds){
		try {
			Thread.sleep(timeoutInSeconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
