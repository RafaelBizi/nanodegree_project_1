package com.udacity.jwdnd.course1.cloudstorage.testsB;

import com.udacity.jwdnd.course1.cloudstorage.TestsConfiguration;
import com.udacity.jwdnd.course1.cloudstorage.mapping.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.testsA.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.testsA.LoginTests;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.*;

/**
 * @author RafaelBizi
 * @created 06/07/2021 - 21:16
 * @project nanodegree_project_1
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoteCRUDTests extends TestsConfiguration {

	private LoginPage loginPage;
	private HomePage homePage;

	@Autowired
	NoteMapper noteMapper;

	@BeforeEach
	public void doLoginBeforeEach() {
		loginPage = new LoginPage(driver);
		driver.get("http://localhost:" + port + "/login");
		loginPage.setUsername(USERNAME);
		loginPage.setPassword(PASSWORD);
		loginPage.clickLoginButton();
		homePage = new HomePage(driver);
	}

	@Test
	@Order(1)
	@DisplayName("adding a new Note")
	public void addNewNoteTest() throws InterruptedException {
		driver.get("http://localhost:" + port + "/home");
		Thread.sleep(300);
		homePage.openNotesTab();
		Thread.sleep(300);
		Assertions.assertEquals(true, homePage.checkNoteTabIsOpened());
		Thread.sleep(300);
		homePage.getAddNewNoteBtn().click();
		Thread.sleep(300);
		Assertions.assertEquals(true, homePage.checkNoteModalIsOpened());
		Thread.sleep(300);
		homePage.fillNoteFields(TITLE_NOTE, DESCRIPTION_NOTE);
		Thread.sleep(300);
		homePage.getNoteSubmit().click();
		Thread.sleep(300);
		Assertions.assertEquals(true, homePage.checkNoteSuccessResultIsDisplayed());
	}

	@Test
	@Order(2)
	@DisplayName("Checking if the new note was added properly")
	public void confirmAddedNoteTest() throws InterruptedException {
		driver.get("http://localhost:" + port + "/home");
		Thread.sleep(300);
		homePage.openNotesTab();
		Thread.sleep(300);
		Assertions.assertEquals(true, homePage.checkNoteEditButtonIsDisplayed());
	}

	@Test
	@Order(3)
	@DisplayName("Editing note test")
	public void updateNoteTest() throws InterruptedException {
		driver.get("http://localhost:" + port + "/home");
		Thread.sleep(300);
		homePage.openNotesTab();
		Thread.sleep(300);
		homePage.editNote();
		Thread.sleep(2000);
		homePage.fillNoteEditFields(TITLE_NOTE_UPDATE, DESCRIPTION_NOTE_UPDATE);
		Thread.sleep(300);
		homePage.getNoteEditSaveChanges().click();
		Thread.sleep(300);
		Assertions.assertEquals(true, homePage.checkNoteUpdatedSuccess());
	}

	@Test
	@Order(4)
	@DisplayName("Deleting note test")
	public void deleteNoteTest() throws InterruptedException {
		driver.get("http://localhost:" + port + "/home");
		Thread.sleep(300);
		homePage.openNotesTab();
		Thread.sleep(300);
		homePage.deleteNote();
		Thread.sleep(300);
		homePage.confirmNoteDelete();
		Thread.sleep(300);
		NoteService noteService = new NoteService(noteMapper);
		noteService.getAllNotesByUserId(1);
		homePage.checkNoteDeleteResultMsgIsDisplayed();
		List<Note> allNotesByUser = noteService.getAllNotesByUserId(1);
		Assertions.assertEquals(0, allNotesByUser.size());
	}
}
