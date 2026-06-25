import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Title_Tests {
    private WebDriver driver;

    @BeforeEach
    void launchBrowser() {
        Browser_Config config = new Browser_Config();
        driver = config.BrowserSelect("firefox");
        config.windowMode("portrait");
    }

    @Test
    public void getHomepageTitle() throws Exception {
        Homepage page = new Homepage(driver).navigate();
        assertEquals("LooBreak", page.get_title());
    }

    @Test
    public void getQuizTitle() throws Exception {
        Quiz_Page page = new Quiz_Page(driver).navigate();
        assertEquals("LooBreak", page.get_title()); //should each page have different title?
    }

    @Test
    public void quizPageShowsQuizHeading() {
        Quiz_Page page = new Quiz_Page(driver).navigate();
        assertEquals("Quiz", page.getHeadingText());
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}