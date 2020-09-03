package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteTests {

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
	public void crudNotePath() throws InterruptedException {
		// creates a note, and verifies it is displayed.
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

		String noteTitle = "mytitle";
		String noteDesc = "mydesc";
		homePage.createNewNote(noteTitle,noteDesc);

		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		Assertions.assertEquals("Success",resultPage.getSuccessMessage());

		driver.get("http://localhost:" + this.port + "/home");
		homePage.goToNotesTab();
		Thread.sleep(1000);
		Assertions.assertEquals(noteTitle,homePage.getFirstNoteTitle());
		Assertions.assertEquals(noteDesc,homePage.getFirstNoteDescription());

		//edits an existing note and verifies that the changes are displayed.
		String secondTitle = "title2";
		String secondDescription = "desc2";
		homePage.editFirstNote(secondTitle,secondDescription);
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("Success",resultPage.getSuccessMessage());

		driver.get("http://localhost:" + this.port + "/home");
		homePage.goToNotesTab();
		Thread.sleep(1000);
		Assertions.assertEquals(secondTitle,homePage.getFirstNoteTitle());
		Assertions.assertEquals(secondDescription,homePage.getFirstNoteDescription());

		//deletes a note and verifies that the note is no longer displayed.
		homePage.deleteFirstNote(driver);
		homePage.goToNotesTab();
		Thread.sleep(1000);
		Assertions.assertTrue(homePage.notesDoesNotExist());
	}


}
