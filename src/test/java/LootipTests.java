import PageObjectModels.BrowserConfig;
import PageObjectModels.Homepage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LootipTests {
    private WebDriver driver;
    private BrowserConfig config;

    @BeforeEach
    void launchBrowser() {
        config = new BrowserConfig();
        BrowserConfig config = new BrowserConfig();
        driver = config.BrowserSelect("chrome");
        config.windowMode("portrait");
    }

    @Test
    public void lootipsAreUnique() throws Exception {
        Homepage page = new Homepage(driver).navigate();
        page.navigate();
        int notUnique = 0;
        Thread.sleep(100);
        String firstLooTip = page.getLootipText();
        int i = 0;
        while(i < 10) {
            Thread.sleep(100);
            String currentLooTip = page.getLootipText();
            if (currentLooTip.equals(firstLooTip)) {
                notUnique++;
            }
            i++;
            driver.navigate().refresh();
        }
        assertTrue(notUnique < 9);
        System.out.println("Number of repeated lootips: " + notUnique);
    }


    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}