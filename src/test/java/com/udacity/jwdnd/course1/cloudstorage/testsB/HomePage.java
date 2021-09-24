package com.udacity.jwdnd.course1.cloudstorage.testsB;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author RafaelBizi
 * @created 08/07/2021 - 08:40
 * @project nanodegree_project_1
 */
public class HomePage {

    @FindBy(id = "logoutBtn")
    private WebElement logoutButton;

    @FindBy(id = "uploadBtn")
    private WebElement fileUpload;

    @FindBy(id = "fileUpload")
    private WebElement chooseFile;

    @FindBy(id = "viewBtn")
    private WebElement viewBtn;

    @FindBy(id = "deleteFileBtn")
    private WebElement deleteBtn;

    @FindBy(id = "delFileRef")
    private WebElement confirmFileDeletation;

    @FindBy(id = "addNoteBtn")
    private WebElement addNewNoteBtn;

    @FindBy(id = "editNoteBtn")
    private WebElement noteEditBtn;

    @FindBy(id = "editCredentialBtn")
    private WebElement credentialEditBtn;

    @FindBy(id = "deleteNoteBtn")
    private WebElement deleteNoteBtn;

    @FindBy(id = "addCredentialBtn")
    private WebElement addCredentialBtn;

    @FindBy(id = "editCredentialBtn")
    private WebElement btnEditCredential;

    @FindBy(id = "deleteCredentialBtn")
    private WebElement deleteCredential;

    @FindBy(id = "credentialUrl")
    private WebElement credentialUrl;

    @FindBy(id = "credentialUsername")
    private WebElement credentialUsername;

    @FindBy(id = "credentialPassword")
    private WebElement credentialPassword;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id = "nav-files-tab")
    private WebElement navFilesTab;

    @FindBy(id = "noteModalLabel")
    private WebElement noteModal;

    @FindBy(id = "noteTitle")
    private WebElement noteTitle;

    @FindBy(id = "noteEditTitle")
    private WebElement noteEditTitle;

    @FindBy(id = "noteDescription")
    private WebElement noteDescription;

    @FindBy(id = "noteEditDescription")
    private WebElement noteEditDescription;

    @FindBy(id = "noteSaveChangesBtn")
    private WebElement noteSubmit;

    @FindBy(id = "noteSuccessMsg")
    private WebElement noteResultConfirm;

    @FindBy(id = "credentialSuccessMsg")
    private WebElement credentialResultConfirm;

    @FindBy(id = "fileSuccessMsg")
    private WebElement fileResultConfirm;

    @FindBy(id = "credentialSaveChangesBtn")
    private WebElement credentialSubmit;

    @FindBy(id = "noteUpdatedMsg")
    private WebElement noteUpdatedMsg;

    @FindBy(id = "noteEditSaveChangesBtn")
    private WebElement noteEditSaveChangesBtn;

    @FindBy(id = "delNoteRef")
    private WebElement deleteNoteConfirmation;

    @FindBy(id = "noteDeletedMsg")
    private WebElement noteDeleteResultMsg;

    public HomePage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean checkNoteTabIsOpened() {
        return this.navNotesTab.isDisplayed();
    }

    public boolean checkCredentialTabIsOpened() {
        return this.navCredentialsTab.isDisplayed();
    }

    public WebElement getAddNewNoteBtn() {
        return this.addNewNoteBtn;
    }

    public WebElement getAddCredentialBtn() {
        return this.addCredentialBtn;
    }

    public boolean checkNoteModalIsOpened() {
        return this.noteModal.isDisplayed();
    }

    public WebElement getNoteTitle() {
        return this.noteTitle;
    }

    public boolean checkNoteEditButtonIsDisplayed() {
        return this.noteEditBtn.isDisplayed();
    }

    public boolean checkCredentialEditButtonIsDisplayed() {
        return this.credentialEditBtn.isDisplayed();
    }

    public WebElement getNoteDescription() {
        return this.noteDescription;
    }

    public WebElement getNoteSubmit() {
        return this.noteSubmit;
    }

    public WebElement getCredentialSubmit() {
        return this.credentialSubmit;
    }

    public boolean checkNoteSuccessResultIsDisplayed() {
        return this.noteResultConfirm.isDisplayed();
    }

    public boolean checkCredentialSuccessResultIsDisplayed() {
        return this.credentialResultConfirm.isDisplayed();
    }

    public boolean checkFileSuccessResultIsDisplayed() {
        return this.fileResultConfirm.isDisplayed();
    }

    public void setNoteTitle(String title) {
        noteTitle.sendKeys(title);
    }

    public void setNoteDescription(String description) {
        noteDescription.sendKeys(description);
    }

    public void fillNoteFields(String title, String description) {
        this.noteTitle.sendKeys(title);
        this.noteDescription.sendKeys(description);
    }

    public void fillNoteEditFields(String title, String description) {
        this.noteEditTitle.clear();
        this.noteEditDescription.clear();
        this.noteEditTitle.sendKeys(title);
        this.noteEditDescription.sendKeys(description);
    }

    public void fillNewCredentialFields(String url, String username, String password) {
        this.credentialUrl.sendKeys(url);
        this.credentialUsername.sendKeys(username);
        this.credentialPassword.sendKeys(password);
    }

    public void openNotesTab() {
        navNotesTab.click();
    }

    public void openCredentialsTab() {
        navCredentialsTab.click();
    }

    public void openFilesTab() {
        navFilesTab.click();
    }

    public void chooseFile(String path) {
        chooseFile.sendKeys(path);
    }

    public void uploadFile() {
        fileUpload.click();
    }

    public void viewFile() {
        viewBtn.click();
    }

    public void deleteFile() {
        deleteBtn.click();
    }

    public void confirmFileDelete() {
        confirmFileDeletation.click();
    }

    public void editNote() {
        noteEditBtn.click();
    }

    public boolean checkNoteUpdatedSuccess() {
        return noteUpdatedMsg.isDisplayed();
    }

    public WebElement getNoteEditSaveChanges() {
        return this.noteEditSaveChangesBtn;
    }

    public void deleteNote() {
        deleteNoteBtn.click();
    }

    public void confirmNoteDelete() {
        deleteNoteConfirmation.click();
    }

    public void checkNoteDeleteResultMsgIsDisplayed() {
        noteDeleteResultMsg.isDisplayed();
    }

}
