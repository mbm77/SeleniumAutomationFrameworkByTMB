package com.tmb.pages;

import org.openqa.selenium.By;

import com.tmb.enums.WaitStrategy;

public final class OrangeHRMHomePage extends BasePage {

	private final By dashboard = By.xpath("//h6[text()='Dashboard']");
	private final By dropdownIcon = By
			.xpath("//ul/li[contains(@class,'oxd-userdropdown')]");
	private final By linkLogout = By.xpath("//ul/li/a[text()='Logout']");

	public OrangeHRMHomePage clickWelcome() {
		click(dashboard, WaitStrategy.PRESENCE, "Dashboard");
		return this;
	}

	public OrangeHRMHomePage clickUserDopdown() {
		click(dropdownIcon, WaitStrategy.PRESENCE, "User Dropdown");
		return this;
	}

	public OrangeHRMLoginPage clickLogout() {
		click(linkLogout, WaitStrategy.CLICKABLE, "Logout button");
		return new OrangeHRMLoginPage();
	}

	// wait.until(d->d.findElement(link_logout).isEnabled()); //Java 8 way
}
