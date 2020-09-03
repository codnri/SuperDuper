package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "nav-files-tab")
    private WebElement filesTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    //--- notes
    @FindBy(id = "addNoteButton")
    private WebElement addNoteButton;

    @FindBy(id = "closeNoteModalButton")
    private WebElement closeNoteModalButton;

    @FindBy(id = "note-title")
    private WebElement noteTitleInput;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionInput;

    @FindBy(id = "saveNoteButton")
    private WebElement saveNoteButton;

    @FindBy(className = "editNoteButton")
    private List<WebElement> editNoteButtonList;

    @FindBy(className = "deleteNoteButton")
    private List<WebElement> deleteNoteButtonList;

    @FindBy(className = "noteTitleText")
    private List<WebElement> noteTitleList;

    @FindBy(className = "noteDescriptionText")
    private List<WebElement> noteDescriptionList;

    //--- credential
    @FindBy(id = "addCredentialButton")
    private WebElement addCredentialButton;

    @FindBy(className = "credentialUrlText")
    private List<WebElement> credentialUrlTextList;

    @FindBy(className = "credentialUsernameText")
    private List<WebElement> credentialUsernameTextList;

    @FindBy(id = "credential-url")
    private WebElement credentialUrlField;

    @FindBy(id = "credential-username")
    private WebElement credentialUsernameField;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordField;

    @FindBy(id = "saveCredentialButton")
    private WebElement saveCredentialButton;

    @FindBy(className = "editCredentialButton")
    private List<WebElement> editCredentialButtonList;

    @FindBy(className = "deleteCredentialButton")
    private List<WebElement> deleteCredentialButtonList;

    @FindBy(id = "closeCredentialModalButton")
    private WebElement closeCredentialModalButton;




    public void goToNotesTab(){
        this.notesTab.click();
    }
    public void goToCredentialsTab(){
        this.credentialsTab.click();
    }

    public HomePage(WebDriver webDriver){
        PageFactory.initElements(webDriver,this);
    }

    public void createNewNote(String title,String description) throws InterruptedException {
        this.notesTab.click();
        Thread.sleep(1000);
        this.addNoteButton.click();
        Thread.sleep(1000);
        this.noteTitleInput.sendKeys(title);
        this.noteDescriptionInput.sendKeys(description);
        this.saveNoteButton.click();
    }

    public void editFirstNote(String title,String description) throws InterruptedException {
        this.notesTab.click();
        Thread.sleep(1000);
        this.editNoteButtonList.get(0).click();
        Thread.sleep(1000);
        this.noteTitleInput.clear();
        this.noteTitleInput.sendKeys(title);
        this.noteDescriptionInput.clear();
        this.noteDescriptionInput.sendKeys(description);
        this.saveNoteButton.click();

    }

    public void deleteFirstNote(WebDriver driver) throws InterruptedException {
        this.notesTab.click();
        Thread.sleep(1000);
        this.deleteNoteButtonList.get(0).click();
        driver.switchTo().alert().accept();//click a confirm button

    }

    public Boolean notesDoesNotExist(){
        return this.noteDescriptionList.size() == 0 && this.noteTitleList.size() == 0;
    }
    public Boolean credentialsDoesNotExist(){
        return this.credentialUrlTextList.size() == 0 && this.credentialUsernameTextList.size() == 0;
    }

    public String getFirstNoteTitle(){
        return this.noteTitleList.get(0).getText();
    }
    public String getFirstNoteDescription(){
        return  this.noteDescriptionList.get(0).getText();
    }

    public void createNewCredential(String url,String username,String password) throws InterruptedException {
        this.credentialsTab.click();
        Thread.sleep(1000);
        this.addCredentialButton.click();
        Thread.sleep(1000);
        this.credentialUrlField.sendKeys(url);
        this.credentialUsernameField.sendKeys(username);
        this.credentialPasswordField.sendKeys(password);
        Thread.sleep(1000);
        this.saveCredentialButton.click();


    }

    public void editDirstCredential(String url,String username,String password) throws InterruptedException {
        this.credentialsTab.click();
        Thread.sleep(1000);
        this.editCredentialButtonList.get(0).click();
        Thread.sleep(1000);

        this.credentialUrlField.clear();
        this.credentialUrlField.sendKeys(url);

        this.credentialUsernameField.clear();
        this.credentialUsernameField.sendKeys(username);

        this.credentialPasswordField.clear();
        this.credentialPasswordField.sendKeys(password);

        this.saveCredentialButton.click();
        Thread.sleep(1000);

    }

    public void deleteFirstCredential(WebDriver driver){
        this.credentialsTab.click();
        this.deleteCredentialButtonList.get(0).click();
        driver.switchTo().alert().accept();
    }

    public String getFirstCredentialUrl(){
        return credentialUrlTextList.get(0).getText();
    }

    public String getFirstCredentialUsername(){
        return credentialUsernameTextList.get(0).getText();
    }

    public String getFirstCredentialPassword() throws InterruptedException {
        this.editCredentialButtonList.get(0).click();
        Thread.sleep(1000);
        String res = this.credentialPasswordField.getAttribute("value");
        this.closeCredentialModalButton.click();
        Thread.sleep(1000);
        return res;
    }



    public void logout(){
        this.logoutButton.click();

    }
}
