import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Navigation_Tests {
    private WebDriver driver;
    String browser = "chrome";
    String browserMode = "mobile"; //to do: add mobile mode

    @BeforeEach
    void launchBrowser() {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equals("safari")) {
            driver = new SafariDriver();
        }
        driver.manage().window().maximize();
    }

    @Test
    public void navigateToQuizPage() throws Exception {
        Homepage page = new Homepage(driver).navigate();
        page.navigateToPage().click();
        assertEquals("http://localhost:5173/quiz", driver.getCurrentUrl());
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}