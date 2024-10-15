package ecommerece;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Amazon extends Index {
	
		// TODO Auto-generated method stub
	@BeforeMethod
	public void preCondition() {

	
		 driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.amazon.in/");
		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		driver.manage().window().maximize();
		
	}
	@Test()
	public  void shopping() throws InterruptedException {
	
	    driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Bags for b");
		Thread.sleep(2000);
		driver.findElement(By.className("s-suggestion-container")).click();
		String Totalresults = driver.findElement(By.xpath("//div[@class='sg-col-inner']")).getText();
		System.out.println(Totalresults);
		driver.findElement(By.linkText("Skybags")).click();
		driver.findElement(By.linkText("American Tourister")).click();
		driver.findElement(By.xpath("//span[@class='a-button-text a-declarative']")).click();
		driver.findElement(By.linkText("Newest Arrivals")).click();
		String a = driver.findElement(By.xpath("(//span[text()='Skybags'])[2]")).getText();
		System.out.println(a);
		String b = driver.findElement(By.xpath("//span[text()='(32% off)']")).getText();
		System.out.println(b);
		String title = driver.getTitle();
		 System.out.println(title);
		 driver.findElement(By.xpath("//span[text()='Hello, sign in']")).click();
		 driver.findElement(By.name("email")).sendKeys("8270344755");
		 driver.findElement(By.id("continue")).click();
		 driver.findElement(By.name("password")).sendKeys("Meena@1995");
		 driver.findElement(By.id("signInSubmit")).click();
		 //Thread.sleep(3000);
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 WebElement scroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add to cart']")));
		 Actions scrollto = new Actions(driver);
		 scrollto.moveToElement(scroll).perform();
		 scroll.click();
	}
		 @AfterMethod()
			public void postCondition() {
			 if (driver != null) {
		            driver.quit();
		 
		
		
		

	}

}}

