package com.tmb.tests;

import java.util.Map;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;

import com.tmb.annotations.FrameworkAnnotation;
import com.tmb.driver.DriverManager;
import com.tmb.enums.CategoryType;
import com.tmb.pages.AmazonHomePage;

/**
 * Contains the test cases of Amazon website for practice.
 * 
 * <pre>
 * <b>
 * <a href=
"https://www.youtube.com/channel/UC6PTXUHb6j4Oxf0ccdRI11A">Testing Mini Bytes Youtube channel</a>
 * </b>
 * </pre>
 * 
 * Jan 22, 2021
 * 
 * @author Amuthan Sakthivel
 * @version 1.0
 * @since 1.0
 */
public final class AmazonDemoTest extends BaseTest {

	/**
	 * Private constructor to avoid external instantiation
	 */
	private AmazonDemoTest() {
	}

	/**
	 * Test Name mentioned here should match the column name "testname" in excel
	 * sheet.This is mandatory to run this test. Otherwise it will be ignored.
	 * <p>
	 * The match has to be there in both of the RUNMANAGER and TESTDATA sheet. Set
	 * the authors who have the created the test which will be logged to the
	 * reports. Set the category which this particular test case belongs to
	 * 
	 * @author Amuthan Sakthivel Jan 22, 2021
	 * @param data HashMap containing all the values of test data needed to run the
	 *             tests
	 */
	@Test
	@FrameworkAnnotation(author = { "Amuthan", "Sachin" }, category = { CategoryType.REGRESSION,
			CategoryType.MINIREGRESSION })
	public void amazonTest(Map<String, String> data) {

		// Save cookies from a manual login session
		Set<Cookie> cookies = DriverManager.getDriver().manage().getCookies();
		for (Cookie cookie : cookies) {
			System.out.println(cookie.getName() + "=" + cookie.getValue());
		}

		// Load cookies into a new session to avoid CAPTCHA
		for (Cookie cookie : cookies) {
			DriverManager.getDriver().manage().addCookie(cookie);
		}
		DriverManager.getDriver().navigate().refresh(); // Reload the page

		String title = new AmazonHomePage().clickHamburger().clickComputer().clickOnSubMenuItem(data.get("menutext"))
				.getTitle();
		Assertions.assertThat(title).isNotNull();
	}

}
