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

    public String get_title() {
        String title = driver.getTitle();
        return title;
    }

    public String getHeadingText() {
        WebElement heading = driver.findElement(By.tagName("h2"));
        return heading.getText();
    }
}