package goldprice;

import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Goldprice {

	WebDriver driver;
	public void openWindow() {
		ChromeOptions options = new ChromeOptions();
		
		WebDriverManager.chromedriver().setup();
		String remote_text = System.getProperty("remote");
		
		boolean remote=false;
		if (remote_text==null || remote_text.contains("f") || remote_text.contains("n") || remote_text.contains("0")) {
			remote=false;
		}else {
			remote=true;
		}
		
		if(!remote) {
		try{
			System.out.println("Initializing Local Driver");
			driver = new ChromeDriver(options);
			System.out.print("Local Driver initialized");
		}catch(Exception e) {
			System.out.print("Exception occured" +e);
		}
		}else {
		try {
			System.out.print("Initializing Remote Driver");
			options.addArguments("--headless");
			URL gridURL = new URL("http://hari-h110:4444/wd/hub");
			driver = new RemoteWebDriver(gridURL,options);
			System.out.print("Remote Driver initialized");
		}catch(Exception e) {
			System.out.print("Exception occured on Remote driver" +e);
		}
		}
		
		driver.get("https://www.livechennai.com/gold_silverrate.asp");
		driver.manage().window().maximize();
	}

	public String[] table(String gdate) throws InterruptedException {
		
		openWindow();
		String[] gold = new String [2];
		try {
		String day24k="//table[contains(@class,'gold-rates')]/tbody//td[contains(text(),'"+gdate+"')]/following-sibling::td[1]";
		String day22k="//table[contains(@class,'gold-rates')]/tbody//td[contains(text(),'"+gdate+"')]/following-sibling::td[3]";
		WebElement price24k=driver.findElement(By.xpath(day24k));
		WebElement price22k=driver.findElement(By.xpath(day22k));
		
		gold[0] = price24k.getText();
		gold[1] = price22k.getText();
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
	}

		
		public void textbox() {
	WebElement workfromhome=driver.findElement(By.xpath("//a[@href='https://www.livechennai.com/work_from_home_genuine.asp']"));
	workfromhome.click();
		}

		
		public void exit() {
			driver.quit();
		}
	
	
	
	
}

