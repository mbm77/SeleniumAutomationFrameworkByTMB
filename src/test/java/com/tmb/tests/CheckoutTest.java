package com.tmb.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckoutTest {
	WebDriver driver;

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@DataProvider(name = "checkoutData")
	public Object[][] checkoutDataProvider() {
		return new Object[][] { { "validUser", "validPass", "validAddress", "creditCard", "validCoupon", "Success" },
				{ "validUser", "validPass", "validAddress", "expiredCard", "validCoupon", "Failure" },
				{ "vaidUser", "validPass", "invalidAddress", "creditCard", "validCoupon", "Address Error" },
				{ "validUser", "validPass", "validAddress", "creditCard", "invalidCoupon", "Coupon Error" },
				{ "", "", "validAddress", "creditCard", "validCoupon", "Login Required" } };
	}

	@Test(dataProvider = "checkoutData")
	public void testCheckout(String username, String password, String address, String paymentMethod, String coupon,
			String expectedOutcome) {
		driver.get("https://example-ecommerce.com");
		if (!username.isEmpty() && !password.isEmpty()) {
			driver.findElement(By.id("loginButton")).click();
			driver.findElement(By.id("username")).sendKeys(username);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("submitLogin")).click();
		}

		driver.findElement(By.id("addToCart")).click();
		driver.findElement(By.id("cartIcon")).click();
		driver.findElement(By.id("checkoutButton")).click();
		WebElement addressField = driver.findElement(By.id("address"));
		addressField.clear();
		addressField.sendKeys(address);

		if (!coupon.isEmpty()) {
			driver.findElement(By.id("coupnCode")).sendKeys(coupon);
			driver.findElement(By.id("applyCoupon")).click();
		}

		if (paymentMethod.equals("creditCard")) {
			driver.findElement(By.id("creditCardOptin")).click();
			driver.findElement(By.id("cardNumber")).sendKeys("411111111111");
			driver.findElement(By.id("expiryDate")).sendKeys("12/26");
			driver.findElement(By.id("cvv")).sendKeys("123");
		} else if (paymentMethod.equals("expiredcard")) {
			driver.findElement(By.id("creditCardOption")).click();
			driver.findElement(By.id("cardNumber")).sendKeys("411111111111");
			driver.findElement(By.id("expiryDate")).sendKeys("12/20");
			driver.findElement(By.id("cvv")).sendKeys("123");
		}

		driver.findElement(By.id("placeOrder")).click();
		WebElement messageElement = driver.findElement(By.id("orderStatus"));
		String actualOutcome = messageElement.getText();
		Assert.assertTrue(actualOutcome.contains(expectedOutcome), "Check out test failed");

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
