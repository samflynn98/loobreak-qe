import PageObjectModels.BrowserConfig;
import PageObjectModels.Homepage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.*;

public class IcebreakerTests {
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
    public void icebreakersOnlyShowWhenActive() throws Exception {
        Homepage page = new Homepage(driver);
        page.navigate();
        Thread.sleep(100);
        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
            page.getIcebreakerText(); // This code block should throw the specified exception
        });
        page.toggleIcebreaker();
        Thread.sleep(100);
        assertTrue(page.getIcebreakerText().length() > 0);
        //config.takeScreenshot(driver, "IcebreakerTest1.png");
    }

    @Test
    public void icebreakersAreUnique() throws Exception {
        Homepage page = new Homepage(driver).navigate();
        page.navigate();
        int notUnique = 0;
        page.toggleIcebreaker();
        Thread.sleep(100);
        String firstIcebreaker = page.getIcebreakerText();
        page.toggleIcebreaker();
        int i = 0;
        while(i < 10) {
            page.toggleIcebreaker();
            Thread.sleep(100);
            String currentIcebreaker = page.getIcebreakerText();
            if (currentIcebreaker.equals(firstIcebreaker)) {
                notUnique++;
            }
            page.toggleIcebreaker();
            i++;
        }
        assertTrue(notUnique < 9);
        System.out.println("Number of repeated icebreakers: " + notUnique);
    }


    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}