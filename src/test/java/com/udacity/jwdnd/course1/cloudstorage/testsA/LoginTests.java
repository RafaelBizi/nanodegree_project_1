package com.udacity.jwdnd.course1.cloudstorage.testsA;

import com.udacity.jwdnd.course1.cloudstorage.TestsConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.*;
import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.PASSWORD;

/**
 * @author RafaelBizi
 * @created 07/07/2021 - 08:27
 * @project nanodegree_project_1
 */
public class LoginTests extends TestsConfiguration {

    private LoginPage loginPage;

    @Test
    @DisplayName("UserLogin testing")
    public void testLoginSuccess() {
        driver.get("http://localhost:" + this.port + "/login");
        loginPage = new LoginPage(driver);
        fillLoginFields();
        loginPage.clickLoginButton();
    }

    public void fillLoginFields() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
    }

}
