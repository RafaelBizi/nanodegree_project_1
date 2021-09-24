package com.udacity.jwdnd.course1.cloudstorage.testsB;

import com.udacity.jwdnd.course1.cloudstorage.TestsConfiguration;
import com.udacity.jwdnd.course1.cloudstorage.testsA.LoginPage;
import org.junit.jupiter.api.*;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.*;

/**
 * @author RafaelBizi
 * @created 06/07/2021 - 21:16
 * @project nanodegree_project_1
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CredentialCRUDTests extends TestsConfiguration {

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
    @DisplayName("adding a new Credential")
    public void addNewCredentialTest() throws InterruptedException {
        Thread.sleep(300);
        homePage.openCredentialsTab();
        Thread.sleep(300);
        Assertions.assertEquals(true, homePage.checkCredentialTabIsOpened());
        Thread.sleep(300);
        homePage.getAddCredentialBtn().click();
        Thread.sleep(300);
        homePage.fillNewCredentialFields(URL_CREDENTIAL, USERNAME_CREDENTIAL, PASSWORD_CREDENTIAL);
        Thread.sleep(300);
        homePage.getCredentialSubmit().click();
        Thread.sleep(300);
        Assertions.assertEquals(true, homePage.checkCredentialSuccessResultIsDisplayed());
    }

    @Test
    @Order(2)
    @DisplayName("Checking if the new credential was added properly")
    public void confirmAddedCredentialTest() throws InterruptedException {
        driver.get("http://localhost:" + port + "/home");
        Thread.sleep(300);
        homePage.openCredentialsTab();
        Thread.sleep(300);
        Assertions.assertEquals(true, homePage.checkCredentialEditButtonIsDisplayed());
    }

}
