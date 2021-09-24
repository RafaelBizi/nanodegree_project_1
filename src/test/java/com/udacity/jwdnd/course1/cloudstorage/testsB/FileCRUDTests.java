package com.udacity.jwdnd.course1.cloudstorage.testsB;

import com.udacity.jwdnd.course1.cloudstorage.TestsConfiguration;
import com.udacity.jwdnd.course1.cloudstorage.config.FileStoreConfig;
import com.udacity.jwdnd.course1.cloudstorage.mapping.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.testsA.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.testsA.LoginTests;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author RafaelBizi
 * @created 06/07/2021 - 21:17
 * @project nanodegree_project_1
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FileCRUDTests extends TestsConfiguration {

    @Autowired
    FileMapper fileMapper;

    private LoginPage loginPage;
    private HomePage homePage;

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
    @DisplayName("adding a new File")
    public void addNewFileTest() throws InterruptedException, IOException {
        FileService fileService = new FileService(new FileStoreConfig(), fileMapper);
        Resource resource = fileService.loadFile(FILENAME);
        String url = resource.getURL().getPath();
        homePage.chooseFile(url);
        homePage.uploadFile();
        Assertions.assertEquals(true, homePage.checkFileSuccessResultIsDisplayed());
        List<File> allFilesByUser = fileService.getAllFilesByUser(1);
        Assertions.assertEquals(false, allFilesByUser.isEmpty());
    }

    @Test
    @Order(2)
    @DisplayName("view the new added File")
    public void viewNewFileTest() throws InterruptedException {
        Thread.sleep(300);
        homePage.viewFile();
        Set<String> windowHandles = driver.getWindowHandles();
        assertEquals(2, windowHandles.size());
    }

    @Test
    @Order(3)
    @DisplayName("delete the new added File")
    public void deleteNewFileTest() throws InterruptedException {
        Thread.sleep(300);
        homePage.deleteFile();
        Thread.sleep(300);
        homePage.confirmFileDelete();
        FileService fileService = new FileService(new FileStoreConfig(), fileMapper);
        List<File> allFilesByUser = fileService.getAllFilesByUser(1);
        Assertions.assertEquals(0, allFilesByUser.size());
    }
}
