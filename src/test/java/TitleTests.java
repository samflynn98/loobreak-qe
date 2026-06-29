import PageObjectModels.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TitleTests {
    private WebDriver driver;
    private BrowserConfig config;

    @BeforeEach
    void launchBrowser() {
        config = new BrowserConfig();
        BrowserConfig config = new BrowserConfig();
        driver = config.BrowserSelect("chrome");
        config.windowMode("portrait");
    }

    //Homepage
    @Test
    public void homepageTitleCorrect() throws Exception {
        Homepage page = new Homepage(driver).navigate();
        assertEquals("LooBreak", page.getTitle());
        //config.takeScreenshot(driver, "TitleTest1.png");
    }

    @Test
    public void homepageShowsHeading() throws Exception {
        Homepage page = new Homepage(driver).navigate();
        assertEquals("Welcome to LooBreak!", page.getHeadingText());
    }

    //Quiz page
    @Test
    public void quizTitleCorrect() throws Exception {
        QuizPage page = new QuizPage(driver).navigate();
        assertEquals("LooBreak", page.getTitle()); //should each page have different title?
    }

    @Test
    public void quizPageShowsHeading() throws Exception {
        QuizPage page = new QuizPage(driver).navigate();
        Thread.sleep(2000);
        assertEquals("Quiz", page.getHeadingText());
    }

    //Leaderboard page
    @Test
    public void leaderboardTitleCorrect() throws Exception {
        LeaderboardPage page = new LeaderboardPage(driver).navigate();
        assertEquals("LooBreak", page.getTitle()); //should each page have different title?
    }

    @Test
    public void leaderboardPageShowsHeading() throws Exception {
        LeaderboardPage page = new LeaderboardPage(driver).navigate();
        Thread.sleep(2000);
        assertEquals("Leaderboard", page.getHeadingText());
    }

    //Results page
    @Test
    public void resultsTitleCorrect() throws Exception {
        ResultsPage page = new ResultsPage(driver).navigate();
        assertEquals("LooBreak", page.getTitle()); //should each page have different title?
    }

    @Test
    public void resultsPageShowsHeading() throws Exception {
        ResultsPage page = new ResultsPage(driver).navigate();
        Thread.sleep(2000);
        assertEquals("Your Results", page.getHeadingText());
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}