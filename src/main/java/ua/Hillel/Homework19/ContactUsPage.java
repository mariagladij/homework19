package ua.Hillel.Homework19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactUsPage {

    private WebDriver driver;

    private By nameInput = By.name("name");
    private By emailInput = By.name("email");
    private By subjectInput = By.name("subject");
    private By messageInput = By.name("message");
    private By chooseFile = By.name("upload_file");
    private By submitButton = By.name("submit");
    private By successMessage = By.cssSelector(".status.alert.alert-success");

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillContactForm(String name, String email, String subject, String message, String filePath) {
        WebElement nameElement = driver.findElement(nameInput);
        nameElement.sendKeys(name);

        WebElement emailElement = driver.findElement(emailInput);
        emailElement.sendKeys(email);

        WebElement subjectElement = driver.findElement(subjectInput);
        subjectElement.sendKeys(subject);

        WebElement messageElement = driver.findElement(messageInput);
        messageElement.sendKeys(message);

        WebElement fileElement = driver.findElement(chooseFile);
        fileElement.sendKeys(filePath);

        WebElement submitButtonElement = driver.findElement(submitButton);
        submitButtonElement.click();
    }

    public boolean isSuccessMessageDisplayed() {
        WebElement successMessageElement = driver.findElement(successMessage);
        return successMessageElement.isDisplayed();
    }
}
