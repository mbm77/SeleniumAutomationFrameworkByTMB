package com.tmb.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
	
	@Test(dataProvider = "loginData")
	public void testLogin(String username,String password, String expectedResult) {
		System.out.println(username+" : "+password);
	}
	
	@DataProvider(name="loginData")
	public Object[][] loginDataProvider() {
		return new Object[][] {
			
			{"validUser","validPass", "Success"},
			{"invalidUser","validPass", "Failure"},
			{"validUser","invalidPass", "Failure"},
			{"","validPass","Failure"},
			{"validPass","","Failure"}
		};
	}
}



