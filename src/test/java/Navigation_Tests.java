import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Navigation_Tests {
    private WebDriver driver;

    @BeforeEach
    void launchBrowser() {
        Browser_Config config = new Browser_Config();
        driver = config.BrowserSelect("chrome");
        config.windowMode("maximize");
    }

    //Navbar tests
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
    }

    //Homepage tests
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
        Quiz_Page page = new Quiz_Page(driver).navigate();
        Thread.sleep(2000);
        assertEquals("Question 1:", page.getQuestionNumber());
        page.answerQuestion(1);
        page.submitAnswer();
        page.goToNextQuestion();
        assertEquals("Question 2:", page.getQuestionNumber());
    }

    @Test
    public void getAnswerText() throws Exception {
        Quiz_Page page = new Quiz_Page(driver).navigate();
        Thread.sleep(2000);
        String q = page.getScore();
        String ans1 = page.getAnswer(1).getText();
        String ans2 = page.getAnswer(2).getText();
        String ans3 = page.getAnswer(3).getText();
        String ans4 = page.getAnswer(4).getText();
        System.out.println(q);
        System.out.println(ans1 + ans2 + ans3 + ans4);
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}