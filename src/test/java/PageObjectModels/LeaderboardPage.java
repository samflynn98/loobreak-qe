package PageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeaderboardPage {
    protected WebDriver driver;

    public LeaderboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public LeaderboardPage navigate() {
        driver.get("http://localhost:5173/leaderboard");
        return this;
    }

    //Heading & title
    public String getTitle() {
        String title = driver.getTitle();
        return title;
    }

    public String getHeadingText() {
        WebElement heading = driver.findElement(By.tagName("h2"));
        return heading.getText();
    }

    //Player attributes
    public String getPlayerRank(int id) {
        try {
            return driver.findElement(By.cssSelector("tr:nth-child(" + id + ") > td:nth-child(1)")).getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return ""; // Return an empty string so your test loop knows to stop
        }
    }

    public String getPlayerName(int id) {
        try {
            return driver.findElement(By.cssSelector("tr:nth-child(" + id + ") > td:nth-child(2)")).getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return ""; // Return an empty string so your test loop knows to stop
        }
    }

    public String getPlayerScore(int id) {
        try {
            return driver.findElement(By.cssSelector("tr:nth-child(" + id + ") > td:nth-child(3)")).getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return ""; // Return an empty string so your test loop knows to stop
        }
    }
}