package org.academy.web.pages;

import org.academy.MainConfig;
import org.academy.web.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {
    public LoginPage(WebDriver webDriver) {
        super(webDriver, false, "");
    }
    public LoginPage(WebDriver webDriver, boolean navigateToPage) {
        super(webDriver, navigateToPage);
    }


    @FindBy(xpath = "//input[@name='login']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passField;

    @FindBy(xpath = "//input[@name='commit']")
    private WebElement submitForm;

    @FindBy(xpath = "//div[@class='container-lg px-2']")
    private WebElement errorMessage;

    public LoginPage fillLoginField(String login) {
        loginField.sendKeys(login);
        return this;
    }

    public LoginPage fillPassField(String pass) {
        passField.sendKeys(pass);
        return this;
    }

    public LoginPage clickOnSubmit() {
        submitForm.click();

        return ((webDriver.getTitle().equals("Sign in to GitHub · GitHub")) ||
                webDriver.getTitle().equals("GitHub")) ? this : new HelpPage(webDriver)
                .goToLoginPage();

       /* if (webDriver.getTitle().equals("Sign in to GitHub · GitHub")){
            return this;
        }
        else if( (new LoginPage(webDriver)).getErrorMessage().equals("Incorrect username or password.")){
            return this;
        }
          else {  return    new HelpPage(webDriver)
                .goToLoginPage();}*/
    }

    public BasePage clickOnSignIn() {
        return new BasePage(webDriver);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public BasePage login(){
        loginField.sendKeys(MainConfig.getLogin());
        passField.sendKeys(MainConfig.getPassword());
        submitForm.click();
        return new BasePage(webDriver, false);
    }


}