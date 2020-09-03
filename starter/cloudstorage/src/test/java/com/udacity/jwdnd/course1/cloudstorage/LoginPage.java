package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id="inputUsername")
    private WebElement usernameField;

    @FindBy(id="inputPassword")
    private WebElement passwordField;

    @FindBy(id="loginButton")
    private WebElement loginButton;

    @FindBy(id="error-msg")
    private WebElement loginErrorMessage;

    public LoginPage(WebDriver webDriver){
        PageFactory.initElements(webDriver,this);
    }
    public void login(String username,String password){
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        loginButton.click();
    }

    public String getLoginErrorMessage(){
        return this.loginErrorMessage.getText();
    }


}
