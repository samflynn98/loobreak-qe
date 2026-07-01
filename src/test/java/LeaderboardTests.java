import PageObjectModels.BrowserConfig;
import PageObjectModels.LeaderboardPage;
import PageObjectModels.QuizPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderboardTests {
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
    public void canGetPlayerInfo() throws Exception {
        LeaderboardPage page = new LeaderboardPage(driver).navigate();
        Thread.sleep(2000);
        int i = 1;
        while (page.getPlayerName(i) != null && !page.getPlayerName(i).trim().isEmpty()) {
            if (i == 1) {
                assertEquals(page.getPlayerRank(i), "\uD83E\uDD47");
            } else if (i == 2) {
                assertEquals(page.getPlayerRank(i), "\uD83E\uDD48");
            } else if (i == 3) {
                assertEquals(page.getPlayerRank(i), "\uD83E\uDD49");
            } else {
                assertEquals(page.getPlayerRank(i), Integer.toString(i));
            }
            assertFalse(page.getPlayerName(i).isEmpty());
            assertTrue(page.getPlayerScore(i).contains("10"));
            System.out.println(page.getPlayerRank(i) + " " + page.getPlayerName(i) + " " + page.getPlayerScore(i));
            i++;
        }
    }

    @Test
    public void playernameAppearsOnLeaderboard() throws Exception {
        QuizPage Quizpage = new QuizPage(driver).navigate();
        LeaderboardPage leaderboardPage = new LeaderboardPage(driver);
        Thread.sleep(100);
        Quizpage.answerAllQuestions();
        Thread.sleep(100);
        Quizpage.generatePlayername();
        String score = Quizpage.getFinalScore();
        String playername = Quizpage.getPlayername();
        Quizpage.submitPlayername();
        Thread.sleep(100);
        // 1. Locate the row
        WebElement playerRow = driver.findElement(By.xpath("//div[@data-testid='leaderboard']//tr[td[text()='" + playername + "']]"));
        // 2. Extract data from that specific row (Score is the 3rd column / 3rd td)
        String rank = playerRow.findElement(By.xpath("./td[1]")).getText();
        assertEquals(score, playerRow.findElement(By.xpath("./td[3]")).getText());
        System.out.println(playername + " is rank " + rank + " with a score of " + score);
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}