package com.tmb.pages;

import static com.tmb.enums.LogType.PASS;
import static com.tmb.reports.FrameworkLogger.log;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tmb.driver.DriverManager;
import com.tmb.enums.WaitStrategy;
import com.tmb.factories.ExplicitWaitFactory;

public class BasePage {

	/**
	 * Locates element by given wait strategy, performs the clicking operation on
	 * webelement and writes the pass even to the extent report.
	 * 
	 * @author Amuthan Sakthivel Jan 22, 2021
	 * @param by           By Locator of the webelement
	 * @param waitstrategy Strategy to find webelement. Known strategies
	 *                     {@link com.tmb.enums.WaitStrategy}
	 * @param elementname  Name of the element that needs to be logged in the
	 *                     report.
	 */
	protected void click(By by, WaitStrategy waitstrategy, String elementname) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		element.click();
		log(PASS, elementname + " is clicked");

	}

	Actions actions = new Actions(DriverManager.getDriver());

	protected void actionClick(By by, WaitStrategy waitstrategy, String elementname) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		actions.moveToElement(element).click().perform();
		log(PASS, elementname + " is clicked");

	}

	JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

	protected void jsClick(By by, WaitStrategy waitstrategy, String elementname) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		js.executeScript("arguments[0].click()", element);
		log(PASS, elementname + " is clicked");

	}

	@SuppressWarnings("deprecation")
	protected String getData(By by, WaitStrategy waitstrategy) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		return element.getAttribute("src");
	}

	/**
	 * Locates element by given wait strategy, sends the value to located webelement
	 * and writes the pass even to the extent report.
	 * 
	 * @author Amuthan Sakthivel Jan 22, 2021
	 * @param by           By Locator of the webelement
	 * @param value        value to be send the text box
	 * @param waitstrategy Strategy to find webelement. Known strategies
	 *                     {@link com.tmb.enums.WaitStrategy}
	 * @param elementname  Name of the element that needs to be logged in the
	 *                     report.
	 */
	protected void sendKeys(By by, String value, WaitStrategy waitstrategy, String elementname) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		element.sendKeys(value);
		log(PASS, value + " is entered successfully in " + elementname);
	}

	/**
	 * Return page title of webpage in String
	 * 
	 * @author Amuthan Sakthivel Jan 22, 2021
	 * @return Page title of the webpage where the selenium is currently
	 *         interacting.
	 */
	protected String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}

}
