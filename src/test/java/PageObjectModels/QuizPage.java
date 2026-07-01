package PageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class QuizPage {
    protected WebDriver driver;

    public QuizPage(WebDriver driver) {
        this.driver = driver;
    }

    //Navigate
    public QuizPage navigate() {
        driver.get("http://localhost:5173/quiz");
        return this;
    }

    //Heading and Title
    public String getTitle() {
        String title = driver.getTitle();
        return title;
    }

    public String getHeadingText() {
        WebElement heading = driver.findElement(By.tagName("h2"));
        return heading.getText();
    }

    //Question Answering
    public void answerQuestion(int id) {
        driver.findElement(By.cssSelector("button:nth-child(" + id + ")")).click();
    }

    public void submitAnswer() {
        driver.findElement(By.cssSelector("div > button:nth-child(5)")).click();
    }

    public void goToNextQuestion() {
        driver.findElement(By.cssSelector("div > button:nth-child(5)")).click();
    }

    public void answerAllQuestions() {
        QuizPage page = new QuizPage(driver);
        while (Objects.equals(page.getHeadingText(), "Quiz")) {
            driver.findElement(By.cssSelector("button:nth-child(1)")).click();
            driver.findElement(By.cssSelector("div > button:nth-child(5)")).click();
            driver.findElement(By.cssSelector("div > button:nth-child(5)")).click();
        }
    }

    //Question attributes
    public String getQuestionNumber() {
        String QuestionNum = driver.findElement(By.cssSelector("h3")).getText();
        return QuestionNum;
    }

    public String getScore() {
        String score = driver.findElement(By.cssSelector("p:nth-child(3)")).getText();
        return score;
    }

    public WebElement getAnswer(int id) {
        WebElement answer = driver.findElement(By.cssSelector("button:nth-child(" + id + ")"));
        return answer;
    }

    //Results Sub Screen
    public void generatePlayername() {
        driver.findElement(By.cssSelector("button:nth-child(3)")).click();
    }

    public void enterPlayername(String name) {
        driver.findElement(By.id("playername")).sendKeys(name);
    }

    public String getPlayername() {
        WebElement playerNameInput = driver.findElement(By.name("playername"));
        String playername = playerNameInput.getAttribute("value");
        return playername;
    }

    public String getFinalScore() {
        String finalScore = driver.findElement(By.cssSelector("[data-testid='score']")).getText();
        return finalScore;
    }

    public void submitPlayername() {
        driver.findElement(By.cssSelector("form > button:nth-child(5)")).click();
    }

    public String getWarningMessage () {
        String warning = driver.findElement(By.cssSelector("p:nth-child(5)")).getText();
        return warning;
    }
}