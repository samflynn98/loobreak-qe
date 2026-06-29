import PageObjectModels.BrowserConfig;
import PageObjectModels.QuizPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuizTests {
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
    public void threeRedOneGreenAnswer() throws Exception {
        QuizPage page = new QuizPage(driver);
        page.navigate();
        Thread.sleep(200);
        page.answerQuestion(1);
        page.submitAnswer();
        int red = 0, green = 0;
        for (int i = 1; i <= 4; i++) {
            String colour = page.getAnswer(i).getCssValue("background-color");
            System.out.println(colour);
            if (colour.contains("0, 128, 0")) {
                green++;
            } else if (colour.contains("255, 0, 0")) {
                red++;
            }
        }
        assertEquals(1, green);
        assertEquals(3, red);
    }

    @Test
    public void scoreIncrementsCorrectly() throws Exception {
        QuizPage page = new QuizPage(driver);
        page.navigate();
        int correctAnswers = 0;
        for (int i = 1; i < 20; i++) {
            Thread.sleep(100);
            assertTrue(page.getScore().contains(Integer.toString(correctAnswers)));
            Random rand = new Random();
            int nextRandom = rand.nextInt(1, 5);
            page.answerQuestion(nextRandom);
            page.submitAnswer();
            String answerColour = page.getAnswer(nextRandom).getCssValue("background-color");
            if (answerColour.contains("0, 128, 0")) {
                correctAnswers++;
            }
        }
        //config.takeScreenshot(driver, "QuizTest1.png");
        System.out.println("number correct: " + correctAnswers);
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}