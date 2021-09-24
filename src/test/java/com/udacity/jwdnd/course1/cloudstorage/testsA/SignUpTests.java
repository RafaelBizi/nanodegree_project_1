package com.udacity.jwdnd.course1.cloudstorage.testsA;

import com.udacity.jwdnd.course1.cloudstorage.TestsConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.*;

/**
 * @author RafaelBizi
 * @created 07/07/2021 - 08:27
 * @project nanodegree_project_1
 */
public class SignUpTests extends TestsConfiguration {

    private SignUpPage signUpPage;

    @Test
    @DisplayName("UserSignUp testing")
    public void testSignUpSuccess() {
        driver.get("http://localhost:" + port + "/signup");
        signUpPage = new SignUpPage(driver);
        fillSignUpFields();
        signUpPage.clickSubmit();
        Assertions.assertEquals(true, driver.findElement(By.id("success-msg")).isDisplayed());
    }

    public void fillSignUpFields() {
        signUpPage.setFirstName(FIRST_NAME);
        signUpPage.setLastName(LAST_NAME);
        signUpPage.setUsername(USERNAME);
        signUpPage.setPassword(PASSWORD);
    }

}
