package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CredentialTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}


	@Test
	public void crudCredentialPath() throws InterruptedException {
		// creates a credential, and verifies it is displayed.
		driver.get("http://localhost:" + this.port + "/signup");

		String firstName = "John";
		String lastName = "Doe";
		String username = "jdoe";
		String password = "JDpassword";
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName,lastName,username,password);

		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username,password);

		driver.get("http://localhost:" + this.port + "/home");

		HomePage homePage = new HomePage(driver);

		String credUrl = "http://example.com/credentialurl";
		String credUsername = "myusername";
		String credPassword = "mypassword";
		homePage.createNewCredential(credUrl,credUsername,credPassword);

		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		Assertions.assertEquals("Success",resultPage.getSuccessMessage());

		driver.get("http://localhost:" + this.port + "/home");
		homePage.goToCredentialsTab();
		Thread.sleep(1000);
		Assertions.assertEquals(credUrl,homePage.getFirstCredentialUrl());
		Assertions.assertEquals(credUsername,homePage.getFirstCredentialUsername());
		Assertions.assertEquals(credPassword,homePage.getFirstCredentialPassword());

		//edits an existing credential and verifies that the changes are displayed.
		String secondUrl = "http://example.com/second";
		String secondUsername = "username2";
		String secondPassword = "pass2";
		homePage.editDirstCredential(secondUrl,secondUsername,secondPassword);
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("Success",resultPage.getSuccessMessage());

		driver.get("http://localhost:" + this.port + "/home");
		homePage.goToCredentialsTab();
		Thread.sleep(1000);
		Assertions.assertEquals(secondUrl,homePage.getFirstCredentialUrl());
		Assertions.assertEquals(secondUsername,homePage.getFirstCredentialUsername());
		Assertions.assertEquals(secondPassword,homePage.getFirstCredentialPassword());

		//deletes a credential and verifies that the note is no longer displayed.
		homePage.deleteFirstCredential(driver);
		homePage.goToCredentialsTab();
		Thread.sleep(1000);
		Assertions.assertTrue(homePage.credentialsDoesNotExist());

	}


}
