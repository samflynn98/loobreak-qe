import PageObjectModels.BrowserConfig;
import PageObjectModels.Homepage;
import PageObjectModels.Navbar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DarkModeTests {
    private WebDriver driver;

    @BeforeEach
    void launchBrowser() {
        BrowserConfig config = new BrowserConfig();
        driver = config.BrowserSelect("chrome");
        config.windowMode("maximize");
    }

    @Test
    public void darkAndLightBackgroundsCorrect() throws Exception {
        Homepage page = new Homepage(driver);
        page.navigate();
        Navbar navbar = new Navbar(driver);
        navbar.toggleDarkMode();
        WebElement body = driver.findElement(By.tagName("body"));
        String darkColour = body.getCssValue("background-color");
        assertTrue(darkColour.contains("0, 0, 0"));
        navbar.toggleDarkMode();
        String lightColour = body.getCssValue("background-color");
        assertTrue(lightColour.contains("255, 255, 255"));
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}