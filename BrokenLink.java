package ecommerece;


	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;

	import java.io.IOException;
	import java.net.HttpURLConnection;
	import java.net.URL;
	import java.util.List;

	public class BrokenLink {

	    public static void main(String[] args) {
	        // Initialize WebDriver (ensure you have the correct ChromeDriver version)
	        ChromeOptions options = new ChromeOptions();
	        //options.addArguments("--headless"); // run in headless mode (optional)
	        WebDriver driver = new ChromeDriver(options);
	        
	        // Navigate to the eCommerce page
	        driver.get("https://www.amazon.in/");
	        driver.manage().window().maximize();

	        // Get all anchor links (a tags) on the page
	        List<WebElement> links = driver.findElements(By.tagName("link"));

	        for (WebElement link : links) {
	            String url = link.getAttribute("href");
	            
	            if (url != null && !url.isEmpty()) {
	                try {
	                    // Check if the link is broken
	                    if (isBrokenLink(url)) {
	                        System.out.println("Broken Link: " + url);
	                    }
	                } catch (IOException e) {
	                    System.out.println("Error checking link: " + url);
	                }
	            }
	        }

	        driver.quit(); // Close the browser
	    }

	    // Function to check if the link is broken
	    public static boolean isBrokenLink(String urlString) throws IOException {
	        URL url = new URL(urlString);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("HEAD"); // Send HEAD request (no body, only headers)

	        // Set a timeout (in milliseconds)
	        connection.setConnectTimeout(5000);
	        connection.setReadTimeout(5000);

	        // Get the HTTP response code
	        int responseCode = connection.getResponseCode();
	        
	        // Check for broken link (e.g., 404 or 500 response)
	        if (responseCode >= 400) {
	            return true; // Broken link
	        }
	        return false; // Valid link
	    }
	}



