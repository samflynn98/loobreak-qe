import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Quiz_Page {
    protected WebDriver driver;

    public Quiz_Page(WebDriver driver) {
        this.driver = driver;
    }

    public Quiz_Page navigate() {
        driver.get("http://localhost:5173/quiz");
        return this;
    }

    public String getTitle() {
        String title = driver.getTitle();
        return title;
    }

    public String getHeadingText() {
        WebElement heading = driver.findElement(By.tagName("h2"));
        return heading.getText();
    }

    public void answerQuestion(int id) {
        driver.findElement(By.cssSelector("button:nth-child(" + id + ")")).click();
    }

    public void submitAnswer() {
        driver.findElement(By.cssSelector(".feed > button")).click();
    }

    public void goToNextQuestion() {
        driver.findElement(By.cssSelector(".feed > button")).click();
    }

    public String getQuestionNumber() {
        String QuestionNum = driver.findElement(By.cssSelector("h3")).getText();
        return QuestionNum;
    }

    public String getScore() {
        String question = driver.findElement(By.tagName("p")).getText();
        return question;
    }

    public WebElement getAnswer(int id) {
        WebElement answer = driver.findElement(By.cssSelector("button:nth-child(" + id + ")"));
        return answer;
    }
}