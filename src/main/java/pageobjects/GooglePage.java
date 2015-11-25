package pageobjects;

import java.util.List;

import omelet.common.ExpectedConditionExtended;
import omelet.data.IProperty;
import omelet.driver.DriverUtility;
import omelet.exception.FrameworkException;
import omelet.testng.support.SAssert;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GooglePage {
	
	private WebDriver driver;
	private IProperty prop;
	SAssert sassert = new SAssert();
	
	@FindBy(name = "q")
	private WebElement searchBar;
	@FindBys(@FindBy(css = ".rc .r a"))
	private List<WebElement> searchReturnLinks;
	@FindBy(css = ".gb_Bd.gb_Ca.gb_9a")
	private WebElement signInHomePageButton;
	@FindBy(id = "Email")
	private WebElement emailInputBox;
	@FindBy(id = "next")
	private WebElement nextButton;
	@FindBy( id = "Passwd")
	private WebElement passwd;
	@FindBy(id = "signIn")
	private WebElement signInMain;
	@FindBy(css = ".gb_P.gb_R")
	private WebElement loginNameMainScreen;
	
	//Simple Test Contructor
	/*public GooglePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}*/
	//Constructor using Property use
	public GooglePage(WebDriver driver,IProperty prop){
		this.driver = driver;
		this.prop = prop;
		PageFactory.initElements(driver, this);
	}
	
	
	
	public GooglePage load(String url){
		driver.get(prop.getValue(DataEnum.Google_url));
		return this;
	}
	
	public GooglePage loadFromProperty(){
		sassert.assertTrue(true,"Navigate to "+prop.getValue("Google_url"));
		driver.get(prop.getValue("Google_url"));
		return this;
	}
	
	public GooglePage isLoaded(){
		if(null == DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(searchBar), driver, 5)){
			throw new FrameworkException("Not able to load Google Home page in 5 seconds");
		}
		return this;
	}
	
	public GooglePage search(String searchText){
		searchBar.sendKeys(searchText+Keys.RETURN);
		return this;
	}
	
	public void clickOnLink(int indexOfTheLink){
		searchReturnLinks.get(indexOfTheLink).click();
	}
	
	public void login(){
		signInHomePageButton.click();
		sassert.assertTrue(DriverUtility.waitFor(ExpectedConditions.visibilityOf(emailInputBox), driver, 15) != null,"Enter email:"+prop.getValue(DataEnum.Google_email));
		emailInputBox.sendKeys(prop.getValue(DataEnum.Google_email));
		nextButton.click();
		this.passwd.sendKeys(prop.getValue(DataEnum.Google_passwd));
		signInMain.click();
		sassert.assertEquals(loginNameMainScreen.getText(), "crest","Did we logged into google?");
		
	}


}
