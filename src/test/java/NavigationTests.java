import PageObjectModels.BrowserConfig;
import PageObjectModels.Homepage;
import PageObjectModels.Navbar;
import PageObjectModels.QuizPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavigationTests {
    private WebDriver driver;
    private BrowserConfig config;

    @BeforeEach
    void launchBrowser() {
        config = new BrowserConfig();
        BrowserConfig config = new BrowserConfig();
        driver = config.BrowserSelect("chrome");
        config.windowMode("portrait");
    }

    //PageObjectModels.Navbar tests
    @Test
    public void canGoToPageFromNavbar() throws Exception {
        Homepage page = new Homepage(driver);
        page.navigate();
        Navbar navbar = new Navbar(driver);
        navbar.goToNavbarPage("Quiz");
        assertEquals("http://localhost:5173/quiz", driver.getCurrentUrl());
        Thread.sleep(2000);
        navbar.goToNavbarPage("Leaderboard");
        assertEquals("http://localhost:5173/leaderboard", driver.getCurrentUrl());
        navbar.goToNavbarPage("Home");
        assertEquals("http://localhost:5173/", driver.getCurrentUrl());
        //config.takeScreenshot(driver, "NavigationTest1.png");
    }

    //PageObjectModels.Homepage tests
    @Test
    public void canNavigateToQuizPageFromHomepage() throws Exception {
        Homepage page = new Homepage(driver).navigate();
        page.navigateToPage("quiz").click();
        assertEquals("http://localhost:5173/quiz", driver.getCurrentUrl());
    }

    @Test
    public void canNavigateToLeaderboardPageFromHomepage() throws Exception {
        Homepage page = new Homepage(driver).navigate();
        page.navigateToPage("leaderboard").click();
        assertEquals("http://localhost:5173/leaderboard", driver.getCurrentUrl());
    }

    //Quiz page tests
    @Test
    public void canNavigateToNextQuizQuestion() throws Exception {
        QuizPage page = new QuizPage(driver).navigate();
        Thread.sleep(2000);
        assertEquals("Question 1:", page.getQuestionNumber());
        page.answerQuestion(1);
        page.submitAnswer();
        page.goToNextQuestion();
        assertEquals("Question 2:", page.getQuestionNumber());
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}