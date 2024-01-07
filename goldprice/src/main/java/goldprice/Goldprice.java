package goldprice;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class Goldprice {

	WebDriver driver;
	public void openWindow() {
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--disable-extensions");
//		options.addArguments("--disable-gpu");
//		options.addArguments("--no-sandbox");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
	   
		driver.get("https://www.livechennai.com/gold_silverrate.asp");
		driver.manage().window().maximize();
	}

	public Double[] table(String gdate) throws InterruptedException {
		
		//Actions a = new Actions(driver);
		//a.sendKeys(Keys.PAGE_DOWN).build().perform();
		//div[@id='gold-tit']/following-sibling::*/tbody/tr/td/*[contains(text(),'25')]
		openWindow();
		Double[] gold = {0.0,0.0};
		try {
		String day_24k="//div[@id='gold-tit']/following-sibling::*/tbody/tr/td[1]/*[starts-with(normalize-space(),'"+ gdate +"')]/ancestor::tr/td[2]";
		String day_22k="//div[@id='gold-tit']/following-sibling::*/tbody/tr/td[1]/*[starts-with(normalize-space(),'"+ gdate +"')]/ancestor::tr/td[4]";
		WebElement price_24k=driver.findElement(By.xpath(day_24k));
		WebElement price_22k=driver.findElement(By.xpath(day_22k));
		
		gold[0] = (Double.parseDouble(price_24k.getText()));
		gold[1] = (Double.parseDouble(price_22k.getText()));
		System.out.println("Gold Rate on "+ gdate + "\n for 24K is "+gold[0]+ "\n for 22K is "+gold[1]);
		
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			System.out.println(gdate +"-" +" no price available");
			System.out.println("Exception occured");
		}
		catch(Exception e) {
			System.out.println("Exception occured "+ e);
		}
		
		return gold;
	//	WebElement day25=driver.findElement(By.xpath("//div[@id='gold-tit']/following-sibling::*/tbody/tr[6]/td[1]"));
	//	System.out.println("..");
	//	WebElement day19=driver.findElement(By.xpath("//div[@id='gold-tit']/following-sibling::*/tbody/tr[12]/td[1]"));
	//	System.out.println("..");
	/*	//scroll up a page
		a.sendKeys(Keys.PAGE_UP).build().perform();
		
		
				JavascriptExecutor js=(JavascriptExecutor)driver;
		
	WebElement table=driver.findElement(By.xpath("https://www.livechennai.com/gold_silverrate.asp"));
		js.executeScript("arguments[0].scrollIntoView()", table);
	/9+*/
	}
	//	pu
		
		public void textbox() {
	WebElement workfromhome=driver.findElement(By.xpath("//a[@href='https://www.livechennai.com/work_from_home_genuine.asp']"));
	workfromhome.click();
	
	
		}
//	Thread.sleep(2000);
/*	Actions a = new Actions(driver);
			a.sendKeys(Keys.PAGE_DOWN).build().perform();
			a.sendKeys(Keys.PAGE_DOWN).build().perform();
	WebElement name=driver.findElement(By.xpath("(//div[.='Your answer']//preceding-sibling::input[@type='text'])[1]"));
	
	name.sendKeys("abi");
	
*/	
		public void exit() {
			driver.quit();
		}
	
	
	
	
}

