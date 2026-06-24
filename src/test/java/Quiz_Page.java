import org.openqa.selenium.WebDriver;

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
}