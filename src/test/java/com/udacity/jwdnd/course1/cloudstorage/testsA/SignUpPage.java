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
public class SignUpPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstName;

    @FindBy(id = "inputLastName")
    private WebElement lastName;

    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "login-link")
    private WebElement loginLink;

    @FindBy(id = "success-msg")
    private WebElement successText;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getFirstName(){
        return this.firstName.getAttribute("value");
    }

    public String getLastName(){
        return this.lastName.getAttribute("value");
    }

    public String getUsername(){
        return this.username.getAttribute("value");
    }

    public String getPassword(){
        return this.password.getAttribute("value");
    }

    public void setFirstName(String firstName){
        this.firstName.sendKeys(firstName);
    }

    public void setLastName(String lastName){
        this.lastName.sendKeys(lastName);
    }

    public void setUsername(String username){
        this.username.sendKeys(username);
    }

    public void setPassword(String password){
        this.password.sendKeys(password);
    }

    public void clickSubmit(){
        submitButton.click();
    }

    public void clickLoginLink(){
        loginLink.click();
    }

}
