import PageObjectModels.BrowserConfig;
import PageObjectModels.QuizPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayernameTests {
    private WebDriver driver;
    private BrowserConfig config;

    @BeforeEach
    void launchBrowser() {
        config = new BrowserConfig();
        BrowserConfig config = new BrowserConfig();
        driver = config.BrowserSelect("chrome");
        config.windowMode("portrait");
    }

    //Playername Tests
    @Test
    public void randomPlayernamesUnique() throws Exception {
        QuizPage page = new QuizPage(driver);
        page.navigate();
        Thread.sleep(200);
        page.answerAllQuestions();
        int notUnique = 0;
        page.generatePlayername();
        String firstUsername = page.getPlayername();
        int i = 0;
        while(i < 50) {
            page.generatePlayername();
            String currentUsername = page.getPlayername();
            if (currentUsername.equals(firstUsername)) {
                notUnique++;
            }
            i++;
        }
        System.out.println("Number of repeated usernames: " + notUnique);
        assertTrue(notUnique < 10);
    }

    @Test
    public void duplicatePlayernameDisallowed() throws Exception {
        QuizPage page = new QuizPage(driver).navigate();
        Thread.sleep(100);
        page.answerAllQuestions();
        Thread.sleep(100);
        page.generatePlayername();
        String playername = page.getPlayername();
        page.submitPlayername();
        page.navigate();
        Thread.sleep(100);
        page.answerAllQuestions();
        Thread.sleep(100);
        page.enterPlayername(playername);
        page.submitPlayername();
        Thread.sleep(100);
        assertEquals("Playername already exists. Playername must be unique.", page.getWarningMessage());
    }

    @Test
    public void shortPlayernameDisallowed() throws Exception {
        QuizPage page = new QuizPage(driver).navigate();
        Thread.sleep(100);
        page.answerAllQuestions();
        Thread.sleep(100);
        page.enterPlayername("ab");
        page.submitPlayername();
        assertEquals("Playername must be more than 3 characters long", page.getWarningMessage());
    }

    @Test
    public void longPlayernameDisallowed() throws Exception {
        QuizPage page = new QuizPage(driver).navigate();
        Thread.sleep(100);
        page.answerAllQuestions();
        Thread.sleep(100);
        page.enterPlayername("abcdefghijklm");
        page.submitPlayername();
        assertEquals("Playername must be less than 12 characters long", page.getWarningMessage());
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}