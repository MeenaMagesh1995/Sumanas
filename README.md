Amazon Shopping Automation with Selenium
This repository contains an automation script for performing shopping-related tasks on the Amazon India website using Selenium WebDriver in Java. 
The script demonstrates how to:

Open the Amazon website.
Perform a product search.
Filter results based on categories and sort by the newest arrivals.
Sign in to an Amazon account.
Add a product to the cart.
Prerequisites
Before running the project, ensure that you have the following installed:

Java: You need to have JDK (Java Development Kit) installed on your machine.

Download from Oracle JDK or use OpenJDK.
Selenium WebDriver: Selenium provides a suite of tools for automating web browsers.

Download the WebDriver for Chrome or the browser of your choice.
TestNG: TestNG is used for running the tests.

Install TestNG in your IDE or add it as a dependency to your project (e.g., using Maven or Gradle).
ChromeDriver: Ensure that you have Chrome installed, and download the corresponding version of the ChromeDriver.

Project Setup
Clone the Repository:

bash
Copy code
git clone (https://github.com/MeenaMagesh1995/Sumanas.git)
cd amazon-shopping-automation
Install Dependencies:

Maven (for dependency management): If you're using Maven, include the following dependencies in your pom.xml file:

xml
Copy code
<dependencies>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.0.0</version> <!-- Make sure to use the latest version -->
    </dependency>
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.4.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
Set up WebDriver:

Download the ChromeDriver and set the system property in your code (if not done already):
java
Copy code
System.setProperty("webdriver.chrome.driver", "path_to_your_chromedriver");
Code Overview
Key Components of the Code:
Pre-condition Setup (@BeforeMethod):
This section opens the Amazon India homepage, maximizes the browser window, and verifies the page title.

java
Copy code
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
Test Case (@Test):

Searches for "Bags for b" on Amazon.
Selects product filters and sorts results.
Logs product details like discounts and the product's name.
Signs in using a test account and adds a product to the shopping cart.
java
Copy code
@Test()
public void shopping() throws InterruptedException {
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
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    WebElement scroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add to cart']")));
    Actions scrollto = new Actions(driver);
    scrollto.moveToElement(scroll).perform();
    scroll.click();
}
Post-condition (@AfterMethod):
Closes the browser session after the test completes.

java
Copy code
@AfterMethod
public void postCondition() {
    if (driver != null) {
        driver.quit();
    }
}
Running the Test
To run the test case:

Using IDE (e.g., IntelliJ IDEA or Eclipse):

Open the project in your IDE.
Right-click on the Amazon class and select Run.
Using Maven: If you're using Maven, you can run the tests using the following command in the terminal:

bash
Copy code
mvn test
TestNG Reports: TestNG will generate an HTML report after running the test. You can find this report in the test-output folder.

Notes
Ensure to replace the login credentials in the script (email and password) with valid ones for the account you intend to test with, or use mock credentials if not needed.
Explicit Waits: The script uses WebDriverWait for waiting for elements to become visible before performing actions (like clicking buttons).
