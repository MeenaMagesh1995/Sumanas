package ecommerece;


	import java.time.Duration;

	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;

	public class Index {
		public ChromeDriver driver; 
		
		@BeforeMethod
		public void preCondition() {

		
			 driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.get("https://www.amazon.in/");
			driver.manage().window().maximize();
			
		}
		
		@AfterMethod()
		public void postCondition() {
			driver.close();

	}}


