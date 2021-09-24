package com.udacity.jwdnd.course1.cloudstorage.testsA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author RafaelBizi
 * @created 07/07/2021 - 20:06
 * @project nanodegree_project_1
 */
public class LoginPage {

    @FindBy(id = "signup-link")
    private WebElement signupLink;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    public LoginPage (WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public String checkLoginPageExists(){
        return this.signupLink.getText();
    }

    public String getUsername(){
        return this.username.getAttribute("value");
    }

    public String getPassword(){
        return this.password.getAttribute("value");
    }

    public void setUsername(String username){
        this.username.sendKeys(username);
    }

    public void setPassword(String password){
        this.password.sendKeys(password);
    }

    public void clickLoginButton(){
        this.submitButton.click();
    }


}
