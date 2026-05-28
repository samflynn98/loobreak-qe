import org.openqa.selenium.WebDriver;

public class TodoPage {
    protected WebDriver driver;

    public TodoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get("https://todomvc.com/examples/react/dist/#/active");
    }
}