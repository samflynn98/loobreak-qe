import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Example_Tests {
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
    public void getHomepageTitle() throws Exception {
        Homepage page = new Homepage(driver).navigate();
        page.get_title();
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}