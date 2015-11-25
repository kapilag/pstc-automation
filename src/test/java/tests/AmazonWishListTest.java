package tests;

import omelet.data.IProperty;
import omelet.data.driverconf.IBrowserConf;
import omelet.testng.support.SAssert;
import org.testng.annotations.Test;
import pageobjects.PageObjectFactory;

public class AmazonWishListTest {
	
	SAssert sassert = new SAssert();

	@Test(description = "verify if Selenium title is as expected", enabled = true,dataProvider="XmlData")
	public void verifyAmazonWishList(IBrowserConf browserConf, IProperty prop) {
		
		System.out.println(browserConf.getCapabilities().getPlatform());
		PageObjectFactory pof = new PageObjectFactory(browserConf, prop);
		//Open Amazon
		pof.amazonPage().load();

        //Search for Books
        pof.amazonPage().search();
        //Go to Product Deatail
        pof.amazonPage().goToProductDetailPage();

        //Add to wishlist
        pof.amazonPage().addWishlist();

        //Login
        pof.amazonPage().loginToAmazon();

        //Verify items in wishlist
        String WishList = pof.amazonPage().getWishList();

	}
	

}
