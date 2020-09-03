package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    @FindBy(id= "successMessage")
    private WebElement successMessage;

    @FindBy(id= "errorMessage")
    private WebElement errorMessage;

    public ResultPage(WebDriver webDriver){
        PageFactory.initElements(webDriver,this);
    }

    public String getSuccessMessage(){
        return this.successMessage.getText();
    }

    public String getErrorMessage(){
        return this.errorMessage.getText();
    }
}
