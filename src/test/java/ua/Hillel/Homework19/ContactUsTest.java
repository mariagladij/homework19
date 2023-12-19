package ua.Hillel.Homework19;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ContactUsTest {

    private WebDriver driver;
    private ContactUsPage contactUsPage;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        contactUsPage = new ContactUsPage(driver);
        WebElement openContuctUs = driver.findElement(By.cssSelector("#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(8) > a"));
        openContuctUs.click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testContactUsForm() throws IOException, InterruptedException {
        // Завантаження даних з .properties файлу
        File file = new File("src/test/test-data.properties");

        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Навігація на сторінку Contact Us
        contactUsPage.fillContactForm(
                properties.getProperty("name"),
                properties.getProperty("email"),
                properties.getProperty("subject"),
                properties.getProperty("message"),
                properties.getProperty("file_path")
        );

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

        // Перевірка, що з'явилося повідомлення про успішне відправлення форми
        String successMessage = String.valueOf(contactUsPage.isSuccessMessageDisplayed());
        Assert.assertNotNull(successMessage);

    }
}
