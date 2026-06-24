import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage {
    protected WebDriver driver;

    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    public Homepage navigate() {
        driver.get("http://localhost:5173/");
        return this;
    }

    public WebElement navigateToPage() {
        WebElement newPage = driver.findElement(By.cssSelector("a[href*='quiz']"));
        return newPage;
    }

    public String get_title() {
        String title = driver.getTitle();
        return title;
    }
}