package amazon;

import omelet.common.ExpectedConditionExtended;
import omelet.data.IProperty;
import omelet.driver.DriverUtility;
import omelet.exception.FrameworkException;
import omelet.testng.support.SAssert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobjects.DataEnum;

public class AmazonPage {

	private WebDriver driver;
	private IProperty prop;
    private String bookName;

	@FindBy(id = "twotabsearchtextbox")
	private WebElement amazonSearchBar;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement searchButton;

	@FindBy(xpath = "//a[@title='The Alchemist']")
    private WebElement productLink;

    @FindBy(id = "add-to-wishlist-button-submit")
    private WebElement wishList;

    @FindBy(id = "ap_email")
    private WebElement userName;

    @FindBy(id = "ap_password")
    private WebElement password;

    @FindBy(id = "signInSubmit")
    private WebElement loginButton;


    @FindBy(xpath = "//a[@title='The Alchemist'][@class='a-link-normal']")
    private WebElement wishListItem;


    @FindBy(xpath = "//span[@value='Email']")
    private WebElement clickOnEmail;

    @FindBy(xpath = "//span[@value='Share this item']")
    private WebElement shareEmail;

    SAssert sassert = new SAssert();

    //Constructor using Property use
	public AmazonPage(WebDriver driver, IProperty prop){
		this.driver = driver;
		this.prop = prop;
		PageFactory.initElements(driver, this);
	}
	
	
	
	public AmazonPage load(){
		driver.get(prop.getValue(DataEnum.Amazon_url));
		return this;
	}
	
	public AmazonPage loadFromProperty(){
		driver.get(prop.getValue("Amazon_url"));
		return this;
	}
	
	public AmazonPage isLoaded(){
		if(null == DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(amazonSearchBar), driver, 5)){
			throw new FrameworkException("Not able to load Amazon Home page in 5 seconds");
		}
		return this;
	}
	
	public AmazonPage search(){
        amazonSearchBar.sendKeys(prop.getValue(DataEnum.Amazon_searchtext));
        searchButton.click();
        sassert.assertTrue(true, "Search for products");
		return this;
	}

    public AmazonPage goToProductDetailPage(){
        productLink.click();
        sassert.assertTrue(true, "Go to product detail page");
        return this;
    }

    public AmazonPage addWishlist(){
        wishList.click();
        sassert.assertTrue(true, "Add product to wishlist");
        return this;
    }

    public AmazonPage loginToAmazon(){
        userName.sendKeys(prop.getValue(DataEnum.Amazon_username));
        password.sendKeys(prop.getValue(DataEnum.Amazon_password));
        loginButton.click();
        sassert.assertTrue(true, "Login to Amazon");
        return this;
    }

    public String getWishList(){
        String item = wishListItem.getText();
        sassert.assertTrue(true, "WishList has been updated with " +item);

        return item;
    }

    public AmazonPage email(){
        clickOnEmail.click();
        sassert.assertTrue(true, "Email has been sent");
        return this;
    }
}
