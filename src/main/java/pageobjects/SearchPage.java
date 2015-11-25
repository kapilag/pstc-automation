package pageobjects;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import omelet.common.ExpectedConditionExtended;
import omelet.data.IProperty;
import omelet.driver.DriverUtility;
import omelet.exception.FrameworkException;
import omelet.testng.support.SAssert;

public class SearchPage {
	public SearchPage(WebDriver driver, IProperty prop) {
		this.driver = driver;
		this.prop = prop;
		PageFactory.initElements(driver, this);
	}

	private WebDriver driver;
	private IProperty prop;
	@FindBy(xpath = ".//*[@id='rso']/div[1]/div/div/div[2]/div/b")
	private WebElement time;

	public SearchPage isLoaded() {
		if (null == DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(time), driver, 5)) {
			throw new FrameworkException("Not able to load Search page in 5 seconds");
		}
		return this;
	}

	public String getConvertedTime() {
		return time.getText();
	}

	public void fileWrite(String time) throws IOException {
		FileWriter out = new FileWriter("output.txt", true);
		out.write(prop.getValue(DataEnum.Google_Timezone) + "-" + time + "\n");
		out.close();
	}

	public void verifyTime(String fileName) throws IOException {
		SAssert sassert = new SAssert();
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = null;
		String lines = null;
		while ((line = bufferedReader.readLine()) != null) {
			lines=lines+line;
		
		}
		sassert.assertEquals(lines.contains(prop.getValue(DataEnum.Google_Timezone)), true);
		sassert.assertAll();
	}
}
