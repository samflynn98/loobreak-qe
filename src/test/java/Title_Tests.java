import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Title_Tests {
    private WebDriver driver;

    @BeforeEach
    void launchBrowser() {
        Browser_Config config = new Browser_Config();
        driver = config.BrowserSelect("chrome");
        config.windowMode("maximize");
    }

    @Test
    public void homepageTitleCorrect() throws Exception {
        Homepage page = new Homepage(driver).navigate();
        assertEquals("LooBreak", page.getTitle());
    }

    @Test
    public void quizTitleCorrect() throws Exception {
        Quiz_Page page = new Quiz_Page(driver).navigate();
        assertEquals("LooBreak", page.getTitle()); //should each page have different title?
    }

    @Test
    public void quizPageShowsHeading() throws Exception {
        Quiz_Page page = new Quiz_Page(driver).navigate();
        Thread.sleep(2000);
        assertEquals("Quiz", page.getHeadingText());
    }

    @Test
    public void homepageShowsHeading() throws Exception {
        Homepage page = new Homepage(driver).navigate();
        assertEquals("Welcome to LooBreak!", page.getHeadingText());
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}