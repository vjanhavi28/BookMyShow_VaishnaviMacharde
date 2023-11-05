package Assessment;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Bookmyshow {

	public static void main(String[] args) throws Throwable {

		String key = "webdriver.chrome.driver";
		String value = "src/main/resources/chromedriver.exe";
		System.setProperty(key, value);

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		try {
			// Step 1: Open https://in.bookmyshow.com/explore/home/
			driver.get("https://in.bookmyshow.com/explore/home/");

			// Step 2: Select Bengaluru as the city
			driver.findElement(By.xpath("//span[text()='Bengaluru']")).click();

			// Step 3: Click on Sign In
			WebElement signInButton = driver.findElement(By.xpath("//div[text()='Sign in']"));
			signInButton.click();

			// Step 4: Click on Continue with Email
			WebElement continueWithEmailButton = driver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div/div/div[2]/div/div[1]/div/div[2]/div/div"));	
			continueWithEmailButton.click();
			

			// Step 5: Enter selauto@yopmail.com and click on continue
			WebElement emailInput = driver.findElement(By.xpath("//input[@id='emailId']"));
			emailInput.sendKeys("vaishnavimacharde.pune@yahooz.xxl.st");

			Thread.sleep(10000);
			WebElement continueButton = driver.findElement(By.xpath("//button[text()='Continue']"));
			continueButton.click();
			Thread.sleep(10000);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_T);
			String originalHandle = driver.getWindowHandle();

			System.out.println(driver.getWindowHandles().size());

			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(originalHandle)) {
					driver.switchTo().window(handle);
					System.out.println("Switch to new window");
					break;
				}
			}

			// Step 6: Go to Yopmail.com
			driver.get("https://yopmail.com");

			// Step 7: Type selauto@yopmail.com and access inbox
			WebElement yopmailInput = driver.findElement(By.className("ycptinput"));
			yopmailInput.sendKeys("vaishnavimacharde.pune@yahooz.xxl.st");

			WebElement checkInboxButton = driver.findElement(By.xpath("//*[@id=\"refreshbut\"]/button/i"));
			checkInboxButton.click();

			// click on refresh button
			WebElement refresh = driver.findElement(By.xpath("//button[@id='refresh']"));
			refresh.click();

			WebElement frame = driver.findElement(By.xpath("//iframe[@id='ifmail']"));
			driver.switchTo().frame(frame);

			// Step 8: Locate the latest email from BookMyShow and fetch the OTP
			WebElement title = driver.findElement(By.xpath("//*[contains(text(),'is your BookMyShow OTP')]"));
			String subj = title.getText();
			System.out.println(subj);

			String Otp = subj.substring(0, subj.indexOf(" ")).trim();

			System.out.println(Otp);

			// Step 9: Come back to sign in Page and enter the OTP
			driver.switchTo().window(originalHandle);
			List<WebElement> typeotp = driver.findElements(
					By.xpath("//div[text()='Verify your Email Address']/following-sibling::div[2]/input"));
			for (int i = 0; i < Otp.length(); i++) {
				typeotp.get(i).sendKeys(String.valueOf(Otp.charAt(i)));
			}

			// Step 10: Validate user is successfully signed in and "Hi, Guest" is displayed
			WebElement greetingText = driver.findElement(By.xpath("//img[@alt='Profile']/following-sibling::span"));
			Assert.assertTrue(greetingText.isDisplayed());
			System.out.println(greetingText.getText());
			Assert.assertEquals(true, greetingText.getText().contains("Hi, Guest"));

		}

		catch (Exception e) {
			System.out.println("Execption : " + e.toString());
		} finally {
			driver.quit();
		}

	}
}